package com.sicredi.jms.consumer;

import com.google.gson.Gson;
import com.sicredi.model.SessionVoting;
import com.sicredi.request.SessionVotinRequest;
import com.sicredi.service.SessionVotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    SessionVotingService service;
    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( Consumer.class );
    }


    @RabbitListener (queues = "voting")
    public void listen(String message) throws Exception {
        logger.info ( "=====Voting computed=====" );
        Gson gson = new Gson ();

        SessionVotinRequest votin=gson.fromJson ( message, SessionVotinRequest.class );
        service.save ( votin );
    }

    @RabbitListener (queues = "all")
    public void listenAll(String message) {
        logger.info ( message );
    }

}
