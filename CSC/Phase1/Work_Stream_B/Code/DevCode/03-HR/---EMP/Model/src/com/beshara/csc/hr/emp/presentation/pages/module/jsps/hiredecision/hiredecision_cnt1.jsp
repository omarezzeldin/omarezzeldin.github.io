<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="colCenter1,colCenter2" cellspacing="0" width="100%"
             align="center" columns="2">
    <h:outputText value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="employees_hiretypes" value="#{pageBeanName.pageDTO.hireTypeKey}">
            <%--<f:selectItem itemValue="#{pageBeanName.virtualStringValue}" itemLabel="#{resourcesBundle.select}"/>--%>
            <f:selectItem itemValue="-1" itemLabel="#{resourcesBundle.ALL_HIRE_TYPES}"/>
            <t:selectItems value="#{pageBeanName.firstLevelHireTypesList}" itemLabel="#{hireTypesList.name}"
                           itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>
            <a4j:support event="onchange" actionListener="#{pageBeanName.filterEmpByHireType}"
                         reRender="dataT_data_panel,paging_panel_group,searchButton,cancelSearchButton,divSearch,DelegationRequestsExcute_ExcuteDate,OperationBar"/>
                
            <%--<t:selectItems value="#{pageBeanName.firstLev
            elHireTypesList}" itemLabel="#{resourcesBundle.ALL_HIRE_TYPES}"
                           itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>--%>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  setFocusAtMyFirstElement();

  function setFocusAtMyFirstElement() {
      document.getElementById('searchButton').focus();
  }
</script>
<t:saveState value="#{pageBeanName.allList}"/>

