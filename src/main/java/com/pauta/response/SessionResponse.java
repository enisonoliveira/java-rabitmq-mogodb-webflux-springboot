package com.pauta.response;

import com.google.gson.Gson;
import com.pauta.data.SessionData;
import com.pauta.model.Pauta;

import java.util.Date;

public class SessionResponse extends SessionData {

    public SessionResponse ( String id , Date startSession , Date endSession , Pauta pauta , boolean init_session , boolean finish_session ) {
        super ( id , startSession , endSession , pauta , init_session , finish_session );
    }

    public String toSessionJson( SessionResponse response){

              Gson gson = new Gson ();
        return gson.toJson ( response );
    }
}
