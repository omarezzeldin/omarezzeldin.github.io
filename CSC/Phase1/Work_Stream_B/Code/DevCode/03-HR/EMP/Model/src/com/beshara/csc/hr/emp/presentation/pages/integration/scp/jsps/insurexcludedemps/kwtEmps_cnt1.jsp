<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<jsp:include page="/integration/org/jsps/ministry/searchministrieswithpagingdiv.jsp"/>
<t:panelGrid columns="6" width="100%" forceId="true" id="searchPanel" rowClasses="row01,row02" cellspacing="0">
    <t:outputText id="category_Label" value="#{intgBundle.category}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="catPanelID" colspan="2">
        <t:inputText forceId="true" id="categoryFilterationId" styleClass="filteration_input_text"
                      value="#{pageBeanName.selectedCatId}"
                     onkeypress="filterationInputOnKeyPress(event,this,'cats_combo','errorCodeId1',changeCategory,null);"
                     onblur="filterationInputOnBlur(event,this,'cats_combo','errorCodeId1',changeCategory,null);"
                     onkeyup="enabelIntegerOnly(this);"></t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true"
                         onchange="filterationDropboxOnChange(event,this,'categoryFilterationId','errorCodeId1',changeCategory,null);"
                         id="cats_combo" value="#{pageBeanName.selectedCatId}" styleClass="textbox_175"
                         converter="javax.faces.Long">
            <f:selectItem itemLabel="#{intgBundle.select}" itemValue=""/>
            <t:selectItems var="cat" itemLabel="#{cat.name}" itemValue="#{cat.code.keys[0]}"
                           value="#{pageBeanName.categories}"/>
            <a4j:jsFunction name="changeCategory" actionListener="#{pageBeanName.clearMinData}"
                            reRender="ministryNamePanelId,catPanelID,scriptGeneratorPanel,OperationBar,searchPanelId,radioPanel2,errorCodePanelId2"
                            oncomplete="clearInputText('minFilterationId'); clearInputText('ministryName');"/>
        </t:selectOneMenu>
        <%-- <h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
        <f:verbatim><br/></f:verbatim>
        <t:panelGroup forceId="true" id="errorCodePanelId1">
            <t:outputText id="errorCodeId1" value="#{intgBundle.MessageForWrongCode}" forceId="true"
                          styleClass="validation_error" style="display:none;color: #FF0000;"/>
            <!--<comparevalidator componenttovalidate="cats_combo" componenttocompare="virtualvalueId" operator="not"
                              errormessage="#{intgBundle.select_one_item}" highlight="false" display="dynamic"/>-->
        </t:panelGroup>
    </t:panelGroup>
    
    <t:panelGroup forceId="true" id="searchPanelId" colspan="3">
        <t:outputText id="min_Label" value="#{intgBundle.ministry}" styleClass="lable01"/>
        <f:verbatim>&nbsp;</f:verbatim><f:verbatim>&nbsp;</f:verbatim>
        <t:inputText forceId="true" id="minFilterationId" styleClass="filteration_input_text"
                     onkeypress="filterDivInputOnKeyPress(event,this,changeMinistry,'searchBtn');"
                     onblur="filterDivInput('minFilterationId',changeMinistry,'searchBtn');"
                     onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedMiniId}">
            <%-- onmouseout="filterDivInput(this,changeMinistry,'searchBtn');"--%>
            <a4j:jsFunction name="changeMinistry" actionListener="#{pageBeanName.changeMinistry}"
                            reRender="radioPanel2,ministryNamePanelId, OperationBar,ministryName,errorCodePanelId2"/>
        </t:inputText>
        <t:panelGroup forceId="true" id="ministryNamePanelId">
            <t:inputText forceId="true" id="ministryName" disabled="true" styleClass="textboxmedium"
                         value="#{pageBeanName.selectedMiniName}"/>
            <%-- <h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
            <a4j:commandButton id="searchMinistryBtn" value="..." action="#{pageBeanName.openSearchMinistryDiv}"
                               styleClass="cssButtonSmall"
                               disabled="#{pageBeanName.selectedCatId == null || pageBeanName.selectedCatId == pageBeanName.virtualLongValue}"
                               reRender="searchMinistryDiv,OperationBar"
                               oncomplete="changeVisibilityDiv(window.blocker,window.searchMinistryDiv);"/>
            <f:verbatim><br/></f:verbatim>
            <!--<requiredfieldvalidator componenttovalidate="ministryName" errormessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>-->
        </t:panelGroup>
        <t:panelGroup forceId="true" id="errorCodePanelId2">
            <%-- <f:verbatim rendered="#{pageBeanName.wrongMinCode}"><br/></f:verbatim>--%>
            <t:outputText id="errorCodeId2" value="#{intgBundle.MessageForWrongCode}" forceId="true"
                          styleClass="validation_error" style="color: #FF0000;"
                          rendered="#{pageBeanName.wrongMinCode}"/>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{intgBundle.CivilID}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText maxlength="12" forceId="true" id="civilId" styleClass="textbox" onkeyup="enabelIntegerOnly(this);"
                     value="#{pageBeanName.realCivilId}"/>
        <%-- <t:inputText styleClass="textbox" value="#{pageBeanName.realCivilId}" disabled="true"
             rendered="#{pageBeanName.civilExist}"/>--%>
        <%-- <h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
        <%-- <t:commandButton rendered="#{!pageBeanName.civilExist}" id="civil_exist_btn" forceId="true"
             onclick="resetMsgInAdd(); return validatemyForm('civilValidations');" value="#{globalResources.Available}"
             styleClass="cssButtonSmall" action="#{pageBeanName.filterByCivilId}"/>--%>
        <%-- <a4j:commandButton type="submit" value="#{globalResources.reSetButton}" rendered="#{pageBeanName.civilExist
             && !pageBeanName.comeFromSalReveiw}" styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}"
             id="reSetData"
             reRender="dataT_data_panel,headerMonSalGroup,cnt1Panel,mainDataEmpPanel,displayBtnPanel,paging_panel,monsalGroupId,OperationBar"/>--%>
        <%-- <f:verbatim>&nbsp;</f:verbatim>--%>
        <%-- <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
             oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
             action="#{pageBeanName.showSearchForEmployeeDiv}" reRender="lovEmp,mainDataEmpPanel,displayBtnPanel"
             rendered="#{!pageBeanName.civilExist}"/>--%>
        <f:verbatim><br/></f:verbatim>
        <%--<c2:requiredFieldValidator active="#{!empVacMonSalEnquiryListBean.civilExist}" componentToValidate="civilId"
                                 group="civilValidations"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
        <%-- <c2:regularExpressionValidator componentToValidate="civilId" pattern="/^[0-9]{12}$/"
             errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false" display="dynamic"/>--%>
    </t:panelGroup>
    <h:outputText value="#{globalResources.globalName}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText forceId="true" id="empName" styleClass="textboxmedium" value="#{pageBeanName.empName}"/>
    </t:panelGroup>
    <h:outputText value="#{intgBundle.sal_elm_guide}" styleClass="divtext"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="salElemGuide" styleClass="Dropdownbox" value="#{pageBeanName.selElemGuide}"
                         onblur="setFousAtNextAfterComboItem();">
            <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems value="#{pageBeanName.elementGuides}" itemLabel="#{elementGuides.name}"
                           itemValue="#{elementGuides.code.key}" var="elementGuides"/>
        </t:selectOneMenu>
        <%--<t:outputLabel value="*" styleClass="mandatoryAsterisk"/>--%>
        <%--<f:verbatim>
            <br/>
        </f:verbatim>--%>
        <%--<!--<c2:compareValidator componentToValidate="salElemGuide" active="#{empAddBean.civilExist}"
                             componentToCompare="virtualvalueId" display="dynamic" operator="not"
                             errorMessage="#{intgBundle.selectItem}" highlight="false"/>-->--%>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid id="buttonsPnl" columns="2" align="center" dir="#{shared_util.pageDirection}">
    <t:commandButton forceId="true" id="displayBtn" value="#{intgBundle.display}" styleClass="cssButtonSmall"
                     action="#{pageBeanName.filterTable}"/>
</t:panelGrid>