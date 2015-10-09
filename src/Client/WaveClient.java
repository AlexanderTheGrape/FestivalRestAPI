/*This is for testing client. But usually clients are different programs at different locations.
 * This is just an example about how to consume the service*/
package Client;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WaveClient {
	public void open()
	{
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8183/api/wave?txt=hello,%20Catherine,%20How%20are%20you"));		
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}		
	}

}
