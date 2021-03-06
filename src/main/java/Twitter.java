import java.io.File;
import java.io.IOException;
import java.util.*;

public class Twitter {
    private static Map<Integer, User> users;
    private static int priority = 0;

    public static void main(String[] args) {

        Scanner input = null;
        users = new HashMap<Integer, User>();
        //map to hold follower and following relationship
        Map<String, List<String>> usersFromFile = new HashMap<String, List<String>>();
        try {
            input = new Scanner(getFile("user.txt"));

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] words = line.split("(,|\\s)+");//removes spaces and commas
                List<String> tempList = new ArrayList<String>();
                for (int i = 0; i < words.length; i++) {
                    if (!words[i].equalsIgnoreCase("follows") && i != 0) {
                        tempList.add(words[i]);
                    }
                }
                usersFromFile.put(words[0], tempList);//mapped all users and followers
            }
            int userid = 0;//unique identifier
            for (Map.Entry<String, List<String>> entry : usersFromFile.entrySet()) {
                User user = new User(entry.getKey(), userid);

                if (!users.containsValue(user)) {//assuming  users will have unique names
                    users.put(userid, user);
                    userid++;
                }
                for (String follower : entry.getValue()) {//go through the users this user wants to follow
                    User temp = new User(follower, userid);
                    if (users.containsValue(temp)){//checks if the follower is already a user
                        for(Integer key: users.keySet()){//get all keys to find this user
                            if(users.get(key).equals(temp)) {
                                for(Integer key2: users.keySet()){
                                    if(users.get(key2).equals(user)) {
                                        users.get(key2).addFollower(key);//add to the current item
                                    }
                                }
                            }
                        }
                    }
                    else {//this is not a known user so add him as a user
                        User tempUser = new User(follower, userid);
                        for(Integer key: users.keySet()){
                            if(users.get(key).equals(user))
                                users.get(key).addFollower(userid);//then add him as a follower
                        }
                        users.put(userid, tempUser);
                        userid++;
                    }
                }

            }
            //Reading the tweet file
            input = new Scanner(getFile("tweet.txt"));
            while (input.hasNextLine()){
                String line  = input.nextLine();
                String[] split = line.split("(>)+");
                //asuming the file follows the format described
                for(Integer key: users.keySet()){
                    User tempUser = new User(split[0]);
                    if(users.get(key).equals(tempUser))
                        users.get(key).addTweet(split[1], priority++);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("The file format is wrong: "+ e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        showNewsFeed();

    }

    /**
     * Method to list all the newsfeed in this Twitter
     */
    private static void showNewsFeed() {
        Map<Integer, User> sortUsers = sortUsersByName(users);//sort user map
        for (Map.Entry<Integer, User> entry : sortUsers.entrySet()) {
            Set<Integer> followers = entry.getValue().getFollowers();//get follower for this user
            List<Tweet> allTweets = new ArrayList<Tweet>();//object to store all tweets for all followers
            for (int follower : followers) {
                List<Tweet> tweet = sortUsers.get(follower).getCurrentTweet();//get tweets for this follower
                allTweets.addAll(tweet);//merge it to the list of tweets for all followers
            }
            //sort all followers tweets based on priority
            Collections.sort(allTweets, new Comparator<Tweet>() {
                public int compare(Tweet user1, Tweet user2) {
                    return (user1.getPriority() + "").compareTo(user2.getPriority() + "");
                }
            });
            System.out.println(entry.getValue().getName());
            for (Tweet tweet : allTweets) {
                System.out.println("\t" + "@" + sortUsers.get(tweet.getUserId()) + ":" + tweet.getMessage());
            }
        }
    }

    /**
     * Method to sort users by name
     * @param users
     * @return
     */
    private static Map<Integer, User> sortUsersByName(Map<Integer, User> users) {
        //converting map to list so we can sort properly
        List<Map.Entry<Integer, User>> list = new LinkedList<Map.Entry<Integer, User>>(users.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            public int compare(Map.Entry<Integer, User> user1, Map.Entry<Integer, User> user2) {
                return (user1.getValue().getName()).compareTo(user2.getValue().getName());
            }
        });

        //convert back the sorted list into a map
        Map<Integer, User> sortedUsers = new LinkedHashMap<Integer, User>();
        for (Map.Entry<Integer, User> entry : list) {
            sortedUsers.put(entry.getKey(), entry.getValue());
        }
        return sortedUsers;
    }

    /**
     * Read a file locally
     * @param fileName
     * @return
     */
    private static File getFile(String fileName) {
        File file = new File(Twitter.class.getResource(fileName).getFile());
        return file;
    }

}
