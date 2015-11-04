package code;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.*;
import twitter4j.Status;

public class BackBone {
	Twitter twitter;
	ArrayList<Status> Timeline;
	
	
	public BackBone(){
	}
	
	public void getTimeline() throws TwitterException{
		//First param of Paging() is the page number, second is the number per page (this is capped around 200 I think.
		Paging paging = new Paging(1, 200);
		ResponseList<Status> statuses = LoginCode.nireLoginCode.twitter.getHomeTimeline(paging);
		for(int i=0;i<statuses.size();i++){
			System.out.println(statuses.get(i).getText());
		}
	}
	
	
	
}
