package wea5.ufo.beans;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Entity;
import wea5.ufo.datalayer.ServiceProxy;

public abstract class AbstractDataBean<T extends Entity<T>> {
	protected int entityId;
	protected T detailedData;
	protected List<T> filteredData;
	protected List<T> data;
	protected boolean isFiltered;
	protected String searchTerm = "";
	
	protected abstract ServiceProxy<T> getServiceProxy();

	public abstract void setServiceProxy(ServiceProxy<T> serviceProxy);

	protected void initList(GenericType<List<T>> genericType) {
		this.data = getServiceProxy().getAll(genericType);
	}

	protected void initList(Class<T> type) {
		try {
			this.data = getServiceProxy().getAll(type.newInstance().getGenericListInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void initEntityWithId(String key, GenericType<T> genericType) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String idString = params.get(key);
		Logger.getAnonymousLogger().info("Id selected is " + idString);
		detailedData = getServiceProxy().getById(idString, genericType);
	}

	public List<T> getAllData() {
		if(isFiltered)
			return filteredData;
		return data;
	}

	public T getDetailedData() {
		return detailedData;
	}
	
	public void setSearchtTerm(String searchTerm){
		this.searchTerm = searchTerm;
	}
	
	public String getSearchtTerm(){
		return this.searchTerm;
	}
	
	protected abstract boolean filterEntity(T entity, String filterValue);
    
	public void onPageLoad(){
		setSearchtTerm("");
		isFiltered = false;
	}
	
    public void onFilterChanged() {
    	if(searchTerm.length() == 0){
    		isFiltered = false;
    	}
    	else {
    		if(data == null)
    			return;
	    	filteredData = data.stream().filter(x -> filterEntity(x, searchTerm)).collect(Collectors.toList());
	    	isFiltered = true;
    	}
    }
	
	@SuppressWarnings("unchecked")
	public void onRowSelect(SelectEvent event) {
		T data = (T) event.getObject();
		detailedData = getServiceProxy().getById(Integer.toString(data.getId()), data.getGenericInstance());
	}
}
