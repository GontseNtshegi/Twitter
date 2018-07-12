import lombok.Data;

import java.util.Set;

/**
 *
 */
@Data
public class User {
    public String name;
    public Integer identifier;
    public Set<Integer> followers;
    public Tweet currentTweet;

    public User(String n, Integer id){
        this.name = n;
        this.identifier = id;
        this.followers.add(id); //user can also follow himself
        this.currentTweet = null;
    }

    public void addFollower(Integer identifier){
        this.followers.add(identifier);
    }
    public void removeFollower(Integer identifier){
        this.followers.remove(identifier);
    }
    public void addTweet(String message){
        Tweet tweet = new Tweet(message);
        tweet.nextTweet = currentTweet;
        this.currentTweet = tweet;

    }
}
