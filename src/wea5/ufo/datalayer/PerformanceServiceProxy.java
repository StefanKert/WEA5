package wea5.ufo.datalayer;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import wea5.ufo.contracts.Performance;

@ManagedBean(name="performanceServiceProxy")
@ApplicationScoped
public class PerformanceServiceProxy extends ServiceProxy<Performance> {
	@Override
	protected String getControllerName(){
		return "performance";
	}
}
