<jsp:include page="Top.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%> 
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page import="common.Util"%>
<div class="article">Please Add new Item</div>


<script type="text/javascript">
	function validateDetails() {
		if (document.getElementById("search:name").value == "") {
			alert("Name cannot be blank");
			document.getElementById("search:name").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:description").value == "") {
			alert("Description cannot be blank");
			document.getElementById("search:description").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:name").value == document
				.getElementById("search:description").value) {
			alert("Name and Description cannot be same");
			document.getElementById("search:description").style.background = "pink";
			document.getElementById("search:name").style.background = "pink";
			return false;
		}
		if(costValidation() == false) return false;
		if(qtyValidation() == false) return false;
		return true;
	}

	function nameValidation() 
	{
		if (document.getElementById("search:name").value == "") 
			{
			document.getElementById("search:name").style.background = "pink";
		}
		else
		{ 
		document.getElementById("search:name").style.background = "white";
		}
	}

	function descriptionValidation() 
	{
		if (document.getElementById("search:description").value == "") 
			{
			document.getElementById("search:description").style.background = "pink";
		}
		else
			{
			document.getElementById("search:description").style.background = "white";
			}
	}

	function costValidation()
	{
		if (document.getElementById("search:cost").value < 0) 
		{
			document.getElementById("search:cost").style.background = "pink";
			return false;
		}
		else
		{
			document.getElementById("search:cost").style.background = "white";
		}
	}

	function qtyValidation()
	{
		if (document.getElementById("search:qty").value < 0) 
		{
			document.getElementById("search:qty").style.background = "pink";
			return false;
		}
		else
		{
			document.getElementById("search:qty").style.background = "white";
		}
	}

	function newWindow()
	{
		popupWindow = window.open('addItemImage.jsp','name','width=200,height=200');
	}

	
</script>

<f:view>
	<h:form id="search" onsubmit="return validateDetails()">
		<table background="images/table_background.jpg">
			<span> <h:panelGrid bgcolor="skyblue" border="3" columns="2"
					cellpadding="0" cellspacing="2">

					<f:facet name="header">
						<h:panelGroup style="display:block; text-align:center">
							<div class="article">
								<font color="red"> <h:outputText id="errorText"
										value="#{addItemBackingBean.error}" />
								</font>
							</div>
						</h:panelGroup>
					</f:facet>

					<h:panelGroup>
						<font color="black">Item Name :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText value="#{addItemBackingBean.stateData.name}"
							id="name" required="true" onblur="nameValidation()"
							maxlength="50">
							<f:validateLength minimum="1" maximum="50" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Item Description :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputTextarea
							value="#{addItemBackingBean.stateData.description}"
							id="description" required="true" onblur="descriptionValidation()">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputTextarea>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Item Image :</font>
					</h:panelGroup>
					<h:panelGroup>
						<input type="button" value="Upload Image" onClick="newWindow()"/>
					</h:panelGroup>


					<h:panelGroup>
						<font color="black">Item Category :</font>
					</h:panelGroup>
					<h:panelGroup>
							<h:selectOneListbox value="#{addItemBackingBean.categoryData}" converter="categoryConverter" size="3">
							<!--
							<c:forEach var="category" items="${addItemBackingBean.categoryData}">
								<OPTION value="<c:out value='${category.name}'/>">
									<c:out value='${category.name}'/>
								</OPTION>
							</c:forEach> 
							-->

								<f:selectItems value="#{addItemBackingBean.categoryName}" />
							</h:selectOneListbox>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Item Cost :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{addItemBackingBean.stateData.cost}"
							id="cost" required="true" onblur="costValidation()" 
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Item Qty :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{addItemBackingBean.stateData.qty}"
							id="qty" required="true" onblur="qtyValidation()"
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup style="display:block; text-align:center">
						<div class="search">
							<h:commandButton image="images/submit.gif" value="Add"
								id="submit" action="#{addItemBackingBean.validate}"/>
						</div>
					</h:panelGroup>

				</h:panelGrid>
			</span>
		</table>
	</h:form>
</f:view>

<jsp:include page="Bottom.jsp"></jsp:include>   