package com.sicredi.data;

import com.sicredi.model.Pauta;

import java.util.Date;

public class SessionData {

    private String id;

    private Date startSession;

    private Date endSession;

    private Pauta pauta;

    private boolean initSession;

    private boolean finishSession;

    public SessionData(){}

    public SessionData ( String id , Date startSession , Date endSession , Pauta pauta , boolean init_session , boolean finish_session ) {
        this.id = id;
        this.startSession = startSession;
        this.endSession = endSession;
        this.pauta = pauta;
        this.initSession = init_session;
        this.finishSession = finish_session;
    }

    public String getId ( ) {
        return id;
    }

    public boolean isInitSession ( ) {
        return initSession;
    }

    public void setInitSession ( boolean initSession ) {
        this.initSession = initSession;
    }

    public boolean isFinishSession ( ) {
        return finishSession;
    }

    public void setFinishSession ( boolean finishSession ) {
        this.finishSession = finishSession;
    }
    public void setId ( String id ) {
        this.id = id;
    }

    public Date getStartSession ( ) {
        return startSession;
    }

    public void setStartSession ( Date startSession ) {
        this.startSession = startSession;
    }

    public Date getEndSession ( ) {
        return endSession;
    }

    public void setEndSession ( Date endSession ) {
        this.endSession = endSession;
    }

    public Pauta getPauta ( ) {
        return pauta;
    }

    public void setPauta ( Pauta pauta ) {
        this.pauta = pauta;
    }

    public void setFinish_session ( boolean finish_session ) {
        this.finishSession = finish_session;
    }

}
