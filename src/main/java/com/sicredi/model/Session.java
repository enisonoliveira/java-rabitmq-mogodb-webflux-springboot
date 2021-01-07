package com.sicredi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "sessions")
public class Session {

    @Id
    private String _id;

    @Field("date_start_session")
    private Date startSession;

    @Field("date_end_session")
    private Date endSession;

    @DBRef
    private Pauta pauta;

    @Field("init_session")
    private boolean initSession;

    @Field("finish_session")
    private boolean finisSession;

    public boolean isInitSession ( ) {
        return initSession;
    }

    public void setInitSession ( boolean initSession ) {
        this.initSession = initSession;
    }

    public boolean isFinisSession ( ) {
        return finisSession;
    }

    public void setFinisSession ( boolean finisSession ) {
        this.finisSession = finisSession;
    }

    public Session ( String _id,
                     Date startSession , Date endSession , Pauta pauta
            , boolean initSession, boolean finisSession ) {
        this.startSession = null;
        this.endSession = null;
        this.pauta = pauta;
        this.finisSession = false;
        this.initSession = false;
        this._id=_id;
    }

    public Date getStartSession () {
        return startSession;
    }

    public void setStartSession (Date startSession) {
        this.startSession = startSession;
    }

    public Date getEndSession () {
        return endSession;
    }

    public void setEndSession (Date endSession) {
        this.endSession = endSession;
    }

    public  Pauta getPauta () {
        return pauta;
    }

    public void setPauta ( Pauta pauta ) {
        this.pauta = pauta;
    }

    public String getId ( ) {
        return _id;
    }

    public void setId ( String id ) {
        this._id = id;
    }
}
