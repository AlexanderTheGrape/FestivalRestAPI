package APIServer;

import java.util.Timer;

import org.restlet.Component;
import org.restlet.data.Protocol;
import Client.WaveClient;

public class Main {
	public static void main(String[] args) throws Exception {  
	    // Create a new Component.  
	    Component component = new Component();  

	    // Add a new HTTP server listening on port 8183.  
	    component.getServers().add(Protocol.HTTP, 8183);  

	    // Attach the sample application.  
	    component.getDefaultHost().attach("/api",  
	            new APISever());  

	    // Start the component.  
	    component.start();	
	    
	    //Delete auto generated .txt and .wav files every 1 hour
	    Timer time = new Timer(); 
	    time.schedule(new DeleteFile(1, "/Users/jinchen/Desktop/festival/GeneratedWave"), 0, 1000 * 60 * 60 * 1);
	    
	    //test Client
		/*PingClient test = new PingClient();
		test.print();
   
		WaveClient waveClient = new WaveClient();
		waveClient.open();	*/
	   
	} 

}
