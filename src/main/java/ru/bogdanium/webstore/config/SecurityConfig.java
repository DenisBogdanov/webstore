package ru.bogdanium.webstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Denis, 26.08.2018
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("john")
                .password("querty")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("userId")
                .passwordParameter("password");

        http.formLogin()
                .defaultSuccessUrl("/market/products/add")
                .failureUrl("/login?error");

        http.logout()
                .logoutSuccessUrl("/login?logout");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/**/add").access("hasRole('ADMIN')")
                .antMatchers("/**/market/**").access("hasRole('USER')");

        http.csrf().disable();
    }
}
