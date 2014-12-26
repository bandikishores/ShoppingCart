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
<script type="text/javascript" src="js/test.js"></script>
<script type="text/javascript">
	function validateDetails() {
		return true;
	}
</script>
<!-- CuFon ends -->
</head>
<body>
	<f:view>
		
		<h:form id="search" onsubmit="return validateDetails()" >
		
			<font size="15">										
				Username : <h:inputText value="#{testBackingBean.nameSelected}"
								id="username" required="true"   maxlength="50">
						<f:validateLength minimum="1" maximum="50" />
					</h:inputText>
			
					<br/>							
				<h:commandButton image="images/submit.gif" value="Login" id="submit" action="#{testBackingBean.validateLogin}"  />
			</font>	
		</h:form>
								
	</f:view>
</body>
</html>
	