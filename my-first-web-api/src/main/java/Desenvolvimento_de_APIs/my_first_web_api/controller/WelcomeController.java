package Desenvolvimento_de_APIs.my_first_web_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "welcome to my spring boot web API!";
    }
}
