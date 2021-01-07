package com.sicredi.request;

import com.sicredi.data.SessionData;
import com.sicredi.data.SessionVotingData;
import com.sicredi.model.User;
import com.sicredi.model.Session;
import com.sicredi.model.SessionVoting;
import com.sicredi.model.User;

public class SessionVotinRequest extends SessionVotingData {
    public SessionVotinRequest ( String id , User user , Session session , boolean vote ) {
        super ( id , user , session , vote );
    }

    public SessionVoting  toSession(SessionVotinRequest request){
        SessionVoting session = new SessionVoting (  request.getUser (),request.getSession ()
                , request.isVote ());
        return session;
    }
}
