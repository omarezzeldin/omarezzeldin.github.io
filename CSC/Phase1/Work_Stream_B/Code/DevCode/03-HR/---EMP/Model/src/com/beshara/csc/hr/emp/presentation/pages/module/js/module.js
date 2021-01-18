function filterById(event) {
    if (document.getElementById("testMsg") != null) {
        document.getElementById("testMsg").style.display = "none";
    }
    if (event.keyCode == 13 || event.keyCode == 9) {
        event.cancelBubble = true;
        event.returnValue = false;
        event.preventDefault();

        if (document.getElementById("minCodeId").value == null || document.getElementById("minCodeId").value == "") {
            document.getElementById("ministryAdd").value = "";

            setFocusOnlyOnElement('myForm:searchMinistryBtn');

        }
        else {
            getMinByCode();

        }

    }
    return;
}

function showvalidate_msg() {
    if (document.getElementById("ministryAdd").value == "") {
        if (document.getElementById("testMsg") != null) {
            document.getElementById("testMsg").style.display = "block";
        }
    }
    else {
        if (document.getElementById("testMsg") != null) {
            document.getElementById("testMsg").style.display = "none";
        }
        setFocusOnlyOnElement('myForm:searchMinistryBtn');
    }

}

function validateJobkey(id_input) {
    var condErr = document.getElementById('outputJobConditionErrMsg');
    if (condErr != null && condErr.style.display != 'none') {
        document.getElementById("testMsg").style.display = "none";
    }
    else {
        if (document.getElementById("testMsg") != null) {
            if (document.getElementById(id_input).value == "") {
                document.getElementById("testMsg").style.display = "block";

            }
            else {
                document.getElementById("testMsg").style.display = "none";
                return validatemyForm();
            }
        }

    }
}

function enabelIntegerOnlyWithZero(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g, "");
    }
}

function filterConById(event) {
    if (event.keyCode == 13) {
        event.cancelBubble = true;
        event.returnValue = false;
        event.preventDefault();
        if (document.getElementById("conditionCode").value == null || document.getElementById("conditionCode").value == "") {

        }
        else {
            getConditionByCode();
        }

        return;
    }
}

function filterMinById(event) {
    if (event.keyCode == 13) {
        event.cancelBubble = true;
        event.returnValue = false;
        event.preventDefault();
        if (document.getElementById("minCodeId").value == null || document.getElementById("minCodeId").value == "") {

        }
        else {
            minByCode();
            //                           var minCodeExist =
            //                          if(minCodeExist){
            //                              document.getElementById("errorMsg").style.display = "none";
            //                          }else{
            //                               document.getElementById("errorMsg").style.display = "block";
            //                          }
        }

        return;
    }
}

// added by A.kamal
function filterByjobkey(event, id_text, id_input) {
    var id_text = id_text;
    var id_input = id_input;
    if (document.getElementById("testMsg") != null) {
        document.getElementById("testMsg").style.display = "none";
    }
    if (event.keyCode == 13 || event.keyCode == 9) {
        event.cancelBubble = true;
        event.returnValue = false;
        event.preventDefault();

        if (document.getElementById(id_text).value == null || document.getElementById(id_text).value == "") {
            document.getElementById(id_input).value = "";
        }
        else {
            getJobByKey();
        }

    }
    return;
}

function showvalidate_msg_jobkey(id_input) {
    if (document.getElementById("testMsg") != null) {
        if (document.getElementById(id_input).value == "") {
            document.getElementById("testMsg").style.display = "block";
        }
        else {
            document.getElementById("testMsg").style.display = "none";
        }
    }
}

//By AKAMAL.MOSTAFA
A4J.AJAX.onError = function (req, status, message) {
    return;
}

//Test Then Delete
function setFocusFirstElem() {
    if ((!isVisibleComponent('lovEmp'))) {
        if (document.getElementById('myForm:resetData_btn_id') != null)
            setFocusOnlyOnElement('myForm:resetData_btn_id');
        else 
            setFocusOnlyOnElement('CivilIdAdd');
    }
}

function resetMsgInAdd() {
    if (document.getElementById('invalCivilID') != null) {
        document.getElementById('invalCivilID').innerHTML = '';
    }
    if (document.getElementById('empHired_error') != null) {
        document.getElementById('empHired_error').innerHTML = '';
    }
    if (document.getElementById('emp_Candidate_error') != null) {
        document.getElementById('emp_Candidate_error').innerHTML = '';
    }
    if (document.getElementById('emp_Nationality_error') != null) {
        document.getElementById('emp_Nationality_error').innerHTML = '';
    }
}

function setFocusAddDiv() {
    setFocusOnlyOnElement('healthyKidsList');
}

function setFocusStopDiv() {
    setFocusOnlyOnElement('reasonStopKidsList');
}

function searchAndvalidateEmp(vGroup) {
    var returnFromValidation = validatemyForm(vGroup);
    if (returnFromValidation) {
        searchLines();
        return true;
    }
    /* else {
          return false;
      }
      return true;*/
    return false;
}

function hireDatevalidateEmp(vGroup) {
    var returnFromValidation = validatemyForm(vGroup);
    if (returnFromValidation) {
        searchLines();
        return true;
    }
    /* else {
          return false;
      }
      return true;*/
    return false;
}

function showWorkCenterIntegrationDiv() {
    changeVisibilityDiv(window.blocker, window.integrationDiv1);

}

function validate(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode(key);
    var regex = /[0-9]|\./;
    if (!regex.test(key)) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault)
            theEvent.preventDefault();
    }
}

function goChangeFilNumber(e) {
    if (e.keyCode == 13) {
        checkAboutFilNum();
        e.preventDefault();
    }
}

function goFilterByCivilButton(e) {
    if (e.keyCode == 13) {
        resetMsgInAdd();
        if (validatemyForm('requiredFields')) {
            searchLines();
        }
        e.preventDefault();
    }
}

function changeFilNumberOnBlur() {
    checkAboutFilNum();
}

function checkaboutHireDate(e) {
    if (e.keyCode == 13) {
        if (searchAndvalidateEmp('87')) {
            calculateNextDateOfRaise();
        }
        e.preventDefault();
    }
}

function checkaboutHireDateValidation(e) {
    if (e.keyCode == 13) {
        var returnFromValidation = validatemyForm('87');
        if (returnFromValidation) {
            calculateNextDateOfRaise();
        }
        e.preventDefault();
    }
}

function checkaboutHireDateOnBlur() {
    if (searchAndvalidateEmp('87')) {
        calculateNextDateOfRaise();
    }
}

function checkaboutHireDateValidationOnBlur() {
    var returnFromValidation = validatemyForm('87');
    if (returnFromValidation) {
        calculateNextDateOfRaise();
    }
}

function validateAllForm() {
    if (document.getElementById('CivilIdAdd') != null && document.getElementById('CivilIdAdd').value == '') {
        resetMsgInAdd();
        return searchAndvalidateEmp('requiredFields');
    }
    else if (document.getElementById('renderErrorHireDateid') != null || document.getElementById('invaldNextYear') != null || document.getElementById('renderEmpFilNumRedundant') != null || document.getElementById('renderCandFilNumRedundant') != null || document.getElementById('outputErrMsg') != null || document.getElementById('data_notComplete_error') != null) {
        return false;
    }
    else if (document.getElementById('employees_hireDate1') != null && document.getElementById('employees_hireDate1').value != '') {
        return searchAndvalidateEmp('87');
    }
    else {
        resetMsgInAdd();
        return validatemyForm();
    }
    //return false;
}

function stopValidation() {
    var result = validatemyForm('requiredFields');
    if (!result) {
        return false;
    }
    result = validatemyForm('hireTypeGroup');
    if (!result) {
        return false;
    }
    var returnFromCmpareDates = validateTwoDatesInDataTable();
    if (returnFromCmpareDates) {
        return validateAllForm();
    }
    return false;
}
// for edit employee
function validateAllEditForm() {
    if (document.getElementById('renderErrorHireDateid') != null || document.getElementById('invaldNextYear') != null || document.getElementById('renderEmpFilNumRedundant') != null || document.getElementById('renderCandFilNumRedundant') != null || document.getElementById('outputErrMsg1') != null) {
        return false;
    }
    else if (document.getElementById('employees_hireDate1').value != '') {
        return validatemyForm('87');
    }
    else {
        return validatemyForm();
    }

}

function validateFromJobInput(event) {
    if (event.keyCode == 13) {
        validatemyForm();
        return false;
    }
}

function stopEditValidation() {
    result = validatemyForm('hireTypeGroup');
    if (!result) {
        return false;
    }
    var returnFromCmpareDates = validateTwoDatesInDataTable();
    if (returnFromCmpareDates) {
        return validateAllEditForm();
    }
    return false;
}
A4J.AJAX.onError = function (req, status, message) {
    return;
}
