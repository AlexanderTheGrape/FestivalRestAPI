package Client;

import java.io.IOException;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class PingClient {
	public void print(){
		ClientResource resource = new ClientResource("http://localhost:8183/api/testPing?txt=google.com");
		try {
			resource.get().write(System.out);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
