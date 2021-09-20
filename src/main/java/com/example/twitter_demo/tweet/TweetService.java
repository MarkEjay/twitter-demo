package com.example.twitter_demo.tweet;

import com.example.twitter_demo.exception.ApiException;
import com.example.twitter_demo.user.User;
import com.example.twitter_demo.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TweetService  {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    public String sendTweet(Tweet tweet, User userId)  {
        Optional<User> userExists = userRepository.findById(userId.getId());
        if(!userExists.isPresent()){
           return "";
        }

        Tweet newTweet = new Tweet(tweet.getBody(), userId);
        tweetRepository.save(newTweet);
        return "Tweet Sent";
    }
}
