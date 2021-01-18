package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.BaseClientImpl2;
import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.deploy.IContextSession;
import com.beshara.csc.hr.emp.business.deploy.HireTypeConditionsSession;

public class HireTypeConditionsClientImpl extends BaseClientImpl3 implements IHireTypeConditionsClient {
    public HireTypeConditionsClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return HireTypeConditionsSession.class;
    }
    
    protected HireTypeConditionsSession SESSION(){
        return (HireTypeConditionsSession)super.SESSION();
    }
}
