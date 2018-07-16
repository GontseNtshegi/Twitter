import lombok.Data;

/**
 *
 */
@Data
public class Tweet {
 public String message;
 public int priority;// timestamp
 public Integer userId;//linked to which user

 /**
  * Parameterised constructor for assigning a tweet
  * @param message
  * @param prio
  * @param userid
  */
 public Tweet(String message, int prio, Integer userid)
 {
  this.message = message;
  this.priority = prio;
  this.userId = userid;
 }

}
