package APIServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

import org.restlet.Component;
import org.restlet.data.Protocol;

import TestClient.TestTxt2Wave;

public class Main {
	public static Integer port = 8183;
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
	    
	    //Delete auto generated .txt and .wav files every 1 hour
	    Timer time = new Timer(); 
	    time.schedule(new DeleteFile(1, wavePath), 0, 1000 * 60 * 60 * 1);	   
	   
	    //test client
	    TestTxt2Wave test = new TestTxt2Wave();
	    //test.testClient();
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
