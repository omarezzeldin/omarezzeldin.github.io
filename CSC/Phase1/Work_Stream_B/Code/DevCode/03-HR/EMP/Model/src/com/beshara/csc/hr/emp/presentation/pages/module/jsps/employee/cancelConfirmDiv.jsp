<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="confirm_panel"  colspan="7">

<t:panelGrid columns="3"  width="100%">
    <h:outputText value="#{resourcesBundle.confirmCancelMsg}" styleClass="msg warning"/>
</t:panelGrid>
     <t:panelGroup forceId="true" id="divDeleteConfirm1_DIV2" styleClass="delDivScroll" >
                        <t:dataTable id="dataT_Warn_List_DIV2" var="list"
                                 value="#{pageBeanName.selectedPageDTO}" preserveDataModel="false"
                                 renderedIfEmpty="false" 
                                 footerClass="grid-footer" sortable="false"
                                 styleClass="grid-footer"
                                 headerClass="standardTable_Header"
                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                                 rowIndexVar="index">
                     
                            
                            <t:column sortable="false" width="40%" defaultSorted="false">
                                 <f:facet name="header">                                    
                                      
                                      <h:outputText id="civilidLabel" value="#{resourcesBundle.civilid}"/>
                                    
                                 </f:facet>
                                <h:outputText id="civilid" value="#{list.citizensResidentsDTO.code.key}"/>
                            </t:column>
                             
                            <t:column sortable="false" width="60%" defaultSorted="false">
                                 <f:facet name="header">                                    
                                      
                                      <h:outputText  id="nameLabel"  value="#{resourcesBundle.employeeName}"/>
                                    
                                 </f:facet>
                                <h:outputText id="nameId" value="#{list.citizensResidentsDTO.fullName}" />
                            </t:column>
                            
                        </t:dataTable>
                    </t:panelGroup>

    <t:panelGrid columns="3" align="center">
        <t:commandButton forceId="true" 
        id="saveAll" styleClass="cssButtonSmall" 
        value="#{globalResources.ok}" action="#{pageBeanName.cancelCandidate}"/>
        
        <t:commandButton forceId="true" 
        id="backButtonCustomDiv1" styleClass="cssButtonSmall" 
        value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);"/>
    </t:panelGrid>
    
</t:panelGroup>
