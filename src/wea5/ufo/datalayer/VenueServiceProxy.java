package wea5.ufo.datalayer;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import wea5.ufo.contracts.Venue;

@ManagedBean(name="venueServiceProxy")
@ApplicationScoped
public class VenueServiceProxy extends ServiceProxy<Venue> implements Serializable {
	private static final long serialVersionUID = -4902915089496644066L;

	@Override
	protected String getControllerName(){
		return "venue";
	}
}
