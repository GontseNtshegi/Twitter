import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Data
@NoArgsConstructor
public class User {
    public String name;
    public Integer identifier;
    public Set<Integer> followers;
    public List<Tweet> currentTweet;

    public User(String n, Integer id){
        this.name = n;
        this.identifier = id;
        this.followers = new HashSet<Integer>() ;
        this.followers.add(id); //user can also follow himself
        this.currentTweet = new LinkedList<Tweet>();
    }

    public void addFollower(Integer identifier){
        this.followers.add(identifier);
    }
    public void removeFollower(Integer identifier){
        this.followers.remove(identifier);
    }
    public void addTweet(String message, int prio){
        Tweet tweet = new Tweet(message, prio, this.identifier);
        this.currentTweet.add(tweet);
       // tweet.nextTweet = currentTweet;
        //if(this.currentTweet==null)
          //  this.currentTweet = tweet;
        //else
          //  this.currentTweet.nextTweet = tweet;
    }
    public String toString(){
        return this.name;
    }
}
