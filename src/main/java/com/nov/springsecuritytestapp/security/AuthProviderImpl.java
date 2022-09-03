package com.nov.springsecuritytestapp.security;


import com.nov.springsecuritytestapp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

// Класс будет смотреть в БД, в таблицу person и сравнивать пароль и имя из формы с паролем и именем в таблице
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    //Передаем Authentication с Credentials, получаем Authentication с Principal (если все прошло успешно)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();

        // Вызываем метод специального сервиса, получаем UserDetails
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);

        // getCredentials возвращает пароль, переводим его в строку
        String password = authentication.getCredentials().toString();

        // Сравниваем пароль у юзера из БД и у юзера из формы (из Credentials)
        if (!password.equals(personDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }
        // Возвращаем Authentication (аргументы - Principal, пароль, и список прав)
        // UsernamePasswordAuthenticationToken внутри имплиментит Authentication
        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    //Технический метод, нужен, чтобы дать понять Spring, для какого объекта Authentication он работает
    //Показывает, для каких сценариев какой юзать AuthenticationProvider
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
