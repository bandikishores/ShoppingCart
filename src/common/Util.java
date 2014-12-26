package common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

public class Util 
{

	public static boolean isNullTrimmedString(String str) 
	{
		return ((str == null) || (str.trim().length() == 0));
	}
	
	public static Object getBean(String beanName)
	{
		if(Connection.getRequest().getSession().getAttribute(beanName) != null)
			return Connection.getRequest().getSession().getAttribute(beanName);
		
	    FacesContext context = FacesContext.getCurrentInstance();  
	    Application app = context.getApplication();  
	    ValueBinding binding = app.createValueBinding("#{" + beanName + "}");  
	    Object value = binding.getValue(context);  
	    return value;  
	} 
	
	public static Object getNewBean(String beanName)
	{
		FacesContext context = FacesContext.getCurrentInstance();  
	    Application app = context.getApplication();  
	    ValueBinding binding = app.createValueBinding("#{" + beanName + "}");  
	    Object value = binding.getValue(context);  
	    return value;  
	} 
	
	public static void removeBean(String beanName)
	{
		if(Connection.getRequest().getAttribute(beanName) != null)
			Connection.getRequest().removeAttribute(beanName);
		if(Connection.getRequest().getSession().getAttribute(beanName) != null)
			Connection.getRequest().getSession().removeAttribute(beanName); 
	}   
	
	public static boolean isNullArray(Object [] arr) 
	{
        if (arr == null || arr.length == 0) return true;
           for (int i = 0; i < arr.length; i++) 
           {
               if (arr[i] != null) return false;
           }
           return true;
   }

   public static boolean isNullArray( String [] arr ) 
   {
        if (arr == null || arr.length == 0) return true;
           for (int i = 0; i < arr.length; i++) 
           {
               if (!Util.isNullTrimmedString(arr[i])) return false;
           }
           return true;
   }

   public static boolean isNullList(Collection lookupList) 
   {
       if(lookupList == null || lookupList.size() == 0) return true;
       for (Iterator iterator = lookupList.iterator(); iterator.hasNext();) 
       {
               if(iterator.next() != null) return false;
        }
        return true;
   }

   public static boolean isNullMap(Map map) 
   {
		if(map == null || map.size() == 0) 
			return true;
		else        
			return false;    
	}

   public static void removeAllBeansExcept(String bean) 
   {
	   if(!bean.equals("indexBackingBean"))
		   removeBean("indexBackingBean");
	   if(!bean.equals("addCategoryBackingBean"))
		   removeBean("addCategoryBackingBean");
	   if(!bean.equals("registerUserBackingBean"))
		   removeBean("registerUserBackingBean");
	   if(!bean.equals("itemListBackingBean"))
		   removeBean("itemListBackingBean");
	   if(!bean.equals("addItemBackingBean"))
		   removeBean("addItemBackingBean");
	   if(!bean.equals("itemDisplayBackingBean"))
		   removeBean("itemDisplayBackingBean");
	   if(!bean.equals("cartBackingBean"))
		   removeBean("cartBackingBean");
	   if(!bean.equals("orderBackingBean"))
		   removeBean("orderBackingBean");
   }

}
