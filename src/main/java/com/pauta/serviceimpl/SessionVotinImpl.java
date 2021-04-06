package com.pauta.serviceimpl;

import java.util.List;

import com.pauta.model.SessionVoting;
import com.pauta.request.SessionVotinRequest;

public interface SessionVotinImpl{

    public SessionVoting save ( SessionVotinRequest sessionVotinRequest ) throws Exception;

    public boolean validateTimeSession ( String session_id ) throws Exception;

    public boolean existsVotinSession ( String user_id , String session_id );

    public List <SessionVoting> getSessionVotin( String session_id) throws IllegalAccessException;

}
