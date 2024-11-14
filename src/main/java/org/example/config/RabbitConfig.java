package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE_NAME = "personQueue";
    public static final String EXCHANGE_NAME = "personExchange";
    public static final String ROUTING_KEY = "person.key";

    @Bean
    public Queue personQueue() {
        return new Queue(QUEUE_NAME, true); // true - очередь персистентная
    }

    @Bean
    public TopicExchange personExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding personQueueBinding() {
        return BindingBuilder.bind(personQueue())
                .to(personExchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(message -> {
            // Логика обработки полученного сообщения
            System.out.println("Received: " + new String(message.getBody()));
        });
        container.setQueues(personQueue());
        return container;
    }

}
