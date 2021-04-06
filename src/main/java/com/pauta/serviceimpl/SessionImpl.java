package com.pauta.serviceimpl;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import com.pauta.model.Pauta;
import com.pauta.model.Session;
import com.pauta.request.SessionRequest;

public interface SessionImpl {

    public Pauta startSession( Session session) throws IllegalAccessException, ParseException;

    public Pauta pauta( Session session ) throws IllegalAccessException;

    public Session save ( SessionRequest sessionRequest ) throws ParseException, IllegalAccessException;

    public Session initSession ( Session session ) throws ParseException, IllegalAccessException;

    public Session finishSession ( Session session ) throws ParseException, IllegalAccessException;

    public Date getDateStartSession ( ) throws ParseException, IllegalAccessException;

    public Date getDateNow ( );

    public Date getDateEndSession ( Date startSession ) throws ParseException, IllegalAccessException;

    public boolean sessionHasInitialized(String sesssion_id) throws Exception;

    public Optional < Session > findById ( String _id ) throws Exception;

    Optional< Session> findPautaId ( String id );

}
