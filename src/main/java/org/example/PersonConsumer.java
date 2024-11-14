package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PersonConsumer {

    private final ObjectMapper objectMapper;

    public PersonConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "personQueue")
    public void receivePerson(String personJson) {
        try {
            Person person = objectMapper.readValue(personJson, Person.class);
            System.out.println("Received person: " + person.getName() + ", Age: " + person.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
