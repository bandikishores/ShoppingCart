package backingbean;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.hibernate.Query;
import org.hibernate.Session;

import common.Connection;
import common.Constants;
import common.MultipartHTTPServletRequest;
import common.Util;

import dao.LoadClasses;
import data.CategoryData;
import data.ItemData;
import data.LoginData;

@MultipartConfig
public class AddItemBackingBean extends BaseBackingBean 
{
	private ItemData stateData = new ItemData();
	private CategoryData categoryData;
	private List<ItemData> itemList = new ArrayList();

	private List<SelectItem> categoryName = new ArrayList<SelectItem>();

	public String STATEOBJ_IDENTIFIER = "ItemDataObj";
	
	public CategoryData getCategoryData() {
		return categoryData;
	}

	public void setCategoryData(CategoryData categoryData) {
		this.categoryData = categoryData;
	}

	public List<SelectItem> getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(List<SelectItem> categoryName) {
		this.categoryName = categoryName;
	}
	
	public ItemData getStateData() 
	{
		return stateData;
	}
	
	public void setStateData(ItemData stateData) 
	{
		this.stateData = stateData;
	}
	
	public AddItemBackingBean()
	{
		super();
		if(isForwardMessageSet())
			setForwardMessageSet(false);
		else
			this.clearError();
		
		Util.removeAllBeansExcept("addItemBackingBean");
		
		if(Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER) != null)
			stateData = (ItemData) Connection.getRequest().getAttribute(STATEOBJ_IDENTIFIER);
		List<CategoryData> categoryDataList = LoadClasses.getListOfCategories();
		if(!Util.isNullList(categoryDataList))
		{
			for(CategoryData category : categoryDataList)
			{
				categoryName.add(new SelectItem(category, category.getName()));
			}
		}
		
		itemList = LoadClasses.getListOfItems();
	}
	
	public String validate()
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
		
		String username = Constants.EMPTY_STRING;
		
		loginBackingBean loginBackingBean = (loginBackingBean) Util.getBean("loginBackingBean"); 
		if(loginBackingBean != null)
		{
			LoginData loginData = loginBackingBean.getStateData();
			if(loginData !=null && !Util.isNullTrimmedString(loginData.getUsername()))
			{
				username = loginData.getUsername();
			}
			else
			{
				this.setError("Please Login Again!!!");
				return Constants.ERROR;
			}
		}
		
		if(categoryData == null || categoryData.getId() == null)
		{
			this.setError("Item Category is required!!");
			return Constants.ERROR;
		}
		else
		{
			long categoryId = categoryData.getId();
			
			if(categoryId == -1L)
			{
				this.setError("Item Category is required!!");
				return Constants.ERROR;
			}
			else
			{
				stateData.setCategoryId(categoryId);
			}
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
			
		stateData.setUsername(username);
		
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
		
		File file = new File(Constants.ABS_FILE_PATH_TO_IMAGES+"\\"+Connection.getRequest().getSession().getId()+".jpg");
		if(file != null && file.exists())
		{
			stateData.setImageUrl(Constants.ABS_FILE_PATH_TO_IMAGES+"\\"+ imgName +".jpg");
			File finalDest = new File(stateData.getImageUrl());
			file.renameTo(finalDest);
			
			File backupFile = new File(Constants.IMAGE_BACKUP_PATH+"\\"+ imgName +".jpg");
			try 
			{
				FileUtils.copyFile(finalDest, backupFile);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		Connection.getCurrentDBSession().persist(stateData);
		Connection.getCurrentTransaction().commit();
		Connection.getCurrentDBSession().close();
		
		this.setError(Constants.EMPTY_STRING);
		Util.removeBean("addItemBackingBean");
		Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Item Added Successfully!!");
		return Constants.SUCCESS;
		
	}
}
