package com.beshara.csc.hr.emp.business.dao;

import com.beshara.base.dao.BaseDAO;

public class EmpCandidateStatusDAO  extends EmpBaseDAO {
   
    public EmpCandidateStatusDAO() {
        super();
    }
    
    @Override
    protected BaseDAO newInstance() {
        return new EmpCandidateStatusDAO();
    }
   
   
}
