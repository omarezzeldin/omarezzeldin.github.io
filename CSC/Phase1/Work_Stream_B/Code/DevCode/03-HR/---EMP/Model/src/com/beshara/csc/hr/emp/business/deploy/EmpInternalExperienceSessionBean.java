package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpInternalExperienceDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.hr.emp.business.entity.EmpInternalExperienceEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


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
@Stateless(name = "EmpInternalExperienceSession", mappedName = "Emp-Model-EmpInternalExperienceSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpInternalExperienceSessionBean extends EmpBaseSessionBean implements EmpInternalExperienceSession {

    /**
     * JobsSession Default Constructor
     */
    public EmpInternalExperienceSessionBean() {
        super();
    }
    
    
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpInternalExperienceEntity.class;
    }

    @Override
    protected EmpInternalExperienceDAO DAO() {
        return (EmpInternalExperienceDAO)super.DAO();
    }
    
    

    public List<IBasicDTO> getAllByCivilIdAndMinCode(IRequestInfoDTO ri,Long civilId, 
                                                     Long minCode) throws DataBaseException, 
                                                                          SharedApplicationException {
        try {
            return DAO().getAllByCivilIdAndMinCode(civilId, minCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    public List<IBasicDTO> getAllBySearchCriteria(IRequestInfoDTO ri, IBasicDTO empInternalExperienceDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        return DAO().getAllBySearchCriteria(empInternalExperienceDTO);
    }

    //==============================Start CSC-18251==========================//
    //@Auditable
    public Boolean aoeReviewInternalExperiancesByMin(IRequestInfoDTO ri,
                                                     List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                SharedApplicationException {
        return DAO().aoeReviewInternalExperiancesByMin(empInternalExperienceDTOList_);
    }
    //@Auditable
    public Boolean aoeCancelApproveInternalExperiancesByDiwaan(IRequestInfoDTO ri,
                                                               List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                          SharedApplicationException {
        return DAO().aoeCancelApproveInternalExperiancesByDiwaan(empInternalExperienceDTOList_);
    }
    //@Auditable
    public Boolean aoeCancelReviewInternalExperiancesByMin(IRequestInfoDTO ri,
                                                           List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                      SharedApplicationException {
        return DAO().aoeCancelReviewInternalExperiancesByMin(empInternalExperienceDTOList_);
    }
    //@Auditable
    public Boolean aoeApproveInternalExperiancesByDiwaan(IRequestInfoDTO ri,
                                                         List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().aoeApproveInternalExperiancesByDiwaan(empInternalExperienceDTOList_);
    }
    //==============================End CSC-18251==========================//

    //@Auditable
    public IEmpInternalExperienceDTO addForAOE (IRequestInfoDTO ri , IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException {
        
        return DAO().addForAOE(empInternalExperienceDTO1);
        
        }
    public List<IEmpInternalExperienceDTO> getAllByCivilId(IRequestInfoDTO ri , Long civilId) throws DataBaseException, SharedApplicationException {
       
         return DAO().getAllByCivilId(civilId);
    }
}
