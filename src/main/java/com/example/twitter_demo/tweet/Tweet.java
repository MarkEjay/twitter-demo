package com.example.twitter_demo.tweet;

import com.example.twitter_demo.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String body;
    @ManyToOne
    @JoinColumn(nullable = false, name="user_id")
    private User userId;

    public Tweet(String body, User userId) {
        this.body=body;
        this.userId=userId;
    }
}
