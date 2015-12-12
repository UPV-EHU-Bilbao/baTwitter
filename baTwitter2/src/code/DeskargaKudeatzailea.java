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

	public  DeskargaKudeatzailea(){
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

	
	public void nireTweet() throws InterruptedException, SQLException, TwitterException{
		//Twitter twitter = LoginBeharrezkoKode.getLoginCode().getTwitterInstance();
		boolean amaituta = false;
		int pagenum= 1;
		long since=0;
		long max=0;
		String user=LoginBeharrezkoKode.getLoginCode().twitter.verifyCredentials().getScreenName();
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
					
					Paging page= new Paging(pagenum++, 200, since);
					statuses.addAll(tw.getUserTimeline(user,page));
					//statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));
					if (statuses.isEmpty()) {
						amaituta = true;
					} else
						this.sartuStatusDB(DBK.getInstantzia(), statuses,user);;
				}
				pagenum = 1;
				amaituta = false;
				while (!amaituta) {
					Paging page= new Paging(pagenum++, 200, since,max);
					statuses.addAll(tw.getUserTimeline(user,page));
					//statuses = twitter.getFavorites(new Paging(pagenum++, 200, since, max));
					if (statuses.isEmpty()) {
						amaituta = true;
					} else
						
						this.sartuStatusDB(DBK.getInstantzia(), statuses, user);;
				}
				DBK.getInstantzia().paramSave(since, max);
				
			}catch (TwitterException | SQLException sq) {
				sq.printStackTrace();
		}
	
	

		}

	
	


private void sartuStatusDB(DBK db,List<Status> lista,String usr) throws SQLException{
		//Hau DBK-n egon behar da.
		Iterator<Status> i= lista.iterator();
		while(i.hasNext()){
			Status t= i.next();
			if(t.getURLEntities().length==0){
				System.out.println(t.getFavoriteCount());
				System.out.println(t.getUser().getScreenName());
			DBK.getInstantzia().saveTweetInfo(t.getText().replace("'", "''"), switchBooltoInt(t.isRetweet()), switchBooltoInt(t.isFavorited()), t.getRetweetCount(), t.getFavoriteCount(),null, null, t.getId(), t.getUser().getScreenName());
			}
			else
				DBK.getInstantzia().saveTweetInfo(t.getText().replace("'", "''"), switchBooltoInt(t.isRetweet()), switchBooltoInt(t.isFavorited()), t.getRetweetCount(), t.getFavoriteCount(), t.getURLEntities()[0].getDisplayURL(), null, t.getId(), t.getUser().getScreenName());
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
	long since=0;
	long max=0;
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
				//statuses= twitter.getHomeTimeline(new Paging(pagenum++, 200, since));
				//statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since));
				statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					this.sartuStatusDB(DBK.getInstantzia(), statuses, LoginBeharrezkoKode.getLoginCode().twitter.verifyCredentials().getScreenName());;
			}
			pagenum = 1;
			amaituta = false;
			while (!amaituta) {
				//statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since,max));
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

public void rtJaitsi(){
	Twitter twitter = LoginBeharrezkoKode.getLoginCode().getTwitterInstance();
	boolean amaituta = false;
	int pagenum= 1;
	long since=0;
	long max=0;
		try{
			List<Status> statuses = new ArrayList<Status>();
			/*
			if(mota.equals("faboritoa")){}
			else if (mota.equals("propioak")){}
			else if (mota.equals("rt")){}*/
					
			ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
			while(var.next()){
				
				since = var.getLong(1);
				max= var.getLong(2);
			}
			if(since==0){since=1;}
			if(max==0){max=1;}
			
			while (!amaituta) {
				/*
				if(mota.equals("faboritoa")){}
				else if (mota.equals("propioak")){}
				else if (mota.equals("rt")){}*/
				//statuses= twitter.getHomeTimeline(new Paging(pagenum++, 200, since));
				statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since));
				//statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					this.sartuStatusDB(DBK.getInstantzia(), statuses, LoginBeharrezkoKode.getLoginCode().twitter.verifyCredentials().getScreenName());;
			}
			pagenum = 1;
			amaituta = false;
			while (!amaituta) {
				/*
				if(mota.equals("faboritoa")){}
				else if (mota.equals("propioak")){}
				else if (mota.equals("rt")){}*/
				//statuses= twitter.getHomeTimeline(new Paging(pagenum++, 200, since));

				statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since,max));
				//statuses = twitter.getFavorites(new Paging(pagenum++, 200, since, max));
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


public void deskargatu(String mota){
	Twitter twitter = LoginBeharrezkoKode.getLoginCode().getTwitterInstance();
	boolean amaituta = false;
	int pagenum= 1;
	long since=0;
	long max=0;
		try{
			List<Status> statuses = new ArrayList<Status>();
			/*
			if(mota.equals("faboritoa")){
			ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
			else if (mota.equals("propioak")){
			ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");}
			else if (mota.equals("rt")){ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");}
			*/
					
			ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
			while(var.next()){
				
				since = var.getLong(1);
				max= var.getLong(2);
			}
			if(since==0){since=1;}
			if(max==0){max=1;}
			
			while (!amaituta) {
				/*
				if(mota.equals("faboritoa")){statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));}
				else if (mota.equals("propioak")){statuses= twitter.getHomeTimeline(new Paging(pagenum++, 200, since));}
				else if (mota.equals("rt")){*/
				
				statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since));
				//}
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					this.sartuStatusDB(DBK.getInstantzia(), statuses, LoginBeharrezkoKode.getLoginCode().twitter.verifyCredentials().getScreenName());;
			}
			pagenum = 1;
			amaituta = false;
			while (!amaituta) {
				/*
				if(mota.equals("faboritoa")){statuses = twitter.getFavorites(new Paging(pagenum++, 200, since,max));}
				else if (mota.equals("propioak")){statuses= twitter.getHomeTimeline(new Paging(pagenum++, 200, since,max));}
				else if (mota.equals("rt")){*/
				

				statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since,max));
				//}
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
	
