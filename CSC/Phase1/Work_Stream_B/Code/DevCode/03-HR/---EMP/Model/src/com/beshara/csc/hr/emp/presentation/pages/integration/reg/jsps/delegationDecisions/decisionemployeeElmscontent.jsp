<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%> 
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.dataT-With-6-row-filter-and-collapse {
    height: 170px !important;
}

.dataT-With-5-row-filter{
 height: 210px !important;
}
</htm:style>

<t:panelGroup forceId="true" id="all_dataT_data_panel" >

 <t:panelGroup forceId="true"  id="salVariedElmGuidesGroupId" styleClass="#{(delegationDecisionMaintainBean.showOnly ? 'dataT-With-2-row-filter'  : 'dataT-With-3-row-filter')}">
     <t:dataTable  forceId="true" id="salVariedElmGuidesDataTable" var="list" rowIndexVar="index"
                     value="#{pageBeanName.currentSalVariedElmGuideDTOList}" binding="#{pageBeanName.salVariedElmGuidesDataTable}"
                     forceIdIndexFormula="#{list.code.key}" footerClass="grid-footer"
                     headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" styleClass="grid-footer" renderedIfEmpty="false">
         
         <%--<t:column id="check_column" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}" disabled="#{delegationDecisionMaintainBean.showOnly}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCurrentAllElements}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="salVariedElmGuidesGroupId,divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" disabled="#{delegationDecisionMaintainBean.showOnly}">                                            
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCurrentElement}" oncomplete="checkCheckAll('chk2');" reRender="salVariedElmGuidesGroupId,divDeleteAlert,OperationBar" />
            </t:selectBooleanCheckbox>
       </t:column>--%>
        
        <t:column id="check_column" styleClass="standardTable_Header3"  width="5%" >
            <f:facet name="header"/>
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" 
                onmousedown="toggleRadio(this);" onkeypress="toggleRadio(this);"  disabled="#{delegationDecisionMaintainBean.showOnly}">
               <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
               <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="OperationBar,salVariedElmGuidesGroupId,divDeleteAlert"/>
            </t:selectOneRadio>
        </t:column>
        
        <t:column id="index_column" width="5%">
            <f:facet name="header">
             <t:outputText id="sort_index" value="#{regResources.index}"/>
            </f:facet>
            <t:outputText id="content_index" value="#{index+1}"/>
        </t:column>
        
          <t:column id="elmguide_column" width="15%">
            <f:facet name="header">
             <t:outputText id="sort_elmguideCode" value="#{regResources.regulation_subjects_symbol}"/>
            </f:facet>
            <t:outputText id="content_elmguideCode" value="#{list.code.elmguideCode}"/>
        </t:column> 
         <t:column id="name_column" width="45%">
            <f:facet name="header">
             <t:outputText id="sort_name" value="#{regResources.section}"/>
            </f:facet>
            <t:outputText id="content_name" value="#{list.name}"/>
        </t:column> 

        <t:column id="deductionSign_column" sortable="false" width="10%" rendered="#{(pageBeanName.decType == 2)}" >
            <f:facet name="header">
                <t:outputText id="deductionSignId" forceId="true" styleClass="headerLink" value="#{regResources.sign}"/>
            </f:facet>
            <t:selectOneMenu forceId="true" id="deductionSignCombo" styleClass="textbox" style="width:40px;"
                             value="#{list.signal}" onchange="adjustTotalValueFn();"
                             disabled="#{delegationDecisionMaintainBean.showOnly}">
                <f:selectItem itemLabel="+" itemValue="+"/>
                <f:selectItem itemLabel="-" itemValue="-"/>
            </t:selectOneMenu>
        </t:column>
                
        <t:column id="value2_column" >
            <f:facet name="header">
              <t:panelGroup>
                <t:outputText id="sort_valueInDetail2" value="#{regResources.financialEmpValueNum}  #{regResources.fels_Denar}"/>
                </t:panelGroup>
            </f:facet>
            <t:inputText forceId="true" id="fels_number2"  value="#{list.felsValue}" tabindex="#{(index*2)+7}" onkeypress="FireNextDinar2(event,#{index});" onchange="adjustTotalValueFn();" onkeyup="disableCharacters(this);" maxlength="3"  styleClass="textbox" style="width:35px;"    disabled="#{delegationDecisionMaintainBean.showOnly}"  />
            <f:verbatim>&nbsp;</f:verbatim>
            <t:inputText forceId="true" id="denar_number2" value="#{list.denarValue}" tabindex="#{(index*2)+6}" onkeypress="fireCurrentFels2(event,#{index});" onchange="adjustTotalValueFn();" onkeyup="disableCharacters(this);" maxlength="8"  styleClass="filteration_input_text"   style=" width: 50px;  margin-right: 4px; margin-left: 1px;"  disabled="#{delegationDecisionMaintainBean.showOnly}"  />
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!delegationDecisionMaintainBean.showOnly}"/>
            <a4j:jsFunction name="adjustTotalValueFn"
                            action="#{pageBeanName.getTotal_ValueForOneEmpMultiElements}"
                            reRender="total_value,divDeleteAlert,OperationBar" />
              <f:verbatim><br/></f:verbatim>
          <t:outputText id="errorValueMessage" forceId="true" styleClass="errMsg" value="#{list.otherDesc}" />
       </t:column>
    </t:dataTable>
 </t:panelGroup>
 </t:panelGroup>
 
 <t:panelGrid forceId="true" id="buttonsPnlGridId"  columns="3" align="center" width="200px" cellpadding="3" cellspacing="0" border="0">
        <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" disabled="#{(pageBeanName.maintainMode == 2) || (pageBeanName.numberOfRecordsDisplayed <= 0)}"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.saveElmsToDec}"/>
        <t:commandButton forceId="true" id="backButtonAddDiv" action="#{pageBeanName.back}"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    </t:panelGrid>
    
<script type="text/javascript">
var denar_id = 'denar_number';
  var fels_id = 'fels_number';
  
var denar_id2 = 'denar_number2';
  var fels_id2 = 'fels_number2';
  function FireNextDinar(e, index) {
      var code1 = (e.keyCode ? e.keyCode : e.which);
      //alert("fireDinar" + index);  
      if (code1 == 13) {
          index = index + 1;
          if (document.getElementById(denar_id + '[' + index + ']') != null) {
              document.getElementById(denar_id + '[' + index + ']').focus();
              //document.getElementById(denar_id + '[' + index + ']').select();
              //document.getElementById(fels_id + '[' + index-1 + ']').unselect();
          }
          return false;

      }
      
  }

  function fireCurrentFels(e, index) {
      var code = (e.keyCode ? e.keyCode : e.which);
      //alert("fireFels" + index);
      if (code == 13) {
          if (document.getElementById(fels_id + '[' + index + ']') != null) {
              document.getElementById(fels_id + '[' + index + ']').focus();
              //document.getElementById(fels_id + '[' + index + ']').select();
              //document.getElementById(denar_id + '[' + index + ']').unselect();
          }
          return false;
      }
   
  }
 function calcualteFelsAndDenar(){
       alert("content calcualteFelsAndDenar");
       var rowsCount = '${pageBeanName.currentListSize}';
        var total =0;
        var val = 0;
        var v1=0;
        var v2= 0;
        for (var i=0;i<rowsCount ;i++){
            // updated by A,Abdel Halim to handle Empty Values
            var vDenarValue = document.getElementById('denar_number['+i+']').value;
            var vFelsValue = document.getElementById('fels_number['+i+']').value;
            
            if(isBlank(vDenarValue)){
                vDenarValue = "0";
            }
            
            if(isBlank(vFelsValue)){
                vFelsValue = "0";
            }
            
            val=  parseFloat(vDenarValue+"."+vFelsValue);
            total +=val;
        }  
        document.getElementById('total_value').value =  toFixed(total,3);
        
     //setFocusAtMyFirstElement();
  }
  
  function isBlank(str) {
            return (!str || /^\s*$/.test(str));
        }
  function toFixed(value, precision) {
    var power = Math.pow(10, precision || 0);
    return String(Math.round(value * power) / power);
}

 function FireNextDinar2(e, index) {
      var code1 = (e.keyCode ? e.keyCode : e.which);
      //alert("fireDinar" + index);  
      if (code1 == 13) {
          index = index + 1;
          if (document.getElementById(denar_id2 + '[' + index + ']') != null) {
              document.getElementById(denar_id2 + '[' + index + ']').focus();
          }
          return false;

      }
      
  }

  function fireCurrentFels2(e, index) {
      var code = (e.keyCode ? e.keyCode : e.which);
      //alert("fireFels" + index);
      if (code == 13) {
          if (document.getElementById(fels_id2 + '[' + index + ']') != null) {
              document.getElementById(fels_id2 + '[' + index + ']').focus();
        }
          return false;
      }
   
  }
 function calcualteFelsAndDenar2(){
       alert("content calcualteFelsAndDenar2");
       var rowsCount = '${pageBeanName.currentListSize}';
        var total =0;
        var val = 0;
        var v1=0;
        var v2= 0;
        for (var i=0;i<rowsCount ;i++){
            // updated by A,Abdel Halim to handle Empty Values
            var vDenarValue = document.getElementById('denar_number2['+i+']').value;
            var vFelsValue = document.getElementById('fels_number2['+i+']').value;
        
            if(isBlank(vDenarValue)){
                vDenarValue = "0";
            }
            
            if(isBlank(vFelsValue)){
                vFelsValue = "0";
            }
            
            val=  parseFloat(vDenarValue+"."+vFelsValue);
            total +=val;
        }        
        document.getElementById('total_value').value =  toFixed(total,3);
        
     //setFocusAtMyFirstElement();
  }
  
  //calcualteFelsAndDenar();
  //calcualteFelsAndDenar2();
</script>
