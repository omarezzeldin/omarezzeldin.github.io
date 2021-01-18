package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
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
public interface EmpReasonDataSession extends BasicSession {

    public List<IBasicDTO> getByTypeCode(IRequestInfoDTO ri, Long restypeCode) throws RemoteException,
                                                                                      SharedApplicationException,
                                                                                      DataBaseException;

    public List<IBasicDTO> getPublicReasons(IRequestInfoDTO ri, Long restypeCode) throws RemoteException,
                                                                                         SharedApplicationException,
                                                                                         DataBaseException;

    public List<IBasicDTO> getSpecialReasons(IRequestInfoDTO ri, Long restypeCode,
                                             Long vactypeCode) throws RemoteException, SharedApplicationException,
                                                                      DataBaseException;


    public List<IBasicDTO> getSpecialReasonsForOthersVac(IRequestInfoDTO ri, Long restypeCode,
                                                         Long vactypeCode) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;

    /**
     *
     * @param restypeCode
     * @param vactypeCode
     * @return list of EmpReasonDataEntity representing rejection reason for certain vacation type
     * (all vacation rejection reasons not assigned to any vactypecode union vactypecode reasons)
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> getEmpReasonsForVacType(IRequestInfoDTO ri, Long restypeCode,
                                                   Long vactypeCode) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    public Boolean isRejReasonUsedBefore(IRequestInfoDTO ri, Long resDatSerial) throws RemoteException,
                                                                                       SharedApplicationException,
                                                                                       DataBaseException;

    public Boolean isGenRejReasonRedundant(IRequestInfoDTO ri, String resDatDesc,
                                           Long resDatSerial) throws RemoteException, SharedApplicationException,
                                                                     DataBaseException;

    public Boolean isGenRejReasonRedundanInPrivateReasons(IRequestInfoDTO ri,
                                                          String resDatDesc) throws RemoteException,
                                                                                    SharedApplicationException,
                                                                                    DataBaseException;

    public Boolean isPrivateRejReasonRedundan(IRequestInfoDTO ri, String resDatDesc,
                                              Long resDatSerial) throws RemoteException, SharedApplicationException,
                                                                        DataBaseException;

    public int convertPrivateToGen(IRequestInfoDTO ri, IBasicDTO empReasonDataDTO1) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public int proceedGenReasonAdd(IRequestInfoDTO ri, IBasicDTO empReasonDataDTO1) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public void removeItems(IRequestInfoDTO ri, IBasicDTO dto, IBasicDTO dto1) throws RemoteException,
                                                                                      SharedApplicationException,
                                                                                      DataBaseException;

    public IBasicDTO addRejectionReason(IRequestInfoDTO ri, IBasicDTO iBasicDTO) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;

}
