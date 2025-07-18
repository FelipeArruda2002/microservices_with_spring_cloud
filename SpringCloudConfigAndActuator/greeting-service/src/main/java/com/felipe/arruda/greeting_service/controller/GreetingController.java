package com.felipe.arruda.greeting_service.controller;

import com.felipe.arruda.greeting_service.config.GreetingConfiguration;
import com.felipe.arruda.greeting_service.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration configuration;

    // http://localhost:8080/greeting?name=Felipe
    @GetMapping
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name) {
        if (name.isBlank()) name = configuration.getDefaultValue();

        return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
    }

}
