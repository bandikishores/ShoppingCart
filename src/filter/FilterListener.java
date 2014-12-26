package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import common.Connection;
import common.Constants;
import common.Util;

/**
 * Servlet Filter implementation class FilterListener
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "*.*" })
public class FilterListener implements Filter {

    /**
     * Default constructor. 
     */
    public FilterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getRequestURI();
		if (path.contains("login") || path.contains("register"))
		{
			try
			{
				String beanName = "loginBackingBean";
				if(request.getAttribute(beanName) != null)
					request.removeAttribute(beanName);
				if(request.getServletContext().getAttribute(beanName) != null)
					request.getServletContext().removeAttribute(beanName);
				if(Connection.getRequest().getAttribute(beanName) != null)
					Connection.getRequest().removeAttribute(beanName);
				if(Connection.getRequest().getSession().getAttribute(beanName) != null)
					Connection.getRequest().getSession().removeAttribute(beanName);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		    chain.doFilter(request, response); // Just continue chain.
		} 
		else 
		{
			String loginSuccess = (String) request.getServletContext().getAttribute("LOGIN_INFO");
			if(loginSuccess != null && "true".equals(loginSuccess))
			{
				chain.doFilter(request, response);
			}
			else
			{
				request.getServletContext().setAttribute(Constants.FORWARD_MESSAGE_TO_NEXT_BB, "Please Login First to access info!!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
