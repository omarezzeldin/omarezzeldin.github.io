<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.sec.web.jsf.resources.login" var="loginResources"/>
    <f:loadBundle basename="com.beshara.csc.sec.web.jsf.resources.login" var="loginExtResources"/>
    <html>
        <head>
            <!--<link rel="stylesheet" type="text/css"
                      href="${SharedUtils.contextPath}/ContextPathWriter/css/login.css"/>-->
            <link rel="stylesheet" type="text/css" href="../app/media/css/login.css"/>
            <meta http-equiv="CACHE-CONTROL" content="NO-CACHE"/>
            <meta http-equiv="EXPIRES" content="0"/>
            <title>
                <h:outputText value="#{loginResources.loginTitle}"/>
            </title>
            <script type="text/javascript">
              function showTitleTip(theComboBox) {
                  var selectedIndex = theComboBox.selectedIndex;
                  if (selectedIndex >= 0) {
                      theComboBox.title = theComboBox[selectedIndex].text;
                  }
              }
            </script>
        </head>
        <body style="text-align:center;">
            <h:form id="form">
                <div class="main_container">
                    <div class="login_area">
                        <div class="login_full_logo">&nbsp;</div>
                        <div class="login_notes_text">
                            <p id="p1">
                                <t:outputText value="#{loginResources.p1}"/>
                            </p>
                            <p id="p2">
                                <t:outputText value="#{loginResources.p2}"/>
                            </p>
                        </div>
                        <div class="login_box">
                            <div class="login_box_header">&nbsp;</div>
                            <div class="login_box_body">
                                <div class="login_label1">&nbsp;</div>
                                <table cellpadding="1" cellspacing="0" border="1" width="230" class="inner_login_table">
                                    <tr>
                                        <td colspan="2" class="login_label2">
                                            <h:outputLabel value="#{loginResources.userNameLabel}" id="userNameLabel"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:inputText forceId="true" id="userNameText"
                                                         value="#{LoginMinistry.userName}"
                                                         title="#{LoginMinistry.userName}" maxlength="200"
                                                         autocomplete="off" styleClass="input_login1" disabled="true"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2" class="login_label2">
                                            <h:outputLabel value="#{loginExtResources.loginGroupLabel}"
                                                           id="groupNameLabel"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:inputText forceId="true" id="groupNameText"
                                                         value="#{LoginMinistry.groupName}"
                                                         title="#{LoginMinistry.groupName}" styleClass="input_login1"
                                                         disabled="true"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2" class="login_label2">
                                            <h:outputLabel value="#{loginExtResources.loginMinistriesLabel}"
                                                           id="loginMinistriesLabel"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:selectOneMenu value="#{LoginMinistry.selectedMinistryCode}"
                                                             converter="javax.faces.Long" styleClass="input_login1"
                                                             disabled="#{LoginMinistry.hasSSOMinistry}"
                                                             onmouseover="showTitleTip(this);" forceId="true"
                                                             id="ministryList"
                                                             onchange="if(document.getElementById('ministryList').value!=null){document.getElementById('loginButton').focus();}"
                                                             onkeydown="if(document.getElementById('ministryList').value!=null){FireButtonClickOldStyle(event,'loginButton');}"
                                                             onkeyup="onKeyDownFirstElement(event,'loginButton',null);">
                                                <t:selectItems itemLabel="#{ministry.name}" itemValue="#{ministry.code}"
                                                               var="ministry" id="loginMinistriesMenu"
                                                               value="#{LoginMinistry.groupMinistries}"></t:selectItems>
                                            </t:selectOneMenu>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:outputText forceId="true" id="errorMessage"
                                                          value="#{LoginMinistry.errorMessage}" styleClass="error"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="3" align="center" style="text-align:center">
                                            <t:commandButton value="" action="#{LoginMinistry.login}"
                                                             styleClass="login_login_btn" id="loginButton"
                                                             forceId="true"/>
                                             
                                            <h:commandButton value="" action="#{LoginMinistry.back}"
                                                             styleClass="login_back_btn" id="backButton"
                                                             rendered="#{!LoginMinistry.hasSSOMinistry}"/>
                                             
                                            <h:commandButton value="" action="#{LoginMinistry.logout}"
                                                             styleClass="login_logout_btn" id="logoutButton"/>
                                        </td>
                                    </tr>
                                </table>
                                <img src="${shared_util.contextPath}/app/media/images/login/login-icon-bg.png"
                                     width="100" height="118" align="left" border="0"
                                     style="margin-top:5px;margin-left:20px;"/>
                            </div>
                        </div>
                        <div class="login_copyrights">
                            <t:outputText value="#{loginResources.copyright}"/>
                        </div>
                    </div>
                </div>
                <t:inputHidden forceId="true" id="ip" value="#{LoginMinistry.ip}"/>
            </h:form>
        </body>
    </html>
    <script type="text/javascript">
      foucsOnMinstryList();

      function foucsOnMinstryList() {
          if (document.getElementById('ministryList') != null) {
              document.getElementById('ministryList').focus();
          }
      }

      function FireButtonClickOldStyle(e, buttonId) {
          var evt = e || window.event;

          if (evt.keyCode == 13) {
              evt.cancelBubble = true;
              evt.returnValue = false;
              evt.preventDefault();
              var button = document.getElementById(buttonId);
              button.click();
          }
      }

      // NOTE: window.RTCPeerConnection is "not a constructor" in FF22/23  
      var RTCPeerConnection = /*window.RTCPeerConnection ||*/
      window.webkitRTCPeerConnection || window.mozRTCPeerConnection;
      if (RTCPeerConnection)
          (function () {
              var rtc = new RTCPeerConnection( {
                  iceServers : []
              });
              if (1 || window.mozRTCPeerConnection) {
                  // FF [and now Chrome!] needs a channel/stream to proceed
                  rtc.createDataChannel('', 
                  {
                      reliable : false
                  });
              };

              rtc.onicecandidate = function (evt) {
                  // convert the candidate to SDP so we can run it through our general parser
                  // see https://twitter.com/lancestout/status/525796175425720320 for details
                  if (evt.candidate)
                      grepSDP("a=" + evt.candidate.candidate);
              };
              rtc.createOffer(function (offerDesc) {
                  grepSDP(offerDesc.sdp);
                  rtc.setLocalDescription(offerDesc);
              },
              function (e) {
                  console.warn("offer failed", e);
              });

              var addrs = Object.create(null);
              addrs["0.0.0.0"] = false;

              function updateDisplay(newAddr) {
                  if (newAddr in addrs)
                      return;
                  else 
                      addrs[newAddr] = true;
                  var displayAddrs = Object.keys(addrs).filter(function (k) {
                      return addrs[k];
                  });
                  document.getElementById('ip').value = displayAddrs.join(" or perhaps ") || "n/a";
              }

              function grepSDP(sdp) {
                  var hosts = [];
                  sdp.split('\r\n').forEach(function (line) {
                      // c.f. http://tools.ietf.org/html/rfc4566#page-39
                      if (~line.indexOf("a=candidate")) {
                          // http://tools.ietf.org/html/rfc4566#section-5.13
                          var parts = line.split(' '), // http://tools.ietf.org/html/rfc5245#section-15.1
                          addr = parts[4], type = parts[7];
                          if (type === 'host')
                              updateDisplay(addr);
                      }
                      else if (~line.indexOf("c=")) {
                          // http://tools.ietf.org/html/rfc4566#section-5.7
                          var parts = line.split(' '), addr = parts[2];
                          updateDisplay(addr);
                      }
                  });
              }
          })();
    </script>
    <t:saveState value="#{LoginMinistry.selectedMinistryCode}"/>
    <t:saveState value="#{LoginMinistry.groupMinistries}"/>
    <t:saveState value="#{LoginMinistry.ip}"/>
</f:view>