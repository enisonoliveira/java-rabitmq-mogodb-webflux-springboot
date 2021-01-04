package com.sicredi.controller;

import com.google.gson.Gson;
import com.sicredi.jms.producer.Producer;
import com.sicredi.model.Session;
import com.sicredi.model.SessionVoting;
import com.sicredi.model.User;
import com.sicredi.service.SessionService;
import com.sicredi.service.SessionVotingService;
import com.sicredi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
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
    public ResponseEntity <?> saveSession(  @RequestParam  String CPF ,@RequestParam String session_id
             ,@RequestParam String voting) throws ParseException, IllegalAccessException {

        Optional < Session > sessionOptional = sessionService.findById ( session_id );
        Session session = sessionOptional.get ();
        User user=null;
        SessionVoting sessionVoting =null;
        {
            boolean votingUser= voting.equals ( "sim" )? true:false;
            if ( ! userService.noExistsCPF ( CPF ) ) {
                List < User > users = userService.findByCPF ( CPF );
                user = users.get ( 0 );
            } else {
                //se não existe cadastra um novo usuário com cpf informado
                user = new User ( null , CPF , true );
                user = userService.save ( user );
            }
             sessionVoting =new SessionVoting ( null , user , session , votingUser );
            if( sessionVotingService.validVoteUserExists( user.get_id (), session_id )){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já votou nessa sessão!");
            }
            if( ! sessionService.compareIntervalDate(session_id )){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sessão encerrada!");
            }
            registerVotin ( sessionVoting );
        }
        return ResponseEntity.ok("OK!");
    }

    private void registerVotin  ( SessionVoting sessionVoting ) throws IllegalAccessException {

        //opicional para concorrencia de votação com mensageria ou direto no banco
        Gson gson = new Gson ();
        if( messages.equals ( "disable" )) {
            sessionVotingService.save ( sessionVoting );
        }else{
            rabbitMQSender.sendVotin ( gson.toJson ( sessionVoting ) );
        }
    }

}
