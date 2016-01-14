package wea5.ufo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ufoContext")
@SessionScoped
public class UFOContext {
	public String allPerformances() {
		return Commands.BROWSE_PERFORMANCES;
	}

	public String allArtists() {
		return Commands.BROWSE_ARTISTS;
	}

	public String artistDetails(String artistId) {
		return Commands.ARTIST_DETAIL;
	}

	public String allVenues() {
		return Commands.BROWSE_VENUES;
	}

	public String venueDetails() {
		return Commands.VENUE_DETAIL;
	}
}
