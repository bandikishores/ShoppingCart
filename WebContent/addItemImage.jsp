<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Image to Item</title>
</head>
<script type="text/javascript">
	function validate()
	{
		var file = document.getElementById("file").value;
		if(file == null || file == "")
			return true;
		if(file.indexOf(".jpg") == -1 && file.indexOf(".gif")  && file.indexOf(".bmp") == -1 && file.indexOf(".jpeg") == -1) 
		{
			alert("Invalid File Format!! Please upload jpg/bmp/gif");
			return false;
		}
		return true;
	} 
</script>
<body>
	<form enctype="multipart/form-data" method="post" action="FileUploaderServler.view" onsubmit="return validate()">
		<input type="file" name="file" id="file" />
		<input type="submit" value="Upload Image">
    </form>
</body>
</html>