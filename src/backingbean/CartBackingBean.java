package backingbean;

import java.util.ArrayList;
import java.util.List;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.CartData;
import data.ItemData;
import data.LoginData;
import data.OrderData;

public class CartBackingBean extends BaseBackingBean 
{
	
	private List<CartData> cartData;
	private boolean invalidAccess = false;
	
	public List<CartData> getCartData() {
		return cartData;
	}

	public void setCartData(List<CartData> cartData) {
		this.cartData = cartData;
	}
	
	public boolean isInvalidAccess() {
		return invalidAccess;
	}

	public void setInvalidAccess(boolean invalidAccess) {
		this.invalidAccess = invalidAccess;
	}
	
	public CartBackingBean() 
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
		
		Util.removeAllBeansExcept("cartBackingBean");
		
		String username = Constants.EMPTY_STRING;
		loginBackingBean loginBackingBean = (loginBackingBean) Util.getBean("loginBackingBean"); 
		if(loginBackingBean != null)
		{
			LoginData loginData = loginBackingBean.getStateData();
			if(loginData !=null && !Util.isNullTrimmedString(loginData.getUsername()))
			{
				username = loginData.getUsername();
			}
		}
		
		if(!Util.isNullTrimmedString(username))
		{
			List<CartData> cartList = LoadClasses.getListOfItemsInCartForUser(username);
			if(Util.isNullList(cartList))
			{
				setCartData(null);
				this.setError("No Items have been added to Cart.");
				setInvalidAccess(true);
			}
			else
			{
				for(CartData cart : cartList)
				{
					ItemData item = LoadClasses.getItembyItemId(cart.getItemId());
					
					if(item != null)
					{
						String imgName = item.getName() + item.getUsername();
						String[] splitImg = imgName.split(" ");
						if(!Util.isNullArray(splitImg))
						{
							imgName = Constants.EMPTY_STRING;
							for(String value : splitImg)
							{
								imgName = imgName + value.trim();
							}
						}
						
						String imageUrl = Constants.REL_FILE_PATH_TO_IMAGES + imgName + ".jpg";
						String[] imgArray = imageUrl.split("/");
						if(!Util.isNullArray(imgArray))
						{
							boolean firstTime = true;
							boolean secondTime = true;
							for(String str : imgArray)
							{
								if(firstTime || secondTime)
								{
									imageUrl = Constants.EMPTY_STRING;
									if(firstTime)
									{
										firstTime = false;
										continue;
									}
									if(secondTime)
									{
										secondTime = false;
										continue;
									}
								}
								imageUrl = imageUrl + "/" + str;
							}
						}
						
						item.setImageUrl(imageUrl);
						cart.setItem(item);
					}
				}
				setCartData(cartList);
			}
		}
		else
		{
			setCartData(null);
			this.setError("Invalid Access!! Please login again.");
			setInvalidAccess(true);
		}
		
		Connection.getRequest().getSession().setAttribute("cartData", cartData);
	}
	
	public boolean validateQty()
	{
		if(!Util.isNullList(getCartData()))
		{
			for(CartData cart : this.cartData)
			{
				if(cart != null && (cart.getQty() == null || cart.getQty() < 0))
				{
					this.setError("Ordered Qty cannot be blank!! for item " + cart.getItem().getName());
					return false;
				}		
				if(cart != null && cart.getItem().getQty() < cart.getQty())
				{
					this.setError("Ordered Qty cannot be greater than available Qty!! for item " + cart.getItem().getName());
					return false;
				}
			}
		}
		return true;
	}
	
	public String addToOrder()
	{
		this.clearError();
		
		if(!validateQty())
		{
			return Constants.ERROR;
		}
		
		List<OrderData> orderList = new ArrayList<OrderData>();
		if(!Util.isNullList(getCartData()))
		{
			for(CartData cart : this.cartData)
			{
				OrderData order = new OrderData();
				ItemData item = cart.getItem();
				order.setName(item.getName());
				order.setDescription(item.getDescription());
				order.setQty(cart.getQty());
				order.setUsername(cart.getUsername());
				order.setCost(cart.getQty() * item.getCost());
				orderList.add(order);
				
				item.setQty(item.getQty() - order.getQty());
				
				cart.setItem(null);
				Connection.getCurrentDBSession().persist(item);
				Connection.getCurrentDBSession().delete(cart);
			}
		}
		
		if(!Util.isNullList(orderList))
		{
			for(OrderData order : orderList)
			{
				order.setItem(null);
				Connection.getCurrentDBSession().persist(order);
			}
		}

		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		this.setError(Constants.EMPTY_STRING);
		Util.removeBean("cartBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Order has been successfully placed!!");
		
		return Constants.INDEX;
	}
	
	public String saveCart()
	{
		this.clearError();
		
		if(!validateQty())
		{
			return Constants.ERROR;
		}
		
		if(!Util.isNullList(getCartData()))
		{
			for(CartData cart : this.cartData)
			{
				cart.setItem(null);
				Connection.getCurrentDBSession().persist(cart);
			}
		}

		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		this.setError(Constants.EMPTY_STRING);
		Util.removeBean("cartBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Cart Saved Successfully!!");
		
		return Constants.CART;
	}
	
	public String removeFromCart()
	{
		Long itemId = -1L;
		
		if((Connection.getRequest().getParameter("itemId") != null 
				&& Long.parseLong(Connection.getRequest().getParameter("itemId").toString()) >= 0))
		{
			itemId = Long.parseLong(Connection.getRequest().getParameter("itemId").toString());
		}
		
		if(itemId < 0L && (Connection.getRequest().getAttribute("itemId") != null 
				&& Long.parseLong(Connection.getRequest().getAttribute("itemId").toString()) >= 0))
		{
			itemId = Long.parseLong(Connection.getRequest().getAttribute("itemId").toString());
		}
		
		if(itemId < 0L && (Connection.getRequest().getSession().getAttribute("itemId") != null 
				&& Long.parseLong(Connection.getRequest().getSession().getAttribute("itemId").toString()) >= 0))
		{
			itemId = Long.parseLong(Connection.getRequest().getSession().getAttribute("itemId").toString());
		}
		
		this.clearError();
		
		if(itemId == -1L)
		{
			this.setError("Could not delete as item was not found");
			return Constants.ERROR;
		}
		
		if(!Util.isNullList(getCartData()))
		{
			for(CartData cart : this.cartData)
			{
				if(cart.getItemId() == itemId)
				{
					cart.setItem(null);
					Connection.getCurrentDBSession().delete(cart);
					break;
				}
			}
		}
		
		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		this.setError(Constants.EMPTY_STRING);
		Util.removeBean("cartBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Item removed from Cart Successfully!!");
		
		return Constants.CART;
	}

}
