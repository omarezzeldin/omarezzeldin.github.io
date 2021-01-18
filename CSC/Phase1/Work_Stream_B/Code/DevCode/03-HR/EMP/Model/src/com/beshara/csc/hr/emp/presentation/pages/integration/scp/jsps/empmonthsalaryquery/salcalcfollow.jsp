<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/> 
 
 <t:panelGroup forceId="true" id="custom3div" styleClass="delDivScroll">
 
 <t:dataTable forceId="true" id="scf" var="list"
                                             value="#{pageBeanName.salMonTrxList}" rowIndexVar="index"
                                             renderedIfEmpty="false"
                                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');"
                                             footerClass="grid-footer" styleClass="grid-footer"
                                             headerClass="standardTable_Header"
                                             rowClasses="standardTable_Row1,standardTable_Row2" width="100%"
                                             align="center" dir="#{shared_util.pageDirection}"
                                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column">
                               
                                   
                                     <t:column id="trxdate" width="10%" style="background:#{list.statusFlag==1? '#b3ffb3' : ''}">
                                        <f:facet name="header">
                                            <h:outputText id="header_mdate" value="#{resourcesBundle.outTxtDate}"/>
                                        </f:facet>
                                        <h:outputText id="content_mdate" value="#{list.trxDateString}" />
                                    </t:column>
                                    
                                    
                                  
                                  
                                            <t:column id="old_status" width="10%" style="background:#{list.statusFlag==1? '#b3ffb3' : ''}">
                                        <f:facet name="header">
                                            <h:outputText id="header_oldstatus" value="#{resourcesBundle.old_status}"/>
                                        </f:facet>
                                        <h:outputText id="contentoldstatus" value="#{list.oldPayStatusCodeDTO.paystatusName}"/>
                                    </t:column>
                                    
                                    
                                            <t:column id="new_status" width="10%" style="background:#{list.statusFlag==1? '#b3ffb3' : ''}">
                                        <f:facet name="header">
                                            <h:outputText id="header_mstatus" value="#{resourcesBundle.new_status}"/>
                                        </f:facet>
                                        <h:outputText id="contentnewstatus" value="#{list.newPayStatusCodeDTO.paystatusName}" />
                                    </t:column>
                                   
                                   
                                    
                                     <t:column id="user_name" width="8%" style="background:#{list.statusFlag==1? '#b3ffb3' : ''}">
                                        <f:facet name="header">
                                            <h:outputText id="header_muname" value="#{resourcesBundle.user_name}"/>
                                        </f:facet>
                                        <h:outputText id="content_user_name" value="#{list.userName}" />
                                    </t:column>
                                    
                                <t:column id="civilid" width="8%" style="background:#{list.statusFlag==1? '#b3ffb3' : ''}">
                                        <f:facet name="header">
                                            <h:outputText id="header_mcivil" value="#{resourcesBundle.CivilID}"/>
                                        </f:facet>
                                        <h:outputText id="content_mcivil" value="#{list.civilID}" />
                                    </t:column>
                         <t:column id="rname" width="15%" style="background:#{list.statusFlag==1? '#b3ffb3' : ''}">
                                        <f:facet name="header">
                                            <h:outputText id="header_rname" value="#{resourcesBundle.user_real_name}"/>
                                        </f:facet>
                                        <h:outputText id="content_rname" value="#{list.userRealName}" />
                                    </t:column>
</t:dataTable>
 <t:panelGrid columns="1" rendered="#{empty pageBeanName.salMonTrxList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>


</t:panelGroup>
  <t:commandButton id="backButtonCustomDiv3" forceId="true" type="button" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv3);"></t:commandButton>
