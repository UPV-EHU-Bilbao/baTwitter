package code;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dbRelated.DBK;
import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class DeskargaKudeatzailea {

	private  Twitter t;

	public  DeskargaKudeatzailea(ConfigurationBuilder cb){
		 t = new TwitterFactory(cb.build()).getInstance();
	}

	public void jarraitzaileak(DBK db) throws InterruptedException, SQLException{
	      try {
	            long cursor = -1;
	            IDs ids;
	            do {
	                    ids= t.getFollowersIDs(cursor);
	            } while ((cursor = ids.getNextCursor()) != 0);
	            int i=0;
	            for (long id : ids.getIDs()) {
              	if(i==179){
              		Thread.sleep(900*1000);
              		i=0;
              	}
	                    User user = t.showUser(id); 
	                    this.sartuJErabiltzaileaDB(user.getName(), false, db);
	                    //gorde db-n
	                    i++;
	                }
	            System.exit(0);
	            
	        } 
	        catch (TwitterException te) {
	        	int i=te.getRateLimitStatus().getSecondsUntilReset();
				System.out.println(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
				i= i/60;
				System.out.println(Integer.toString(i)+" minutu falta dira berriro exekutatu ahal izateko");
	            
	        }
	    }

	public void jarraituak(DBK db) throws InterruptedException, SQLException{
	      try {
	            long cursor = -1;
	            IDs ids;
	            do {
	                    ids = t.getFriendsIDs(cursor);
	                    
	               // Thread.sleep(itxaroteko);
	            } while ((cursor = ids.getNextCursor()) != 0);
	            int i=0;
	            for (long id : ids.getIDs()) {
            	if(i==179){
            		Thread.sleep(900*1000);
            		i=0;
            	}
	                    User user = t.showUser(id);      
	                    this.sartuJErabiltzaileaDB(user.getName(), true, db);

	                    i++;
	                }
	            System.exit(0);
	            
	        } 
	        catch (TwitterException te) {
	        	int i=te.getRateLimitStatus().getSecondsUntilReset();
				System.out.println(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
				i= i/60;
				System.out.println(Integer.toString(i)+" minutu falta dira berriro exekutatu ahal izateko");
	            
	        }
	    }

	public void faboritoak(String usr,DBK db) throws InterruptedException, SQLException{
		int pagenum= 1;
		ArrayList<Tweet> favLista= new ArrayList<Tweet>();
		List<Status> statuses = new ArrayList<Status>();
		long since = 1; //berriagoak db tik hartu behar
		//long max=1; // db tik
		while(true){
			try{
				for(int i=0;i<=15;i++){
				int size = statuses.size();
				
				//Paging page = new Paging(pagenum++,214,since,max);
				Paging page = new Paging(pagenum++,214);
				
				//page.sinceId(since);
				//statuses.addAll(t.getUserTimeline(usr,page));
				statuses.addAll(t.getFavorites(usr, page));				
				since=statuses.get(0).getId();
				for(Status status : statuses) {
					long id=status.getId();
					String erab=status.getUser().getName();
					String edukia=status.getText();
					int favKop=status.getFavoriteCount();
					int rtKop= status.getRetweetCount();
					boolean fav=status.isFavorited();
					boolean rt= status.isRetweeted();
					String url=status.getURLEntities().toString();
					//String image=status.m
					Tweet twet= new Tweet(edukia,erab,rt,fav,rtKop,favKop,id,url);
					
						favLista.add(twet);
				if(size==statuses.size()){
					break;
				}
				if (i==15){
					i=0;
					this.sartuTweetDB(db, favLista, usr);
					Thread.sleep(900*1000);
				}
				}		
			}}
		catch(TwitterException e){
			int i=e.getRateLimitStatus().getSecondsUntilReset();
			System.out.println(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
			//MaximoaGaindituta m = new MaximoaGaindituta(i);
			
			System.exit(-1);
			
		}
	

		}

	
	}

	public void nireTweet(String usr,DBK db) throws InterruptedException, SQLException{
		ArrayList<Tweet> propioak= new ArrayList<Tweet>();
		ArrayList<Tweet> rtLista= new ArrayList<Tweet>();

		int pagenum= 1;
		List<Status> statuses = new ArrayList<Status>();
		long since = 1; //berriagoak db tik hartu behar
		long max=1; // db tik hartu
		while(true){
			try{
				for(int i=0;i<=15;i++){
				int size = statuses.size();
				//if(max=null) {
				//Paging page = new Paging(pagenum++,214,since,max);}
				//else{
				Paging page = new Paging(pagenum++,214,since);
				//}
				
				//page.sinceId(since);
				statuses.addAll(t.getUserTimeline(usr,page));
				since=statuses.get(0).getId();
				for(Status status : statuses) {
					long id=status.getId();
					String erab=status.getUser().getName();
					String edukia=status.getText();
					int favKop=status.getFavoriteCount();
					int rtKop= status.getRetweetCount();
					boolean fav=status.isFavorited();
					boolean rt= status.isRetweeted();
					String url=status.getURLEntities().toString();
					//String image=status.m
					Tweet twet= new Tweet(edukia,erab,rt,fav,rtKop,favKop,id,url);
					if (!rt&&!fav){propioak.add(twet);}
					else if (rt&&!fav){
						rtLista.add(twet);
					}
					
				
				}
				if(size==statuses.size()){
					break;
				}
				if (i==15){
					i=0;
					this.sartuTweetDB(db, rtLista, usr);
					this.sartuTweetDB(db, rtLista, usr);
					Thread.sleep(900*1000);
				}
				}		
			}
	
		catch(TwitterException e){
			int i=e.getRateLimitStatus().getSecondsUntilReset();
			System.out.println(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
			//MaximoaGaindituta m = new MaximoaGaindituta(i);
			
			System.exit(-1);
			
		}
	

		}

	
	}

	private void sartuTweetDB(DBK db,ArrayList<Tweet> lista,String usr) throws SQLException{
		
		Iterator<Tweet> i= lista.iterator();
		while(i.hasNext()){
			Tweet t= i.next();
			DBK.getInstantzia().saveTweetInfo(t.getTextua(), t.getRT(), t.getFav(), t.getRtKop(), t.getFavKop(), t.getUrl(), null, t.getId(), usr, t.getIdazlea());
		}
	}

	private void sartuJErabiltzaileaDB(String izena,boolean jarraitua, DBK db) throws SQLException{
		if(!jarraitua)
		db.saveFollowers(izena);
		else
			db.saveFollowing(izena);
	}
}