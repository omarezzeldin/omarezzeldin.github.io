package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.EmpMoiWsCriminalDAO;
import com.beshara.csc.hr.emp.business.entity.EmpMoiWsCriminalEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

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
 
@Stateless(name = "EmpMoiWsCriminalSession" , mappedName = "Emp-Model-EmpMoiWsCriminalSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpMoiWsCriminalSessionBean extends EmpBaseSessionBean implements EmpMoiWsCriminalSession {
    //@PersistenceContext(unitName = "Emp")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public EmpMoiWsCriminalSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpMoiWsCriminalEntity.class;
    }

    @Override
    protected EmpMoiWsCriminalDAO DAO() {
        return (EmpMoiWsCriminalDAO)super.DAO();
    }
    
    public Long getCriminalCount(IRequestInfoDTO ri , Long civilId) throws DataBaseException, SharedApplicationException{
        return DAO().getCriminalCount(civilId);
    }
}
