<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup rendered="#{pageBeanName.fromModuleName != 'emp'}">
    <t:panelGrid columns="4" id="proceedCandidateContiner" rowClasses="row01,row02" width="100%" border="0" cellpadding="0" cellspacing="0" >
        
        <h:outputText value="#{resourcesBundle.category}:" styleClass="lable01"/>
        <t:selectOneMenu  id="categoryList" forceId="true" styleClass="DropdownboxMedium2" value="#{pageBeanName.catCode}" converter="javax.faces.Long">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
            <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList" itemValue="#{categoryList.code.catCode}" itemLabel="#{categoryList.name}"/>
            <a4j:support event="onchange" reRender="ministryList, dataT_data_panel, OperationBar, paging_panel" action="#{pageBeanName.fillMisitriesByCatCode}"/>
        </t:selectOneMenu>    
          
        <h:outputText value="#{resourcesBundle.ministry_name}:" styleClass="lable01"/>    
        <h:panelGroup>
            <t:selectOneMenu styleClass="DropdownboxMedium2" id="ministryList" value="#{pageBeanName.miniCode}" forceId="true" converter="javax.faces.Long" disabled="#{pageBeanName.catCode == null}" >
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Item_Select}"/>
                <t:selectItems value="#{pageBeanName.ministryList}" var="ministryList" itemValue="#{ministryList.code.minCode}" itemLabel="#{ministryList.name}"/>
                <a4j:support event="onchange" reRender="dataT_data_panel,OperationBar,paging_panel" action="#{pageBeanName.fillDataTableByMinCode}"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </h:panelGroup>
        
    </t:panelGrid>
</t:panelGroup>


<t:panelGroup rendered="#{pageBeanName.fromModuleName == 'emp'}">
    <t:panelGrid rowClasses="row01,row02" width="100%" border="0" style="text-align:right" cellpadding="0" cellspacing="0">
        <t:panelGrid columns="2" id="proceedCandidateContinerEmp" border="0" cellpadding="2" cellspacing="0" style="text-align:right">
            <h:outputText value="#{empBundle.candStatus}" styleClass="lable01"/>
            <t:selectOneMenu  id="candTypesList" forceId="true" styleClass="DropdownboxxLarge" value="#{pageBeanName.selectedCandType}">
                <f:selectItem itemValue="#{pageBeanName.hireSuggJobandDegree}" itemLabel="#{empBundle.hireSuggJobandDegree}"/>
                <f:selectItem itemValue="#{pageBeanName.acceptJobAndNameInPro}" itemLabel="#{empBundle.acceptJobAndNameInPro}"/>
                <f:selectItem itemValue="#{pageBeanName.hireStageJobNameAndFinDegree}" itemLabel="#{empBundle.hireStageJobNameAndFinDegree}"/>
                <f:selectItem itemValue="#{pageBeanName.rejectOrderToWorkMinistry}" itemLabel="#{empBundle.rejectOrderToWorkMinistry}"/>
                <a4j:support event="onchange" reRender="ministryList, dataT_data_panel, OperationBar, paging_panel" action="#{pageBeanName.fillDataTableByCandType}"/>
            </t:selectOneMenu>
        </t:panelGrid>
    </t:panelGrid>
</t:panelGroup>
