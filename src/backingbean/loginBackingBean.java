package backingbean;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import common.Connection;
import common.Constants;
import common.Util;
import data.LoginData;

public class loginBackingBean extends BaseBackingBean
{
	private LoginData stateData = new LoginData();
	public String STATEOBJ_IDENTIFIER = "LoginDataObj";
	
	public loginBackingBean()
	{
		super();
		setForwardMessageWasSet(false);
		if(isForwardMessageSet())
		{
			setForwardMessageSet(false);
			setForwardMessageWasSet(true);
		}
		else
			this.clearError();
		
		Util.removeAllBeansExcept("loginBackingBean");
		if(Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER) != null)
			stateData = (LoginData) Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER);
		if(Util.isNullTrimmedString(this.getError()))
			this.setError("Please Login!!");
	}

	@Override
	public boolean isValidateLogin() {
		return false;
	}

	public void setValidateLogin(boolean validateLogin) {
		this.validateLogin = validateLogin;
	}
	
	public LoginData getStateData() 
	{
		return stateData;
	}

	public void setStateData(LoginData stateData) 
	{
		this.stateData = stateData;
	}

	public String validateLogin()
	{
		this.clearError();
		Session session = Connection.getCurrentDBSession();
		LoginData loadedData = null;
		try
		{
			System.out.println("before load");
			loadedData = (LoginData) session.load(LoginData.class, stateData.getUsername());
			System.out.println("after load");
		}
		catch(ObjectNotFoundException ex)
		{
			ex.printStackTrace();
			stateData.setPassword(Constants.EMPTY_STRING);
			this.setError("Invalid UserName / Password");
			return Constants.ERROR;
		}
		
		if(loadedData != null && !Util.isNullTrimmedString(loadedData.getPassword()) && 
				!Util.isNullTrimmedString(stateData.getPassword()) && loadedData.getPassword().equals(stateData.getPassword()))
		{
			session.close();
			stateData = loadedData;
			stateData.setPassword(Constants.EMPTY_STRING);
			this.setError(Constants.EMPTY_STRING);
			this.setStateData(stateData);
			Connection.getRequest().getServletContext().setAttribute("LOGIN_INFO", "true");
			return Constants.SUCCESSFUL_LOGIN;
		}
		else
		{
			stateData.setPassword(Constants.EMPTY_STRING);
			this.setError("Invalid UserName / Password");
			return Constants.ERROR_LOGIN;
		}
	}

}
