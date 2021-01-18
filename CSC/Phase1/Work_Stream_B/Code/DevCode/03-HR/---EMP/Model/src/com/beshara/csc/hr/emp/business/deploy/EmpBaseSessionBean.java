package com.beshara.csc.hr.emp.business.deploy;

import com.beshara.base.deploy.BasicSessionBean3;
import javax.persistence.PersistenceContext;

@PersistenceContext(unitName = "HRM", name = "em/HRM")

public abstract class EmpBaseSessionBean extends BasicSessionBean3 {
    public EmpBaseSessionBean() {
        super();
    }
}
