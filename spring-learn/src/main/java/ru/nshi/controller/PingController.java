package ru.nshi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.model.Ping;

@RestController
@RequestMapping(PingController.MAPPING)
public class PingController {
    public static final String MAPPING = "/ping";

    @GetMapping
    public Ping getPing() {
        return new Ping();
    }
}

