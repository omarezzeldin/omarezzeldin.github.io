<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:saveState value="#{detailBeanName.fullColumnName}"/>
<t:saveState value="#{detailBeanName.sortAscending}"/>
<t:saveState value="#{detailBeanName.sorting}"/>
<t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
<t:panelGroup forceId="true" id="dataT_data_panel" style="height: 385px; width: 735px;">
    <t:inputHidden value="" forceId="true" id="virtualValue"/>
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" value="#{detailBeanName.currentDisplayDetails}"
                 rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                 sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}">
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                                 oncomplete="setAll('checkAll', 'chk2');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                             oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="Field_Name" width="10%" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_documentTypeDTO-code-key" forceId="true" styleClass="headerLink"
                               value="#{globalResources.Code}" actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-code-key') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.documentTypeDTO.code.key}"/>
        </t:column>
        <t:column id="typeTitle" width="19%" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_documentTypeDTO-name" forceId="true" styleClass="headerLink"
                               value="#{globalResources.name}" actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_tt" value="#{list.documentTypeDTO.name}"/>
        </t:column>
        <t:column id="citizenshiptype" width="18%">
            <f:facet name="header">
                <h:outputText id="citizenshitype" value="#{resourcesBundle.citizenshitype}"/>
            </f:facet>
            <t:selectOneMenu value="#{list.nationalityType}" forceId="true" styleClass="textboxsmall2"
                             id="nationalityType" converter="javax.faces.Long">
                <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue=""/>
                <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_SPECIFIED_ALL}"
                              itemValue="#{detailBeanName.nationality_NonSpecified}"/>
                <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_KUWAITI}"
                              itemValue="#{detailBeanName.nationality_Kuwaiti}"/>
                <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_KUWAITI}"
                              itemValue="#{detailBeanName.nationality_NonKuwaiti}"/>
                <%-- <t:selectItems value="#{pageBeanName.salaryType}" itemLabel="#{salaryType.name}"
                     itemValue="#{salaryType.code.key}" var="salaryType"/>--%>
            </t:selectOneMenu>
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <%-- c:compareValidator operator="not" componentToCompare="virtualValue"
                 componentToValidate="nationalityType" display="dynamic" errorMessage="#{globalResources.missingField}"
                 highlight="false" isArray="true" arraySize="#{documentTypeMaintain.currentListSize}" /--%>
            <c:requiredFieldValidator componentToValidate="nationalityType" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      uniqueId="nationalityTypeCvId" isArray="true"
                                      arraySize="#{documentTypeMaintain.currentListSize}"/>
        </t:column>
        <t:column id="gentype" width="18%">
            <f:facet name="header">
                <h:outputText id="header_type" value="#{resourcesBundle.type}"/>
            </f:facet>
            <t:selectOneMenu id="genderListAdd" forceId="true" value="#{list.genderType}" styleClass="textboxsmall2">
                <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue=""/>
                <%-- <t:selectItems var="genderList" itemLabel="#{genderList.name}" itemValue="#{genderList.code.key}"
                     value="#{detailBeanName.genderList}" />--%>
                <f:selectItem itemLabel="#{resourcesBundle.female}" itemValue="1"/>
                <f:selectItem itemLabel="#{resourcesBundle.male}" itemValue="2"/>
                <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="3"/>
            </t:selectOneMenu>
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <%-- c:compareValidator operator="not" componentToCompare="virtualValue" componentToValidate="genderListAdd"
                 display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" isArray="true"
                 arraySize="#{documentTypeMaintain.currentListSize}" /--%>
            <c:requiredFieldValidator componentToValidate="genderListAdd" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      uniqueId="genderListAddCvId" isArray="true"
                                      arraySize="#{documentTypeMaintain.currentListSize}"/>
        </t:column>
        <t:column id="required" width="10%">
            <f:facet name="header">
                <h:outputText id="header_required" value="#{resourcesBundle.document_required}"/>
            </f:facet>
            <t:selectBooleanCheckbox id="basicStatus" value="#{list.basicStatusBoolean}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedDocStatus}"
                             reRender="dataT_data_panel,dataT_data"/>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="attachment" width="10%">
            <f:facet name="header">
                <h:outputText id="header_attachment" value="#{resourcesBundle.attachment_required}"/>
            </f:facet>
            <t:selectBooleanCheckbox id="attachmentId" value="#{list.attachmentRequiredBoolean}"
                                     disabled="#{list.basicStatusBoolean==false}"></t:selectBooleanCheckbox>
        </t:column>
        <t:column id="statusx" width="10%">
            <f:facet name="header">
                <h:outputText id="header_statusx" value="#{resourcesBundle.active_status}"/>
            </f:facet>
            <t:selectBooleanCheckbox id="statusId"
                                     value="#{(pageBeanName.maintainMode == 0) ? detailBeanName.documentStatus : ((list.statusFlag==0) ? true:list.statusBoolean)}"
                                     disabled="#{(pageBeanName.maintainMode == 0) ? true : ((list.statusFlag==0) ? true:false)}"></t:selectBooleanCheckbox>
                                     
                <%--<t:selectBooleanCheckbox id="statusId" rendered="#{pageBeanName.maintainMode == 0}"
                                    value="#{ detailBeanName.documentStatus }"  disabled="true" 
                                    ></t:selectBooleanCheckbox>--%>  
                                    
                                    
                  <%--<t:selectBooleanCheckbox id="newstatusId" rendered="#{pageBeanName.maintainMode == 1}"
                                    value="#{list.statusBoolean}"
                                    ></t:selectBooleanCheckbox>--%>                      

                                     
        </t:column>
    </t:dataTable>
    <t:panelGrid rendered="#{detailBeanName.currentListSize == 0}" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
        <h:outputText value="#{globalResources.global_noTableResults_m2m}"/>
    </t:panelGrid>
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
