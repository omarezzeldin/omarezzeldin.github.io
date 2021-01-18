package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Interface Contains a set of Methods Which Should be Implemented
 * with Different Implementation (SessionBean , Message Driven Bean, RMI Service  etc ...)
 * and Generated through The Client Factory Class .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Sherif El-Rabbat  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry
 * @version      1.0
 * @since        03/09/2007
 */
public interface IEmpInternalExperienceClient extends IBasicClient {
    
    public List<IBasicDTO> getAllByCivilIdAndMinCode(Long civilId, Long minCode) throws DataBaseException, 
                                                    SharedApplicationException;
    
    public List<IBasicDTO> getAllBySearchCriteria(IBasicDTO empInternalExperienceDTO) throws DataBaseException, SharedApplicationException;
    
    //==============================Start CSC-18251==========================//

    public Boolean aoeReviewInternalExperiancesByMin(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                SharedApplicationException;

    public Boolean aoeApproveInternalExperiancesByDiwaan(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                    SharedApplicationException;

    public Boolean aoeCancelReviewInternalExperiancesByMin(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                      SharedApplicationException;

    public Boolean aoeCancelApproveInternalExperiancesByDiwaan(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                          SharedApplicationException;

    //==============================End CSC-18251==========================//

    public IEmpInternalExperienceDTO addForAOE (IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException;
    
    public List<IEmpInternalExperienceDTO> getAllByCivilId(Long civilId) throws DataBaseException, SharedApplicationException;
}
