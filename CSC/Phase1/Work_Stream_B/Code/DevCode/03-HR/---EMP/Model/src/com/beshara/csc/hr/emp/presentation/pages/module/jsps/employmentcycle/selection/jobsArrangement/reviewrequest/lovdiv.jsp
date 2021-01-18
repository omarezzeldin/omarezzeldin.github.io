<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="lovDivPanelGroup" >
<t:panelGroup rendered="#{pageBeanName.renderLovDiv}" >
<t:panelGrid  align="center">
    <t:panelGroup>
        <t:outputText value="#{pageBeanName.lovBaseBean.errorMsgValue}" forceId="true" id="errorMessag_lov12" styleClass="errMsgNoHeight" />
    </t:panelGroup>
    <t:selectOneRadio forceId="true" id="lov_searchRadioBtn" value="#{pageBeanName.lovBaseBean.searchTyp}" styleClass="divtext" onblur="settingFoucsAtLovDiv();">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
    </t:selectOneRadio>
  </t:panelGrid>
  
<t:panelGrid columns="4" id="lov_searchPanel" align="center" dir="#{shared_util.pageDirection}" forceId="true" width="60%" >
       <%--<h:outputText value="#{resourcesBundle[pageBeanName.titleFlageOfDiv]}"  styleClass="lable01" id="title_Id"/>--%>
     <t:inputText forceId="true" id="lov_searchText"  value="#{pageBeanName.lovBaseBean.searchQuery}" styleClass="textboxmedium"  onkeypress="FireButtonClick('myForm:lov_search');"/>
    <t:panelGroup>
        <t:commandButton type="button" id="lov_search_btn_code_string" forceId="true" onclick="if(validateCodeNameSrchParamter('lov_searchRadioBtn','lov_searchText','true','errorMessag_lov12')){ lov_search();}" styleClass="ManyToManySearchButton" />
        <a4j:jsFunction name="lov_search" id="lov_search" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.lovBaseBean.searchLovDiv}" reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole"  />
    </t:panelGroup>  
     <a4j:commandButton id="lov_cancelSearch" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.lovBaseBean.cancelSearchLovDiv}" reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lov_searchRadioBtn"  styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.lovBaseBean.searchMode}" />
     <t:outputText value="" rendered="#{!pageBeanName.lovBaseBean.searchMode}" style="WIDTH: 85px;"/>
 </t:panelGrid>
                         
                     
<t:outputText value="" forceId="true" id="lov_errorConsole" styleClass="errMsgNoHeight" />              
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center" >
    <t:panelGroup forceId="true" id="lov_dataT_data_panel" styleClass="lovDivScroll5" >
        <t:dataTable id="lov_dataT_data" var="list" 
                 value="#{pageBeanName.lovBaseBean.usingPaging ? pageBeanName.lovBaseBean.pagingBean.myPagedDataModel : pageBeanName.lovBaseBean.myTableData}"
                 rowStyleClass="#{ pageBeanName.lovBaseBean.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.lovBaseBean.myDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                 dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.lovBaseBean.sortColumn}" sortAscending="#{pageBeanName.lovBaseBean.ascending}">
       <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
        <f:facet name="header">
        </f:facet>
        <t:selectOneRadio onkeypress="FireButtonClick('myForm:okLoveButtondiv');" styleClass="radioButton_DataTable"  id="chk" valueChangeListener="#{pageBeanName.lovBaseBean.selectedRadioButton}" value="#{pageBeanName.lovBaseBean.selectedRadio}"  onmousedown="toggleRadio(this);document.getElementById('myForm:okLoveButtondiv').disabled=false;" onkeyup="toggleRadioKeyUpEnableBtn(this,'myForm:okLoveButtondiv');" >
         <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
         <%--a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedRadioButton}"/--%>
        </t:selectOneRadio>
       </t:column>
       
            <t:column id="code_column" sortable="false"  width="#{pageBeanName.jobMode? '45%':'20%'}">
                <f:facet name="header">
                 <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.lovBaseBean.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.lovBaseBean.sortAscending&&pageBeanName.lovBaseBean.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.lovBaseBean.sortAscending&&pageBeanName.lovBaseBean.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                
                <h:outputText id="content_code" value="#{pageBeanName.workCenterMode ? list.code.wrkCode : pageBeanName.jobMode ?list.jobKey:list.code.key}"/>
            </t:column>
            
            <t:column id="name_column" sortable="false" width="#{pageBeanName.jobMode? '50%':'75%'}">
                <f:facet name="header">
                   <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" actionListener="#{pageBeanName.lovBaseBean.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.lovBaseBean.sortAscending&&pageBeanName.lovBaseBean.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.lovBaseBean.sortAscending&&pageBeanName.lovBaseBean.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.lovBaseBean.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.lovBaseBean.reloadList}" binding="#{pageBeanName.lovBaseBean.reloadField}"/>
    </t:panelGroup>    
</t:panelGrid>
<t:panelGroup forceId="true" id="lov_paging_panel">
        <h:panelGrid id="panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.lovBaseBean.listSize > shared_util.noOfTableRows}" >
            
          <t:dataScroller id="scroll_1" 
                    fastStep="5" pageCountVar="pageCount" 
                    pageIndexVar="pageIndex"
                    paginator="true"
                    paginatorMaxPages="5"
                    paginatorTableClass="scroller"
                    fastfStyleClass="textpage"
                    fastrStyleClass="textpage"
                    firstStyleClass="textpage"
                    lastStyleClass="textpage"
                    nextStyleClass="textpage"
                    previousStyleClass="textpage"
                    paginatorColumnClass="textpage"
                    paginatorActiveColumnClass="paging"
                    paginatorActiveColumnStyle="font-weight:bold"
                    styleClass="textpage"
                    immediate="false"
                    for="lov_dataT_data"
                    renderFacetsIfSinglePage="false"
                    actionListener="#{pageBeanName.lovBaseBean.openLovDiv}">
                <f:facet name="first" >                            
                    <h:panelGroup id="jobs_list_panelGrp_first">
                        <t:graphicImage id="jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="jobs_list_panelGrp_last">
                            <t:graphicImage id="jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="jobs_list_panelGrp_prv">
                            <t:graphicImage id="jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="jobs_list_panelGrp_nxt">
                            <t:graphicImage id="jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jobs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
</t:panelGroup>
<t:panelGrid columns="2" align="center">
 <h:panelGroup>
 <t:commandButton onclick="OkLovJsFunction();return false;" id="okLoveButtondiv" disabled="true"   value="#{globalResources.ok}" binding="#{pageBeanName.lovBaseBean.okCommandButton}"  styleClass="cssButtonSmall"  onblur="settingFoucsAtLovDiv();" /> 
 <a4j:jsFunction name="OkLovJsFunction" action="#{pageBeanName.fillJobKey}" oncomplete="hideLookUpDiv(window.blocker,window.divLov);" reRender="lovDivPanelGroup,cnt1Panel"/>    
 </h:panelGroup>
 <h:panelGroup>
    <t:commandButton forceId="true" id="lov_cancel" onblur="settingFoucsAtLovDiv();" onclick="backLovJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:jsFunction name="backLovJsFunction" action="#{pageBeanName.lovBaseBean.hideLovDiv}" oncomplete="hideLookUpDiv(window.blocker,window.divLov);foucsFirstElemenyOnPage();" reRender="lovDivPanelGroup"/>    
</h:panelGroup>
<%--<t:inputText value="" style="width=0px; height:0px; font-size=0;"/>--%>
</t:panelGrid>


<t:saveState value="#{pageBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.fullColumnName}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.sortAscending}"/>

<t:saveState value="#{pageBeanName.lovBaseBean.myTableData}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.returnMethodName}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.searchMode}" /> 
<t:saveState value="#{pageBeanName.lovBaseBean.searchQuery}" /> 
<t:saveState value="#{pageBeanName.lovBaseBean.searchMethod}" />
<t:saveState value="#{pageBeanName.lovBaseBean.cancelSearchMethod}" />
<!-- Start Paging -->
<t:saveState value="#{pageBeanName.lovBaseBean.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.sorting}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.usingPaging}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.resettedPageIndex}"/>

<t:saveState value="#{pageBeanName.lovBaseBean.pagingRequestDTO}"/>

<t:saveState value="#{pageBeanName.lovBaseBean.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
</t:panelGroup>
</t:panelGroup>
