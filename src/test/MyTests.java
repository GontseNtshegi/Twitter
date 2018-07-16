import org.junit.Assert;
import org.junit.Test;

public class MyTests {

    @Test
    public void equals() {
        User user1 = new User("User1");
        User user2 = new User("User1");
        User user3 = new User("User3");

        Assert.assertEquals("Two objects are the same",true,user1.equals(user2));
        Assert.assertEquals("Two objects are not the same",false ,user1.equals(user3));

    }
    @Test
    public void addFollower() {
        User user1 = new User("User1", 0);
        user1.addFollower(1);
        Assert.assertEquals("adding a follower",true ,user1.getFollowers().size()>1);
    }
    @Test
    public void removeFollower() {
        User user1 = new User("User1", 0);
        user1.removeFollower(0);
        Assert.assertEquals("adding a follower",true ,user1.getFollowers().size()==0);
    }
    @Test
    public void addTweet() {
        User user1 = new User("User1", 0);
        user1.addTweet("added tweet",1);
        Assert.assertEquals("adding a tweet",true ,user1.getCurrentTweet().size()>=1);
    }
}
