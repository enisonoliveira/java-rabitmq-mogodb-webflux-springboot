package com.sicredi.controller;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;
import java.util.Date;
import java.util.DuplicateFormatFlagsException;

@ControllerAdvice
@RestController
public class ErrorContoller  {


    @ExceptionHandler ( value = {DuplicateFormatFlagsException.class} )
    protected ResponseEntity < Response > duplicateFormatFlagsException ( DuplicateFormatFlagsException exception ,
                                                                    WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.CONFLICT ).body ( response );
    }


    @ExceptionHandler ( value = {ParseException.class} )
    protected ResponseEntity < Response > parseException ( ParseException exception ,
                                                                          WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.CONFLICT ).body ( response );
    }



    @ExceptionHandler ( value = {Exception.class} )
    protected ResponseEntity < Response > exception ( Exception exception ,
                                                           WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.NOT_FOUND ).body ( response );
    }



    @ExceptionHandler ( value = {IllegalAccessException.class} )
    protected ResponseEntity < Response > illegalAccessException ( IllegalAccessException exception ,
                                                                   WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.CONFLICT ).body ( response );
    }

    @ExceptionHandler ( value = {HttpClientErrorException.Conflict.class} )
    protected ResponseEntity < Response > handleConflictException ( HttpClientErrorException exception ,
                                                                    WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.CONFLICT ).body ( response );
    }

    @ExceptionHandler ( value = {HttpMessageNotReadableException.class , JsonParseException.class} )
    protected ResponseEntity < Response > handleMessageNotReadableException ( HttpMessageNotReadableException
                                                                                      exception , WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.UNPROCESSABLE_ENTITY ).body ( response );
    }

    @ExceptionHandler ( value = {HttpClientErrorException.TooManyRequests.class} )
    protected ResponseEntity < Response > handleTooManyRequestException ( HttpClientErrorException exception ,
                                                                          WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.TOO_MANY_REQUESTS ).body ( response );
    }

    @ExceptionHandler ( value = {ServerErrorException.class} )
    protected ResponseEntity < Response > handleAPIException ( ServerErrorException exception ,
                                                               WebRequest request ) {

        Response response = new Response ( );
        response.addErrorMsgToResponse ( exception.getLocalizedMessage ( ) );

        return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( response );
    }

    protected static class Response {
        private String message;
        private String description;
        private Date date;

        public Response ( Date date , String message , String description ) {
            this.date = date;
            this.description = description;
            this.message = message;
        }

        public Response ( ) {
        }

        public String getMessage ( ) {
            return message;
        }

        public void addErrorMsgToResponse ( String message ) {
            this.message = message;
        }
    }
}
