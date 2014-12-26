<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%> 
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="c"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Design by http://www.bandikishores.com
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>BANDI KISHORE S</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<!-- CuFon: Enables smooth pretty custom font rendering. 100% SEO friendly. To disable, remove this section -->
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<!-- CuFon ends -->
<script type="text/javascript">
	function clearSearch() 
	{
			document.getElementById("searchText").value = "";
		}
</script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="logo">
      <h1><a href="login.jsp">Welcome to<span> OSM</span> <small>Online shopping mall</small></a></h1>
    </div>
    <div class="menu_nav">
      <ul>
        <li class="active"><a href="login.jsp">Home</a></li>
        <li><a href="myAccount.jsp">My Account</a></li>
        <li><a href="cart.jsp">Cart</a></li>
        <li><a href="about.jsp">About Us</a></li>
		<li><a href="login.jsp">Login</a></li>
      </ul>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>
    <div class="hbg"><img src="images/header_images.jpg" width="1060" height="238" alt="header images" /></div>
  </div>
  <div class="content">
    <div class="content_bg">
      <div class="mainbar">
<%@page import="common.Util"%>
<div class="article">Please Add new Category</div>


<script type="text/javascript">
	function validateDetails() {
		if (document.getElementById("search:name").value == "") {
			alert("User Name cannot be blank");
			document.getElementById("search:name").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:password").value == "") {
			alert("Password cannot be blank");
			document.getElementById("search:password").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:confirmPassword").value == "") {
			alert("Confirm Password cannot be blank");
			document.getElementById("search:confirmPassword").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:name").value == document
				.getElementById("search:password").value) {
			alert("User Name and Password cannot be same");
			document.getElementById("search:password").style.background = "pink";
			document.getElementById("search:name").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:password").value != document
				.getElementById("search:confirmPassword").value) {
			alert("Passwords doesn't match");
			document.getElementById("search:password").style.background = "pink";
			document.getElementById("search:confirmPassword").style.background = "pink";
			return false;
		}
		if(firstNameValidation() == false) return false;
		if(lastNameValidation() == false) return false;
		if(addressValidation() == false) return false;
		if(cityValidation() == false) return false;
		if(stateValidation() == false) return false;
		if(countryValidation() == false) return false;
		if(emailValidation() == false) return false;
		return true;
	}

	function nameValidation() {
		if (document.getElementById("search:name").value == "") 
			{
			document.getElementById("search:name").style.background = "pink";
			return false;
		}
		else
		{ 
		document.getElementById("search:name").style.background = "white";
		}
	}

	function passwordValidation() {
		if (document.getElementById("search:password").value == "") 
			{
			document.getElementById("search:password").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:password").style.background = "white";
			}

		if (document.getElementById("search:password").value != document
				.getElementById("search:confirmPassword").value) 
		{
			document.getElementById("search:password").style.background = "pink";
			document.getElementById("search:confirmPassword").style.background = "pink";
			return false;
		}
	}

	function confirmPasswordValidation() 
	{
		if (document.getElementById("search:confirmPassword").value == "") 
			{
			document.getElementById("search:confirmPassword").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:confirmPassword").style.background = "white";
			}

		if (document.getElementById("search:password").value != document
				.getElementById("search:confirmPassword").value) 
		{
			document.getElementById("search:password").style.background = "pink";
			document.getElementById("search:confirmPassword").style.background = "pink";
			return false;
		}
	}

	function firstNameValidation() {
		if (document.getElementById("search:firstName").value == "") 
			{
			document.getElementById("search:firstName").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:firstName").style.background = "white";
			}
	}

	function lastNameValidation() {
		if (document.getElementById("search:lastName").value == "") 
			{
			document.getElementById("search:lastName").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:lastName").style.background = "white";
			}
	}

	function addressValidation() {
		if (document.getElementById("search:address").value == "") 
			{
			document.getElementById("search:address").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:address").style.background = "white";
			}
	}

	function cityValidation() {
		if (document.getElementById("search:city").value == "") 
			{
			document.getElementById("search:city").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:city").style.background = "white";
			}
	}

	function stateValidation() {
		if (document.getElementById("search:state").value == "") 
			{
			document.getElementById("search:state").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:state").style.background = "white";
			}
	}

	function countryValidation() {
		if (document.getElementById("search:country").value == "") 
			{
			document.getElementById("search:country").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:country").style.background = "white";
			}
	}

	function emailValidation() {
		var emailValue = document.getElementById("search:email").value;
		if(emailValue == null || emailValue == "")
			return true;
		if(emailValue.indexOf("@") == -1 || emailValue.indexOf(".") == -1 ) 
			{
			document.getElementById("search:email").style.background = "pink";
			return false;
		}
		else
			{
			document.getElementById("search:email").style.background = "white";
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
										value="#{registerUserBackingBean.error}" />
								</font>
							</div>
						</h:panelGroup>
					</f:facet>

					<h:panelGroup>
						<font color="black">UserName :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText value="#{registerUserBackingBean.stateData.username}"
							id="name" required="true" onblur="nameValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Password :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputSecret
							value="#{registerUserBackingBean.stateData.password}"
							id="password" required="true" onblur="passwordValidation()"
							maxlength="50">
							<f:validateLength minimum="1" maximum="50" />
						</h:inputSecret>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Confirm Password :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputSecret
							value="#{registerUserBackingBean.confirmPassword}"
							id="confirmPassword" required="true" onblur="confirmPasswordValidation()"
							maxlength="50">
							<f:validateLength minimum="1" maximum="50" />
						</h:inputSecret>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">First Name :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.firstName}"
							id="firstName" required="true" onblur="firstNameValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Last Name :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.lastName}"
							id="lastName" required="true" onblur="lastNameValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Address :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.address}"
							id="address" required="true" onblur="addressValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">City :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.city}"
							id="city" required="true" onblur="cityValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">State :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.state}"
							id="state" required="true" onblur="stateValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Country :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.country}"
							id="country" required="true" onblur="countryValidation()"
							maxlength="20">
							<f:validateLength minimum="1" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Contact Number :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.contact}"
							id="contact" required="false"
							maxlength="20">
							<f:validateLength minimum="0" maximum="20" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup>
						<font color="black">Email :</font>
					</h:panelGroup>
					<h:panelGroup>
						<h:inputText
							value="#{registerUserBackingBean.stateData.email}"
							id="email" required="false" onblur="emailValidation()"
							maxlength="30">
							<f:validateLength minimum="0" maximum="30" />
						</h:inputText>
					</h:panelGroup>

					<h:panelGroup style="display:block; text-align:center">
						<div class="search">
							<h:commandButton image="images/submit.gif" value="Add"
								id="submit" action="#{registerUserBackingBean.validate}" />
						</div>
					</h:panelGroup>

				</h:panelGrid>
			</span>
		</table>
	</h:form>
</f:view>

</div>
      <div class="sidebar">
        <div class="gadget">
          <div class="search">
            <form method="get" id="search" action="">
              <span>
              <input type="text" value="Search..." name="s" id="searchText" size="38" onfocus="clearSearch()"/>
              <input name="searchsubmit" type="image" src="images/search.gif" value="Go" id="searchsubmit" class="btn"  />
              </span>
            </form>
            <!--/searchform -->
            <div class="clr"></div>
          </div>
          <div class="clr"></div>
        </div>
        <div class="gadget">
          <h2>Please Register and Login</h2>
          <div class="clr"></div>
          <ul class="sb_menu">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="Items.jsp">Categories</a></li>
			<li><a href="#">Orders</a></li>
			<li><a href="#">My Account</a></li>
            <li><a href="#">Contact us</a></li>
            <li><a href="#">About us</a></li>
          </ul>
        </div>
        <div class="gadget">
          <h2><span></span></h2>
          <div class="clr"></div>
        
        </div>
        <div class="gadget">
          <h2 class="grey"><span>Wise Words</span></h2>
          <div class="clr"></div>
          <div class="testi">
            <p><span class="q"><img src="images/quote_1.gif" width="16" height="14" alt="quote" /></span> Best shopping you can ever experience. <span class="q"><img src="images/quote_2.gif" width="16" height="14" alt="quote" /></span></p>
            <p class="title"><strong>OSM</strong></p>
          </div>
        </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="fbg">
    <div class="fbg_resize">
      <div class="col c1">
        <h2><span>Image Gallery</span></h2>
        <a href="#"><img src="images/pic_1.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_2.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_3.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_4.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_5.jpg" width="58" height="58" alt="pix" /></a> <a href="#"><img src="images/pic_6.jpg" width="58" height="58" alt="pix" /></a> </div>
      <div class="col c2">
         </div>
      <div class="col c3">
        </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="footer">
    <div class="footer_resize">
       <p class="lf">© Copyright OSM</p>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>