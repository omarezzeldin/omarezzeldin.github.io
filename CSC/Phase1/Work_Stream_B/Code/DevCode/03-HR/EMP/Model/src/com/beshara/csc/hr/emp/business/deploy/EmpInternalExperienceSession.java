package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean .
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
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
@Remote
public interface EmpInternalExperienceSession extends BasicSession {

    public List<IBasicDTO> getAllByCivilIdAndMinCode(IRequestInfoDTO ri,Long civilId, 
                                                     Long minCode) throws RemoteException,DataBaseException, 
                                                                          SharedApplicationException ;

    public List<IBasicDTO> getAllBySearchCriteria(IRequestInfoDTO ri, IBasicDTO empInternalExperienceDTO) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;
    
    //==============================Start CSC-18251==========================//
    public Boolean aoeReviewInternalExperiancesByMin(IRequestInfoDTO ri,
                                                     List<IBasicDTO> empInternalExperienceDTOList_) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public Boolean aoeApproveInternalExperiancesByDiwaan(IRequestInfoDTO ri,
                                                         List<IBasicDTO> empInternalExperienceDTOList_) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;
    
    public Boolean aoeCancelReviewInternalExperiancesByMin(IRequestInfoDTO ri,
                                                     List<IBasicDTO> empInternalExperienceDTOList_) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public Boolean aoeCancelApproveInternalExperiancesByDiwaan(IRequestInfoDTO ri,
                                                         List<IBasicDTO> empInternalExperienceDTOList_) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;
    
    //==============================End CSC-18251==========================//
    public IEmpInternalExperienceDTO addForAOE (IRequestInfoDTO ri , IBasicDTO empInternalExperienceDTO1) throws RemoteException , DataBaseException, SharedApplicationException ;
    
    public List<IEmpInternalExperienceDTO> getAllByCivilId(IRequestInfoDTO ri , Long civilId) throws RemoteException ,DataBaseException, SharedApplicationException;
}
