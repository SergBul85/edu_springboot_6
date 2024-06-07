package com.hstn.crud_empl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MySecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1Ivan = User.builder()
                .username("ivan")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails user2Oleg = User.builder()
                .username("oleg")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails user3Inna = User.builder()
                .username("inna")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1Ivan, user2Oleg, user3Inna);
    }

}
