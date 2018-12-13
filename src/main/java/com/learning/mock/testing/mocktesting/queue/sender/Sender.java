package com.learning.mock.testing.mocktesting.queue.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.mock.testing.mocktesting.entity.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {

    @Autowired
    Queue queue;

    private AmqpTemplate amqpTemplate;

    @Autowired
    public Sender(AmqpTemplate amqpTemplate)
    {
        this.amqpTemplate = amqpTemplate;
    }

    public void produce (Employee employee)
    {
        try {
            amqpTemplate.convertAndSend(queue.getName(), new ObjectMapper().writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

