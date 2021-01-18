<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<!-- 
    copy this page to your module 
-->
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="integrationBundle"/>
    
    <h:form id="myForm" binding="#{yourBeanName.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{yourBeanName}">
      <tiles:insert flush="false" definition="empMerQuery.page"/>
      <t:saveState value="#{pageBeanName.itemCode}"/>
      <t:saveState value="#{pageBeanName.conditionName}"/>
      <t:saveState value="#{pageBeanName.itemDetails}"/>
      <t:saveState value="#{pageBeanName.fromDate}"/>
      <t:saveState value="#{pageBeanName.employeesDTO}"/>
      <t:saveState value="#{pageBeanName.linesDTOList}"/>
      <t:saveState value="#{pageBeanName.employeeHelper}" id="employeeHelper1"/>
    </t:aliasBean>
    <t:panelGroup forceId="true" id="scriptpanel">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    
    </h:form>
    <script type="text/javascript">
      function filterDivInputOnKeyPress(e, inputtext, ajaxFunction, nextFocusId) {
          var event = e || window.event;
          if (event.keyCode == 13 || event.keyCode == 9) {
              trimBorders(inputtext.value);
              if (inputtext.value == '') {
                  if (nextFocusId != null) {
                      setFocusOnElement(nextFocusId);
                  }
                  event.stopPropagation();
                  event.preventDefault();
                  return false;
              }

              if (ajaxFunction != null) {
                  ajaxFunction();
              }
              else {
                  event.stopPropagation();
                  event.preventDefault();
              }
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }

          }
      }
    </script>
</f:view>
