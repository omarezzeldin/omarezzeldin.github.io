<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<%--<t:panelGroup forceId="true" colspan="6" id="hideQulDivImg" styleClass="hideDivPnl">


<t:panelGroup onclick="showHideFilterPnlFn();"></t:panelGroup>
</t:panelGroup>--%>
<%--<a4j:jsFunction action="#{pageBeanName.showHideFilterPnl}" name="showHideFilterPnlFn"
                reRender="hiddenBtnPnl,decDataPanel,dataT_panel,paging_panel"
                oncomplete="togglePageUsingCstmHeight('hideQulDivImg', 'decDataPanel', 'dataT_panel',345,405,document.getElementById('filterPnlCollapsed').value);"/>--%>
                
                
<t:panelGrid id="decDataPanel" forceId="true" align="center" columns="4" border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >

    
    <h:outputText value="#{intgerationBundle.dec_no}" styleClass="divtext"/>
    <t:panelGroup>
    <t:inputText maxlength="25" forceId="true" id="decNo" value="#{pageBeanName.decNumber}" 
        styleClass="textboxlarge" onclick="FireButtonClick('showDecData');"  />
    <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
    <c:requiredFieldValidator componentToValidate="decNo" display="dynamic" active="#{!financialDuesSearchByDecNoListBean.decisionFound}"
      errorMessage="#{globalResources.missingField}"/>
    </t:panelGroup>
    
    <h:outputText value="#{intgerationBundle.dec_auto_no}" styleClass="divtext"/>
    <t:panelGroup>
    <t:inputText maxlength="50" forceId="true" id="decAutoNo" value="#{pageBeanName.decAutoNumber}" 
        styleClass="textboxlarge" onclick="FireButtonClick('showDecData');"  />
    <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
    <c:requiredFieldValidator componentToValidate="decAutoNo" display="dynamic" active="#{!financialDuesSearchByDecNoListBean.decisionFound}"
      errorMessage="#{globalResources.missingField}"/>
    </t:panelGroup>    
    
    <%--<h:outputText value="#{intgerationBundle.dec_type}" styleClass="divtext"/>--%>
    <%--<t:panelGroup>
        <t:selectOneMenu forceId="true" id="decisionTypes"
                     styleClass="textboxmedium2" value="#{pageBeanName.decTypeCode}" >
        <f:selectItem itemLabel="#{intgerationBundle.select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.decisionTypesList}" itemLabel="#{decisionTypesList.name}"
                     itemValue="#{decisionTypesList.code.key}" var="decisionTypesList" />
        </t:selectOneMenu>
        <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <c:requiredFieldValidator componentToValidate="decisionTypes" display="dynamic"
        errorMessage="#{intgerationBundle.Financial_Dues_dec_type_missing}" />        
        
    </t:panelGroup>--%>
    
    <%--<h:outputText value="#{intgerationBundle.dec_make_year}" styleClass="divtext"/>--%>
    <%--<t:selectOneMenu forceId="true" id="decisionYear"
                     styleClass="textbo" value="#{pageBeanName.decYear}" >
        <t:selectItems value="#{pageBeanName.decYearsList}" itemLabel="#{decYearsList.code.key}"
                     itemValue="#{decYearsList.code.key}" var="decYearsList" />
        </t:selectOneMenu>--%>
    <t:panelGroup colspan="4" style="background: none repeat scroll 0 0 #ffffff; display: block; width: 100%; text-align: center;" >
    <t:commandButton type="button" forceId="true" value="#{intgerationBundle.show}" styleClass="cssButtonSmall"
      onclick="return validateSearch();" id="showDecData">
      
      <a4j:jsFunction name="searchDec" action="#{pageBeanName.GetDecData}"
      rerender="decDataPanel,decData,dataT_panel,dataT_data,paging_panel,hiddenBtnPnl,OperationBar" />
      <%--oncomplete=" togglePageUsingCstmHeight('hideQulDivImg', 'decDataPanel', 'dataT_panel',345,405,document.getElementById('filterPnlCollapsed').value);changeVisibilityMsg(); "--%>
      </t:commandButton>
    </t:panelGroup>
    
    <%--<t:panelGroup id="decData" forceId="true" rendered="#{pageBeanName.decisionFound}" colspan="6" >
      
    --%><%--<t:panelGrid  align="center" columns="6" border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >
    
    <h:outputText value="#{intgerationBundle.dec_subject}" styleClass="divtext"/>
    <t:panelGroup colspan="5">
    <t:inputText disabled="true"  styleClass="textboxlarge"  forceId="true" id="dec_details_label" value="#{pageBeanName.decisionText}"/>
    --%><%----%><%--<t:inputTextarea disabled="true" forceId="true" id="dec_details_label" value="#{pageBeanName.decisionText}"
                             styleClass="textareaXlarge" style="#{'height: 213px; width: 770px;'}"/>--%><%----%><%--
    </t:panelGroup>                             
                             
                             
    <h:outputText value="#{intgerationBundle.Financial_Dues_payment_method}" styleClass="divtext"/>                                     
    <t:selectOneMenu forceId="true" id="paymentMethod"
                     styleClass="textbox" value="#{pageBeanName.paymentMethodCode}" >
        <t:selectItems value="#{pageBeanName.paymentMethods}" itemLabel="#{paymentMethods.name}"
                     itemValue="#{paymentMethods.code.key}" var="paymentMethods" />
        </t:selectOneMenu>
        
    <h:outputText value="#{intgerationBundle.Financial_Dues_payment_month}" styleClass="divtext"/>        
    <t:selectOneMenu forceId="true" id="paymentMonth"
                     styleClass="textbox" value="#{pageBeanName.monthCode}" >
        <t:selectItems value="#{pageBeanName.monthesList}" itemLabel="#{monthesList.name}"
                     itemValue="#{monthesList.code.key}" var="monthesList" />
        </t:selectOneMenu>
    
    <h:outputText value="#{intgerationBundle.Financial_Dues_payment_year}" styleClass="divtext"/>        
    <t:selectOneMenu forceId="true" id="paymentYear"
                     styleClass="textbox" value="#{pageBeanName.yearCode}" >
        <t:selectItems value="#{pageBeanName.yearsList}" itemLabel="#{yearsList.code.key}"
                     itemValue="#{yearsList.code.key}" var="yearsList" />
        </t:selectOneMenu>   
        </t:panelGrid>--%><%--
    </t:panelGroup>--%>
    
    

</t:panelGrid>

<t:saveState value="#{financialDuesSearchByDecNoListBean.filterPnlCollapsed}"/>

 <t:panelGroup id="hiddenBtnPnl" forceId="true">
        <t:inputHidden id="filterPnlCollapsed" forceId="true" value="#{pageBeanName.filterPnlCollapsed}"/>
    </t:panelGroup>
<script type="text/javascript">
  function validateSearch() {
    if(document.getElementById('decNo') != null) {
        var decNo = document.getElementById('decNo').value;
    }
        if(document.getElementById('decAutoNo') != null) {
        var decAutoNo = document.getElementById('decAutoNo').value;
    }
    var validForm = true;
    if(decNo == '' && decAutoNo == ''){
      validForm = validatemyForm();  
    }
      
      if (validForm) {
          searchDec();
      }
      else {
          return false;
      }
      return true;
  }
</script>
