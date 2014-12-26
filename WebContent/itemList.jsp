<%@page import="data.*"%>
<%@page import="common.*"%>
<%@page import="backingbean.*"%>
<%@page import="java.util.*" %>
<jsp:include page="Top.jsp"></jsp:include>
<div class="article">Please continue shopping</div>
<table border="2" background="images/table_background.jpg" cellpadding="2" cellspacing="10">
	<%
	ItemListBackingBean itemListBackingBean = (ItemListBackingBean) Util.getBean("itemListBackingBean");
	List<ItemData> itemList = itemListBackingBean.getItemData();
	if(itemListBackingBean.hasAnyErrors() && itemListBackingBean.isForwardMessageWasSet() == false)
	{
		out.print("<tr><td colspan=\"4\"> <font color=\"red\">" + itemListBackingBean.getError() +" </font></td></tr>");
	}
	else
	{
		if(itemListBackingBean.isForwardMessageWasSet())
		{
			out.print("<tr><td colspan=\"4\"> <font color=\"red\">" + itemListBackingBean.getError() +" </font></td></tr>");
		}
		if(!Util.isNullList(itemList))
		{
			out.print("<tr><td><b><font color='black'> Item </font></b></td><td><b><font color='black'>Item Name</font></b></td><td><b><font color='black'>Item Qty</font></b></td><td><b><font color='black'>Item Price</font></b></td></tr>");
			for(ItemData item : itemList)
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
				
				out.print("<tr>");
				out.println("<td><a href=\"itemDisplay.jsp?itemId="+ item.getId() +"\"> <img src=\""+Constants.REL_FILE_PATH_TO_IMAGES+ imgName +".jpg\" width='300' height='60'/></a></td>");
				out.println("<td><a href=\"itemDisplay.jsp?itemId="+ item.getId() +"\">"+item.getName()+"</a></td>");
				out.println("<td>"+item.getQty()+"</td>");
				out.println("<td>"+item.getCost()+"</td>");
				out.print("</tr>");
			}
		}
	} %>
	<tr><td colspan="4">
	<a href="addItem.jsp" style="color: black">Add New Item</a>
	</td></tr>
</table>

<jsp:include page="Bottom.jsp"></jsp:include>          