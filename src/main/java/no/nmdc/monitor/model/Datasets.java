package no.nmdc.monitor.model;

import java.util.ArrayList;
import no.nmdc.monitor.pojo.RemoteDataset;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class Datasets {
    
    ArrayList<RemoteDataset> remoteDatasets;
    
    public Datasets(){
        remoteDatasets = new ArrayList<RemoteDataset>();
    }
    
    public void  addRemoteDatasets(ArrayList<RemoteDataset> datasets) {
        remoteDatasets.addAll(datasets);
    }

    public ArrayList<RemoteDataset> getRemoteDatasets() {
        return remoteDatasets;
    }
    
    
    
    
    

}
