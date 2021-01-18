package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.EmpExternalExperienceDAO;
import com.beshara.csc.hr.emp.business.dto.EmpExternalExperienceDTO;
import com.beshara.csc.hr.emp.business.entity.EmpExternalExperienceEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


//import org.cementj.base.trans.TransactionException;

/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents the Business Object Wrapper (as Session Bean ) for Business Component publishing.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author     Beshara Group
 * @author     Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "EmpExternalExperienceSession", mappedName = "Emp-Model-EmpExternalExperienceSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpExternalExperienceSessionBean extends EmpBaseSessionBean implements EmpExternalExperienceSession {

    /**
     * JobsSession Default Constructor
     */
    public EmpExternalExperienceSessionBean() {
        super();
    }
     
   

 
   
    public List<EmpExternalExperienceDTO> getAllByCivilId(IRequestInfoDTO ri,Long civilId) throws DataBaseException, SharedApplicationException{
        return DAO().getAllByCivilId(civilId);
    }
    protected EmpExternalExperienceDAO DAO() {
        return (EmpExternalExperienceDAO)super.DAO();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpExternalExperienceEntity.class;
    }
}
