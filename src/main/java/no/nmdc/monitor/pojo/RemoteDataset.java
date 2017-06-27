package no.nmdc.monitor.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Terry Hannant <a5119>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteDataset {
    
   @JsonProperty("Provider")
    String provider;
   
   @JsonProperty("Data_URL_Type")
    String Data_URL_Type;
       
   @JsonProperty("Data_URL")
    String Data_URL;
    


    public String getProvider() {
        return provider;
    }
    public String getData_URL_Type() {
        return Data_URL_Type;
    }

    public String getData_URL() {
        return Data_URL;
    }

    

}
