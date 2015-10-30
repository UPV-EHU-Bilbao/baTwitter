package propertyManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class TextPropertyWriter {
	static FileOutputStream fos =null;

    public static void main(String args[]) throws FileNotFoundException, IOException {
		Properties props = new Properties();
    	if(checkExists("C:/"+System.getProperty("user.name")+".properties"))
        //Creating properties files from Java program
        	fos = new FileOutputStream("c:/user.properties");
      
        	props.setProperty("RequestToken", "value1");
        	props.setProperty("RequestTokenSecret", "value2");
        	props.setProperty("AccessToken","arg1");
        	props.setProperty("AccessTokenSecret", "arg2");
      
        	//writing properties into properties file from Java
        	props.store(fos, "Properties generated for Twitter Access Code Storage");
      
        	fos.close();
      
    }
    
    public static boolean checkExists(String file) {
    	String user= System.getProperty("user.name");
        File dir = new File("C:\\Users\\"+ user);
        File[] dir_contents = dir.listFiles();
        String temp = file + ".properties";
        //boolean check = new File(dir, temp).exists();
        boolean res=false;

        for(int i = 0; i<dir_contents.length;i++) {
            if(dir_contents[i].getName() == (file + ".properties"))
                res=true;
        }

        return res;
    }
}


