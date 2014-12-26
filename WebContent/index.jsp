<%@page import="data.CategoryData"%>
<%@page import="common.Util"%>
<%@page import="backingbean.IndexBackingBean"%>
<%@page import="java.util.*" %>
<jsp:include page="Top.jsp"></jsp:include>
<div class="article">Please continue shopping</div>
<table border="2" background="images/table_background.jpg" cellpadding="2" cellspacing="10">
	<%
	 IndexBackingBean indexBackingBean = (IndexBackingBean) Util.getBean("indexBackingBean");
	List<CategoryData> categoryList = indexBackingBean.getCategoryData();
	if(indexBackingBean.hasAnyErrors() && indexBackingBean.isForwardMessageWasSet() == false)
	{
		out.print("<tr><td colspan=\"2\"> <font color=\"red\">" + indexBackingBean.getError() +" </font></td></tr>");
	}
	else
	{
		if(indexBackingBean.isForwardMessageWasSet())
		{
			out.print("<tr><td colspan=\"2\"> <font color=\"red\">" + indexBackingBean.getError() +" </font></td></tr>");
		}
		if(!Util.isNullList(categoryList))
		{
			for(CategoryData category : categoryList)
			{
				out.print("<tr>");
				out.println("<td><a href=\"itemList.jsp?categoryId="+ category.getId() +"\">"+category.getName()+"</a></td>");
				out.println("<td>"+category.getDescription()+"</td>");
				out.print("</tr>");
			}
		}
	} %>
	<tr><td colspan="2">
	<a href="addCategory.jsp" style="color: black">Add New Category</a>
	</td></tr>
</table>

<jsp:include page="Bottom.jsp"></jsp:include>          