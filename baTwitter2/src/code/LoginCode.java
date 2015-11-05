package code;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class LoginCode {
	private String CK="VZui0P0P00DX1q9SeCxLlSDYv";
	private String CS="ZvlLujot49kqG6qd0SJp3PLFRyHUIp9XtmEw6bYOlOmqpFC1F1";
	AccessToken AT;
	RequestToken RT;
	Twitter twitter;
	static LoginCode nireLoginCode=new LoginCode();
	
	
	public LoginCode(){
		AT=null;
		ConfigurationBuilder cb= new ConfigurationBuilder();
		cb.setOAuthConsumerKey(CK);
		cb.setOAuthConsumerSecret(CS);
		Configuration conf=cb.build();
		TwitterFactory tf=new TwitterFactory(conf);
		twitter=tf.getInstance();
		RT=null;		
	}
	public static synchronized LoginCode getLoginCode(){
		if (nireLoginCode==null){
			nireLoginCode=new LoginCode();
		}
		return nireLoginCode;
	}
	public RequestToken getRT(){
		return RT;
	}
	
	
	
	public void Login () throws Exception{
		twitter.setOAuthAccessToken(null);
		RT=twitter.getOAuthRequestToken();
		AT=null;
        URI url=new URI(RT.getAuthorizationURL());
        Desktop.getDesktop().browse(url);
        
	}
	public void LoginWithCredentials(String AT, String ATS){
		AccessToken AccessToken2 = new AccessToken(AT,ATS);
		twitter.setOAuthAccessToken(AccessToken2);
	}
	
	public void getAccessToken(String Pin) throws FileNotFoundException, IOException{
		System.out.println(getRT());
		System.out.println(Pin);		
		try {
			
            if (Pin.length() > 0) {
            	//AT = twitter.getOAuthAccessToken(RT);
                AT = twitter.getOAuthAccessToken(getRT(), Pin);
                System.out.println("Got access token.");
                System.out.println("Access token: " + AT.getToken());
                System.out.println("Access token secret: " + AT.getTokenSecret());
            } else {
                AT = twitter.getOAuthAccessToken(getRT());
            }
            twitter.setOAuthAccessToken(AT);
            
			
        } catch (TwitterException te) {
       System.out.println("Unable to get the access token.");
            if (401 == te.getStatusCode()) {
               
            } else {
            	System.out.println(te.getStatusCode());
                te.printStackTrace();
            }
        } 
		
    
	}	
	
	
	public void showStatus(){
		if (!twitter.getAuthorization().isEnabled()) {
            System.out.println("OAuth consumer key/secret is not set.");
            System.exit(-1);
        }
	}
	
	public Twitter getTwitterInstance(){
		return this.twitter;
	}
	
	
    
}
