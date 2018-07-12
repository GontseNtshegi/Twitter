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
}
