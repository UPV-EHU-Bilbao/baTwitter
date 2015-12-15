package code;

import java.awt.Desktop;
import java.net.URI;
import java.sql.SQLException;

import dbRelated.DBK;
import exception.Salbuespenak;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitterren logeatzeko klasea
 * tokenak deskargatu eta datu basean gorde, berriro ez deskargatzeko beharra izateko.
 * @author BATwitter
 *
 */
public class LoginBeharrezkoKode {
	private String CK="VZui0P0P00DX1q9SeCxLlSDYv";
	private String CS="ZvlLujot49kqG6qd0SJp3PLFRyHUIp9XtmEw6bYOlOmqpFC1F1";
	private AccessToken AT;
	private RequestToken RT;
	private Twitter twitter;
	private static LoginBeharrezkoKode nireLoginCode=new LoginBeharrezkoKode();
	
	
	
	public LoginBeharrezkoKode(){
		AT=null;
		ConfigurationBuilder cb= new ConfigurationBuilder();
		cb.setOAuthConsumerKey(CK);
		cb.setOAuthConsumerSecret(CS);
		Configuration conf=cb.build();
		TwitterFactory tf=new TwitterFactory(conf);
		twitter=tf.getInstance();
		RT=null;		
	}
	public static synchronized LoginBeharrezkoKode getLoginCode(){
		if (nireLoginCode==null){
			nireLoginCode=new LoginBeharrezkoKode();
		}
		return nireLoginCode;
	}
	public RequestToken getRT(){
		return RT;
	}
	
	
	
	public void Login (){
		try{
		twitter.setOAuthAccessToken(null);
		RT=twitter.getOAuthRequestToken();
		AT=null;
        URI url=new URI(RT.getAuthorizationURL());
        Desktop.getDesktop().browse(url);
		} catch(Exception e){
			throw new Salbuespenak("Ezin da internetera konektatu");

		}
        
	}
	public void LoginWithCredentials() {
		String[] token;
		try {
		token = DBK.getInstantzia().getATokens();
		AT=new AccessToken(token[0], token[1]);
		twitter.setOAuthAccessToken(AT);
	} catch (Exception e) {
		throw new Salbuespenak("Ezin dira Kredentzialak verifikatu");

		}
		}
	
	public void getAccessToken(String Pin) {
			
		try {
			
            if (Pin.length() > 0) {
            	//AT = twitter.getOAuthAccessToken(RT);
                AT = twitter.getOAuthAccessToken(getRT(), Pin);
            } else {
                AT = twitter.getOAuthAccessToken(getRT());
            }
            twitter.setOAuthAccessToken(AT);
            
			
        } catch (Exception e) {
		throw new Salbuespenak("Ezin dira kredentzialak lortu");

        } 
		
    
	}	
	
	
	public void showStatus(){
		if (!twitter.getAuthorization().isEnabled()) {
			throw new Salbuespenak("Autorizazioa ez da ziurtatu");
        }
	}
	public void SaveToken(){
        DBK.getInstantzia().saveToken(AT.getScreenName(),AT.getToken(), AT.getTokenSecret());
	}
	
	public Twitter getTwitterInstance(){
		return this.twitter;
	}
	public AccessToken getAccessToken(){
		return this.AT;
	}
	public boolean isTokenSet(){
		try {
			return DBK.getInstantzia().isAnyToken();
		} catch (SQLException e) {
			throw new Salbuespenak("Tokenak hutsik daude, mesedez logeatu berriro");

		}
	}
	
	
    
}


