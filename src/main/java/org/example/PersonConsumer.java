package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RestConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonConsumer {

    private final PersonService personService;

    private final RestTemplate restTemplate;

    @RabbitListener(queues = "personQueue")
    public void receivePerson(@Payload Person person) {
        log.info("Received: [{}]", person);
        personService.isAdult(person);
        var resp = restTemplate.postForEntity(RestConfig.URL_VALIDATE_PERSON, person, String.class);
        log.info("Sent to server: [{}] got response: [{}]", person,resp);
    }
}
