package com.sicredi.controller;

import com.google.gson.Gson;
import com.sicredi.model.User;
import com.sicredi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping (path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping (value = "/save/{CPF}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity < Mono <String> > save( @PathVariable ("CPF") String CPF)
            throws IllegalAccessException {

        User user = new User ( null,CPF,true );
        user=userService.save ( user );

        return   ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Reason", "ok")
                .body(Mono.just("ok"));
    }

    @GetMapping (value = "/cpf/status/{CPF}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity < Mono <String>> statusCPF( @PathVariable ("CPF") String CPF)
            throws IllegalAccessException {

        List<User> user = userService.findByCPF ( CPF );
        Gson gson = new Gson ();
        String userJson=gson.toJson ( user.get ( 0 ));
        if( 0 == user.size ( ) ) {
            return ResponseEntity
                    .status ( HttpStatus.UNAUTHORIZED )
                    .header ( "X-Reason" , "user-invalid" )
                    .body ( Mono.just ( "Usuráio com CPF nao cadastrado em nossa base!" ) );
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-Reason", "ok")
                .body(Mono.just(userJson));
    }
}
