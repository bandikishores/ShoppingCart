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

<script type="text/javascript" src="js/commonAction.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript">
	function validateDetails() {
		if (document.getElementById("search:username").value == "") {
			alert("User Name cannot be blank");
			document.getElementById("search:username").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:password").value == "") {
			alert("Password cannot be blank");
			document.getElementById("search:password").style.background = "pink";
			return false;
		}
		if (document.getElementById("search:username").value == document
				.getElementById("search:password").value) {
			alert("User Name and Password cannot be same");
			document.getElementById("search:password").style.background = "pink";
			document.getElementById("search:username").style.background = "pink";
			return false;
		}
		validateLogin();
		return true;
	}

	function usernameValidation() {
		if (document.getElementById("search:username").value == "") 
			{
			document.getElementById("search:username").style.background = "pink";
		}
		else
		{ 
		document.getElementById("search:username").style.background = "white";
		}
	}

	function passwordValidation() {
		if (document.getElementById("search:password").value == "") 
			{
			document.getElementById("search:password").style.background = "pink";
		}
		else
			{
			document.getElementById("search:password").style.background = "white";
			}
	}
</script>
<!-- CuFon ends -->
</head>
<body>
	<f:view>
		<div class="main">
			<div class="header">
				<div class="logo">
					<h1>
						<a href="login.jsp">Welcome to<span> OSM</span> <small><font color = "black">Online
								shopping mall</font></small></a>
					</h1>
				</div>
				<div class="menu_nav">
					<ul>
						<li class="active"><a href="login.jsp">Home</a></li>
					</ul>
					<div class="clr"></div>
				</div>
				<div class="clr"></div>
				<div class="hbg">
					<img src="images/header_images.jpg" width="1060" height="238"
						alt="header images" />
				</div>
			</div>
			<div class="content">
				<div class="content_bg">
					<div class="mainbar">
						<div class="gadget">
								<h:form id="search" onsubmit="return validateDetails()" >
								<table background="images/table_background.jpg">
									<span> <h:panelGrid bgcolor="skyblue" border="3" columns="2" cellpadding="0" cellspacing="2">
  
	 									<f:facet name="header">									
											<h:panelGroup style="display:block; text-align:center">
												<div class="article">
													<font color="red"> <h:outputText id="errorText"
														value="#{loginBackingBean.error}" />
													</font>
												</div>
											</h:panelGroup>
										</f:facet>
										
										<h:panelGroup>
											<font color="black">Username :</font> 
											</h:panelGroup><h:panelGroup><h:inputText value="#{loginBackingBean.stateData.username}"
												id="username" required="true" onblur="usernameValidation()" maxlength="50">
												<f:validateLength minimum="1" maximum="50" />
											</h:inputText>
										</h:panelGroup>

										<h:panelGroup>
											<font color="black">Password :</font> 
											</h:panelGroup><h:panelGroup> 
											<h:inputSecret value="#{loginBackingBean.stateData.password}"
												id="password" required="true" onblur="passwordValidation()" maxlength="20">
												<f:validateLength minimum="1" maximum="20" />
											</h:inputSecret>
										</h:panelGroup>
											
										<h:panelGroup  style="display:block; text-align:center">
											<div class="search" >
											
												<h:commandButton image="images/submit.gif" value="Login"
													id="submit" action="#{loginBackingBean.validateLogin}" />
													<%-- 
													<h:commandButton image="images/submit.gif" value="Login"
													id="submit" action="validateLogin()" /> --%>
											</div>
										</h:panelGroup>

										<f:facet name="footer">
											<h:panelGroup style="display:block; text-align:center">
												<div class="search">
													<a href="registerUser.jsp" style="color: black">Register</a>
												</div>
											</h:panelGroup>
										</f:facet>
										
									</h:panelGrid>
									</span>
									</table>
								</h:form>
								<!--/searchform -->
								<div class="clr"></div>
						</div>

					</div>
					<div class="sidebar">

						<div class="clr"></div>
					</div>
					<div class="gadget">
						<h2>
							<span>Welcome to Shopping Cart</span>
						</h2>
						<div class="clr"></div>
						<div class="article">Please Login</div>
						<div class="gadget">
							<h2>
								<span></span>
							</h2>
							<div class="clr"></div>

						</div>
						<div class="gadget">
							<h2 class="grey">
								<span>Wise Words</span>
							</h2>
							<div class="clr"></div>
							<div class="testi">
								<p>
									<span class="q"><img src="images/quote_1.gif" width="16"
										height="14" alt="quote" /></span> Best shopping you can ever
									experience. <span class="q"><img
										src="images/quote_2.gif" width="16" height="14" alt="quote" /></span>
								</p>
								<p class="title">
									<strong>OSM</strong>
								</p>
							</div>
						</div>
					</div>
					<div class="clr"></div>
				</div>
			</div>
			<div class="fbg">
				<div class="fbg_resize">
					<div class="col c1">
						<h2>
							<span>Image Gallery</span>
						</h2>
						<a href="#"><img src="images/pic_1.jpg" width="58" height="58"
							alt="pix" /></a> <a href="#"><img src="images/pic_2.jpg"
							width="58" height="58" alt="pix" /></a> <a href="#"><img
							src="images/pic_3.jpg" width="58" height="58" alt="pix" /></a> <a
							href="#"><img src="images/pic_4.jpg" width="58" height="58"
							alt="pix" /></a> <a href="#"><img src="images/pic_5.jpg"
							width="58" height="58" alt="pix" /></a> <a href="#"><img
							src="images/pic_6.jpg" width="58" height="58" alt="pix" /></a>
					</div>
					<div class="col c2"></div>
					<div class="col c3"></div>
					<div class="clr"></div>
				</div>
			</div>
			<div class="footer">
				<div class="footer_resize">
					<p class="lf">© Copyright OSM</p>
					<p class="rf"></p>
					<div class="clr"></div>
				</div>
			</div>
		</div>
	</f:view>
</body>
</html>