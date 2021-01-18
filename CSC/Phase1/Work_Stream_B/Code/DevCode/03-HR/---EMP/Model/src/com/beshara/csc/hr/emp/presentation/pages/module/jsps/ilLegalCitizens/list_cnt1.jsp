<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>



<t:panelGrid columns="2" rowClasses="row01" width="100%" columnClasses="colStaticSize1" cellpadding="3" cellspacing="0">
    <h:outputLabel value="#{resourcesBundle.orderType}" styleClass="lable01"/>
    <t:panelGroup colspan="1">
          <t:selectOneMenu id="Inf_request_type" forceId="true" value="#{pageBeanName.requestStatust}"
                             styleClass="Dropdownboxmedium" >
                <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="0"/>          
                 <f:selectItem itemLabel="#{resourcesBundle.under_studding}" itemValue="1"/>
                 <f:selectItem itemLabel="#{resourcesBundle.approve}" itemValue="2"/>
                 <f:selectItem itemLabel="#{resourcesBundle.refuse}" itemValue="3"/>
               
             <a4j:support event="onchange" actionListener="#{pageBeanName.getSelectedSataus}" reRender="dataT_data,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"  />
            </t:selectOneMenu>
    </t:panelGroup>
</t:panelGrid>
<t:saveState value="#{pageBeanName.requestStatust}"/>

