package com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision;

import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;


public class EighthStepBean extends LookupMaintainBaseBean{
    public EighthStepBean() {
        setContent1Div("module_tabs_cont");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
         app.setShowWizardBar(true);
         return app;
    }
}
