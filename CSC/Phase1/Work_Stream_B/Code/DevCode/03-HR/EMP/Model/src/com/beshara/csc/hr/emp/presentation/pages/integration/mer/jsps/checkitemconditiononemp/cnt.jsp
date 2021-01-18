<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" columns="2">
    <t:panelGroup forceId="true" id="dataT_data_pnl" styleClass="delDivScroll" style="max-height: 255px !important;">
        <t:dataTable id="dataT_data_line" var="list" binding="#{pageBeanName.myDataTable}"
                     value="#{pageBeanName.myTableData}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" renderedIfEmpty="false">
       
            <t:column id="code_column1" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="code" forceId="true"  value="#{globalResources.code}"/>
                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.lineDTO.code.key}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.lineDTO.code.key}"/>
            </t:column>
            <t:column id="rightArcs_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="rightArcs" forceId="true"  value="#{integrationBundle.right_arc}"/>
                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.rightArcs}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.rightArcs}"/>
            </t:column>
            <t:column id="name_column1" sortable="false" width="50%">
                <f:facet name="header">
            <t:outputText id="name" forceId="true"  value="#{integrationBundle.linedetails}"/>

                </f:facet>
                
                  <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.lineDTO.name}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.lineDTO.name}"/>
            </t:column>
            <t:column id="leftArcs_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="leftArcs" forceId="true"  value="#{integrationBundle.left_arc}"/>
                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.leftArcs}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.leftArcs}"/>
            </t:column>
            <t:column id="jonconditionSign_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="jonconditionSign" forceId="true"  value="#{integrationBundle.join_cond}"/>
                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.jonconditionSign}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.jonconditionSign}"/>
            </t:column>
            <t:column id="actualValue_column1" sortable="false" width="25%">
                <f:facet name="header">
            <t:outputText id="actualValue" forceId="true"  value="#{integrationBundle.lineActualValue}"/>

                </f:facet>
                
                  <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{(list.actualLineValue != null) ? list.actualLineValue : resourcesBundle.noActualValue}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{(list.actualLineValue != null) ? list.actualLineValue : resourcesBundle.noActualValue}"/>
            </t:column>
            
            <t:column id="lineExcption_column1" sortable="false" width="10%">
                <f:facet name="header">
                 <t:outputText id="lineExcption" forceId="true"  value="#{integrationBundle.line_exception}"/>

                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{(list.lineDTO.exceptionStatus == 1) ? integrationBundle.valid : ''}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{(list.lineDTO.exceptionStatus == 1) ? integrationBundle.valid : ''}"/>
                
            </t:column>
            
            <t:column id="passCond_column1" sortable="false" width="17%">
                <f:facet name="header">
                 <t:outputText id="passCond" forceId="true"  value="#{integrationBundle.lineStatus}"/>

                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{(list.passAllConditions == 0) ? integrationBundle.ConditionsNotPassed:integrationBundle.ErrorCondtionNotFound}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{integrationBundle.ConditionsPassed}"/>
                
            </t:column>
          
            
          
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
    
    
</t:panelGrid>
