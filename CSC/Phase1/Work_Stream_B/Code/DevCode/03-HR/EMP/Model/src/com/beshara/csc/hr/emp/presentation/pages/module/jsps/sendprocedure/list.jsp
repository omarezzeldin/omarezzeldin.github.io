<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
  <t:aliasBean alias="#{pageBeanName}" value="#{sendProcedureListBean}">
    <h:form id="myForm" binding="#{pageBeanName.frm}">
      <tiles:insert definition="sendProdureList.page" flush="false">
      
        <%-- start paging --%>
        <t:saveState value="#{pageBeanName.currentPageIndex}"/>
        <t:saveState value="#{pageBeanName.oldPageIndex}"/>
        <t:saveState value="#{pageBeanName.sorting}"/>
        <t:saveState value="#{pageBeanName.usingPaging}"/>
        <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
        <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
        <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
        <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
        <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
        <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
        <%-- end paging --%>
      <t:saveState  value="#{pageBeanName.fullColumnName}" />
    <t:saveState  value="#{pageBeanName.sortAscending}" />
    <t:saveState  value="#{pageBeanName.saveSortingState}" />
    <t:saveState  value="#{pageBeanName.sortColumn}" />
      </tiles:insert>
    </h:form>
  </t:aliasBean>
</f:view>