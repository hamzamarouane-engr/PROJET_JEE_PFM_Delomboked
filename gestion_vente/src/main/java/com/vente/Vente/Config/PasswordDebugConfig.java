package com.vente.Vente.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordDebugConfig {

    @Bean
    CommandLineRunner printPassword(PasswordEncoder encoder) {
        return args -> {
            System.out.println("BCrypt password = " + encoder.encode("adminadmin"));
        };
    }
}
