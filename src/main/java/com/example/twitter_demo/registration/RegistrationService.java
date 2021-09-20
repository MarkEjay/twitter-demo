package com.example.twitter_demo.registration;

import com.example.twitter_demo.user.User;
import com.example.twitter_demo.user.UserRole;
import com.example.twitter_demo.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;

    public String registerUser(Registration registration){
        String result = userService.signUp(
                new User(
                        registration.getUsername(),
                        registration.getPassword(),
                        UserRole.USER
                )
        );
        return result;
    }
}
