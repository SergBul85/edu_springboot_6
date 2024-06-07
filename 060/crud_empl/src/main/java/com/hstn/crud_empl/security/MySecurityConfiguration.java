package com.hstn.crud_empl.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configer -> configer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csfr -> csfr.disable());

        return http.build();
    }

}
