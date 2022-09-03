package com.nov.springsecuritytestapp.services;


import com.nov.springsecuritytestapp.models.Person;
import com.nov.springsecuritytestapp.repositories.PeopleRepository;
import com.nov.springsecuritytestapp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Это специальный сервис для аутентификации, поэтому он должен реализовывать интерфейс UserDetailsService
@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    // Метод интерфейса, должен получить пользователя по имени
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty()) {
            // Если человек в БД не найден, выбрасываем исключение
            throw new UsernameNotFoundException("User not found!");
        } else {
            // Если найдем, оборачиваем полученный из Optional объект в класс-обертку
            // и возвращаем
            return new PersonDetails(person.get());
        }
    }
}
