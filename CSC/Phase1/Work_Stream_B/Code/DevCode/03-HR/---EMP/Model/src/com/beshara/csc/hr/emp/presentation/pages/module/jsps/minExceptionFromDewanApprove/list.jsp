<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <t:aliasBean alias="#{pageBeanName}" value="#{exceptionFromDewanListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <tiles:insert definition="exceptionFromDewanList.page" flush="false">
                <t:saveState value="#{pageBeanName.extraDataTypesList}"/>
                <t:saveState value="#{pageBeanName.extraDataTypesValue}"/>
                <t:saveState value="#{pageBeanName.extraDataTypesName}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.myDataTable}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:inputHidden value="#{pageBeanName.selectedListSize}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.addBefore}"/>
                <t:saveState value="#{pageBeanName.selectedCategory}"/>
                <t:saveState value="#{pageBeanName.selectedMinistry}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                <t:saveState value="#{pageBeanName.ministryList}"/>
                <t:saveState value="#{pageBeanName.statusActive}"/>
                <t:saveState value="#{pageBeanName.wcBgtList}"/>
                <t:saveState value="#{pageBeanName.wcBgtValue}"/>
                
            </tiles:insert>
        </h:form>
    </t:aliasBean>
    <t:panelGroup forceId="true" id="scriptGeneratorGrp">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>