<jsp:include page="Top.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%> 
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="c"%>
<%@page import="common.Util"%>
<div class="article">Please Add new Category</div>


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
		return true;
	}

	function nameValidation() {
		if (document.getElementById("search:name").value == "") 
			{
			document.getElementById("search:name").style.background = "pink";
		}
		else
		{ 
		document.getElementById("search:name").style.background = "white";
		}
	}

	function descriptionValidation() {
		if (document.getElementById("search:description").value == "") 
			{
			document.getElementById("search:description").style.background = "pink";
		}
		else
			{
			document.getElementById("search:description").style.background = "white";
			}
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
										value="#{addCategoryBackingBean.error}" />
								</font>
							</div>
						</h:panelGroup>
					</f:facet>

					<h:panelGroup>
						<font color="black">Category Name :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText value="#{addCategoryBackingBean.stateData.name}"
							id="name" required="true" onblur="nameValidation()"
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Category Description :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{addCategoryBackingBean.stateData.description}"
							id="description" required="true" onblur="descriptionValidation()"
							maxlength="200">
							<f:validateLength minimum="1" maximum="200" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup style="display:block; text-align:center">
						<div class="search">
							<h:commandButton image="images/submit.gif" value="Add"
								id="submit" action="#{addCategoryBackingBean.validate}" />
						</div>
					</h:panelGroup>

				</h:panelGrid>
			</span>
		</table>
	</h:form>
</f:view>

<jsp:include page="Bottom.jsp"></jsp:include>   