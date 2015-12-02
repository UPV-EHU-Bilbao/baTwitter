package code;
import java.util.*;

import twitter.MaximoaGaindituta;
import twitter4j.*;
import twitter4j.conf.*;

public class DeskargatuTwittak {
	
	
	
	public static void  main(String[] args) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthAccessToken("368286014-qLSe2tzmXV934K3FBcXuhILwoYeOQd3C9EOQMrAQ");
		cb.setOAuthAccessTokenSecret("TLrGCtG976ZNDSQyDmtwKZ17SyXyrjBlBvfZBP9Qff2es");
		cb.setOAuthConsumerKey("VZui0P0P00DX1q9SeCxLlSDYv");
	    cb.setOAuthConsumerSecret("ZvlLujot49kqG6qd0SJp3PLFRyHUIp9XtmEw6bYOlOmqpFC1F1");
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		int pagenum= 1;
		String usr= "bingenzio";
		List<Status> statuses = new ArrayList<Status>();
		long since = 1; //berriagoak
		long maxid = 1; //zaharragoak bakarrik lehehengo erabileran
		while(true){
			try{
				int size = statuses.size();
				
				Paging page = new Paging(pagenum++,214);
				//page.sinceId(sinceId)
				statuses.addAll(twitter.getUserTimeline(usr,page));
				//statuses.addAll(twitter.getFavorites(usr,page));
				for(Status status : statuses) {
					System.out.println(status.getId() + " " + status.getUser().getName()+" "+status.getText());
				
				}
				
				since=statuses.get(0).getId();
				maxid=statuses.get(statuses.size()-1).getId();
				System.out.println(since+" "+ maxid);
				
				if(statuses.size()== size){
					

					break;
				}//
				
			}
			catch(TwitterException e){
				int i=e.getRateLimitStatus().getSecondsUntilReset();
				System.out.println(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
				//MaximoaGaindituta m = new MaximoaGaindituta(i);
				
				System.exit(-1);
				
			}
		}
	
		// TODO Auto-generated method stub

	}

}
