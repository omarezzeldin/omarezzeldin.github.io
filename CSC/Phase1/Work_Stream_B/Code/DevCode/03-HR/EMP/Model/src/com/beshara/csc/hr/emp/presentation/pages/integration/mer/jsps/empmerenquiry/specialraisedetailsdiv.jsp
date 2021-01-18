<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%><%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:panelGrid columns="1" width="100%">
     <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll" style="display:block;width: 100%;max-height: 360px !important;">
        <t:dataTable id="dataT_Delete" var="list" value="#{pageBeanName.specialRaiseDetailsList}" preserveDataModel="false" renderedIfEmpty="true" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
                width="100%" align="top" rowIndexVar="index" dir="rtl">
           
            <t:column width="20%">
                <f:facet name="header">
                    <h:outputText id="raise_Reason" value="#{integrationBundle.raise_Reason}"/>
                </f:facet>
                <h:outputText id="lck2" value="#{list.salElementGuidesDTO.name}" />
            </t:column>
            
            <t:column width="13%" >
                <f:facet name="header">
                    <h:outputText id="from" value="#{integrationBundle.date_from}"/>
                </f:facet>
                <h:outputText id="date_from" value="#{list.fromDate}" converter="SqlDateConverter"/>
            </t:column>
            
             <t:column width="13%" >
                <f:facet name="header">
                    <h:outputText id="until" value="#{integrationBundle.date_until}"/>
                </f:facet>
                <h:outputText id="date_until" value="#{list.untilDate}" converter="SqlDateConverter"/>
            </t:column>
            
             <t:column width="10%" >
                <f:facet name="header">
                    <h:outputText id="valueLbl" value="#{integrationBundle.special_reward_value}"/>
                </f:facet>
                <h:outputText id="raise_value" value="#{list.elementValue}" converter="FloatThreeDigitConverter"/>
            </t:column>
            
             <t:column width="10%" >
                <f:facet name="header">
                    <h:outputText id="reg_no" value="#{integrationBundle.reg_no_name}"/>
                </f:facet>
                <h:outputText id="reg_no_name" value="#{list.regEmpDecisionDtlsDTO.decisionNumber}"/>
            </t:column>
            
            <t:column width="12%" >
                <f:facet name="header">
                    <h:outputText id="csc_book_no_lbl" value="#{integrationBundle.csc_book_no}"/>
                </f:facet>
                <h:outputText id="csc_book_no" value="#{list.cscBookNo}" style=""/>
            </t:column>
            
            <t:column width="12%" >
                <f:facet name="header">
                    <h:outputText id="csc_book_date_lbl" value="#{integrationBundle.csc_book_date}"/>
                </f:facet>
                <h:outputText id="csc_book_date" value="#{list.cscBookDate}" converter="SqlDateConverter"/>
            </t:column>
            
            <t:column width="10%" >
                <f:facet name="header">
                    <h:outputText id="status" value="#{globalResources.Status}"/>
                </f:facet>
                <t:graphicImage id="graph1" border="0" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{integrationBundle.Valid}')"
                                rendered="#{list.status == 1}"></t:graphicImage>
                <t:graphicImage id="graph2" border="0" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{integrationBundle.notValid}')"
                                rendered="#{list.status == 0}"/>               
            </t:column>
            
        </t:dataTable>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" 
        id="backButtonCustomDiv2" styleClass="cssButtonSmall"
        value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv2,null,null); return false;"/>
    </t:panelGrid>
</t:panelGrid>
