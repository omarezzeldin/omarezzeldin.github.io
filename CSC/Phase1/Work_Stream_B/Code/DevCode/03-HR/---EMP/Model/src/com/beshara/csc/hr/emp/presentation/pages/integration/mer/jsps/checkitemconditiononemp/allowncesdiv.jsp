<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<t:panelGroup styleClass="customDiv_header" style="width:590px !important">
    <t:outputText value="#{intgBundle.show_Extra_Data}" />
</t:panelGroup>

<t:panelGroup styleClass="allowncesDivId">


<t:panelGrid width="100%" align="center" cellpadding="3" cellspacing="0" forceId="true" id="allowncesPanelGroup1" >
            <t:panelGroup id="allownces_table_panel1" forceId="true"  styleClass="fullWidthScroll270">
                <t:dataTable id="allownces_table1" forceId="true" var="list" value="#{pageBeanName.allowancesList}"
                                preserveDataModel="false" 
                                footerClass="grid-footer" styleClass="grid-footer"
                                 headerClass="table_header" 
                                 align="top" dir="rtl" renderedIfEmpty="false">
                    <t:column  width="20%">
                        <f:facet name="header">
                            <t:outputText value="#{integrationBundle.allowance_code}" id="allownceCodeId" />
                        </f:facet>
                        <t:outputText id="allownce_code" forceId="true" value="#{list.code.key}" styleClass="output_label"/>
                   </t:column>
                   <t:column  width="80%">
                        <f:facet name="header">
                            <t:outputText value="#{integrationBundle.allowance_name}"  id="allownceNameId"/>
                        </f:facet>
                        <t:outputText id="allownce_name" value="#{list.name}" styleClass="output_label" forceId="true"/>
                   </t:column>
                </t:dataTable>
                <t:panelGrid id="empty_msg_panel1" forceId="true" rendered="#{empty pageBeanName.allowancesList}" align="center">
                    <t:outputText id="empty_msg1"  value="#{globalResources.global_noTableResults}" styleClass="msg"//>
                </t:panelGrid>
            </t:panelGroup>
            
       
</t:panelGrid>
<t:panelGroup forceId="true" id="bttnPGId" >
<t:outputText dir="ltr" forceId="true" id="allowancesListSize" 
                      styleClass="noOfRecords" rendered="#{pageBeanName.allowancesListSize != 0}"
                      value="#{globalResources.noOfRecords} : #{pageBeanName.allowancesListSize}" style="margin-right: 15px;"/>
  <t:commandButton id="backButtonCustomDiv2" forceId="true" value="#{globalResources.back}"
                     onclick="hideLookUpDiv(window.blocker,window.customDiv2);" type="button"
                     styleClass="cssButtonSmall"/>
</t:panelGroup>
</t:panelGroup>

