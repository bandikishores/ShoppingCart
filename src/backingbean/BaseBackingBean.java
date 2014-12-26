package backingbean;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;
import com.sun.faces.application.ViewHandlerResponseWrapper;

import common.Connection;
import common.Constants;
import common.Util;

public class BaseBackingBean 
{
	String error = "";
	boolean forwardMessageSet = false;
	boolean forwardMessageWasSet = false;
	boolean validateLogin = true;
	boolean reDirectionEnabled = false;
	
	public boolean isReDirectionEnabled() {
		return reDirectionEnabled;
	}

	public void setReDirectionEnabled(boolean reDirectionEnabled) {
		this.reDirectionEnabled = reDirectionEnabled;
	}

	public boolean isValidateLogin() {
		return validateLogin;
	}

	public void setValidateLogin(boolean validateLogin) {
		this.validateLogin = validateLogin;
	}

	public boolean isForwardMessageWasSet() {
		return forwardMessageWasSet;
	}

	public void setForwardMessageWasSet(boolean forwardMessageWasSet) {
		this.forwardMessageWasSet = forwardMessageWasSet;
	}

	public boolean isForwardMessageSet() {
		return forwardMessageSet;
	}

	public void setForwardMessageSet(boolean forwardMessageSet) {
		this.forwardMessageSet = forwardMessageSet;
	}

	public BaseBackingBean() 
	{
		super();
		
		String firstLoad = (String) Connection.getRequest().getSession().getAttribute("SessionLoadedForFirst");
		if(firstLoad == null || !firstLoad.equals("FALSE"))
		{
			Connection.getRequest().getSession().setAttribute("SessionLoadedForFirst","FALSE");
			
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
			
			/*List<File> fileList = (List<File>) FileUtils.listFiles(new File(Constants.IMAGE_BACKUP_PATH), , null);
			
			if(!Util.isNullList(fileList))
			{
				for(File srcFile : fileList)
				{
					File destFile = new File(Constants.ABS_FILE_PATH_TO_IMAGES+"\\"+srcFile.getName());
					try 
					{
						FileUtils.copyFile(srcFile, destFile);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}*/
		}
		
		/*if(isValidateLogin())
		{
			if(Connection.getRequest().getSession().getAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB) != null 
					&& "Please Login First to access info!!".equals(Connection.getRequest().getSession().getAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB)) )
			{
				// redirection complete.
			}
			else
			{
				HttpServletRequest request = Connection.getRequest();
				ViewHandlerResponseWrapper response = (com.sun.faces.application.ViewHandlerResponseWrapper) Connection.getResponse();
				String path = ((HttpServletRequest) request).getRequestURI();
				if (path.contains("login.jsp"))
				{
				    // Just continue chain.
				} 
				else 
				{
					String loginSuccess = (String) request.getServletContext().getAttribute("LOGIN_INFO");
					if(loginSuccess != null && "true".equals(loginSuccess))
					{
						// Just continue chain.
					}
					else
					{
						Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Please Login First to access info!!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						try 
						{
							reDirectionEnabled = true;
							dispatcher.forward(request, response);
						} 
						catch (ServletException | IOException e) 
						{
							e.printStackTrace();
						}
					}
				}
			}
		}*/
		
		if(!reDirectionEnabled)
		{
			if(Connection.getRequest().getSession().getAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB) != null)
			{
				String message = (String) Connection.getRequest().getSession().getAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB);
				if(!Util.isNullTrimmedString(message))
				{
					setError(message);
					Connection.getRequest().getSession().removeAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB);
					this.setForwardMessageSet(true);
				}
			}
			if(!this.isForwardMessageSet())
			{
				if(Connection.getRequest().getServletContext().getAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB) != null)
				{
					String message = (String) Connection.getRequest().getServletContext().getAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB);
					if(!Util.isNullTrimmedString(message))
					{
						setError(message);
						Connection.getRequest().getServletContext().removeAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB);
						this.setForwardMessageSet(true);
					}
				}
			}
		}
	}

	public BaseBackingBean(boolean b) 
	{
		validateLogin = b;
	}

	public String getError() 
	{
		return error;
	}

	public void setError(String error) 
	{
		this.error = error;
	}

	public void clearError()
	{
		error = Constants.EMPTY_STRING;
	}
	
	public boolean hasAnyErrors()
	{
		if(Util.isNullTrimmedString(this.getError()))
			return false;
		else
			return true;
	}
	
	

}
