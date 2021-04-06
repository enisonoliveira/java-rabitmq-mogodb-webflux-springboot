package com.pauta.controller;

import com.google.gson.Gson;
import com.pauta.jms.producer.Producer;
import com.pauta.model.Pauta;
import com.pauta.model.Session;
import com.pauta.request.PautaRequest;
import com.pauta.request.SessionRequest;
import com.pauta.response.PautaResponse;
import com.pauta.response.SessionResponse;
import com.pauta.service.PautaService;
import com.pauta.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping (path = "/session")
public class PautaController {

    @Autowired
    private PautaService service;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Producer rabbitMQSender;


    @GetMapping (value = "/start/{session_id}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity < Mono <String> > saveSession( @PathVariable ("session_id") String session_id)
            throws Exception {

        Optional < Session > sessionOptional =sessionService.findById (session_id);
        Session session = sessionOptional.get ();

        Pauta pauta = sessionService.startSession ( session );
        {
            Gson gson = new Gson ( );
            //envia o resultado via mensageria
            rabbitMQSender.sendAll ( "Finish job session " );
            rabbitMQSender.sendAll ( gson.toJson ( pauta ) );
        }
        PautaResponse pautaResponse = new PautaResponse ( pauta.getId (),pauta.getName ()
                ,pauta.getTotalVoteFavorable (),pauta.getTotalVoteNotFavorable () );

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Reason", "ok")
                .body(Mono.just( pautaResponse.toPautaJson ( pautaResponse )));
    }

    @PostMapping (value = "/save/{name}")
    @ResponseStatus ( HttpStatus.OK)
    public  ResponseEntity < Mono <String> > savePauta( @PathVariable ("name") String name)
            throws ParseException, IllegalAccessException {

        PautaRequest pautaRequest = new PautaRequest ( null,name,0,0 );
        Pauta pauta =service.save ( pautaRequest );

        SessionRequest sessionRequest = new SessionRequest (null,null, new Date ( ) , pauta ,false ,false);
        Session session=sessionService.save ( sessionRequest );

        rabbitMQSender.sendAll ("pauta cadastrada com suscesso!");

        SessionResponse sessionResponse  = new SessionResponse ( session.getId (),session.getStartSession ()
                ,session.getEndSession (),session.getPauta (),session.isInitSession ()
                ,session.isFinisSession ()  );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Reason", "ok")
                .body(Mono.just( sessionResponse.toSessionJson ( sessionResponse )));

    }
}
