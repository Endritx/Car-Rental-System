package com.RentalCars.security;


import com.RentalCars.domain.User;
import com.RentalCars.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUser {

        private final UserRepository userRepository;

        public LoggedInUser(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        public User getUser() {

                Authentication principal = SecurityContextHolder.getContext().getAuthentication();
                String username = principal.getName();
                return userRepository.findByUsername(username).orElseThrow();

        }

}
