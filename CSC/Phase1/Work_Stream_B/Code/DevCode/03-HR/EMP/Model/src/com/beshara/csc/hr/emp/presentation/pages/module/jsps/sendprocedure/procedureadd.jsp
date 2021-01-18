<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.procedures_list}"/>
<t:saveState value="#{pageBeanName.searchHireProceduresDTO}"/>
<f:verbatim>

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td align="center">
                <table border="0" width="99%" id="table5" cellspacing="0" cellpadding="0">
                    <tr>
                        <td valign="bottom" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                        <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                        <td valign="bottom" width="12"><img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                    </tr>
                    <tr>
                        <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                        <td valign="top" bgcolor="#FFFFFF" class="paddingbox">
                            </f:verbatim>
                                <t:panelGrid columns="5" width="50%" forceId="true" id="searchPanel">
                                    <h:outputText value="#{resourcesBundle.name_procedure}"  styleClass="lable01" />
                                    <t:inputText onkeypress="FireButtonClick('myForm:search_procedure_btn');"  forceId="true" id="add_first_inputTxt" value="#{pageBeanName.searchHireProceduresDTO.hirprocedureName}" styleClass="textboxmedium" />
                                    <a4j:commandButton oncomplete="settingFoucsAtDivAdd();" id="search_procedure_btn" action="#{pageBeanName.search}" reRender="divAdds,buttonPanel,scrollerPanel,searchPanel,errorConsole" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"/>
                                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                    <a4j:commandButton oncomplete="settingFoucsAtDivAdd();" action="#{pageBeanName.cancelSearch}" reRender="divAdds,buttonPanel,scrollerPanel,add_first_inputTxt,searchPanel,errorConsole"  styleClass="cssButtonSmall"  value="#{globalResources.ViewAllButton}" rendered="#{pageBeanName.searchMode}" />
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
            <t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsgNoHeight" />
            <t:panelGroup forceId="true" id="divAdds" styleClass="delDivScroll"  style="height: 120px; width:100%;">
            <t:saveState value="#{pageBeanName.searchMode}" />
           
           
           
           
            <t:dataTable id="dataT_available" var="list" value="#{pageBeanName.procedures_list}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                            binding="#{pageBeanName.myDataTable}" renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                            footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                            dir="#{shared_util.pageDirection}" preserveSort="true" >
                    <t:column sortable="false" width="5%">
                   <f:facet name="header">
                    <h:outputText value=""/>
                   </f:facet>
                  <t:selectOneRadio  id="chkCond" value="#{pageBeanName.selectedRadio}" valueChangeListener="#{pageBeanName.selectedRadioButton}" onclick="toggleRadio(this); document.getElementById('sending_button').disabled=false;">
                           <f:selectItem    itemLabel=" " itemValue="#{list.code.key}" />
                        </t:selectOneRadio>
                  </t:column>
                  
                    <t:column id="name_column" sortable="false" width="75%">
                        <f:facet name="header">                            
                                <h:outputText id="header_name" value="#{resourcesBundle.name_procedure}" styleClass="standardTable_Header2"/>                          
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.name}"/>
                    </t:column>
                </t:dataTable>
                 <t:panelGrid rendered="#{empty pageBeanName.procedures_list }" align="center" styleClass="msg">
                   <h:outputText value="#{globalResources.global_noTableResults}"/>
                 </t:panelGrid>
                <f:verbatim>
                </td></tr></table>
                </f:verbatim>
</t:panelGroup>


<t:panelGrid columns="1" forceId="true" id="scrollerPanel">
  <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{pageBeanName.procedures_list_size > shared_util.noOfTableRows}">
            
          <t:dataScroller 
                    id="scroll_1add"     
                    actionListener="#{pageBeanName.scrollerActionProcedure}"
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
<t:panelGrid columns="3" forceId="true" id="buttonPanel">
<t:commandButton forceId="true" id="sending_button" disabled="true" value="#{globalResources.addButton}" action="#{pageBeanName.add_procedure}" styleClass="cssButtonSmall" /> <f:verbatim>&nbsp; </f:verbatim>
                         
<h:panelGroup>
    <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.exit_procedure}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');setFocusAtMyFirstElement(); " reRender="divAdds,buttonPanel,scrollerPanel,add_first_inputTxt,searchPanel,errorConsole"/>
</h:panelGroup>

</t:panelGrid>