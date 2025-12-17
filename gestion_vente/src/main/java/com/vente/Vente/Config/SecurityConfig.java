package com.vente.Vente.Config;

import com.vente.Vente.Security.JwtAuthenticationFilter;
import com.vente.Vente.Security.JwtAuthenticationSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration for the application.
 * Configures JWT-based authentication and authorization.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;

        private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

        public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthenticationFilter,
                        UserDetailsService userDetailsService,
                        PasswordEncoder passwordEncoder,
                        @Lazy JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
                this.userDetailsService = userDetailsService;
                this.passwordEncoder = passwordEncoder;
                this.jwtAuthenticationSuccessHandler = jwtAuthenticationSuccessHandler;
        }

        /**
         * Configure security filter chain.
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(auth -> auth
                                                // Public endpoints
                                                .requestMatchers("/", "/login", "/auth/**", "/css/**", "/js/**",
                                                                "/images/**")
                                                .permitAll()
                                                // API endpoints - require authentication
                                                .requestMatchers("/api/**").authenticated()
                                                // Admin endpoints - require ADMIN role
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                // All other requests require authentication
                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider())
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                // Form login for web interface
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .successHandler(jwtAuthenticationSuccessHandler)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .deleteCookies("JWT-TOKEN", "JSESSIONID")
                                                .permitAll());

                return http.build();
        }

        /**
         * Configure authentication provider.
         */
        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder);
                return authProvider;
        }

        /**
         * Configure authentication manager.
         */
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }
}
