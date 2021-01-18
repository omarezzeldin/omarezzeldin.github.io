package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.EmpMinEdtypesDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpMinEdtypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpMinEdtypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

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
@Stateless(name = "EmpMinEdtypesSession", mappedName = "Emp-Model-EmpMinEdtypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpMinEdtypesSessionBean extends EmpBaseSessionBean implements EmpMinEdtypesSession {


    /**
     * JobsSession Default Constructor
     */
    public EmpMinEdtypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpMinEdtypesEntity.class;
    }

    @Override
    protected EmpMinEdtypesDAO DAO() {
        return (EmpMinEdtypesDAO)super.DAO();
    }
    
    public List<IEmpMinEdtypesDTO> getByMinCode(IRequestInfoDTO ri , Long minCode) throws DataBaseException, SharedApplicationException{
        return DAO().getByMinCode(minCode);
        }
 

}
