package com.abc.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Alhagie Bai Cham
 * @date 2/16/23
 */
public class SecurityConfig {
    private final static String DEPARTMENT_RESOURCE = "department-service/api/departments/**";
    private final static String EMPLOYEE_RESOURCE = "employee-service/api/employees/**";
    private final static String EUREKA_RESOURCE = "http://localhost:8671/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(
                        authorize -> authorize.requestMatchers(DEPARTMENT_RESOURCE, EMPLOYEE_RESOURCE, EUREKA_RESOURCE)
                                .permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
