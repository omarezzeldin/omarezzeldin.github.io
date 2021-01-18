package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpInternalExperienceSession;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Implements a specified Client Interface as Session Bean
 * and Generated through the Client Factory Class Based on the Key Returned from the Properties File .
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
 * @author       Beshara Group
 * @author       Ahmed Sabry, Taha El-Fitiany, Sherif El-Rabbat
 * @version      1.0
 * @since        03/09/2007
 */
public class EmpInternalExperienceClientImpl extends BaseClientImpl3 implements IEmpInternalExperienceClient {

    /**
     * @param empInternalExperienceSession
     */
    public EmpInternalExperienceClientImpl() {
        super();
    }
    
    
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpInternalExperienceSession.class;
    }

    @Override
    protected EmpInternalExperienceSession SESSION() {
        return (EmpInternalExperienceSession)super.SESSION();
    }

   

    public List<IBasicDTO> getAllByCivilIdAndMinCode(Long civilId, 
                                                     Long minCode) throws DataBaseException, 
                                                                          SharedApplicationException {
        try {
            return SESSION().getAllByCivilIdAndMinCode(RI(), 
                                                                 civilId, 
                                                                 minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    
    public List<IBasicDTO> getAllBySearchCriteria(IBasicDTO empInternalExperienceDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllBySearchCriteria(RI(), empInternalExperienceDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

//==============================Start CSC-18251==========================//

    public Boolean aoeReviewInternalExperiancesByMin(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            return SESSION().aoeReviewInternalExperiancesByMin(RI(), empInternalExperienceDTOList_);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean aoeApproveInternalExperiancesByDiwaan(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            return SESSION().aoeApproveInternalExperiancesByDiwaan(RI(), empInternalExperienceDTOList_);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean aoeCancelReviewInternalExperiancesByMin(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                      SharedApplicationException {
        try {
            return SESSION().aoeCancelReviewInternalExperiancesByMin(RI(), empInternalExperienceDTOList_);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean aoeCancelApproveInternalExperiancesByDiwaan(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                          SharedApplicationException {
        try {
            return SESSION().aoeCancelApproveInternalExperiancesByDiwaan(RI(), empInternalExperienceDTOList_);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
//==============================End CSC-18251==========================//
    
    public IEmpInternalExperienceDTO addForAOE (IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException {
        
            try {
                return SESSION().addForAOE(RI(), empInternalExperienceDTO1);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        
        }   
    public List<IEmpInternalExperienceDTO> getAllByCivilId(Long civilId) throws DataBaseException, SharedApplicationException {
     
    try {
        return SESSION().getAllByCivilId(RI(), civilId);
    } catch (SharedApplicationException e) {
        throw e;
    } catch (Exception e) {
        throw getWrappedException(e);
    }
    
    }       
}
