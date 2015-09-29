package APIServer;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import WavFile.WavFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Since I can't run festival locally, just test it by ping for now:
 * http://localhost:8183/firstSteps/festivalAudio?txt=google.com
 */
public class TestPing extends ServerResource{
	@Get
    public String test() {
		//WavFile result = null;
		String result = "";
		String txt = null;
		Request request =getRequest();
		Form form = request.getResourceRef().getQueryAsForm();
		txt = form.getValues("txt");
		result= Process(txt);
		return result;
    }
	
	@Post
    public String represent() {
		//WavFile result = null;
		String result = "";
		String txt = null;
		Request request =getRequest();
		txt = request.getEntityAsText();
		result= Process(txt);
		return result;
    }
	
	private String Process(String txt){
		//WavFile result = null;
		String result = "";
		String command = "ping -c 3 " + txt;
		result = executeCommand(command);
		return result;
	}
	
	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return output.toString();

	}

}

