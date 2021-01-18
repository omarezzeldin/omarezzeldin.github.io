<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    
    <t:aliasBean alias="#{pageBeanName}" value="#{hireTypesListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
         <t:saveState  value="#{hireTypesListBean.fullColumnName}" />
            <t:saveState  value="#{hireTypesListBean.sortAscending}" />
            <t:saveState  value="#{hireTypesListBean.saveSortingState}" />
            <t:saveState  value="#{hireTypesListBean.sortColumn}" />
        
            <%-- <a4j:log popup="false" level="ALL" height="400" width="800"></a4j:log>--%>
            <tiles:insert definition="hireTypesList.page" flush="false">
                <t:messages/>
            </tiles:insert>
        </h:form>
        <c2:scriptGenerator form="myForm"/>
    </t:aliasBean>
    <t:saveState value="#{hireTypesListBean.selectedDTOS}"/>
</f:view>