<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<htm:style>.msg.warning { padding-left: 0; }</htm:style>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">
    <t:panelGroup>
        <t:outputText  styleClass="msg warning" value="#{intgBundle.sal_calc_with_ins_alert}" rendered="#{pageBeanName.pageMode != 1}"/>
        <t:outputText  styleClass="msg warning" value="#{intgBundle.sal_calc_alert_ins_delete}" rendered="#{pageBeanName.pageMode == 1}"/>
    </t:panelGroup>
    
</t:panelGrid>
<t:panelGrid  align="center" columns="2">
        <a4j:commandButton type="button" id="mng_aff_okButton" styleClass="cssButtonSmall" value="#{globalResources.ok}"
                           oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,null,null);changeVisibilityMsg();"
                           rendered="#{pageBeanName.pageMode != 1}"
                           action="#{pageBeanName.saveOrSaveAndNew}"
                           reRender="ssds,salcalchidden,buttonGrid,customDiv1,mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar,divMsg"/>


    <%--<a4j:commandButton type="button" id="mng_aff_okButtonandnewgulf" styleClass="cssButtonSmall" 
                            value="and new"
                           oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,null,null);changeVisibilityMsg();"
                          rendered="#{pageBeanName.pageMode == 0}"
                           action="#{pageBeanName.saveEmployeeExceptionAndNew}"
                           reRender="ssds,salcalchidden,buttonGrid,customDiv1,mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar,divMsg"/>--%>
                           
    
    <%--<t:panelGroup rendered="#{pageBeanName.pageMode == 2}">
            <t:commandButton forceId="true"  value="#{intgBundle.exception_save_new}"
                             styleClass="cssButtonSmall" id="btnSaveAdddiv"
                              action="#{pageBeanName.saveEmployeeExceptionAndNew}" />
        </t:panelGroup>--%>
    
     <t:commandButton  id="mng_aff_okButton2" styleClass="cssButtonSmall" value="#{globalResources.ok}" 
                           rendered="#{pageBeanName.pageMode == 1}"
                           action="#{pageBeanName.deleteDiv}" />
                      
        
        <t:commandButton forceId="true" id="backButtonCustomDiv1"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false;"
                         styleClass="cssButtonSmall" value="#{globalResources.back}" tabindex="2"/>
    </t:panelGrid>
