package wea5.ufo.contracts;

import org.jboss.logging.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

public class Venue {
    public int id;
    public String title;
    public String description;
    public double longitude;
    public double latitude;
	private MapModel mapModel;
	
    public Venue() {  
    	this.mapModel = new DefaultMapModel();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	
	public MapModel getMapModel() {
		Logger.getLogger("s").info("loading mapmodel");
		LatLng coord = new LatLng(this.latitude, this.longitude);
		mapModel.getMarkers().clear();
		mapModel.addOverlay(new Marker(coord, this.title));
		Logger.getLogger("s").info("added things to mapmodel mapmodel");
		return mapModel;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}  
}
