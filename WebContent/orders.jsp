<jsp:include page="Top.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%> 
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page import="common.Util"%>
<%@page import="data.*"%>
<%@page import="common.*"%>
<%@page import="backingbean.*"%>
<%@page import="java.util.*" %>
<div class="article">Complete Orders View</div>


<script type="text/javascript">

	

	
</script>

<f:view>
	<h:form id="search">
		<!--  <span> <h:panelGrid bgcolor="skyblue" border="3" columns="6"
					cellpadding="0" cellspacing="2">
					

					<f:facet name="header">
						<h:panelGroup style="display:block; text-align:center">
							<div class="article">
								<font color="red"> 
									<h:outputText id="errorText" value="#{orderBackingBean.error}" />
								</font>
							</div>
						</h:panelGroup>
					</f:facet> 
					
					<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
							<font color="black">Order Status :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
							<font color="black">Item Name :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
							<font color="black">Item Description :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
							<font color="black">Ordered Qty :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
							<font color="black">Order Cost :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
							<font color="black">Shipping Address :</font>
						</h:panelGroup>
					
					<c:if test="${not empty orderBackingBean.orderData}">
						<c:forEach var="order" items="#{orderBackingBean.orderData}">
							
							<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
								<h:outputText value="#{order.status}" style="color: red; font-style: italic;">
								</h:outputText>
							</h:panelGroup>
							
							<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
								<h:outputText value="#{order.name}" style="color: blue; font-style: italic;">
								</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
									<h:outputText value="#{order.description}" style="color: blue; font-style: italic;" >
									</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
								<h:outputText value="#{order.qty}" style="color: green; font-style: italic;">
								</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
								<h:outputText value="#{order.cost}" style="color: sky blue; font-style: italic;" >
								</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!orderBackingBean.invalidAccess}">
								<h:outputText value="#{order.address}"  style="color: black; font-style: italic;" escape="false">
								</h:outputText>
							</h:panelGroup>
						
						</c:forEach> 
					</c:if>

				</h:panelGrid>
			</span> -->
			
			<table border="2" background="images/table_background.jpg" cellpadding="2" cellspacing="10">
	<%
	OrderBackingBean orderBackingBean = (OrderBackingBean) Util.getBean("orderBackingBean");
	List<OrderData> orderList = orderBackingBean.getOrderData();
	if(orderBackingBean.hasAnyErrors() && orderBackingBean.isForwardMessageWasSet() == false)
	{
		out.print("<tr><td colspan=\"6\"> <font color=\"red\">" + orderBackingBean.getError() +" </font></td></tr>");
	}
	else
	{
		if(orderBackingBean.isForwardMessageWasSet())
		{
			out.print("<tr><td colspan=\"6\"> <font color=\"red\">" + orderBackingBean.getError() +" </font></td></tr>");
		}
		if(!Util.isNullList(orderList))
		{
			out.print("<tr><td><b><font color='black'> Order Status </font></b></td><td><b><font color='black'>Item Name</font></b></td><td><b><font color='black'>Item Description</font></b></td><td><b><font color='black'>Ordered Qty</font></b></td><td><b><font color='black'>Order Cost</font></b></td><td><b><font color='black'>Shipping Address</font></b></td></tr>");
			for(OrderData order : orderList)
			{				
				out.print("<tr>");
				out.println("<td style=\"color: red; font-style: italic;\"> "+order.getStatus()+"</td>");
				out.println("<td style=\"color: pink; \"> "+order.getName()+"</td>");
				out.println("<td style=\"color: pink; \"> "+order.getDescription()+"</td>");
				out.println("<td style=\"color: white; font-style: oblique;\"> "+order.getQty()+"</td>");
				out.println("<td style=\"color: white; font-style: oblique;\"> "+order.getCost()+"</td>");
				out.println("<td style=\"color: yellow; font-style: Bold;\"> "+order.getAddress()+"</td>");
				out.print("</tr>");
			}
		}
	} %>
</table>
	</h:form>
</f:view>

<jsp:include page="Bottom.jsp"></jsp:include>   