<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
 <h:form id="myForm" binding="#{employmentRequestEnquiry.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{employmentRequestEnquiry}">
       
            <tiles:insert definition="empReqEnquiryList.page" flush="false">
                
             <%-- start paging --%>
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                 <t:saveState value="#{pageBeanName.sortAscending}"/>
                   <t:saveState value="#{pageBeanName.bsnSortingColumnName}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{pageBeanName.selectedMinistry}"/>
                <t:saveState value="#{pageBeanName.selectedCategory}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                <t:saveState value="#{pageBeanName.ministryList}"/>
                 <t:saveState value="#{pageBeanName.civilExist}"/>
                 <t:saveState value="#{pageBeanName.civilName}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                 <t:saveState value="#{pageBeanName.validCivilId}"/>
                  <t:saveState value="#{pageBeanName.searchDTO}"/>
                <%-- end paging --%>
            </tiles:insert>
    
    </t:aliasBean>
        </h:form>
    <t:panelGroup forceId="true" id="scriptGeneratorGrp">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>