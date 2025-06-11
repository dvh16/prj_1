package org.example.hello.controller;

import org.example.hello.Hello;
import org.example.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {
    @Autowired
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public Hello HelloService() {
        return helloService.HelloService();
    }

int statusCode;
    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        statusCode = HttpStatus.OK.value();
        return ResponseEntity.status(statusCode).body("Status code: " + statusCode);
        // Status: 200
    }

    @GetMapping("/not-found")
    public ResponseEntity<String> notFound() {
        int status = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(statusCode).body("Status code: " + status);
        // Status: 404
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return ResponseEntity.status(statusCode).body("Status code: " + status);
        // Status: 500
    }
}



