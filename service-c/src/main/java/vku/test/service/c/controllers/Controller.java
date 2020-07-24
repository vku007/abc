package vku.test.service.c.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/tst")
    public String getTestMessage() {
        return "Hi there!" + System.currentTimeMillis();
    }
}
