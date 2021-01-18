<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
 <h:form id="myForm" binding="#{mainDataIllegalCand.frm}">
<t:aliasBean alias="#{pageBeanName}" value="#{mainDataIllegalCand}">
   <t:aliasBean alias="#{detailBeanName}" value="#{mainDataCandidateBean}">
          <tiles:insert definition="mainDataIllegalCand.page" flush="false">
          
       </tiles:insert>
   </t:aliasBean>
</t:aliasBean>
 </h:form>
</f:view>