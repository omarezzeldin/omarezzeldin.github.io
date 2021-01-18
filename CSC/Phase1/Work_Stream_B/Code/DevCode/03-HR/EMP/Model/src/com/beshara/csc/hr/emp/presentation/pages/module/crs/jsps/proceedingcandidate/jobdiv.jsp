<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:inputText forceId="true" id="tempComponent2" style="width:0px; height:0px;"/>
<t:panelGroup forceId="true" id="divEditLookup">
 <t:saveState value="#{pageBeanName.jobList}"/>
 <t:dataTable id="dataT_Jobs" var="list" value="#{pageBeanName.jobList}" binding="#{pageBeanName.jobTable}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
              styleClass="grid-footer" headerClass="standardTable_Header" rows="5" rowClasses="standardTable_Row1,standardTable_Row2"
              columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
  <t:column sortable="false" width="5%" defaultSorted="true">
   <f:facet name="header">
    <h:outputText value=""/>
   </f:facet>
   <t:selectOneRadio id="chk"   onmousedown="toggleRadio(this);"
                                onkeyup="toggleRadioKeyUp(this);" 
                                onkeypress="FireButtonClick('okJobDivBtn');">
    <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" oncomplete="document.getElementById('okJobDivBtn').disabled=false;"/>
   </t:selectOneRadio>
  </t:column>
  <t:column sortable="false" width="20%" defaultSorted="true">
   <f:facet name="header">
    <h:outputText value="#{globalResources.Code}"/>
   </f:facet>
   <h:outputText id="activity_code" value="#{list.code.key}"/>
  </t:column>
  <t:column sortable="false" width="70%" defaultSorted="true">
   <f:facet name="header">
    <h:outputText id="activity_codestatus" value="#{globalResources.SearchName}"/>
   </f:facet>
   <h:outputText id="activity_code_name" value="#{list.name}"/>
  </t:column>
 </t:dataTable>
</t:panelGroup>
<t:panelGrid columns="1" forceId="true" id="scrollerPanel_jobs">
 <h:panelGrid id="panelGrd_scrolleradd_jobs" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px">
  <t:dataScroller id="scroll_1add_jobs" actionListener="#{pageBeanName.scrollerAction}" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                  paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                  previousStyleClass="textpage" paginatorColumnClass="textpage" paginatorActiveColumnClass="paging" paginatorActiveColumnStyle="font-weight:bold" styleClass="textpage"
                  immediate="false" for="dataT_Jobs" renderFacetsIfSinglePage="false">
   <f:facet name="first">
    <h:panelGroup id="req_list_panelGrp_first">
     <t:graphicImage id="req_list_img_firstOn" rendered="#{pageIndex > 1}" title="#{globalResources.scroller_first}" url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
     <t:graphicImage id="req_list_img_firstOff" onclick="return false;" rendered="#{pageIndex <= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/>
    </h:panelGroup>
   </f:facet>
   <f:facet name="last">
    <h:panelGroup id="req_list_panelGrp_last">
     <t:graphicImage id="req_list_img_lastOn" rendered="#{pageIndex < pageCount}" title="#{globalResources.scroller_last}" url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
     <t:graphicImage id="req_list_img_lastOff" onclick="return false;" rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg" border="0"/>
    </h:panelGroup>
   </f:facet>
   <f:facet name="previous">
    <h:panelGroup id="req_list_panelGrp_prv">
     <t:graphicImage id="req_list_img_prvOn" rendered="#{pageIndex > 1}" title="#{globalResources.scroller_previous}" url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
     <t:graphicImage id="req_list_img_prvOff" onclick="return false;" rendered="#{pageIndex <= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/>
    </h:panelGroup>
   </f:facet>
   <f:facet name="next">
    <h:panelGroup id="req_list_panelGrp_nxt">
     <t:graphicImage id="req_list_img_nxtOn" rendered="#{pageIndex < pageCount}" title="#{globalResources.scroller_next}" url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
     <t:graphicImage id="req_list_img_nxtOff" rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/>
    </h:panelGroup>
   </f:facet>
   <f:facet name="fastforward">
    <h:panelGroup id="req_list_panelGrp_ffrwrd">
     <t:graphicImage id="req_list_img_ffrwrdOn" rendered="#{pageIndex < pageCount}" title="#{globalResources.scroller_fastforward}" url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
     <t:graphicImage id="req_list_img_ffrwrdOff" onclick="return false;" rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/>
    </h:panelGroup>
   </f:facet>
   <f:facet name="fastrewind">
    <h:panelGroup id="req_list_panelGrp_frwnd">
     <t:graphicImage id="req_list_img_frwndOn" rendered="#{pageIndex > 1}" title="#{globalResources.scroller_fastrewind}" url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
     <t:graphicImage id="req_list_img_frwndOff" onclick="return false;" rendered="#{pageIndex <= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg" border="0"/>
    </h:panelGroup>
   </f:facet>
  </t:dataScroller>
 </h:panelGrid>
</t:panelGrid>
<t:panelGrid align="center" columns="2"> 
 <t:commandButton forceId="true" id="okJobDivBtn" value="#{globalResources.ok}" styleClass="cssButtonSmall" disabled="true"/>
 <t:commandButton forceId="true" id="CancelButtonEdit" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null);" 
                    onblur="setFocusOnlyOnElement('tempComponent2');" styleClass="cssButtonSmall"/>
</t:panelGrid>