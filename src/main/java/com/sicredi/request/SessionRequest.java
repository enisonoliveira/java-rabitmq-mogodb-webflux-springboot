package com.sicredi.request;

import com.sicredi.data.PautaData;
import com.sicredi.data.SessionData;
import com.sicredi.model.Pauta;
import com.sicredi.model.Session;

import java.util.Date;

public class SessionRequest extends SessionData {

    public SessionRequest ( String id , Date startSession , Date endSession , Pauta pauta , boolean init_session , boolean finish_session ) {
        super ( id , startSession , endSession , pauta , init_session , finish_session );
    }

    public Session toSession(SessionRequest request){
        Session session = new Session (request.getId (),request.getStartSession (),request.getEndSession (),request.getPauta (),request.isInitSession (),request.isFinishSession ());
        return session;
    }


}
