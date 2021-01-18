<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" style="height:310px">
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
             <t:column id="check_column" styleClass="standardTable_HeaderPadding" width="5%" rendered="#{!pageBeanName.singleSelection}">
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
               
              <t:column id="radio_column" styleClass="standardTable_HeaderPadding" rendered="#{pageBeanName.singleSelection}">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem  itemLabel="" itemValue="#{list.code.key}"/>
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
              </t:column>
                
            <t:column id="actionDate_column" sortable="false" styleClass="standardTable_HeaderPadding" rendered="#{pageBeanName.indexArray[0]}">
                <f:facet name="header">
                    <t:commandLink id="sort_actionDate" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_actionDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='actionDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='actionDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="actionDate" value="#{list.actionDate}">
                    <f:converter converterId="TimeStampConverter"/>
                </h:outputText>
            </t:column>            
            <t:column id="trxTypesDTO-code-key_column" rendered="#{pageBeanName.indexArray[1]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_trxTypesDTO-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_trxTypesCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='trxTypesDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='trxTypesDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_trxTypesDTO-code-key"   value="#{list.trxTypesDTO.code.key}"/>
            </t:column>
            <t:column id="trxTypesDTO-name_column" rendered="#{pageBeanName.indexArray[2]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">

                    <t:commandLink id="sort_trxTypesDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_trxTypesName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='trxTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='trxTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="trxTypesDTO-name" value="#{list.trxTypesDTO.name}"/>
            </t:column>
            <t:column id="workCenterDTO-ministriesDTO-catsDTO-code-key_column" rendered="#{pageBeanName.indexArray[3]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-ministriesDTO-catsDTO-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_catCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-catsDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-catsDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterDTO-ministriesDTO-catsDTO-code-key" value="#{list.workCenterDTO.ministriesDTO.catsDTO.code.key}"/>
            </t:column>
            <t:column id="workCenterDTO-ministriesDTO-catsDTO-name_column" rendered="#{pageBeanName.indexArray[4]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-ministriesDTO-catsDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_catName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-catsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-catsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterDTO-ministriesDTO-catsDTO-name" value="#{list.workCenterDTO.ministriesDTO.catsDTO.name}"/>
            </t:column>
            <t:column id="workCenterDTO-ministriesDTO-code-key_column" rendered="#{pageBeanName.indexArray[5]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-ministriesDTO-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_minCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterDTO-ministriesDTO-code-key" value="#{list.workCenterDTO.ministriesDTO.code.key}"/>
            </t:column>
            <t:column id="workCenterDTO-ministriesDTO-name_column" rendered="#{pageBeanName.indexArray[6]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-ministriesDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_minName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterDTO-ministriesDTO-name" value="#{list.workCenterDTO.ministriesDTO.name}"/>
            </t:column>
            <t:column id="jobsDTO-code-key_column" rendered="#{pageBeanName.indexArray[7]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_jobsDTO-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_jobsCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_jobsDTO-code-key" value="#{list.jobsDTO.code.key}"/>
            </t:column>            
            <t:column id="pisJobCode_column" rendered="#{pageBeanName.indexArray[8]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_pisJobCode" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_jobsPisCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='pisJobCode') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='pisJobCode') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_pisJobCode" value="#{list.pisJobCode}"/>
            </t:column>
            <t:column id="jobsDTO-name_column" rendered="#{pageBeanName.indexArray[9]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_jobsDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_jobsName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="jobsDTO_name" value="#{list.jobsDTO.name}"/>
            </t:column>
            <t:column id="jobDetail_column" rendered="#{pageBeanName.indexArray[10]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_jobDetail" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_jobDetail}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobDetail') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobDetail') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="jobDetail" value="#{list.jobDetail}"/>
            </t:column>
            <t:column id="caderName_column" rendered="#{pageBeanName.indexArray[11]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-parentObject-parentObject-parentObject-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_caderName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-parentObject-parentObject-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-parentObject-parentObject-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="caderName" value="#{list.salElementGuidesDTO.parentObject.parentObject.parentObject.name}"/>
            </t:column>
            <t:column id="groupCode_column" rendered="#{pageBeanName.indexArray[12]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-parentObject-parentObject-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_groupCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-parentObject-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-parentObject-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="groupCode" value="#{list.salElementGuidesDTO.parentObject.parentObject.code.key}"/>
            </t:column>
            <t:column id="groupName_column" rendered="#{pageBeanName.indexArray[13]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-parentObject-parentObject-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_groupName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-parentObject-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-parentObject-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="groupName" value="#{list.salElementGuidesDTO.parentObject.parentObject.name}"/>
            </t:column>
            <t:column id="degreeCode_column" rendered="#{pageBeanName.indexArray[14]}"  sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-parentObject-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_degreeCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="degreeCode" value="#{list.salElementGuidesDTO.parentObject.code.key}"/>
            </t:column>
            <t:column id="degreeName_column" rendered="#{pageBeanName.indexArray[15]}"  sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-parentObject-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_degreeName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-parentObject-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="degreeName" value="#{list.salElementGuidesDTO.parentObject.name}"/>
            </t:column>
            <t:column id="workCenterDTO-code-key_column" rendered="#{pageBeanName.indexArray[16]}"  sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-code-key" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_workCenterCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterDTO-code-key" value="#{list.workCenterDTO.code.keys[1]}"/>
            </t:column>
            <t:column id="workCenterPisCode_column" rendered="#{pageBeanName.indexArray[17]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_pisWrkCode" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_workCenterPisCode}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='pisWrkCode') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='pisWrkCode') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterPisCode" value="#{list.pisWrkCode}"/>
            </t:column>
            <t:column id="workCenterDTO-name_column" rendered="#{pageBeanName.indexArray[18]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_workCenterName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="workCenterDTO-name" value="#{list.workCenterDTO.name}"/>
            </t:column>
            <t:column id="bgtProgramsDTO-name_column" rendered="#{pageBeanName.indexArray[19]}"  sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_bgtProgramsDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_bgtProgramsName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtProgramsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtProgramsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="bgtProgramsDTO-name" value="#{list.bgtProgramsDTO.name}"/>
            </t:column>
            <t:column id="bgtTypesDTO-name_column" rendered="#{pageBeanName.indexArray[20]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_bgtTypesDTO-name" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_bgtTypesName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="bgtTypesDTO-name" value="#{list.bgtTypesDTO.name}"/>
            </t:column>
            <t:column id="recordSource_column" rendered="#{pageBeanName.indexArray[21]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_recordSourceFlag" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_recordSource}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='recordSourceFlag') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='recordSourceFlag') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <t:outputText id="recordSourcePis-name" value="#{resourcesBundle.emp_internal_exp_recordSourcePis}" rendered="#{list.recordSourcePis}"/>
                <t:outputText id="recordSourceLegacy-name" value="#{resourcesBundle.emp_internal_exp_recordSourceLegacy}" rendered="#{list.recordSourceLegacy}"/>
                <t:outputText id="recordSourceCsc-name" value="#{resourcesBundle.emp_internal_exp_recordSourceCsc}" rendered="#{list.recordSourceCsc}"/>
            </t:column>
            
            <t:column id="revFlag_column" rendered="#{pageBeanName.indexArray[22]}" sortable="false" styleClass="standardTable_HeaderPadding">
                <f:facet name="header">
                    <t:commandLink id="sort_revFlag" forceId="true" styleClass="headerLink_NoRap" value="#{resourcesBundle.emp_internal_exp_revFlag}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='revFlag') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='revFlag') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:graphicImage id="revFlag" value="#{(list.revFlagBoolean) ? '/app/media/images/DataGrid_Icon_ok.gif' :'/app/media/images/DataGrid_Icon_not.gif'}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>
    
    </t:panelGroup>
    
</t:panelGrid>
