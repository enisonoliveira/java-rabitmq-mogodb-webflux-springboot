package com.sicredi.service;

import com.sicredi.dao.SessionVotinRepository;
import com.sicredi.model.SessionVoting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;


@Service
public class SessionVotingService {

    @Autowired
    private SessionVotinRepository repository;

    @Autowired
    private SessionService sessionService;



    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( SessionVotingService.class );
    }

    public SessionVoting save ( SessionVoting sessionVotin ) throws IllegalAccessException {

        String user_id = sessionVotin.getUser ( ).get_id ( );
        String session_id = sessionVotin.getSession ( ).get_id ( );
        if ( validVoteUserExists ( user_id , session_id ) ) {
            logger.error ( "Usuário já votou nessa sessão!" );
            throw new DuplicateFormatFlagsException ( ": Operação não permitida! Usuário ja votou nessa sessão" );
        }
        if ( ! validateTimeSession ( sessionVotin.getSession ( ).get_id ( ) ) ) {
            logger.error ( ": Tempo esgotado para a votação!" );
            throw new DuplicateFormatFlagsException ( ": Operação não permitida! Tempo esgotado para a votação." );
        }
        repository.save ( sessionVotin );
        logger.info ( ": save data vote" );
        logger.info ( ": Voto computado com sucesso!" );
        logger.info ( ": user ID : " + sessionVotin.getUser ( ).get_id ( ) );
        return sessionVotin;
    }

    private boolean validateTimeSession ( String session_id ) throws IllegalAccessException {

        if ( session_id.equals ( "" )  || session_id==null){
            throw new IllegalAccessException ( " : Certifique de passar um usuario e sessao valida! " );
        }
        return sessionService.compareIntervalDate ( session_id );
    }


    public boolean validVoteUserExists ( String user_id , String session_id ) throws IllegalAccessException {

        if((user_id.equals ( "" ) || user_id==null)|| (session_id.equals ( "" ) || session_id==null)){
            throw new IllegalAccessException ( " : Certifique de passar um usuario e sessao valida! " );
        }

        if ( exists ( user_id , session_id ) ) {
            return true;
        }
        return false;
    }

    private boolean exists ( String user_id , String session_id ) {

        List < SessionVoting > sessionVotin = repository.findSessionVotinUser ( user_id , session_id );

        if ( sessionVotin != null || sessionVotin.size ()>0 ) {
            return false;
        }
        return true;
    }

    public List<SessionVoting> getSessionVotin( String session_id) throws IllegalAccessException {

        logger.info (" session_id:"+ session_id );
        if((session_id.equals ( "" ) || session_id==null)|| (session_id.equals ( "" ) || session_id==null)){
            throw new IllegalAccessException ( " : Certifique de passar um usuario e sessao valida! " );
        }

        List<SessionVoting> list= repository.findSessionVotin ( session_id );
        list.forEach ( s->{
            logger.info ("Pauta name:"+ s.getSession ().getPauta ().getName () );
            logger.info ("Session:"+ s.getSession ().get_id () );
            logger.info ("id:"+ s.get_id () );
            logger.info ("CPF:"+ s.getUser ().getCPF () );
            logger.info ("Voto:"+ s.isVote () );
        } );
        return  list;
    }


}
