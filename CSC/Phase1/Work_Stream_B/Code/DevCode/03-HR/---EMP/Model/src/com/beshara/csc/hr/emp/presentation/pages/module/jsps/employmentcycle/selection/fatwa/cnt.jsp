<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
 <htm:style> .dataT-With-4-row-filter {
	width: 874px !important;
}
     </htm:style>
<t:panelGroup id="dataT_data_panel" forceId="true" styleClass="dataT-With-4-row-filter"  >
    
    <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
         forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
         rowClasses="standardTable_Row1,standardTable_Row2"  style="width: 1130 !important;"
         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
         align="top" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
         
        <t:column id="check_column" styleClass="standardTable_Header3" width="30">
            <f:facet name="header"/>        
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="divDeleteAlert,OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        
        <t:column id="code_column" width="50">
            <f:facet name="header">
                <t:commandLink id="sort_citizensResidentsDTO-code-key" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_CIVIL_ID}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.citizensResidentsDTO.code.key}"/>
        </t:column>
        
        <t:column id="name_column" width="170">
            <f:facet name="header">
                <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.candidate_name}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_FULL_NAME}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
        </t:column>
        
        <t:column id="extraDataValue_column" width="50">
             <f:facet name="header">
                <t:commandLink id="sort_extraDataValue" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.transfer_to_dewan_Date}" actionListener="#{pageBeanName.sortDataTable}">
                    <f:param name="bsnSortingColumnName" value="dVALUE"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='extraDataValue') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='extraDataValue') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_extraDataValue"
                          value="#{list.extraDataValue}"/>
        </t:column>
        
             <t:column id="transReqDate_column" width="50">
             <f:facet name="header">
                <t:commandLink id="sort_transReqDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.trans_req_date}" actionListener="#{pageBeanName.sortDataTable}">
                    <f:param name="bsnSortingColumnName" value="C.TRANS_REQ_DATE"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='transReqDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='transReqDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_transReqDate"
                          value="#{list.transReqDate}">
                          <f:converter converterId="SqlDateConverter"/>
                  </h:outputText>        
        </t:column>
         <t:column id="minstry_Name_column" width="170">
            <f:facet name="header">
                <t:commandLink id="sort_workCentersDTO-ministriesDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.candidate_min_name}" actionListener="#{pageBeanName.sortDataTable}">
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.ministryWokCenterName}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
             <h:outputText id="ministry_Name" value="#{list.workCentersDTO.ministriesDTO.name}" />
        </t:column>

        
        <t:column id="lastPersonQualification_column" width="300">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.qualificationName}" id="lpqc"/>
            </f:facet>
            <h:outputText id="content_lastPersonQualification" value="#{pageBeanName.lastPersonQualification.qualificationsDTO.name}"/>
        </t:column>
        
        
        
           <t:column id="hasRevisionFatwa_column" width="130" rendered="#{pageBeanName.approveBtnShow}">
         <f:facet name="header">
                <t:commandLink id="sort_hasRevisionFatwa" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.orderType}" actionListener="#{pageBeanName.sortDataTable}">
                    <f:param name="bsnSortingColumnName" value="hasRevisionFatwa"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hasRevisionFatwa') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hasRevisionFatwa') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_hasRevision" value="#{resourcesBundle.order_viewed}" rendered="#{list.hasRevisionFatwa == true}"/>
            <h:outputText id="content_hasnotRevision" value="#{resourcesBundle.order_not_viewed}" rendered="#{list.hasRevisionFatwa == false}"/>
        </t:column>
      
      
        <t:column id="userNameValue_column" width="150" rendered="#{pageBeanName.approveBtnShow}" >
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.user_name}" id="userNameValuec"/>
            </f:facet>
            <h:outputText id="content_userNameValue" value="#{list.userNameValueFatwa}"/>
        </t:column>
        
       
        
        <t:column id="exp_column" width="30">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.has_experience}" id="expcc" />
            </f:facet>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_ok.gif" width="16" height="16" rendered="#{list.hasExperience}"/>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_not.gif" width="16" height="16" rendered="#{!list.hasExperience}"/>
        </t:column>
    </t:dataTable>
    
    <h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </h:panelGrid>
    
</t:panelGroup>
