package wea5.ufo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineGroup;
import org.primefaces.extensions.model.timeline.TimelineModel;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Performance;
import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.PerformanceServiceProxy;

@ManagedBean(name = "performanceBean")
@ViewScoped
public class PerformanceBean extends AbstractDataBean<Performance> implements Serializable {
	private static final long serialVersionUID = -7661418168694226954L;
	private static final Logger logger = Logger.getLogger("PerformancesDataTable");
	private TimelineModel model;
	private TimelineEvent event; // current changed event

	@ManagedProperty("#{performanceServiceProxy}")
	private PerformanceServiceProxy performanceServiceProxy;

	public void setPerformanceServiceProxy(PerformanceServiceProxy performanceServiceProxy) {
		this.performanceServiceProxy = performanceServiceProxy;
	}

	@PostConstruct
	public void init() {
		List<Performance> performances = performanceServiceProxy.getAll(new GenericType<List<Performance>>() {
		});
		List<Venue> venues = performances.stream().map(s -> s.Venue).distinct().collect(Collectors.toList());
		model = new TimelineModel();

		for (Venue venue : venues) {
			TimelineGroup group1 = new TimelineGroup(Integer.toString(venue.id), venue);
			model.addGroup(group1);
		}
		
        for(Performance performance : performances){
        	model.add(new TimelineEvent(performance, performance.time.toDate(), performance.time.plusHours(1).toDate(), true,Integer.toString(performance.venueID)));
        }
	}

	public TimelineModel getModel() {
		return model;
	}

	public Performance getSelectedPerformance(){
		return detailedData;
	}
	
	public void onSelected(TimelineSelectEvent event){
		setEntity((Performance)event.getTimelineEvent().getData());
	}
	 
	public void setEntity(Performance entity) {
		logger.info("settinge entity to " + entity.getId());
		detailedData =	entity;
	} 
}
