package com.sicredi.controller;

import com.google.gson.Gson;
import com.sicredi.jms.producer.Producer;
import com.sicredi.model.Ruling;
import com.sicredi.model.Session;
import com.sicredi.service.RulingService;
import com.sicredi.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping (path = "/session")
public class RulingController {

    @Autowired
    private RulingService service;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Producer rabbitMQSender;

    @GetMapping (value = "/start/{session_id}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity <Object> saveSession( @PathVariable ("session_id") String session_id)
            throws ParseException, IllegalAccessException {

        Optional < Session > sessionOptional =sessionService.findById (session_id);
        Session session = sessionOptional.get ();

        Ruling ruling = sessionService.startSession ( session );
        {
            Gson gson = new Gson ( );
            //envia o resultado via mensageria
            rabbitMQSender.sendAll ( "Finish job session " );
            rabbitMQSender.sendAll ( gson.toJson ( ruling ) );
        }
        return ResponseEntity.ok( ruling );
    }

    @PostMapping (value = "/save/{name}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity <?> savePauta( @PathVariable ("name") String name)
            throws ParseException, IllegalAccessException {

        Ruling ruling = new Ruling ( null,name,0,0 );
        ruling =service.save ( ruling );

        Session session = new Session ( null,null,null, ruling ,false );
        session=sessionService.save ( session );

        rabbitMQSender.sendAll ("pauta cadastrada com suscesso!");

        return ResponseEntity.ok(session);

    }
}
