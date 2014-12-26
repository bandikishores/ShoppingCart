package common;

import java.io.File;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sun.faces.application.ViewHandlerResponseWrapper;

public class Connection 
{
	Connection()
	{
		// Copy Image Files to runtime.
		File source = new File(Constants.IMAGE_BACKUP_PATH);
		File desc = new File(Constants.ABS_FILE_PATH_TO_IMAGES);
		try 
		{
		    FileUtils.copyDirectory(source, desc);
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	//creating configuration object
	static Configuration cfg;
		
	//creating seession factory object
	public static SessionFactory factory;
		
	//creating session object
	static Session session;
		
	//creating transaction object
	public static Transaction transaction;
	
	private static void loadConfigurationAndFactory()
	{
		if(cfg == null || factory == null)
		{
			cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file
			factory = cfg.buildSessionFactory();
		}
	}

	public static Session getCurrentDBSession()
	{
		if(session != null)
		{
			if(session.isOpen())
				return session;
		}
		loadConfigurationAndFactory();
		session = factory.openSession();
		return session;
	}

	public static Session getNewDBSession()
	{
		loadConfigurationAndFactory();
		return factory.openSession();
	}
	
	public static Transaction getCurrentTransaction()
	{
		if(transaction != null)
		{
			if(transaction.isActive())
				return transaction;
		}
		transaction = getCurrentDBSession().beginTransaction();
		return transaction;
	}
	
	public static HttpServletRequest getRequest()
	{
		Object obj = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return (HttpServletRequest) obj;
	}
	
	public static ViewHandlerResponseWrapper getResponse()
	{
		Object obj = FacesContext.getCurrentInstance().getExternalContext().getResponse();
		return (ViewHandlerResponseWrapper) obj;
	}

}
