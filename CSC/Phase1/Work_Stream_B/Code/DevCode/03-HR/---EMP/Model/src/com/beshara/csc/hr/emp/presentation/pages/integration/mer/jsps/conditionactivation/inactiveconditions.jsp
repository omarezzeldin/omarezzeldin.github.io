<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%-- ##################START################# Strat of Join Condition Section #################SHEKA###############--%>

<t:panelGroup forceId="true" id="merIntgInactiveConditionsPnl">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="merIntgInactiveConditionsTblPnl" styleClass="#{conditionActivationHelperBean.conditionsTableStyle}">
            <t:dataTable id="merIntgSearchResultTbl" var="list" value="#{conditionActivationHelperBean.myTableData}"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                         renderedIfEmpty="false" binding="#{conditionActivationHelperBean.myDataTable}"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:merIntgSearchResultTbl');"
                         styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <%-- <t:column id="check_column" rendered="#{conditionActivationHelperBean.singleCondition}"
                     styleClass="standardTable_Header2 noText" width="5%"> <h:selectOneRadio
                     styleClass="radioButton_DataTable" onclick="toggleRadio(this);
                     grsIntgUpdateSelectedCondition('#{list.code.key}');"> <f:selectItem itemLabel=" "
                     itemValue="#{list.code.key}"/> </h:selectOneRadio> </t:column>--%>
                <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{conditionActivationHelperBean.singleSelection}">
                    <f:facet name="header"/>
                    <t:selectOneRadio styleClass="radioButton_DataTable" id="chk"
                                      value="#{conditionActivationHelperBean.selectedRadio}"
                                      onmousedown="toggleRadio(this);"
                                      onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                        <a4j:support event="onclick"
                                     actionListener="#{conditionActivationHelperBean.selectedRadioButton}"
                                     reRender="merIntgNavSection"/>
                    </t:selectOneRadio>
                </t:column>
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{!conditionActivationHelperBean.singleSelection}">
                    <f:facet name="header">
                        <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{conditionActivationHelperBean.checkAllFlag}">
                            <f:attribute name="checkAll" value="true"/>
                            <f:attribute name="listSize" value="#{conditionActivationHelperBean.listSize}"/>
                            <a4j:support event="onclick" actionListener="#{conditionActivationHelperBean.selectedCheckboxsAll}"
                                         oncomplete="setAll('checkAll', 'chk');"
                                         reRender="merIntgNavSection"/>
                        </t:selectBooleanCheckbox>
                    </f:facet>
                    <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                        <a4j:support event="onclick" actionListener="#{conditionActivationHelperBean.selectedCheckboxs}"
                                     oncomplete="checkCheckAll('chk');"
                                     reRender="merIntgNavSection"/>
                    </t:selectBooleanCheckbox>
                </t:column>
                <t:column id="code_column" sortable="false" width="15%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{merIntgResources.divInfoConditionCode}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.conditionsDTO.code.key}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="65%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{merIntgResources.divInfoConditionName}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.conditionsDTO.name}"/>
                </t:column>
                <t:column id="condDetails" sortable="false" width="15%">
                    <f:facet name="header">
                        <h:outputText id="header_details" value="#{merIntgResources.conditionDetails}"/>
                    </f:facet>
                    <a4j:commandButton styleClass="cssButtonSmaller" id="content_details"
                                       value="#{globalResources.Available}"
                                       actionListener="#{conditionActivationHelperBean.viewConditionDetails}"
                                       reRender="merIntgHiddenValues" oncomplete="openMerIntgConditionDetailsWindow();">
                        <f:attribute name="conditionCode" value="#{list.conditionsDTO.code.key}"/>
                    </a4j:commandButton>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ conditionActivationHelperBean.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <%-- <t:panelGrid id="grsIntgSearchResultScrollPnl" columns="1" dir="#{shared_util.pageDirection}"
         styleClass="scroller" width="300px" rendered="#{conditionActivationHelperBean.listSize >
         shared_util.noOfTableRows}"> <t:dataScroller id="grsIntgSearchResultScroll"
         actionListener="#{conditionActivationHelperBean.scrollerAction}" fastStep="5" pageCountVar="pageCount"
         pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
         fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
         nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage"
         paginatorActiveColumnClass="paging" paginatorActiveColumnStyle="font-size: 10pt;text-decoration:
         none;font-weight:bold" styleClass="textpage" immediate="false" for="merIntgSearchResultTbl"
         renderFacetsIfSinglePage="false"> <f:facet name="first"> <t:panelGroup
         id="conditionDiv_req_list_panelGrp_first"> <t:graphicImage id="conditionDiv_req_list_img_firstOn"
         rendered="#{pageIndex > 1}" title="#{globalResources.scroller_first}"
         url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/> <t:graphicImage
         id="conditionDiv_req_list_img_firstOff" onclick="return false;" rendered="#{pageIndex &lt;= 1}"
         url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/> </t:panelGroup> </f:facet>
         <f:facet name="last"> <t:panelGroup id="conditionDiv_req_list_panelGrp_last"> <t:graphicImage
         id="conditionDiv_req_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
         title="#{globalResources.scroller_last}" url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
         border="0"/> <t:graphicImage id="conditionDiv_req_list_img_lastOff" onclick="return false;"
         rendered="#{pageIndex >= pageCount}" url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
         border="0"/> </t:panelGroup> </f:facet> <f:facet name="previous"> <t:panelGroup
         id="conditionDiv_req_list_panelGrp_prv"> <t:graphicImage id="conditionDiv_req_list_img_prvOn"
         rendered="#{pageIndex > 1}" title="#{globalResources.scroller_previous}"
         url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/> <t:graphicImage
         id="conditionDiv_req_list_img_prvOff" onclick="return false;" rendered="#{pageIndex &lt;= 1}"
         url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/> </t:panelGroup> </f:facet>
         <f:facet name="next"> <t:panelGroup id="conditionDiv_req_list_panelGrp_nxt"> <t:graphicImage
         id="conditionDiv_req_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
         title="#{globalResources.scroller_next}" url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
         border="0"/> <t:graphicImage id="conditionDiv_req_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
         url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/> </t:panelGroup> </f:facet>
         <f:facet name="fastforward"> <t:panelGroup id="conditionDiv_req_list_panelGrp_ffrwrd"> <t:graphicImage
         id="conditionDiv_req_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
         title="#{globalResources.scroller_fastforward}"
         url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/> <t:graphicImage
         id="conditionDiv_req_list_img_ffrwrdOff" onclick="return false;" rendered="#{pageIndex >= pageCount}"
         url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/> </t:panelGroup> </f:facet>
         <f:facet name="fastrewind"> <t:panelGroup id="conditionDiv_req_list_panelGrp_frwnd"> <t:graphicImage
         id="conditionDiv_req_list_img_frwndOn" rendered="#{pageIndex > 1}"
         title="#{globalResources.scroller_fastrewind}" url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
         border="0"/> <t:graphicImage id="conditionDiv_req_list_img_frwndOff" onclick="return false;"
         rendered="#{pageIndex &lt;= 1}" url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
         border="0"/> </t:panelGroup> </f:facet> </t:dataScroller> </t:panelGrid>--%>
</t:panelGroup>
<t:inputHidden forceId="true" id="merSelectedConditionCode"
               value="#{conditionActivationHelperBean.selectedConditionCode}"/>
<%--<f:verbatim>
    <script type="text/javascript">
      handleGrsIntgSearchBtn();

      function handleGrsIntgSearchBtn() {
          document.getElementById('grsIntgSearchCondBtn').disabled = true;
          document.getElementById('grsIntgSearchCondBtn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
          var conditionCode = document.getElementById("grsIntgSearchCondCode").value;
          var conditionName = document.getElementById("grsIntgSearchCondName").value;
          var lineValueCode = document.getElementById("grsIntgSearchLineVal").value;
          if (conditionCode.length != 0 || conditionName.length != 0 || lineValueCode != '-100') {
              document.getElementById('grsIntgSearchCondBtn').disabled = false;
              document.getElementById('grsIntgSearchCondBtn').className = 'ManyToManySearchButton';
          }
      }

      function FireGrsIntgSearchBtn() {
          handleGrsIntgSearchBtn();
          FireButtonClick('grsIntgSearchCondBtn');
      }

      function grsIntgUpdateSelectedCondition(selectedCode) {
          document.getElementById('merSelectedConditionCode').value = selectedCode;

          document.getElementById('grsIntgJoinSaveBtn').disabled = false;
          document.getElementById('grsIntgJoinSaveBtn').focus();
          document.getElementById('grsIntgJoinSaveBtn').focus();
      }

          function FireGrsIntgSaveBtn() {
              FireButtonClick('grsIntgJoinSaveBtn');
          }
    </script>
</f:verbatim>--%>