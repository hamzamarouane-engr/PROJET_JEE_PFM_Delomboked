package com.vente.Vente.Service;

import com.vente.Vente.Models.Users;
import com.vente.Vente.Repository.UsersRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

        private static final Logger log = LoggerFactory.getLogger(UserService.class);

        private final UsersRepository usersRepository;

        public UserService(UsersRepository usersRepository) {
                this.usersRepository = usersRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username)
                        throws UsernameNotFoundException {

                log.info("Loading user: {}", username);

                Users user = usersRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return new org.springframework.security.core.userdetails.User(
                                user.getUsername(),
                                user.getPassword(),
                                user.getEnabled(),
                                true,
                                true,
                                true,
                                List.of(new SimpleGrantedAuthority(user.getRole())));
        }
}
