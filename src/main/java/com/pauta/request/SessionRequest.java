package com.pauta.request;

import java.util.Date;

import com.pauta.data.SessionData;
import com.pauta.model.Pauta;
import com.pauta.model.Session;

public class SessionRequest extends SessionData {

    public SessionRequest ( String id , Date startSession , Date endSession , Pauta pauta , boolean init_session , boolean finish_session ) {
        super ( id , startSession , endSession , pauta , init_session , finish_session );
    }

    public Session toSession(SessionRequest request){
        Session session = new Session (request.getId (),request.getStartSession (),request.getEndSession (),request.getPauta (),request.isInitSession (),request.isFinishSession ());
        return session;
    }


}
