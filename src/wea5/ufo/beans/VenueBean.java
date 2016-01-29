package wea5.ufo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.ServiceProxy;

@ManagedBean(name = "venueBean")
@ViewScoped
public class VenueBean extends AbstractDataBean<Venue> implements Serializable {
	private static final long serialVersionUID = 4279159904165335946L;

	@ManagedProperty("#{venueServiceProxy}")
	protected ServiceProxy<Venue> serviceProxy;

	@Override
	protected ServiceProxy<Venue> getServiceProxy() {
		return serviceProxy;
	}

	@Override
	public void setServiceProxy(ServiceProxy<Venue> serviceProxy) {
		this.serviceProxy = serviceProxy;
	}

	@PostConstruct
	public void init() {
		initList(Venue.class);
	}

	public void setEntityWithId() {
		initEntityWithId("venueId", new GenericType<Venue>() {});
	}

	@Override
	protected boolean filterEntity(Venue entity, String filterValue) {
		return entity.getTitle().toUpperCase().contains(filterValue.toUpperCase());
	}
}