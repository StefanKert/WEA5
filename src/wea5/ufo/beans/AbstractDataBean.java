package wea5.ufo.beans;

import java.util.List;


public abstract class AbstractDataBean<T> {
	protected int entityId;
	protected T detailedData;
	protected List<T> data;
	
	public List<T> getAllData() {
		return data;
	}

	public T getDetailedData() { 
		return detailedData;
	}

	//public abstract void setEntity(T entity);
}
