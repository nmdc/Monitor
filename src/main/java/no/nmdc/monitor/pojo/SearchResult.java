package no.nmdc.monitor.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

/**
 *
 * @author Terry Hannant <a5119>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    
    int matches;
    int numFound;
    ArrayList<RemoteDataset> results;
    
 
    public int getMatches() {
        return matches;
    }


    public int getNumFound() {
        return numFound;
    }
  

    public ArrayList<RemoteDataset> getResults() {
        return results;
    }
    
    

}



 