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
        
        <t:aliasBean alias="#{pageBeanName}" value="#{adminAssignEditBean}">  
        
            <h:form id="myForm" binding="#{pageBeanName.frm}">    
            
            <tiles:insert definition="adminassignedit.page" flush="false">    
                
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>                 
                <t:saveState value="#{pageBeanName.assignTypesDTOList}"/>
                <t:saveState value="#{pageBeanName.assignTypesCode}"/>
                <t:saveState value="#{pageBeanName.cadersList}"/>
                <t:saveState value="#{pageBeanName.caderCode}"/>
                <t:saveState value="#{pageBeanName.groupsList}"/>                
                <t:saveState value="#{pageBeanName.groupCode}"/>
                <t:saveState value="#{pageBeanName.jobsDTOList}"/>
                <t:saveState value="#{pageBeanName.jobCode}"/>
                <t:saveState value="#{pageBeanName.assignReasonsDTOList}"/>
                <t:saveState value="#{pageBeanName.assignReasonCode}"/>
                <t:saveState value="#{pageBeanName.workMinistriesList}"/>
                <t:saveState value="#{pageBeanName.workMinistrieKey}"/>
                
         
                <t:saveState value="#{adminAssignListBean.currentPageIndex}"/>
                <t:saveState value="#{adminAssignListBean.oldPageIndex}"/>
                <t:saveState value="#{adminAssignListBean.sorting}"/>
                <t:saveState value="#{adminAssignListBean.usingPaging}"/>
                <t:saveState value="#{adminAssignListBean.updateMyPagedDataModel}"/>
                <t:saveState value="#{adminAssignListBean.resettedPageIndex}"/>
                <t:saveState value="#{adminAssignListBean.pagingRequestDTO}"/>
                <t:saveState value="#{adminAssignListBean.pagingBean.totalListSize}"/>
                <t:saveState value="#{adminAssignListBean.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{adminAssignListBean.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{adminAssignListBean.myTableData}"/>
                <t:saveState value="#{adminAssignListBean.highlightedDTOS}"/>
                <t:saveState value="#{adminAssignListBean.searchMode}"/>                
                <t:saveState value="#{adminAssignListBean.selectedPageDTO}"/>
                <t:saveState value="#{adminAssignListBean.myDataTable}"/>
                
                <t:saveState  value="#{adminAssignListBean.fullColumnName}" />
                <t:saveState  value="#{adminAssignListBean.sortAscending}" />
                <t:saveState  value="#{adminAssignListBean.saveSortingState}" />
                <t:saveState  value="#{adminAssignListBean.sortColumn}" />
                
                <t:saveState value="#{pageBeanName.workCenterHasJobs}"/>
                <t:saveState value="#{pageBeanName.jobName}"/>
                <t:saveState value="#{pageBeanName.workCenterName}"/>
                <t:saveState value="#{pageBeanName.jobDescription}"/>
                
                
                <t:saveState value="#{pageBeanName.caderName}"/>
                <t:saveState value="#{pageBeanName.raiseName}"/>
                <t:saveState value="#{pageBeanName.groupName}"/>
                
            </tiles:insert>
            <t:panelGroup id="scriptPnl">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        
    </h:form>
    </t:aliasBean>
</f:view>