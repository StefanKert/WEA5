package wea5.ufo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineGroup;
import org.primefaces.extensions.model.timeline.TimelineModel;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Performance;
import wea5.ufo.contracts.Venue;
import wea5.ufo.datalayer.ServiceProxy;

@ManagedBean(name = "performanceBean")
@ViewScoped
public class PerformanceBean extends AbstractDataBean<Performance> implements Serializable {
	private static final long serialVersionUID = -7661418168694226954L;
	private static final Logger logger = Logger.getLogger("PerformancesDataTable");
	private TimelineModel model;

	@ManagedProperty("#{performanceServiceProxy}")
	private ServiceProxy<Performance> serviceProxy;

	@Override
	protected ServiceProxy<Performance> getServiceProxy() {
		return serviceProxy;
	}

	@Override
	public void setServiceProxy(ServiceProxy<Performance> serviceProxy) {
		this.serviceProxy = serviceProxy;
	}

	@PostConstruct
	public void init() {
		initList(new GenericType<List<Performance>>() {
		});
		initializeModel();
	}

	private void initializeModel() {
		logger.info("refresheing data");
		List<Venue> venues = getAllData().stream().map(s -> s.getVenue()).distinct().collect(Collectors.toList());
		model = new TimelineModel();

		for (Venue venue : venues) {
			model.addGroup(new TimelineGroup(Integer.toString(venue.getId()), venue));
		}

		for (Performance performance : getAllData()) {
			TimelineEvent event = new TimelineEvent(performance, performance.getTime().toDate(),
					performance.getTime().plusHours(1).toDate(), true, Integer.toString(performance.getVenueID()));
			if (performance.isCanceled()) {
				event.setStyleClass("eventtype-canceled");
			} else {
				event.setStyleClass("eventtype-ok");
			}
			model.add(event);
		}
	}

	public TimelineModel getModel() {
		return model;
	}

	public Performance getSelectedPerformance() {
		return detailedData;
	}

	public void onSelected(TimelineSelectEvent event) {
		setEntity((Performance) event.getTimelineEvent().getData());
	}

	public void setEntity(Performance entity) {
		detailedData = serviceProxy.getById(Integer.toString(entity.getId()), entity.getGenericInstance());
	}

	@Override
	public void onFilterChanged() {
		logger.info("filter changed searchterm = " + searchTerm);
		if (searchTerm.length() == 0) {
			isFiltered = false;
		} else {
			filteredData = data.stream().filter(x -> filterEntity(x, searchTerm)).collect(Collectors.toList());
			isFiltered = true;
			initializeModel();
		}
	}

	@Override
	protected boolean filterEntity(Performance entity, String filterValue) {
		return entity.getArtist().getName().toUpperCase().contains(filterValue.toUpperCase())
				|| entity.getTitle().toUpperCase().contains(filterValue.toUpperCase())
				|| entity.getVenue().getTitle().toUpperCase().contains(filterValue.toUpperCase());
	}

	public void cancelPerformance() {
		getDetailedData().setCanceled(true);
		saveDetailedData();
		init();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void renewPerformance() {
		getDetailedData().setCanceled(false);
		saveDetailedData();
		init();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public void postponePerformance() {
		saveDetailedData();
		init();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	private void saveDetailedData() {
		try {
			serviceProxy.saveData(getDetailedData());
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
					"Something went terribly wrong. Sorry for that!"));
		}
	}

	public long getZoomMin() {
		return 1000L * 60 * 60 * 12;
	}

	public long getZoomMax() {
		return 1000L * 60 * 60 * 24 * 5;
	}
}
