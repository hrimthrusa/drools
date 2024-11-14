package org.example;

import org.example.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendPerson(String name, int age) {
        try {
            Person person = new Person(name, age);
            String jsonPerson = objectMapper.writeValueAsString(person);
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, jsonPerson);
            System.out.println("Sent: " + jsonPerson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
