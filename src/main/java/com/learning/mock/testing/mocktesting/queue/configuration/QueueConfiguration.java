package com.learning.mock.testing.mocktesting.queue.configuration;


import com.learning.mock.testing.mocktesting.queue.receiver.Receiver;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class QueueConfiguration {

    private static final String LISTENER_METHOD = "processMessage";

    @Value("${direct.exchange}")
    private String directExchange;

    @Value("${queue.name}")
    private String queueName;

    @Value("${routing.key}")
    private String routingKey;

    @Value( "${cloud.rabbit.url}" )
    private String rabbitUrl;

    @Bean
    public Queue queue()
    {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange exchange()
    {
        return new DirectExchange(directExchange);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }

    @Bean
    public Channel binding(Queue queue, DirectExchange exchange)
    {
        CachingConnectionFactory factory = connectionFactory();
        Channel channel = null;
      Connection connection = factory.createConnection();
      channel = connection.createChannel(true);
      try
      {
       channel.exchangeDeclare(directExchange,"direct",true);
       channel.queueBind(queueName,directExchange,"mocking_testing");
      }
      catch(Exception e)
      {
        System.out.println("Exception in Queue Binding");
      }
      return channel;
    }

    @Bean
    public CachingConnectionFactory connectionFactory()
    {
        URI rabbitMQURI = null;
        try {
            rabbitMQURI = new URI(rabbitUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(rabbitMQURI);
       /* factory.setUsername(rabbitMqUri.getUserInfo().split(":")[0]);
        factory.setPassword(rabbitMqUri.getUserInfo().split(":")[1]);
        factory.setHost(rabbitMqUri.getHost());
        factory.setVirtualHost(rabbitMqUri.getHost());
        factory.setPort(rabbitMqUri.getPort());
        factory.setVirtualHost(rabbitMqUri.getPath().substring(1));*/
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer (CachingConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue().getName());
        container.setMessageListener(messageListenerAdapter);
        return container;

    }
}
