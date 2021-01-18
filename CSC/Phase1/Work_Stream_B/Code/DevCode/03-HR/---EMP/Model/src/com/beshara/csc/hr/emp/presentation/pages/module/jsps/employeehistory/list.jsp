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

<t:aliasBean alias="#{pageBeanName}" value="#{employeeHistory}">  

<h:form id="myForm" binding="#{pageBeanName.frm}">    

<tiles:insert definition="employeehistory.page" flush="false">    
        <t:saveState value="#{pageBeanName.pageDTO}"/> 
        <t:saveState value="#{pageBeanName.civilId}"/>
        <t:saveState value="#{pageBeanName.validCivilId}"/>
        <t:saveState value="#{pageBeanName.civilExist}"/>
        <t:saveState value="#{pageBeanName.empHired}"/>
        <t:saveState value="#{pageBeanName.searchDTO}"/>
        <t:saveState value="#{pageBeanName.searchMode}"/>
        
        <t:saveState value="#{pageBeanName.filteredByCivilIDList}"/>
        <t:saveState value="#{pageBeanName.jobsSearchDTOList}"/>
        <t:saveState value="#{pageBeanName.workCentersSearchDTOList}"/>
        <t:saveState value="#{pageBeanName.ministriesSearchDTOList}"/>
        
        <t:saveState value="#{pageBeanName.indexArray}"/>
        
        <t:saveState value="#{pageBeanName.payrollInfoExist}"/>
        <t:saveState value="#{pageBeanName.degree}"/>
        <t:saveState value="#{pageBeanName.employeesDTO}"/>
        <t:saveState value="#{pageBeanName.myTableData}"/>
        <t:saveState value="#{pageBeanName.selectedListSize}"/>
        <t:saveState value="#{pageBeanName.notDefinedGenderTypeFlag}"/>
        <t:saveState value="#{pageBeanName.enableEmpLovDiv}"/>
        
        <t:saveState value="#{pageBeanName.fullColumnName}"/>
        <t:saveState value="#{pageBeanName.sortAscending}"/>

        <%--t:saveState value="#{pageBeanName.payrollInfoExist}"/>
        <t:saveState value="#{pageBeanName.salaryElementDTO}"/>
        <t:saveState value="#{pageBeanName.personQulDTO}"/>
        <t:saveState value="#{pageBeanName.outerModule}"/--%>
         
</tiles:insert>
<t:panelGroup forceId="true" id="scriptPanelID">
     <c2:scriptGenerator form="myForm"/>
</t:panelGroup>  


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
    
    
    function setFocusAddDiv(){
        setFocusOnlyOnElement('healthyKidsList');
    }
    
    function setFocusStopDiv(){
        setFocusOnlyOnElement('reasonStopKidsList');
    }
    
</script>


</h:form>
</t:aliasBean>
</f:view>