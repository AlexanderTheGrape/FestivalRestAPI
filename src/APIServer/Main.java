package APIServer;

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
	    // Create a new Component.  
	    Component component = new Component(); 

	    // Add a new HTTP server listening on port configured, the default port is 8183.  
	    component.getServers().add(Protocol.HTTP, port);  

	    // Attach the application.  
	    component.getDefaultHost().attach("/api",  
	            new APISever());  

	    // Start the component.  
	    component.start();	
	    
	    //Delete auto generated .txt and .wav files every 1 hour
	    Timer time = new Timer(); 
	    time.schedule(new DeleteFile(1, wavePath), 0, 1000 * 60 * 60 * 1);
	    
	    //test Client
   
		//WaveClient waveClient = new WaveClient();
		//waveClient.open();	*/
	   
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
