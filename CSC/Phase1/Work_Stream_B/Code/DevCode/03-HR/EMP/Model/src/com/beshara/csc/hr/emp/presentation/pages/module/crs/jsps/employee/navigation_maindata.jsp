<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGrid align="center" columns="1">
    <t:commandButton id="finish_btn" forceId="true" onblur="document.getElementById('reset_btn').focus();" 
        value="#{globalResources.FinishButton}" action="#{pageBeanName.finish}" styleClass="cssButtonSmall" 
        disabled="#{!empMainDataBean_crs.civilExist || empMainDataBean_crs.invalidCivilID || empMainDataBean_crs.nonDBCivilID || empMainDataBean_crs.invalidCivilWithHireType}" 
        onclick="z= validateSearchData_comboBox(null,'employees_hiretypes','virtualvalueId','outputErrMsg','#{globalResources.missingField}'); x= checkExactLength('12','employees_civilIdAdd','civilLengthError','#{resourcesBundle.civilLengthError}'); y = isIntegerValueWithRequiredParam('employees_civilIdAdd','civilLengthError','#{resourcesBundle.civilLengthError}','#{resourcesBundle.civilLengthError}','true'); if ( x && y && z) {return  stepValidation();} else {return false;};document.getElementById('reset_btn').focus();"/>
</t:panelGrid>
<t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/> 