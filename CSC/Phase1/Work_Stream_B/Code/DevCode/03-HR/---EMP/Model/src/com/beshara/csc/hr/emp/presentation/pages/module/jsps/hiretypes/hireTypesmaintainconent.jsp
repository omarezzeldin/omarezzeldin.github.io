<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="grsIntgResources"/>
<t:panelGrid columns="1" width="100%" forceId="true" id="container">
    <t:panelGrid columns="2" align="center" width="100%" styleClass="divtext" rowClasses="row02,row01" cellspacing="0"
                 cellpadding="3">
        <t:outputText value="#{resourcesBundle.parent_hire_type}" styleClass="divtext"
                      rendered="#{hireTypesMaintainBean.pageDTO.parentHireType!= null}"/>
        <t:panelGroup rendered="#{hireTypesMaintainBean.pageDTO.parentHireType!= null}">
            <t:inputText id="hireTypeNameparent" forceId="true" onchange="trimBorders(hireTypeName);"
                         value="#{hireTypesMaintainBean.pageDTO.parentHireType.name}" styleClass="textboxlarge"
                         disabled="true"/>
        </t:panelGroup>
        <!--######### Row 1 #########-->
        <t:outputText value="#{resourcesBundle.hireTypeName}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText id="hireTypeName" tabindex="1" forceId="true"   onchange="trimBorders(hireTypeName);"
                          value="#{hireTypesMaintainBean.pageDTO.name}" styleClass="textboxlarge"
                        
                         maxlength="#{pageBeanName.nameMaxLength}"  disabled="#{hireTypesMaintainBean.pageDTO.parentHireType == null}"/>
            <t:outputText value=" * " styleClass="defaultValidationMsgClass"/>
            <%-- f:verbatim><br/></f:verbatim--%>
            <f:verbatim>&nbsp; &nbsp;</f:verbatim>
            <c2:requiredFieldValidator componentToValidate="hireTypeName" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
        </t:panelGroup>
            <t:panelGroup rendered="#{hireTypesMaintainBean.pageDTO.parentHireType!= null}">
                <t:outputText id="min_label" forceId="true" value="#{resourcesBundle.ministry}" styleClass="lable01"/>
            </t:panelGroup>
            <t:panelGroup rendered="#{hireTypesMaintainBean.pageDTO.parentHireType!= null}">
   <t:inputText id="minCodeId" tabindex="2" value="#{detailBeanName.ministryCode}" forceId="true"
                             disabled="#{pageBeanName.maintainMode!=0}"
                             onkeypress="enabelIntegerOnlyWithZero(this);filterById(event);" styleClass="textboxsmall"
                             style="margin-left: 7px;"></t:inputText>
                 <a4j:jsFunction name="getMinByCode" action="#{detailBeanName.fillMinById}" id="functionGetMin"
                                reRender="ministryAdd,minCodeErrMsg" oncomplete="showvalidate_msg();"/>
                <t:inputText forceId="true" id="ministryAdd" value="#{hireTypesMaintainBean.pageDTO.ministriesDTO.name}"
                             disabled="true" styleClass="textboxlarge" style=" width: 253px;"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <a4j:commandButton value="..." styleClass="cssButtonSmall" type="button"
                                   action="#{minHelperBeanName.openMinistriesDiv}"
                                   tabindex="3"
                                   rendered="#{pageBeanName.maintainMode==0}"
                                   reRender="ministryDiv ,minCodeId,lov_dataT_data_panel,lovLinkStatusPanel,javaScriptCallerOutputText"
                                   oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtDivSearch();"
                                   id="searchMinistryBtn"/>
                <t:outputText value=" * " styleClass="defaultValidationMsgClass" rendered="#{pageBeanName.maintainMode==0}"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <c2:requiredFieldValidator componentToValidate="ministryAdd" active="#{pageBeanName.maintainMode!=0}"
                                           errorMessage="#{globalResources.missingField}" display="dynamic"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true" style="display:none;"
                              styleClass="mandatoryAsterisk"/>
        
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.decisionIsMust}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectBooleanCheckbox value="#{detailBeanName.mustDecision}" forceId="true" id="DecisionID" tabindex="4" onkeydown="onKeyDownFirstElement(event,'NextButtonManyToMany',null)"
                                     checked="#{detailBeanName.mustDecision}"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGrid>
