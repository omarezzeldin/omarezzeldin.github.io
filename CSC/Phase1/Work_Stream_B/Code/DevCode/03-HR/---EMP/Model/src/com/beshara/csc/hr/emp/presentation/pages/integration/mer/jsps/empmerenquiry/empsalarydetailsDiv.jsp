<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
                                        <htm:div styleClass="popup_body">

   <htm:div styleClass="popup_inner_title">
            <h:outputText value="#{integrationBundle.basic_salary_details}"/>
    </htm:div>
    </htm:div>
    
<t:panelGrid forceId="true" id="cnt1Panel2" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="0">
             
    <h:outputText forceId="true" id="degreeFirstId" value="#{integrationBundle.degreeFirstLabel}" styleClass="divtext"/>
    <t:inputText forceId="true" disabled="true" id="degreeFirstValue" styleClass="textbox"
                 value="#{pageBeanName.degreeFirstValue}" converter="FloatThreeDigitConverter"/>
                 
    <h:outputText forceId="true" id="raisesTotalValueId" value="#{integrationBundle.raisesTotalValueLabel}"
                  styleClass="divtext"/>
    <t:inputText forceId="true" disabled="true" id="raisesTotalValue" styleClass="textbox"
                 value="#{pageBeanName.raisesTotalValue}" converter="FloatThreeDigitConverter" />
    
     <h:outputText forceId="true" id="promotion_diff" value="#{integrationBundle.promotion_diff}"
                   styleClass="divtext"/>
    <t:inputText forceId="true" disabled="true" id="promotion_diffValue" styleClass="textbox"
                value="#{pageBeanName.promotionDiffValue}" converter="FloatThreeDigitConverter" />
                
    <h:outputText forceId="true" id="salary_diff" value="#{integrationBundle.salary_diff}"
                 styleClass="divtext"/>
    <t:inputText forceId="true" disabled="true" id="salary_diffValue" styleClass="textbox"
                 value="#{pageBeanName.salaryDiffValue}" converter="FloatThreeDigitConverter" />
             
</t:panelGrid>
<t:commandButton forceId="true" id="backButtonCustomDiv1" styleClass="cssButtonSmall" value="#{globalResources.back}"
                 onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false;"/>