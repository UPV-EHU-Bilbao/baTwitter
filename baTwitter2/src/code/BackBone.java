package code;

import twitter4j.Paging;

import twitter4j.TwitterException;

import java.sql.SQLException;
import java.util.*;

import dbRelated.DBK;
import twitter4j.Status;

public class BackBone {
	ArrayList<Status> Timeline;
	
	
	public BackBone(){
	}
	
	public void getTimeline() throws TwitterException, SQLException{
		
		int pageno=1;
		List<Status> statuses = new ArrayList<Status>();
		
		while (true){
			try{
				int size = statuses.size();
				Paging page = new Paging(pageno++,100);
				statuses.addAll(LoginCode.getLoginCode().twitter.getHomeTimeline(page));
				if (statuses.size()==size){
					break;
				}
			}catch (TwitterException e){
				e.printStackTrace();
			}
		}
		Iterator<Status> itr = statuses.iterator();
		while (itr.hasNext()){
			Status st1=itr.next();
			String Superuser="";
			if(st1.getUser().getId()==LoginCode.getLoginCode().getAccessToken().getUserId()){
				Superuser=st1.getUser().getName();
			}
			DBK.getDBK().saveTweetInfo(st1.getText(), st1.isRetweet(), st1.isFavorited(), st1.getRetweetCount(), st1.getFavoriteCount(), st1.getURLEntities().toString(), st1.getMediaEntities().toString(), st1.getId(), Superuser, st1.getUser().getName());
			System.out.println(st1.getText());
			
		}
		System.out.println(statuses.size());
		
		/*First param of Paging() is the page number, second is the number per page (this is capped around 200 I think.
		Paging paging = new Paging(1, 200);
		ResponseList<Status> statuses = LoginCode.nireLoginCode.twitter.getHomeTimeline(paging);
		for(int i=0;i<statuses.size();i++){
			System.out.println(statuses.get(i).getText());
		}*/
		//return statuses;
	}
	
	
	
}
