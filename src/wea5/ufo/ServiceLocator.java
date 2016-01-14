package wea5.ufo;

import java.util.logging.Logger;

import wea5.ufo.contracts.Artist;
import wea5.ufo.contracts.Performance;
import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.ArtistServiceProxy;
import wea5.ufo.datalayer.PerformanceServiceProxy;
import wea5.ufo.datalayer.ServiceProxy;
import wea5.ufo.datalayer.VenueServiceProxy;

public class ServiceLocator {

	private static Logger logger = Logger.getLogger(ServiceLocator.class.getName());
	private static ServiceLocator instance;

	private String serviceHost;
	private String servicePort;
	private boolean initialized = false;
	
	private ServiceLocator() {
	}

	public static synchronized ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	
	public void init(String serviceHost, String servicePort) {
		this.serviceHost = serviceHost;
		this.servicePort = servicePort;
		initialized = true;
	}
	
	public ServiceProxy<Artist> getArtistServiceProxy() {
		logger.info("ArtistServiceProxy requested.");
		
		if(!initialized) {
			logger.severe("Service locater not initialized.");
			return null;
		}
		return new ArtistServiceProxy();
	}
	
	public ServiceProxy<Performance> getPerformanceServiceProxy() {
		logger.info("PerformanceServiceProxy requested.");
		
		if(!initialized) {
			logger.severe("Service locater not initialized.");
			return null;
		}
		return new PerformanceServiceProxy();
	}
	
	public ServiceProxy<Venue> getVenueServiceProxy() {
		logger.info("VenueServiceProxy requested.");
		
		if(!initialized) {
			logger.severe("Service locater not initialized.");
			return null;
		}
		return new VenueServiceProxy();
	}

	public String getServiceHost() {
		return serviceHost;
	}
	
	public String getServicePort() {
		return servicePort;
	}
}
