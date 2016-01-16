package wea5.ufo.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.VenueServiceProxy;
import wea5.ufo.util.FacesUtil;

@ManagedBean(name = "venueBean")
@SessionScoped
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
		this.data = venueServiceProxy.getAll(new GenericType<List<Venue>>() {
		});
		if (data == null)
			FacesUtil.showFatalErrorMessage("No data is loaded. Please refresh the page.");
	}

	public void setEntity(Venue entity) {
		detailedData = venueServiceProxy.getById(Integer.toString(entity.getId()), new GenericType<Venue>() {
		});
	}

	public void setEntityWithId() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idString = params.get("venueId");
		detailedData = venueServiceProxy.getById(idString, new GenericType<Venue>() {
		});
	}

	public void showDetails() {
		setEntityWithId();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		options.put("contentWidth", 1200);
		RequestContext.getCurrentInstance().openDialog("venueDetails", options, null);
	}
}
