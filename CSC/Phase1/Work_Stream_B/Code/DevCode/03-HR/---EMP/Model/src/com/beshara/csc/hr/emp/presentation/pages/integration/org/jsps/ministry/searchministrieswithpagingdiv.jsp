<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<t:div forceId="true" styleClass="#{searchMinistryHelper.divStyleClass}" id="searchMinistryDiv">
    <t:div styleClass="popup_header">
        <t:commandButton id="searchMinistryDivclose" forceId="true" type="button"
                         onclick="document.getElementById('searchministry_cancel').click();" styleClass="close"/>
         
        <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                      rendered="#{searchMinistryHelper.divTitleKey==null}"/>
         
        <t:outputText styleClass="popup_title" value="#{resourcesBundle[searchMinistryHelper.divTitleKey]}"
                      rendered="#{searchMinistryHelper.divTitleKey!=null}"/>
    </t:div>
    <t:div styleClass="popup_body">
        <t:div styleClass="popup_body_contents">
            <t:panelGrid align="center">
                <t:panelGroup>
                    <%-- t:outputText value="#{searchMinistryHelper.errorMsgValue}" forceId="true"
                         id="searchministry_errorConsole" styleClass="errMsgNoHeight" /--%>
                    <t:outputText value="#{searchMinistryHelper.errorMsgValue}" forceId="true"
                                  id="errorMessag_searchMinistry" styleClass="errMsgNoHeight"/>
                </t:panelGroup>
                <t:selectOneRadio forceId="true" id="searchministry_searchRadioBtn"
                                  value="#{searchMinistryHelper.searchTyp}" styleClass="divtext"
                                  onblur="settingFoucsAtSearchMinistryDiv();">
                    <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                    <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
                </t:selectOneRadio>
            </t:panelGrid>
            
            <t:panelGrid align="center" style="text-align:center;" columns="2" width="100%" forceId="true"
                         id="searchministry_searchPanel">
                <t:outputText styleClass="lable01" value="#{searchMinistryHelper.searchMinistryDivLabelSearch}"/>
                <t:panelGroup>
                    <t:inputText  forceId="true"
                                 id="searchministry_searchText" value="#{searchMinistryHelper.searchQuery}"
                                 styleClass="textboxmedium"
                                 onkeypress="if(document.getElementById('searchministry_search_btn') != null){ FireButtonClickOldStyle(event,'searchministry_search_btn');}else{FireButtonClickOldStyle(event,'searchministry_search_btn_code_string');}"/>
                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <t:commandButton rendered="#{!searchMinistryHelper.codeTypeString}" type="button"
                                     id="searchministry_search_btn" forceId="true"
                                     onclick="if(validateCodeNameSrchParamter('searchministry_searchRadioBtn','searchministry_searchText','','errorMessag_searchMinistry')){ searchministry_search();}"
                                     styleClass="ManyToManySearchButton"/>
                    <t:commandButton rendered="#{searchMinistryHelper.codeTypeString}" type="button"
                                     id="searchministry_search_btn_code_string" forceId="true"
                                     onclick="if(validateCodeNameSrchParamter('searchministry_searchRadioBtn','searchministry_searchText','true','errorMessag_searchMinistry')){ searchministry_search();}"
                                     styleClass="ManyToManySearchButton"/>
                    <a4j:jsFunction name="searchministry_search" id="searchministry_search"
                                    oncomplete="settingFoucsAtSearchMinistryDiv();"
                                    action="#{searchMinistryHelper.searchDiv}"
                                    reRender="searchministry_dataT_data_panel,searchministry_searchPanel,errorMessag_searchMinistry,searchministry_paging_panel,searchMinistry_btnsPnlGrd"/>
                    <f:verbatim>&nbsp;</f:verbatim>
                    <a4j:commandButton id="searchministry_cancelSearch" oncomplete="settingFoucsAtSearchMinistryDiv();"
                                       action="#{searchMinistryHelper.cancelSearchDiv}"
                                       reRender="searchministry_searchText,searchministry_dataT_data_panel,searchministry_searchPanel,errorMessag_searchMinistry,searchministry_paging_panel,searchministry_searchRadioBtn,searchMinistry_btnsPnlGrd"
                                       styleClass="ManyToManyCancelSearchButton"
                                       rendered="#{searchMinistryHelper.searchMode}"/>
                </t:panelGroup>
            </t:panelGrid>
             
             <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                <t:panelGroup forceId="true" id="searchministry_dataT_data_panel" styleClass="lovDivScroll5">
                    <t:dataTable id="searchministry_dataT_data" var="list"
                                 value="#{searchMinistryHelper.usingPaging ? searchMinistryHelper.pagingBean.myPagedDataModel : searchMinistryHelper.myTableData}"
                                 rowStyleClass="#{ searchMinistryHelper.selected ? 'standardTable_RowHighlighted' : null}"
                                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}"
                                 rowIndexVar="index" renderedIfEmpty="false"
                                 binding="#{searchMinistryHelper.myDataTable}"
                                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                 footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                 width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                                 sortColumn="#{searchMinistryHelper.sortColumn}"
                                 sortAscending="#{searchMinistryHelper.ascending}">
                        <t:column id="searchMinistry_check_column" styleClass="standardTable_Header3" width="5%">
                            <f:facet name="header"></f:facet>
                            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk_searchMinistry"
                                              value="#{searchMinistryHelper.selectedRadio}"
                                              onkeyup="toggleRadioKeyUp(this);" onmousedown="toggleRadio(this);">
                                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                                <a4j:support event="onclick"
                                             actionListener="#{searchMinistryHelper.selectedRadioButton}"
                                             oncomplete="document.getElementById('searchMinistry_ok').disabled=false;document.getElementById('searchMinistry_ok').focus();document.getElementById('searchMinistry_ok').focus();"
                                             reRender="searchMinistry_btnsPnlGrd"/>
                            </t:selectOneRadio>
                        </t:column>
                        <t:column sortable="false" width="20%">
                            <f:facet name="header">
                                <t:commandLink id="sort_code" forceId="true" styleClass="headerLink"
                                               value="#{globalResources.Code}" onclick="return false;"></t:commandLink>
                            </f:facet>
                            <t:outputText id="content_code" forceId="true" value="#{list.code.key}"/>
                        </t:column>
                        <t:column sortable="false" width="75%">
                            <f:facet name="header">
                                <t:commandLink id="sort_name" forceId="true" styleClass="headerLink"
                                               value="#{globalResources.name}" onclick="return false;"></t:commandLink>
                            </f:facet>
                            <h:outputText id="content_name" value="#{list.name}"></h:outputText>
                        </t:column>
                    </t:dataTable>
                    <t:panelGrid columns="1"
                                 rendered="#{ searchMinistryHelper.searchMode && searchMinistryHelper.listSize == 0 }"
                                 align="center">
                        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
                    </t:panelGrid>
                </t:panelGroup>
            </t:panelGrid>
            
            <t:panelGroup forceId="true" id="searchministry_paging_panel" style="height: 31px;">
                <h:panelGrid id="searchministry_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}"
                             styleClass="scroller" width="300"
                             rendered="#{searchMinistryHelper.listSize > shared_util.noOfTableRows}">
                    <t:dataScroller id="searchministry_scroll_1" fastStep="5" pageCountVar="pageCount"
                                    pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                                    paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                                    firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                                    previousStyleClass="textpage" paginatorColumnClass="textpage"
                                    paginatorActiveColumnClass="paging"
                                    paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                                    styleClass="textpage" immediate="false" for="searchministry_dataT_data"
                                    renderFacetsIfSinglePage="false"
                                    actionListener="#{searchMinistryHelper.openSearchMinistryDiv}">
                        <f:facet name="first">
                            <h:panelGroup id="searchministry_jobs_list_panelGrp_first">
                                <t:graphicImage id="searchministry_jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchministry_jobs_list_img_firstOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="last">
                            <h:panelGroup id="searchministry_jobs_list_panelGrp_last">
                                <t:graphicImage id="searchministry_jobs_list_img_lastOn"
                                                rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_last}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchministry_jobs_list_img_lastOff" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="previous">
                            <h:panelGroup id="searchministry_jobs_list_panelGrp_prv">
                                <t:graphicImage id="searchministry_jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_previous}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchministry_jobs_list_img_prvOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="next">
                            <h:panelGroup id="searchministry_jobs_list_panelGrp_nxt">
                                <t:graphicImage id="searchministry_jobs_list_img_nxtOn"
                                                rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_next}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchministry_jobs_list_img_nxtOff"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastforward">
                            <h:panelGroup id="searchministry_jobs_list_panelGrp_ffrwrd">
                                <t:graphicImage id="jsearchministry_obs_list_img_ffrwrdOn"
                                                rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_fastforward}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchministry_jobs_list_img_ffrwrdOff" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastrewind">
                            <h:panelGroup id="searchministry_jobs_list_panelGrp_frwnd">
                                <t:graphicImage id="searchministry_jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_fastrewind}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchministry_searchministry_jobs_list_img_frwndOff"
                                                onclick="return false;" rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                    </t:dataScroller>
                </h:panelGrid>
            </t:panelGroup>
            
            <t:panelGrid columns="3" align="center" forceId="true" id="searchMinistry_btnsPnlGrd">
                <t:panelGroup>
                    <t:commandButton forceId="true" id="searchMinistry_ok" binding="#{searchMinistryHelper.okBtnDiv}"
                                     disabled="true" value="#{globalResources.ok}" type="button"
                                     styleClass="cssButtonSmall"
                                     onclick="clearOutputText('errorMessag_searchMinistry'); return searchMinistries();">
                        <a4j:jsFunction id="searchMinistries" name="searchMinistries"
                                        action="#{searchMinistryHelper.closeDiv}"
                                        oncomplete="hideLookUpDiv(window.blocker,window.searchMinistryDiv);"
                                        reRender="#{searchMinistryHelper.renderedCmpIds}"/>
                    </t:commandButton>
                </t:panelGroup>
                <t:panelGroup>
                    <t:commandButton forceId="true" id="searchministry_cancel"
                                     onclick="clearOutputText('errorMessag_searchMinistry');" type="button"
                                     styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                    <a4j:support action="#{searchMinistryHelper.hideDiv}" event="onclick"
                                 oncomplete="hideLookUpDiv(window.blocker,window.searchMinistryDiv);"
                                 reRender="divSearch"/>
                </t:panelGroup>
                <%--<t:inputHidden id="indexOfSelectedDataInDataTableId" forceId="true"
                               value="#{searchMinistryHelper.indexOfSelectedDataInDataTable}"/>--%>
            </t:panelGrid>
            
            <t:saveState value="#{searchMinistryHelper.selectedDTOS}"/>
             
            <t:saveState value="#{searchMinistryHelper.fullColumnName}"/>
             
            <t:saveState value="#{searchMinistryHelper.sortAscending}"/>
             
            <t:saveState value="#{searchMinistryHelper.myTableData}"/>
             
            <t:saveState value="#{searchMinistryHelper.searchTyp}"/>
             
            <t:saveState value="#{searchMinistryHelper.searchQuery}"/>
             
            <t:saveState value="#{searchMinistryHelper.renderedCmpIds}"/>
             
            <t:saveState value="#{searchMinistryHelper.searchMode}"/>
             
            <t:saveState value="#{searchMinistryHelper.returnMethodName}"/>
             
            <t:saveState value="#{searchMinistryHelper.catCode}"/>
            <t:saveState value="#{searchMinistryHelper.excludeLoginMinstry}"/>
             
            <!-- Start Paging -->
             
            <t:saveState value="#{searchMinistryHelper.currentPageIndex}"/>
             
            <t:saveState value="#{searchMinistryHelper.oldPageIndex}"/>
             
            <t:saveState value="#{searchMinistryHelper.sorting}"/>
             
            <t:saveState value="#{searchMinistryHelper.usingPaging}"/>
             
            <t:saveState value="#{searchMinistryHelper.usingBsnPaging}"/>
             
            <t:saveState value="#{searchMinistryHelper.updateMyPagedDataModel}"/>
             
            <t:saveState value="#{searchMinistryHelper.resettedPageIndex}"/>
             
            <t:saveState value="#{searchMinistryHelper.bsnSortingColumnName}"/>
             
            <t:saveState value="#{searchMinistryHelper.minSearchDTO}"/>
             
            <t:saveState value="#{searchMinistryHelper.pagingRequestDTO}"/>
             
            <t:saveState value="#{searchMinistryHelper.pagingBean.totalListSize}"/>
             
            <t:saveState value="#{searchMinistryHelper.pagingBean.myPagedDataModel}"/>
             
            <t:saveState value="#{searchMinistryHelper.pagingBean.preUpdatedDataModel}"/>
            <script>
              function settingFoucsAtSearchMinistryDiv() {
                  if (document.getElementById('searchministry_searchText') != null) {
                      document.getElementById('searchministry_searchText').focus();
                      document.getElementById('searchministry_searchText').focus();
                  }
              }
            </script>
        </t:div>
    </t:div>
</t:div>