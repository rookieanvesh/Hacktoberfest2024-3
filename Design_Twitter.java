// Class to simulate basic Twitter functionality
class Twitter {
    List<Tweet> tweetList;  // Store all tweets
    HashMap<Integer, Set<Integer>> followMap;  // Store follow relationships
    // Constructor to initialize data structures
    public Twitter() {
        tweetList = new ArrayList<>();
        followMap = new HashMap<>();
    }
    // Method to post a new tweet
    public void postTweet(int userId, int tweetId) {
        tweetList.add(new Tweet(userId, tweetId));
    }
    // Method to get the news feed for a user
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> tweets = new ArrayList<>();
        Set<Integer> following = followMap.get(userId);
        if(following == null) {
            following = new HashSet<>();
        }
        following.add(userId);  // Include user's own tweets
        // Iterate through tweets in reverse order (most recent first)
        for(int i = tweetList.size() - 1; i >= 0; i--) {
            if(following.contains(tweetList.get(i).userId)) {
                tweets.add(tweetList.get(i).tweetId);
            }
            if(tweets.size() == 10) break;  // Limit to 10 tweets
        }
        return tweets;
    }
    // Method to follow a user
    public void follow(int userId, int followeeId) {
        Set<Integer> following;
        if(followMap.containsKey(userId)) {
            following = followMap.get(userId);
        } else {
            following = new HashSet<>();
        }
        following.add(followeeId);
        followMap.put(userId, following);
    }
    // Method to unfollow a user
    public void unfollow(int userId, int followeeId) {
        Set<Integer> following = followMap.get(userId);
        if(following != null) {
            following.remove(followeeId);
            followMap.put(userId, following);
        }
    }
}

// Class to represent a tweet
class Tweet {
    int userId;
    int tweetId;

    public Tweet(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
    }
}
