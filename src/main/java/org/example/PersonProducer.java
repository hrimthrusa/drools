package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class PersonProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendPerson(String name, int age) {
        try {
            Person person = new Person(name, age);
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, person);
            log.info("Sent person: [{}]",person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
