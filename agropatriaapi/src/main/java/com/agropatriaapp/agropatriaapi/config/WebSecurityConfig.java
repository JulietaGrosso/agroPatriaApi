package com.agropatriaapp.agropatriaapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.agropatriaapp.agropatriaapi.utils.AuthTokenFilter;

@Configuration
public class WebSecurityConfig {
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Updated configuration for Spring Security 6.x
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.addAllowedOrigin("http://localhost:3000");
                    config.addAllowedOrigin("http://localhost:5173");
                    config.addAllowedOrigin("https://julietagrosso.github.io");
                    config.addAllowedOrigin("https://agropatria.com");
                    config.addAllowedOrigin("https://www.agropatria.com");
                    config.addAllowedHeader("*");
                    // Permitimos todos los métodos
                    config.addAllowedMethod("GET");
                    config.addAllowedMethod("POST");
                    config.addAllowedMethod("PUT");
                    config.addAllowedMethod("DELETE");
                    config.addAllowedMethod("OPTIONS");
                    return config;
                }))
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                    authorizeRequests
                        // POST públicos
                        .requestMatchers("/cuenta/login", "/cuenta/registro", "/mercadopago/notification/*").permitAll()
                        // GET públicos
                        .requestMatchers(HttpMethod.GET, 
                            "/tutoriales", "/tutoriales/*",
                            "/vendedor", "/vendedor/*",
                            "/busqueda/busca",
                            "/plan",
                            "/productos/producto",
                            "/productos/buscar",
                            "/publicacion",
                            "/publicacion/*",
                            "/seguros",
                            "/textos/*"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        // Add the JWT Token filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}