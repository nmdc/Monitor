package no.nmdc.monitor.dao;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import jdk.nashorn.internal.runtime.URIUtils;
import no.nmdc.monitor.model.Datasets;
import no.nmdc.monitor.pojo.RemoteDataset;
import no.nmdc.monitor.pojo.SearchResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class DatasetDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DatasetDAO.class);

    private String baseURL = "http://prod1.nmdc.no/UserInterface/metadata-api/search";

    Datasets datasets;

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private ObjectMapper mapper = new ObjectMapper();

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        refresh();

    }

    public void refresh() {
        LOG.debug("Start refresh");

        refreshRemoteDatasets();
      //  refreshOpenDAPList();
        

        LOG.debug("End refresh with " + datasets.getRemoteDatasets().size() + " datasets ");
    }

    
    public List getDatasets(){
        return datasets.getRemoteDatasets();
    }
    
    private void refreshRemoteDatasets() {

        Datasets newDatasets = new Datasets();

        SearchResult r = getRESTChunkAt(0);
        newDatasets.addRemoteDatasets(r.getResults());

        int totalCount = r.getNumFound();
        int recordCount = r.getResults().size();
        while (recordCount < totalCount) {

            LOG.debug("*********************************" + recordCount + "/" + totalCount);
            r = getRESTChunkAt(recordCount);
            newDatasets.addRemoteDatasets(r.getResults());

            recordCount = recordCount + r.getResults().size();
        }
        datasets = newDatasets;
    }

    private SearchResult getRESTChunkAt(int start) {
      // mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);

        SearchResult result = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(baseURL);

            uriBuilder.addParameter("q", "");
            uriBuilder.addParameter("offset", String.valueOf(start));

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.OK.value() == statusCode) {
                result = mapper.readValue(response.getEntity().getContent(), SearchResult.class);
            }
        } catch (URISyntaxException ex) {
            LOG.error("Invalid URI building ", ex);
        } catch (IllegalStateException ex) {
            LOG.error("Invalid JSON parsing ", ex);
        } catch (IOException ex) {
            LOG.error("Invalid IO  ", ex);
        }

        return result;
    }

    private void refreshOpenDAPList() {
        HashMap newOpenDAPSet = new HashMap();
        
        
        for (RemoteDataset dataset:datasets.getRemoteDatasets()) {
          String  urlList =  dataset.getData_URL();
          if (urlList != null) {
            urlList = urlList.substring(1, urlList.length()-1); //Chop enclosing square brackets
            for (String url:urlList.split(",")) {
                try {
                    
                    
                  //  newOpenDAPSet.put(dataset.getLandingPage(), url)
                    LOG.debug(URLDecoder.decode(url,"UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    LOG.error("", ex);
                }
            }

          }
            
        }
        
        
    }

}
