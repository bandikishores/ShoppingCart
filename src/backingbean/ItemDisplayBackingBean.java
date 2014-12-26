package backingbean;

import java.util.List;

import org.hibernate.Query;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.CartData;
import data.ItemData;
import data.LoginData;

public class ItemDisplayBackingBean extends BaseBackingBean
{
	private ItemData stateData = new ItemData();
	private CartData cartStateData = new CartData();
	private boolean invalidAccess = false;
	private String imageUrl = Constants.EMPTY_STRING;
	private Long itemId = -1L;
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isInvalidAccess() {
		return invalidAccess;
	}

	public void setInvalidAccess(boolean invalidAccess) {
		this.invalidAccess = invalidAccess;
	}

	public CartData getCartStateData() {
		return cartStateData;
	}

	public void setCartStateData(CartData cartStateData) {
		this.cartStateData = cartStateData;
	}

	private boolean isEditMode = false;

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public ItemData getStateData() {
		return stateData;
	}

	public void setStateData(ItemData stateData) {
		this.stateData = stateData;
	} 
	
	public ItemDisplayBackingBean() 
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
		
		Util.removeAllBeansExcept("itemDisplayBackingBean");
		
		Long itemId = -1L;
		
		if((Connection.getRequest().getParameter("itemId") != null 
				&& Long.parseLong(Connection.getRequest().getParameter("itemId").toString()) >= 0))
		{
			itemId = Long.parseLong(Connection.getRequest().getParameter("itemId").toString());
			setItemId(itemId);
		}
		
		if(itemId < 0L && (Connection.getRequest().getAttribute("itemId") != null 
				&& Long.parseLong(Connection.getRequest().getAttribute("itemId").toString()) >= 0))
		{
			itemId = Long.parseLong(Connection.getRequest().getAttribute("itemId").toString());
			setItemId(itemId);
		}
		
		if(itemId < 0L && (Connection.getRequest().getSession().getAttribute("itemId") != null 
				&& Long.parseLong(Connection.getRequest().getSession().getAttribute("itemId").toString()) >= 0))
		{
			itemId = Long.parseLong(Connection.getRequest().getSession().getAttribute("itemId").toString());
			setItemId(itemId);
		}
		
		if(itemId < 0L && getItemId() >= 0)
		{
			itemId = getItemId();
		}
		
		
		if(itemId >= 0)
		{
			Query HQLQuery = LoadClasses.returnQueryByHQL("From ItemData item where item.id = :itemId");
			HQLQuery.setParameter("itemId", itemId);
			List loadedItemList = HQLQuery.list();
			if(Util.isNullList(loadedItemList))
			{
				setStateData(null);
				setInvalidAccess(true);
				this.setError("Invalid Access!! Item chosen doesn't exist.");
			}
			else
			{
				stateData = (ItemData) loadedItemList.get(0);
				String imgName = stateData.getName() + stateData.getUsername();
				String[] splitImg = imgName.split(" ");
				if(!Util.isNullArray(splitImg))
				{
					imgName = Constants.EMPTY_STRING;
					for(String value : splitImg)
					{
						imgName = imgName + value.trim();
					}
				}
				
				imageUrl = Constants.REL_FILE_PATH_TO_IMAGES + imgName + ".jpg";
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
				
				loginBackingBean loginBackingBean = (loginBackingBean) Util.getBean("loginBackingBean"); 
				if(loginBackingBean != null)
				{
					LoginData loginData = loginBackingBean.getStateData();
					if(loginData !=null && loginData.getUsername() != null && loginData.getUsername().equals(stateData.getUsername()))
					{
						setEditMode(true);
					}
					else
					{
						setEditMode(false);
					}
				}
				setStateData(stateData);
			}
		}
		else
		{
			setStateData(null);
			setInvalidAccess(true);
			this.setError("Invalid Access!! No Item has been selected.");
		}
		
		if(!isEditMode())
		{
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

			Query HQLQuery = LoadClasses.returnQueryByHQL("From CartData cart where cart.itemId = :itemId and cart.username = :username");
			HQLQuery.setParameter("itemId", itemId);
			HQLQuery.setParameter("username", username);
			List loadedCartList = HQLQuery.list();
			if(!Util.isNullList(loadedCartList))
			{
				setCartStateData((CartData) loadedCartList.get(0));
			}
			
		}
	}
	
	public String addToCart()
	{
		if(cartStateData != null && (cartStateData.getQty() == null || cartStateData.getQty() < 0))
		{
			this.setError("Ordered Qty cannot be blank!!");
			return Constants.ERROR;
		}		
		if(cartStateData != null && stateData.getQty() < cartStateData.getQty())
		{
			this.setError("Ordered Qty cannot be greater than available Qty!!");
			return Constants.ERROR;
		}
		
		cartStateData.setItemId(stateData.getId());
		loginBackingBean loginBackingBean = (loginBackingBean) Util.getBean("loginBackingBean"); 
		if(loginBackingBean != null)
		{
			LoginData loginData = loginBackingBean.getStateData();
			if(loginData !=null && !Util.isNullTrimmedString(loginData.getUsername()))
			{
				cartStateData.setUsername(loginData.getUsername());
			}
			else
			{
				this.setError("Please Login Again!!!");
				return Constants.ERROR;
			}
		}
		
		Connection.getCurrentDBSession().persist(cartStateData);
		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		
		this.setError(Constants.EMPTY_STRING);
		Connection.getRequest().setAttribute("categoryId",stateData.getCategoryId());
		Connection.getRequest().getSession().setAttribute("categoryId",stateData.getCategoryId());
		Util.removeBean("itemDisplayBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Item Added to Cart Successfully!!");
		
		return Constants.ITEMLIST;
	}
	
	public String saveItem()
	{
		this.clearError();
		
		if(Util.isNullTrimmedString(stateData.getName()))
		{
			this.setError("Item Name cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getDescription()))
		{
			this.setError("Item Description cannot be null!!");
			return Constants.ERROR;
		}
		if(stateData.getCost() == null || stateData.getCost() < 0)
		{
			this.setError("Item Cost cannot be blank!!");
			return Constants.ERROR;
		}
		if(stateData.getQty() == null || stateData.getQty() < 0)
		{
			this.setError("Item Qty cannot be blank!!");
			return Constants.ERROR;
		}
		
		Connection.getCurrentDBSession().persist(stateData);
		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		
		this.setError(Constants.EMPTY_STRING);
		Connection.getRequest().setAttribute("itemId",stateData.getId());
		Connection.getRequest().getSession().setAttribute("itemId",stateData.getId());
		Util.removeBean("itemDisplayBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Item Updated Successfully!!");
		
		return Constants.ITEMDISPLAY;
	}
	
	public String removeItem()
	{
		this.clearError();
		Connection.getCurrentDBSession().delete(stateData);
		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		
		this.setError(Constants.EMPTY_STRING);
		Connection.getRequest().setAttribute("categoryId",stateData.getCategoryId());
		Connection.getRequest().getSession().setAttribute("categoryId",stateData.getCategoryId());
		Util.removeBean("itemDisplayBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Item Deleted Successfully!!");
		
		return Constants.ITEMLIST;
	}

}
