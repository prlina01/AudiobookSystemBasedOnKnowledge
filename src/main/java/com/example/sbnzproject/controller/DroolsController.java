package com.example.sbnzproject.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drools")
public class DroolsController {

    @Autowired
    private KieContainer kieContainer;

    @PostMapping("/process")
    public String processNumbers(@RequestBody List<Integer> numbers) {
        KieSession kieSession = kieContainer.newKieSession();
        for (Integer number : numbers) {
            kieSession.insert(number);
        }
        int fired = kieSession.fireAllRules();
        kieSession.dispose();
        return "Number of rules fired: " + fired;
    }
}