<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<h:messages showDetail="true"   />

            <t:panelGrid width="480px" align="center" style="text-align:center; display: inline;">
                <t:selectOneRadio forceId="true" id="searchRadioBtn" value="#{detailBeanName.searchTypeStr}" styleClass="divtext">
                     <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{detailBeanName.searchTypeByCode}"/>
                     <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{detailBeanName.searchTypeByName}"/>
                </t:selectOneRadio>
            </t:panelGrid>
                
            <t:panelGrid columns="4" width="50%" forceId="true" id="searchPanel" align="center">
                <%--
                <h:outputText value="#{globalResources.name}"  styleClass="lable01" />
                --%>
                <t:inputText forceId="true" id="add_first_inputTxt" onkeypress="FireButtonClick('myForm:search_name_btn');"
                    value="#{detailBeanName.searchString}" 
                    styleClass="textboxmedium" onblur="document.getElementById('myForm:search_name_btn').focus();"/>
                <a4j:commandButton id="search_name_btn" action="#{detailBeanName.searchAvailable}" oncomplete="settingFoucsAtDivAdd();"
                    reRender="divAdds,buttonPanel,scrollerPanel,searchPanel,errorConsole"  styleClass="ManyToManySearchButton"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <a4j:commandButton action="#{detailBeanName.cancelSearchAvailable}"  oncomplete="settingFoucsAtDivAdd();"
                    reRender="divAdds,buttonPanel,scrollerPanel,add_first_inputTxt,searchPanel,errorConsole"  
                    styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchMode}" />
            </t:panelGrid>

            <t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsgNoHeight" />
            <t:panelGroup forceId="true" id="divAdds" style="height: 205px;overflow:auto;display:block">
            <t:saveState value="#{detailBeanName.searchMode}" />
           
            <t:panelGrid rendered="#{detailBeanName.availableListSize == 0}" styleClass="msg msg_no_icon" align="center" style="width:auto !important;">
          
            <h:outputText value="#{globalResources.global_noTableResults}"/>
      
            </t:panelGrid>
           
        
            <t:dataTable id="dataT_available" var="list" value="#{detailBeanName.availableDetails}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                            binding="#{detailBeanName.availableDataTable}" renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                            footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                            dir="#{shared_util.pageDirection}" preserveSort="true" >
                <t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                    <f:facet name="header">
                        <t:selectBooleanCheckbox forceId="true" id="checkAllAdd"
                            value="#{detailBeanName.checkAllFlagAvailable}" onkeypress="FireButtonClick('SaveButton');">
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');setFocusOnlyOnElement('SaveButton');" reRender="selectedAvailableListSize,SaveButton"/>
                        </t:selectBooleanCheckbox>
                    </f:facet>
                    <t:selectBooleanCheckbox forceId="true" id="chk2Add" value="#{list.checked}" onkeypress="FireButtonClick('SaveButton');">
                        <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add');setFocusOnlyOnElement('SaveButton');" reRender="selectedAvailableListSize,SaveButton" />
                    </t:selectBooleanCheckbox>
                    </t:column>
                    <t:column id="code_column" sortable="false" width="20%">
                        <f:facet name="header">                           
                                <h:outputText id="header_code" value="#{globalResources.Code}" styleClass="standardTable_Header2"/>                          
                        </f:facet>
                      <h:outputText id="content_code" value="#{list.documentTypeDTO.code.key}"/>
                    </t:column>
                    <t:column id="name_column" sortable="false" width="55%">
                        <f:facet name="header">                            
                                <h:outputText id="header_name" value="#{globalResources.name}" styleClass="standardTable_Header2"/>                          
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.documentTypeDTO.name}"/>
                    </t:column>
                
                   <t:column id="required_column" sortable="false" width="20%">
                        <f:facet name="header">                            
                                <h:outputText id="header_required" value="#{resourcesBundle.document_required_status}" styleClass="standardTable_Header2"/>                          
                        </f:facet>
                        <t:graphicImage border="0"  url="/app/media/images/DataGrid_Icon_ok.gif" width="15" height="15"
                    rendered="#{list.basicStatusBoolean == true }"/>
                    
               <t:graphicImage border="0"  url="/app/media/images/DataGrid_Icon_not.gif" width="15" height="15"
                    rendered="#{list.basicStatusBoolean == false }"/>
    
    <h:inputHidden value="#{list.basicStatusBoolean}"/>
                    </t:column>
                </t:dataTable>
                
</t:panelGroup>


<t:panelGrid columns="1" forceId="true" id="scrollerPanel">
  <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
            
          <t:dataScroller id="scroll_1add"     actionListener="#{pageBeanName.scrollerAction}" binding="#{pageBeanName.dataScroller}"
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
     
        
<t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
<%-- Start css modification add two break line to UI --%>
<%-- End css modification add two break line to UI --%>
<t:panelGrid columns="3" forceId="true" id="buttonPanel" align="center">
<t:commandButton id="SaveButton" forceId="true" value="#{globalResources.ok}" action="#{detailBeanName.add}" styleClass="cssButtonSmall" onclick="ignoreClientSideValidation();return confirmCheckBoxSelection('chk2Add');" rendered="#{detailBeanName.availableListSize > 0}" disabled="#{detailBeanName.selectedAvailableListSize == 0}"/> <f:verbatim>&nbsp; </f:verbatim>
<%-- <h:commandButton id="cancel" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error');" styleClass="cssButtonSmall" /> --%>
<h:panelGroup style="text-align:center; ">
    <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:jsFunction name="backJsFunction" action="#{detailBeanName.cancelSearchAvailable}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " reRender="divAdds,buttonPanel,scrollerPanel,add_first_inputTxt,searchPanel,errorConsole"/>    
</h:panelGroup>

</t:panelGrid>


<t:saveState value="#{detailBeanName.searchTypeStr}"/>
