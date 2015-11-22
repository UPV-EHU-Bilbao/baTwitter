package code;

import java.util.ArrayList;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class FollowerANDFollowed {

	//private ArrayList<String> followed= new ArrayList<String>();
	//private ArrayList<String> following= new ArrayList<String>();
	//private static Twitter t;
	public FollowerANDFollowed(ConfigurationBuilder cb){
		// t = new TwitterFactory(cb.build()).getInstance();
	}

	

	 public static void main(String[] args) {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setOAuthAccessToken("368286014-qLSe2tzmXV934K3FBcXuhILwoYeOQd3C9EOQMrAQ");
			cb.setOAuthAccessTokenSecret("TLrGCtG976ZNDSQyDmtwKZ17SyXyrjBlBvfZBP9Qff2es");
			cb.setOAuthConsumerKey("VZui0P0P00DX1q9SeCxLlSDYv");
		    cb.setOAuthConsumerSecret("ZvlLujot49kqG6qd0SJp3PLFRyHUIp9XtmEw6bYOlOmqpFC1F1");
			Twitter t = new TwitterFactory(cb.build()).getInstance();
	        try {
	          //  Twitter twitter = new TwitterFactory().getInstance();
	        	
	            long cursor = 1;
	            IDs ids;
	            System.out.println("Listing followers's ids.");
	            do {
	                if (0 < args.length) {
	                    ids = t.getFollowersIDs(args[0], cursor);
	                    
	                    System.out.println(ids);
	                    //t.getFollowersList(args[0],cursor);
	                } else {
	                    ids = t.getFollowersIDs(cursor);
	                }
	                for (long id : ids.getIDs()) {
	                    System.out.println(id);
	                }
	            } while ((cursor = ids.getNextCursor()) != 0);
	            System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get followers' ids: " + te.getMessage());
	            System.exit(-1);
	        }
	 }
	 }
