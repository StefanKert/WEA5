package wea5.ufo.contracts;

import java.util.List;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.owlike.genson.GenericType;

public class Venue implements Entity<Venue>  {
	private int id;
	private String title;
	private String description;
	private double longitude;
	private double latitude;
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
	
	public void setMapModel(MapModel mapModel){
		this.mapModel = mapModel;
	}
	
	public MapModel getMapModel() {
		LatLng coord = new LatLng(this.latitude, this.longitude);
		mapModel.getMarkers().clear();
		mapModel.addOverlay(new Marker(coord, this.title));
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
	
	@Override
	public GenericType<Venue> getGenericInstance() {
		return new GenericType<Venue>(){};
	}

	@Override
	public GenericType<List<Venue>> getGenericListInstance() {
		return new GenericType<List<Venue>>(){};
	}
}
