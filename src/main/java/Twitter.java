import java.util.*;

public class Twitter {
    private static Map<Integer, User> users;

    public static void main(String[] args){


         User ward = new User("Ward", 0);
        ward.addFollower(1);

        User alan = new User("Alan", 1);
        alan.addFollower(2);

        User martin = new User("Martin", 2);

        ward.addFollower(2);
        ward.addFollower(1);

        alan.addTweet("If you have a procedure with 10 parameters, you probably missed some.");
        ward.addTweet("There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.");
        martin.addTweet("Random numbers should not be generated with a method chosen at random.");
        martin.addTweet("If you have a procedure with 10 parameters, you probably missed some.");

        users = new HashMap<Integer, User>();
        users.put(0, ward);
        users.put(1, alan);
        users.put(2, martin);

        showNewsFeed();

    }
    private static void showNewsFeed(){
        Map<Integer, User> sortUsers = sortUsersByName(users);
        for(Map.Entry<Integer, User> entry: sortUsers.entrySet()){
            System.out.println("key: "+ entry.getKey() + " Value: "+ entry.getValue().name);
        }
    }
    private static Map<Integer, User> sortUsersByName(Map<Integer, User> users){
        //converting map to list so we can sort properly
        List<Map.Entry<Integer, User>> list = new LinkedList<Map.Entry<Integer, User>>(users.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            public int compare(Map.Entry<Integer, User> user1, Map.Entry<Integer, User> user2) {
                return (user1.getValue().name).compareTo(user2.getValue().name);
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
