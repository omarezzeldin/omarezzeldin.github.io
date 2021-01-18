package com.beshara.csc.hr.emp.presentation.backingbean.hiredecision;


import com.beshara.csc.nl.reg.integration.presentation.backingbean.decision.details.DecisionMainDataMaintain;

import javax.faces.event.ActionEvent;

public class HireDecisionMainDataMaintain extends DecisionMainDataMaintain {

    public void changeDecDate(ActionEvent event) {

        HireDecisionListBean bean = (HireDecisionListBean)evaluateValueBinding("hireDecisionListBean");
        bean.buildDecisionText();


    }
    public void changeSignName(ActionEvent event) {
        HireDecisionListBean bean = (HireDecisionListBean)evaluateValueBinding("hireDecisionListBean");
        bean.buildDecisionText();
    }

    public void fillSignBy(ActionEvent event) {
        super.fillSignBy(event);
        HireDecisionListBean bean = (HireDecisionListBean)evaluateValueBinding("hireDecisionListBean");
        bean.buildDecisionText();
    }
}


