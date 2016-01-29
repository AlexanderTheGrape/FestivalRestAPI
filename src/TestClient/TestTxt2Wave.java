package TestClient;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import APIServer.Main;


public class TestTxt2Wave {
	public void testClient() {
		try {
			Desktop.getDesktop().browse(new URI("http://robotspeech.auckland.ac.nz:8183/api/txt2wave?txt=hello,I%27m%20a%20health%20care%20robot&token=TestToken"));		
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}		
	}
}
