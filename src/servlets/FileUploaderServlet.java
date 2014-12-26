package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import common.Connection;
import common.Constants;
import common.MultipartHTTPServletRequest;
import common.Util;

/**
 * Servlet implementation class FileUploaderServlet
 */
@WebServlet("/FileUploaderServlet")
public class FileUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploaderServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<FileItem> items = null;
		try 
		{
			 MultipartHTTPServletRequest multipartHTTPServletRequest = new MultipartHTTPServletRequest(Connection.getRequest());
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(multipartHTTPServletRequest);
		} 
		catch (FileUploadException e) 
		{
			e.printStackTrace();
		}
		if(!Util.isNullList(items))
		{
			for (FileItem item : items) 
			{
	            if (!item.isFormField()) 
	            {
	                String itemName = item.getFieldName();
	                InputStream filecontent = null;

	                try 
	                {
						filecontent = item.getInputStream();
					} 
	                catch (IOException e) 
					{
						e.printStackTrace();
					}
	                
	               /* String currDirPath = System.getProperty("user.dir"); // C:\Users\KISHORE\Desktop\eclipse
	                File file = new File(currDirPath+"\\itemImages");*/
	                new File(Constants.ABS_FILE_PATH_TO_IMAGES).mkdir();
	                File fw = new File(Constants.ABS_FILE_PATH_TO_IMAGES+"\\"+Connection.getRequest().getSession().getId()+".jpg"); // E:\Karthik Shopping Cart\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ShoppingCart\WEB-INF\itemImages\EC34EEE58065AD674192D3D57124F07E.jpg
	                fw.delete();
	                fw.createNewFile();
	                try 
	                {
						item.write(fw);
					} 
	                catch (Exception e) 
	                {
						e.printStackTrace();
					}
	            }
	        }
			
		}
		
		PrintWriter out = response.getWriter();
		out.print("<html><title>Add Image to Item</title><body onload='window.close()'>Uploaded Successfully!!</body>");
		System.out.print("</html>");
	}

}
