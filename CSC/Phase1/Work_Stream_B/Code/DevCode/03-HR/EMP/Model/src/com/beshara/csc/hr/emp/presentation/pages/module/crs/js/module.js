/* 
Purpose:check for integer value only and accept empty string
Creation/Modification History :
  1.1 - Developer Name:Sherif.omar
  1.2 - Date: 24/5/2008
  1.3 - Creation/Modification:Modification      
  1.4-  Description:
* 
*/

function validateInput(input,console,Errmsg)
{
value = document.getElementById(input).value;

if(!isInteger(value))
{
    document.getElementById(console).innerHTML=Errmsg;
    return false;
}
return true;
}

function validateRegPeriodAdd()
{
    var validateVar=validatemyForm();
    if(validateVar == true)
    {
        changeVisibilityDiv(window.blocker,window.lookupAddDiv);
        setFocusOnElement('searchText');
        return false;   
    }
    return false;
}

function reinitializeSaveMsg(componentID){

     if(document.getElementById(componentID) != null){
       document.getElementById(componentID).innerHTML = '';
     }
}



function vaildateYearWithRegDate()
{
    if(stepValidation() == false)
    {
    document.getElementById('vaildateIssuanceYearWithRegDateId').innerText='';
    return false;
    }
    else
    {   
         if(document.getElementById('maintainModeId').value==0)
        {
            if(document.getElementById('regulationDate1')!=null && document.getElementById('yearsTypeIDO')!=null)
            {
             return compareYearWithYearofDate('yearsTypeIDO','regulationDate1','vaildateIssuanceYearWithRegDateId','yearAndYearDateMsgErrorId');
            }
        }
        return true;
    }

}
