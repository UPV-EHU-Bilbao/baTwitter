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
	AccessToken AT=null;
	TwitterFactory tf=new TwitterFactory(cb.build());
	Twitter twitter=tf.getInstance();
	static LoginTest2 nireLoginTest2=new LoginTest2();
	
	
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
	public static synchronized LoginTest2 getLogintest2(){
		if (nireLoginTest2==null){
			nireLoginTest2=new LoginTest2();
		}
		return nireLoginTest2;
	}
	public RequestToken getRT(){
		return RT;
	}
	
	
	public void openURL (RequestToken RT) throws Exception{
        URI url=new URI(RT.getAuthorizationURL());
        Desktop.getDesktop().browse(url);
	}
	
	public void getAccessToken(String Pin){
		System.out.println(Pin);
		try {
            if (Pin.length() > 0) {
                AT = twitter.getOAuthAccessToken(RT, Pin);
            } else {
                AT = twitter.getOAuthAccessToken(RT);
            }
        } catch (TwitterException te) {
            if (401 == te.getStatusCode()) {
                System.out.println("Unable to get the access token.");
            } else {
                te.printStackTrace();
            }
        } 
		
    System.out.println("Got access token.");
    System.out.println("Access token: " + AT.getToken());
    System.out.println("Access token secret: " + AT.getTokenSecret());
	}	
	
	
	public void showStatus(){
		if (!twitter.getAuthorization().isEnabled()) {
            System.out.println("OAuth consumer key/secret is not set.");
            System.exit(-1);
        }
	}
	
	
    
}
