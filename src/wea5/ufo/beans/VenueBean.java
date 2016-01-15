package wea5.ufo.beans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.VenueServiceProxy;
import wea5.ufo.util.FacesUtil;

@ManagedBean(name="venueBean")
@ViewScoped
public class VenueBean extends AbstractDataBean<Venue> implements Serializable {
	private static final long serialVersionUID = 4279159904165335946L;
	private static final Logger logger = Logger.getLogger("VenueBean");
	
	@ManagedProperty("#{venueServiceProxy}")
	private VenueServiceProxy venueServiceProxy;
	
	public void setVenueServiceProxy(VenueServiceProxy venueServiceProxy) {
		this.venueServiceProxy = venueServiceProxy;
	}
    @PostConstruct
    public void init() {
    	this.data = venueServiceProxy.getAll(new GenericType<List<Venue>>() {});
    	if(data == null)
    		FacesUtil.showFatalErrorMessage("No data is loaded. Please refresh the page.");
    }
	
    
	public void setEntity(Object test){
		logger.info("settinge entity to " + test.toString());
		//detailedData = venueServiceProxy.getById(Integer.toString(entity.getId()), new GenericType<Venue>() {});
	}
	

}
