<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>

<t:aliasBean alias="#{pageBeanName}" value="#{empInternalExperience}">  

<h:form id="myForm" binding="#{pageBeanName.frm}">    

<tiles:insert definition="empinternalexperience.page" flush="false">    
        <t:saveState value="#{pageBeanName.pageDTO}"/> 
        <t:saveState value="#{pageBeanName.civilId}"/>
        <t:saveState value="#{pageBeanName.validCivilId}"/>
        <t:saveState value="#{pageBeanName.civilExist}"/>
        <t:saveState value="#{pageBeanName.empHired}"/>
        <t:saveState value="#{pageBeanName.searchMode}"/>
        <t:saveState value="#{pageBeanName.empFullName}"/>
        
        <t:saveState value="#{pageBeanName.indexArray}"/>
        <t:saveState value="#{pageBeanName.singleSelection}"/>
        
        <t:saveState value="#{pageBeanName.payrollInfoExist}"/>
        <t:saveState value="#{pageBeanName.myTableData}"/>
        <t:saveState value="#{pageBeanName.selectedDTOS}"/>
        <t:saveState value="#{pageBeanName.selectedListSize}"/>
        <t:saveState value="#{pageBeanName.selectedPageDTO}"/>

        
        <t:saveState  value="#{pageBeanName.fullColumnName}" />
        <t:saveState  value="#{pageBeanName.sortAscending}" />
        <t:saveState  value="#{pageBeanName.saveSortingState}" />
        <t:saveState  value="#{pageBeanName.sortColumn}" />
 
</tiles:insert>


<script type="text/javascript"> 

setFocusFirstElem();
function setFocusFirstElem(){
    if( (!isVisibleComponent('lovEmp'))){
       if(document.getElementById('myForm:resetData_btn_id')!=null)
            setFocusOnlyOnElement('myForm:resetData_btn_id'); 
       else 
           setFocusOnlyOnElement('CivilIdAdd');
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


</h:form>
</t:aliasBean>
</f:view>