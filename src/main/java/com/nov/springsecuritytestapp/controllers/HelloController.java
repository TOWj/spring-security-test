package com.nov.springsecuritytestapp.controllers;

import com.nov.springsecuritytestapp.models.Person;
import com.nov.springsecuritytestapp.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Principal легко сдаункастить до нужного объекта
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        // Теперь можно на PersonDetails вызвать метод getPerson(), чтобы получить объект пользователя
        Person person = personDetails.getPerson();

        System.out.println(person);

        return "hello";
    }

}
