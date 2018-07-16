import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 *
 */
@Data
@NoArgsConstructor
public class User{
    public String name;
    public Integer identifier;
    public Set<Integer> followers;
    public List<Tweet> currentTweet;

    public User(String n){
        this.name = n;
    }

    /**
     * Parameterised constructor for defaulting fields
     * @param n
     * @param id
     */
    public User(String n, Integer id){
        this.name = n;
        this.identifier = id;
        this.followers = new HashSet<Integer>() ;
        this.followers.add(id); //user can also follow himself
        this.currentTweet = new LinkedList<Tweet>();
    }

    /**
     * add users this user is following
     * @param identifier
     */
    public void addFollower(Integer identifier){
        this.followers.add(identifier);
    }

    /**
     * Remove a user that this user is following
     * @param identifier
     */
    public void removeFollower(Integer identifier){
        this.followers.remove(identifier);
    }
    public void addTweet(String message, int prio){
        Tweet tweet = new Tweet(message, prio, this.identifier);
        this.currentTweet.add(tweet);

    }
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * assume unique names or twitter handles
     * @param o
     * @return
     */
    public boolean equals(Object o){
        User user = (User)o;
        return this.name.equals(user.name);
    }
    @Override
    public int hashCode(){
       int result =17;
       result =31 * result + name.hashCode();

       return result;
    }
}
