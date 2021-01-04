package com.sicredi.config;

import com.sicredi.jms.consumer.ReportReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JmsConfig  {


    @Value ("${voting.rabbitmq.queue}")
    String queueName;

    @Value("${voting.rabbitmq.exchange}")
    String exchange;

    @Value("${voting.rabbitmq.routingkey}")
    private String routingkey;


    @Value ("${all.rabbitmq.queue}")
    String queueNameAll;

    @Value("${all.rabbitmq.exchange}")
    String exchangeAll;

    @Value("${all.rabbitmq.routingkey}")
    private String routingkeyAll;



    @Bean
    Queue queueVotin() {
        return new Queue(queueName, true);
    }

    @Bean
    Queue queueAll() {
        return new Queue(queueNameAll, true);
    }

    //create MessageListenerContainer using default connection factory
    @Bean
    MessageListenerContainer messageListenerContainerVotin( ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queueVotin ());
        simpleMessageListenerContainer.setMessageListener(new ReportReceiver ());
        return simpleMessageListenerContainer;

    }

    @Bean
    MessageListenerContainer messageListenerContainerAll( ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queueAll ());
        simpleMessageListenerContainer.setMessageListener(new ReportReceiver ());
        return simpleMessageListenerContainer;

    }

    @Bean
    @Qualifier("exchangeVotin")
    DirectExchange exchangeVotin() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding bindingVotin( @Qualifier("queueVotin") Queue queue, @Qualifier("exchangeVotin")  DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

    @Bean
    DirectExchange  exchangeAll() {
        return new DirectExchange( exchangeAll);
    }

    @Bean
    Binding bindingAll( @Qualifier("queueAll") Queue queue, @Qualifier("exchangeAll")  DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkeyAll);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}