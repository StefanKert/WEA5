package wea5.ufo.contracts;

import org.joda.time.LocalDateTime;

public class Performance {
	public int id;
    public String title;
    public String description;
    public LocalDateTime time;
    public int venueID;
    public int artistID;
    public Artist Artist;
    public Venue Venue;
    
    public Performance() {    	
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

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getTimeString() {
		return time.toString("dd.MM.yyyy HH:mm");
	}
	
	public int getVenueID() {
		return venueID;
	}

	public void setVenueID(int venueID) {
		this.venueID = venueID;
	}

	public int getArtistID() {
		return artistID;
	}

	public void setArtistID(int artistID) {
		this.artistID = artistID;
	}

	public Artist getArtist() {
		return Artist;
	}

	public void setArtist(Artist artist) {
		Artist = artist;
	}

	public Venue getVenue() {
		return Venue;
	}

	public void setVenue(Venue venue) {
		Venue = venue;
	}    
}
