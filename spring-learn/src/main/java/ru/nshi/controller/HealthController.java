package ru.nshi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.model.Health;

@RestController
@RequestMapping(HealthController.MAPPING)
public class HealthController {
    public static final String MAPPING = "/ping";

    @GetMapping
    public Health getPing() {
        return new Health();
    }
}
