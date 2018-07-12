import lombok.Data;

/**
 *
 */
@Data
public class Tweet {
 public String message;
 public Tweet nextTweet;

 public Tweet(String message)
 {
  this.message = message;
  this.nextTweet = null;
 }

}
