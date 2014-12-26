<%@page import="common.*"%>
<%@page import="data.*"%>
<%@page import="backingbean.*"%>
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
      <h1>
      <div class="logo">
      	<a href="index.jsp"> Welcome to<span> OSM</span> <small><font color = "black">Online
								shopping mall</font></small></a>
	</div>
	  </h1>
    
    <div class="menu_nav">
      <ul>
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="myAccount.jsp">My Account</a></li>
        <li><a href="cart.jsp">Cart</a></li>
        <li><a href="about.jsp">About Us</a></li>
		<li>
			<a href="LogoutServlet.view">LogOut</a>
		</li>
      </ul>
      <div class="clr"></div>
    </div>
    <div class="clr"></div>
    <div class="hbg"><img src="images/header_images.jpg" width="1060" height="238" alt="header images" /></div>
  </div>
  <div class="content">
    <div class="content_bg">
      <div class="mainbar">