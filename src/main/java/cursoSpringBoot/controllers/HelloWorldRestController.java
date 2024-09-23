package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping({"/hello", "/hw", "/hola"})//The URL should makea reference to what the method (endpoint) will return
    public String helloWorld() {//This method is an endpoint
        System.out.println("Solicitud ejecutada");
        return "Hello world!";
    }
}
