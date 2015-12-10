package code;

import java.nio.channels.Pipe.SinkChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.AQUA;

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

	private  Twitter tw;

	public  DeskargaKudeatzailea(ConfigurationBuilder cb){
		tw= LoginBeharrezkoKode.getLoginCode().getTwitterInstance();
		//t = new TwitterFactory(cb.build()).getInstance();
	}
//cambiar a conn
	public void jarraitzaileak(DBK db) throws InterruptedException, SQLException{
	      try {
	            long cursor = -1;
	            IDs ids;
	            do {
	                    ids= tw.getFollowersIDs(cursor);
	            } while ((cursor = ids.getNextCursor()) != 0);
	            int i=0;
	            for (long id : ids.getIDs()) {
              	if(i==179){
              		Thread.sleep(900*1000);
              		i=0;
              	}
	                    User user = tw.showUser(id); 
	                    System.out.println(user.getName());
	                    this.sartuJErabiltzaileaDB(user.getName().replace("'", " "), false, db);
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
	                    ids = tw.getFriendsIDs(cursor);
	                    
	               // Thread.sleep(itxaroteko);
	            } while ((cursor = ids.getNextCursor()) != 0);
	            int i=0;
	            for (long id : ids.getIDs()) {
            	if(i==179){
            		Thread.sleep(900*1000);
            		i=0;
            	}
	                    User user = tw.showUser(id); 
	                    System.out.println(user.getName());
	                    this.sartuJErabiltzaileaDB(user.getName().replace("'", " "), true, db);
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
		long since=0;
		long max=0;
		ArrayList<Tweet> favLista= new ArrayList<Tweet>();
		List<Status> statuses = new ArrayList<Status>();
		ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
		while(var.next()){
			since = var.getLong(1);
			max= var.getLong(2);
		}
		
		while(true){
			try{
				for(int i=0;i<=15;i++){
				int size = statuses.size();
				if(max==0&&since==0){
					Paging page = new Paging(pagenum++,214);
				}
				else if(max==0){
					Paging page = new Paging(pagenum++,214,since);

				}else if (since==0){
					Paging page = new Paging(pagenum++,214,max);

				}else{
				Paging page = new Paging(pagenum++,214,since,max);
				
				//page.sinceId(since);
				//statuses.addAll(t.getUserTimeline(usr,page));
				statuses.addAll(tw.getFavorites(usr, page));				
				//since=statuses.get(0).getId();
				for(Status status : statuses) {
					System.out.println(status.getText());
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

	public void nireTweet(String usr,DBK db) throws InterruptedException, SQLException{
		ArrayList<Tweet> rtLista= new ArrayList<Tweet>();

		int pagenum= 1;
		List<Status> statuses = new ArrayList<Status>();
		long since = 1; //berriagoak db tik hartu behar
		long max=1; // db tik hartu
		ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
		while(var.next()){
			
			since = var.getLong(1);
			max= var.getLong(2);
		}
		while(true){
			try{
				Paging page;
				for(int i=0;i<=15;i++){
				int size = statuses.size();
				if(max!=0) {
				 page = new Paging(pagenum++,214,since,max);}
				else {
				 page = new Paging(pagenum++,214);
				}
				
				//page.sinceId(since);
				statuses.addAll(tw.getUserTimeline(usr,page));
				//since=statuses.get(0).getId();
				max=statuses.get(statuses.size()-1).getId();
				for(Status status : statuses) {
					System.out.println(status.getText());
					long id=status.getId();
					String erab=status.getUser().getName();
					String edukia=status.getText();
					int favKop=status.getFavoriteCount();
					int rtKop= status.getRetweetCount();
					boolean fav=status.isFavorited();
					boolean rt= status.isRetweetedByMe();
					String url=status.getURLEntities().toString();
					//String image=status.m
					Tweet twet= new Tweet(edukia,erab,rt,fav,rtKop,favKop,id,url);
				
				rtLista.add(twet);
				if(size==statuses.size()){
						break;
					}
				if (i==15){
						i=0;
						this.sartuTweetDB(db, rtLista, usr);
						Thread.sleep(900*1000);
							}
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
			DBK.getInstantzia().saveTweetInfo(t.getTextua(), switchBooltoInt(t.getRT()), switchBooltoInt(t.getFav()), t.getRtKop(), t.getFavKop(), t.getUrl(), null, t.getId(), t.getIdazlea());
		}
	}
private void sartuStatusDB(DBK db,List<Status> lista,String usr) throws SQLException{
		//Hau DBK-n egon behar da.
		Iterator<Status> i= lista.iterator();
		while(i.hasNext()){
			Status t= i.next();
			if(t.getURLEntities().length==0){
			DBK.getInstantzia().saveTweetInfo(t.getText().replace("'", "''"), switchBooltoInt(t.isRetweetedByMe()), switchBooltoInt(t.isFavorited()), t.getRetweetCount(), t.getFavoriteCount(),null, null, t.getId(), t.getUser().getScreenName());
			}
			else
				DBK.getInstantzia().saveTweetInfo(t.getText().replace("'", "''"), switchBooltoInt(t.isRetweetedByMe()), switchBooltoInt(t.isFavorited()), t.getRetweetCount(), t.getFavoriteCount(), t.getURLEntities()[0].getDisplayURL(), null, t.getId(), t.getUser().getScreenName());
		}
	}
private int switchBooltoInt(boolean b){
	int var = b? 1 : 0;
	return var;

}


private void sartuJErabiltzaileaDB(String izena,boolean jarraitua, DBK db) throws SQLException, IllegalStateException, TwitterException{
		if(!jarraitua)
		db.saveFollowers(izena);
		else
			db.saveFollowing(izena);
	}

	
	
	

public void gustokoakJaitsi(){
	Twitter twitter = LoginBeharrezkoKode.getLoginCode().getTwitterInstance();
	boolean amaituta = false;
	int pagenum= 1;
	long since=1;
	long max=1;
		try{
			List<Status> statuses = new ArrayList<Status>();
			ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
			while(var.next()){
				
				since = var.getLong(1);
				max= var.getLong(2);
			}
			if(since==0){since=1;}
			if(max==0){max=1;}
			
			while (!amaituta) {
				
				statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					this.sartuStatusDB(DBK.getInstantzia(), statuses, LoginBeharrezkoKode.getLoginCode().twitter.verifyCredentials().getScreenName());;
			}
			pagenum = 1;
			amaituta = false;
			while (!amaituta) {
				
				statuses = twitter.getFavorites(new Paging(pagenum++, 200, since, max));
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					
					this.sartuStatusDB(DBK.getInstantzia(), statuses, LoginBeharrezkoKode.getLoginCode().twitter.getScreenName());;
			}
			DBK.getInstantzia().paramSave(since, max);
			
		}catch (TwitterException | SQLException sq) {
			sq.printStackTrace();
	}
	
	}

}
	
