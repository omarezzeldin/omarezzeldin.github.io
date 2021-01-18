<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>

<t:div styleClass="#{pageBeanName.divTreeDetails}" forceId="true" id="divTreeDetails">
             
           
    <t:saveState value="#{pageBeanName.dtoDetails}"/>
    <%--<t:panelGroup forceId="true" id="grsIntgHiddenValues">
        <t:inputHidden forceId="true" id="grsIntgFullURLId" value="#{hireTypesTreeListBean.jcHelper.fullURL}"/>
        <t:outputText forceId="true" id="grsIntgWindowTitleId" value="#{grsIntgResources.conditionDetails}" style="display:none;"/>
    </t:panelGroup>--%>
    <t:div styleclass="divDetailsHead">
        <t:commandButton id="divTreeDetailsclose" forceId="true"
                         onclick="hideLookUpDiv(window.blocker,window.divTreeDetails,'','','');cancelEditFunction();return false;"
                         styleClass="close"/>
    </t:div>
    <t:div styleclass="popup_body">
        <t:panelGroup id="treeDetailsDiv" forceId="true" style="width:400px;display:block">
            <t:panelGrid columns="2" width="100%" id="treeViewAndEditDiv" forceId="true" columnClasses="col1,col2"
                         cellpadding="2" cellspacing="0" rowClasses="row01,row02">
                <t:outputText value="#{resourcesBundle[code]}" styleClass="lable01"/>
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.code.key}" styleClass="textboxsmall"/>
                <t:outputText value="#{resourcesBundle[name]}" styleClass="lable01"/>
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.name}" styleClass="textboxmedium"/>
                <%--  <t:outputText value="#{resourcesBundle[leaf]}" styleClass="lable01"/>
                <t:panelGroup>
                    <t:selectBooleanCheckbox value="#{pageBeanName.dtoDetails.booleanLeafFlag}" disabled="true"
                                             forceId="true" id="LeafID"/>
                    <t:div style="visibility:hidden;height:1px;">
                        <t:inputText style="height:1px;" value="#{pageBeanName.dtoDetails.childernNumbers}" id="hasCHILD"
                                     forceId="true" disabled="true"/>
                    </t:div>
                </t:panelGroup> --%>
            </t:panelGrid>
            
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton  forceId="true" 
                action="#{pageBeanName.goEdit}" 
                id="SaveButtonEdit" 
                styleClass="cssButtonSmall" 
                value="#{resourcesBundle.editButton}" />
        </t:panelGrid>
            
        </t:panelGroup>
        
    </t:div>
</t:div>


    


<%--<f:verbatim>

<script type="text/javascript">
      function openGrsIntgConditionDetailsWindow() {
          openGrsIntgNewWindow('grsIntgFullURLId' , 'grsIntgWindowTitleId');
      }
    function openGrsIntgNewWindow(fullURL, winNameInput) {
        var url = document.getElementById(fullURL).value;
        var windowTitle = document.getElementById(winNameInput).innerHTML;
        var wOpen;
        var sOptions;
        var popupWindowWidth = 632;
        var popupWindowheight = 540;
    
        sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no';
        sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
        sOptions = sOptions + ',height=' + (popupWindowheight).toString();
        sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';
    
        globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";
    
        wOpen = window.open("", '', sOptions);
        wOpen.document.write(globalHTML);
        wOpen.focus();
        wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);
    
        return wOpen;
    }
</script>
</f:verbatim>--%>
<%--<f:verbatim>

<script type="text/javascript">
function changeVisibilityMsg()
{
   if(document.getElementById('successMsg') != null && document.getElementById('successMsg').value != '')
   {
      changeVisibilityMsgDiv(window.divMsg);
      settingFoucsAtDivMsg();
       i = 0;
   }
   if(document.getElementById('ErrMsg') != null && document.getElementById('ErrMsg').value != '')
   {
       changeVisibilityDiv(window.blocker,window.divMsg);
       settingFoucsAtDivMsg();
       i = 0;
   }
   enableMsgScript();
}
</script>
</f:verbatim>--%>
