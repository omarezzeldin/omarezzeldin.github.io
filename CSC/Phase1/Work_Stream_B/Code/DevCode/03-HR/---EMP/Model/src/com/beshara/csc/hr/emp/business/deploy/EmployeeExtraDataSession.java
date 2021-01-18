package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Date;

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
public interface EmployeeExtraDataSession extends BasicSession {

    /**
     * Get all employees main data in a given work center for VAC (sick vac)
     * @param entityKey
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getByWorkCenterForSickVac(IRequestInfoDTO ri, IEntityKey entityKey) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;

    List<IBasicDTO> getEmpExtraDataByWorkCenterForSickVac(IRequestInfoDTO ri,
                                                          IEntityKey entityKey) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;

    List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVac(IRequestInfoDTO ri, List<IWorkCentersDTO> list,
                                                               Long minCode) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;

    IPagingResponseDTO getEmpExtraDataByWorkCentersListForSickVacPaging(IRequestInfoDTO ri, List<IWorkCentersDTO> list,
                                                                        Long minCode,
                                                                        IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                             DataBaseException,
                                                                                                             SharedApplicationException;

    /**
     * Update emp extra data VALUE ONLY used by VAC (sick vac)
     * @param employeeExtraDataDTO
     * @return Boolean
     */


    public Boolean updateExtraDataValue(IRequestInfoDTO ri, IBasicDTO employeeExtraDataDTO) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;


    /**
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataType(IRequestInfoDTO ri, Long civilId,
                                         Long extraDataTypeCode) throws RemoteException, DataBaseException,
                                                                        SharedApplicationException;

    /**
     * Search for EmployeeExtraDataEntity  used by VAC (sick vac)
     * <br>
     * @return List
     */
    public List<IBasicDTO> searchForSickVac(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public IPagingResponseDTO searchForSickVacInWorkCentersList(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                                List<IWorkCentersDTO> wcList,
                                                                Long minCode) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public List<IEmployeeExtraDataDTO> getAllEmployeeExtraDataByCivilId(IRequestInfoDTO ri,
                                                                        Long civilId) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;
    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByCivilId(IRequestInfoDTO ri,
                                                                        Long civilId) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;
    public IBasicDTO getEmployeeExtraDataForMov(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;

    public Boolean updateEmpExtraDataForMov(IRequestInfoDTO ri, IBasicDTO employeeExtraDataDTO) throws RemoteException,
                                                                                                       DataBaseException,
                                                                                                       SharedApplicationException;

    public int updateStatusByMovingDate(IRequestInfoDTO ri, Long realCivilId, Date movingDate) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;

    public Boolean isDateOverlaped(IRequestInfoDTO ri, IBasicDTO _dto) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;

    public IEmployeeExtraDataDTO getByExtraDataTypeAndCivilId(IRequestInfoDTO ri, Long civilId,
                                                              Long extraDataTypeCode) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    public Boolean updateEmpExtraDataForMer(IRequestInfoDTO ri, IBasicDTO employeeExtraDataDTO) throws RemoteException,
                                                                                                       DataBaseException,
                                                                                                       SharedApplicationException;


    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByName(IRequestInfoDTO ri, Long civilId,
                                                                  String typeName) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException;

    public IEmployeeExtraDataDTO getEmpExtraDataByRealCivilId(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;
    
    public List<IBasicDTO> getByCivilIdAndExtraDataTypeCodeLst(IRequestInfoDTO ri, Long civilId,
                                                                  List<Long> extraDataTypeCodeLst, Long status) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException;
    
    public String getNationalityNumberByCivilId(IRequestInfoDTO ri, Long civilId) throws RemoteException,DataBaseException,
                                                                                         SharedApplicationException ;
    public Date getFirstHireDateFromSocialInsurance (IRequestInfoDTO ri, Long civilId)  throws RemoteException,DataBaseException,
                                                                                         SharedApplicationException ;
}
