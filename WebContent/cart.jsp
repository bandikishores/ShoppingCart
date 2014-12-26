<jsp:include page="Top.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%> 
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page import="common.Util"%>
<%@page import="data.*"%>
<div class="article">Complete Cart View</div>


<script type="text/javascript">

	

	
</script>

<f:view>
	<h:form id="search">
		<table background="images/table_background.jpg">
			<span> <h:panelGrid bgcolor="skyblue" border="3" columns="7"
					cellpadding="0" cellspacing="2">
					

					<f:facet name="header">
						<h:panelGroup style="display:block; text-align:center">
							<div class="article">
								<font color="red"> 
									<h:outputText id="errorText" value="#{cartBackingBean.error}" />
								</font>
							</div>
						</h:panelGroup>
					</f:facet> 
					
					<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Item Image :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Item Name :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Item Category :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Item Cost/Unit :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Qty Available :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Ordered Qty :</font>
						</h:panelGroup>
						
						<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<font color="black">Action :</font>
						</h:panelGroup>
					
					<c:if test="${not empty cartBackingBean.cartData}">
						<c:forEach var="cart" items="#{cartBackingBean.cartData}">
							
							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
								<h:graphicImage value="#{cart.item.imageUrl}" height="60" width="300"/>
							</h:panelGroup>
							
							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
								<h:outputText value="#{cart.item.name}">
								</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
									<h:outputText value="#{cart.item.category.name}" id="category">
									</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
								<h:outputText value="#{cart.item.cost}">
								</h:outputText>
							</h:panelGroup>
		
							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
								<h:outputText value="#{cart.item.qty}" >
								</h:outputText>
							</h:panelGroup>
							
							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
								<h:inputText
									value="#{cart.qty}"
									 required="true" 
									maxlength="200">
									<f:validateLength minimum="1" maximum="200" />
								</h:inputText>
							</h:panelGroup>

							<h:panelGroup rendered="#{!cartBackingBean.invalidAccess}">
							<%
								CartData cart = (CartData) request.getAttribute("cart");
								if(cart != null)
									out.println("<input type=\"Hidden\" id =\"itemId\" name =\"itemId\" value =\""+ cart.getItem().getId() +"\" />");
							%>
							
							<a href="RemoveItemFromCart.view?itemId=${cart.item.id}" style="color: blue; font-style: italic;border: green; ">Remove item from cart</a>
							
						<!--  		<h:commandButton value="Remove from Cart" 
									id="submit2" action="#{cartBackingBean.removeFromCart}" />  -->
							</h:panelGroup>
						
						</c:forEach> 
					</c:if>
	
						<h:panelGroup style="display:block; text-align:center" rendered="#{!cartBackingBean.invalidAccess}">
							<div class="search">
								<h:commandButton value="Add to Order"
									id="submit1" action="#{cartBackingBean.addToOrder}"/>
								<h:commandButton image="images/submit.gif" value="Save Cart" 
									id="submit" action="#{cartBackingBean.saveCart}"/>
							</div>
						</h:panelGroup>

				</h:panelGrid>
			</span>
		</table>
	</h:form>
</f:view>

<jsp:include page="Bottom.jsp"></jsp:include>   