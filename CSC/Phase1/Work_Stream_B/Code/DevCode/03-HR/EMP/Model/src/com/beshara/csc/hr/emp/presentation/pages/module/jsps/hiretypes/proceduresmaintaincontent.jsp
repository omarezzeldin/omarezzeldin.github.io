<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
<t:saveState value="#{detailBeanName.fullColumnName}"/>
<t:saveState value="#{detailBeanName.sortAscending}"/>
<t:saveState value="#{detailBeanName.sorting}"/>
<t:panelGroup forceId="true" id="dataT_data_panel" style="height: 385px; width: 735px;">
<t:inputHidden value="" forceId="true" id="virtualValue" />
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" value="#{detailBeanName.currentDisplayDetails}" rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                 dir="#{shared_util.pageDirection}" preserveSort="true"
                 sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}">

        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}" oncomplete="setAll('checkAll', 'chk2');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
                <%-- <a4j:jsFunction name="checkTableFunction" actionListener="#{detailBeanName.selectedCurrent}"
                     oncomplete="updateButtonsStatusTable();updateMenuItemsStatusTable('myForm_myMenu_menu',myForm_myMenu_menu,'hbl');" reRender="divDeleteAlert,selectedNumber,searchMode" />--%>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="Field_Name" width="10%" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_hireProceduresDTO-code-key" forceId="true" styleClass="headerLink"
                               value="#{globalResources.Code}" actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-code-key') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.hireProceduresDTO.code.key}"/>
        </t:column>
        <t:column id="typeTitle" width="25%" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_hireProceduresDTO-name" forceId="true" styleClass="headerLink"
                               value="#{globalResources.name}" actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_tt" value="#{list.hireProceduresDTO.name}"/>
        </t:column>
        <t:column id="citizenshiptype" width="25%">
            <f:facet name="header">
                <%--t:commandSortHeader id="citizenshiptypeTitleName" columnName="name3" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow1" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow2" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="citizenshitype" value="#{resourcesBundle.citizenshitype}"/>
                </t:commandSortHeader--%>
                <h:outputText id="citizenshitype" value="#{resourcesBundle.citizenshitype}"/>
            </f:facet>
            <t:selectOneMenu  value="#{list.nationalityType}" forceId="true" styleClass="textboxsmall2" id="nationalityType" converter="javax.faces.Long">
                <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue="" />
                <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_SPECIFIED_ALL}" itemValue="#{detailBeanName.nationality_NonSpecified}"/>
               <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_KUWAITI}" itemValue="#{detailBeanName.nationality_Kuwaiti}" />
               <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_KUWAITI}" itemValue="#{detailBeanName.nationality_NonKuwaiti}" />
                <%--<t:selectItems value="#{pageBeanName.salaryType}" itemLabel="#{salaryType.name}" itemValue="#{salaryType.code.key}" var="salaryType"/>--%>
            </t:selectOneMenu>
             <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim><br/></f:verbatim>
            <%--c:compareValidator operator="not" componentToCompare="virtualValue" componentToValidate="nationalityType"  display="dynamic"   errorMessage="#{globalResources.missingField}"   highlight="false"  isArray="true"  arraySize="#{documentTypeMaintain.currentListSize}" /--%>
            <c:requiredFieldValidator componentToValidate="nationalityType" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="nationalityTypeCvId" isArray="true" arraySize="#{documentTypeMaintain.currentListSize}"/>
        </t:column>
        <t:column id="gentype" width="25%">
            <f:facet name="header">
                <%--t:commandSortHeader id="typeName" columnName="name4" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow1" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow2" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_type" value="#{resourcesBundle.type}"/>
                </t:commandSortHeader--%>
                <h:outputText id="header_type" value="#{resourcesBundle.type}"/>
            </f:facet>
            
            <t:selectOneMenu id="genderListAdd" forceId="true"  value="#{list.genderType}"  styleClass="textboxsmall2"  >
                <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue=""  itemDisabled="true"/>
                <%--<t:selectItems var="genderList" itemLabel="#{genderList.name}" itemValue="#{genderList.code.key}"  value="#{detailBeanName.genderList}" />--%>
                
                  <f:selectItem itemLabel="#{resourcesBundle.female}" itemValue="1"   />
                 <f:selectItem itemLabel="#{resourcesBundle.male}" itemValue="2" />
                 <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="3" />
             </t:selectOneMenu>
             <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
             <f:verbatim><br/></f:verbatim>
            <%--c:compareValidator operator="not" componentToCompare="virtualValue" componentToValidate="genderListAdd"  display="dynamic"   errorMessage="#{globalResources.missingField}"   highlight="false"  isArray="true"  arraySize="#{documentTypeMaintain.currentListSize}" /--%>
            <c:requiredFieldValidator componentToValidate="genderListAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="genderListAddCvId1" isArray="true" arraySize="#{documentTypeMaintain.currentListSize}"/>
        </t:column>
        <t:column id="statusx" width="20%">
            <f:facet name="header">
                <%--t:commandSortHeader id="statusName" columnName="name5" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow1" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow2" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_status" value="#{resourcesBundle.field_required}"/>
                </t:commandSortHeader--%>
                <h:outputText id="header_status" value="#{resourcesBundle.active_status}"/>
            </f:facet>
            <t:selectBooleanCheckbox id="statusId" value="#{(pageBeanName.maintainMode == 0) ? detailBeanName.procedureStatus : list.statusBoolean}" disabled="#{pageBeanName.maintainMode == 0}"></t:selectBooleanCheckbox>
        </t:column>
        
    </t:dataTable>
    <t:panelGrid rendered="#{detailBeanName.currentListSize == 0}" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
        <h:outputText value="#{globalResources.global_noTableResults_m2m}"/>
    </t:panelGrid>
</t:panelGroup>

