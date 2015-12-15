package code;

public class HariKudeatzailea {
   @SuppressWarnings("unused")
private static DeskargaKudeatzailea z=new DeskargaKudeatzailea();

	/* public static void main(String args[]){


	    

	      Thread tweet=new Thread(){
	    	  public void run() {
	    	        try {
	    	           z.nireTweet(LoginBeharrezkoKode.getLoginCode().twitter.getScreenName(), DBK.getInstantzia());
	    	        } catch(InterruptedException | IllegalStateException | SQLException | TwitterException v) {
	    	            System.out.println(v);
	    	        }
	    	    }  
	    	};
	      Thread faboritoak=new Thread(){
		    	  public void run() {
		    	        try {
		    	        z.faboritoak(LoginBeharrezkoKode.getLoginCode().twitter.getScreenName(), DBK.getInstantzia());	     
		    	        } catch(InterruptedException | IllegalStateException | SQLException | TwitterException v) {
		    	            System.out.println(v);
		    	        }
		    	    }  
		    	};
		Thread jarraitzaileak=new Thread(){
			public void run() {
    	        try {
    	        z.jarraitzaileak(DBK.getInstantzia());	     
    	        } catch(SQLException | InterruptedException v) {
    	            System.out.println(v);
    	        }
    	    }  
    	};
    	Thread jarraituak=new Thread(){
			public void run() {
    	        try {
    	        z.jarraituak(DBK.getInstantzia());	     
    	        } catch(SQLException | InterruptedException v) {
    	            System.out.println(v);
    	        }
    	    }  
    	};
		
    	tweet.start();
    	faboritoak.start();
    	jarraitzaileak.start();
    	jarraituak.start();
			
		
    	
	 }

	*/
}
