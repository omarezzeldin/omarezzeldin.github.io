<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:panelGroup id="searchPanel" forceId="true">
<t:outputText styleClass="errMsg" forceId="true" id="outPutMsg"/>
<t:panelGroup>
   <t:outputText forceId="true" id="one_field_required" value="#{resourcesBundle.one_field_required}"
                              styleClass="error" style="display:none"/>
</t:panelGroup>

<t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%">

 <!-- Row1-->
 <t:outputLabel value="#{resourcesBundle.civilid}" styleClass="lable01"/>
 <t:inputText id="search_first_inputTxt" forceId="true"  styleClass="textbox" tabindex="1"  onkeydown="onKeyDownFirstElement(event,'emp_Name','customSearchBackBtn')" value="#{pageBeanName.searchDTO.civilId}" onkeyup="disableCharacters(this);" maxlength="12" converter="javax.faces.Long"/>
 
 <t:outputLabel value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
 <t:inputText id="emp_Name" tabindex="2" forceId="true" value="#{pageBeanName.searchName}"  styleClass="textboxmedium"/>
 
 
 <!-- Row2-->
 <<t:outputLabel id="forCategory" value="#{resourcesBundle.emp_internal_exp_catName}" styleClass="lable01"/>
 
 <t:selectOneMenu id="category" forceId="true" value="#{pageBeanName.selectedCategory}"
                             styleClass="textbox" >
                             
            <f:selectItem itemValue="null" itemLabel="#{resourcesBundle.select}"/>
             <t:selectItems value="#{pageBeanName.catList}" itemLabel="#{catList.name}"
                           itemValue="#{catList.code.key}" var="catList"/>
              
               
            </t:selectOneMenu>
            
      <!-- Row3-->
    
      
        <h:outputLabel value="#{resourcesBundle.orderType}" styleClass="lable01"/>
 
          <t:selectOneMenu id="Inf_request_type_order" forceId="true" value="#{pageBeanName.requestStatustInSearch}" >
               <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="0"  />          
                 <f:selectItem itemLabel="#{resourcesBundle.under_studding}" itemValue="1"/>
                 <f:selectItem itemLabel="#{resourcesBundle.approve}" itemValue="2"/>
                 <f:selectItem itemLabel="#{resourcesBundle.refuse}" itemValue="3"/>
               
             
            </t:selectOneMenu>
      
      
 
 
 
 
 
 
 


</t:panelGrid>
<t:panelGrid columns="2" align="center" >

 
 <t:commandButton forceId="true" id="customSearchBtn" value="#{globalResources.SearchButton}"action="#{pageBeanName.search}" 
onclick="return validateMySearch();test1();" styleClass="cssButtonSmall"  /> 
<a4j:jsFunction name="test1" reRender="OperationBar"/>
 <t:commandButton forceId="true" id="customSearchBackBtn"     value="#{globalResources.back}" type="button" onclick="hideLookUpDiv(window.blocker,window.divSearch,'null','null');"  styleClass="cssButtonSmall"/> 
</t:panelGrid>
</t:panelGroup>


<script language="javascript" type="text/javascript">
    function validateMySearch()
    {

        var result = validatemyForm();
        var requiredMsg = document.getElementById('one_field_required');
        
        var field_1 = document.getElementById('search_first_inputTxt');
        var field_2 = document.getElementById('emp_Name');
        var field_3 = document.getElementById('category');
        var field_4 = document.getElementById('Inf_request_type_order');
      
        
        
        if( 
            ( field_1 == null || field_1.value == "" )
            &&
            ( field_2 == null || field_2.value == "" )
            &&
            ( field_3.selectedIndex == 0 )
            &&
            ( field_4.selectedIndex == 0 )
           
             
         )
         {
         
 
            requiredMsg.style.display = "inline";
            return false;
         }else{
             requiredMsg.style.display = "none";
             return result;
         }
    }
</script>

