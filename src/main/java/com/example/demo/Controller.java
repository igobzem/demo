package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/test")
    public String getOne() {
        return "Hello, World!";
    }

}
