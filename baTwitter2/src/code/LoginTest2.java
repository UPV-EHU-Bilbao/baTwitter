package code;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class LoginTest2 {
	String CK="VZui0P0P00DX1q9SeCxLlSDYv";
	String CS="ZvlLujot49kqG6qd0SJp3PLFRyHUIp9XtmEw6bYOlOmqpFC1F1";
	ConfigurationBuilder cb = new ConfigurationBuilder();
	RequestToken RT=null;
	TwitterFactory tf=null;
	Twitter twitter=null;
	
	
	public LoginTest2(){
		cb.setDebugEnabled(true).setOAuthConsumerKey(CK).
		setOAuthConsumerSecret(CS).
		setOAuthAccessTokenURL(null).
		setOAuthAccessTokenSecret(null);
		try {
            tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
    		RT=twitter.getOAuthRequestToken();

            }catch(Exception e){
            	e.printStackTrace();
            }
		
	}
	
	
	public void openURL (RequestToken RT) throws Exception{
        URI url=new URI(RT.getAuthorizationURL());
        Desktop.getDesktop().browse(url);
	}
	
	public void getAccessToken(String Pin, RequestToken RT2){
		AccessToken AT=null; 
		try {
             if (Pin.length() > 0) {
                 AT = twitter.getOAuthAccessToken(RT2, Pin);
             } else {
                 AT = twitter.getOAuthAccessToken(RT2);
             }
         } catch (TwitterException te) {
             if (401 == te.getStatusCode()) {
                 System.out.println("Unable to get the access token.");
             } else {
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
	
	
    
}
