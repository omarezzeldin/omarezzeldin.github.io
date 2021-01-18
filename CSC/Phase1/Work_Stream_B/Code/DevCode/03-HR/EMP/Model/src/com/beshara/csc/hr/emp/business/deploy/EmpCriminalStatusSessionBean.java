package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.dao.EmpCriminalStatusDAO;
import com.beshara.csc.hr.emp.business.entity.EmpCriminalStatusEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Stateless(name = "EmpCriminalStatusSession", mappedName = "Emp-Model-EmpCriminalStatusSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpCriminalStatusSessionBean extends EmpBaseSessionBean implements EmpCriminalStatusSession {
    //@PersistenceContext(unitName = "Emp")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public EmpCriminalStatusSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCriminalStatusEntity.class;
    }

    @Override
    protected EmpCriminalStatusDAO DAO() {
        return (EmpCriminalStatusDAO)super.DAO();
    }

}
