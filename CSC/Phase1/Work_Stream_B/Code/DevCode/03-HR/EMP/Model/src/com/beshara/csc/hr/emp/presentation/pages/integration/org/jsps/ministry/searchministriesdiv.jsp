    <%@ page contentType="text/html;charset=UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
    <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
    <%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
    <%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
    <%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


    <t:saveState value="#{minHelperBeanName.divSearchType}"/>
    <t:saveState value="#{minHelperBeanName.divSearchQuery}"/>
    <t:saveState value="#{minHelperBeanName.intgSearchMode}"/>
    <t:saveState value="#{minHelperBeanName.renderSearchSection}"/>

    <f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration"
                  var="orgIntgResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

    <t:panelGroup forceId="true" id="minIntgDiv">
    
        <t:panelGroup forceId="true" id="minIntgHideDivImg" styleClass="hideDivPnl">
            <t:panelGroup onclick="toggleDivUsingMaxHeight('minIntgHideDivImg', 'minIntgSearchPnl', 'minIntgSearchResultGroupId')"/>
        </t:panelGroup>
        
        <t:panelGroup id="minIntgSearchPnl" forceId="true">
            <t:panelGrid id="filterCatGridId" columns="2" border="0" align="center" cellpadding="3" cellspacing="0">
                <t:outputText value="#{orgIntgResources.category_label}" styleClass="lable01"/>
                <t:panelGroup>
                    <t:inputText forceId="true" id="orgIntgFirstInputTextId" styleClass="filteration_input_text"
                                 onkeypress="fireEnterBtn(event,this,'categoriesList','minIntgComboError',changeCategoryVal,null);"
                                 onblur="filterationInputOnBlur(event,this,'categoriesList','minIntgComboError',changeCategoryVal,null);"
                                 onkeyup="enabelIntegerOnly(this);"/>
                    <a4j:jsFunction name="changeCategoryVal" action="#{minHelperBeanName.filterDataTableByCategory}"
                                    reRender="filterCatGridId,categoriesList,minIntgSearchResultGroupId,minIntgSearchPanelGroupId,minIntgComboError,minIntgPanelGrd_scrolleradd"/>
                    <f:verbatim>&nbsp;</f:verbatim>
                    <t:selectOneMenu forceId="true" id="categoriesList" value="#{minHelperBeanName.categoryType}"
                                     styleClass="DropdownboxMedium2" onblur="setFocusOnlyOnElement('min_searchRadioBtn');"
                                     onchange="filterationDropboxOnChange(event,this,'orgIntgFirstInputTextId','minIntgComboError',changeCategoryValD);">
                        <f:selectItem itemLabel="#{orgIntgResources.selectOneCategory}"
                                      itemValue="#{minHelperBeanName.virtualConstValue}"/>
                        <t:selectItems value="#{minHelperBeanName.categoriesList}" var="categories"
                                       itemValue="#{categories.code.key}" itemLabel="#{categories.name}"/>
                        <a4j:jsFunction name="changeCategoryValD"
                                        action="#{minHelperBeanName.filterDataTableByCategory}"
                                        reRender="filterCatGridId,minIntgSearchResultGroupId,minIntgSearchPanelGroupId,orgIntgFirstInputTextId,minIntgComboError,minIntgPanelGrd_scrolleradd"/>
                    </t:selectOneMenu>
                    <t:outputText forceId="true" id="minIntgComboError" value="#{orgIntgResources.comboError}"
                                  style="display:none; color:red;" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
            </t:panelGrid>
            <t:panelGroup id="minIntgSearchPanelGroupId" forceId="true">
                <t:panelGrid columns="1" border="0" align="center" rendered="#{minHelperBeanName.renderSearchSection}">
                    <t:selectOneRadio forceId="true" value="#{minHelperBeanName.divSearchType}" id="min_searchRadioBtn"
                                      layout="spread" styleClass="divtext"
                                      onblur="setFocusOnlyOnElement('min_searchText');">
                        <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                        <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
                    </t:selectOneRadio>
                    <t:panelGrid align="center">
                        <t:panelGroup>
                            <t:panelGroup id="minIntgErrorGroup" forceId="true" colspan="2">
                                <t:outputText forceId="true" id="errorMessag_minIntg" styleClass="errMsgNoHeight"/>
                            </t:panelGroup>
                        </t:panelGroup>
                        <t:panelGrid id="radioPanel" align="center" columns="2" dir="#{shared_util.pageDirection}"
                                     styleClass="divtext">
                            <t:radio for="min_searchRadioBtn" index="0"/>
                            <t:radio for="min_searchRadioBtn" index="1"/>
                        </t:panelGrid>
                    </t:panelGrid>
                    <t:panelGrid align="center" columns="4" width="50%" forceId="true" id="minIntgSearchPanel">
                        <t:inputText forceId="true" id="min_searchText" value="#{minHelperBeanName.divSearchQuery}"
                                     styleClass="textboxmedium" onkeypress="fireButtonClickSearch('min_search_btn');" onblur="setFocusOnlyOnElement('min_searchRadioBtn');"/>
                        <t:panelGroup>
                            <t:commandButton type="button" forceId="true" id="min_search_btn"
                                             onclick="if(validateCodeNameSrchParamter('min_searchRadioBtn','min_searchText','','errorMessag_minIntg')){ min_search();}"
                                             styleClass="ManyToManySearchButton"/>
                            <a4j:jsFunction name="min_search" id="min_search"
                                            action="#{minHelperBeanName.searchInResult}"
                                            reRender="minIntgErrorGroup,minIntgNoTableResultsPanelGrid,minIntgSearchPanelGroupId,minIntgSearchResultGroupId,minIntgPanelGrd_scrolleradd,minIntgButtonPanel"/>
                        </t:panelGroup>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <a4j:commandButton id="min_cancelSearch" action="#{minHelperBeanName.cancelSearch}"
                                           reRender="minIntgSearchPanelGroupId,minIntgSearchResultGroupId,minIntgPanelGrd_scrolleradd,minIntgButtonPanel"
                                           styleClass="ManyToManyCancelSearchButton"
                                           rendered="#{minHelperBeanName.intgSearchMode}"/>
                    </t:panelGrid>
                </t:panelGrid>
            </t:panelGroup>
        </t:panelGroup>
        
        <t:panelGroup forceId="true" id="minIntgSearchResultGroupId" styleClass="fullWidthScroll150">
            <t:dataTable forceId="true" id="minIntgDataT_available" var="list" value="#{minHelperBeanName.availableDetails}"
                         rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                         binding="#{minHelperBeanName.availableDataTable}" renderedIfEmpty="false"
                         rowOnMouseOver="javascript:addRowHighlight('minIntgDataT_available');" footerClass="grid-footer"
                         styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                         width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true">
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                    <f:facet name="header">
                        <t:selectBooleanCheckbox forceId="true" id="minIntgCheckAll"
                                                 value="#{minHelperBeanName.checkAllFlagAvailable}"
                                                 onkeypress="FireButtonClick('okBtnId');">
                            <a4j:support event="onclick"
                                         actionListener="#{minHelperBeanName.selectedCheckboxsAllForIntgDiv}"
                                         oncomplete="setAllMinIntg('minIntgCheckAll', 'chk');setFocusOnlyOnElement('okBtnId');"
                                         reRender="selectedAvailableListSize,minIntgButtonPanel"/>
                        </t:selectBooleanCheckbox>
                    </f:facet>
                    <t:selectBooleanCheckbox forceId="true" id="chk" onkeypress="FireButtonClick('okBtnId');"
                                             value="#{list.checked}">
                        <a4j:support event="onclick" actionListener="#{minHelperBeanName.selectedCheckboxsForIntgDiv}"
                                     oncomplete="checkCheckAll('chk');setFocusOnlyOnElement('okBtnId');"
                                     reRender="selectedAvailableListSize,minIntgButtonPanel"/>
                    </t:selectBooleanCheckbox>
                </t:column>
                <t:column id="code_column" sortable="false" width="20%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.key}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="75%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid rendered="#{minHelperBeanName.availableListSize == 0 }" styleClass="msg" align="center"
                         forceId="true" id="minIntgNoTableResultsPanelGrid" style="height:auto; width: 100%;">
                <h:outputText value="#{globalResources.global_noTableResults}"/>
            </t:panelGrid>
        </t:panelGroup>
        
        <t:panelGrid forceId="true" id="minIntgPanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}"
                     styleClass="scroller">
            <t:dataScroller id="minIntgScroll_1add" actionListener="#{minHelperBeanName.scrollerAction}"
                            binding="#{minHelperBeanName.dataScroller}" fastStep="5" pageCountVar="pageCount"
                            pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                            paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                            firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                            previousStyleClass="textpage" paginatorColumnClass="textpage"
                            paginatorActiveColumnClass="paging" paginatorActiveColumnStyle="font-weight:bold"
                            styleClass="textpage" immediate="false" for="minIntgDataT_available"
                            renderFacetsIfSinglePage="false">
                <f:facet name="first">
                    <h:panelGroup id="minIntgReq_list_panelGrp_first">
                        <t:graphicImage id="minIntgReq_list_img_firstOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="minIntgReq_list_img_firstOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <h:panelGroup id="minIntgReq_list_panelGrp_last">
                        <t:graphicImage id="minIntgReq_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="minIntgReq_list_img_lastOff" onclick=" return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <h:panelGroup id="minIntgReq_list_panelGrp_prv">
                        <t:graphicImage id="minIntgReq_list_img_prvOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="minIntgReq_list_img_prvOff" onclick=" return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="minIntgReq_list_panelGrp_nxt">
                        <t:graphicImage id="minIntgReq_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="minIntgReq_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="minIntgReq_list_panelGrp_ffrwrd">
                        <t:graphicImage id="minIntgReq_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="minIntgReq_list_img_ffrwrdOff" onclick=" return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="minIntgReq_list_panelGrp_frwnd">
                        <t:graphicImage id="minIntgReq_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="minIntgReq_list_img_frwndOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                        <t:inputHidden forceId="true" id="minIntgPageIndex" value="#{pageIndex}"
                                       valueChangeListener="#{shared_util.changePageIndex}"/>
                        <t:inputHidden forceId="true" id="minIntgNoOfTableRows" value="#{shared_util.noOfTableRows}"/>
                    </h:panelGroup>
                </f:facet>
            </t:dataScroller>
        </t:panelGrid>
        
        <t:panelGrid columns="3" forceId="true" id="minIntgButtonPanel" align="center">
            <t:commandButton forceId="true" id="okBtnId" value="#{globalResources.ok}"
                             disabled="#{empty minHelperBeanName.selectedDTOSList}"
                             onclick="clearInputText('orgIntgFirstInputTextId');"
                             onblur="setFocusOnlyOnElement('backButtonIntegrationDiv1');"
                             binding="#{minHelperBeanName.okCommandButton}" action="#{minHelperBeanName.closeIntDiv}"
                             styleClass="cssButtonSmall">
            </t:commandButton>
            <t:commandButton forceId="true" id="backButtonIntegrationDiv1" type="button" value="#{globalResources.back}"
                             styleClass="cssButtonSmall" onclick="doMinIntgDivBack();"/>
            <a4j:jsFunction name="minIntgDivBackJS" action="#{minHelperBeanName.resetDivValues}" reRender="minIntgDiv"
                            oncomplete="clearInputText('orgIntgFirstInputTextId'); hideLookUpDiv();"/>
        </t:panelGrid>
        
    </t:panelGroup>
    <script type="text/javascript">
      function fireButtonClickSearch(searchBtnId) {
          FireButtonClick(searchBtnId);
      }

      function doMinIntgDivBack() {
          minIntgDivBackJS();
          return false;
      }

      checkCheckAll('chk');

      function setAllMinIntg(checkAllId, arrayId) {
          var object;
          var pageIndex;
          var noOfTableRows;
          if (document.getElementById(checkAllId) != null)
              object = document.getElementById(checkAllId);

          if (document.getElementById(arrayId) != null)
              document.getElementById(arrayId).value = arrayId;

          if (document.getElementById('minIntgNoOfTableRows') != null)
              noOfTableRows = document.getElementById('minIntgNoOfTableRows').value;

          if (document.getElementById('minIntgPageIndex') != null)
              pageIndex = document.getElementById('minIntgPageIndex').value;

          for (i = 0;;i++) {
              id = arrayId + '[' + i + ']';

              if (document.getElementById) {
                  elem = document.getElementById(id);
                  if (elem == null && i != 0) {
                      break;
                  }
                  else if (elem == null) {
                      i = ((pageIndex - 1) * noOfTableRows) - 1;
                  }
                  else {
                      elem.checked = object.checked;
                      //setSelected(elem, arrayId);
                  }
              }
          }
      }
      
      minIntgCheckAllConflict();
      function minIntgCheckAllConflict() {
          var arrayId = 'chk';
          var pageIndex;
          var noOfTableRows;
          var checkAll = 'minIntgCheckAll';

          if (document.getElementById('minIntgPageIndex') != null && document.getElementById('minIntgPageIndex').value != '')
              pageIndex = document.getElementById('minIntgPageIndex').value;
          if (document.getElementById('minIntgNoOfTableRows') != null)
              noOfTableRows = document.getElementById('minIntgNoOfTableRows').value;

          var startIndex = eval((pageIndex * noOfTableRows) - noOfTableRows);
          var endIndex = startIndex + (noOfTableRows - 1);

          var uncheckedOneFound = false;
          var chkElmId = '';
          var chkElm = null;
          for (var i = startIndex;i <= endIndex;i++) {
              chkElmId = arrayId + '[' + i + ']';

              if (document.getElementById(chkElmId) != null) {
                  chkElm = document.getElementById(chkElmId);

                  if (chkElm.checked == false) {
                      uncheckedOneFound = true;
                      break;
                  }
              }
          }
          if (uncheckedOneFound) {
              document.getElementById(checkAll).checked = false;
          }
          else {
              document.getElementById(checkAll).checked = true;
          }
      }

          function fireEnterBtn(e, inputtext, dropdownId, errMsgId, ajaxFunction, nextFocusId) {
              filterationInputOnKeyPress(e, inputtext, dropdownId, errMsgId, ajaxFunction, nextFocusId);
          }
    </script>
    

 
