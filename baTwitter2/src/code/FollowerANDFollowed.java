package code;



import twitter4j.IDs;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;


public class FollowerANDFollowed {

	//private ArrayList<String> followed= new ArrayList<String>();
	//private ArrayList<String> following= new ArrayList<String>();
	private static Twitter t;
	
	public FollowerANDFollowed(ConfigurationBuilder cb){
		 t = new TwitterFactory(cb.build()).getInstance();
	}

	
	  
	  
	   public static void main(String[] args) {
		   ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setOAuthAccessToken("368286014-qLSe2tzmXV934K3FBcXuhILwoYeOQd3C9EOQMrAQ");
			cb.setOAuthAccessTokenSecret("TLrGCtG976ZNDSQyDmtwKZ17SyXyrjBlBvfZBP9Qff2es");
			cb.setOAuthConsumerKey("VZui0P0P00DX1q9SeCxLlSDYv");
		    cb.setOAuthConsumerSecret("ZvlLujot49kqG6qd0SJp3PLFRyHUIp9XtmEw6bYOlOmqpFC1F1");
	        try {
	    		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
	            long cursor = -1;
	            IDs ids;
	          //  IDs idss;
	            System.out.println("Listing following ids.");
	            do {
	                if (0 < args.length) {
	                    //ids = twitter.getFriendsIDs(args[0], cursor);
	                    ids= twitter.getFollowersIDs(args[0], cursor);
	                } else {
	                    ids = twitter.getFriendsIDs(cursor);
	                    
	                }
	               // final ResponseList<User> users = twitter.lookupUsers(ids.getIDs());
	                for (long id : ids.getIDs()) {
	                    System.out.println(id);
	                    User user = twitter.showUser(id);
	                    System.out.println(user.getName());
	                }
	                //for (User u : users) {
	              //System.out.println(users.get(0).getName());
	                   // System.out.println(u.getScreenName());
	               // }
	            } while ((cursor = ids.getNextCursor()) != 0);
	            System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	           System.out.println(te.getRateLimitStatus());
	            System.exit(-1);
	        }
	    }
	 }
