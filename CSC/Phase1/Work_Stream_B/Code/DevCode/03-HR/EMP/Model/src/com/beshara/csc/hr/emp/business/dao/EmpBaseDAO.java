package com.beshara.csc.hr.emp.business.dao;

import com.beshara.base.dao.BaseDAO;

public abstract class EmpBaseDAO extends BaseDAO {
    public EmpBaseDAO() {
        super();
    }
    @Override
    protected String getEMJNDI(){
        return "em/HRM";
    }
   
}

