package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateStatusDAO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateStatusEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless(name = "EmpCandidateStatusSession", mappedName = "Emp-Model-EmpCandidateStatusSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(EmpCandidateStatusSession.class)
public class EmpCandidateStatusSessionBean  extends EmpBaseSessionBean implements EmpCandidateStatusSession  {



    public EmpCandidateStatusSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCandidateStatusEntity.class;
    }

    @Override
    protected EmpCandidateStatusDAO DAO() {
        return (EmpCandidateStatusDAO)super.DAO();
    }
}
