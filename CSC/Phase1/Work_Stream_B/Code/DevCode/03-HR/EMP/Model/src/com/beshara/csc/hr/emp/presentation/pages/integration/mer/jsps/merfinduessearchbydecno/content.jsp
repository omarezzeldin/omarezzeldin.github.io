<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>/*.dataT-With-5-row-filter{ height: 361px !important; }*/</htm:style>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid border="0" cellpadding="0" columns="6" cellspacing="0" width="100%">
    <t:messages showDetail="true"/>
    <t:panelGroup forceId="true" id="dataT_panel" styleClass="dataT-With-5-row-filter" colspan="6"
                  style="height: 361px !important;">
        <t:dataTable id="dataT_data" binding="#{pageBeanName.myDataTable}" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="3%"
                      rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                        <f:attribute name="checkAll" value="true"/>
                        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                     oncomplete="setAll('checkAll', 'chk');" reRender="OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                 oncomplete="checkCheckAll('chk');" reRender="OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="civilName_column" width="15%">
                <f:facet name="header">
                    <t:outputText id="sort-civilName" forceId="true" value="#{intgerationBundle.Financial_Dues_Name}"/>
                </f:facet>
                <h:outputText id="emp_name" value="#{list.civilName}"/>
            </t:column>
            <t:column id="civilId_column" width="12%">
                <f:facet name="header">
                    <t:outputText id="sort-civilId" forceId="true" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="emp_code" value="#{list.civilID}"/>
            </t:column>
            <t:column id="ministryName_column" width="10%">
                <f:facet name="header">
                    <t:outputText id="sort-ministryName" forceId="true" value="#{intgerationBundle.Financial_Dues_Min}"/>
                </f:facet>
                <h:outputText id="ministry_name" value="#{list.ministryName}"/>
            </t:column>
            <%-- <t:column id="elementGuideName_column" width="20%"> <f:facet name="header"> <t:outputText
                 id="sort-elementGuideName" forceId="true" value="#{intgerationBundle.Financial_Dues_ElemGuide}"/>
                 </f:facet> <h:outputText id="elementGuide_name" value="#{list.elementGuideName}"/> </t:column>--%>
            <%--<t:column id="bankName_column" width="10%">
                <f:facet name="header">
                    <t:outputText id="sort-bankName" forceId="true" value="#{intgerationBundle.Financial_Dues_Bank}"/>
                </f:facet>
                <h:outputText id="bank_name" value="#{list.bankName}"/>
            </t:column>--%>
            <t:column id="bankBranchName_column" width="7%">
                <f:facet name="header">
                    <t:outputText id="sort-bankBranchName" forceId="true"
                                  value="#{intgerationBundle.bank} - #{intgerationBundle.branch}"/>
                </f:facet>
                <%--<h:outputText id="bankBranch_name" value="#{list.bankBranchName}"/>--%>
                <t:graphicImage id="graph7" border="0" value="/app/media/images/infe-icon-btn.png" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.bankName} - #{list.bankBranchName}')"/>
            </t:column>
            <t:column id="accountNo_column" width="20%">
                <f:facet name="header">
                    <t:outputText id="sort-accountNo" forceId="true" value="#{intgerationBundle.Financial_Dues_AccountNo}"/>
                </f:facet>
                <h:outputText id="account_no" value="#{list.accountNo}"/>
            </t:column>
            <t:column id="totalValue_column" width="7%">
                <f:facet name="header">
                    <t:outputText id="sort-totalValue" forceId="true"
                                  value="#{intgerationBundle.needed_value_header}"/>
                </f:facet>
                <h:outputText id="total_value" value="#{list.totalValue}" converter="DoubleThreeDigitConverter"/>
            </t:column>

            <t:column id="salMonStatusName_column"  width="7%">
                <f:facet name="header">
                    <t:outputText id="sort-salMonStatusName" forceId="true" value="#{intgerationBundle.reg_calc_status}"/>
                </f:facet>
                <h:outputText id="status" value="#{list.salMonStatusName}"/>
                
                                
                <%--<t:graphicImage id="graph7" border="0" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == 3 || list.salMonStatus == 33 || list.salMonStatus == 42  }"></t:graphicImage>
                <t:graphicImage id="graph6" border="0" value="/app/media/images/grey-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == 1 || list.salMonStatus == 32   }"></t:graphicImage>
                <t:graphicImage id="graph5" border="0" value="/app/media/images/yellow-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == 9}"/>
                <t:graphicImage id="graph4" border="0" value="/app/media/images/yellow-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == 16}"/>
                <t:graphicImage id="graph3" border="0" value="/app/media/images/yellow-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == 99 || list.salMonStatus == 31 || list.salMonStatus == 41  }"></t:graphicImage>
                <t:graphicImage id="graph2" border="0" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == -2}"/>
                <t:graphicImage id="graph1" border="0" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salMonStatusName}')"
                                rendered="#{list.salMonStatus == -1}"/>--%>
            </t:column>
              <t:column id="salaryMonthe_column" sortable="false" width="7%">
                        <f:facet name="header">
                            <t:outputText styleClass="headerLink" value="#{intgerationBundle.payment_month}"/>   
                        </f:facet>
                        <h:outputText id="salaryMonth" value="#{list.salaryMonth}/#{list.yearCode}"/>
                      
              </t:column>
               <t:column id="paymentMethodName_column" sortable="false" width="11%">
                        <f:facet name="header">
                            <t:commandLink id="sort_paymentMethodName" forceId="true" styleClass="headerLink" value="#{intgerationBundle.payment_method}"
                                           actionListener="#{pageBeanName.sortDataTable}">
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='paymentMethodName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                                border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='paymentMethodName') ? '/app/media/images/descending-arrow.gif' :''}"
                                                border="0"/>
                            </t:commandLink>
                        </f:facet>
                        <h:outputText id="paymentMethodName" value="#{list.paymentMethodName}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 && pageBeanName.showMessage }" align="center">
            <t:outputText value="#{pageBeanName.userMessage}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  //togglePageUsingCstmHeight('hideQulDivImg', 'decDataPanel', 'dataT_panel',345,405,document.getElementById('filterPnlCollapsed').value);
</script>