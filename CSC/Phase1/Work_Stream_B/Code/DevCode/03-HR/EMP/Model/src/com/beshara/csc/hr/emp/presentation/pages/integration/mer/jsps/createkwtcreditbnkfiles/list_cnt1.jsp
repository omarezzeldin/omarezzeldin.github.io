<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<jsp:include page="/integration/org/jsps/ministry/searchministrieswithpagingdiv.jsp"/>
<t:panelGrid id="cnt1PnlGrid" forceId="true" columns="6" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">

    
    <t:panelGroup id="ministryTypePanel"  dir="#{shared_util.pageDirection}" colspan="6">
    <t:panelGrid align="center">
        <t:selectOneRadio  forceId="true" id="chooseTypeId" value="#{pageBeanName.minTypeRadio}" >
            <f:selectItem   itemLabel="#{integrationBundle2.ministry}" id="zeroId"  itemValue="0"/>
            <f:selectItem itemLabel="#{integrationBundle2.all_ministries}" itemValue="1"/>
            <a4j:support event="onclick" actionListener="#{pageBeanName.ministryTypeButtonChanged}"   
                         reRender="category_Label,catPanelID,searchPanelId,ministryTypePanel,sheetLabelId,sheetsMenuPnl,btnPGId"/>
        </t:selectOneRadio>     
        </t:panelGrid>
        <t:inputHidden forceId="true" id="selectedtypHiddenId" value="#{pageBeanName.selectedMinTypeHidden}"/>
    </t:panelGroup>
        <t:outputText id="category_Label" value="#{integrationBundle2.category}" styleClass="lable01" />
        <t:panelGroup forceId="true" id="catPanelID" colspan="2">
        <t:inputText forceId="true" id="categoryFilterationId" styleClass="filteration_input_text" disabled="#{pageBeanName.minTypeRadio =='1'}"
                     onkeypress="filterationInputOnKeyPress(event,this,'cats_combo','errorCodeId1',changeCategory,'minFilterationId');"
                     onblur="filterationInputOnBlur(event,this,'cats_combo','errorCodeId1',changeCategory,'minFilterationId');" 
                     onkeyup="enabelIntegerOnly(this);"  value="#{pageBeanName.selectedCatId}">
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true" 
                         onchange="filterationDropboxOnChange(event,this,'categoryFilterationId','errorCodeId1',changeCategory,'minFilterationId');"
                         id="cats_combo" value="#{pageBeanName.selectedCatId}" disabled="#{pageBeanName.minTypeRadio =='1'}"
                         styleClass="textbox_175" converter="javax.faces.Long">
            <f:selectItem itemLabel="#{integrationBundle2.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="cat" itemLabel="#{cat.name}" itemValue="#{cat.code.keys[0]}"
                           value="#{pageBeanName.categories}"/>
            <a4j:jsFunction name="changeCategory" actionListener="#{pageBeanName.clearMinData}"
                            reRender="ministryNamePanelId,catPanelID,scriptGeneratorPanel,searchPanelId,btnPGId" oncomplete="clearInputText('minFilterationId'); clearInputText('ministryName');"/>
        </t:selectOneMenu>
        <%--<h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.minTypeRadio =='1'}"/>--%>
        <f:verbatim><!--<br/>--></f:verbatim>
        <t:panelGroup  forceId = "true" id="errorCodePanelId1">
            <t:outputText id="errorCodeId1" value="#{integrationBundle2.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="display:none;color: #FF0000;"/>
             <%--<c:compareValidator componentToValidate="cats_combo" componentToCompare="virtualvalueId" operator="not" 
                            errorMessage="#{integrationBundle2.select_one_item}" highlight="false" display="dynamic"/>--%>
        </t:panelGroup>
       
    </t:panelGroup>
    
    <t:outputText id="min_Label" value="#{integrationBundle2.ministry}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="searchPanelId" colspan="2">


            <t:inputText forceId="true" id="minFilterationId" styleClass="filteration_input_text" disabled="#{pageBeanName.minTypeRadio =='1'}"
                         onkeypress="filterDivInputOnKeyPress(event,this,changeMinistry,'searchBtn');"
                         onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedMiniId}">
                         <%--onmouseout="filterDivInput(this,changeMinistry,'searchBtn');"--%>
                <a4j:jsFunction name="changeMinistry" actionListener="#{pageBeanName.changeMinistry}" 
                                reRender="ministryNamePanelId,ministryName,errorCodePanelId2,searchBtn,createInsurFile,sheetsMenuPnl"/>
            </t:inputText>
        
        <t:panelGroup forceId = "true" id="ministryNamePanelId">
            <t:inputText forceId="true" id="ministryName" disabled="true" styleClass="textboxmedium" value="#{pageBeanName.selectedMiniName}"/>
            <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>       
<a4j:commandButton id="searchMinistryBtn" value="..." action="#{pageBeanName.openSearchMinistryDiv}" styleClass="cssButtonSmall" disabled="#{pageBeanName.selectedCatId == null || pageBeanName.selectedCatId == pageBeanName.virtualLongValue}" reRender="searchMinistryDiv,searchBtn,createInsurFile" oncomplete="changeVisibilityDiv(window.blocker,window.searchMinistryDiv);"/>        
            <%--<f:verbatim><br/></f:verbatim>--%>
            <%--<c:requiredFieldValidator componentToValidate="ministryName" 
                                      errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>--%>
        </t:panelGroup>

        
        
     
        <t:panelGroup  forceId = "true" id="errorCodePanelId2">
            <f:verbatim rendered="#{pageBeanName.wrongMinCode}"><br/></f:verbatim>
            <t:outputText id="errorCodeId2" value="#{integrationBundle2.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="color: #FF0000;" rendered="#{pageBeanName.wrongMinCode}"/>
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
            <t:selectOneMenu forceId="true" id="monthMenu" styleClass="Dropdownbox" value="#{pageBeanName.month}" onchange="loadSheetValues();">
               <%--<f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>--%>
                <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                               var="months" />               
            </t:selectOneMenu>
         <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
            <%--<f:verbatim><br/></f:verbatim>--%>
            <%--<t:outputText id="to_monthErrMsg" forceId="true" value="#{resourcesBundle.select}" style="display:none;"
                          styleClass="error"/>--%>
        </t:panelGroup>
        
        <t:outputText id="yearText" forceId="true" value="#{integrationBundle2.year}" styleClass="lable01" />
        <t:panelGroup id="yearMenuPnl" forceId="true">
            <t:selectOneMenu forceId="true" id="yearMenu" styleClass="Dropdownbox" value="#{pageBeanName.year}"
                             onchange="loadSheetValues();">
              <%--<f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>--%>
                <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                               var="years"/>             
            </t:selectOneMenu>
            <%--<t:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
            <%--<f:verbatim>
            <br/>
        </f:verbatim>--%>
        <%--<t:outputText id="to_yearErrMsg" forceId="true" value="#{integrationBundle2.select}" style="display:none;"
                      styleClass="error"/>--%>
                      
        <a4j:jsFunction name="viewSheets"  action="#{pageBeanName.searchSalSheets}" reRender="sheetsMenuPnl" />
    </t:panelGroup>
    
    
    <t:panelGroup  forceId="true" id="sheetsMenuPnl"  colspan="2">
        <t:outputLabel forceId="true" id="sheetLabelId" value="#{integrationBundle2.sheet_no}" styleClass="lable01" rendered="#{pageBeanName.minTypeRadio =='0'}"  style="margin-right: 8px;"/>
        <t:selectOneMenu forceId="true" id="sheetsMenu" styleClass="Dropdownbox" style="margin-right: 20px;"
                         value="#{pageBeanName.selectedSheetCodeStr}"  rendered="#{pageBeanName.minTypeRadio =='0'}">
            <f:selectItem itemLabel="#{integrationBundle2.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems value="#{pageBeanName.salarySheets}" itemLabel="#{sheet1.code.key}" itemValue="#{sheet1.code.key}"
                           var="sheet1"/>
        </t:selectOneMenu>
                  <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>

        <%--<f:verbatim><br/></f:verbatim>--%>
    <%--<c:compareValidator  componentToValidate="sheetsMenu" componentToCompare="virtualConstValue" operator="not" display="dynamic"
                            errorMessage="#{resourcesBundle.selectItem}" highlight="false" />--%>
    
    </t:panelGroup>
    <t:outputLabel value="#{integrationBundle2.deductions}" styleClass="lable01"  />
    <t:panelGroup  forceId="true" id="deductionPnl" colspan="5" >
        <t:selectOneMenu forceId="true" id="deductionListId" styleClass="Dropdownboxmediums"
                         value="#{pageBeanName.selectedDeductCode}" >
            <f:selectItem itemLabel="#{integrationBundle2.all}" itemValue="0"/>
            <t:selectItems value="#{pageBeanName.deductionList}" itemLabel="#{ded.name}" itemValue="#{ded.code.key}"
                           var="ded"/>
        </t:selectOneMenu>
    
    </t:panelGroup>    
       
</t:panelGrid>
<t:panelGroup style="display:block;text-align: center;" forceId="true" id="btnPGId">
    <t:commandButton id="createInsurFile" forceId="true" value="#{integrationBundle2.create}"  onclick="block();"  disabled="#{pageBeanName.disabledButtons}" action="#{pageBeanName.createBankFile}" styleClass="cssButtonSmall" />
    <t:commandButton id="searchBtn" forceId="true" value="#{integrationBundle2.view}"  disabled="#{pageBeanName.disabledButtons}" styleClass="cssButtonSmall" action="#{pageBeanName.viewFileDetails}"  />
</t:panelGroup>

<script type="text/javascript">
  function loadSheetValues() {
      if(document.getElementById("selectedtypHiddenId") != null){  
      var selectionType = document.getElementById("selectedtypHiddenId").value;        
          if(selectionType != null && selectionType === '0'){
            viewSheets();
          }
      }
  }
</script>