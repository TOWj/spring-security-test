package com.nov.springsecuritytestapp.config;


import com.nov.springsecuritytestapp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Класс устарел, в дальнейшем заменить
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Этот метод настраивает аутентификацию, надо юзать свою логику authenticationProvider
        auth.userDetailsService(personDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // Не шифрованный пароль - deprecated
        return NoOpPasswordEncoder.getInstance();
    }
}
