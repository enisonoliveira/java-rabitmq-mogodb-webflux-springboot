package com.sicredi.service;

import com.sicredi.dao.SessionRepository;
import com.sicredi.model.Pauta;
import com.sicredi.model.Session;
import com.sicredi.model.SessionVoting;
import com.sicredi.request.SessionRequest;
import com.sicredi.serviceimpl.SessionImpl;
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
public class SessionService implements SessionImpl {

    @Autowired
    private SessionRepository repository;

    @Autowired
    private SessionVotingService sessionVotingService;

    @Autowired
    private PautaService pautaService;


    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( SessionService.class );
    }


    @Async
    public Pauta startSession( Session session) throws IllegalAccessException, ParseException {

        //delay de um minuto para a o termino da votacao
        initSession ( session );
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         //Finaliza a sessao
        finishSession ( session );
        logger.info("Finished process.");
       //Faz a apurçao da votacao
        Pauta pauta = pauta ( session );

        return pauta;
    }

    public Pauta pauta( Session session ) throws IllegalAccessException {

        int totalVoteNotFavorable=0;
        int totalVoteFavorable=0;

        List < SessionVoting > votinOptional = sessionVotingService.getSessionVotin (  session.getId () );
        Optional< Pauta > pautaOptional= pautaService.findOne ( session.getPauta ().getId ());
        Pauta pauta =pautaOptional.get ();

        for ( SessionVoting votin:votinOptional ) {
            if(votin.isVote ()){
                totalVoteFavorable++;
            }else {
                totalVoteNotFavorable++;
            }
        }
        pauta.setTotalVoteNotFavorable ( totalVoteNotFavorable );
        pauta.setTotalVoteFavorable ( totalVoteFavorable );
        logger.info ( "update pauta ID "+ pauta.getId () );
        pautaService.update (  pauta );

        return pauta;
    }

    public Session save ( SessionRequest sessionRequest ) throws ParseException, IllegalAccessException {

            Session session = sessionRequest.toSession ( sessionRequest );
             repository.save ( session );

        return session;
    }

    public Session initSession ( Session session ) throws ParseException, IllegalAccessException {

        session.setStartSession ( getDateStartSession ( ) );
        session.setEndSession ( getDateEndSession ( session.getStartSession ( ) ) );
        session.setInitSession ( true );
        repository.save ( session );

        return session;
    }


    public Session finishSession ( Session session ) throws ParseException, IllegalAccessException {

        session.setFinisSession ( true );
        repository.save ( session );

        return session;
    }

    public boolean compareIntervalDate ( String _id ) throws Exception {

        Optional < Session > optionalSession = findById ( _id );
        Session session = optionalSession.get ( );
        Date currentDate = new Date ( );
        if( session.getEndSession ( )==null){
            return true;
        }
        return currentDate.after (  session.getEndSession ( ) );

    }

    public Date getDateStartSession ( ) throws ParseException, IllegalAccessException {
        return getDateNow ( );
    }

    public Date getDateNow ( ) {
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

    public Date getDateEndSession ( Date startSession ) throws ParseException, IllegalAccessException {

        Date endDate = new Date ( getDateStartSession ( ).getTime ( ) + ( 60000 ) );
        return endDate;
    }

    public boolean sessionHasInitialized(String sesssion_id) throws Exception {

        Optional<Session> sessionOptional=findById ( sesssion_id );
        Session session=sessionOptional.get ();
        return session.isInitSession ();
    }

    public Optional < Session > findById ( String id ) throws Exception {
        if( ! repository.existsById ( id )){
            throw new Exception (" Sessao nao encontrada!" );
        }
        Optional<Session> sessionOptional =repository.findById ( id );
        logger.info ( "Sessão encontrada ID "+sessionOptional.get ().getId () );

        return sessionOptional;
    }

    @Override
    public Optional < Session > findPautaId ( String pauta_id ) {
        return repository.findPautaId ( pauta_id );
    }

}
