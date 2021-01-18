<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

<f:view locale="#{shared_util.locale}">
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>


    <t:aliasBean alias="#{pageBeanName}" value="#{externalExperienceListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <tiles:insert definition="externalexperience.page" flush="false">
            <t:saveState value="#{pageBeanName.civilId}"/>
             <t:saveState value="#{pageBeanName.validCivilId}"/>
             <t:saveState value="#{pageBeanName.civilExist}"/>
             <t:saveState value="#{pageBeanName.validEmpStatus}"/>
             <t:saveState value="#{pageBeanName.enableEmpLovDiv}"/>
             <t:saveState value="#{pageBeanName.personQulDTO}"/>
             <t:saveState value="#{pageBeanName.empName}"/>
             <t:saveState value="#{pageBeanName.emp}"/>
             <t:saveState value="#{pageBeanName.searchDTO}"/>
              <t:saveState value="#{externalExperienceListBean.pageMode}"/>
            </tiles:insert>
            <t:panelGroup forceId="true" id="scriptPanelId">  
                 <c:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </t:aliasBean>
</f:view>
