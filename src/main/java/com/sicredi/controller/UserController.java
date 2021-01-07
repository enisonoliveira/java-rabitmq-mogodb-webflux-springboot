package com.sicredi.controller;

import com.google.gson.Gson;
import com.sicredi.model.User;
import com.sicredi.request.UserRequest;
import com.sicredi.response.UserResponse;
import com.sicredi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping (path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping (value = "/save/{CPF}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity < Mono <String> > save( @PathVariable ("CPF") String CPF)
            throws Exception {

        UserRequest userRequest = new UserRequest ( null,CPF,true );
        userService.save ( userRequest );

        return   ResponseEntity
                .status( HttpStatus.CREATED)
                .header("X-Reason", "ok")
                .body(Mono.just("ok"));
    }

    @GetMapping (value = "/cpf/status/{CPF}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity < Mono <String>> statusCPF( @PathVariable ("CPF") String CPF)
            throws Exception {

        Optional <User> user = userService.findByCPF ( CPF );
        UserResponse response = new UserResponse (  user.get ().getId (), user.get ().getCPF (), user.get ().isAbleToVote ());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Reason", "ok")
                .body(Mono.just(response.userToJson ( response )));
    }
}
