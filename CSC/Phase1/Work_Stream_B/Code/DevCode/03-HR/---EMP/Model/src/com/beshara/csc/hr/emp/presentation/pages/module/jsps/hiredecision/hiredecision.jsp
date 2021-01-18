<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <t:aliasBean alias="#{pageBeanName}" value="#{hireDecisionListBean}">
        <t:aliasBean alias="#{jdHelperBeanName}" value="#{hireDecisionListBean.jdHelper}">
            <t:aliasBean alias="#{conditionLinesdetailsHelperBean}" value="#{hireDecisionListBean.cdIntegrationBean}">
                <h:form id="myForm" binding="#{pageBeanName.frm}">

     <!-- start CSC-23106 -->
         <script type="text/javascript">
            var ctxPath = '<%=request.getContextPath()%>';
            /*function openGrsIntgConditionlinesDetailsWindow() {
              var moduleName = '${shared_util.contextPath}';
              openNewWindowNew('cdLinesIntegrationFullURL_Id', moduleName, 'vacRequest_WindowTitleId');
            }*/
        </script>
    <!-- end CSC-23106 -->

                    <script type="text/javascript">
                      function openGrsIntgConditionlinesDetailsWindow() {
                          var moduleName = '${shared_util.contextPath}';
                          openNewWindowNew('cdLinesIntegrationFullURLId', moduleName, 'movRequestWindowTitleId');
                      }

                      function openNewWindowNew(fullURL, moduleName, winNameInput) {
                          var url = document.getElementById(fullURL).value;
                          var newUrl = window.location.protocol + "//" + window.location.host;
                          url = newUrl + moduleName + url;
                          var windowTitle = document.getElementById(winNameInput).innerHTML;
                          var wOpen;
                          var sOptions;
                          var popupWindowWidth = 632;
                          var popupWindowheight = 540;

                          sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no,dialog=yes,minimizable=yes,maximizable=no';
                          sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
                          sOptions = sOptions + ',height=' + (popupWindowheight).toString();
                          sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

                          globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";

                          wOpen = window.open("", '', sOptions);
                          wOpen.document.write(globalHTML);
                          wOpen.focus();
                          wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

                          return wOpen;
                      }
                    </script>

    <!-- start CSC-23106 -->
    <t:inputHidden id="reportUrl" forceId="true" value="#{pageBeanName.reportUrl}"/>
    <!-- end CSC-23106 -->
    <!-- start CSC-23106 -->
    <t:saveState value="#{pageBeanName.reportsTemplate}"/>
    <t:saveState value="#{pageBeanName.reportTemplatesList}"/>
    <t:saveState value="#{pageBeanName.templateName}"/>
    <t:saveState value="#{pageBeanName.reportUrl}"/>
    <!-- end CSC-23106 -->

                    <t:saveState value="#{pageBeanName.myTableData}"/>
                    <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                    <t:saveState value="#{pageBeanName.searchMode}"/>
                    <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                    <t:saveState value="#{pageBeanName.decisionBasicDTO}"/>
                    <t:saveState value="#{pageBeanName.fullColumnName}"/>
                    <t:saveState value="#{pageBeanName.sortAscending}"/>
                    <t:saveState value="#{pageBeanName.validToLink}"/>
                    <t:saveState value="#{pageBeanName.resList}"/>
                      <t:saveState value="#{pageBeanName.empExecuteListBean}"/>
                    
                    <t:saveState value="#{hireDecisionListBean.jdHelper}"/>
                    <%-- start paging--%>
                    <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                    <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                    <t:saveState value="#{pageBeanName.sorting}"/>
                    <t:saveState value="#{pageBeanName.usingPaging}"/>
                    <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                    <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                    <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                    <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                    <t:saveState value="#{pageBeanName.allList}"/>
                    <t:saveState value="#{pageBeanName.firstLevelHireTypesList}"/>
                    <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                    <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                    <t:saveState value="#{pageBeanName.loginedMinistrycode}"/>
                    <t:saveState value="#{pageBeanName.hireType}"/>
                    <t:saveState value="#{pageBeanName.hireTypeName}"/>
                    <t:saveState value="#{pageBeanName.enableViewCondDtls}"/>
                    <t:saveState value="#{hireDecisionListBean.filteredList}"/>
                    <t:saveState value="#{hireDecisionListBean.pageDTO}"/>
                    <t:saveState value="#{hireDecisionListBean.saveSortingState}"/>
                    <t:saveState value="#{hireDecisionListBean.sortColumn}"/>
                    
                    <%-- end paging--%>
                    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
                    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
                    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions"
                                  var="globalexceptions"/>
                    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration"
                                  var="grsIntgResources"/>
                    <tiles:insert definition="hiredecision.page" flush="false"></tiles:insert>
                    <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
                                   value="hireDateFrom_Date,200,150:hireDateTo_Date,200,150"/>
                    <t:panelGroup forceId="true" id="movReuestValues">
                        <t:inputHidden forceId="true" id="cdLinesIntegrationFullURLId"
                                       value="#{conditionLinesdetailsHelperBean.fullURL}"/>
                        <t:outputText forceId="true" id="movRequestWindowTitleId"
                                      value="#{grsIntgResources.conditionDetails}" style="display:none;"/>
                    </t:panelGroup>
                    <t:panelGroup forceId="true" id="scriptPanel">
                        <c2:scriptGenerator form="myForm"/>
                    </t:panelGroup>
                </h:form>
            </t:aliasBean>
        </t:aliasBean>
    </t:aliasBean>
</f:view>
