
function testValidateLogin(){

	document.forms[0].value = '#{testBackingBean.validateLogin}';
    document.forms[0].submit();
	alert("1");
}