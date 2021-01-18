<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
    <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" status="notdefined"
                    reRender="divDeleteAlert,OperationBar,divEditLookup"
                    oncomplete="javascript:FireButton('editButton');">
        <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
        <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
        <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
        <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    </a4j:jsFunction>
    <t:dataTable id="dataT_data" forceId="true" var="list"
                 value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                 rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                 rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});" renderedIfEmpty="false"
                 binding="#{pageBeanName.myDataTable}" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                 footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                 dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                 sortAscending="#{pageBeanName.ascending}">
        <%-- t:column id="check_column" styleClass="standardTable_Header3" width="5%"> <f:facet name="header">
             <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}"> <f:attribute
             name="checkAll" value="true"/> <f:attribute name="listSize" value="#{pageBeanName.listSize}"/> <a4j:support
             event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}" oncomplete="setAll('checkAll',
             'chk');" reRender="OperationBar"/> </t:selectBooleanCheckbox> </f:facet> <t:selectBooleanCheckbox
             forceId="true" id="chk" value="#{list.checked}"> <a4j:support event="onclick"
             actionListener="#{pageBeanName.selectedCheckboxs}" oncomplete="checkCheckAll('chk');"
             reRender="OperationBar"/> </t:selectBooleanCheckbox> </t:column--%>
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header"/>
            <t:selectOneRadio id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);"
                              onkeyup="toggleRadioKeyUp(this);" styleClass="radioButton_DataTable">
                <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                             reRender="OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        <t:column id="citizensResidentsDTO-code_column" width="10%">
            <f:facet name="header">
                <%-- t:commandSortHeader id="codeColumn" columnName="code" arrow="false"
                     styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                     id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                     </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                     value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                     <h:outputText id="header_code" value="#{resourcesBundle.civilid}"/> </t:commandSortHeader--%>
                <t:commandLink id="sort_citizensResidentsDTO-code" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">
                   <f:param name="bsnSortingColumnName" value="C.CIVIL_ID"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.citizensResidentsDTO.code.key}"/>
        </t:column>
        <t:column id="citizensResidentsDTO-fullName_column" width="25%">
            <f:facet name="header">
                <%-- t:commandSortHeader id="name_sort" columnName="name" arrow="false"
                     styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                     id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                     </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                     value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                     <h:outputText id="header_name" value="#{resourcesBundle.name}"/> </t:commandSortHeader--%>
                <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.candidate_name}" actionListener="#{pageBeanName.sortDataTable}">
                               
                                <f:param name="bsnSortingColumnName" value="GET_NAME (C.CIVIL_ID)"/>  
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
        </t:column>
        <t:column id="hireStagesDTO-name_column" width="25%" >
            <f:facet name="header">
                <t:commandLink id="sort_hireStagesDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.hireStage_cand}" actionListener="#{pageBeanName.sortDataTable}">
                             <f:param name="bsnSortingColumnName" value="C.HIRSTAGE_CODE"/>  
                               
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireStagesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireStagesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_hireStagesname" value="#{list.hireStagesDTO.name}"  style="color: #ff0000;"/>
        </t:column>
        <t:column id="hireTypesDTO-name_column" width="15%">
            <f:facet name="header">
                <t:commandLink id="sort_hireTypesDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.hiretype}" actionListener="#{pageBeanName.sortDataTable}">
                               
                               <f:param name="bsnSortingColumnName" value="C.HIRTYPE_CODE"/>
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
      
        <t:column id="documentsStatus_column" width="10%">
            <f:facet name="header">
            
                <t:outputText id="documentsStatus" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.document_type_status}" >
                </t:outputText>
            </f:facet>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_green_flag.gif" 
                            onmouseover="doTooltip(event,'#{resourcesBundle.completed}') "
                            onmouseout="hideTip()"
                            rendered="#{list.documentsStatus == true}" border="0"/>
                            
            <t:graphicImage value="/app/media/images/DataGrid_Icon_red_flag.gif"
                                onmouseover="doTooltip(event,'#{resourcesBundle.not_completed}') "
                                onmouseout="hideTip()"
                                rendered="#{list.documentsStatus == false }" border="0"/>
            <h:inputHidden value="#{list.documentsStatus}"/>
        </t:column>
        <t:column id="proceduresStatus_column" width="10%">
            <f:facet name="header">
                <t:outputText id="proceduresStatus" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.resultStatus}" >
                </t:outputText>
            </f:facet>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_green_flag.gif" 
                            onmouseover="doTooltip(event,'#{resourcesBundle.completed}') "
                            onmouseout="hideTip()"
                            rendered="#{list.proceduresStatus == true}" border="0"/>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_red_flag.gif"
                                onmouseover="doTooltip(event,'#{resourcesBundle.not_completed}') "
                                onmouseout="hideTip()"
                                rendered="#{list.proceduresStatus == false }" border="0"/>
            <h:inputHidden value="#{list.proceduresStatus}"/>
        </t:column>
        <t:column id="exp_column" width="30">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.has_experience}" id="expcc"/>
            </f:facet>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_ok.gif" width="16" height="16"
                            rendered="#{list.hasExperience}"/>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_not.gif" width="16" height="16"
                            rendered="#{!list.hasExperience}"/>
        </t:column>
    </t:dataTable>
    <h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
        <%-- <h:outputText value="#{resourcesBundle.chooseOneHireType}" />--%>
    </h:panelGrid>
</t:panelGroup>
<f:verbatim>
    <script type="text/javascript">
      setFocusAtMyFirstElement();

      function setFocusAtMyFirstElement() {
          if (document.getElementById('addButton') != null) {
              document.getElementById('addButton').focus();
          }
      }
    </script>
</f:verbatim>