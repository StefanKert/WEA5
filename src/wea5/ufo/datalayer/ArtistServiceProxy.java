package wea5.ufo.datalayer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import wea5.ufo.contracts.Artist;

@ManagedBean(name="artistServiceProxy")
@ApplicationScoped
public class ArtistServiceProxy extends ServiceProxy<Artist> {
	@Override
	protected String getControllerName(){
		return "artist";
	}
}
