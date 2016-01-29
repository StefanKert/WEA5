package wea5.ufo.contracts;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.owlike.genson.GenericType;
import com.owlike.genson.annotation.JsonIgnore;

public class Performance implements Entity<Performance> {
	private int id;
	private String title;
	private String description;
	private LocalDateTime time;
	private int venueID;
	private int artistID;
	private Artist Artist;
	private Venue Venue;
	private boolean canceled;

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

	public String getTimeFromString() {
		return time.toString("dd.MM.YYYY HH:mm");
	}

	public String getTimeUntilString() {
		return time.plusHours(1).toString("dd.MM.YYYY HH:mm");
	}

	@JsonIgnore
	public Date getTimeAsDate() {
		return time.toDate();
	}

	@JsonIgnore
	public void setTimeAsDate(Date date) {
		time = new LocalDateTime(date);
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
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

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	@JsonIgnore
	@Override
	public GenericType<Performance> getGenericInstance() {
		return new GenericType<Performance>() {
		};
	}

	@JsonIgnore
	@Override
	public GenericType<List<Performance>> getGenericListInstance() {
		return new GenericType<List<Performance>>() {
		};
	}
}
