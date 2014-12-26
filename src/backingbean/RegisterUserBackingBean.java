package backingbean;

import java.util.List;

import org.hibernate.Query;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.LoginData;

public class RegisterUserBackingBean extends BaseBackingBean 
{
	private LoginData stateData = new LoginData();
	private String confirmPassword = Constants.EMPTY_STRING;
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String STATEOBJ_IDENTIFIER = "RegisterUserDataObj";
	
	public LoginData getStateData() 
	{
		return stateData;
	}
	
	public void setStateData(LoginData stateData) 
	{
		this.stateData = stateData;
	}
	
	public RegisterUserBackingBean()
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
		
		Util.removeAllBeansExcept("registerUserBackingBean");
		if(Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER) != null)
			stateData = (LoginData) Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER);
	}
	
	public String validate()
	{
		this.clearError();
		if(Util.isNullTrimmedString(stateData.getUsername()))
		{
			this.setError("UserName cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getPassword()))
		{
			this.setError("Password cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(confirmPassword))
		{
			this.setError("Confirm Password cannot be null!!");
			return Constants.ERROR;
		}
		if(!confirmPassword.equals(stateData.getPassword()))
		{
			this.setError("Passwords doesn't match!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getFirstName()))
		{
			this.setError("First Name cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getLastName()))
		{
			this.setError("Last Name cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getAddress()))
		{
			this.setError("Address cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getCity()))
		{
			this.setError("City cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getState()))
		{
			this.setError("State cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getCountry()))
		{
			this.setError("Country cannot be null!!");
			return Constants.ERROR;
		}
		stateData.setType(Constants.ACCOUNT_TYPE_CUSTOMER);
		
		Query query = LoadClasses.returnQueryByHQL("From LoginData login Where login.username = :username");
		query.setParameter("username", stateData.getUsername());
		List LoginList = query.list();
		
		if(Util.isNullList(LoginList))
		{
			Connection.getCurrentDBSession().persist(stateData);
			Connection.getCurrentTransaction().commit();
			Connection.getCurrentDBSession().close();
			
			Util.removeBean("registerUserBackingBean");
			Util.removeBean("loginBackingBean");
			Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Registration Successful!! Please Login.");
			this.setError(Constants.EMPTY_STRING);
			return Constants.SUCCESS;
		}
		else
		{
			this.setError("UserName "+ stateData.getUsername() +" Already Exists!!");
			return Constants.ERROR;
		}
	}

}
