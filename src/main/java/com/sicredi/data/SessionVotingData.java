package com.sicredi.data;

import com.sicredi.model.Session;
import com.sicredi.model.User;

public class SessionVotingData {

    private String id;

    private User userData;

    private Session sessionData;

    private boolean vote;

    public SessionVotingData ( String id , User user , Session session , boolean vote ) {
        this.id = id;
        this.userData = user;
        this.sessionData = session;
        this.vote = vote;
    }

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public User getUser ( ) {
        return userData;
    }

    public void setUser ( User userData ) {
        this.userData = userData;
    }

    public Session getSession ( ) {
        return sessionData;
    }

    public void setSession ( Session session ) {
        this.sessionData = session;
    }

    public boolean isVote ( ) {
        return vote;
    }

    public void setVote ( boolean vote ) {
        this.vote = vote;
    }
}
