<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">

    <t:panelGroup rendered="#{pageBeanName.selCountry==101}">
        <t:outputText  styleClass="msg warning" value="#{pageBeanName.stopConfirmationMsg}" />
                <f:verbatim><br/></f:verbatim>

    </t:panelGroup>
    
    <t:panelGroup forceId="true" id="stopDateDiv" rendered="#{pageBeanName.selCountry!=101}" >
        <h:outputText id="code"  style="margin-left:10px;" styleClass="lable01" value="#{intgBundle.stop_kw_insurance_Stop_date}"/>
        <t:panelGroup >
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" value="#{pageBeanName.stopDate}" id="stopValuesDate" size="20"
                             onchange="return validatemyForm();" maxlength="10" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             onblur="return validatemyForm();" styleClass="textbox"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" tabindex="3"
                             title="#{globalResources.inputCalendarHelpText}">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <c2:requiredFieldValidator componentToValidate="stopValuesDate" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" />
            <c2:dateFormatValidator componentToValidate="stopValuesDate" display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}" />
            <c2:compareDateValidator componentToValidate="currentDateId" componentToCompare="stopValuesDate"
                                     display="dynamic"
                                     errorMessage="#{intgBundle.stop_Date_error_message}"
                                     operator="after" highlight="false"
                                     />
           <c2:compareDateValidator componentToValidate="stopValuesDate" componentToCompare="fromDateId"
                                     display="dynamic"
                                     errorMessage="#{intgBundle.stop_Date_error_message_fromDate}"
                                     operator="after" highlight="false"
                                     />
        </t:panelGroup>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" id="OkupdateStopDateDiv" styleClass="cssButtonSmall" onclick="return validatemyForm();"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.updateStopDate}"/>
        <t:commandButton forceId="true" id="backButtonCustomDiv3" styleClass="cssButtonSmall"  
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv3,null,null); return false;"/>
      <%--<t:commandButton forceId="true" id="backButtonCustomDiv2" styleClass="cssButtonSmall" rendered="#{!pageBeanName.kwtPage}"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv2,null,null); return false;"/>--%>
                         
    </t:panelGrid>
    <t:inputHidden id="currentDateId" converter="SqlDateConverter" value="#{pageBeanName.currentDate}" forceId="true"/>
    <t:inputHidden id="fromDateId" converter="SqlDateConverter" value="#{pageBeanName.fromDate}" forceId="true"/>

</t:panelGrid>
