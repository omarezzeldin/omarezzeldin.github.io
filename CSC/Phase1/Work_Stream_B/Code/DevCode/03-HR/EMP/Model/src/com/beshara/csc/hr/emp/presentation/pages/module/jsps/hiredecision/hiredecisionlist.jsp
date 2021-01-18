<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{pageBeanName.allList}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.citizensResidentsDTO.civilId}" rows="#{shared_util.noOfTableRows}"
                     rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
            <%--<t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAllCandidate" value="#{pageBeanName.checkAllFlag}">
                        <f:attribute name="checkAllCandidate" value="true"/>
                        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                     oncomplete="setAllForCand('checkAllCandidate', 'chk');"
                                     reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                 oncomplete="checkCheckAll('chk');"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>--%>
            <t:column id="check_column" styleClass="standardTable_Header3" width="7%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" 
                    onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="civilId_column" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-civilId" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-civilId') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.citizensResidentsDTO.civilId}"/>
            </t:column>
            <t:column id="citizensResidentsDTO-fullName_column" width="33%">
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.candidate_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
            </t:column>
            <t:column id="hireTypesDTO-name_column" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_hireTypesDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hiretype}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="hireType_NoParent" value="#{list.hireTypesDTO.name}"
                              rendered="#{list.hireTypesDTO.parentHireType == null}"/>
                <h:outputText id="hireType_Name" rendered="#{list.hireTypesDTO.parentHireType != null}"
                              value="#{list.hireTypesDTO.parentHireType.name} / #{list.hireTypesDTO.name}"/>
            </t:column>
            <%--<t:column id="hireDate_column" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_hireDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hireDate}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_hireDate" value="#{list.hireDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>--%>
            <t:column id="hireStage_column" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_hireStagesDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.procedureStatus}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireStagesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireStagesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_hireStage" value="#{list.hireStagesDTO.name}"></h:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    </t:panelGroup>
</t:panelGrid>


<script type="text/javascript">


function setAllForCand(checkAllId, arrayId) {

    var object;
    var pageIndex;
    var noOfTableRows;
    if(document.getElementById('checkAllCandidate') != null)
       object = document.getElementById('checkAllCandidate');
       
     if(document.getElementById('arrayId')!=null)  
      document.getElementById('arrayId').value = arrayId;
      
     if(document.getElementById('listSize')!=null)
      pageIndex = document.getElementById('listSize').value;
      
      if(document.getElementById('noOfTableRows')!=null)
      noOfTableRows = document.getElementById('noOfTableRows').value;
      
    if(document.getElementById('pageIndex') != null)
        pageIndex = document.getElementById('pageIndex').value;
//    alert(" setAll pageIndex  = "+pageIndex+" , arrayId  = "+
//            arrayId+" , noOfTableRows = "+noOfTableRows + " , object = "+object);
    
    for (i = 0; ; i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            } else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
            } else {
                elem.checked = object.checked;
                //setSelected(elem, arrayId);
            }
        }
    }
}


</script>
