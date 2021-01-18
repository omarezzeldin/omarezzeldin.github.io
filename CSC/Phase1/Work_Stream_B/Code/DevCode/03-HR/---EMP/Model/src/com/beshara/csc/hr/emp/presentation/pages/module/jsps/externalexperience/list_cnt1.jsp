<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:messages showDetail="true" />
<t:panelGroup  forceId="true" style="width:100%" id="emp_query_panel_id">


    <t:panelGrid columnClasses="colu1,colu2" columns="6" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
        <h:outputText value="#{resourcesBundle.civilid}" styleClass="lable01"/>
       
 <t:panelGroup >
        <t:inputText onkeyup="disableCharacters(this);" rendered="#{!pageBeanName.civilExist}" tabindex="1" onblur="document.getElementById('civil_exist_btn').focus();" maxlength="12" forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{pageBeanName.civilId}" onkeypress="FireButtonClick('civil_exist_btn')"/>
        <t:outputText   styleClass="textbox"  value="#{pageBeanName.civilId}" rendered="#{pageBeanName.civilExist}" />
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{!pageBeanName.civilExist}" id="civil_exist_btn" forceId="true" onclick="resetMsgInAdd();return validatemyForm();" tabindex="2" 
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{pageBeanName.init}"/>
           
        <t:commandButton rendered="#{pageBeanName.civilExist}" id="resetButton_id" forceId="true" onclick="foucsFirstElementOnListPage();resetMsgInAdd();return validatemyForm();" 
               value="#{globalResources.reSetButton}"  styleClass="cssButtonSmall"  action="#{pageBeanName.reSetData}"/>
            <f:verbatim> &nbsp; </f:verbatim>
            <a4j:commandButton type="button"  value="#{globalResources.SearchButton}" styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();" onblur="foucsFirstElementOnListPage();" action="#{pageBeanName.showSearchForEmployeeDiv}" tabindex="3"
                   reRender="lovEmp,mainDataEmpPanel" rendered="#{!pageBeanName.civilExist}" />    
            <f:verbatim ><br/></f:verbatim>
            <c2:requiredFieldValidator     active="#{!externalExperienceListBean.civilExist}" componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
            <c2:regularExpressionValidator active="#{!externalExperienceListBean.civilExist}" componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic"/>
            <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        </t:panelGroup>
         <t:panelGroup colspan="4" />
         <t:outputText  value="#{resourcesBundle.employeeName}" styleClass="lable01"/>
         <t:inputText value="#{pageBeanName.emp.citizensResidentsDTO.fullName}" styleClass="textboxlarge" disabled="true"/>
         
        
        <t:outputText  value="#{resourcesBundle.cscfilno}" styleClass="lable01" />
        <t:inputText value="#{pageBeanName.emp.cscFileNo}" styleClass="textbox" disabled="true"/>
        
         <t:panelGroup colspan="2"/>
        <t:outputText  value="#{resourcesBundle.job_name}" styleClass="lable01" />
        <t:inputText value="#{pageBeanName.emp.jobDTO.name}" styleClass="textboxlarge" disabled="true"/>
        
        <t:outputText  value="#{resourcesBundle.empDate}"  styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.emp.hireDate}" styleClass="textbox" disabled="true" converter="SqlDateConverter"/>
        
       <%--
       <t:outputText  value="#{resourcesBundle.personal_information_identity_no}"/>
        <t:panelGroup colspan="5">
          <t:inputText value="" styleClass="textbox" disabled="true"/>
        </t:panelGroup>
       
       <t:outputText  value="#{resourcesBundle.status}"/>
       <t:inputText value="#{pageBeanName.emp.citizensResidentsDTO.maritalStatusDTO.name}" styleClass="textbox" disabled="true"/>
       
       
       <t:outputText  value="#{resourcesBundle.nationalityType}"/>
       <t:inputText value="#{pageBeanName.emp.citizensResidentsDTO.countriesDTO.name}" styleClass="textbox" disabled="true"/>
       
        <t:outputText  value="#{resourcesBundle.personal_information_nationality_no}"/>
        <t:inputText value="#{pageBeanName.emp.citizensResidentsDTO.countriesDTO.code.keys[1]}" styleClass="textbox" disabled="true"/>
       
      
        <t:outputText  value="#{resourcesBundle.gender}"/>
       <t:inputText value="#{pageBeanName.emp.citizensResidentsDTO.genderTypesDTO.name}" styleClass="textbox" disabled="true"/>
        
        <t:outputText  value="#{resourcesBundle.personal_information_religion}"/>
       <t:inputText value="#{pageBeanName.emp.citizensResidentsDTO.religionsDTO.name}" styleClass="textbox" disabled="true" forceId="true" id="hireDateTxt"/> 
    --%>
       
       
</t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
    
    foucsFirstElementOnListPage();
    function foucsFirstElementOnListPage(){        
        if(document.getElementById('CivilIdAdd') != null){            
            document.getElementById('CivilIdAdd').focus();
            document.getElementById('CivilIdAdd').focus();
           }
           else if(document.getElementById('resetButton_id') != null) {
            document.getElementById('resetButton_id').focus();
            document.getElementById('resetButton_id').focus();
           }
        
    }
    
    
     function resetMsgInAdd()
    {
        if(document.getElementById('invalCivilID') != null)
        {
            document.getElementById('invalCivilID').innerHTML='';
        } 
        if(document.getElementById('empHired')!=null)
        {
            document.getElementById('empHired').innerHTML='';
        }
        if(document.getElementById('payrollInfoExist')!=null)
        {
            document.getElementById('payrollInfoExist').innerHTML='';
        }
    }
    
</script>
