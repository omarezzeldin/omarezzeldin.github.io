<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
 
    <h:form id="myForm" binding="#{adminAssignFinishListBean.frm}">
       <t:aliasBean alias="#{pageBeanName}" value="#{adminAssignFinishListBean}">
                
        <t:saveState value="#{pageBeanName.searchDTO}"/>
        <t:saveState value="#{pageBeanName.assignReasonsDTOList}"/>
        
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
        
      <tiles:insert definition="adminassignfinishlist.page" flush="false">
      </tiles:insert>
       <t:panelGroup id="scriptPnl">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
  </t:aliasBean>
    </h:form>
  
</f:view>