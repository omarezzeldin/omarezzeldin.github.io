<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup forceId="true" id="divAddLookup">

    <t:panelGrid border="0" align="center">
        <t:selectOneRadio forceId="true" id="lov_searchRadioBtn" value="#{pageBeanName.lovBaseBean.searchTyp}" styleClass="divtext" onblur="settingFoucsAtLovDiv();">
             <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
             <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
        </t:selectOneRadio>
    </t:panelGrid>
    
    <t:panelGrid  border="0" columns="3" id="lov_searchPanel" align="center" dir="#{shared_util.pageDirection}" forceId="true">
        <%--
        <t:outputText value="#{resourcesBundle[detailBeanName.titleFlageOfDiv]}"  styleClass="lable01" id="title_Id"/>
        --%>
        <t:inputText forceId="true" id="lov_searchText"  value="#{pageBeanName.lovBaseBean.searchQuery}" styleClass="textboxmedium"  onkeypress="FireButtonClick('myForm:lov_search');"/>
        <a4j:commandButton id="lov_search" action="#{pageBeanName.searchQual}" oncomplete="settingFoucsAtLovDiv();" reRender="divAddLookup,scrollerPanel_qual"  styleClass="ManyToManySearchButton"/>     
        <a4j:commandButton id="lov_cancelSearch" oncomplete="settingFoucsAtLovDiv();" action="#{pageBeanName.cancelSearchQual}" reRender="divAddLookup,scrollerPanel_qual"  styleClass="ManyToManyCancelSearchButton" rendered="#{pageBeanName.lovBaseBean.searchMode}" />
    </t:panelGrid>
  
    <t:inputText forceId="true" id="tempComponent" style="width:0px; height:0px;"/>
    
    <t:panelGrid align="center" border="0" cellpadding="0" cellspacing="0">
    <t:panelGroup forceId="true" id="lov_dataT_data_panel" styleClass="delDivScroll">
    <t:dataTable id="dataT_Qual" var="list" value="#{pageBeanName.qualList}"
         forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
         renderedIfEmpty="false" binding="#{pageBeanName.qualTable}"
         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" 
         styleClass="grid-footer" headerClass="standardTable_Header"
         rowClasses="standardTable_Row1,standardTable_Row2" 
         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" 
         width="100%" align="top" dir="#{shared_util.pageDirection}">
       
        <t:column sortable="false" width="5%">
            <f:facet name="header">
                <t:outputText value=""/>
            </f:facet>
            <t:selectOneRadio id="chkQual" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);" onkeypress="FireButtonClick('ok_qul_btn');">
                <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButtonQual}" oncomplete="document.getElementById('ok_qul_btn').disabled=false;"/>
            </t:selectOneRadio>
        </t:column>
        
        <t:column sortable="false" width="20%" >
            <f:facet name="header">
                <t:outputText id="activity_codename" value="#{globalResources.Code}"/>
            </f:facet>
            <t:outputText id="qual_code" value="#{list.code.key}"/>
        </t:column>
        
        <t:column sortable="false" width="70%" defaultSorted="true">
            <f:facet name="header">
                <t:outputText id="activity_codestatus" value="#{globalResources.SearchName}"/>
            </f:facet>
            <t:outputText id="qual_name" value="#{list.name}"/>
        </t:column>
        
    </t:dataTable>
    </t:panelGroup>
    </t:panelGrid>



<t:panelGrid  border="0" columns="1" forceId="true" id="scrollerPanel_qual">
    <t:panelGrid  border="0" id="panelGrd_scrolleradd_qual" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px">
        
        <t:dataScroller id="scroll_1add_qual" actionListener="#{pageBeanName.scrollerActionQual}" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                  paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                  nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage" paginatorActiveColumnClass="paging" paginatorActiveColumnStyle="font-weight:bold"
                  styleClass="textpage" immediate="false" for="dataT_Qual" renderFacetsIfSinglePage="false">
                  
            <f:facet name="first">
                <t:panelGroup id="list_panelGrp_first">
                    <t:graphicImage id="list_img_firstOn" rendered="#{pageIndex > 1}" title="#{globalResources.scroller_first}" url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                    <t:graphicImage id="list_img_firstOff" onclick="return false;" rendered="#{pageIndex <= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="last">
                <t:panelGroup id="list_panelGrp_last">
                    <t:graphicImage id="list_img_lastOn" rendered="#{pageIndex < pageCount}" title="#{globalResources.scroller_last}" url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                    <t:graphicImage id="list_img_lastOff" onclick="return false;" rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="previous">
                <t:panelGroup id="list_panelGrp_prv">
                    <t:graphicImage id="list_img_prvOn" rendered="#{pageIndex > 1}" title="#{globalResources.scroller_previous}" url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                    <t:graphicImage id="list_img_prvOff" onclick="return false;" rendered="#{pageIndex <= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="next">
                <t:panelGroup id="list_panelGrp_nxt">
                    <t:graphicImage id="list_img_nxtOn" rendered="#{pageIndex < pageCount}" title="#{globalResources.scroller_next}" url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                    <t:graphicImage id="list_img_nxtOff" rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="fastforward">
                <t:panelGroup id="list_panelGrp_ffrwrd">
                    <t:graphicImage id="list_img_ffrwrdOn" rendered="#{pageIndex < pageCount}" title="#{globalResources.scroller_fastforward}" url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                    <t:graphicImage id="list_img_ffrwrdOff" onclick="return false;" rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="fastrewind">
                <t:panelGroup id="list_panelGrp_frwnd">
                    <t:graphicImage id="list_img_frwndOn" rendered="#{pageIndex > 1}" title="#{globalResources.scroller_fastrewind}" url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                    <t:graphicImage id="list_img_frwndOff" onclick="return false;" rendered="#{pageIndex <= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
        </t:dataScroller>
    </t:panelGrid>
</t:panelGrid>

<t:panelGrid  border="0" align="center" columns="2"> 
    <t:commandButton forceId="true" id="ok_qul_btn" value="#{globalResources.ok}" styleClass="cssButtonSmall" disabled="true"/>
    <t:commandButton forceId="true" id="backButtonAddDiv" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,null,null);" 
                    onblur="setFocusOnlyOnElement('tempComponent');" styleClass="cssButtonSmall"/>
</t:panelGrid>

</t:panelGroup>
