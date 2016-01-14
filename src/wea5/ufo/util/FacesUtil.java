package wea5.ufo.util;

import java.util.Map;
import java.util.logging.Logger;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

// import wea5.shop.util.Failure;

/**
 * Util class for common JSF methods.
 * 
 * @author snadschlaeger
 */
public class FacesUtil {

	/**
	 * Returns a JSF managed bean.
	 * 
	 * @param name
	 * @return
	 */
	public static Object getSessionVariable(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		ExpressionFactory expressionFactory = application.getExpressionFactory();
		ELContext elContext = facesContext.getELContext();
	
		ValueExpression valueExpression = 
				expressionFactory.createValueExpression(elContext, "#{" + name + "}", Object.class);
		return valueExpression.getValue(elContext);
	}

	/**
	 * Creates a new {@link Failure} object.
	 * 
	 * @param ex
	 * @param logger
	 * @return
	 */
	public static String createFailure(Exception ex, Logger logger) {
		Failure f = (Failure) getSessionVariable("failureModel");
		f.setException(ex);
		f.setMessage(ex.getMessage());
		logger.severe(ex.getMessage());
		return "FailureEvent";
	}
	
	/**
	 * Returns the request parameter value defined by "name".
	 * 
	 * @param name
	 * @return
	 */
	public static String getRequestParameterValue(String name) {
		FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();	  
        return params.get(name);
	}
}
