package org.example;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public PersonService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    private final KieContainer kieContainer;


    public boolean isAdult(Person person) {
        KieSession kieSession = kieContainer.newKieSession();
        try {
            System.out.println("Person age before rule: " + person.getAge());
            kieSession.insert(person);
            int rulesFired = kieSession.fireAllRules();
            System.out.println("Rules fired: " + rulesFired);
            System.out.println("Person adult status after rules: " + person.isAdult());
            return person.isAdult(); // Предполагается, что метод isAdult() меняется правилом
        } finally {
            kieSession.dispose(); // Освобождаем ресурсы KieSession
        }
    }
}
