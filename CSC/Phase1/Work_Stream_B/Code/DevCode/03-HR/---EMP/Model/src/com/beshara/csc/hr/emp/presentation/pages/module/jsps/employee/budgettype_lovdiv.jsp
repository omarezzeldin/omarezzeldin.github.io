<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

    <htm:table border="0" cellpadding="0" cellspacing="0" width="100%">
        <htm:tr>
            <htm:td align="center">
                <t:panelGrid align="center" columns="4" width="50%" forceId="true" id="lov_searchPanel">
                             <t:inputText forceId="true" id="lov_searchText"  value="#{pageBeanName.lovBaseBean.searchQuery}" 
                                styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:lov_search');"/>
                             <a4j:commandButton id="lov_search" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.lovBaseBean.searchLovDiv}" reRender="lov_dataT_data_panel,lov_searchPanel,lov_errorConsole,lov_paging_panel"  styleClass="ManyToManySearchButton"/>
                             <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                             <a4j:commandButton id="lov_cancelSearch" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.lovBaseBean.cancelSearchLovDiv}" reRender="lov_searchText,lov_dataT_data_panel,lov_searchPanel,lov_errorConsole,lov_paging_panel,lov_searchRadioBtn"  styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.lovBaseBean.searchMode}" />
                </t:panelGrid>
            </htm:td>
        </htm:tr>
        <htm:tr>
            <htm:td>
        
            <t:outputText value="" forceId="true" id="lov_errorConsole" styleClass="errMsgNoHeight" />
            <t:saveState value="#{pageBeanName.lovBaseBean.searchMode}" />           
            
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="lov_dataT_data_panel" styleClass="delDivScroll"  style="display: block;height: 220px;overflow: auto">
        <t:dataTable id="lov_dataT_data" var="list" value="#{pageBeanName.lovBaseBean.myTableData}" rowStyleClass="#{ pageBeanName.lovBaseBean.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.lovBaseBean.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.lovBaseBean.sortColumn}" sortAscending="#{pageBeanName.lovBaseBean.ascending}">
       <t:column id="check_column_" styleClass="standardTable_Header3" width="5%">
        <f:facet name="header">
        </f:facet>
        <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.lovBaseBean.selectedRadio}" 
            onmousedown="toggleRadio(this);document.getElementById('myForm:lov_ok').disabled=false;" 
            onkeyup="toggleRadioKeyUpEnableBtn(this,'myForm:lov_ok');"
            onkeypress="FireButtonClick('myForm:lov_ok');">
         <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
         <a4j:support event="onclick" actionListener="#{pageBeanName.lovBaseBean.selectedRadioButton}"/>
        </t:selectOneRadio>
       </t:column> 
       
            <t:column id="code_column_" sortable="false"  width="20%">
                <f:facet name="header">
                 <t:commandLink styleClass="headerLink" value="#{globalResources.Code}" onclick="return false;">                         
                 </t:commandLink>
                </f:facet>
                <h:outputText id="content_code_" value="#{list.code.keys[1]}"/>
            </t:column>
            
            <t:column id="name_column_" sortable="false" width="75%">
                <f:facet name="header">
                 <t:commandLink styleClass="headerLink" value="#{globalResources.name}" onclick="return false;">
                 </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_name_" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.lovBaseBean.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
      
    </t:panelGroup>    
</t:panelGrid>

                </htm:td></htm:tr></htm:table>
                
                
<t:panelGroup forceId="true" id="lov_paging_panel">
        <h:panelGrid id="lov_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.lovBaseBean.listSize > shared_util.noOfTableRows}" >
            
          <t:dataScroller id="lov_scroll_1" 
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
                    <h:panelGroup id="lov_jobs_list_panelGrp_first">
                        <t:graphicImage id="lov_jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="lov_jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_last">
                            <t:graphicImage id="lov_jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_prv">
                            <t:graphicImage id="lov_jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="lov_jobs_list_panelGrp_nxt">
                            <t:graphicImage id="lov_jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="lov_jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jlov_obs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="lov_jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="lov_jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="lov_lov_jobs_list_img_frwndOff"
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
 <t:commandButton onclick="block();" disabled="true" id="lov_ok" value="#{globalResources.ok}" action="#{pageBeanName.lovBaseBean.closeLovDiv}" styleClass="cssButtonSmall"  binding="#{pageBeanName.lovBaseBean.okCommandButton}" />
 <t:commandButton forceId="true" id="lov_cancel" onblur="settingFoucsAtLovDiv();"  type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.divLov);" styleClass="cssButtonSmall"/>
</t:panelGrid>
<t:saveState value="#{pageBeanName.lovBaseBean.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.fullColumnName}"/>
<t:saveState value="#{pageBeanName.lovBaseBean.sortAscending}"/>
<t:saveState id="lovBaseBean" value="#{pageBeanName.lovBaseBean.myTableData}" />
<t:saveState id="lovSearchType" value="#{pageBeanName.lovBaseBean.searchTyp}" />
<t:saveState id="lovSearchQuery" value="#{pageBeanName.lovBaseBean.searchQuery}" />
<t:saveState id="lovRenderedDropDown" value="#{pageBeanName.lovBaseBean.renderedDropDown}" />
<t:saveState id="lovsearchMode"  value="#{pageBeanName.lovBaseBean.searchMode}"/>
<t:saveState id="lovreturnMethodName" value="#{pageBeanName.lovBaseBean.returnMethodName}"/>
<t:saveState id="lovsearchMethod" value="#{pageBeanName.lovBaseBean.searchMethod}" />
<t:saveState id="lovcancelSearchMethod" value="#{pageBeanName.lovBaseBean.cancelSearchMethod}" />
