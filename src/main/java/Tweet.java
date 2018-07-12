import lombok.Data;

/**
 *
 */
@Data
public class Tweet {
 public String message;
 //public Tweet nextTweet;
 public int priority;
 public Integer userId;

 public Tweet(String message, int prio, Integer userid)
 {
  this.message = message;
  //this.nextTweet = null;
  this.priority = prio;
  this.userId = userid;
 }

}
