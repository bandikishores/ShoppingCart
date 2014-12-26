package common;

public class Constants 
{
	public static final String ACCOUNT_TYPE_CUSTOMER = "CUSTOMER";

	public static final String FORWARD_MESSAGE_TO_NEXT_BB = "forwardToNBB";

	public static final String ABS_FILE_PATH_TO_IMAGES = Connection.getRequest().getServletContext().getRealPath("/itemImages/");
	
	public static final String REL_FILE_PATH_TO_IMAGES = Connection.getRequest().getServletContext().getContextPath()+"/itemImages/";
	
	public static final String IMAGE_BACKUP_PATH = "C:\\itemImages\\";

	public static final String ITEMLIST = "itemList";

	public static final String ITEMDISPLAY = "itemDisplay";

	public static final String SUCCESSFUL_LOGIN = "successfulLogin";

	public static final String ERROR_LOGIN = "errorLogin";

	public static final String CART = "cart";

	public static final String INDEX = "index";

	public static final String PROCESSING = "Processing"; 

	public static String ERROR = "error";
	
	public static String SUCCESS = "success";
	
	public static String EMPTY_STRING = "";

}
