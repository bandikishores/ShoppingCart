package backingbean;

import java.util.ArrayList;
import java.util.List;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.OrderData;
import data.LoginData;

public class OrderBackingBean extends BaseBackingBean 
{
	List<OrderData> orderList = new ArrayList<OrderData>();
	
	private boolean invalidAccess = false;
	
	public List<OrderData> getOrderData() {
		return orderList;
	}

	public void setOrderData(List<OrderData> orderList) {
		this.orderList = orderList;
	}
	
	public boolean isInvalidAccess() {
		return invalidAccess;
	}

	public void setInvalidAccess(boolean invalidAccess) {
		this.invalidAccess = invalidAccess;
	}
	
	public OrderBackingBean()
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
		
		Util.removeAllBeansExcept("orderBackingBean");
		
		String username = Constants.EMPTY_STRING;
		LoginData loginData = null;
		loginBackingBean loginBackingBean = (loginBackingBean) Util.getBean("loginBackingBean"); 
		if(loginBackingBean != null)
		{
			loginData = loginBackingBean.getStateData();
			if(loginData !=null && !Util.isNullTrimmedString(loginData.getUsername()))
			{
				username = loginData.getUsername();
			}
		}
		
		if(!Util.isNullTrimmedString(username))
		{
			List<OrderData> orderList = LoadClasses.getListOfOrdersForUser(username);
			if(Util.isNullList(orderList))
			{
				setOrderData(null);
				this.setError("You haven't ordered any item.");
				setInvalidAccess(true);
			}
			else
			{
				for(OrderData order : orderList)
				{
					String address = Constants.EMPTY_STRING;
					if(!Util.isNullTrimmedString(loginData.getFirstName()) && !"null".equalsIgnoreCase(loginData.getFirstName()))
					{
						address = address + loginData.getFirstName() + "  ";
					}
					if(!Util.isNullTrimmedString(loginData.getLastName()) && !"null".equalsIgnoreCase(loginData.getLastName()))
					{
						address = address + loginData.getLastName() + "  <br/>";
					}
					if(!Util.isNullTrimmedString(loginData.getAddress()) && !"null".equalsIgnoreCase(loginData.getAddress()))
					{
						address = address + loginData.getAddress() + " <br/>";
					}
					if(!Util.isNullTrimmedString(loginData.getCity()) && !"null".equalsIgnoreCase(loginData.getCity()))
					{
						address = address + loginData.getCity() + " <br/>";
					}
					if(!Util.isNullTrimmedString(loginData.getState()) && !"null".equalsIgnoreCase(loginData.getState()))
					{
						address = address + loginData.getState() + " <br/>";
					}
					if(!Util.isNullTrimmedString(loginData.getEmail()) && !"null".equalsIgnoreCase(loginData.getEmail()))
					{
						address = address + "EMail : " + loginData.getEmail() + " <br/>";
					}
					if(!Util.isNullTrimmedString(loginData.getContact()) && !"null".equalsIgnoreCase(loginData.getContact()))
					{
						address = address + "Contact Number : " + loginData.getContact() + " <br/>";
					}
					order.setAddress(address);
				}
			}
			setOrderData(orderList);
		}
		else
		{
			setOrderData(null);
			this.setError("Invalid Access!! Please login again.");
			setInvalidAccess(true);
		}
		
		Connection.getRequest().getSession().setAttribute("orderList", orderList);
	}

}
