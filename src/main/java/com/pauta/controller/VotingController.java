package com.pauta.controller;

import com.pauta.model.Session;
import com.pauta.model.User;
import com.pauta.request.SessionVotinRequest;
import com.pauta.service.SessionService;
import com.pauta.service.SessionVotingService;
import com.pauta.service.UserService;

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

        sessionVotingService.save ( sessionVoting );
    }

}
