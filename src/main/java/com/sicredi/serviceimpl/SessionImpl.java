package com.sicredi.serviceimpl;

import com.sicredi.model.Pauta;
import com.sicredi.model.Session;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public interface SessionImpl {

    public Pauta startSession( Session session) throws IllegalAccessException, ParseException;

    public Pauta pauta( Session session ) throws IllegalAccessException;

    public Session save ( Session session ) throws ParseException, IllegalAccessException;

    public Session initSession ( Session session ) throws ParseException, IllegalAccessException;

    public Session finishSession ( Session session ) throws ParseException, IllegalAccessException;

    public Date getDateStartSession ( ) throws ParseException, IllegalAccessException;

    public Date getDateNow ( );

    public Date getDateEndSession ( Date startSession ) throws ParseException, IllegalAccessException;

    public boolean sessionHasInitialized(String sesssion_id);

    public Optional < Session > findById ( String _id );
}
