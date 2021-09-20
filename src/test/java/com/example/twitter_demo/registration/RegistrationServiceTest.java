package com.example.twitter_demo.registration;

import com.example.twitter_demo.TwitterDemoApplication;
import com.example.twitter_demo.user.User;
import com.example.twitter_demo.user.UserRepository;
import com.example.twitter_demo.user.UserRole;
import com.example.twitter_demo.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
class RegistrationServiceTest
{


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
   // @MockBean
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
  //  String encodePassword = bCryptPasswordEncoder.encode("password");


    @Test
    void testUserRegistration() {
        String result = userService.signUp(new User("Mark5", "encodePassword", UserRole.USER));
        assertThat(result.equals("User Created"));
    }

    @Test
    void testLoadUsername(){
        userService.signUp(new User("Mark4", "encodePassword", UserRole.USER));
        final Optional<User> existUser = userRepository.findByUsername("Mark4");
        assertThat(existUser.isPresent());

    }
}
