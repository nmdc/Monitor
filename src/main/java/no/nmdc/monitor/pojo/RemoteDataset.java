package no.nmdc.monitor.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Terry Hannant <a5119>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteDataset {
    
    private static final Logger LOG = LoggerFactory.getLogger(RemoteDataset.class);
    
   @JsonProperty("Provider")
    String provider;
   
   @JsonProperty("Data_URL_Type")
    String Data_URL_Type;
       
   //@JsonProperty("Data_URL")
    String Data_URL;
 
  @JsonProperty("landingpage")
    String landingPage;

    ArrayList<String> urlList;
  

    public String getProvider() {
        return provider;
    }
    public String getData_URL_Type() {
        return Data_URL_Type;
    }

    public ArrayList<String> getUrlList() {
        return urlList;
    }

   @JsonSetter("Data_URL")
   public void setData_URL(String urlListString) {
      this.Data_URL=urlListString;
      urlList = new ArrayList<String>();
          if (urlListString != null) {
            urlListString = urlListString.substring(1, urlListString.length()-1); //Chop enclosing square brackets
            for (String url:urlListString.split(",")) {
                try {
                    urlList.add(URLDecoder.decode(url,"UTF-8"));
                    LOG.debug("----------------------"+urlList.size());
                } catch (UnsupportedEncodingException ex) {
                    LOG.error("Error in url decode", ex);
                }
            }      


          }
    }

    
    public String getData_URL() {
        return Data_URL;
    }

    public String getLandingPage() {
        return landingPage;
    }

    

}
