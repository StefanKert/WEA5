package wea5.ufo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Artist;
import wea5.ufo.datalayer.ArtistServiceProxy;
import wea5.ufo.util.FacesUtil;

@ManagedBean(name="artistBean")
@SessionScoped
public class ArtistBean extends AbstractDataBean<Artist> implements Serializable{
	private static final long serialVersionUID = 8719086752016341311L;
	private static final Logger logger = Logger.getLogger("ArtistBean");
	
	@ManagedProperty("#{artistServiceProxy}")
	private ArtistServiceProxy artistServiceProxy;
	
	public void setArtistServiceProxy(ArtistServiceProxy artistServiceProxy) {
		this.artistServiceProxy = artistServiceProxy;
	}
	
    @PostConstruct
    public void init() {
    	this.data = artistServiceProxy.getAll(new GenericType<List<Artist>>() {});
    	if(data == null){
    		FacesUtil.showFatalErrorMessage("No data is loaded. Please refresh the page.");    	
    	}
    }
	
	public void setEntity(Artist entity){
		detailedData = artistServiceProxy.getById(Integer.toString(entity.getId()), new GenericType<Artist>() {});	
	}
}