import java.util.*;

public class Twitter {
    private static Map<Integer, User> users;
    private static int priority=0;

    public static void main(String[] args){


         User ward = new User("Ward", 0);
        ward.addFollower(1);

        User alan = new User("Alan", 1);
        alan.addFollower(2);

        User martin = new User("Martin", 2);

        ward.addFollower(2);
        ward.addFollower(1);

        alan.addTweet("If you have a procedure with 10 parameters, you probably missed some.", priority++);
        ward.addTweet("There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.", priority++);
        alan.addTweet("Random numbers should not be generated with a method chosen at random.", priority++);


        users = new HashMap<Integer, User>();
        users.put(0, ward);
        users.put(1, alan);
        users.put(2, martin);

        showNewsFeed();

    }
    private static void showNewsFeed(){
        Map<Integer, User> sortUsers = sortUsersByName(users);
        for(Map.Entry<Integer, User> entry: sortUsers.entrySet()){
            Set<Integer> followers = entry.getValue().getFollowers();
            List<Tweet> allTweets = new ArrayList<Tweet>();
            for(int follower: followers){
                //System.out.println(sortUsers.get(follower).name);
                List<Tweet> tweet = sortUsers.get(follower).getCurrentTweet();
                allTweets.addAll(tweet);
            }
            Collections.sort(allTweets, new Comparator<Tweet>() {
                public int compare(Tweet user1, Tweet user2) {
                    return (user1.getPriority()+"").compareTo(user2.getPriority()+"");
                }
            });
            System.out.println(entry.getValue().getName());
            for(Tweet tweet: allTweets){
                System.out.println("\t" +"@"+sortUsers.get(tweet.getUserId())+":" + tweet.getMessage());
            }
        }
    }
    private static Map<Integer, User> sortUsersByName(Map<Integer, User> users){
        //converting map to list so we can sort properly
        List<Map.Entry<Integer, User>> list = new LinkedList<Map.Entry<Integer, User>>(users.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            public int compare(Map.Entry<Integer, User> user1, Map.Entry<Integer, User> user2) {
                return (user1.getValue().getName()).compareTo(user2.getValue().getName());
            }
        });

        //convert back the sorted list into a map
        Map<Integer, User> sortedUsers = new LinkedHashMap<Integer, User>();
        for (Map.Entry<Integer, User> entry: list) {
            sortedUsers.put(entry.getKey(), entry.getValue());
        }
        return  sortedUsers;
    }
}
