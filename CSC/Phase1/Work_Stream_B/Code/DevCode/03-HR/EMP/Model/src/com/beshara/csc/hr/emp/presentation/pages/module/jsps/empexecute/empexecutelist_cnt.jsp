<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="colCenter1,colCenter2" cellspacing="0" width="100%"
             align="center" columns="2" styleClass="divContent1Dynamic">
    <h:outputText value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="employees_hiretypes" value="#{pageBeanName.pageDTO.hireTypeKey}">
            <%--<f:selectItem itemValue="#{pageBeanName.virtualStringValue}" itemLabel="#{resourcesBundle.select}"/>--%>
            <f:selectItem itemValue="0" itemLabel="#{resourcesBundle.ALL_HIRE_TYPES}"/>
            <t:selectItems value="#{pageBeanName.firstLevelHireTypesList}" itemLabel="#{hireTypesList.name}"
                           itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>
            
            <a4j:support event="onchange" actionListener="#{pageBeanName.filterEmpByHireType}"
                         reRender="OperationBar,dataT_data_panel, paging_panel_group, searchButton, cancelSearchButton, divSearch"/>
            
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    </t:panelGroup>
</t:panelGrid>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">    
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" 
                    onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="OperationBar"/>
                </t:selectOneRadio>
            </t:column>
              
            <t:column id="citizensResidentsDTO-code-civilId_column"  width="15%">
                <f:facet name="header">                 
                    <t:commandLink id="sort_citizensResidentsDTO-code-civilId" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code-civilId') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0" />
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code-civilId') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_civilid" value="#{list.citizensResidentsDTO.code.civilId}"/>
            </t:column>
            
            <t:column id="citizensResidentsDTO-fullName_column" width="35%">
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.the_name}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>                
            </t:column>
            <!-- new added -->
         <t:column id="hireTypesDTO-name_column" width="25%">
            <f:facet name="header">
               
                <t:commandLink id="sort_hireTypesDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.hiretype}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="hireType_NoParent" value="#{list.hireTypesDTO.name}" rendered="#{list.hireTypesDTO.parentHireType == null}"/>
            <h:outputText id="hireType_Name" rendered="#{list.hireTypesDTO.parentHireType != null}"
                          value="#{list.hireTypesDTO.parentHireType.name} / #{list.hireTypesDTO.name}"/>
        </t:column>
            
             <!-- end added --> 
            
            <t:column id="ministryFileNo_column" width="10%">
                <f:facet name="header">                 
                    <t:commandLink id="sort_ministryFileNo" forceId="true" styleClass="headerLink" value="#{resourcesBundle.ministryFileNo}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_ministryFileNo" value="#{list.ministryFileNo}"/>
            </t:column>
            
            <t:column id="startWorkingDate_column" width="10%">
                <f:facet name="header">                 
                    <t:commandLink id="sort_startWorkingDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.hireDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='startWorkingDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='startWorkingDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_hireDate" value="#{list.hireDate}">
			<f:converter converterId="SqlDateConverter"/>
		</h:outputText>	
            </t:column>
            
        </t:dataTable>
        
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>        
    </t:panelGroup>
 <t:saveState value="#{pageBeanName.allList}"/>   
</t:panelGrid>
<f:verbatim>
<script type="text/javascript">
  setFocusAtMyFirstElement();

  function setFocusAtMyFirstElement() {
      document.getElementById('searchButton').focus();
  }
 
</script>
</f:verbatim>

 
<%--<f:verbatim>
    <script type="text/javascript">
        setFocusAtMyFirstElement();
        function setFocusAtMyFirstElement(){
            if(document.getElementById('searchButton') != null){            
                document.getElementById('searchButton').focus();                
            }
        }
    </script>
</f:verbatim>




<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel">
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{pageBeanName.noOfDataTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
                     
                     <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                      <h:selectOneRadio id="popup_radio_str" onclick="toggleRadio(this);" valueChangeListener="#{pageBeanName.radioBtnChecked}">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                      </h:selectOneRadio>
                    </t:column>
                    
                     <t:column id="civilid_column" sortable="false"  width="15%">
                        <f:facet name="header">                 
                            <t:commandLink id="sort_civilid" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='civilid') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='civilid') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
        
                        </f:facet>
                        <h:outputText id="content_civilid" value="#{list.citizensResidentsDTO.code.civilId}"/>
                    </t:column>
                    
                    <t:column id="name_column" sortable="false" width="35%">
                        <f:facet name="header">
                            <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                           
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
                    </t:column>  
                    
                    <t:column id="ministryFileNo_column" sortable="false"  width="25%">
                        <f:facet name="header">                 
                            <t:commandLink id="sort_ministryFileNo" forceId="true" styleClass="headerLink" value="#{resourcesBundle.ministryFileNo}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
        
                        </f:facet>
                        
                    </t:column>
                    
                    <t:column id="hireDate_column" sortable="false"  width="25%">
                        <f:facet name="header">                 
                            <t:commandLink id="sort_hireDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.hireDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
        
                        </f:facet>
                        <h:outputText id="content_hireDate" value="#{list.startWorkingDate}"/>                        
                    </t:column>
                    
                    
                      
        </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>            
    </t:panelGroup>
    
</t:panelGrid>
--%>
