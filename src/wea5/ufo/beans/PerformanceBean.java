package wea5.ufo.beans;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedProperty;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Performance;
import wea5.ufo.datalayer.PerformanceServiceProxy;


public class PerformanceBean {
	private static final Logger logger = Logger.getLogger("PerformancesDataTable");
	
	@ManagedProperty("#{performanceServiceProxy}")
	private PerformanceServiceProxy performanceServiceProxy;
	
	public void setPerformanceServiceProxy(PerformanceServiceProxy performanceServiceProxy) {
		this.performanceServiceProxy = performanceServiceProxy;
	}
	
	public List<Performance> getPerformances() {
		logger.info("getPerformances");
		return performanceServiceProxy.getAll(new GenericType<List<Performance>>() {});
	}
}
