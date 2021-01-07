package com.sicredi.service;

import com.sicredi.dao.SessionVotinRepository;
import com.sicredi.model.SessionVoting;
import com.sicredi.request.SessionVotinRequest;
import com.sicredi.serviceimpl.SessionVotinImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;


@Service
public class SessionVotingService implements SessionVotinImpl {

    @Autowired
    private SessionVotinRepository repository;

    @Autowired
    private SessionService sessionService;

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( SessionVotingService.class );
    }

    public SessionVoting save ( SessionVotinRequest sessionVotinRequest ) throws Exception {

        logger.info ( "computando voto " );
        SessionVoting sessionVotin = sessionVotinRequest.toSession ( sessionVotinRequest );
        String user_id = sessionVotin.getUser ( ).getId ( );
        String session_id = sessionVotin.getSession ( ).getId ( );
        logger.info ( "session_id: "+session_id + "user_id :" +user_id );
        if ( existsVotinSession ( user_id , session_id ) ) {
            logger.error ( "Usuário já votou nessa sessão!" );
            throw new DuplicateFormatFlagsException ( ": Operação não permitida! Usuário ja votou nessa sessão" );
        }
        if ( ! validateTimeSession ( sessionVotin.getSession ( ).getId ( ) ) ) {
            logger.error ( ": Tempo esgotado para a votação!" );
            throw new DuplicateFormatFlagsException ( ": Operação não permitida! Tempo esgotado para a votação." );
        }
        repository.save ( sessionVotin );
        logger.info ( ": save data vote" );
        logger.info ( ": Voto computado com sucesso!" );
        logger.info ( ": user ID : " + sessionVotin.getUser ( ).getId ( ) );
        return sessionVotin;
    }

    public boolean validateTimeSession ( String session_id ) throws Exception {

        if ( session_id.equals ( "" )  || session_id==null){
            throw new IllegalAccessException ( " : Certifique de passar um usuario e sessao valida! " );
        }
        return sessionService.compareIntervalDate ( session_id );
    }

    public boolean existsVotinSession ( String user_id , String session_id ) {

        List < SessionVoting > sessionVotin = repository.findSessionVotinUser ( user_id , session_id );

        if (sessionVotin.size ()>0 ) {
            logger.info ( "id user="+user_id );
            logger.info ( "id session="+session_id );
            logger.info ( " usuario ja votou na sessao :" +sessionVotin.size ());
            return true;
        }
        return false;
    }

    public List<SessionVoting> getSessionVotin( String session_id) throws IllegalAccessException {

        logger.info (" session_id:"+ session_id );
        if((session_id.equals ( "" ) || session_id==null)|| (session_id.equals ( "" ) || session_id==null)){
            throw new IllegalAccessException ( " : Certifique de passar um usuario e sessao valida! " );
        }

        List<SessionVoting> list= repository.findSessionVotin ( session_id );
        list.forEach ( s->{
            logger.info ("Pauta name:"+ s.getSession ().getPauta ().getName () );
            logger.info ("Session:"+ s.getSession ().getId () );
            logger.info ("id:"+ s.getId () );
            logger.info ("CPF:"+ s.getUser ().getCPF () );
            logger.info ("Voto:"+ s.isVote () );
        } );
        return  list;
    }
}
