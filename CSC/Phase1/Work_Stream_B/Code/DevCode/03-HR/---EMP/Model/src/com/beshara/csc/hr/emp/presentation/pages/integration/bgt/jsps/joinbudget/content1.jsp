<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" id="budget_content" forceId="true">
        
        <h:outputText value="#{intgBundle.element}" styleClass="divtext" rendered="#{pageBeanName.showSelectedElementGuide}"/>
        <t:panelGroup colspan="5" rendered="#{pageBeanName.showSelectedElementGuide}" >
            <t:inputText forceId="true" id="selectedItemCode"  value="#{pageBeanName.selectedElementCode}" styleClass="textboxsmall" disabled="true"/>
            <t:inputText forceId="true" id="selectedItemName"  value="#{pageBeanName.selectedElementName}" styleClass="textboxlarge2" style="width:518px;" disabled="true"/>
        </t:panelGroup>
        <%--<h:outputText value="#{intgBundle.cader}" styleClass="divtext"/>--%>
            <%--<t:panelGroup colspan="5">

                <t:inputText forceId="true" id="selectedBudget"  value="#{pageBeanName.selectedBudgetDTO.name}" styleClass="textboxmedium" disabled="true"/>
          </t:panelGroup>--%>
        <h:outputText value="#{intgBundle.cader}" styleClass="divtext"/>
            <t:panelGroup>
                <t:selectOneMenu  forceId="true" id="caderMenu" styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedCader}">
                    <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{pageBeanName.caders}" itemLabel="#{caders.name}" itemValue="#{caders.code.elmguideCode}" var="caders"/>
                    <a4j:support actionListener="#{pageBeanName.getGroupsByCader}" event="onchange" reRender="groupMenu, degreeMenu"/>
                </t:selectOneMenu>
                <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
                <%--<f:verbatim><BR/></f:verbatim>--%>
                <%--<c:compareValidator componentToValidate="caderMenu" componentToCompare="virtualLongValue" operator="not" display="dynamic"
                                    errorMessage="#{globalResources.missingField}" highlight="false" />--%>
            </t:panelGroup>
            <h:outputText value="#{intgBundle.group}" styleClass="divtext"/>
            <t:panelGroup>
                <t:selectOneMenu  forceId="true" id="groupMenu" styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedGroup}">
                    <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{pageBeanName.groups}" itemLabel="#{groups.name}" itemValue="#{groups.code.elmguideCode}" var="groups"/>
                    <a4j:support actionListener="#{pageBeanName.getDegreesByGroup}" event="onchange" reRender="degreeMenu"/>
                </t:selectOneMenu>
                <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
                <%--<f:verbatim><BR/></f:verbatim>--%>
                <%--<c:compareValidator componentToValidate="groupMenu" componentToCompare="virtualLongValue" operator="not" display="dynamic"
                                    errorMessage="#{globalResources.missingField}" highlight="false" />--%>
            </t:panelGroup>
            <h:outputText value="#{intgBundle.Degree}" styleClass="divtext"/>
            <t:panelGroup >
                <t:selectOneMenu  forceId="true" id="degreeMenu" styleClass="DropdownboxMedium2"
                                 value="#{pageBeanName.selectedDegree}">
                    <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{pageBeanName.degrees}" itemLabel="#{degrees.name}"
                                   itemValue="#{degrees.code.elmguideCode}" var="degrees"/>
                </t:selectOneMenu>
                <%--<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
                <%--<f:verbatim><BR/></f:verbatim>--%>
                <%--<c:compareValidator componentToValidate="degreeMenu" componentToCompare="virtualLongValue" operator="not" display="dynamic"
                                    errorMessage="#{globalResources.missingField}" highlight="false" />--%>
            </t:panelGroup>
            
            <h:outputText value="#{intgBundle.budgetItem}" styleClass="divtext"/>
            <t:panelGroup colspan="5" id="bgtpnlsrch" forceId="true">
            
          <t:inputText forceId="true" id="filterBgtCodeID_srch" styleClass="filteration_input_text"
                     value="#{pageBeanName.filterBgtCode_srch}" onkeyup="enabelIntegerOnly(this);"
                     style="margin-left:5px;" 
                     onkeypress="filterDivInputOnKeyPress(event,this,filterBgtCode_srch,null);"
                     onblur="filterDivInput('filterBgtCodeID_srch',filterBgtCode_srch,null);">
          
            <a4j:jsFunction name="filterBgtCode_srch" actionListener="#{pageBeanName.filterByBgtCode_srch}"
                            reRender="bgtpnlsrch,scriptGenerator"/>
        </t:inputText>
        
                <t:inputText forceId="true" id="selectedBudget"  value="#{pageBeanName.selectedBudgetDTO.name}" styleClass="textboxmedium" disabled="true"/>
                <a4j:commandButton value="..."  styleClass="cssButtonSmall" 
                    action="#{pageBeanName.openTreeDiv}"  
                    oncomplete="changeVisibilityDiv(window.blocker,window.divTree,'backButtonTreeDiv');focusOnSearchText();" 
                    reRender="divTree" id="selectBudget" />
                <a4j:commandButton value="#{intgBundle.clear}"  styleClass="cssButtonSmall" 
                    action="#{pageBeanName.resetSelectedBudget}"  
                    reRender="filterBgtCodeID_srch,selectedBudget,bgtpnlsrch" id="resetBudget" />
          <f:verbatim><BR/></f:verbatim>
        
         <t:outputLabel id="filterErrId_srch" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                       styleClass="error" rendered="#{pageBeanName.wrongfilterBgtCode_srch}"/>
    
            </t:panelGroup>
            
            <%-- no need to use defaultValue in filteration --%>
            <%--<h:outputText value="#{intgBundle.defaultValue}" styleClass="divtext"/>--%>
            <%--<t:selectBooleanCheckbox id="default_value" value="#{pageBeanName.defaultFlag}" />--%>
            
            
       
</t:panelGrid>
<t:panelGroup style="text-align:center;display: block;" id="btns_panel" forceId="true">
<t:commandButton value="#{intgBundle.view}" styleClass="cssButtonSmall" action="#{pageBeanName.getAll}"  />

<%--<t:commandButton value="#{intgBundle.save}" styleClass="cssButtonSmall" action="#{pageBeanName.addBgtAcctElmguides}" onclick="return validatemyForm();" rendered="#{pageBeanName.pageMode != 1}"/>--%>
<%--<t:commandButton value="#{intgBundle.edit}" styleClass="cssButtonSmall" action="#{pageBeanName.edit}" onclick="return validatemyForm();" rendered="#{pageBeanName.pageMode == 1}"/>--%>
<%--<t:commandButton value="#{intgBundle.cancel}" styleClass="cssButtonSmall" action="#{pageBeanName.reset}" rendered="#{pageBeanName.pageMode == 1}"/>--%>
<%--<t:commandButton value="#{globalResources.back}" styleClass="cssButtonSmall" action="#{pageBeanName.back}"  />--%>
</t:panelGroup>
<t:inputHidden id="virtualLongValue" forceId="true" converter="javax.faces.Long" value="#{pageBeanName.virtualLongValue}" />
