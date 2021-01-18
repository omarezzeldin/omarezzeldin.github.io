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
   
    <t:aliasBean alias="#{pageBeanName}" value="#{hireProceduresSearchBean}">
   
        <h:form id="myForm" binding="#{pageBeanName.frm}">    
            
            <tiles:insert definition="hireproceduressearch.page" flush="false">
                
                <t:saveState value="#{pageBeanName.selectedMinistry}"/> 
                <t:saveState value="#{pageBeanName.pageDTO.ministriesDTO.name}"/>
                <t:saveState value="#{hireProceduresListBean.selectedDTOS}" />
                <t:saveState value="#{hireProceduresListBean.pageDTO}" />
                <t:saveState value="#{hireProceduresListBean.myTableData}" />
         <%--       <t:saveState value="#{pageBeanName.selectedGenderType}"/>
                <t:saveState value="#{pageBeanName.genderTypeList}"/>
                <t:saveState value="#{pageBeanName.selectedNationalityType}" />
                <t:saveState value="#{pageBeanName.selectedGender}" />        --%>
                <t:saveState value="#{pageBeanName.errorMsgValue}"/>
                <t:saveState value="#{pageBeanName.searchTypeStr}" />
                
                <t:saveState value="#{pageBeanName.selectedGovFlag}" />
                <t:saveState value="#{pageBeanName.ministrySearchStr}" />
                <t:saveState value="#{pageBeanName.selectedCategoryType}"/>
                
                
                <t:saveState value="#{hireProceduresListBean.searchMode}" />
                 <t:saveState  value="#{hireProceduresListBean.rendrAdvancedSearch}" />
                
     
            </tiles:insert>
            <c2:scriptGenerator form="myForm"/>
        
    </h:form>
    </t:aliasBean>
</f:view>