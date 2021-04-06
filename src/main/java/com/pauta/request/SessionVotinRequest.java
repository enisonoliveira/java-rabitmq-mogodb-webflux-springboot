package com.pauta.request;

import com.pauta.data.SessionVotingData;
import com.pauta.model.Session;
import com.pauta.model.SessionVoting;
import com.pauta.model.User;

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
