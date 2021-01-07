package com.sicredi.serviceimpl;

import com.sicredi.model.SessionVoting;
import com.sicredi.model.User;
import com.sicredi.request.SessionVotinRequest;

import java.util.List;
import java.util.Optional;

public interface SessionVotinImpl{

    public SessionVoting save ( SessionVotinRequest sessionVotinRequest ) throws IllegalAccessException ;

    public boolean validateTimeSession ( String session_id ) throws IllegalAccessException ;

    public boolean validVoteUserExists ( String user_id , String session_id ) throws IllegalAccessException;

    public boolean existsVotinSession ( String user_id , String session_id );

    public List <SessionVoting> getSessionVotin( String session_id) throws IllegalAccessException;

}
