function submitForm(submitString)
{/*
    element=document.getElementById("targetLink");
    element.value=submitString;*/
	document.forms[0].value = submitString;
    document.forms[0].submit();
    //
    /*element.value='';*/
}