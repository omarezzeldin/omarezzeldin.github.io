<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


<f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="integrateResources"/>    

<t:panelGroup id="conditionLinesDiv"  forceId="true"  >
<t:saveState value="#{pageBeanName.linesResultList}"/>
<t:outputText styleClass="msg" value="#{resourcesBundle.condition_lines_detail_div_title_add}" style="width:350px"/>
<htm:br/>

<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="linesDivStatus" styleClass="delDivScroll" style="width:600px;" >
        <t:dataTable id="dataT_linesStatus" var="list" value="#{pageBeanName.linesResultList}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top" rowIndexVar="index" dir="rtl">
           
            <%--<t:column sortable="false" width="5%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="code_line" value="#{integrateResources.divInfoConditionLine}"/>
                </f:facet>
                <h:outputText id="line_code_output" value="#{list.currentObject.code.key}"/>
            </t:column>--%>
            
            <t:column sortable="false" width="90%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name_line" value="#{integrateResources.divInfoConditionLine}"/>
                </f:facet>
                <h:outputText id="line_name_output" style="#{list.status == 'LineSuccess' ? 'color: #000000' : 'color: RED'}" value="#{list.currentObject.name}"/>
            </t:column>
            
           <%-- <t:column sortable="false" width="30%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="actual_line_value" value="#{integrateResources.Actual_Line_value}"/>
                </f:facet>
                 <h:outputText  id="actual_line_value_id" value="#{(list.currentObject.actualLineValue == 'null' || list.currentObject.actualLineValue == '-1') ? '-' : list.currentObject.actualLineValue}" /> 
            </t:column>--%>
            
             <t:column sortable="false" width="10%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="status_line" value="#{globalResources.Status}"/>
                </f:facet>
                 <h:outputText   value=""  styleClass="#{list.status == 'LineSuccess' ? 'iconOk' : 'iconNot'}"/> 
            </t:column>
            
            
        </t:dataTable>
    </t:panelGroup>
    
    <t:panelGrid align="center">
    <t:outputText value="#{resourcesBundle.condition_line_warning}"  styleClass="search_info_message" style="color:#A77B32;font-family:Tahoma;font-size:12px" />                   
    </t:panelGrid>
    
    <t:panelGrid align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="cancelLinesDivStatusDiv" onblur="document.getElementById('cancelLinesDivStatusDiv').focus();" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'null;','null');return false;" />
        <%--h:commandButton id="cancelconfirm" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
      
      
      
      
      
       

   
</t:panelGroup>




