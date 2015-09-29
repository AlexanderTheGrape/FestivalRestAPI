package Client;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class WaveClient {
	public void open()
	{
		try {
			Desktop.getDesktop().browse(new URI("http://localhost:8183/api/wave?txt=hello,%20Catherine,%20How%20are%20you"));
			//Desktop.getDesktop().browse(new URI("http://localhost:8183/api/wave?txt=I%20am%20doing%20a%20test"));			
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}		
	}

}
