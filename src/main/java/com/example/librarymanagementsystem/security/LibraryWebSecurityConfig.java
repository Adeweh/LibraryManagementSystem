package com.example.librarymanagementsystem.security;

import com.example.librarymanagementsystem.data.dtos.requests.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@NoArgsConstructor

public class LibraryWebSecurityConfig {
    //    private RegisterRequest request;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/register").csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll() // allow CORS option calls for Swagger UI
                );

        return http.build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/register")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username(request.getEmail())
//                        .password(request.getPassword())
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
