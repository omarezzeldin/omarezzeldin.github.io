<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.popup_body {
   max-hight:368px important;
}
</htm:style>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:saveState value="#{detailBeanName.availableDetails}"/>
<t:saveState value="#{detailBeanName.searchMode}"/>
<t:saveState value="#{detailBeanName.searchString}"/>
<t:saveState value="#{detailBeanName.availableListSize}"/>

<t:panelGroup forceId="true" id="resetPublicConditionDivGroup">
    
    <t:panelGrid align="center">
        <t:selectOneRadio forceId="true" id="searchRadioBtn" value="#{detailBeanName.searchTypeStr}" styleClass="divtext">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{detailBeanName.searchTypeByCode}"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{detailBeanName.searchTypeByName}"/>
        </t:selectOneRadio>
    </t:panelGrid>
    
    <t:panelGrid columns="5" width="25%" forceId="true" id="searchPanel"  align="center">
        <t:inputText  onkeypress="FireButtonClick('myForm:searchAvaliabelCondition');" forceId="true" id="add_first_inputTxt" 
            value="#{detailBeanName.searchString}" styleClass="textboxmedium"/>
        
        <a4j:commandButton type="button" id="searchAvaliabelCondition" 
            onclick="if(validateCodeNameSrchParamter('searchRadioBtn','add_first_inputTxt','','errorMessag_lov')){ lov_search2();}"
            styleClass="ManyToManySearchButton"/>
        
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton action="#{detailBeanName.cancelSearchAvailable}" oncomplete="settingFoucsAtDivAdd();" reRender="divAdds,buttonPanel,scrollerPanel,add_first_inputTxt,searchPanel,errorConsole"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchMode}" />
        
        <a4j:jsFunction name="lov_search2"
            oncomplete="settingFoucsAtDivAdd();" 
            action="#{detailBeanName.searchAvailable}" 
            reRender="divAdds,buttonPanel,scrollerPanel,searchPanel,errorConsole"/>
        
    </t:panelGrid>
                                
    <t:panelGroup colspan="2" style="height:20px;">
        <t:outputText value="#{detailBeanName.errorMsgValue}" forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight"/>
        
    </t:panelGroup>
    
    <t:panelGroup forceId="true" id="divAdds"  styleClass="delDivScroll2" style="width:100%;display: block;height: 170px;">
    
        <t:panelGrid rendered="#{detailBeanName.availableListSize == 0}" styleClass="msg" align="center">
            <h:outputText value="#{globalResources.global_noTableResults}"/>
        </t:panelGrid>
        
        <t:dataTable id="dataT_available" var="list" value="#{detailBeanName.availableDetails}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
            binding="#{detailBeanName.availableDataTable}" renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
            footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
            dir="#{shared_util.pageDirection}" preserveSort="true" >
                
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header">
                </f:facet>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chkCond" valueChangeListener="#{detailBeanName.selectedRadioButton}" 
                    value="" onmousedown="toggleRadio(this);document.getElementById('SaveButton').disabled=false;" 
                    onkeyup="toggleRadioKeyUpEnableBtn(this,'SaveButton');" onkeypress="FireButtonClick('SaveButton');">
                    <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />                         
                </t:selectOneRadio>
            </t:column>
            
            <t:column id="code_column" sortable="false" width="20%">
                <f:facet name="header">                           
                        <h:outputText id="header_code" value="#{globalResources.Code}" styleClass="standardTable_Header2"/>                          
                </f:facet>
              <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            
            <t:column id="name_column" sortable="false" width="75%">
                <f:facet name="header">                            
                        <h:outputText id="header_name" value="#{globalResources.name}" styleClass="standardTable_Header2"/>                          
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
            
        </t:dataTable>
    </t:panelGroup>
    
    <t:panelGrid columns="1" forceId="true" id="scrollerPanel" align="center">
        <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
                
            <t:dataScroller id="scroll_1add"     actionListener="#{detailBeanName.scrollerAction}" binding="#{detailBeanName.dataScroller}"
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
                    for="dataT_available"
                    renderFacetsIfSinglePage="false">
                    <f:facet name="first" >                            
                        <h:panelGroup id="req_list_panelGrp_first">
                            <t:graphicImage id="req_list_img_firstOn"
                                                    rendered="#{pageIndex > 1}"
                                                    title="#{globalResources.scroller_first}"
                                                    url="/app/media/images//#{globalResources.photoFolder}/back3.jpg"
                                                    border="0"/>
                            <t:graphicImage id="req_list_img_firstOff"
                                                    onclick="return false;"
                                                    rendered="#{pageIndex <= 1}"
                                                    url="/app/media/images//#{globalResources.photoFolder}/dis-back3.jpg"
                                                    border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="last">                            
                        <h:panelGroup id="req_list_panelGrp_last">
                                <t:graphicImage id="req_list_img_lastOn"
                                                rendered="#{pageIndex < pageCount}"
                                                title="#{globalResources.scroller_last}"
                                                url="/app/media/images//#{globalResources.photoFolder}/next3.jpg"
                                                border="0"/>
                                <t:graphicImage id="req_list_img_lastOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images//#{globalResources.photoFolder}/dis-next3.jpg"
                                                border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="previous">                            
                        <h:panelGroup id="req_list_panelGrp_prv">
                                <t:graphicImage id="req_list_img_prvOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_previous}"
                                                url="/app/media/images//#{globalResources.photoFolder}/back1.jpg"
                                                border="0"/>
                                <t:graphicImage id="req_list_img_prvOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images//#{globalResources.photoFolder}/dis-back1.jpg"
                                                border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="next">                            
                        <h:panelGroup id="req_list_panelGrp_nxt">
                                <t:graphicImage id="req_list_img_nxtOn"
                                                rendered="#{pageIndex < pageCount}"
                                                title="#{globalResources.scroller_next}"
                                                url="/app/media/images//#{globalResources.photoFolder}/next1.jpg"
                                                border="0"/>
                                <t:graphicImage id="req_list_img_nxtOff"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images//#{globalResources.photoFolder}/dis-next1.jpg"
                                                border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="fastforward">
                        <h:panelGroup id="req_list_panelGrp_ffrwrd">
                                <t:graphicImage id="req_list_img_ffrwrdOn"
                                                rendered="#{pageIndex < pageCount}"
                                                title="#{globalResources.scroller_fastforward}"
                                                url="/app/media/images//#{globalResources.photoFolder}/next2.jpg"
                                                border="0"/>
                                <t:graphicImage id="req_list_img_ffrwrdOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images//#{globalResources.photoFolder}/dis-next2.jpg"
                                                border="0"/>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="fastrewind">
                        <h:panelGroup id="req_list_panelGrp_frwnd">
                                <t:graphicImage id="req_list_img_frwndOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_fastrewind}"
                                                url="/app/media/images//#{globalResources.photoFolder}/back2.jpg"
                                                border="0"/>
                                <t:graphicImage id="req_list_img_frwndOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images//#{globalResources.photoFolder}/dis-back2.jpg"
                                                border="0"/>
                                
                        </h:panelGroup>
                        
                    </f:facet>
                    
            </t:dataScroller>
        </h:panelGrid>
            
    </t:panelGrid>
            
    <%-- End css modification add two break line to UI --%>
    <t:panelGrid columns="3" forceId="true" id="buttonPanel" align="center">
    
    <t:commandButton id="SaveButton" type="button" forceId="true"  value="#{globalResources.ok}"  styleClass="cssButtonSmall" rendered="#{detailBeanName.availableListSize > 0}" disabled="true">
        <a4j:support action="#{detailBeanName.addCondition}" event="onclick"  oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,'myForm:Name','myForm:error','add');changeVisibilityMsg(); document.getElementById('SaveButton').disabled = true; setFocusAtMyFirstElement();" reRender="publicCondition,conditionCode,msgShow"/>
    </t:commandButton>

    <f:verbatim>&nbsp; </f:verbatim>

    <h:panelGroup>
            <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{detailBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,'add_first_inputTxt','myForm:error','add');setFocusAtMyFirstElement(); " reRender="customDiv1,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
        </h:panelGroup>
    </t:panelGrid>
    
</t:panelGroup>

<t:saveState value="#{detailBeanName.errorMsgValue}"/>
