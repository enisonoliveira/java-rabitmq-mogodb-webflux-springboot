package com.sicredi.controller;

import com.sicredi.model.User;
import com.sicredi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping (value = "/save/{CPF}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity <?> saveSession( @PathVariable ("CPF") String CPF)
            throws IllegalAccessException {

        User user = new User ( null,CPF,true );
        user=userService.save ( user );

        return ResponseEntity.ok(user);
    }

    @GetMapping (value = "/cpf/status/{CPF}")
    @ResponseStatus ( HttpStatus.OK)
    public ResponseEntity <?> statusCPF( @PathVariable ("CPF") String CPF)
            throws IllegalAccessException {

        List<User> user = userService.findByCPF ( CPF );

        return ResponseEntity.ok(user);
    }
}
