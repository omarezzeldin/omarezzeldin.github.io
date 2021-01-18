<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:outputText value="#{resourcesBundle.add_ministry_div_title}" styleClass="TitleMinistryAddDiv"/>

<t:inputText value="" style="width:0px; height:0px;font-size:0px;" forceId="true" id="tempComponent" onblur="setFocusOnlyOnElement('firstElementAddDiv');"/>

<t:panelGrid  forceId="true" id="ministryDiv"  width="100%" columns="1" border="0" cellpadding="0" cellspacing="0">
    
    <t:panelGrid  width="100%" align="center"  columns="2">
     <%--   <t:selectOneRadio value="#{pageBeanName.selectedGovFlag}" styleClass="radio1" onblur="settingFoucsAtDivSearch();" forceId="true" id="firstElementAddDiv">
            <f:selectItem itemLabel="#{resourcesBundle.Gov_Flag}" itemValue="#{pageBeanName.gov_Flag}" />
            <f:selectItem itemLabel="#{resourcesBundle.NonGov_Flag}" itemValue="#{pageBeanName.nonGov_Flag}" />
            <a4j:support event="onclick" actionListener="#{pageBeanName.chooseCategoryByGovFlag}" reRender="dataT_dataPanelGroup,scrollerPanel,buttonPanelId,ministryDiv,ministryNameadd" />
        </t:selectOneRadio> --%>
        
        <t:panelGrid  columns="2" align="center" border="0"> 
            
            <t:outputText value="#{resourcesBundle.categoryType}" styleClass="lable01"/>
            
            <t:selectOneMenu id="search_first_inputTxt" valueChangeListener="#{pageBeanName.filterDataTableByCategory}"   forceId="true"  value="#{pageBeanName.selectedCategoryType}"  styleClass="DropdownboxMedium2" style="width:210px;"  >
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="" />
                <t:selectItems var="categoryList" itemLabel="#{categoryList.name}" itemValue="#{categoryList.code.key}"  value="#{pageBeanName.categoryList}" />
                <a4j:support event="onchange"  actionListener="#{pageBeanName.filterDataByCategory}"  reRender="dataT_dataPanelGroup,scrollerPanel,buttonPanelId,ministryDiv,ministryNameadd"/>
             </t:selectOneMenu>
            
        </t:panelGrid>
    </t:panelGrid>
    
    <t:panelGrid align="center"  columns="2" rendered="#{pageBeanName.selectedCategoryType!='' && pageBeanName.selectedCategoryType!=null && pageBeanName.selectedCategoryType!=pageBeanName.virtualConstValue }">                                
        <t:selectOneRadio forceId="true" id="searchRadioBtn" value="#{pageBeanName.searchTypeStr}" styleClass="divtext">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{pageBeanName.searchTypeByCode}"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{pageBeanName.searchTypeByName}"/>
        </t:selectOneRadio>
        
        <t:panelGroup>
            <t:inputText forceId="true"  value="#{pageBeanName.ministrySearchStr}" styleClass="textbox" id="ministryNameadd" rendered="#{pageBeanName.selectedCategoryType!='' && pageBeanName.selectedCategoryType!=null && pageBeanName.selectedCategoryType!=pageBeanName.virtualConstValue }"
                onkeypress="FireButtonClick('myForm:search_ministry_btn')"/>
            
            <a4j:commandButton type="button" id="search_ministry_btn" 
                onclick="if(validateCodeNameSrchParamter('searchRadioBtn','ministryNameadd','','errorMessag_lov')){ lov_search();}"
                styleClass="ManyToManySearchButton" 
                rendered="#{pageBeanName.selectedCategoryType!='' && pageBeanName.selectedCategoryType!=null && pageBeanName.selectedCategoryType!=pageBeanName.virtualConstValue }" />
                
            <a4j:commandButton rendered="#{pageBeanName.searchMode}" oncomplete="settingFoucsAtDivSearch();" action="#{pageBeanName.cancelSearch}" reRender="ministryDiv"   styleClass="ManyToManyCancelSearchButton"/>                             
            
            <a4j:jsFunction name="lov_search" id="lov_search" 
                oncomplete="settingFoucsAtDivSearch();" 
                action="#{pageBeanName.searchForMinistry}"
                reRender="ministryDiv"/>
        </t:panelGroup>
        <t:panelGroup colspan="2">
            <t:panelGrid width="100%" style="text-align:center">
                <t:outputText value="#{pageBeanName.errorMsgValue}" forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    
    <t:panelGrid align="center" rendered="#{pageBeanName.listSize == 0}">          
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>      
    </t:panelGrid>
    
    <t:panelGrid width="100%" forceId="true" id="dataT_dataPanelGroup">
        <t:panelGroup style="display:block;height:105px;overflow-y:auto;"> 
            <t:dataTable id="dataT_data" forceId="true" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
               rows="#{shared_util.noOfTableRows}"  rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                    <f:facet name="header">
                    </f:facet>
                    <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}"  valueChangeListener="#{pageBeanName.selectedRadioButton}"
                            onkeyup="toggleRadioKeyUpEnableBtn(this,'searchButton');" onmousedown="toggleRadio(this);document.getElementById('searchButton').disabled=false;">
                     <f:selectItem itemLabel="" itemValue="#{list.code.key}" />         
                    </t:selectOneRadio>
                </t:column>
                   
                <t:column id="code_column" width="10%">
                    <f:facet name="header">
                      <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.key}" converter="javax.faces.Long"/>
                </t:column>
                
                <t:column id="name_column" width="85%">
                    <f:facet name="header">
                      <h:outputText id="header_name" value="#{resourcesBundle.minisName}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
                
            </t:dataTable>
        </t:panelGroup>
    </t:panelGrid>

    <t:panelGrid align="center"  columns="1" forceId="true" id="scrollerPanel">
  
        <t:dataScroller id="scroll_paging"
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
                    for="dataT_data"
                    renderFacetsIfSinglePage="false"
                    actionListener="#{pageBeanName.scrollerAction}" rendered="#{pageBeanName.usingPaging}">
                    
                <f:facet name="previous">                            
                    <h:panelGroup id="jobs_list_panelGrp_prv_paging">
                            <t:graphicImage id="jobs_list_img_prvOn_paging"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_prvOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="jobs_list_panelGrp_nxt_paging">
                            <t:graphicImage id="jobs_list_img_nxtOn_paging"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_nxtOff_paging"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd_paging">
                            <t:graphicImage id="jobs_list_img_ffrwrdOn_paging"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd_paging">
                            <t:graphicImage id="jobs_list_img_frwndOn_paging"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_frwndOff_paging"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                        <t:inputHidden forceId="true" id="pageIndexWithPaging" value="#{pageIndex}"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        
    </t:panelGrid>
 
    <t:panelGrid columns="3" border="0" align="center" id="buttonPanelId">
        <t:commandButton forceId="true" styleClass="cssButtonSmall" id="searchButton" disabled="true" value="#{globalResources.ok}"  action="#{pageBeanName.addMinistry}" onclick="setFocusAtMyFirstElement();"/>
        <h:panelGroup>
            <t:commandButton forceId="true" id="customSearchBackBtn" onblur="setFocusOnlyOnElement('tempComponent');" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.divSearch,null,null);setFocusAtMyFirstElement(); " reRender="searchArea,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>                                        
        </h:panelGroup>
    </t:panelGrid>

</t:panelGrid>

<!-- Start Paging -->
<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.selectedCategoryType}"/>
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>

<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->

<t:saveState value="#{pageBeanName.indexArray}"/>
<t:saveState value="#{pageBeanName.searchTypeStr}"/>
