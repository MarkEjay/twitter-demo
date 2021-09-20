package com.example.twitter_demo.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService implements UserDetailsService {
    private final  UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> existUser = userRepository.findByUsername(username);
        if(existUser.isPresent()){
            return existUser.get();
        }
        else{
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
    }

    public String signUp(User user){
        String usernameExists = user.getUsername();
        final Optional<User> userExists = userRepository.findByUsername(usernameExists);
        if(userExists.isPresent()){
            throw  new IllegalStateException("Username already exist");
        }
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setEnabled(true);
        userRepository.save(user);
        return "User Created";
    }

    public int enableUser(String username){return userRepository.enableUser(username);}
}
