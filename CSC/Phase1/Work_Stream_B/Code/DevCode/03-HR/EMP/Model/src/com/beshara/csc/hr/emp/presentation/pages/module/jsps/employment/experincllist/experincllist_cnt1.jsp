<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


<t:panelGroup  forceId="true" style="width:100%" id="emp_query_panel_id">


<t:panelGrid columnClasses="colu1,colu2"  columns="2"   width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
<t:panelGroup>
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
   
<t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
        <t:inputText   tabindex="1" maxlength="12" forceId="true"  disabled="true" id="CivilIdAdd" styleClass="textboxlarge"   value="#{pageBeanName.civileId}" onkeypress="FireButtonClick('civil_exist_btn')"/>
      
      </t:panelGroup>
      <t:panelGroup>
     <t:outputText  value="#{resourcesBundle.candidate_name_label}" styleClass="divtext"/>
     <t:outputText value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" escape="false" />
   <t:inputText  styleClass="textboxlarge" disabled="true" value="#{pageBeanName.employeeName}"  />
  
   </t:panelGroup>
   <t:panelGroup  colspan="2" >
                   <t:panelGrid align="center">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="lable01"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                  <f:selectItem itemLabel="#{resourcesBundle.gov_min}"   itemValue="false" />                   
                  <f:selectItem itemLabel="#{resourcesBundle.speas_min}" itemValue="true"/>
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="dataT_data_panel,OperationBar, paging_panel"/>
                </t:selectOneRadio>
             </t:panelGrid>
                  
         </t:panelGroup>
         
   
  </t:panelGrid>
  </t:panelGroup>

