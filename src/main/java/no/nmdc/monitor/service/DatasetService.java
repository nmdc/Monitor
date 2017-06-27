package no.nmdc.monitor.service;

import no.nmdc.monitor.dao.DatasetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Service
public class DatasetService {
    
    @Autowired
    DatasetDAO datasetDao;

}
