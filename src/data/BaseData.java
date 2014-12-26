package data;

import common.Constants;

public class BaseData implements java.io.Serializable
{
	private String error = "";
	
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

}
