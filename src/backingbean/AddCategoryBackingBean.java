package backingbean;

import java.util.List;

import org.hibernate.Query;

import common.Connection;
import common.Constants;
import common.Util;

import dao.LoadClasses;
import data.CategoryData;

public class AddCategoryBackingBean extends BaseBackingBean 
{
	private CategoryData stateData = new CategoryData();
	public String STATEOBJ_IDENTIFIER = "CategoryDataObj";
	
	public CategoryData getStateData() 
	{
		return stateData;
	}
	
	public void setStateData(CategoryData stateData) 
	{
		this.stateData = stateData;
	}
	
	public AddCategoryBackingBean()
	{
		super();
		if(isForwardMessageSet())
			setForwardMessageSet(false);
		else
			this.clearError();
		
		Util.removeAllBeansExcept("addCategoryBackingBean");
		if(Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER) != null)
			stateData = (CategoryData) Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER);
	}
	
	public String validate()
	{
		this.clearError();
		if(Util.isNullTrimmedString(stateData.getName()))
		{
			this.setError("Category Name cannot be null!!");
			return Constants.ERROR;
		}
		if(Util.isNullTrimmedString(stateData.getDescription()))
		{
			this.setError("Category Description cannot be null!!");
			return Constants.ERROR;
		}
		
		Query query = LoadClasses.returnQueryByHQL("From CategoryData category Where category.name = :categoryName");
		query.setParameter("categoryName", stateData.getName());
		List categoryList = query.list();
		
		if(Util.isNullList(categoryList))
		{
			Connection.getCurrentDBSession().persist(stateData);
			Connection.getCurrentTransaction().commit();
			Connection.getCurrentDBSession().close();
			
			this.setError(Constants.EMPTY_STRING);
			Util.removeBean("addCategoryBackingBean");
			Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Category Added Successfully!!");
			return Constants.SUCCESS;
		}
		else
		{
			this.setError("Category "+ stateData.getName() +" Already Exists!!");
			return Constants.ERROR;
		}
	}
	
	

}
