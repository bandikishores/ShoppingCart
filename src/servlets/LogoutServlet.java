package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Connection;
import common.Constants;
import common.Util;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet.view")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
		request.getServletContext().removeAttribute("LOGIN_INFO");
		Util.removeBean("loginBackingBean");
		Connection.getCurrentTransaction().rollback();
		Connection.getCurrentDBSession().close();
		Connection.getRequest().getSession().invalidate();
		
		request.getServletContext().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Logged Out Successfully!!");
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		try 
		{
			dispatcher.forward(request, response);
		} 
		catch (ServletException e)
		{
			e.printStackTrace(); 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
