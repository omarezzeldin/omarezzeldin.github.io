<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions"
                  var="globalexceptions"/>
    <h:form id="myForm" binding="#{conditionIntgLineSub.frm}">
        <t:aliasBean alias="#{contianerBean}" value="#{conditionsIntgMaintainBean}">
            <t:aliasBean alias="#{pageBeanName}" value="#{conditionIntgLineSub}">
                <t:aliasBean alias="#{detailBeanName}" value="#{conditionIntgLineDetailBean}">
                    <tiles:insert definition="conditionIntgLineSub.page" flush="false">
                        <t:saveState value="#{conditionsIntgMaintainBean.listBeanObject}"/> 
                        <t:saveState value="#{pageBeanName.selectedRow}"/>
                        <t:saveState value="#{wizardbar.wizardSteps}"/>
                        <t:saveState value="#{wizardbar.currentStep}"/>
                        <t:saveState value="#{wizardbar.configurationId}"/>
                        <t:saveState value="#{contianerBean.pageDTO}"/>
                        <t:saveState value="#{contianerBean.nextNavigationCase}"/>
                        <t:saveState value="#{contianerBean.previousNavigationCase}"/>
                        <t:saveState value="#{contianerBean.finishNavigationCase}"/>
                        <t:saveState value="#{contianerBean.currentNavigationCase}"/>
                        <t:saveState value="#{contianerBean.currentStep}"/>
                        <t:saveState value="#{contianerBean.maintainMode}"/>
                        <t:saveState value="#{contianerBean.renderSave}"/>
                        <t:saveState value="#{contianerBean.renderFinish}"/>
                        <t:saveState value="#{detailBeanName.availableDetails}"/>
                        <t:saveState value="#{detailBeanName.currentDetails}"/>
                        <t:saveState value="#{detailBeanName.joinConditions}"/>
                        <t:saveState value="#{detailBeanName.currentDataTable}"/>
                        <t:saveState value="#{pageBeanName.rowNo}"/>
                        <t:saveState value="#{pageBeanName.lineParamter}"/>
                        <t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
                        <%-- t:saveState value="#{detailBeanName.condition}"/--%>
                        
                        <t:saveState value="#{wizardbar.wizardSteps}"/>
                        <t:saveState value="#{wizardbar.configurationId}"/>
                        <t:saveState value="#{wizardbar.currentStep}"/>
                        
                        <t:saveState value="#{conditionsIntgMaintainBean.targetForJoinConditionDTO}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.allowMultiCondition}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.transactionName}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.restoreMethod}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.objectCode}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.hmObjects}"/>
                    </tiles:insert>
                </t:aliasBean>
            </t:aliasBean>
        </t:aliasBean>
        <c2:scriptGenerator form="myForm"/>
    </h:form>
    <script type="text/javascript">
 setFocusAtMyFirstElement();

      function setFocusAtMyFirstElement() {
          if (document.getElementById('codeSearch') != null)
              setFocusOnlyOnElement('codeSearch').focus();
      }
    </script>
</f:view>