package no.nmdc.monitor.controller;

import no.nmdc.monitor.model.DataURLModel;
import no.nmdc.monitor.service.DataURLService;
import no.nmdc.monitor.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Controller
public class DatasetController {

    
    @Autowired
    private  DatasetService datasetService;
    
    @RequestMapping("/datasets")
    @ResponseBody
    public Object getDataURL() {
        return datasetService.getDatasets();
    }
  
    @RequestMapping("/openDapSets")
    @ResponseBody
    public Object getOpenDAPSets() {
        return datasetService.getOpenDAPMap();
    }
  
  
}
