package wea5.ufo.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.ServletException;

/**
 * Simple class representing a failure view model.
 * 
 * @author snadschlaeger
 */
@ManagedBean(name="failureModel")
@RequestScoped
public class Failure {

	private Exception exception;
	private String message;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		fillStackTrace(getException(), pw);
		return sw.toString();
	}
	
	private void fillStackTrace(Throwable t, PrintWriter w) {
		if (t == null) {
			return;
		}
		
		t.printStackTrace(w);
		
		if (t instanceof ServletException) {
			Throwable cause = ((ServletException) t).getRootCause();
			w.println("Root cause: ");
			fillStackTrace(cause, w);
		} else if (t instanceof SQLException) {
			Throwable cause = ((SQLException) t).getNextException();
			w.println("Next exception: ");
			fillStackTrace(cause, w);
		} else {
			Throwable cause = t.getCause();
			w.println("Cause: ");
			fillStackTrace(cause, w);
		}
	}
	
}
