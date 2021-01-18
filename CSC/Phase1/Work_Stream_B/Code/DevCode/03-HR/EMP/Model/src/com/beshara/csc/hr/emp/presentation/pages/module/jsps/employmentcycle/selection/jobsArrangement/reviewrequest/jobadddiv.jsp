<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup forceId="true" id="jobDivPanelGroup" >
<t:messages />
<t:saveState value="#{pageBeanName.selectedRadio}"/>

<t:panelGroup rendered="#{pageBeanName.renderJobDiv}" >
     
        <t:panelGrid align="center">  
            <t:selectOneRadio  onblur="settingFoucsAtDivAdd();" forceId="true" id="job_searchRadioBtn" value="#{pageBeanName.jobSearchType}" styleClass="divtext">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
            </t:selectOneRadio>
        </t:panelGrid> 
    <t:panelGrid  align="center" dir="#{shared_util.pageDirection}" forceId="true" width="80%" columns="5" id="searchPanel2" >             
             <h:outputText value="#{resourcesBundle.job_name}"  styleClass="lable01" id="searchNameID"/>
             <t:inputText onkeypress="FireButtonClick('myForm:job_lov_search_btn');" forceId="true" id="add_first_inputTxt"  value="#{pageBeanName.searchQuery}" styleClass="textboxmedium" />
             
              <%-- a4j:commandButton oncomplete="settingFoucsAtDivAdd();" id="job_lov_search_btn" action="#{pageBeanName.searchJob}" reRender="dataT_popup,jobDivPanelGroup,divAddPopup,searchPanel2,scrollerPanel_popup"  styleClass="ManyToManySearchButton" /--%>
              <t:commandButton styleClass="ManyToManySearchButton" value="" action="#{pageBeanName.searchJob}" />
                 <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <a4j:commandButton oncomplete="settingFoucsAtDivAdd();toggleRadio(null);" action="#{pageBeanName.cancelSearchJob}" reRender="dataT_popup,jobDivPanelGroup,divAddPopup,searchPanel2,scrollerPanel_popup,errorConsole"  styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.searchJobMode}"/>
              <t:outputText value="" rendered="#{!pageBeanName.searchJobMode}" style="WIDTH: 85px;"/>
        </t:panelGrid>
      
            <t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsgNoHeight" />
            <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
            <t:panelGroup forceId="true" id="divAddPopup" styleClass="delDivScroll">
                    
                    <t:dataTable  id="dataT_popup" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel:pageBeanName.jobList}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                                     renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_job');" styleClass="grid-footer"
                                    footerClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                    columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                                    dir="#{shared_util.pageDirection}" preserveSort="true"  binding="#{pageBeanName.myDataTable}" >
                            <t:column sortable="false" width="5%">
                           <f:facet name="header">
                            <h:outputText value=""/>
                           </f:facet>
                           <h:selectOneRadio value="#{pageBeanName.selectedRadio}" onkeypress="FireButtonClick('okjobbtn');" id="job_radio_btn" onkeyup="toggleRadioKeyUpEnableBtn(this,'okjobbtn');" onmousedown="toggleRadio(this);document.getElementById('okjobbtn').disabled=false;" valueChangeListener="#{pageBeanName.radioBtnChecked}"  styleClass="radioButton_DataTable" >
                           <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                           </h:selectOneRadio>
                          </t:column>
                           <t:column id="code_column" sortable="false" width="30%">
                                <f:facet name="header">                            
                                        <h:outputText id="header_code" value="#{globalResources.Code}" styleClass="standardTable_Header2"/>                          
                                </f:facet>
                                <h:outputText id="content_code" value="#{list.code.key}"/>
                            </t:column>
                            <t:column id="name_column" sortable="false" width="60%">
                                <f:facet name="header">                            
                                        <h:outputText id="header_name" value="#{resourcesBundle.job_name}" styleClass="standardTable_Header2"/>                          
                                </f:facet>
                                <h:outputText id="content_name" value="#{list.name}"/>
                            </t:column>
                    
                </t:dataTable>
                 <t:panelGrid rendered="#{pageBeanName.pagingBean.totalListSize == 0}" align="center" styleClass="msg">
                   <h:outputText value="#{globalResources.global_noTableResults}"/>
                 </t:panelGrid>
                 
                 </t:panelGroup>
  </t:panelGrid>             
                
                



<t:panelGrid columns="1" forceId="true" id="scrollerPanel_popup">
  <h:panelGrid id="panelGrd_scrolleradd_popup" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.jobListSize > shared_util.noOfTableRows}">
          <t:dataScroller 
                    id="scroll_1add_job"     
                    actionListener="#{pageBeanName.scrollerAction}" 
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
                    for="dataT_popup"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <h:panelGroup id="req_list_panelGrp_first">
                        <t:graphicImage id="req_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="req_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="req_list_panelGrp_last">
                            <t:graphicImage id="req_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="req_list_panelGrp_prv">
                            <t:graphicImage id="req_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="req_list_panelGrp_nxt">
                            <t:graphicImage id="req_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="req_list_panelGrp_ffrwrd">
                            <t:graphicImage id="req_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="req_list_panelGrp_frwnd">
                            <t:graphicImage id="req_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
                
              
            </t:dataScroller>
        </h:panelGrid>
        
        
        
        </t:panelGrid>
<t:panelGrid columns="3" forceId="true" id="buttonPanel">
<t:commandButton forceId="true" id="okjobbtn" disabled="true" value="#{globalResources.ok}" action="#{pageBeanName.addJob}" styleClass="cssButtonSmall" onclick="return CheckRadioSelection(this.form,'#{resourcesBundle.Select_One_Item}','errorConsole'); " /> <f:verbatim>&nbsp; </f:verbatim>

<h:panelGroup>
    <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" onblur="settingFoucsAtDivAdd();" onclick="backDelConfirmJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:jsFunction name="backDelConfirmJsFunction" action="#{pageBeanName.hideJobDiv}" oncomplete="hideLookUpDiv(window.blocker,window.delAlert,null,null);foucsFirstElemenyOnPage(); " reRender="jobDivPanelGroup"/>
</h:panelGroup>
<%--<t:inputText value="" style="width=0px; height:0px; font-size=0;"/>--%>
</t:panelGrid>
<!-- Start Paging -->

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

<!-- End Paging -->
</t:panelGroup>
</t:panelGroup>
