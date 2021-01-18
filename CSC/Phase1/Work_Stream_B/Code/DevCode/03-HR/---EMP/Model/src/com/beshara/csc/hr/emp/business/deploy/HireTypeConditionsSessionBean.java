package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.dao.HireTypeConditionsDAO;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless(name = "HireTypeConditionsSession", mappedName = "Emp-Model-HireTypeConditionsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HireTypeConditionsSessionBean extends EmpBaseSessionBean implements HireTypeConditionsSession {
    public HireTypeConditionsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HireTypeConditionsEntity.class;
    }
    
    @Override 
    protected HireTypeConditionsDAO DAO(){
        return (HireTypeConditionsDAO)super.DAO();
    }
}
