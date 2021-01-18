<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{qualificationBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{qualificationBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{qulListIntegrationBean}">
                <tiles:insert definition="qualificationsList.page" flush="false"></tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptGeneratorID">
            <scriptgenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>