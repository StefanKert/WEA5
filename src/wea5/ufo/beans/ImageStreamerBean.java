package wea5.ufo.beans;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.owlike.genson.GenericType;

import wea5.ufo.contracts.Artist;
import wea5.ufo.datalayer.ServiceProxy;

@ManagedBean(name="imageStreamer")
@ApplicationScoped
public class ImageStreamerBean {

	@ManagedProperty("#{artistServiceProxy}")
	private ServiceProxy<Artist>  serviceProxy;
	
	protected ServiceProxy<Artist> getServiceProxy() {
		return serviceProxy;
	}

	public void setServiceProxy(ServiceProxy<Artist> serviceProxy) {
		this.serviceProxy = serviceProxy;
	}
	
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String id = context.getExternalContext().getRequestParameterMap().get("artistId");
            if(id == null || id.isEmpty())
            	return new DefaultStreamedContent();
            Artist artist = getServiceProxy().getById(id, new GenericType<Artist>() {});
            
            return artist.getImageJSF();
        }
    }

}