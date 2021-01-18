<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/>
    
    <h:form id="myForm" binding="#{proceedingCandidateListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{proceedingCandidateListBean}" id="alias3">
            <tiles:insert definition="advancedsearchcandidates.page" flush="false">
                <!-- Start Paging -->
                <t:saveState value="#{proceedingCandidateListBean.currentPageIndex}"/>
                <t:saveState value="#{proceedingCandidateListBean.oldPageIndex}"/>
                <t:saveState value="#{proceedingCandidateListBean.sorting}"/>
                <t:saveState value="#{proceedingCandidateListBean.usingPaging}"/>
                <t:saveState value="#{proceedingCandidateListBean.updateMyPagedDataModel}"/>
                <t:saveState value="#{proceedingCandidateListBean.resettedPageIndex}"/>
                
                <t:saveState value="#{proceedingCandidateListBean.pagingRequestDTO}"/>
                
                <t:saveState value="#{proceedingCandidateListBean.pagingBean.totalListSize}"/>
                <t:saveState value="#{proceedingCandidateListBean.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{proceedingCandidateListBean.pagingBean.preUpdatedDataModel}"/>
                <!-- End Paging -->
                
                <t:saveState value="#{proceedingCandidateListBean.fromModuleName}"/>
                <t:saveState value="#{proceedingCandidateListBean.miniCode}"/>
                
                <t:saveState value="#{proceedingCandidateListBean.selectedCandType}" id="selectedCandTypeSt"/>
                
                <t:saveState value="#{proceedingCandidateListBean.lovBaseBean.searchTyp}"/>
                <t:saveState value="#{proceedingCandidateListBean.lovBaseBean.searchQuery}"/>
                <t:saveState value="#{proceedingCandidateListBean.lovBaseBean.searchMode}"/>
                
            </tiles:insert>
        </t:aliasBean>
        
        <script type="text/javascript"> 
            if( (!isVisibleComponent('lookupAddDiv')) && (!isVisibleComponent('lookupEditDiv'))){
                setFocusOnElement('canditatesDateFrom');
            }
        </script>
        
    </h:form>
    
    <c2:scriptGenerator form="myForm"/>
    
</f:view>