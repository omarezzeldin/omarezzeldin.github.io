<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.stopDateID  img[src="/mer/app/media/images/icon_calendar.jpg"]{
        background: url("../../images/cal.gif") repeat scroll 0 0 rgba(0, 0, 0, 0) !important;
    bottom: 1px !important;
    height: 16px !important;
    left: 2px !important;
    margin-right: -18px !important;
    margin-top: 0 !important;
    position: relative !important;
    width: 16px !important;
    }
    .divContent1Dynamic{
    border-radius: 6px;
    box-shadow: 0 0 3px #5D9BC5;
    margin: 8px auto 3px auto;
    overflow-y: auto;
    overflow-x: hidden;
    max-height: 450px;
    padding: 0;
    width: 870px !important;
}
#maintain_decisionText_ifr{
    height: 200px !important;
}
 img[onclick*="CalendarVar"], img[onclick*="CalendarVar"] {
           margin-bottom: 4px; position: relative !important; }
</htm:style>
<t:messages showDetail="true"/>
<t:panelGrid id="cnt1PG" forceId="true" columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" >


    <t:outputText value="#{merIntgResources.allowance_reasons_title}" styleClass="lable01"
                   rendered="#{pageBeanName.decisionParams.showAllowanceReasons}"/>
    <t:panelGroup rendered="#{pageBeanName.decisionParams.showAllowanceReasons}" >
        <t:selectOneMenu value="#{pageBeanName.decisionParams.selAllowanceReason}" styleClass="Dropdownbox"
                         forceId="true" id="allowanceResonsList" >
            <t:selectItems value="#{pageBeanName.allowanceReasons}" itemLabel="#{CReason.name}"
                           itemValue="#{CReason.code.key}" var="CReason"/>
            <a4j:support event="onchange" reRender="cnt1PG,_stopDateID,stopDatePG,scriptPanelGroup" action="#{pageBeanName.changeAllowanceReason}" />     
        </t:selectOneMenu>
    </t:panelGroup>

    <h:outputLabel value="#{merIntgResources.MER_RAISE_CLMN_SUSPEND_REASON}"
                   rendered="#{pageBeanName.renderStopReasonGrd}"/>
    <t:panelGroup rendered="#{pageBeanName.renderStopReasonGrd}" colspan="2">
        <t:selectOneMenu value="#{pageBeanName.salEmpRelDTO.stopReasonsKey}" styleClass="DropdownboxLarge"
                         forceId="true" id="selectedKidsSuspenionElements"
                         onblur="setFocusOnlyOnElement('MER_RAISE_CLMN_ALLOWANCE');"
                         disabled="#{pageBeanName.stopReason}" onchange="getAutoStopDate();">
            <f:selectItem itemLabel="#{merIntgResources.selectElement}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems value="#{pageBeanName.suspendReasons}" itemLabel="#{CReason.name}"
                           itemValue="#{CReason.code.key}" var="CReason"/>
                           
            <a4j:jsFunction name="changeStopReason"  action="#{pageBeanName.divorcedDateShowFun}" 
                           reRender="cnt1PG,renderStopReasonGrd,divorcedDateShow,textEditorPGId,stopDateIDPnl,divorceDatePnl,currentDateId,scriptPanelGroup,divMsg" onComplete="updateDecText();changeVisibilityMsg();" />     
                           
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:compareValidator componentToValidate="selectedKidsSuspenionElements" componentToCompare="virtualvalueId"
                             operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                             display="dynamic" active="#{issueDecisionBean.renderStopReasonGrd}"/>
    </t:panelGroup>

    <h:outputLabel value="#{merIntgResources.ALLOWANCE_DATE}" styleClass="lable01"
                   rendered="#{pageBeanName.motherGrantChildrenRaise && pageBeanName.addRaise}"/>
    <t:panelGroup rendered="#{pageBeanName.motherGrantChildrenRaise && pageBeanName.addRaise}" styleClass="stopDateID">
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="startDateID" maxlength="#{pageBeanName.calendarTextLength}"
                         styleClass="textbox" currentDayCellClass="currentDayCell"
                         value="#{pageBeanName.salEmpRelDTO.allowanceDate}" renderAsPopup="true" converter="SqlDateConverter"
                         align="#{globalResources.align}" disabled="#{pageBeanName.disableAllowanceCal}"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         onchange="updateDecTextAllowanceDate();" ></t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="startDateID" errorMessage="#{globalResources.missingField}"
                                   highlight="false" display="dynamic"
                                   active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise}"/>
        <c2:dateFormatValidator componentToValidate="startDateID" display="dynamic"
                                errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise}"/>
        <c2:compareDateValidator componentToValidate="lastStopDateId" componentToCompare="startDateID" display="dynamic"
                                 errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_LAST_STOP_ALLOWNCE_DATE} - #{pageBeanName.lastStopDate}"
                                 operator="before" highlight="false" active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise}"/>

        <%--<c2:compareDateValidator componentToValidate="startDateID" componentToCompare="lastStopDateId" display="dynamic"
                                 errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_LAST_STOP_ALLOWNCE_DATE} - #{pageBeanName.lastStopDate}"
                                 operator="after" highlight="false" active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise && pageBeanName.lastStopDate != null}"/>--%>
    </t:panelGroup>
    <t:panelGroup colspan="4" rendered="#{pageBeanName.motherGrantChildrenRaise && pageBeanName.addRaise && pageBeanName.salEmpRelDTOs == null}"/>
    
    <t:panelGroup colspan="2" id="stopDatePG" forceId="true" rendered="#{pageBeanName.motherGrantChildrenRaise && pageBeanName.addRaise && pageBeanName.salEmpRelDTOs != null}">
    <h:outputLabel value="#{merIntgResources.MER_RAISE_CLMN_SUSPEND_ALLOWANCE}" styleClass="lable01"
                   rendered="#{pageBeanName.decisionParams.selAllowanceReason == 1}"/>
    <t:panelGroup rendered="#{pageBeanName.decisionParams.selAllowanceReason == 1}" styleClass="stopDateID">
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="_stopDateID" maxlength="#{pageBeanName.calendarTextLength}"
                             styleClass="textbox" currentDayCellClass="currentDayCell"
                             value="#{pageBeanName.salEmpRelDTO.stopDate}" renderAsPopup="true" converter="SqlDateConverter"
                             align="#{globalResources.align}" disabled="#{pageBeanName.disableCal}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             onchange="updateDecText();"></t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.motherGrantChildrenRaise && pageBeanName.addRaise }"/>
            <f:verbatim><br/></f:verbatim>
            <c2:requiredFieldValidator componentToValidate="_stopDateID" errorMessage="#{globalResources.missingField}"
                                       highlight="false" display="dynamic" group="motherStopDateGrp" />
                                       <%--active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise && issueDecisionBean.salEmpRelDTOs != null && issueDecisionBean.decisionParams.selAllowanceReason == 1 }"--%>
            <c2:dateFormatValidator componentToValidate="_stopDateID" display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" group="motherStopDateGrp" />
                                    <%--active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise && issueDecisionBean.salEmpRelDTOs != null && issueDecisionBean.decisionParams.selAllowanceReason == 1 }"--%>
            <c2:compareDateValidator componentToValidate="startDateID" componentToCompare="_stopDateID" display="dynamic"
                                     errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_STOP_ALLOWN_DATE}"
                                     operator="before" highlight="false" group="motherStopDateGrp" />
                                     <%--active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise && issueDecisionBean.salEmpRelDTOs != null && issueDecisionBean.decisionParams.selAllowanceReason == 1 }"--%>
           
           
            <%--<c2:compareDateValidator componentToValidate="_stopDateID" componentToCompare="currentDateId" display="dynamic"
                                     errorMessage="#{merIntgResources.STOP_DATE_LESSTHAN_INFORM_CURRENT_DATE}"
                                     operator="before" highlight="false" active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise}"/>--%>
            <%--<c2:compareDateValidator componentToValidate="startDateID" componentToCompare="stopDateID" display="dynamic"
                                     errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_STOP_ALLOWN_DATE}"
                                     operator="before" highlight="false" active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise}"/>--%>
    </t:panelGroup>
    </t:panelGroup>
    
    <h:outputLabel value="#{merIntgResources.MER_RAISE_CLMN_SUSPEND_ALLOWANCE}" styleClass="lable01"
                   rendered="#{pageBeanName.renderStopReasonGrd }"/>
    <t:panelGroup id="stopDateIDPnl" forceId="true" rendered="#{pageBeanName.renderStopReasonGrd }" colspan="2" styleClass="stopDateID">
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="stopDateID" maxlength="#{pageBeanName.calendarTextLength}"
                         styleClass="textbox" currentDayCellClass="currentDayCell"
                         value="#{pageBeanName.salEmpRelDTO.stopDate}" renderAsPopup="true" converter="SqlDateConverter"
                         align="#{globalResources.align}" disabled="#{pageBeanName.disableCal}"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         onchange="updateDecText();"></t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.renderStopReasonGrd}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="stopDateID" errorMessage="#{globalResources.missingField}"
                                   highlight="false" display="dynamic" group="stopDateGrp" />
                                   <%-- active="#{issueDecisionBean.renderStopReasonGrd }" --%>
        <c2:dateFormatValidator componentToValidate="stopDateID" display="dynamic"
                                errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" group="stopDateGrp" />
                                <%-- active="#{issueDecisionBean.renderStopReasonGrd }" --%>
        <c2:compareDateValidator componentToValidate="MasterDateId" componentToCompare="stopDateID" display="dynamic"
                                 errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_STOP_ALLOWN_DATE}"
                                 operator="before" group="stopDateGrp" />
                                 <%-- active="#{issueDecisionBean.renderStopReasonGrd }" --%>
        <c2:compareDateValidator componentToValidate="stopDateID" componentToCompare="currentDateId" display="dynamic" group="stop_Date_Grp222"
                                 errorMessage="#{merIntgResources.STOP_DATE_LESSTHAN_INFORM_CURRENT_DATE}"
                                 operator="before"  />
                                 <%-- active="#{issueDecisionBean.renderStopReasonGrd }" --%>
              <f:verbatim> <br/></f:verbatim>                   
       <c2:compareDateValidator componentToValidate="divorcedDate" componentToCompare="stopDateID" display="dynamic"
                                 errorMessage="#{merIntgResources.StopDate_Greater_DivorceDate}"
                                 operator="beforeOrEqual"  group="stopDate_Grp333" uniqueId="validates_topDateID_5"/>
                                 <%--active="#{issueDecisionBean.renderStopReasonGrd && issueDecisionBean.divorcedDateShow}"--%>                                  
        <%--<c2:compareDateValidator componentToValidate="startDateID" componentToCompare="stopDateID" display="dynamic"
                                 errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_STOP_ALLOWN_DATE}"
                                 operator="before" highlight="false" active="#{issueDecisionBean.motherGrantChildrenRaise && issueDecisionBean.addRaise}"/>--%>
    </t:panelGroup>
    <t:panelGroup colspan="6" rendered="#{(!pageBeanName.renderStopReasonGrd) && (!pageBeanName.addRaise) }"
                  style="text-align: center;display: block;">
                  <%-- pageBeanName.stopDateID2Label--%>
        <h:outputLabel value="#{merIntgResources.MER_RAISE_CLMN_SUSPEND_ALLOWANCE}" styleClass="lable01"
                       rendered="#{(!pageBeanName.renderStopReasonGrd) && (!pageBeanName.addRaise) }"/>
        <t:panelGroup colspan="5" rendered="#{(!pageBeanName.renderStopReasonGrd) && (!pageBeanName.addRaise) }">
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             style="margin-right: 5px;" forceId="true" id="stopDateID2"
                             disabled="#{!pageBeanName.enableGroup}" maxlength="#{pageBeanName.calendarTextLength}"
                             styleClass="textbox" currentDayCellClass="currentDayCell"
                             value="#{pageBeanName.salEmpRelDTO.untilDate}" renderAsPopup="true"
                             converter="SqlDateConverter" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" onchange="updateDecText();" ></t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator componentToValidate="stopDateID2" errorMessage="#{globalResources.missingField}"
                                       highlight="false" display="dynamic"
                                       active="#{(!issueDecisionBean.renderStopReasonGrd) && (!issueDecisionBean.addRaise)}"/>
            <c2:dateFormatValidator componentToValidate="stopDateID2" display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                    active="#{(!issueDecisionBean.renderStopReasonGrd) && (!issueDecisionBean.addRaise)}"/>
            <c2:compareDateValidator componentToValidate="MasterDateId" componentToCompare="stopDateID2"
                                     display="dynamic"
                                     errorMessage="#{merIntgResources.ALLOWN_DATE_LESSTHAN_STOP_ALLOWN_DATE}"
                                     operator="before" highlight="false"
                                     active="#{(!issueDecisionBean.renderStopReasonGrd) && (!issueDecisionBean.addRaise)}"/>
        </t:panelGroup>
    </t:panelGroup>
    <!--- Start of Row 1-->
    <h:outputText value="#{merIntgResources.type}"/>
    <t:panelGroup colspan="2">
        <t:selectOneMenu forceId="true" id="maintain_regTypeAdd"
                         style="#{pageBeanName.addRaise ? 'width: 270px;' : ''} " styleClass="DropdownboxLarge"
                         value="#{pageBeanName.pageDTO.typesDTOKey}">
            <f:selectItem itemValue="" itemLabel="#{merIntgResources.select}"/>
            <t:selectItems value="#{pageBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}"
                           var="type"/>
            <a4j:support actionListener="#{pageBeanName.filterDecMkrBytype}" event="onchange"
                         reRender="maintain_decisionDecisionMakerAdd"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="maintain_regTypeAdd" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   />
        <%-- active="#{pageBeanName.addDecisionMode || pageBeanName.cancelDecisionMode}"--%>
    </t:panelGroup>
    <h:outputText value="#{merIntgResources.decisions_decision_maker}"/>
    <t:panelGroup colspan="2">
        <t:selectOneMenu forceId="true" id="maintain_decisionDecisionMakerAdd" 
                         styleClass="DropdownboxLarge" value="#{pageBeanName.pageDTO.decisionMakerDTOKey}">
            <f:selectItem itemValue="" itemLabel="#{merIntgResources.select}"/>
            <t:selectItems value="#{pageBeanName.decisionMakers}" itemLabel="#{dMaker.name}"
                           itemValue="#{dMaker.code.key}" var="dMaker"/>
          <%--<a4j:support action="#{pageBeanName.updateDecTextWithDecMakers}" event="onchange"
                         oncomplete="initMCEEditor('maintain_decisionText');" reRender="textEditorPGId"/>--%>               
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="maintain_decisionDecisionMakerAdd" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   />
        <%-- active="#{!pageBeanName.cancelDecisionMode}"--%>
    </t:panelGroup>
   
          
          <!-- start of CSC-17389 -->

           <%--h:outputText value="#{merIntgResources.decision_number}"  />
            <t:panelGroup style="#{pageBeanName.addRaise ? 'display: block; margin: 3px 14px 0px 47px;' : 'display: block; margin: 3px 18px 0px 47px;'}">
                <t:inputText forceId="true" id="maintain_decisionNumber" maxlength="10"
                             onkeyup="disableCharacters(this)" styleClass="textbox"
                             value="#{pageBeanName.pageDTO.decisionNumber}"/>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator componentToValidate="maintain_decisionNumber" display="dynamic"
                                           errorMessage="#{globalResources.missingField}" highlight="false"
                                           uniqueId="maintain_decisionNumberID"/>
            </t:panelGroup--%>
        
        <!-- end of CSC-17389 -->
            
            
            <t:outputText value="#{merIntgResources.issuance_year}"/>
            <t:panelGroup colspan="2" >
                <t:inputText forceId="true"  id="maintain_regIssuanceYearEdit"  
                            onkeypress="enabelIntegerOnly(this);"
                             onkeyup="enabelIntegerOnly(this);" maxlength="4" styleClass="textbox"
                                 value="#{pageBeanName.pageDTO.yearsDTOKey}"></t:inputText>
                <t:outputText value="*" styleClass="mandatoryAsterisk" />
                <f:verbatim>
                    <br/>
                </f:verbatim>
              
                <c2:requiredFieldValidator componentToValidate="maintain_regIssuanceYearEdit" display="dynamic"
                                           errorMessage="#{globalResources.missingField}" highlight="false"/>
                  <c2:regularExpressionValidator componentToValidate="maintain_regIssuanceYearEdit" pattern="/^[0-9]{4}$/"
                                                   errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                                   display="dynamic"/>
            </t:panelGroup>
            <h:outputText value="#{merIntgResources.decision_date}" />
            <%-- <t:inputText forceId="true" id="maintain_regIssuanceDateEdit" disabled="true" styleClass="textbox"
                 value="#{pageBeanName.pageDTO.decisionDate}"/>--%>
            <t:panelGroup colspan="2" >
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="maintain_regIssuanceDateEdit"
                                  maxlength="#{pageBeanName.calendarTextLength}"
                                 styleClass="textbox" currentDayCellClass="currentDayCell"
                                 value="#{pageBeanName.pageDTO.decisionDate}" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true" style="position: relative !important;">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                <!--disabled="#{!pageBeanName.enableGroup}" -->
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator componentToValidate="maintain_regIssuanceDateEdit"
                                           errorMessage="#{globalResources.missingField}" highlight="false"
                                           display="dynamic"/>
                                           <!--active="#{(!issueDecisionBean.renderStopReasonGrd) && (!issueDecisionBean.addRaise)}"
                -->
                <c2:dateFormatValidator componentToValidate="maintain_regIssuanceDateEdit" display="dynamic"
                                        errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
                                        <!--active="#{(!issueDecisionBean.renderStopReasonGrd) && (!issueDecisionBean.addRaise)}"
            -->
            </t:panelGroup>
     
    <h:outputText value="#{merIntgResources.decision_description}" styleClass="nowrap_txt"/>
    <t:panelGroup colspan="3">
        <t:inputText forceId="true" id="maintain_decisionTitle" maxlength="400" styleClass="textboxlarge2"
                     style="width: 391px;" value="#{pageBeanName.pageDTO.decisionTitle}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="maintain_decisionTitle" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false" />
    </t:panelGroup>
    <t:panelGroup id="divorceDatePnl" forceId="true" colspan="2">
     <t:panelGroup forceId="true" id="divorcedDatefieldPG">
    <h:outputLabel value="#{merIntgResources.devorce_date}" styleClass="lable01" rendered="#{pageBeanName.renderStopReasonGrd && pageBeanName.divorcedDateShow}" />
    </t:panelGroup>
    <t:panelGroup forceId="true" id="divorcedDatePG" rendered="#{pageBeanName.renderStopReasonGrd && pageBeanName.divorcedDateShow}" >
           
          <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="divorcedDate" maxlength="#{pageBeanName.calendarTextLength}" 
                             rendered="#{pageBeanName.renderStopReasonGrd && pageBeanName.divorcedDateShow}"
                             styleClass="textbox" currentDayCellClass="currentDayCell" 
                             value="#{pageBeanName.divorcedDate}" renderAsPopup="true" converter="TimeStampConverter"
                             align="#{globalResources.align}" disabled="#{pageBeanName.disableDivorcedDate}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" onchange="changeDivorcedDateFn();">
         </t:inputCalendar>
        <a4j:jsFunction name="changeDivorcedDateFn"  action="#{pageBeanName.changeDivorcedDate}" 
                       reRender="cnt1PG,renderStopReasonGrd,divorcedDateShow,textEditorPGId,stopDateIDPnl,divorceDatePnl,currentDateId,scriptPanelGroup,divMsg" onComplete="updateDecText();changeVisibilityMsg();" />     
         
         
       <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.renderStopReasonGrd && pageBeanName.divorcedDateShow}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        
  <%--<c2:compareDateValidator componentToValidate="currentDateId" componentToCompare="divorcedDate" display="dynamic" group ="ValidateDate"
                                 errorMessage="#{merIntgResources.DEVORCE_DATE_LESSTHAN_INFORM_CURRENT_DATE}"
                                 operator="afterOrEqual" highlight="false"/>--%>   
    
    
      <c2:dateFormatValidator componentToValidate="divorcedDate" display="dynamic" group="divorceDateGrp"
                                errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
                                <%--active="#{issueDecisionBean.renderStopReasonGrd && issueDecisionBean.divorcedDateShow}"--%>
                                
     <c2:requiredFieldValidator componentToValidate="divorcedDate" display="dynamic" group="divorceDateGrp"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
                                       <%-- active="#{issueDecisionBean.renderStopReasonGrd && issueDecisionBean.divorcedDateShow}" --%>

         <c2:compareDateValidator componentToValidate="divorcedDate" componentToCompare="marriageDate" display="dynamic" group ="divorceDateGrp"
                                 errorMessage="#{merIntgResources.DEVORCE_DATE_GREATER_INFORM_MARRIAGE_DATE}"
                                 operator="afterOrEqual" highlight="false"/>
        <c2:compareDateValidator componentToValidate="divorcedDate" componentToCompare="currentDateId" display="dynamic"
                                 errorMessage="#{merIntgResources.DEVORCE_DATE_LESSTHAN_INFORM_CURRENT_DATE}"
                                 operator="before" highlight="false" group="divorceDateGrp" />
     
       <%--<h:outputText value="#{merIntgResources.DEVORCE_DATE_GREATER_INFORM_MARRIAGE_DATE}" rendered="#{pageBeanName.divorcedDateValidationShow}" styleClass="errMsg"/>--%>
        <%--<f:verbatim>      <br/>  </f:verbatim>--%>
       <%--<h:outputText value="#{merIntgResources.DEVORCE_DATE_LESSTHAN_INFORM_CURRENT_DATE}" rendered="#{pageBeanName.compareDivorcedDateWithCurrentDateFlag}" styleClass="errMsg"/>--%>
    </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{merIntgResources.dec_subject}" styleClass="nowrap_txt"/>
    <%-- merIntgResources.decision_subject--%>
    <t:panelGroup colspan="5">
        <%-- rendered="#{!pageBeanName.cancelDecisionMode}"--%>
        <t:inputText forceId="true" id="maintain_decisionSubject" disabled="true" readonly="true"
                     styleClass="textboxlarge2" style="width: 720px;" value="#{pageBeanName.pageDTO.subjectsDTO.name}"/>
    </t:panelGroup>
    <%--ddddddddddd--%>
    
</t:panelGrid>

<t:panelGrid id="cnt1PG222" forceId="true" columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" >
        <h:outputText value="#{merIntgResources.decision_text}" styleClass="nowrap_txt"/>
        <t:panelGroup colspan="5">
            <t:panelGroup forceId="ture" id="textEditorPGId">
                <%--<t:inputTextarea forceId="true" id="maintain_decisionText" value="#{pageBeanName.pageDTO.decisionText}"
                                 styleClass="textareaXlarge"
                                 style="#{pageBeanName.addRaise ? 'height: 250px; width: 715px;' : 'height: 213px; width: 715px;'}"/>--%>
                
                <t:inputText forceId="true" id="maintain_decisionText" styleClass="textareaXlarge"  value="#{pageBeanName.pageDTO.decisionText}"
                      style="#{pageBeanName.addRaise ? 'height: 250px; width: 715px;' : 'height: 213px; width: 715px;'}"  />
                
                
                <%-- cols="72" rows="3"--%>
                <t:outputText value="*" styleClass="mandatoryAsterisk"  />
                <%--<f:verbatim>
                    <br/>
                </f:verbatim>--%>
                <%--<c2:requiredFieldValidator componentToValidate="maintain_decisionText" display="dynamic"
                                           errorMessage="#{globalResources.missingField}" highlight="false"
                                           uniqueId="maintain_regTextID"/>--%>
                 <c2:customValidator componentToValidate="maintain_decisionText"  errorMessage="#{globalResources.missingField}"
                               function="validateEditor('maintain_decisionText');" display="dynamic"/>
            </t:panelGroup>
        </t:panelGroup>
    </t:panelGrid>

<t:panelGrid columns="2" align="center">
    <h:panelGroup>
        <t:commandButton forceId="true" id="SaveButtonId" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" onclick="return validateMyPage();"
                         action="#{pageBeanName.saveDecision}"/>
                         
        <%-- <a4j:jsFunction name="saveJsFunction" action="#{pageBeanName.saveDecision}" reRender="dataT_data_panel" />--%>
    </h:panelGroup>
    <h:panelGroup>
        <t:commandButton type="button" forceId="true" id="backButtonId" onblur="setFocusAtMyFirstElement();"
                         onclick="backJsFunction(); return false;" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"/>
        <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.back}"/>
        <%-- oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage();
             " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/--%>
    </h:panelGroup>
</t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
               value="maintain_regIssuanceDateEdit,30,30:startDateID,0,0:stopDateID,0,0"/>
<t:inputHidden id="MasterDateId" converter="SqlDateConverter" value="#{pageBeanName.allowanceDate}" forceId="true"/>
<t:inputHidden id="currentDateId" converter="SqlDateConverter" value="#{pageBeanName.currentDate}" forceId="true"/>
<t:inputHidden id="lastStopDateId" converter="SqlDateConverter" value="#{pageBeanName.lastStopDate}" forceId="true"/>
<t:inputHidden id="divorcedDateShow" value="#{pageBeanName.divorcedDateShow}" forceId="true"/>
<t:inputHidden id="renderStopReasonGrd" value="#{pageBeanName.renderStopReasonGrd}" forceId="true"/>

<a4j:jsFunction name="updateDecText" action="#{pageBeanName.updateDecText}" oncomplete="initMCEEditor('maintain_decisionText');" reRender="textEditorPGId"/>
<a4j:jsFunction name="updateDecTextAllowanceDate" action="#{pageBeanName.updateDecTextAllowanceDate}" oncomplete="initMCEEditor('maintain_decisionText');" reRender="textEditorPGId"/>
<%--<a4j:jsFunction name="updateDecTextDivorceDate" action="#{pageBeanName.updateDecTextDivorceDate}" oncomplete="initMCEEditor('maintain_decisionText');" reRender="textEditorPGId"/>--%>
<t:inputHidden id="marriageDate" converter="TimeStampConverter" value="#{pageBeanName.marriageDate}" forceId="true"/>

<f:verbatim> 
<script type="text/javascript">
  //setFocusAtMyFirstElement();
  function setFocusAtMyFirstElement() {
      document.getElementById("myForm:content1Div").scrollTop = 0;
      if (document.getElementById('maintain_regTypeAdd') != null)
          setFocusOnlyOnElement('maintain_regTypeAdd');
      else if (document.getElementById('maintain_decisionTitle') != null)
          setFocusOnlyOnElement('maintain_decisionTitle').focus();
  }
  
  initMCEEditor('maintain_decisionText');  
     function initMCEEditor(inputTextIds){
          tinyMCE.init( {
              mode : "exact",
              language: "ar",
              directionality : 'rtl',
              width:"720px",
              elements : inputTextIds,
              menubar: false,
              statusbar: false,
              theme : "modern",
              fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt",
              plugins : ["table fontselect  fontformat directionality", "textcolor colorpicker"], 
              toolbar1 : "table bold italic underline | fontselect fontsizeselect | alignleft aligncenter alignright alignjustify | numlist  forecolor backcolor "
         });
      }
   /*function validateAllScreen(){       
         
          var valid = validatemyForm();
          alert( ' fffffffff '+ valid);
          if( valid ){
          return true ;
          }else{
          return false ;
          }
          
          
          
      }*/
  function getAutoStopDate() {
      //alert("***getAutoStopDate***");
      changeStopReason();
      //updateDecText();
  }

  function validateMyPage() {
      var valid2 = true;
      if (document.getElementById('allowanceResonsList') != null) {
          var selAllowanceResons = document.getElementById('allowanceResonsList').value;
          //alert("motherStopDateGrp selAllowanceResons = "+selAllowanceResons);
          if (selAllowanceResons == '1') {
              valid2 = validatemyForm('motherStopDateGrp');
              //alert("222 motherStopDateGrp valid2 = "+valid2);
          }
      }
      
      var valid3 = true;
      var divorcedDateShow = false;
      if (document.getElementById('divorcedDateShow') != null) {
          divorcedDateShow = document.getElementById('divorcedDateShow').value;
          //alert("divorceDateGrp divorcedDateShow = "+divorcedDateShow);
          if (divorcedDateShow == 'true') {
              valid3 = validatemyForm('divorceDateGrp');
              //alert("333 divorceDateGrp valid3 = "+valid3);
          }
      }
      
      var valid4 = true;
      if (document.getElementById('renderStopReasonGrd') != null) {
          var renderStopReasonGrd = document.getElementById('renderStopReasonGrd').value;
          //alert("divorceDateGrp renderStopReasonGrd = "+renderStopReasonGrd);
          if (renderStopReasonGrd == 'true') {
              valid4 = validatemyForm('stopDateGrp');
              //alert("444 divorceDateGrp valid4 = "+valid4);
              var valid4_222 = true;
              if (document.getElementById('selectedKidsSuspenionElements') != null) {
                  var selStopReason = document.getElementById('selectedKidsSuspenionElements').value;
                  //alert("stop_Date_Grp222 selStopReason = "+selStopReason);
                  if (selStopReason != '1' && selStopReason != '2' && selStopReason != '3'  && selStopReason != '5' && selStopReason != '10' ) {/** these cases stop date may be on future */
                    //alert("stop_Date_Grp222 selStopReason selStopReason != '1' && selStopReason != '2' && selStopReason != '3' = "+(selStopReason != '1' && selStopReason != '2' && selStopReason != '3'));
                    valid4_222 = validatemyForm('stop_Date_Grp222');
                  }
              }
              
            var valid4_333 = true;
              if (divorcedDateShow == 'true') {
                valid4_333 = validatemyForm('stopDate_Grp333');
                //  alert("stopDate_Grp333 valid4_333 = "+valid4_333);
              }
              
              valid4 = valid4 && valid4_222 && valid4_333;
          }
      }
      
      var valid = false;
      valid = validatemyForm();
      //alert("validateMyPage valid = "+valid);
      valid = valid && valid2 && valid3 && valid4;
      //alert(" KLKLKL validateMyPage valid = "+valid);
      return valid;
  }
</script>
</f:verbatim>