package wea5.ufo.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.VenueServiceProxy;
import wea5.ufo.util.FacesUtil;

@ManagedBean(name="venue")
@SessionScoped
public class VenueBean {
	private MapModel mapModel;
	
	@ManagedProperty("#{venueServiceProxy}")
	private VenueServiceProxy venueServiceProxy;
	
	public void setVenueServiceProxy(VenueServiceProxy venueServiceProxy) {
		this.venueServiceProxy = venueServiceProxy;
	}
	
	public List<Venue> getVenues() {
		return this.venueServiceProxy.getAll(new GenericType<List<Venue>>(){});
	}

	public Venue getSelectedVenue() {
		String id = FacesUtil.getRequestParameterValue("venueId");
		Venue selectedVenue = this.venueServiceProxy.getById(id, new GenericType<Venue>() {});
		LatLng coord = new LatLng(selectedVenue.latitude, selectedVenue.longitude);
		getMapModel().getMarkers().clear();
		getMapModel().addOverlay(new Marker(coord, selectedVenue.title));
		return selectedVenue;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}
}
