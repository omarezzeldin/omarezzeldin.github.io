<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="divResetAlert" styleClass="delDivScroll">
        <t:outputText styleClass="msg warning" value="#{regResources.resetEmpAlertMsg}"/>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <a4j:commandButton id="reset_ok_btn" value="#{globalResources.ok}" 
            actionListener="#{financeDecisionCycleEmployeesMaintainBean.resetEmpData}"
            styleClass="cssButtonSmall" oncomplete="hideLookUpDiv(window.blocker,window.customDiv3,null,null);"
            reRender="totalValuePnl,denar_number1,fels_number1,paging_panel,all_dataT_data_panel,detail_sal_elm_guide,EmpNamePnl,civilPnl"/>
       
        <t:commandButton id="backButtonCustomDiv3"  forceId="true" type="button" value="#{globalResources.cancel}" 
            onclick="hideLookUpDiv(window.blocker,window.customDiv3,null,null);return false;" styleClass="cssButtonSmall"/>
    </t:panelGrid>
</t:panelGrid>