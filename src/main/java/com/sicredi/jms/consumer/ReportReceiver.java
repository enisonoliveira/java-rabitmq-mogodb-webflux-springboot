package com.sicredi.jms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class ReportReceiver implements MessageListener {

    private Logger logger;

    {
        logger = LoggerFactory.getLogger ( Consumer.class );
    }

    public void onMessage(Message message) {
        logger.info("Consuming all mensagem- " + new String(message.getBody()));
    }

}
