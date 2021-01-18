<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
            <t:panelGroup styleClass="divContent1Fixed">
                
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <htm:tr>    
                <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                <htm:td styleClass="group_title"> <t:outputLabel value="#{resourcesBundle.main_data}" styleClass="lable01"/> </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="3" valign="top"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td>
            </htm:tr>
            </htm:table>
        </t:panelGroup>

<%-- <t:outputLabel value="#{resourcesBundle.main_data}" styleClass="pannel_small_title" style="align:right;"/> 
                     --%>
                     
                    <t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="col1,col2,col1,col2" cellspacing="0" width="100%">
                        <!-- Row 1-->
                        <t:outputLabel value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.code.key}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.nationality}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.countriesDTO.name}" disabled="true" styleClass="textbox" readonly="true"/>
                        <!-- Row 2-->
                        <t:outputLabel value="#{resourcesBundle.first_name}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.firstName}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.second_name}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.secondName}" disabled="true" styleClass="textbox" readonly="true"/>
                        <!-- Row 3-->
                     <t:outputLabel value="#{resourcesBundle.third_name}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.thirdName}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.last_name}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.lastName}" disabled="true" styleClass="textbox" readonly="true"/>
                        <!-- Row 4-->
                       <t:outputLabel value="#{resourcesBundle.family_name}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.familyName}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.english_name}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.englishName}" disabled="true" styleClass="textbox" readonly="true"/>
                     </t:panelGrid>
                     
                      <f:verbatim><br/></f:verbatim>
                     <t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="col1,col2,col1,col2" cellspacing="0" width="100%">
                        <!-- Row 5-->
                        <t:outputLabel value="#{resourcesBundle.birthdate}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.birthDate}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.social_state}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.maritalStatusDTO.name}" disabled="true" styleClass="textbox" readonly="true"/>
                        <!-- Row 6-->
                        <t:outputLabel value="#{resourcesBundle.gender}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.genderTypesDTO.gentypeName}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.religion}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.religionsDTO.name}" disabled="true" styleClass="textbox" readonly="true"/>
                        <!-- Row 7-->
                        <t:outputLabel value="#{resourcesBundle.phone_number}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.phonesNo}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.mobile_number}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.mobileNo}" disabled="true" styleClass="textbox" readonly="true"/>
                       
                    </t:panelGrid>
                    
                      <f:verbatim><br/></f:verbatim>
                     <t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="col1,col2,col1,col2" cellspacing="0" width="100%">
                        <!-- Row 8-->
                       <t:outputLabel value="#{resourcesBundle.Province}" styleClass="lable01"/>
                         <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.kwMapDTO.name}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="" styleClass="lable01"/>
                        <t:outputLabel value="" styleClass="lable01"/>
                       
                        <!-- Row 9-->
                        <t:outputLabel value="#{resourcesBundle.medical_state}" styleClass="lable01"/>
                        <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.handicapStatusDTO.name}" disabled="true" styleClass="textbox" readonly="true"/>
                        <t:outputLabel value="#{resourcesBundle.special_state}" styleClass="lable01"/>
                       <h:inputText  value="#{proceedingCandidateListBean.personalInfoDTO.specialCaseTypesDTO.name}" disabled="true" styleClass="textbox" readonly="true"/>
                        <!-- Row 10-->
                        
                    </t:panelGrid>
                    </t:panelGroup>                    
                    
                    <f:verbatim><br/></f:verbatim>
                      <t:panelGrid columns="1" cellpadding="0" cellspacing="0" width="10%" align="center">
                      <t:commandButton  value="#{globalResources.back}" id="BackButtonMasterDetailDiv" forceId="true" action="list_candidates_preceed" 
                                        onblur="setFocusOnlyOnElement('BackButtonMasterDetailDiv');" styleClass="cssbuttonSmall"/>
                      </t:panelGrid>
                      <t:inputText style="width:0px; height:0px;"/>
                      