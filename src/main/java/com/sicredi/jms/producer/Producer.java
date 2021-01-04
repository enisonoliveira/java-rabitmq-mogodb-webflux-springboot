package com.sicredi.jms.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendAll(String send ) {
        rabbitTemplate.convertAndSend("all","all", send);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendVotin( String voting ) {
        rabbitTemplate.convertAndSend("voting","voting", voting);
    }
}
