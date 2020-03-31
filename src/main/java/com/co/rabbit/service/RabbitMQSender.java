package com.co.rabbit.service;

import com.co.rabbit.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private final RabbitTemplate amqpTemplate;

    @Value(value="${jms.rabbitmq.exchange}")
    public String exchange;
    @Value(value="${jms.rabbitmq.routingkey}")
    public String routingKey;

    public RabbitMQSender(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public boolean send(Employee company){
        try{
            amqpTemplate.convertAndSend(exchange,routingKey,company);
        //System.out.println("Send msg = " + company);
        return true;}catch(Exception exc){
            return false;
        }
    }
}
