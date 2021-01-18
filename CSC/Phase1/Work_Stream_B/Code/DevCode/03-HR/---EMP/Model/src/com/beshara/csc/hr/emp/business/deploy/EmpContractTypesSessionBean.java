package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.dao.EmpContractTypesDAO;
import com.beshara.csc.hr.emp.business.entity.EmpContractTypesEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * @author Hany Omar
 * @version 1.0
 * @since 30/11/2016
 */
@Stateless(name = "EmpContractTypesSession", mappedName = "Emp-Model-EmpContractTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpContractTypesSessionBean extends EmpBaseSessionBean implements EmpContractTypesSession { //@PersistenceContext ( unitName = "Emp" )


    /**
     * JobsSession Default Constructor */
    public EmpContractTypesSessionBean() {
    }

    @Override
    protected EmpContractTypesDAO DAO() {
        return (EmpContractTypesDAO)super.DAO();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpContractTypesEntity.class;
    }
}
