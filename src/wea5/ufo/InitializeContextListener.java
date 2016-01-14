package wea5.ufo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializeContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		ServletContext servletContext = context.getServletContext();

		String serviceHost = servletContext.getInitParameter("SERVICE_HOST");
		String servicePort = servletContext.getInitParameter("SERVICE_PORT");
		
		ServiceLocator.getInstance().init(serviceHost, servicePort);
	}
}
