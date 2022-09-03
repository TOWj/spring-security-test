package com.nov.springsecuritytestapp.security;

import com.nov.springsecuritytestapp.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //нужен для авторизации, здесь мы получаем роли
        return null;
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        //показывает, жив/активен ли аккаунт
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //показывает, не заблокирован ли аккаунт
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //пароль не просрочен
        return true;
    }

    @Override
    public boolean isEnabled() {
        //акк включен/работает
        return true;
    }

    // Посредством этого геттера мы получаем данные аутентифицированного пользователя
    public Person getPerson() {
        return this.person;
    }
}
