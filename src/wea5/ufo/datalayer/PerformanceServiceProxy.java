package wea5.ufo.datalayer;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import wea5.ufo.contracts.Performance;

@ManagedBean(name="performanceServiceProxy")
@ApplicationScoped
public class PerformanceServiceProxy extends ServiceProxy<Performance> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected String getControllerName(){
		return "performance";
	}
}
