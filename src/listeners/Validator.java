package listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class Validator
 *
 */
@WebListener
public class Validator implements HttpSessionListener, ServletContextListener, ServletRequestListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) 
	{
		String obj = (String) arg0.getSession().getAttribute("LOGIN_INFO");
		if(obj != null && obj.equals("true"))
        	System.out.println("called from session");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) 
	{
		String obj = (String) arg0.getSession().getAttribute("LoginCall");
        if(obj != null && obj.equals("true"))
        	System.out.println("called from login");
		
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) 
	{
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) 
	{
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
