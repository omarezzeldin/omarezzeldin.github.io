<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:verbatim>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td align="center">
                <table border="0" width="99%" id="table51" cellspacing="0" cellpadding="0">
                    <tr>
                        <td valign="bottom" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                        <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                        <td valign="bottom" width="12"><img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                    </tr>
                    <tr>
                        <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                        <td valign="top" bgcolor="#FFFFFF" class="paddingbox">
                            </f:verbatim>
                               <t:panelGrid columns="4" width="100%" forceId="true" id="searchPanel1" >                            
                                     <h:outputText value="#{resourcesBundle.work_ministry_name}"  styleClass="lable01" id="searchNameID1"/>
                                     <t:inputText forceId="true" id="searchText1"  value="#{detailBeanName.searchWorkMinistriesString}" styleClass="textboxmedium" />                                     
                                      <a4j:commandButton action="#{detailBeanName.searchWorkMinistry}" reRender="divAddPopup1,searchPanel1,scrollerPanel_popup1"  styleClass="ManyToManySearchButton" />                                     
                                      <a4j:commandButton action="#{detailBeanName.cancelSearchWorkMinistry}" reRender="divAddPopup1,searchPanel1,scrollerPanel_popup1,errorConsole1"  styleClass="ManyToManyCancelSearchButton" rendered="#{detailBeanName.searchWorkMinistriesMode}"/>
                                </t:panelGrid>
                            <f:verbatim>
                        </td>
                        <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                    </tr>
                    <tr>
                        <td valign="top" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
                        <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                        <td valign="top"><img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
        
        
            </f:verbatim>
            <t:outputText value="" forceId="true" id="errorConsole1" styleClass="errMsgNoHeight" />
            <t:panelGroup forceId="true" id="divAddPopup1" styleClass="delDivScroll"  style="height: 120px">
                    
                    <t:dataTable  id="dataT_popup1" var="list" value="#{detailBeanName.workMinistriesList}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                                     renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_popup1');"
                                    footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                    columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                                    dir="#{shared_util.pageDirection}" preserveSort="true"  binding="#{detailBeanName.workMinistriesDataTable}" >
                            <t:column sortable="false" width="5%">
                           <f:facet name="header">
                            <h:outputText value=""/>
                           </f:facet>
                           <h:selectOneRadio id="work_ministry_radio_btn" onclick="toggleRadio(this);" valueChangeListener="#{detailBeanName.radioBtnChecked}"  >
                           <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                           </h:selectOneRadio>
                          </t:column>
                          
                            <t:column id="name_column1" sortable="false" width="75%">
                                <f:facet name="header">                            
                                        <h:outputText id="header_name1" value="#{resourcesBundle.work_ministry_name}" styleClass="standardTable_Header2"/>                          
                                </f:facet>
                                <h:outputText id="content_name1" value="#{list.name}"/>
                            </t:column>
                    
                </t:dataTable>
                 <t:panelGrid rendered="#{empty detailBeanName.workMinistriesList}" align="center" styleClass="msg">
                   <h:outputText value="#{globalResources.global_noTableResults}"/>
                 </t:panelGrid>
                <f:verbatim>
                </td></tr></table>
                </f:verbatim>
</t:panelGroup>


<t:panelGrid columns="1" forceId="true" id="scrollerPanel_popup1">
  <h:panelGrid id="panelGrd_scrolleradd_popup1" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.workMinistriesListSize > shared_util.noOfTableRows}">
          <t:dataScroller 
                    id="scroll_1add_work_ministry"     
                    actionListener="#{detailBeanName.scrollerAction}" 
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
                    <h:panelGroup id="req_list_panelGrp_first1">
                        <t:graphicImage id="req_list_img_firstOn1"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="req_list_img_firstOff1"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="req_list_panelGrp_last1">
                            <t:graphicImage id="req_list_img_lastOn1"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_lastOff1"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="req_list_panelGrp_prv1">
                            <t:graphicImage id="req_list_img_prvO1"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_prvOff3"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="req_list_panelGrp_nxt1">
                            <t:graphicImage id="req_list_img_nxtOn1"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_nxtOff1"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="req_list_panelGrp_ffrwrd1">
                            <t:graphicImage id="req_list_img_ffrwrdOn1"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_ffrwrdOff1"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="req_list_panelGrp_frwnd1">
                            <t:graphicImage id="req_list_img_frwndOn1"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_frwndOff1"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
                
              
            </t:dataScroller>
        </h:panelGrid>
        
        
        
        </t:panelGrid>
<t:panelGrid columns="3" forceId="true" id="buttonPanel1" align="center" >
<t:commandButton id="ok21" value="#{globalResources.ok}" action="#{detailBeanName.addworkMinistry}" styleClass="cssButtonSmall" onclick="return CheckRadioSelection(this.form,'#{resourcesBundle.Select_One_Item}','errorConsole'); " /> <f:verbatim>&nbsp; </f:verbatim>
<a4j:commandButton  id="cancel1" value="#{globalResources.back}"  styleClass="cssButtonSmall" type="button"  onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);"/> 

</t:panelGrid>