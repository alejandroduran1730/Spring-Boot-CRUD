package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {
    @GetMapping({"/saludo/{name}", "/hola/{name}"})
    public String greeting(@PathVariable String name) {//The annotation @PatVariable gets the value name from the URL and transfer it to the String name variable from the method (endpoint).
        return "Hola " + name;
    }
}
