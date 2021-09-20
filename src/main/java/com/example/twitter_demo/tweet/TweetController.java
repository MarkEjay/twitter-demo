package com.example.twitter_demo.tweet;


import com.example.twitter_demo.exception.ApiException;
import com.example.twitter_demo.messaging.MessagingConfig;
import com.example.twitter_demo.user.User;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TweetController {

    private final TweetRepository tweetRepository;
    private final TweetService tweetService;


    @Autowired
    private RabbitTemplate template;

    @GetMapping("api/tweets")
    public List<Tweet> getTweets(){return tweetRepository.findAll();}

    @PostMapping("api/tweets/{id}")
    public String post(@RequestBody Tweet tweet, @PathVariable("id") User userId){

        String postTweet = tweetService.sendTweet(tweet, userId);

        //Sending the tweet to the queue
        template.convertAndSend(MessagingConfig.topicExchangeName,MessagingConfig.routingKey,tweet);
        if(postTweet.isEmpty()){
            throw new ApiException("User Id not found");
        }
        return postTweet;
    }
}
