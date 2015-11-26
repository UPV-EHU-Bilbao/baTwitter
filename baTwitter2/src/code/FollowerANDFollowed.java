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

	
	  public void deskaratuJarraitzaileak(){}
	  
	   public static void main(String[] args) throws InterruptedException {
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
	                    //ids= twitter.getFollowersIDs(cursor);

	                }
	            
	              
	                
	               // Thread.sleep(itxaroteko);
	            } while ((cursor = ids.getNextCursor()) != 0);
	            int i=0;
	            for (long id : ids.getIDs()) {
                	if(i==179){
                		Thread.sleep(900*1000);
                		i=0;
                	}
	                   System.out.println(id);
	                    User user = twitter.showUser(id);
	                    System.out.println(user.getName());
	                    i++;
	                }
	            System.exit(0);
	            
	        } 
	        catch (TwitterException te) {
	        	int i=te.getRateLimitStatus().getSecondsUntilReset();
				System.out.println(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
				i= i/60;
				System.out.println(Integer.toString(i)+" minutu falta dira berriro exekutatu ahal izateko");
				//itxaroteko= te.getRateLimitStatus().getResetTimeInSeconds();
	            }
	        
	    }
	 }
