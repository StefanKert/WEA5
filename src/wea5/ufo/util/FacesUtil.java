package wea5.ufo.util;

import java.util.Map;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class FacesUtil {

	public static void showFatalErrorMessage(String message){
		Logger.getLogger("ArtistBean").info("INFO: --------------- Showing error dialog.");
		Logger.getLogger("ArtistBean").info("INFO: " + message);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal Error", message);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMessage);
		RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
	}

	public static String getRequestParameterValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();	  
        return params.get(name);
	}
}
