package com.sicredi.data;

import com.sicredi.model.Pauta;

import java.util.Date;

public class SessionData {

    private String id;

    private Date startSession;

    private Date endSession;

    private Pauta pauta;

    private boolean init_session;

    private boolean finish_session;

    public SessionData(){}

    public SessionData ( String id , Date startSession , Date endSession , Pauta pauta , boolean init_session , boolean finish_session ) {
        this.id = id;
        this.startSession = startSession;
        this.endSession = endSession;
        this.pauta = pauta;
        this.init_session = init_session;
        this.finish_session = finish_session;
    }

    public String getId ( ) {
        return id;
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

    public boolean isInit_session ( ) {
        return init_session;
    }

    public void setInit_session ( boolean init_session ) {
        this.init_session = init_session;
    }

    public boolean isFinish_session ( ) {
        return finish_session;
    }

    public void setFinish_session ( boolean finish_session ) {
        this.finish_session = finish_session;
    }

}
