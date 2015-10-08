package Core;

import twitter4j.*;

public class Login {
	      public static void main(String[] args) throws TwitterException {
	              Twitter twitter = new Twitter("username", "password"); // login to service
	              twitter.updateStatus("Tweeting!"); // update your status
	          }
	      }
	  


