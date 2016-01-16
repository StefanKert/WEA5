package wea5.ufo.beans;

import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractDataBean<T> {
	protected int entityId;
	protected T detailedData;
	protected List<T> data;
	
	public List<T> getAllData() {
		Logger.getAnonymousLogger().info("elements in list: " + Integer.toString(data.size()));
		return data;
	}

	public T getDetailedData() { 
		return detailedData;
	}

	//public abstract void setEntity(T entity);
}
