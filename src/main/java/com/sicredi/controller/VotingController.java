package com.sicredi.controller;

import com.google.gson.Gson;
import com.sicredi.jms.producer.Producer;
import com.sicredi.model.Session;
import com.sicredi.model.User;
import com.sicredi.request.SessionVotinRequest;
import com.sicredi.service.SessionService;
import com.sicredi.service.SessionVotingService;
import com.sicredi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping (path = "/voting")
public class VotingController {

    @Autowired
    private SessionVotingService sessionVotingService;

    @Value ( "${messages.status}" )
    protected String messages;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Producer rabbitMQSender;

    @PostMapping (value = "/save")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity < Mono <String> > saveSession( @RequestParam  String CPF , @RequestParam String session_id
             , @RequestParam String voting) throws Exception {

        Optional < Session > sessionOptional = sessionService.findById ( session_id );
        Session session = sessionOptional.get ();
        {
            boolean votingUser= voting.equals ( "sim" )? true:false;
            User user = userService.saveSearchUser(CPF);
            SessionVotinRequest sessionVoting =new SessionVotinRequest ( null, user , session , votingUser );
            registerVotin ( sessionVoting );
        }

        return ResponseEntity
                .status( HttpStatus.CREATED)
                .header("X-Reason", "ok")
                .body(Mono.just("ok"));
    }

    private void registerVotin  ( SessionVotinRequest sessionVoting ) throws Exception {

        //opicional para concorrencia de votação com mensageria ou direto no banco
        Gson gson = new Gson ();
         sessionVotingService.save ( sessionVoting );
    }

}
