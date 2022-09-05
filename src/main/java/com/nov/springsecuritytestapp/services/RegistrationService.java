package com.nov.springsecuritytestapp.services;

import com.nov.springsecuritytestapp.models.Person;
import com.nov.springsecuritytestapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional// Здесь происходят изменения в БД
    public void register(Person person) {
        peopleRepository.save(person);
    }
}
