package no.nmdc.monitor.controller;

import no.nmdc.monitor.model.DataURLModel;
import no.nmdc.monitor.service.DataURLService;
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
public class DataURLController {

    
    @Autowired
    private  DataURLService dataURLService;
    
    @RequestMapping("/dataURL")
    @ResponseBody
    public Object getDataURL() {
        DataURLModel model = new DataURLModel();
        model.initialise();
        
        return null;
    }
    
  
}
