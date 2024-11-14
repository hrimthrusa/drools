package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonProducer personProducer;

    @Autowired
    private PersonService personService;

    @GetMapping("/sendPerson")
    public String sendPerson() {
        personProducer.sendPerson("John Doe", 30);
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
