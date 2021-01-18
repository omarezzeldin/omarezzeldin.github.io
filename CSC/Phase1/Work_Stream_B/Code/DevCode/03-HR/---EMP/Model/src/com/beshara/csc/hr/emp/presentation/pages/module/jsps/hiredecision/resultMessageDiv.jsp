<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.HR.emp.presentation.resources.emp" var="emp"/>
<t:panelGrid columns="1" border="0" width="100%" cellpadding="0" cellspacing="0">
    <htm:span styleClass="msg warning">
        <h:outputText id="errormessage" value="#{pageBeanName.errorMessage}"/></htm:span>
    <t:panelGroup forceId="true" id="dataT_data_pnl" styleClass="delDivScroll">
        <t:dataTable id="dataT_data1234" var="list" styleClass="grid-footer" value="#{hireDecisionListBean.resList}">
            <t:column id="code_column1" sortable="false" width="30%">
                <f:facet name="header">
                    <t:outputText id="code" forceId="true" value="#{emp.civilid}"></t:outputText>
                </f:facet>
                <h:outputText id="content_code" value="#{list.citizensResidentsDTO.civilId}"/>
            </t:column>
            <t:column id="name_column1" sortable="false" width="70%">
                <f:facet name="header">
                    <t:outputText id="name" forceId="true" value="#{emp.candidate_name}"></t:outputText>
                </f:facet>
                <h:outputText id="lineName" value="#{list.citizensResidentsDTO.fullName}"/>
            </t:column>
            <t:column id="details_column" sortable="false" width="15%" rendered="#{pageBeanName.enableViewCondDtls}">
                <f:facet name="header">
                    <h:outputText id="header_details" value="#{grsIntgResources.conditionDetails}"/>
                </f:facet>
                <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="..."
                        actionListener="#{pageBeanName.viewCondLines}" 
                        reRender="condLinesReport,movReuestValues" oncomplete="openGrsIntgConditionlinesDetailsWindow();">
                     <f:attribute name="empCandDTO" value="#{list}"/>
                </a4j:commandButton>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <h:panelGroup style="text-align: center; display: block;">
        <t:commandButton forceId="true" id="backButtonreturn"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="backJsFunction" action="/module/jsps/parameters/parameterlistcontent.jsp"/>
    </h:panelGroup>
</t:panelGrid>
<!-- End Paging -->
