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
    private boolean init_session;

    @Field("finish_session")
    private boolean finish_session;

    public Session ( String _id , Date startSession , Date endSession , Pauta pauta , boolean finish_session ) {
        this._id = _id;
        this.startSession = null;
        this.endSession = null;
        this.pauta = pauta;
        this.finish_session = false;
        this.init_session = false;
    }

    public String get_id () {
        return _id;
    }

    public void set_id (String _id) {
        this._id = _id;
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

    public Pauta getPauta () {
        return pauta;
    }

    public void setPauta (Pauta pauta) {
        this.pauta = pauta;
    }

    public boolean getFinish_session ( ) {
        return finish_session;
    }

    public void setFinish_session ( boolean finish_session ) {
        this.finish_session = finish_session;
    }

    public boolean isInit_session ( ) {
        return init_session;
    }

    public void setInit_session ( boolean init_session ) {
        this.init_session = init_session;
    }
}
