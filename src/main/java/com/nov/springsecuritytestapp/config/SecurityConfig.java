package com.nov.springsecuritytestapp.config;


import com.nov.springsecuritytestapp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Здесь конфигурируем Spring Security и авторизацию

        // Указываем Спрингу, что хотим юзать свою форму аутентификации через Правила
        // Сначала идут наиболее специфиные Правила, после более общие
        httpSecurity.csrf().disable()
                .authorizeRequests()// Сначала конфигурируем авторизацию, теперь все запросы сначала пройдут через авторизацию
                .antMatchers("/auth/login", "/error")// Через эти матчеры мы можем смотреть, какой запрос пришел.
                // Если запрос пришел по указанным страницам, мы должны его пускать
                .permitAll()// Эдакое подтверждение того, что всех юзеров по указанным адресам мы пускаем
                .anyRequest()// Все любые другие запросы, кроме матчеров
                .authenticated()// Юзер должен быть аутентифицирован
                .and()// Переходим к настройке страницы логина
                .formLogin().loginPage("/auth/login")// Указываем, по какому пути будет находиться наша форма
                .loginProcessingUrl("/process_login")// Указываем, куда были отправлены данные с формы (смотри html формы)
                .defaultSuccessUrl("/hello", true)// Что будет происходить при успешной аутентификации
                .failureUrl("/auth/login?error");// Если аутентификация была провалена,
                                                                    // идем на ту же страницу, но с параметром error
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Этот метод настраивает аутентификацию, сам Спринг все проверяет
        auth.userDetailsService(personDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // Не шифрованный пароль - deprecated
        return NoOpPasswordEncoder.getInstance();
    }
}
