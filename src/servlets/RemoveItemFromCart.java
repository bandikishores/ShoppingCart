package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backingbean.loginBackingBean;

import common.Connection;
import common.Constants;
import common.Util;
import dao.LoadClasses;
import data.CartData;
import data.ItemData;
import data.LoginData;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class RemoveItemFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveItemFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) 
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
		
		if(itemId == -1L)
		{
			Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Could not delete as item was not found!!");
		}
		else
		{
			List<CartData> cartList = new ArrayList<CartData>();
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
				cartList = LoadClasses.getListOfItemsInCartForUser(username);
				
				for(CartData cart : cartList)
				{
					ItemData item = LoadClasses.getItembyItemId(cart.getItemId());
					
					if(item != null)
					{
						cart.setItem(item);
					}
				}
			}
				
			boolean success = false;
			if(!Util.isNullList(cartList))
			{
				for(CartData cart : cartList)
				{
					if(cart.getItemId().longValue() == itemId.longValue())
					{
						cart.setItem(null);
						Connection.getCurrentDBSession().delete(cart);
						success = true;
						break;
					}
				}
			}
			
			Connection.getCurrentTransaction().commit();
			Connection.getCurrentDBSession().close();
			Util.removeBean("cartBackingBean");
			if(success)
				Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Item removed from Cart Successfully!!");
			else
				Connection.getRequest().getSession().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Could not delete as Cart could not be loaded!!");
		}


		RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
		try 
		{
			requestDispatcher.forward(request, response);
		} 
		catch (ServletException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
