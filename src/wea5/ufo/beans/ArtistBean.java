package wea5.ufo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import wea5.ufo.contracts.Artist;
import wea5.ufo.datalayer.ServiceProxy;

@ManagedBean(name="artistBean")
@ViewScoped
public class ArtistBean extends AbstractDataBean<Artist> implements Serializable{
	private static final long serialVersionUID = 8719086752016341311L;
	
	@ManagedProperty("#{artistServiceProxy}")
	private ServiceProxy<Artist>  serviceProxy;
	
	@Override
	protected ServiceProxy<Artist> getServiceProxy() {
		return serviceProxy;
	}
	
	@Override
	public void setServiceProxy(ServiceProxy<Artist> serviceProxy) {
		this.serviceProxy = serviceProxy;
	}
	
    @PostConstruct
    public void init() {
    	initList(Artist.class);
    }
    
    @Override
    protected boolean filterEntity(Artist entity, String filterValue){
    	return entity.getName().toUpperCase().contains(filterValue.toUpperCase()) 
    			|| entity.getCategory().getName().toUpperCase().contains(filterValue.toUpperCase()) 
    			|| entity.getCountry().toUpperCase().contains(filterValue.toUpperCase());
    }
}