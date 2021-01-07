package com.sicredi.response;

import com.sicredi.data.SessionVotingData;
import com.sicredi.model.Session;
import com.sicredi.model.User;

public class SessionVotinResponse extends SessionVotingData {
    public SessionVotinResponse ( String id , User userData , Session session , boolean vote ) {
        super ( id , userData , session , vote );
    }
}
