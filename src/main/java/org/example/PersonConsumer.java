package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class PersonConsumer {

   @RabbitListener(queues = "personQueue")
    public void receivePerson(@Payload Person person) {
      log.info("Received: [{}]",person);
    }
}
