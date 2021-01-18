<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"
           prefix="c2"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <t:aliasBean alias="#{pageBeanName}" value="#{hireProceduresListBean}">           
     <h:form id="myForm" binding="#{pageBeanName.frm}">    
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>                        
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
            <t:saveState value="#{pageBeanName.relatedList}"/>
            
            <t:saveState value="#{hireProceduresSearchBean.selectedMinistry}"/>
            <t:saveState value="#{hireProceduresSearchBean.pageDTO.ministriesDTO.name}"/>
            <t:saveState value="#{hireProceduresSearchBean.selectedGenderType}"/>
       <%--     <t:saveState value="#{hireProceduresSearchBean.genderTypeList}"/>
            <t:saveState value="#{hireProceduresSearchBean.selectedNationalityType}" />
            <t:saveState value="#{hireProceduresSearchBean.selectedGender}" />  --%>
            <t:saveState  value="#{hireProceduresListBean.fullColumnName}" />
            <t:saveState  value="#{hireProceduresListBean.sortAscending}" />
            <t:saveState  value="#{hireProceduresListBean.saveSortingState}" />
            <t:saveState  value="#{hireProceduresListBean.sortColumn}" />
             <t:saveState  value="#{hireProceduresListBean.rendrAdvancedSearch}" />
   <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
   <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
   <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
          
    <tiles:insert definition="hireprocedures.page" flush="false">                                           
     
     
   </tiles:insert>
   <c2:scriptGenerator form="myForm"/>
    </h:form>
    </t:aliasBean>      
    </f:view>
