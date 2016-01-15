package wea5.ufo.datalayer;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import wea5.ufo.contracts.Artist;

@ManagedBean(name="artistServiceProxy")
@ApplicationScoped
public class ArtistServiceProxy extends ServiceProxy<Artist> implements Serializable {
	private static final long serialVersionUID = -2860742347410717202L;

	@Override
	protected String getControllerName(){
		return "artist";
	}
}
