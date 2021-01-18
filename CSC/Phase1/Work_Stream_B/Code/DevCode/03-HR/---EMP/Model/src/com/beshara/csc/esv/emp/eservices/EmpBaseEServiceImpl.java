package com.beshara.csc.esv.emp.eservices;


import com.beshara.base.integration.eservices.EServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EmpBaseEServiceImpl extends EServiceImpl{
    
    @PersistenceContext(unitName = "ESV-EMP")
    private EntityManager em;
    
    public EmpBaseEServiceImpl() {
        super();
    }
    
    protected EntityManager EM(){
        return em;
    }
}
