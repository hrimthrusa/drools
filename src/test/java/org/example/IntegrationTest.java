//package org.example;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class IntegrationTest {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testMessageFlow() {
//        Person person = new Person("John Doe", 25);
//        ResponseEntity<String> response = restTemplate.postForEntity("/testapi/send", person, String.class);
//        assertEquals("Message sent to RabbitMQ", response.getBody());
//
//        Person receivedPerson = (Person) rabbitTemplate.receiveAndConvert(RabbitMQConfig.QUEUE_NAME);
//        assertNotNull(receivedPerson);
//        assertEquals("John Doe", receivedPerson.getName());
//    }
//}
