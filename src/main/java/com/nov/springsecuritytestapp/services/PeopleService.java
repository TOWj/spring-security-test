package com.nov.springsecuritytestapp.services;


import com.nov.springsecuritytestapp.models.Person;
import com.nov.springsecuritytestapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public Optional<Person> loadUserByUsername(String username){
        Optional<Person> person = peopleRepository.findByUsername(username);

        return person;
    }
}
