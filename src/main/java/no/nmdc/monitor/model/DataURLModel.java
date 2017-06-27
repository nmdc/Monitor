
package no.nmdc.monitor.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thannant
 */
public class DataURLModel {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DataURLModel.class);
    
    public void initialise() {
        
      try {
        
     	URL obj = new URL("http://prod1.nmdc.no/UserInterface/metadata-api/search?q=&offset=0");
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
	
	BufferedReader in = new BufferedReader(
					       new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	
	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.toString());
        JsonObject res = element.getAsJsonObject();
	JsonArray results = res.getAsJsonArray("results");
	int totalCount = res.getAsJsonPrimitive("numFound").getAsInt();
	System.out.println("Total count "+totalCount);
	for (JsonElement rec:results) {
	    JsonObject record = rec.getAsJsonObject();
	    String data_url_list=record.getAsJsonPrimitive("Data_URL").getAsString();
	    for (String data_url_encoded:data_url_list.substring(1,data_url_list.length()-1).split(",")) {
		String data_url = java.net.URLDecoder.decode(data_url_encoded, "UTF-8");
		System.out.println("url "+data_url);
                LOG.debug("url"+data_url);
	    }
	    
	}  
        
        
     String testURL = "ftp://ftp.nmdc.no/nmdc/UIB/Currents/moorings/FSC197308_C/0166_RCM_65.txt";     

     String hostname ="ftp.nmdc.no";
     String filePath ="/nmdc/UIB/Currents/moorings/FSC197308_C/0166_RCM_65.txt";     

     //String filePath ="0166_RCM_65.pdf";
     //String dirPath ="/nmdc/UIB/Currents/moorings/FSC197308_C/";
     

     

     FTPClient ftpClient = new FTPClient();
     //ftpClient.connect(hostname, port);
     ftpClient.connect(hostname);
     ftpClient.login("anonymous","nmdc");

     /*      FTPFile[] lt = ftpClient.listDirectories();
      for (FTPFile er:lt) {
	  System.out.println(er);
	  }*/
     
     int returnCode = ftpClient.getReplyCode();
     System.out.println("Reply code"+returnCode);


     

     //ftpClient.changeWorkingDirectory("nmdc");
     returnCode = ftpClient.getReplyCode();
     System.out.println("Reply code"+returnCode);
	 
     InputStream inputStream = ftpClient.retrieveFileStream(filePath);
     returnCode = ftpClient.getReplyCode();
     System.out.println("Got stream ");
     if (inputStream == null) {
	 System.out.println("Input stream is null");
     }
      LOG.debug("Reply code"+returnCode);
     LOG.debug("Reply is "+FTPReply.isPositiveCompletion(returnCode));
     LOG.debug("Reply is "+FTPReply.isPositivePreliminary(returnCode));
        
        
        
      }
      catch (Exception e){
          e.printStackTrace();
      }
    }
    
}
