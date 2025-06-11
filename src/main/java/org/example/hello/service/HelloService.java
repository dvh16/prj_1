package org.example.hello.service;

import org.example.hello.Hello;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
public Hello HelloService() {
    return new Hello("hi");
}
}
