<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<tiles:importAttribute scope="request"/>

<htm:center>
    <t:outputText value="#{regIntgResources.relatedDecisionsDivTile}" styleClass="TitlePage"/>
    <f:verbatim><br/></f:verbatim>
</htm:center>

<%--t:panelGroup forceId="true" id="decIntgRelatedDecisionsCnt1" styleClass="divContent1FullWidth" rendered="#{jdHelperBeanName.relatedDecisionsCnt1Path != null}">
    <jsp:include page="${jdHelperBeanName.relatedDecisionsCnt1Path}" flush="true" />
</t:panelGroup--%>

<t:panelGrid border="0" forceId="true" id="decisionPGId" cellpadding="0" cellspacing="0" width="100%" align="center">
       <t:inputHidden id="intgreportUrl" forceId="true" value="#{jdHelperBeanName.reportUrl}"/>
                            <t:saveState value="#{jdHelperBeanName.reportUrl}"/>
                            <t:saveState value="#{jdHelperBeanName.showBirtBttn}"/>
    <t:panelGroup forceId="true" id="decIntgRelatedDecisionsTblPnl" styleClass="#{jdHelperBeanName.relatedDecisionsCntStyle}">
        <t:dataTable forceId="true" id="decIntgRelatedDecisionsTbl" var="list" 
                value="#{jdHelperBeanName.myTableData}"
                forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                renderedIfEmpty="false"
                rowOnMouseOver="javascript:addRowHighlight('myForm:decIntgRelatedDecisionsTbl');" footerClass="grid-footer"
                styleClass="grid-footer" headerClass="standardTable_Header"
                rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered"
                width="100%" align="top" dir="#{shared_util.pageDirection}">
          
      <t:column id="number_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_number" value="#{regIntgResources.dec_number}"/>
                </f:facet>
                <h:outputText id="content_number" value="#{list.regNum}"/>
     </t:column>
          
    
       <t:column id="date_column" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="header_date" value="#{regIntgResources.dec_date}"/>
                </f:facet>
                <h:outputText id="content_date" value="#{list.decisionDate}"/>
     </t:column>
     
     
       <t:column id="decision_maker_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_decision_maker" value="#{regIntgResources.dec_maker_lbl}"/>
                </f:facet>
                <h:outputText id="content_decision_maker" value="#{list.decisionMakerTypesDTO.decmkrtypeName}"/>
     </t:column>
     
     
            <t:column id="type_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_type" value="#{regIntgResources.type}"/>
                </f:facet>
                <h:outputText id="content_type" value="#{list.typesDTO.name}"/>
            </t:column>
           
            <%--<t:column id="year_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_year" value="#{regIntgResources.reg_year}"/>
                </f:facet>
                <h:outputText id="content_year" value="#{list.yearsDTO.code.key}"/>
            </t:column>--%>
          
            <t:column id="title_column" sortable="false" width="30%">
                <f:facet name="header">
                    <h:outputText id="header_title" value="#{regIntgResources.decision_description}"/>
                </f:facet>
                <h:outputText id="content_title" value="#{list.decisionTitle}"/>
            </t:column>
            <%--t:column id="dateFrom_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_dateFrom" value="#{regIntgResources.dec_date_from_date}"/>
                </f:facet>
                <h:outputText id="content_dateFrom" value="#{list.decisionDate}" converter="TimeStampConverter"/>
            </t:column>
            <t:column id="applyDate_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_applyDate" value="#{regIntgResources.applied_date}"/>
                </f:facet>
                <h:outputText id="content_applyDate" value="#{list.decisionAppliedDate}" converter="TimeStampConverter"/>
            </t:column--%>
            <t:column id="decDetails" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_details" value="#{regIntgResources.details}"/>
                </f:facet>
                <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="#{globalResources.Available}"
                        actionListener="#{jdHelperBeanName.openReport}" oncomplete="openReportWindow('intgreportUrl');"
                        reRender="decIntgHiddenValues,intgreportUrl"   > <!-- rendered="#{!jdHelperBeanName.showBirtBttn}" style="min-width: 35px !important;"-->
                     <f:attribute name="decisionKey" value="#{list.code.key}"/>
                </a4j:commandButton>
                
              
                 <%--<a4j:commandButton  id="okButtonCustomDiv22" value="#{globalResources.view_menu_print"} 
                         actionListener="#{jdHelperBeanName.openBirtReport}"
                         styleClass="cssButtonSmaller"  oncomplete="openBirtReportWindow('intgreportUrl');"
                        reRender="decisionPGId,intgreportUrl" rendered="#{jdHelperBeanName.showBirtBttn}" > 
                     <f:attribute name="decisionKey" value="#{list.code.key}"/>
                </a4j:commandButton>--%>        
                
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{jdHelperBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>