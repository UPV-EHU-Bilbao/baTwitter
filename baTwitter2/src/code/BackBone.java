package code;

import twitter4j.Paging;

import twitter4j.TwitterException;

import java.util.*;
import twitter4j.Status;

public class BackBone {
	ArrayList<Status> Timeline;
	
	
	public BackBone(){
	}
	
	public List<Status> getTimeline() throws TwitterException{
		
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
			System.out.println(itr.next().getText());
			
		}
		System.out.println(statuses.size());
		
		/*First param of Paging() is the page number, second is the number per page (this is capped around 200 I think.
		Paging paging = new Paging(1, 200);
		ResponseList<Status> statuses = LoginCode.nireLoginCode.twitter.getHomeTimeline(paging);
		for(int i=0;i<statuses.size();i++){
			System.out.println(statuses.get(i).getText());
		}*/
		return statuses;
	}
	
	
	
}
