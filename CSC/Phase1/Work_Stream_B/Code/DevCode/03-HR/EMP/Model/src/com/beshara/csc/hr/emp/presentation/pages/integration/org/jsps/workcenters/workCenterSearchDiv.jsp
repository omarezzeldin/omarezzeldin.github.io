<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.DropdownboxLarge { width: 320px; }</htm:style>
<!--tiles:importAttribute scope="request"/-->
<f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration" var="orgIntgResources"/>
<t:panelGroup forceId="true" id="hideDivImgId" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingCustHeight('hideDivImgId', 'workcentersSearchPnl', 'workcentersIntgSearchResultPnl')"></t:panelGroup>
</t:panelGroup>
<t:panelGroup forceId="true" id="workcentersSearchPnl">
    <t:panelGroup forceId="true" id="workcentersSearchCriteriaPnl">
        <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="1" cellspacing="0">
            <%-- show all ministries = false--%>
            <%-- show all ministries = true--%>
            <t:outputLabel value="#{orgIntgResources.category_label}" styleClass="lable01"/>
            <t:panelGroup colspan="3" rendered="#{!wcIntegrationBeanName.showAllMinistries}">
                <t:inputText styleClass="textboxsmall" forceId="true" id="catCodeID"
                             value="#{wcIntegrationBeanName.selectedCatCode}" disabled="true"/>
                <t:inputText forceId="true" id="catNameID" value="#{wcIntegrationBeanName.catName}"
                             styleClass="textboxsmall" disabled="true" style="width:261px;margin-right:2px;"/>
            </t:panelGroup>
            <t:panelGroup id="cat_pnl" rendered="#{wcIntegrationBeanName.showAllMinistries}" colspan="3">
                <t:inputText forceId="true" id="categoriesFilterationIdInt" styleClass="textboxsmall"
                             onkeypress="filterationInputOnKeyPress(event,this,'categoriesListInt','errorCodeId1Int',changeCategoryValInt,'ministriesFilterationIdInt');"
                             onblur="filterationInputOnBlur(event,this,'categoriesListInt','errorCodeId1Int',changeCategoryValInt,'ministriesFilterationIdInt');"
                             value="#{wcIntegrationBeanName.selectedCatCode}" onkeyup="enabelIntegerOnly(this);">
                    <a4j:jsFunction name="changeCategoryValInt"
                                    actionListener="#{wcIntegrationBeanName.obtainSelCategory}"
                                    oncomplete="clearInputText('ministriesFilterationIdInt');setFocusAndSelect('ministriesFilterationIdInt');"
                                    reRender="classificationId,bgtProgId,categoriesListInt,MinistryListMenuInt,OperationBar,workcentersIntgSearchResultPnl,wcIntgPaging_panel_pnl,listSizeInt,errorCodePanelId2Int"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu forceId="true" id="categoriesListInt" value="#{wcIntegrationBeanName.selectedCatCode}"
                                 styleClass="DropdownboxLarge" style="width:261px;"
                                 onchange="filterationDropboxOnChange(event,this,'categoriesFilterationIdInt','errorCodeId1Int',changeCategoryValIntD,'ministriesFilterationIdInt');">
                    <f:selectItem itemLabel="#{orgIntgResources.selectOneCategory}" itemValue=""/>
                    <t:selectItems value="#{wcIntegrationBeanName.org_categories_list}" var="categories"
                                   itemValue="#{categories.code.key}" itemLabel="#{categories.name}"/>
                    <a4j:jsFunction name="changeCategoryValIntD"
                                    actionListener="#{wcIntegrationBeanName.obtainSelCategory}"
                                    oncomplete="clearInputText('ministriesFilterationIdInt');setFocusAndSelect('ministriesFilterationIdInt');"
                                    reRender="classificationId,bgtProgId,MinistryListMenuInt,OperationBar,workcentersIntgSearchResultPnl,wcIntgPaging_panel_pnl,listSizeInt,errorCodePanelId1Int,errorCodePanelId2Int"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1" forceId="true" id="errorCodePanelId1Int">
                    <t:outputLabel id="errorCodeId1Int" value="#{orgIntgResources.MessageForWrongCode}" forceId="true"
                                   styleClass="validation_error" style="display:none"/>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputLabel value="#{orgIntgResources.ministriesLabel}" styleClass="lable01"/>
            <t:panelGroup colspan="3" rendered="#{!wcIntegrationBeanName.showAllMinistries}">
                <t:inputText styleClass="textboxsmall" forceId="true" id="minCodeID"
                             value="#{wcIntegrationBeanName.selectedMinCode}" disabled="true"/>
                <t:inputText forceId="true" id="minNameID" value="#{wcIntegrationBeanName.minName}"
                             styleClass="textboxsmall" style="width:261px;margin-right:2px;" disabled="true"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1" forceId="true" id="errorCodePanelId2IntMin" style="color:red"></t:panelGroup>
            </t:panelGroup>
            <t:panelGroup rendered="#{wcIntegrationBeanName.showAllMinistries}" colspan="3">
                <t:inputText forceId="true" id="ministriesFilterationIdInt" styleClass="textboxsmall"
                             onkeypress="filterationInputOnKeyPress(event,this,'MinistryListMenuInt','errorCodeId2Int',changeMinistryValInt);"
                             onblur="filterationInputOnBlur(event,this,'MinistryListMenuInt','errorCodeId2Int',changeMinistryValInt);"
                             value="#{wcIntegrationBeanName.selectedMinCode}" onkeyup="enabelIntegerOnly(this);">
                    <a4j:jsFunction name="changeMinistryValInt"
                                    actionListener="#{wcIntegrationBeanName.obtainSelMinistry}"
                                    reRender="classificationId,bgtProgId,MinistryListMenuInt,workcentersIntgSearchResultPnl,OperationBar,wcIntgPaging_panel_pnl,listSize"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu id="MinistryListMenuInt" forceId="true"
                                 value="#{wcIntegrationBeanName.selectedMinCode}" styleClass="DropdownboxLarge"
                                 style="width:261px;"
                                 onchange="filterationDropboxOnChange(event,this,'ministriesFilterationIdInt','errorCodeId2Int',changeMinistryValIntD);">
                    <f:selectItem itemLabel="#{orgIntgResources.selectMin}" itemValue=""/>
                    <t:selectItems value="#{wcIntegrationBeanName.org_ministries_list}" var="ministries"
                                   itemValue="#{ministries.code.key}" itemLabel="#{ministries.name}"/>
                    <a4j:jsFunction name="changeMinistryValIntD"
                                    actionListener="#{wcIntegrationBeanName.obtainSelMinistry}"
                                    reRender="classificationId,bgtProgId,OperationBar,workcentersIntgSearchResultPnl,wcIntgPaging_panel_pnl,listSizeInt,errorCodePanelId1Int,errorCodePanelId2Int"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1" forceId="true" id="errorCodePanelId2Int" style="color:red">
                    <t:outputLabel id="errorCodeId2Int" value="#{orgIntgResources.MessageForWrongCode}" forceId="true"
                                   styleClass="validation_error" style="display:none"/>
                </t:panelGroup>
            </t:panelGroup>
            <%-- ---------end r2------------%>
            <h:outputText styleClass="lable01" value="#{orgIntgResources.unitLevel}"/>
            <t:panelGroup colspan="3">
                <t:selectOneMenu forceId="true" id="workLevelId" value="#{wcIntegrationBeanName.selectedWorkLevel}"
                                 disabled="#{wcIntegrationBeanName.showWrkcenterLevels}" styleClass="DropdownboxLarge">
                    <f:selectItem itemLabel="#{orgIntgResources.all}" itemValue=""/>
                    <t:selectItems value="#{wcIntegrationBeanName.workLevelList}" var="work"
                                   itemValue="#{work.code.key}" itemLabel="#{work.name}"/>
                </t:selectOneMenu>
            </t:panelGroup>
            <%-- ---------end r3------------%>
            <h:outputText styleClass="lable01" value="#{orgIntgResources.BGTPrograms}"/>
            <t:panelGroup colspan="3">
                <t:selectOneMenu forceId="true" id="bgtProgId" value="#{wcIntegrationBeanName.selectedBgt}"
                                 disabled="#{wcIntegrationBeanName.showWrkcenterBgt}" styleClass="DropdownboxLarge">
                    <f:selectItem itemLabel="#{orgIntgResources.all}" itemValue=""/>
                    <t:selectItems value="#{wcIntegrationBeanName.bgtProgList}" var="bgt" itemValue="#{bgt.code.key}"
                                   itemLabel="#{bgt.name}"/>
                </t:selectOneMenu>
            </t:panelGroup>
            <%-- ---------end r4------------%>
            <h:outputText styleClass="lable01" value="#{orgIntgResources.classificationType}"/>
            <t:panelGroup colspan="3">
                <t:selectOneMenu forceId="true" id="classificationId" value="#{wcIntegrationBeanName.selectedClass}"
                                 styleClass="DropdownboxLarge">
                    <f:selectItem itemLabel="#{orgIntgResources.all}" itemValue=""/>
                    <t:selectItems value="#{wcIntegrationBeanName.classificationList}" var="classific"
                                   itemValue="#{classific.code.key}" itemLabel="#{classific.name}"/>
                </t:selectOneMenu>
            </t:panelGroup>
            <%-- ---------end r1------------%>
            <h:outputText styleClass="lable01" value="#{orgIntgResources.WorkCenterCode}"/>
            <t:panelGroup colspan="3">
                <t:inputText forceId="true" id="codeID" value="#{wcIntegrationBeanName.wrkCode}"
                             styleClass="textboxsmall" onkeyup="disableCharacters(this);"
                             onkeypress="disableCharacters(this); loadWorkCenter(event);"/>
                <h:outputText styleClass="lable01" value="#{orgIntgResources.WorkCenterName}"
                              style="margin-right:2px;"/>
                <t:inputText forceId="true" id="nameID" value="#{wcIntegrationBeanName.wrkName}"
                             onkeypress="clickSearch('searchBtnId');" styleClass="DropdownboxLarge"
                             style="width:228px;margin-right:2px;"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
<t:panelGrid columns="2" align="center" border="0" forceId="true" id="workcentersIntgSearchBtn">
    <t:commandButton forceId="true" id="searchBtnId" value="" onclick="return validateMinistryAndCollapse();"
                     rendered="#{!wcIntegrationBeanName.intgSearchMode}" styleClass="ManyToManySearchButton"
                     type="button">
        <a4j:jsFunction name="backJsFunction" action="#{wcIntegrationBeanName.search}" oncomplete="collapse();"
                        reRender="workcentersIntgSearchBtn,workcentersSearchPnl,workcentersIntgSearchResultPnl,workcentersIntgNavPtnID,wcIntgPaging_panel_pnl,wcIntgPanelGrd_scroller"/>
    </t:commandButton>
    <%-- <a4j:support event="onclick" action="#{wcIntegrationBeanName.search}"
         reRender="workcentersIntgSearchBtn,workcentersSearchPnl,workcentersIntgSearchResultPnl,workcentersIntgNavPtnID,paging_panel_pnl,panelGrd_scroller"
         />--%>
    <t:commandButton forceId="true" id="cancelSearchBtnId" value="" rendered="#{wcIntegrationBeanName.intgSearchMode}"
                     styleClass="ManyToManyCancelSearchButton" type="button">
        <a4j:support event="onclick" action="#{wcIntegrationBeanName.cancelSearch}"
                     reRender="workcentersIntgSearchBtn,workcentersSearchPnl,workcentersIntgSearchResultPnl,workcentersIntgNavPtnID,wcIntgPaging_panel_pnl,wcIntgPanelGrd_scroller"/>
    </t:commandButton>
    <%-- <a4j:commandButton forceId="true" id="cancelSearchBtnId" value="" styleClass="ManyToManyCancelSearchButton"
         rendered="#{wcIntegrationBeanName.intgSearchMode}" action="#{wcIntegrationBeanName.cancelSearch}"
         reRender="workcentersIntgSearchBtn,workcentersSearchPnl,workcentersIntgSearchResultPnl,workcentersIntgNavPtnID,paging_panel_pnl,panelGrd_scroller"
         />--%>
</t:panelGrid>
<t:panelGroup forceId="true" id="workcentersIntgSearchResultPnl" style="display:block;overflow:auto;height: 35px;">
    <t:dataTable id="workcentersIntgSearchTbl" var="list" value="#{wcIntegrationBeanName.availableDetails}"
                 forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" rows="#{shared_util.noOfTableRows}"
                 renderedIfEmpty="false" binding="#{wcIntegrationBeanName.availableDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('workcentersIntgSearchTbl');addRowHighlight('workcentersIntgSearchTbl');"
                 styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                 width="100%" align="top" dir="#{shared_util.pageDirection}">
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="wcIntgChkAll"
                                         value="#{wcIntegrationBeanName.checkAllFlagAvailable}"
                                         rendered="#{!wcIntegrationBeanName.singleSelectionFlag}">
                    <a4j:support event="onclick"
                                 actionListener="#{wcIntegrationBeanName.selectedCheckboxsAllForIntgDiv}"
                                 oncomplete="setAllWcIntg('wcIntgChkAll', 'wcIntgChk');"
                                 reRender="OperationBar,workcentersIntgNavPtnID"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="wcIntgChk" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{wcIntegrationBeanName.selectedCheckboxsForIntgDiv}"
                             oncomplete="checkCheckAll('wcIntgChk');"
                             reRender="workcentersIntgNavPtnID,workcentersIntgSearchTbl"></a4j:support>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="code_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_code" value="#{globalResources.Code}"/>
            </f:facet>
            <h:outputText id="content_code" value="#{list.code.wrkCode}"/>
        </t:column>
        <t:column id="name_column" sortable="false" width="75%">
            <f:facet name="header">
                <h:outputText id="header_name" value="#{globalResources.name}"/>
            </f:facet>
            <h:outputText id="content_name" value="#{list.name}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{ wcIntegrationBeanName.availableListSize == 0 }" align="center">
        <t:outputText value="#{orgIntgResources.noWorkCentersFound}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGroup forceId="true" id="wcIntgPaging_panel_pnl">
    <t:panelGroup forceId="true" id="wcIntgPaging_panel" rendered="#{wcIntegrationBeanName.availableListSize >0}">
        <t:outputText dir="ltr" forceId="true" id="listSizeInt" style="width:100%" styleClass="noOfRecords"
                      value="#{globalResources.noOfRecords} : #{wcIntegrationBeanName.availableListSize}"
                      rendered="#{wcIntegrationBeanName.availableListSize >0}"/>
        <h:panelGrid id="wcIntgPanelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller"
                     width="300px" rendered="#{wcIntegrationBeanName.availableListSize > shared_util.noOfTableRows}">
            <t:dataScroller id="wcIntgScroll_paging" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                            paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
                            fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage"
                            lastStyleClass="textpage" nextStyleClass="textpage" previousStyleClass="textpage"
                            paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                            paginatorActiveColumnStyle="font-weight:bold" styleClass="textpage" immediate="false"
                            for="workcentersIntgSearchTbl" renderFacetsIfSinglePage="false"
                            actionListener="#{wcIntegrationBeanName.scrollerAction}">
                <f:facet name="first">
                    <h:panelGroup id="workcenterss_list_panelGrp_first_paging">
                        <t:graphicImage id="workcenterss_list_img_firstOn_paging" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="workcenterss_list_img_firstOff_paging" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <h:panelGroup id="workcenterss_list_panelGrp_last_paging">
                        <t:graphicImage id="workcenterss_list_img_lastOn_paging" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="workcenterss_list_img_lastOff_paging" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <h:panelGroup id="workcenterss_list_panelGrp_prv_paging">
                        <t:graphicImage id="workcenterss_list_img_prvOn_paging" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="workcenterss_list_img_prvOff_paging" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="workcenterss_list_panelGrp_nxt_paging">
                        <t:graphicImage id="workcenterss_list_img_nxtOn_paging" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="workcenterss_list_img_nxtOff_paging" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="workcenterss_list_panelGrp_ffrwrd_paging">
                        <t:graphicImage id="workcenterss_list_img_ffrwrdOn_paging"
                                        rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="workcenterss_list_img_ffrwrdOff_paging" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="workcenterss_list_panelGrp_frwnd_paging">
                        <t:graphicImage id="workcenterss_list_img_frwndOn_paging" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="workcenterss_list_img_frwndOff_paging" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                        <t:inputHidden forceId="true" id="wcIntgPageIndex" value="#{pageIndex}"
                                       valueChangeListener="#{shared_util.changePageIndex}"/>
                        <t:inputHidden forceId="true" id="wcIntgNoOfTableRows" value="#{shared_util.noOfTableRows}"/>
                    </h:panelGroup>
                </f:facet>
                <%-- <a4j:support oncomplete="checkCheckAll_Gen2();" ></a4j:support>--%>
            </t:dataScroller>
        </h:panelGrid>
        <%-- <t:inputHidden forceId="true" id="noOfTableRows" value="#{shared_util.noOfTableRows}"/>--%>
        <%-- <t:inputHidden forceId="true" id="arrayId" value=""/>--%>
    </t:panelGroup>
</t:panelGroup>
<t:panelGroup id="workcentersIntgNavPtnID" forceId="true">
    <t:commandButton id="int_div_Submit_ok" value="#{globalResources.ok}" action="#{wcIntegrationBeanName.closeIntDiv}"
                     rendered="#{wcIntegrationBeanName.useSubmit}"
                     onclick="hideLookUpDiv(window.blocker,#{wcIntegrationBeanName.divId});changeVisibilityMsg(); "
                     styleClass="cssButtonSmall"
                     disabled="#{empty wcIntegrationBeanName.selectedDTOSList || (wcIntegrationBeanName.singleSelectionFlag == 'true' && wcIntegrationBeanName.selectedDTOSListsize>1)}"></t:commandButton>
    <a4j:commandButton id="int_div_ok" value="#{globalResources.ok}" action="#{wcIntegrationBeanName.closeIntDiv}"
                       rendered="#{!wcIntegrationBeanName.useSubmit}"
                       oncomplete="hideLookUpDiv(window.blocker,#{wcIntegrationBeanName.divId});changeVisibilityMsg(); "
                       styleClass="cssButtonSmall" reRender="#{wcIntegrationBeanName.okCommandButton.reRender}"
                       binding="#{wcIntegrationBeanName.okCommandButton}"
                       disabled="#{empty wcIntegrationBeanName.selectedDTOSList || (wcIntegrationBeanName.singleSelectionFlag == 'true' && wcIntegrationBeanName.selectedDTOSListsize>1)}"></a4j:commandButton>
    <t:panelGroup>
        <t:commandButton forceId="true" id="backButtonIntegrationDiv1" type="button" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"/>
        <a4j:support action="#{wcIntegrationBeanName.hideIntDiv}" event="onclick" reRender="integrationDiv1"
                     oncomplete="hideLookUpDiv(window.blocker,#{wcIntegrationBeanName.divId})"></a4j:support>
    </t:panelGroup>
</t:panelGroup>
<script type="text/javascript">
  function clickSearch(buttonId) {
      keypress.combo("enter", function () {
          var button = document.getElementById(buttonId);
          button.click();
          return false;
      });
  }

  function setAllWcIntg(checkAllId, arrayId) {
      var object;
      var pageIndex;
      var noOfTableRows;
      if (document.getElementById(checkAllId) != null)
          object = document.getElementById(checkAllId);

      if (document.getElementById(arrayId) != null)
          document.getElementById(arrayId).value = arrayId;

      if (document.getElementById('wcIntgNoOfTableRows') != null)
          noOfTableRows = document.getElementById('wcIntgNoOfTableRows').value;

      if (document.getElementById('wcIntgPageIndex') != null)
          pageIndex = document.getElementById('wcIntgPageIndex').value;

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

  wcIntgCheckCheckAll();

  function wcIntgCheckCheckAll() {
      var arrayId = 'wcIntgChk';
      var pageIndex;
      var noOfTableRows;
      var checkAll = 'wcIntgChkAll';

      if (document.getElementById('wcIntgPageIndex') != null && document.getElementById('wcIntgPageIndex').value != '')
          pageIndex = document.getElementById('wcIntgPageIndex').value;

      if (document.getElementById('wcIntgNoOfTableRows') != null)
          noOfTableRows = document.getElementById('wcIntgNoOfTableRows').value;
      var startIndex = eval((pageIndex * noOfTableRows) - noOfTableRows);
      var endIndex = startIndex + (noOfTableRows - 1);
      // alert("startIndex>>"+ startIndex)
      // alert("endIndex>>"+ endIndex)
      var uncheckedOneFound = false;
      var chkElmId = '';
      var chkElm = null;
      for (var i = startIndex;i <= endIndex;i++) {
          chkElmId = arrayId + '[' + i + ']';
          if (document.getElementById(chkElmId) != null) {
              chkElm = document.getElementById(chkElmId);
              if (chkElm.checked == false) {
                  //      alert("foundnotchecked>>"+ i)
                  uncheckedOneFound = true;
                  break;
              }
          }
      }

      if (uncheckedOneFound) {
          if (document.getElementById(checkAll) != null)
              document.getElementById(checkAll).checked = false;
          // alert("scheck all set to false")
      }
      else {
          if (document.getElementById(checkAll) != null)
              document.getElementById(checkAll).checked = true;
          // alert("scheck all set to true")
      }
  }

  function validateMinistryAndCollapse() {
      if (validateInputText1('ministriesFilterationIdInt', 'errorCodePanelId2Int') == true && validateInputText1('minNameID', 'errorCodePanelId2IntMin') == true) {
          backJsFunction();
          return true;
      }
      else {
          return false;
      }
  }

  function validateInputText1(inputTextId1, spanErrorId1) {
      if (document.getElementById(inputTextId1) != null && document.getElementById(inputTextId1).value == '') {
          document.getElementById(spanErrorId1).innerHTML = '&nbsp;                             ' + 'برجاء ملئ الحقل';
          return false;
      }
      else {
          if (document.getElementById(spanErrorId1) != null) {
              document.getElementById(spanErrorId1).innerHTML = '';
          }
          return true;
      }
  }

  function collapse() {
      toggleDivUsingCustHeight('hideDivImgId', 'workcentersSearchPnl', 'workcentersIntgSearchResultPnl');
  }

  function showToggle() {
      var searchMode = (document.getElementById('cancelSearchBtnId') != null) ? true : false;
      if (searchMode) {
          collapse();
      }
  }

  showToggle();

  function toggleDivUsingCustHeight(togglerId, cnt1DivId, cntDivId) {
      if (document.getElementById(cnt1DivId).style.display == 'none') {
          document.getElementById(cnt1DivId).style.display = 'block';
          document.getElementById(togglerId).className = 'hideDivPnl';
          document.getElementById(cntDivId).style.height = "35";
      }
      else {
          document.getElementById(cnt1DivId).style.display = 'none';
          document.getElementById(togglerId).className = 'showDivPnl';
          document.getElementById(cntDivId).style.height = "203";
      }
  }

      function loadWorkCenter(event) {
          var unicode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
          if (unicode == 13) {
              backJsFunction();
              if (event.preventDefault) {
                  event.preventDefault();
              }
              else {
                  event.returnValue = false;
              }
          }
      }
</script>
<t:saveState value="#{wcIntegrationBeanName.useSubmit}"/>
<t:saveState value="#{wcIntegrationBeanName.ministryDTO}"/>
<t:saveState value="#{wcIntegrationBeanName.catName}"/>
<t:saveState value="#{wcIntegrationBeanName.minName}"/>
<t:saveState value="#{wcIntegrationBeanName.execludedList}"/>
<t:saveState value="#{wcIntegrationBeanName.containerBeanName}"/>
<t:saveState value="#{wcIntegrationBeanName.selectedWorkLevel}"/>
<t:saveState value="#{wcIntegrationBeanName.showWrkcenterLevels}"/>
<t:saveState value="#{wcIntegrationBeanName.showWrkcenterBgt}"/>
<t:saveState value="#{wcIntegrationBeanName.divId}"/>
<t:saveState value="#{wcIntegrationBeanName.showAllMinistries}"/>
<t:saveState value="#{wcIntegrationBeanName.readOnlyMinistryAndCategory}"/>
<t:saveState value="#{wcIntegrationBeanName.readMinistryFromSecurityInfo}"/>
<t:saveState value="#{wcIntegrationBeanName.showAllWorkCentersCenterStatus}"/>
<t:saveState value="#{wcIntegrationBeanName.showAllWorkCentersStatus}"/>
<t:saveState value="#{wcIntegrationBeanName.selectedDTOSList}"/>
<t:saveState value="#{wcIntegrationBeanName.availableDetails}"/>
<t:saveState value="#{wcIntegrationBeanName.intgSearchMode}"/>
<t:saveState value="#{wcIntegrationBeanName.returnMethodName}"/>
<t:saveState value="#{wcIntegrationBeanName.preOpenMethodName}"/>
<t:saveState value="#{wcIntegrationBeanName.resetIntgDivAfterClose}"/>
<t:saveState value="#{wcIntegrationBeanName.resetIntgDivAfterHide}"/>
<t:saveState value="#{wcIntegrationBeanName.org_categories_list}"/>
<t:saveState value="#{wcIntegrationBeanName.selectedCatCode}"/>
<t:saveState value="#{wcIntegrationBeanName.selectedMinCode}"/>
<t:saveState value="#{wcIntegrationBeanName.org_ministries_list}"/>
<t:saveState value="#{wcIntegrationBeanName.singleSelectionFlag}"/>
