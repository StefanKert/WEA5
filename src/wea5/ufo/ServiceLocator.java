package wea5.ufo;

public class ServiceLocator {
	private static ServiceLocator instance;

	private String serviceHost;
	private String servicePort;
	
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
	}

	public String getServiceHost() {
		return serviceHost;
	}
	
	public String getServicePort() {
		return servicePort;
	}
}
