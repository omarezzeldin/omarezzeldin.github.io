<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
   <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
   <h:form id="myForm"  binding="#{proceduresMaintain.frm}">
      <t:aliasBean alias="#{pageBeanName}" value="#{hireTypesMaintainBean}">
         <t:aliasBean alias="#{detailBeanName}" value="#{proceduresMaintain}">
            <t:saveState value="#{pageBeanName.pageDTO}" id="fieldpagedto"/>
            <t:saveState value="#{pageBeanName.nextNavigationCase}" id="nextncase"/>
            <t:saveState value="#{pageBeanName.previousNavigationCase}" id="prencase"/>
            <t:saveState value="#{pageBeanName.finishNavigationCase}" id="finishncase"/>
            <t:saveState value="#{pageBeanName.currentNavigationCase}" id="currentncase"/>
            <t:saveState value="#{pageBeanName.currentStep}" id="currentstep"/>
            <t:saveState value="#{pageBeanName.maintainMode}"/>
            <t:saveState value="#{detailBeanName.currentDetails}" id="cdetails"/>
            <t:saveState value="#{detailBeanName.availableDetails}" id="adetails"/>
            <t:saveState value="#{detailBeanName.selectedCurrentDetails}" id="scdetails"/>
            <t:saveState value="#{detailBeanName.selectedAvailableDetails}" id="sadetails"/>
            <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>
            <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
            <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
            <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
            <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
            <t:saveState value="#{detailBeanName.searchString}" />
            
            <t:saveState value="#{hireTypesMainData.ministryCode}"/>
            <t:saveState value="#{hireTypesMainData.conditionCode}"/>
            
            <t:saveState  value="#{hireTypesListBean.fullColumnName}" />
            <t:saveState  value="#{hireTypesListBean.sortAscending}" />
            <t:saveState  value="#{hireTypesListBean.saveSortingState}" />
            <t:saveState  value="#{hireTypesListBean.sortColumn}" />
            
            <tiles:insert definition="proceduresMaintain.page" flush="false">
            <tiles:put name="titlepage" type="string" 
                     value="${pageBeanName.maintainMode == 1 ? 'hireTypesList_title_edit' : 'hireTypesList_title_add'  }" /> 
              <t:messages/>
            </tiles:insert>
         </t:aliasBean>
      </t:aliasBean>
        <c:scriptGenerator form="myForm"/>
   </h:form>
   <script type="text/javascript">
      foucsAddbuttonOnList()
      adjustDataTable('dataT_data_panel','380');

      function foucsAddbuttonOnList() {
          if (document.getElementById('addButton') != null) {
              document.getElementById('addButton').focus();
          }
      }
    </script>
</f:view>
