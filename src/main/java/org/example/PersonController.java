package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonProducer personProducer;

    private final PersonService personService;

    private final Random random = new Random();

    @GetMapping("/sendPerson")
    public String sendPerson() {
        personProducer.sendPerson("John Doe", random.nextInt(36));
        return "Person sent!";
    }

    @PostMapping("/validate-person")
    public String validatePerson(@RequestBody Person person) {
        try {
            // Проверка бизнес-правил с помощью Drools
            personService.isAdult(person);
            return "Person is valid!";
        } catch (IllegalArgumentException ex) {
            return "Validation failed: " + ex.getMessage();
        }
    }

}
