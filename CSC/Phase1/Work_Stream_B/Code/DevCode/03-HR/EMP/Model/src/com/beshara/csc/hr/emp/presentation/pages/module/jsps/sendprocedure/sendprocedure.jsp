<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
 <t:aliasBean alias="#{pageBeanName}" value="#{sendProcedure}">
  <h:form id="myForm" binding="#{pageBeanName.frm}">
   <t:saveState  value="#{sendProcedureListBean.fullColumnName}" />
    <t:saveState  value="#{sendProcedureListBean.sortAscending}" />
    <t:saveState  value="#{sendProcedureListBean.saveSortingState}" />
    <t:saveState  value="#{sendProcedureListBean.sortColumn}" />
   <tiles:insert definition="sendProdure.page" flush="false">
   </tiles:insert>
  </h:form>
  <c2:scriptGenerator form="myForm"/>
 </t:aliasBean>
</f:view>