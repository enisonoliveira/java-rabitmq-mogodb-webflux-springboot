package com.sicredi.service;

import com.google.gson.Gson;
import com.sicredi.dao.SessionRepository;
import com.sicredi.jms.producer.Producer;
import com.sicredi.model.Pauta;
import com.sicredi.model.Session;
import com.sicredi.model.SessionVoting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository repository;

    @Autowired
    private SessionVotingService sessionVotingService;

    @Autowired
    private  PautaService pautaService;


    @Autowired
    Producer rabbitMQSender;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( SessionService.class );
    }


    @Async
    public  Pauta startSession(Session session) throws IllegalAccessException, ParseException {

        //Inicia a sessao para a votaçao
        session = save ( session );
        //delay de um minuto para a o termino da votacao
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         //Finaliza a sessao
        update ( session );
        logger.info("Finished process.");
       //Faz a apurçao da votacao
        Pauta pauta = pauta ( session );
        //envia o resultado via mensageria
        Gson gson = new Gson ();
        rabbitMQSender.sendAll ("Finish job session ");
        rabbitMQSender.sendAll (gson.toJson ( pauta ));

        return pauta;
    }

    private Pauta pauta( Session session ) throws IllegalAccessException {

        int totalVoteNotFavorable=0;
        int totalVoteFavorable=0;

        List < SessionVoting > votinOptional = sessionVotingService.getSessionVotin (  session.get_id () );
        Optional<Pauta> pautaOptional=pautaService.findOne ( session.getPauta ().get_id ());
        Pauta pauta=pautaOptional.get ();

        for ( SessionVoting votin:votinOptional ) {
            if(votin.isVote ()){
                totalVoteFavorable++;
            }else {
                totalVoteNotFavorable++;
            }
        }
        pauta.setTotalVoteNotFavorable ( totalVoteNotFavorable );
        pauta.setTotalVoteFavorable ( totalVoteFavorable );
        pautaService.update ( pauta );

        return pauta;
    }

    public Session save ( Session session ) throws ParseException, IllegalAccessException {
        session.setStartSession ( getDateStartSession ( ) );
        session.setEndSession ( getDateEndSession ( session.getStartSession ( ) ) );
        repository.save ( session );

        return session;
    }

    private Session update ( Session session ) throws ParseException, IllegalAccessException {

        session.setFinish_session ( true );
        repository.save ( session );

        return session;
    }

    protected boolean compareIntervalDate ( String _id ) {

        Optional < Session > optionalSession = findById ( _id );
        Session session = optionalSession.get ( );
        Date currentDate = new Date ( );
        return currentDate.after ( session.getStartSession ( ) ) && currentDate.before ( session.getEndSession ( ) );

    }

    private Date getDateStartSession ( ) throws ParseException, IllegalAccessException {
        return getDateNow ( );
    }

    private Date getDateNow ( ) {
        //America/Sao_Paulo
        ZoneId z = ZoneId.of ( "America/Sao_Paulo" );
        ZonedDateTime dateTime = ZonedDateTime.now ( z );
        Calendar c = Calendar.getInstance ( );
        c.set ( Calendar.MONTH , dateTime.getMonthValue ( ) );
        c.set ( Calendar.DATE , dateTime.getDayOfMonth ( ) );
        c.set ( Calendar.YEAR , dateTime.getYear ( ) );
        c.set ( Calendar.HOUR , dateTime.getHour ( ) );
        c.set ( Calendar.SECOND , dateTime.getSecond ( ) );
        Date date = c.getTime ( );
        return date;
    }

    private Date getDateEndSession ( Date startSession ) throws ParseException, IllegalAccessException {

        Date endDate = new Date ( getDateStartSession ( ).getTime ( ) + ( 60000 ) );
        return endDate;
    }

    public Optional < Session > findById ( String _id ) {
        return repository.findById ( _id );
    }

}
