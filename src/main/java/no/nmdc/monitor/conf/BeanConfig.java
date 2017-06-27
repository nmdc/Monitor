package no.nmdc.monitor.conf;

import no.nmdc.monitor.dao.DatasetDAO;
import no.nmdc.monitor.model.Datasets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Configuration
public class BeanConfig {

     
    
       @Bean
       public DatasetDAO datasetDAO() {
           return new DatasetDAO();
       }
       
}
