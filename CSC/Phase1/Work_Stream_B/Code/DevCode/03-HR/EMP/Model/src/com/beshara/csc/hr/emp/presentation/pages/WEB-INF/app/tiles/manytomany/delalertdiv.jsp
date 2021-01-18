<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


               <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
              
               <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
                        <t:dataTable id="dataT_delete" var="list" value="#{detailBeanName.selectedCurrentDetails}"     
                             rowIndexVar="index"
                             renderedIfEmpty="false"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header" 
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                             width="100%" align="center" dir="#{shared_util.pageDirection}"  >
                        
                   
                    <t:column id="code_column"  width="20%">
                        <f:facet name="header">
                            
                                <h:outputText id="header_code" value="#{globalResources.Code}"/>
                            
                        </f:facet>
                       <h:outputText id="lck1" value="#{list.code.keys[1]}" rendered="#{list.code.keys[1]!=null}"/>
                       <h:outputText id="lck" value="#{list.code.key}" rendered="#{list.code.keys[1]==null}"/>
                    </t:column>
                    
                    <t:column id="name_column"  width="75%">
                        <f:facet name="header">
                           
                                <h:outputText id="header_name" value="#{globalResources.name}"/>
                          
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.name}"/>
                    </t:column>
                </t:dataTable>

     
                    </t:panelGroup>
                    <%-- Start css modification add two break line to UI --%>
                    <f:verbatim>
                    <br/>
                   </f:verbatim>
                   <%-- End css modification add two break line to UI --%>
                  <t:commandButton forceId="true" id="OkButtonDelAlertDiv" onblur="if(isVisibleComponent('delAlert'))document.getElementById('CancelButtonDelAlertDiv').focus();"  value="#{globalResources.ok}" action="#{detailBeanName.delete}" styleClass="cssButtonSmall"  onclick="ignoreClientSideValidation();" /> <f:verbatim>&nbsp; </f:verbatim>
                  <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" onblur="if(isVisibleComponent('delAlert'))document.getElementById('OkButtonDelAlertDiv').focus();" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall" />
