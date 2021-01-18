<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<htm:style >
    #customDiv2{top:70px !important;}
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="salCalcExceptionCustomDiv">
        <%-- rowOnDblClick="rowOnDblClickJs('chk',#{index});" by Ashraf Gaber
            <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" reRender="divDeleteAlert,OperationBar,divEditLookup" oncomplete="changeVisibilityDiv(window.blocker,window.lookupEditDiv);">
                <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
                <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
                <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
                <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
                <a4j:actionparam name="action" value="pageBeanName.showEditDiv"/>
            </a4j:jsFunction>
            --%>
            <%--binding="#{pageBeanName.myDataTable}"--%>
        <t:dataTable id="salCalcException_data" var="list" value="#{pageBeanName.calcExceptionsList}" 
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" 
                     rowOnMouseOver="javascript:addRowHighlight('myForm:salCalcException_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
             <%--<t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                  <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                    <f:attribute name="checkAll" value="true"/>
                    <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                 oncomplete="setAll('checkAll', 'chk');"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                  </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                  <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                               oncomplete="checkCheckAll('chk');"
                               reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectBooleanCheckbox>
              </t:column>
               
              <t:column id="radio_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.singleSelection}">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
              </t:column>
              --%>
               
                
             <%--<t:column id="code_column" sortable="false"  width="30%" >
                <f:facet name="header">
                  <h:outputText id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"/>   
                   t:commandLink  id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink

                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>--%>
            
            <t:column id="name_column" sortable="false" width="70%">
                <f:facet name="header">
                    <h:outputText id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}"/>   
                    <%--t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink--%>
                   
                </f:facet>
                <h:inputText id="content_name" value="#{list.name}"  readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
        </t:dataTable>
        
        <%--<t:panelGrid columns="1" rendered="#{ pageBeanName.calcExceptionsListSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>--%>
                         
    <t:panelGrid columns="1" border="0" align="center">
        <t:commandButton id="backButtonIntegrationDiv1" forceId="true" 
                         styleClass="cssButtonSmall" value="#{globalResources.back}" 
                         onclick="hideLookUpDiv(window.blocker,window.integrationDiv1);return false;">
        </t:commandButton>
                    <%--a4j:support disableDefault="true" event="onclick" 
                         oncomplete="hideLookUpDiv(window.blocker,window.customDiv2);return false;" /--%>

    </t:panelGrid>

    <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>


    
    </t:panelGroup>
    
    
    
</t:panelGrid>


    
