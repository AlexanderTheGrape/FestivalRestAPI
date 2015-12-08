package APIServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main {
	static Integer port = 8183;
	static String wavePath = "";
	static String festivalHome = "";
	static String token = "";
	
	public static void main(String[] args) throws Exception {  		
		getProperties(args[0]);
		
		// Initiate temporary folder for keeping auto generated files
		File folder = new File(wavePath);
		try {
			   if (!(folder.isDirectory())) {
			    new File(wavePath).mkdir();
			   }
			  } catch (SecurityException e) {
			   e.printStackTrace();
			  }
		
	    // Create a new Component.  
	    Component component = new Component(); 

	    // Add a new HTTP server listening on port configured, the default port is 8183.  
	    component.getServers().add(Protocol.HTTP, port);  

	    // Attach the application.  
	    component.getDefaultHost().attach("/api",  
	            new APIServer());  

	    // Start the component.  
	    component.start();	
	    
	    //Delete auto generated .txt and .wav files every 10 minutes
	    Timer time = new Timer(); 
	    time.schedule(new DeleteFile(1, wavePath), 0, 1000 * 60 * 10);	   
	   
	} 
	
	private static void getProperties(String configFilePath){
		Properties configFile = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(configFilePath);
			configFile.load(file);
			file.close();
			port = Integer.parseInt(configFile.getProperty("port"));
			wavePath = configFile.getProperty("waveFilePath");
			festivalHome = configFile.getProperty("festivalHome");
			token = configFile.getProperty("APIToken");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
