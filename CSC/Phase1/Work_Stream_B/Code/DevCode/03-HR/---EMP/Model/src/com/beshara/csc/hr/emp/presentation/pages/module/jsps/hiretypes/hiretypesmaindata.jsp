<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

<!-- added by M.abdelsabour -->
    <f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration"
                  var="orgIntgResources"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{hireTypesMainData.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{hireTypesMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{hireTypesMainData}">
             <!-- added by M.abdelsabour for integration-->
        <t:aliasBean alias="#{minHelperBeanName}" value="#{hireTypesMainData.ministryHelper}">
                <t:aliasBean alias="#{jcHelperBeanName}" value="#{hireTypesMainData.jcHelper}">
                <tiles:insert definition="hireTypesdatamaintain.page" flush="false">
                    <t:saveState value="#{detailBeanName.selectedTabregSer}"/>
                   <t:saveState value="#{detailBeanName.conditionsDTO}"/>
                    <t:saveState value="#{detailBeanName.jcHelper}"/>
                    <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}" id="mainpstep"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
                    <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
                    <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
                    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
                    <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
                    <t:saveState value="#{detailBeanName.codeNotFoundMsg}"/>
                    <t:saveState value="#{detailBeanName.ministryCode}"/>
                    <t:saveState value="#{detailBeanName.minCodeNotFound}"/>
                    <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
                    <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
                    <t:saveState value="#{detailBeanName.currentDetails}"/>
                    <t:saveState value="#{detailBeanName.availableDetails}"/>
                    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
                    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
                    <t:saveState value="#{detailBeanName.masterEntityKey}" id="mainentitykey"/>
                    <t:saveState value="#{detailBeanName.searchTypeStr}"/>
                    <t:saveState value="#{detailBeanName.conditionCode}"/>
                    <t:saveState value="#{detailBeanName.publicCondition}"/>
                   
                    <t:saveState value="#{hireTypesListBean.fullColumnName}"/>
                    <t:saveState value="#{hireTypesListBean.sortAscending}"/>
                    <t:saveState value="#{hireTypesListBean.saveSortingState}"/>
                    <t:saveState value="#{hireTypesListBean.sortColumn}"/>
                    <!-- added by m.abdelsabour-->
                   <t:saveState value="#{hireTypesMainData.ministryHelper}"/>
                    <t:saveState value="#{hireTypesMainData.minDTO}"/>
                     <t:saveState value="#{hireTypesMainData.selectedMinDTO}"/>
                
                    <tiles:put name="titlepage" type="string"
                               value="${pageBeanName.maintainMode == 1 ? 'hireTypesList_title_edit' : 'hireTypesList_title_add'  }"/>
                </tiles:insert>
            </t:aliasBean>
            </t:aliasBean>
        </t:aliasBean>
        </t:aliasBean>
        <c:scriptGenerator form="myForm"/>
        <f:verbatim>
            <script type="text/javascript">
              setTimeout("setFocusAtMyFirstElement()", 700);

              function setFocusAtMyFirstElement() {

                  if (!isVisibleComponent('lookupAddDiv')) {

                      //alert(isVisibleComponent('lookupAddDiv'));
                      if (document.getElementById('hireTypeName') != null) {
                          document.getElementById('hireTypeName').focus();
                          document.getElementById('hireTypeName').focus();
                      }
                  }
                  else {
                      //alert("sss");
                      document.getElementById('add_first_inputTxt').focus();

                  }
              }
              
 
            </script>
        </f:verbatim>
    </h:form>
</f:view>
