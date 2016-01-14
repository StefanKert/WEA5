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

@ManagedBean(name="artistBean")
@SessionScoped
public class ArtistBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("ArtistsDataTable");
	private String artistId;
	private Artist detailedArtist;
	private List<Artist> artists;
	
	@ManagedProperty("#{artistServiceProxy}")
	private ArtistServiceProxy artistServiceProxy;
	
	public void setArtistServiceProxy(ArtistServiceProxy artistServiceProxy) {
		this.artistServiceProxy = artistServiceProxy;
	}
	
    @PostConstruct
    public void init() {
    	this.artists = artistServiceProxy.getAll(new GenericType<List<Artist>>() {});
    }

	
	public List<Artist> getArtists() {
		return artists;
	}

	public Artist getSelectedArtist() { 
		return artistServiceProxy.getById(artistId, new GenericType<Artist>() {});
	}
	
	public void setDetailedArtist(String artistId){
		logger.info(artistId);
		this.artistId = artistId;
	}
}