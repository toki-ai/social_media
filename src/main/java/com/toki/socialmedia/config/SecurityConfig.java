package com.toki.socialmedia.config;

import com.toki.socialmedia.security.JwtValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private Environment env;

    @Autowired
    private JwtValidator jwtValidator;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Cho phép truy cập đến các tài nguyên Swagger
                        .requestMatchers(
                                "/v3/api-docs/**",  // Cấu hình cho OpenAPI
                                "/swagger-ui/**",   // Cấu hình cho Swagger UI
                                "/swagger-resources/**", // Cấu hình cho tài nguyên Swagger
                                "/webjars/**" // Cấu hình cho các tệp webjars
                        ).permitAll() // Cho phép tất cả truy cập đến các tài nguyên này
                        .requestMatchers("/post/**").authenticated()
                        .requestMatchers("/users/**", "/users").authenticated()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtValidator, BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // Địa chỉ frontend
        corsConfiguration.setAllowedMethods(Collections.singletonList("*")); // Cho phép tất cả các phương thức
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*")); // Cho phép tất cả các header
        corsConfiguration.setAllowCredentials(true); // Cho phép cookie
        corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization")); // Expose header nếu cần
        corsConfiguration.setMaxAge(3600L); // Thời gian cache cho CORS

        return request -> corsConfiguration; // Trả về cấu hình CORS cho tất cả các request
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
