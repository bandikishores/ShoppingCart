<jsp:include page="Top.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%> 
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@page import="common.Util"%>
<div class="article">Complete Item View</div>


<script type="text/javascript">

	var removeItemCalled = "FALSE";
	function validateDetails() 
	{
		if(removeItemCalled == "TRUE")
			return true;
		
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
		if(qtySaveValidation() == false) return false;
		if(qtyOrderedValidation == false) return false;
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

	function qtySaveValidation()
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

	function qtyOrderedValidation()
	{
		if (document.getElementById("search:qtyAvailable").value < document.getElementById("search:qtyOrdered").value) 
		{
			document.getElementById("search:qtyOrdered").style.background = "pink";
			return false;
		}
		
		if (document.getElementById("search:qtyOrdered").value < 0) 
		{
			document.getElementById("search:qtyOrdered").style.background = "pink";
			return false;
		}
		else
		{
			document.getElementById("search:qtyOrdered").style.background = "white";
		}
	}

	function newWindow()
	{
		popupWindow = window.open('addItemImage.jsp','name','width=200,height=200');
	}

	function removeItem()
	{
		removeItemCalled = "TRUE";
	}

	
</script>

<f:view>
<h:inputHidden id="itemId" value="#{itemDisplayBackingBean.itemId}"> </h:inputHidden>
	<h:form id="search" onsubmit="return validateDetails()">
		<table background="images/table_background.jpg">
			<span> <h:panelGrid bgcolor="skyblue" border="3" columns="2"
					cellpadding="0" cellspacing="2">
					

					<f:facet name="header">
						<h:panelGroup style="display:block; text-align:center">
							<div class="article">
								<font color="red"> 
									<h:outputText id="errorText" value="#{itemDisplayBackingBean.error}" />
								</font>
							</div>
						</h:panelGroup>
					</f:facet>
					
					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<font color="black">Item Image :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<h:graphicImage value="#{itemDisplayBackingBean.imageUrl}" />
					</h:panelGroup>

					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<font color="black">Item Name :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:outputText value="#{itemDisplayBackingBean.stateData.name}">
						</h:outputText>
					</h:panelGroup>
					<h:panelGroup rendered="#{itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:inputText value="#{itemDisplayBackingBean.stateData.name}"
							id="name" required="true" onblur="nameValidation()"
							maxlength="50">
							<f:validateLength minimum="1" maximum="50" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<font color="black">Item Description :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:outputText value="#{itemDisplayBackingBean.stateData.description}">
						</h:outputText>
					</h:panelGroup>
					<h:panelGroup rendered="#{itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:inputTextarea
							value="#{itemDisplayBackingBean.stateData.description}"
							id="description" required="true" onblur="descriptionValidation()">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputTextarea>
					</h:panelGroup>

					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<font color="black">Item Category :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
							<h:outputText value="#{itemDisplayBackingBean.stateData.category.name}" id="category">
							</h:outputText>
					</h:panelGroup>

					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<font color="black">Item Cost :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:outputText value="#{itemDisplayBackingBean.stateData.cost}">
						</h:outputText>
					</h:panelGroup>
					<h:panelGroup rendered="#{itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:inputText 
							value="#{itemDisplayBackingBean.stateData.cost}"
							id="cost" required="true" onblur="costValidation()" 
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<font color="black">Qty Available :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:outputText value="#{itemDisplayBackingBean.stateData.qty}"
							id="qtyAvailable" >
						</h:outputText>
					</h:panelGroup>
					<h:panelGroup rendered="#{itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:inputText
							value="#{itemDisplayBackingBean.stateData.qty}"
							id="qty" required="true" onblur="qtySaveValidation()"
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>
					
					<h:panelGroup rendered="#{!itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<font color="black">Ordered Qty :</font>
					</h:panelGroup>
					<h:panelGroup rendered="#{!itemDisplayBackingBean.editMode && !itemDisplayBackingBean.invalidAccess}">
						<h:inputText
							value="#{itemDisplayBackingBean.cartStateData.qty}"
							id="qtyOrdered" required="true" onblur="qtyOrderedValidation()"
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup style="display:block; text-align:center" rendered="#{!itemDisplayBackingBean.invalidAccess}">
						<div class="search">
							<h:commandButton image="images/submit.gif" value="Add to Cart"
								id="submit1" action="#{itemDisplayBackingBean.addToCart}" rendered="#{!itemDisplayBackingBean.editMode}"/>
							<h:commandButton image="images/submit.gif" value="Save"
								id="submit" action="#{itemDisplayBackingBean.saveItem}" rendered="#{itemDisplayBackingBean.editMode}"/>
						</div>
					</h:panelGroup>
					
					<h:panelGroup style="display:block; text-align:center" rendered="#{!itemDisplayBackingBean.invalidAccess && itemDisplayBackingBean.editMode}">
						<div class="search">
							<h:commandButton value="Remove Item"
								id="removeItem" onclick="removeItemCalled = 'TRUE'" action="#{itemDisplayBackingBean.removeItem}" rendered="#{itemDisplayBackingBean.editMode}"/>
						</div>
					</h:panelGroup>

				</h:panelGrid>
			</span>
		</table>
	</h:form>
</f:view>

<jsp:include page="Bottom.jsp"></jsp:include>   