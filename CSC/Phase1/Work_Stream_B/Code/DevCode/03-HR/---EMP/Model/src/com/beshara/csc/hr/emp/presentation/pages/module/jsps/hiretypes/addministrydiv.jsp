<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:outputText value="#{resourcesBundle.add_ministry_div_title}" styleClass="TitleMinistryAddDiv"/>

<t:inputText value="" style="width:0px; height:0px;font-size:0px;" forceId="true" id="tempComponent" onblur="setFocusOnlyOnElement('firstElementAddDiv');"/>

<t:panelGrid  forceId="true" id="ministryDiv"  width="100%" columns="1" border="0" cellpadding="0" cellspacing="0">
    
    <t:panelGrid  width="100%" align="center"  columns="2">
         
        <t:panelGrid  columns="2" align="center" border="0"> 
            
            <t:outputText value="#{resourcesBundle.categoryType}" styleClass="lable01"/>
            
            <t:selectOneMenu id="search_first_inputTxt" valueChangeListener="#{detailBeanName.filterDataTableByCategory}"   forceId="true"  value="#{detailBeanName.selectedCategoryType}"  styleClass="DropdownboxMedium2"  >
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="" />
                <t:selectItems var="categoryList" itemLabel="#{categoryList.name}" itemValue="#{categoryList.code.key}"  value="#{detailBeanName.categoryList}" />
                <a4j:support event="onchange"  actionListener="#{detailBeanName.filterDataByCategory}"  reRender="dataT_dataPanelGroup2,scrollerPanel2,buttonPanelId2,ministryDiv,ministryNameadd"/>
             </t:selectOneMenu>
            
        </t:panelGrid>
    </t:panelGrid>
    
    <t:panelGrid align="center"  columns="2" rendered="#{detailBeanName.selectedCategoryType!='' && detailBeanName.selectedCategoryType!=null && detailBeanName.selectedCategoryType!=detailBeanName.virtualConstValue }">                                
       <t:selectOneRadio forceId="true" id="searchRadioBtn2" value="#{detailBeanName.searchTypeStr}" styleClass="divtext">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{detailBeanName.searchTypeByCode}"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{detailBeanName.searchTypeByName}"/>
        </t:selectOneRadio>
        
        <t:panelGroup>
            <t:inputText forceId="true"  value="#{detailBeanName.ministrySearchStr}" styleClass="textbox" id="ministryNameadd" rendered="#{detailBeanName.selectedCategoryType!='' && detailBeanName.selectedCategoryType!=null && detailBeanName.selectedCategoryType!=detailBeanName.virtualConstValue }"
                onkeypress="FireButtonClick('myForm:search_ministry_btn')"/>
            
            
      <a4j:commandButton type="button" id="search_ministry_btn" 
            onclick="if(validateCodeNameSrchParamter('searchRadioBtn2','ministryNameadd','','errorMessag_lov2')){ lov_search();}"
            styleClass="ManyToManySearchButton"/>
        
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton action="#{detailBeanName.cancelSearchAvailable}" oncomplete="settingFoucsAtDivAdd();" reRender="ministryDiv" styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchMode}" />
        
        
              <a4j:jsFunction name="lov_search" id="lov_search" 
                oncomplete="settingFoucsAtDivSearch();" 
                action="#{detailBeanName.searchForMinistry}"
                reRender="ministryDiv"/>
            <%--
            <a4j:commandButton type="button" id="search_ministry_btn" 
                onclick="lov_search2()"
                styleClass="ManyToManySearchButton" 
                rendered="#{detailBeanName.selectedCategoryType!='' && detailBeanName.selectedCategoryType!=null && detailBeanName.selectedCategoryType!=detailBeanName.virtualConstValue }" />
                
            <a4j:commandButton rendered="#{detailBeanName.searchMode}" oncomplete="settingFoucsAtDivSearch();" action="#{detailBeanName.cancelSearch}" reRender="ministryDiv"   styleClass="ManyToManyCancelSearchButton"/>                             
            
            <a4j:jsFunction name="lov_search2" id="lov_search2" 
                oncomplete="settingFoucsAtDivSearch();" 
                action="#{detailBeanName.searchForMinistry}"
                reRender="ministryDiv"/>
                
                --%>
        </t:panelGroup>
        <t:panelGroup colspan="2">
            <t:panelGrid width="100%" style="text-align:center;margin-top:5px;">
                <t:outputText value="#{detailBeanName.errorMsgValue}" forceId="true" id="errorMessag_lov2" styleClass="errMsgNoHeight"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    
    <t:panelGrid align="center" rendered="#{detailBeanName.listSize == 0}">          
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>      
    </t:panelGrid>
    
    <t:panelGrid width="100%" forceId="true" id="dataT_dataPanelGroup2">
        <t:panelGroup style="display:block;height:95px;overflow-y:auto;"> 
            <t:dataTable id="dataT_data" forceId="true" var="list" value="#{detailBeanName.usingPaging ? detailBeanName.pagingBean.myPagedDataModel : detailBeanName.myTableData}"
                rowStyleClass="#{ detailBeanName.selected ? 'standardTable_RowHighlighted' : null}"
               rows="#{shared_util.noOfTableRows}"  rowIndexVar="index" renderedIfEmpty="false" binding="#{detailBeanName.myDataTable}"
                rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}">
            
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                    <f:facet name="header">
                    </f:facet>
                    <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{detailBeanName.selectedRadio}"  valueChangeListener="#{detailBeanName.selectedRadioButtonBase}"
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

    <t:panelGrid align="center"  columns="1" forceId="true" id="scrollerPanel2">
  
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
                    actionListener="#{detailBeanName.scrollerMinAction}" rendered="#{detailBeanName.usingPaging}">
                    
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
 
    <t:panelGrid columns="3" border="0" align="center" id="buttonPanelId2">
        <t:commandButton forceId="true" styleClass="cssButtonSmall" id="searchButton" disabled="true" value="#{globalResources.ok}"  action="#{detailBeanName.addMinistry}" onclick="setFocusAtMyFirstElement();"/>
        <h:panelGroup>
            <t:commandButton forceId="true" id="customSearchBackBtn" onblur="setFocusOnlyOnElement('tempComponent');" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{detailBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.divSearch,null,null);setFocusAtMyFirstElement(); " reRender="searchArea,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>                                        
        </h:panelGroup>
    </t:panelGrid>

</t:panelGrid>

<!-- Start Paging -->
<t:saveState value="#{detailBeanName.currentPageIndex}"/>
<t:saveState value="#{detailBeanName.oldPageIndex}"/>
<t:saveState value="#{detailBeanName.sorting}"/>
<t:saveState value="#{detailBeanName.usingPaging}"/>
<t:saveState value="#{detailBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{detailBeanName.resettedPageIndex}"/>
<t:saveState value="#{detailBeanName.selectedCategoryType}"/>
<t:saveState value="#{detailBeanName.pagingRequestDTO}"/>

<t:saveState value="#{detailBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{detailBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{detailBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->

<t:saveState value="#{detailBeanName.indexArray}"/>
<t:saveState value="#{detailBeanName.searchTypeStr}"/>
