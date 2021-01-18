<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <t:aliasBean alias="#{pageBeanName}" value="#{hireStagesListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <%-- <a4j:log popup="false" level="ALL" height="400" width="800"></a4j:log>--%>
            <tiles:insert definition="hirestagelist.page" flush="false">
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.myDataTable}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>                        
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedRadio}"/>
                <t:saveState value="#{pageBeanName.selectedRadio}"/>
            </tiles:insert>
        </h:form>
    </t:aliasBean>
</f:view>
