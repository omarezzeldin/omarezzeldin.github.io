
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<jsp:include page="/integration/org/jsps/ministry/searchministrieswithpagingdiv.jsp"/>
<t:panelGrid id="cnt1PnlGrid" forceId="true" columns="4" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">

    <%--<t:outputText id="requestcreator-content_ministrytype" value="#{integrationBundle.type}"  styleClass="lable01 nowrap_txt" />--%>
        <%--<t:panelGroup colspan="5">
        <t:selectOneMenu id="minType" value="#{pageBeanName.selectedTypeId}"  >
            <t:selectItems id="requestcreator-content_selectministrytype"
                   value="#{pageBeanName.banksTypeList}" var="list" itemLabel="#{list.name}"
                   itemValue="#{list.code.keys[0]}"/>
          --%><%--f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/--%><%--
          --%><%--<f:selectItem id="requestcreator-content_selectministrytype1"
                        itemLabel="#{integrationBundle.governmental}" itemValue="#{pageBeanName.govFlag}"/>--%><%--
          --%><%--<f:selectItem id="requestcreator-content_selectministrytype2"
                        itemLabel="#{integrationBundle.nongovernmental}" itemValue="#{pageBeanName.nonGovFlag}"/>--%><%--
          <a4j:support event="onchange" actionListener="#{pageBeanName.updateCategories}" 
          reRender="catPanelID,cats_combo,scriptGeneratorPanel,OperationBar,searchPanelId" oncomplete="clearInputText('categoryFilterationId');clearInputText('minFilterationId');clearInputText('ministryName');" />
        </t:selectOneMenu>
    </t:panelGroup>--%>
    
        <t:outputText id="category_Label" value="#{integrationBundle.category}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="catPanelID">
        <t:inputText forceId="true" id="categoryFilterationId" styleClass="filteration_input_text"
                     onkeypress="filterationInputOnKeyPress(event,this,'cats_combo','errorCodeId1',changeCategory,null);"
                     onblur="filterationInputOnBlur(event,this,'cats_combo','errorCodeId1',changeCategory,null);" 
                     onkeyup="enabelIntegerOnly(this);">
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true"
                         onchange="filterationDropboxOnChange(event,this,'categoryFilterationId','errorCodeId1',changeCategory,null);"
                         id="cats_combo" value="#{pageBeanName.selectedCatId}"
                         styleClass="textbox_175" converter="javax.faces.Long">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="cat" itemLabel="#{cat.name}" itemValue="#{cat.code.keys[0]}"
                           value="#{pageBeanName.categories}"/>
            <a4j:jsFunction name="changeCategory" actionListener="#{pageBeanName.clearMinData}"
                            reRender="ministryNamePanelId,catPanelID,scriptGeneratorPanel,OperationBar,searchPanelId,radioPanel2,errorCodePanelId2,btnsPnl" 
                            oncomplete="clearInputText('minFilterationId'); clearInputText('ministryName');"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim><br/></f:verbatim>
        <t:panelGroup  forceId = "true" id="errorCodePanelId1">
            <t:outputText id="errorCodeId1" value="#{integrationBundle.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="display:none;color: #FF0000;"/>
             <c:compareValidator componentToValidate="cats_combo" componentToCompare="virtualvalueId" operator="not" 
                            errorMessage="#{integrationBundle.select_one_item}" highlight="false" display="dynamic"/>
        </t:panelGroup>
       
    </t:panelGroup>
    
    <t:outputText id="min_Label" value="#{integrationBundle.ministry}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="searchPanelId">


            <t:inputText forceId="true" id="minFilterationId" styleClass="filteration_input_text"
                         onkeypress="filterDivInputOnKeyPress(event,this,changeMinistry,'searchBtn');"
                         onblur="filterDivInput('minFilterationId',changeMinistry,'searchBtn');"
                         
                         onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedMiniId}">
                         <%--onmouseout="filterDivInput(this,changeMinistry,'searchBtn');"--%>
                <a4j:jsFunction name="changeMinistry" actionListener="#{pageBeanName.changeMinistry}"
                                reRender="radioPanel2,ministryNamePanelId, OperationBar,ministryName,errorCodePanelId2,btnsPnl"/>
            </t:inputText>
        
        <t:panelGroup forceId = "true" id="ministryNamePanelId">
            <t:inputText forceId="true" id="ministryName" disabled="true" styleClass="textboxmedium" value="#{pageBeanName.selectedMiniName}"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>       
            <a4j:commandButton id="searchMinistryBtn" value="..." action="#{pageBeanName.openSearchMinistryDiv}" styleClass="cssButtonSmall" 
                disabled="#{pageBeanName.selectedCatId == null || pageBeanName.selectedCatId == pageBeanName.virtualLongValue}" 
                reRender="searchMinistryDiv,btnsPnl,OperationBar" 
                oncomplete="changeVisibilityDiv(window.blocker,window.searchMinistryDiv);"/>        
            <f:verbatim><br/></f:verbatim>
            <c:requiredFieldValidator componentToValidate="ministryName" 
                                      errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>
        </t:panelGroup>
     
        <t:panelGroup  forceId = "true" id="errorCodePanelId2">
            <f:verbatim rendered="#{pageBeanName.wrongMinCode}"><br/></f:verbatim>
            <t:outputText id="errorCodeId2" value="#{integrationBundle.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="color: #FF0000;" rendered="#{pageBeanName.wrongMinCode}"/>
        </t:panelGroup>        
        
    </t:panelGroup>
    
    <%--<t:panelGroup>
        <t:inputCalendar forceId="true" id="clndr_From" title="#{globalResources.inputCalendarHelpText}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         value="#{pageBeanName.salaryDate}" size="20" maxlength="200" styleClass="textboxsmall2"
                         currentDayCellClass="currentDayCell" renderAsPopup="true" renderPopupButtonAsImage="true"
                         align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="clndr_From" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false" />
        <c:dateFormatValidator componentToValidate="clndr_From" display="dynamic"
                             errormessage="#{globalResources.messageErrorForAdding}" highlight="false"
                            />
    </t:panelGroup>--%>
     <t:outputText id="monthText" forceId="true" value="#{integrationBundle2.month}" styleClass="lable01" />
        <t:panelGroup id="monthMenuPnl" forceId="true">
            <t:selectOneMenu forceId="true" id="monthMenu" styleClass="Dropdownbox" value="#{pageBeanName.month}" >
                <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                               var="months"/>               
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        
        <t:outputText id="yearText" forceId="true" value="#{integrationBundle2.year}" styleClass="lable01" />
        <t:panelGroup id="yearMenuPnl" forceId="true">
            <t:selectOneMenu forceId="true" id="yearMenu" styleClass="Dropdownbox" value="#{pageBeanName.year}"
                             onblur="setFousAtNextAfterComboItem();">
                <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                               var="years"/>             
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        
  
    <t:panelGroup forceId="true" id="radioPanel"  dir="#{shared_util.pageDirection}" colspan="4">
        <t:panelGrid align="center">
            <t:selectOneRadio  forceId="true" id="radioBtn" value="#{pageBeanName.fileTypeRadio}">
                <f:selectItem   itemLabel="#{integrationBundle2.main_data_only_title}" itemValue="0"/>
                <f:selectItem itemLabel="#{integrationBundle2.detailed_data_title}" itemValue="1"/>
                <f:selectItem itemLabel="#{integrationBundle2.detailed_mer_ded_title}" itemValue="2"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.radioButtonChanged}" reRender="btnsPnl,radioPanel2"/>
            </t:selectOneRadio>     
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="radioPanel2"  dir="#{shared_util.pageDirection}" colspan="4">
        <t:panelGrid align="center" forceId="true" id="graditionFileTypeRadio_pnl" rendered="#{pageBeanName.showGraditionFileTypeRadio}">
            <t:selectOneRadio  forceId="true" id="graditionFileTypeRadio_radioBtn" value="#{pageBeanName.graditionFileTypeRadio}">
                <f:selectItem itemLabel="#{integrationBundle2.actual_from_payment_title}" itemValue="1"/>
                <f:selectItem itemLabel="#{integrationBundle2.without_settelment_title}" itemValue="2"/>
                <%--<a4j:support event="onclick" actionListener="#{pageBeanName.radioButtonChanged}" reRender="btnsPnl,radioPanel"/>--%>
            </t:selectOneRadio>     
        </t:panelGrid>
    </t:panelGroup>
    
</t:panelGrid>
<t:panelGroup id="btnsPnl" forceId="true" style="display:block;text-align: center;">
    <%--<t:commandButton id="searchBtn" forceId="true" value="#{integrationBundle.display}" disabled="#{pageBeanName.selectedMiniName==null || pageBeanName.selectedMiniName==''}" styleClass="cssButtonSmall" action="#{pageBeanName.viewFileDetails}"  onclick="clearMsgs();return validatemyForm();"/>--%>
    <t:commandButton id="createFile" forceId="true" value="#{integrationBundle2.file_creation}"  
        disabled="#{pageBeanName.selectedMiniName==null || pageBeanName.selectedMiniName==''}" 
        action="#{pageBeanName.createFireGeneralDepartmentFile}" styleClass="cssButtonSmall" onclick="clearMsgs();if(validatemyForm()){block();return true; }else {return false;}"/>
    <%-- || ( pageBeanName.fileTypeRadio=='0' &&  (pageBeanName.selectedMiniId == 10   || pageBeanName.selectedMiniId == 1 ))--%>
    <t:commandButton id="searchBtn" forceId="true" value="#{integrationBundle2.file_view}"  
        disabled="#{pageBeanName.selectedMiniName==null || pageBeanName.selectedMiniName==''}" styleClass="cssButtonSmall" action="#{pageBeanName.viewFileDetails}" 
        onclick="clearMsgs();return validatemyForm();"/>
    <%--<t:commandButton id="searchBtn2" forceId="true" value="#{integrationBundle2.viewJointInsurFile}" rendered=="#{pageBeanName.fileTypeRadio=='0'}" disabled="#{pageBeanName.selectedMiniName==null || pageBeanName.selectedMiniName==''}" styleClass="cssButtonSmall" action="#{pageBeanName.viewFileDetails}"  onclick="clearMsgs();return validatemyForm();"/>--%>
</t:panelGroup>
<%--<t:saveState value="#{createInsuranceFileBean.pageMode}"/>--%>
