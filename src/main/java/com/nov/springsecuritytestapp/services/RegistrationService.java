package com.nov.springsecuritytestapp.services;

import com.nov.springsecuritytestapp.models.Person;
import com.nov.springsecuritytestapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional// Здесь происходят изменения в БД
    public void register(Person person) {
        // Шифруем пароль
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);

        // Назначаем роль каждому новому юзеру
        person.setRole("ROLE_USER");

        peopleRepository.save(person);
    }
}
