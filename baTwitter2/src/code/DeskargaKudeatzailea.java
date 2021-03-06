
package code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import dbRelated.DBK;
import exception.Salbuespenak;
import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Tweet-ak eta erabiltzaileak deskargatzeko eta DB-n gordetzeko klase kudeatzailea.
 * @author BATwitter
 *
 */

public class DeskargaKudeatzailea {

	private  Twitter tw;

	public  DeskargaKudeatzailea(){
		tw= LoginBeharrezkoKode.getLoginCode().getTwitterInstance();
	}


	/**
	 * Jarraitzaileak deskargatu eta datu basean gordetzen ditu.
	 * @param db gure datu basea
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public void jarraitzaileak(DBK db){
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
	                    this.sartuJErabiltzaileaDB(user.getName().replace("'", " "), false, db);
	                    //gorde db-n
	                    i++;
	                }
	            System.exit(0);
	            
	        } 
	        catch (TwitterException te ) {
	        	int i=te.getRateLimitStatus().getSecondsUntilReset();
				throw new Salbuespenak(Integer.toString(i)+" segundu falta dira berriro exekutatu ahal izateko");
	        } catch (InterruptedException e) {
				throw new Salbuespenak("Arazo bat egon da Kontadorearekin, berriro saia zaitez");
			}
	    }
	/**
	 * Erabiltzaileak Twitterren jarraitzen dituen erabiltzaileak deskargatu eta datu basean gordetzen ditu.
	 * @param db gure datu basea
	 * @throws InterruptedException
	 * @throws SQLException
	 */
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
	                    this.sartuJErabiltzaileaDB(user.getName().replace("'", " "), true, db);
	                    i++;
	                }
	            System.exit(0);
	            
	        } 
	        catch (TwitterException te) {
	        	int i=te.getRateLimitStatus().getSecondsUntilReset();
				throw new Salbuespenak(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
				}
	    }

	/**
	 * Erabltzaileak idatzitako tweet-ak eta egindako retweetak deskargatu eta datu basean gordetzen duen metodoa.
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public void nireTweet(){
		boolean amaituta = false;
		int pagenum= 1;
		long since=0;
		long max=0;
			try{
				String user=LoginBeharrezkoKode.getLoginCode().getTwitterInstance().verifyCredentials().getScreenName();
				List<Status> statuses = new ArrayList<Status>();
				ResultSet var=DBK.getInstantzia().execSQL("Select since, max from superuser");
				while(var.next()){
					since = var.getLong(1);
					max= var.getLong(2);
				}
				if(since==0){since=1L;}
				if(max==0){max=Long.MAX_VALUE;}
				
				while (!amaituta) {
					
					Paging page= new Paging(pagenum++, 200, since);
					statuses.addAll(tw.getUserTimeline(user,page));
					//statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));
					if (statuses.isEmpty()) {
						amaituta = true;
					} else
						this.sartuStatusDB(statuses,user);;
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
						
						this.sartuStatusDB(statuses, user);;
				}
				DBK.getInstantzia().paramSave(since, max);
				
			} catch (TwitterException te) {
	        	int i=te.getRateLimitStatus().getSecondsUntilReset();
				throw new Salbuespenak(Integer.toString(i)+" segundo falta dira berriro exekutatu ahal izateko");
				
			}catch(SQLException sq) {
				throw new Salbuespenak("Datu baseak errore bat du");
		}
	
	

		}

	
	

	/**
	 * Tweetak datu basean gordetzeko metodoa
	 * @param lista tweet lista bat
	 * @param usr Erabiltzailearen twitter izena
	 * @throws SQLException
	 */
private void sartuStatusDB(List<Status> lista,String usr){
		//Hau DBK-n egon behar da.
		Iterator<Status> i= lista.iterator();
		try{
			while(i.hasNext()){
			Status t= i.next();
			if(t.getURLEntities().length==0){
				
			DBK.getInstantzia().saveTweetInfo(t.getText().replace("'", "''"), switchBooltoInt(t.isRetweet()), switchBooltoInt(t.isFavorited()), t.getRetweetCount(), t.getFavoriteCount(),null, null, t.getId(), t.getUser().getScreenName());
			}
			else
				DBK.getInstantzia().saveTweetInfo(t.getText().replace("'", "''"), switchBooltoInt(t.isRetweet()), switchBooltoInt(t.isFavorited()), t.getRetweetCount(), t.getFavoriteCount(), t.getURLEntities()[0].getDisplayURL(), null, t.getId(), t.getUser().getScreenName());
		}
		}catch (Exception e){
			throw new Salbuespenak(e.getMessage());
		} 
	}
/**
 * Honek Boolean balioak 1 eta 0an bihurtzen ditu datu baseak kudeatu dezan
 * @param b
 * @return
 */
private int switchBooltoInt(boolean b){
	int var = b? 1 : 0;
	return var;
}


/**
 * Erabiltzaileak datu basean gordetzeko metodoa
 * @param izena erabilztailearen twitter izena
 * @param jarraitua Jarraitua den ala ez
 * @param db gure datu basea 
 * @throws SQLException
 */
private void sartuJErabiltzaileaDB(String izena,boolean jarraitua, DBK db){
	try{	
	if(!jarraitua)
		db.saveFollowers(izena);
		else
			db.saveFollowing(izena);
	}catch (Exception e){
		throw new Salbuespenak(e.getMessage());
	}
	}

/**
 * Tweet faboritoak deskargatu eta datu basean gordetzeko metodoa.
 */
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
			if(since==0){since=1L;}
			if(max==0){max=Long.MAX_VALUE;}
			
			while (!amaituta) {
				//statuses= twitter.getHomeTimeline(new Paging(pagenum++, 200, since));
				//statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since));
				statuses = twitter.getFavorites(new Paging(pagenum++, 200, since));
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					this.sartuStatusDB(statuses, LoginBeharrezkoKode.getLoginCode().getTwitterInstance().verifyCredentials().getScreenName());;
			}
			pagenum = 1;
			amaituta = false;
			while (!amaituta) {
				//statuses = twitter.getRetweetsOfMe(new Paging(pagenum++, 200, since,max));
				statuses = twitter.getFavorites(new Paging(pagenum++, 200, since, max));
				if (statuses.isEmpty()) {
					amaituta = true;
				} else
					
					this.sartuStatusDB(statuses, LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName());;
			}
			DBK.getInstantzia().paramSave(since, max);
			
		}catch (Exception sq) {
			throw new Salbuespenak(sq.getMessage());

	}
	
	}
/**
 * rt guztiak deskargatzeko metodoa
 * 
 */
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
			if(since==0){since=1L;}
			if(max==0){max=Long.MAX_VALUE;}
			
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
					this.sartuStatusDB(statuses, LoginBeharrezkoKode.getLoginCode().getTwitterInstance().verifyCredentials().getScreenName());;
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
					
					this.sartuStatusDB(statuses, LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName());;
			}
			DBK.getInstantzia().paramSave(since, max);
			
		}catch (Exception sq) {
			throw new Salbuespenak(sq.getMessage());
	}
	
	}

/**
 * tweetak deskargatzeko metodo alternatiboa
 * 3 metodo erabili ordez mota parametroa emanda mota horietako tweetak deskargatzen ditu.
 * @param mota
 */
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
			if(since==0){since=1L;}
			if(max==0){max=Long.MAX_VALUE;}
			
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
					this.sartuStatusDB(statuses, LoginBeharrezkoKode.getLoginCode().getTwitterInstance().verifyCredentials().getScreenName());;
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
					
					this.sartuStatusDB(statuses, LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName());;
			}
			DBK.getInstantzia().paramSave(since, max);
			
		}catch (Exception sq) {
			throw new Salbuespenak(sq.getMessage());

	}
	
	}
}
