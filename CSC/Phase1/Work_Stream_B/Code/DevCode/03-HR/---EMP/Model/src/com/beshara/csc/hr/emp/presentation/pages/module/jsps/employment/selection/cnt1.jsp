<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGroup>

        <t:panelGrid columns="4" id="proceedCandidateContinerEmp" rowClasses="row02,row01" width="100%" border="0" cellpadding="3" cellspacing="0"
                     style="text-align:right">
            
            <%--<t:selectOneMenu id="candTypesList1" forceId="true" styleClass="DropdownboxxLarge2" style="width: 336px;"
                             value="#{pageBeanName.selectedStageId}"
                             rendered="#{pageBeanName.pageId == pageBeanName.pageIdFromCRS}">
                <f:selectItem itemValue="#{pageBeanName.stageIdCompletingJobName}"
                              itemLabel="#{resourcesBundle.completingJobName}"/>
                <f:selectItem itemValue="#{pageBeanName.stageIdRejectedByJobsArrangementDept}"
                              itemLabel="#{resourcesBundle.rejectedByJobsArrangementDept}"/>
                <f:selectItem itemValue="#{pageBeanName.stageIdRejectedByFatwaDept}"
                              itemLabel="#{resourcesBundle.rejectedByFatwaDept}"/>
                <a4j:support event="onchange" reRender="divMsg,dataT_data_panel, OperationBar, paging_panel"
                             action="#{pageBeanName.fillDataTableByStageId}" oncomplete="changeVisibilityMsg()"/>
            </t:selectOneMenu>--%>
            <%--<t:selectOneMenu id="candTypesList2" forceId="true" styleClass="DropdownboxxLarge2" style="width: 336px;"
                             value="#{pageBeanName.selectedStageId}"
                             rendered="#{pageBeanName.pageId == pageBeanName.pageIdJobNameAndFinDegreeAccepted}">
                <f:selectItem itemValue="#{pageBeanName.stageIdJobNameAccepted}"
                              itemLabel="#{resourcesBundle.jobNameAccepted}"/>
                <f:selectItem itemValue="#{pageBeanName.stageIdJobNameAndFinDegreeAccepted}"
                              itemLabel="#{resourcesBundle.jobNameAndFinDegreeAccepted}"/>
                <a4j:support event="onchange" reRender="divMsg,dataT_data_panel, OperationBar, paging_panel"
                             action="#{pageBeanName.fillDataTableByStageId}" oncomplete="changeVisibilityMsg()"/>
            </t:selectOneMenu>--%>
            <%--<t:inputText value="#{resourcesBundle.jobNameforRequired}" disabled="true" styleClass="textboxlarge" style="width: 336px;"
                         rendered="#{pageBeanName.pageId == pageBeanName.pageIdJobNameRequired}"/>--%>
            <%--<t:inputText value="#{resourcesBundle.finDegreeRequired}" disabled="true" styleClass="textboxlarge" style="width: 336px;"
                         rendered="#{pageBeanName.pageId == pageBeanName.pageIdFinDegreeRequired}"/>--%>
            
            <t:panelGroup colspan="2" >
            <h:outputText value="#{resourcesBundle.candStatus}" styleClass="lable01"/>
               <%--<t:panelGrid rowClasses="row01,row02" width="100%" border="0" style="text-align:right" cellpadding="0" cellspacing="0">
               <t:panelGrid columns="2" id="proceedCandidateContinerId" border="0" cellpadding="2" cellspacing="0" style="text-align:right">
                   <h:outputText value="#{resourcesBundle.candStatus}" styleClass="lable01"/>--%>
                 <t:selectOneMenu  id="candTypesList" forceId="true" styleClass="DropdownboxLargeB" style="margin-right:15px;" value="#{pageBeanName.selectedStageId}">
                   <f:selectItem itemValue="#{pageBeanName.hireSuggJobandDegree}" itemLabel="#{resourcesBundle.hireSuggJobandDegree}"/>
                   <f:selectItem itemValue="#{pageBeanName.acceptJobAndNameInPro}" itemLabel="#{resourcesBundle.acceptJobAndNameInPro}"/>
                   <f:selectItem itemValue="#{pageBeanName.hireStageJobNameAndFinDegree}" itemLabel="#{resourcesBundle.hireStageJobNameAndFinDegree}"/>
                   <f:selectItem itemValue="#{pageBeanName.rejectOrderToWorkMinistry}" itemLabel="#{resourcesBundle.rejectOrderToWorkMinistry}"/>
                   <a4j:support event="onchange" reRender="ministryList, listSize, dataT_data_panel, OperationBar, paging_panel" action="#{pageBeanName.fillDataTableByStageId}"/>
                </t:selectOneMenu>
               <%--</t:panelGrid>
               </t:panelGrid>--%>
            </t:panelGroup>
            
            
            
            <%--<h:outputText value="#{resourcesBundle.minOfCandidate}" styleClass="lable01"
                          rendered="#{pageBeanName.pageId == pageBeanName.pageIdFromCRS || pageBeanName.pageId == pageBeanName.pageIdJobNameAndFinDegreeAccepted}"/>--%>
            <%--<h:outputText value="#{resourcesBundle.employees_work_ministry}" styleClass="lable01"
                          rendered="#{pageBeanName.pageId == pageBeanName.pageIdJobNameRequired || pageBeanName.pageId == pageBeanName.pageIdFinDegreeRequired}"/>--%>
          <%--  <t:panelGroup colspan="2" />
            <h:outputText id="category_name" value="#{resourcesBundle.emp_internal_exp_catName}" styleClass="lable01"/>
            <t:panelGroup>
            <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text"
                         onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                         onblur="filterationInputOnBlur(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                <a4j:jsFunction name="changeCategoryVal" actionListener="#{pageBeanName.filterByCategory}"
                                oncomplete="resetMinistry();"
                                reRender="period,periodId,OperationBar,ministryListID,ministryId,listSize,myDataTableId,paging_panel_group"/>
            </t:inputText>
            <t:selectOneMenu id="categoryList" forceId="true" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedCategory}"
                             onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList"
                               itemValue="#{categoryList.code.key}" itemLabel="#{categoryList.name}"/>
                <a4j:jsFunction name="changeCategoryValD" actionListener="#{pageBeanName.filterByCategory}"
                                oncomplete="resetMinistry();"
                                reRender="OperationBar,ministryListID,ministryId,listSize,myDataTableId,paging_panel_group,errorCodePanelId,errorCodePanelId2"/>
            </t:selectOneMenu>
            <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
            </t:panelGroup>
            </t:panelGroup>
            <h:outputText id="ministry_name" value="#{resourcesBundle.emp_internal_exp_minName}" styleClass="lable01"/>
             <t:panelGroup>
             <t:inputText forceId="true" id="ministryId" styleClass="filteration_input_text"
                 disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                 onkeypress="filterationInputOnKeyPress(event,this,'ministryListID','errorCodeId2',changeMinistryVal);"
                 onblur="filterationInputOnBlur(event,this,'ministryListID','errorCodeId2',changeMinistryVal);"
                 onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"> <a4j:jsFunction name="changeMinistryVal"
                 actionListener="#{pageBeanName.filterByMinistry}" oncomplete="copyDropdownIntoInputtext('period',
                 'periodId');" reRender="period,periodId,OperationBar,listSize,myDataTableId,paging_panel_group"/>
                 </t:inputText>
            <t:selectOneMenu id="ministryListID" forceId="true" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedMinistry}"
                             disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.ministryList}" var="ministryList"
                               itemValue="#{ministryList.code.key}" itemLabel="#{ministryList.name}"/>
                <a4j:support event="onchange" reRender="divMsg,dataT_data_panel, OperationBar, paging_panel"
                             action="#{pageBeanName.fillDataTableByStageId}" oncomplete="changeVisibilityMsg()"/>
            </t:selectOneMenu>
            <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup forceId="true" id="errorCodePanelId2" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId2" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
            </t:panelGroup>
            </t:panelGroup>  --%>
            <%-- <t:selectOneMenu id="minsList" forceId="true" styleClass="DropdownboxXLarge"
                 value="#{pageBeanName.selectedMinistry}"> <t:selectItems value="#{pageBeanName.minstriesList}"
                 var="min" itemLabel="#{min.name}" itemValue="#{min.code.key}"/> <a4j:support event="onchange"
                 reRender="divMsg,dataT_data_panel, OperationBar, paging_panel"
                 action="#{pageBeanName.fillDataTableByStageId}" oncomplete="changeVisibilityMsg()"/> </t:selectOneMenu>--%>
        </t:panelGrid>

</t:panelGroup>
