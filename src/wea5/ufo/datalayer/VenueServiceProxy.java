package wea5.ufo.datalayer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import wea5.ufo.contracts.Venue;

@ManagedBean(name="venueServiceProxy")
@ApplicationScoped
public class VenueServiceProxy extends ServiceProxy<Venue> {
	@Override
	protected String getControllerName(){
		return "venue";
	}
}
