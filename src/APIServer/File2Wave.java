package APIServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.rmi.server.UID;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class File2Wave extends ServerResource {
	String errorWave = "/Users/jinchen/Desktop/festival/GeneratedWave/Error.wav";
	
	@Post
	public FileRepresentation accept(Representation entity) throws Exception {
		FileRepresentation result = null;
	    if (entity != null) {
	        if (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true)) {
	            // 1/ Create a factory for disk-based file items
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            factory.setSizeThreshold(1000240);

	            // 2/ Create a new file upload handler based on the Restlet
	            // FileUpload extension that will parse Restlet requests and
	            // generates FileItems.
	            RestletFileUpload upload = new RestletFileUpload(factory);

	            // 3/ Request is parsed by the handler which generates a
	            // list of FileItems
	            FileItemIterator fileIterator = upload.getItemIterator(entity);

	            // Process only the uploaded item called "fileToUpload"
	            // and return back
	            boolean found = false;
	            while (fileIterator.hasNext() && !found) {
	                FileItemStream fi = fileIterator.next();
	                if (fi.getFieldName().equals("fileToUpload")) {
	                    found = true;
	                    // consume the stream immediately, otherwise the stream
	                    // will be closed.
	                    StringBuilder sb = new StringBuilder();
	                    BufferedReader br = new BufferedReader(
	                            new InputStreamReader(fi.openStream()));
	                    String line = null;
	                    while ((line = br.readLine()) != null) {
	                        sb.append(line);
	                    }
	                    String waveFilePath = "";
	                    String[] matches = sb.toString().split(" ");
	                    String match = matches[matches.length-1]; 
	                    waveFilePath = match.split("\"")[1];
	                    File newFile = new File("/Users/jinchen/Desktop/festival/GeneratedWave/" + GenerateUid() + ".scm");
	                    BufferedWriter out = new BufferedWriter (new FileWriter(newFile));
	                    out.write(sb.toString());
	                    out.close();
	                    //result = new StringRepresentation(sb.toString(), MediaType.TEXT_PLAIN);
	                    result = Process(newFile.getName(), waveFilePath);
	                }
	            }
	        } else {
	            // POST request with no entity.
	            setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	        }
	    }
	    return result;
	}
	
	private FileRepresentation Process(String fileName, String filePath){		
		FileRepresentation result = new FileRepresentation(errorWave,MediaType.AUDIO_WAV);
		String waveFilePath = "";
		String scmFile = fileName;		
		waveFilePath = filePath;
		String command = "/Users/jinchen/Desktop/festival/festival/bin/festival -b " + 
		"/Users/jinchen/Desktop/festival/GeneratedWave/" + scmFile;
		if(ExcuteCommand(command))
		{
			result = new FileRepresentation(waveFilePath,MediaType.AUDIO_WAV);			
		}
		return result;
	}
	
	private String GenerateUid()
	{
		return UUID.randomUUID().toString();
	}
	
	private boolean ExcuteCommand(String[] command) 
	{
		boolean result = false;
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	private boolean ExcuteCommand(String command) 
	{
		boolean result = false;
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
}
