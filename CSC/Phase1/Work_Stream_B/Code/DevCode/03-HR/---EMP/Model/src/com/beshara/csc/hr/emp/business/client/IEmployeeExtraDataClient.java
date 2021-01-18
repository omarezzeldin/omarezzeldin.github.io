package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Date;

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
public interface IEmployeeExtraDataClient extends IBasicClient {

    /**
     *Get all employees main data in a given work center for VAC (sick vac)
     * @param entityKey
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getByWorkCenterForSickVac(IEntityKey entityKey) throws DataBaseException,
                                                                                  SharedApplicationException;

    List<IBasicDTO> getEmpExtraDataByWorkCenterForSickVac(IEntityKey entityKey) throws DataBaseException,
                                                                                       SharedApplicationException;

    List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVac(List<IWorkCentersDTO> list,
                                                               Long minCode) throws DataBaseException,
                                                                                    SharedApplicationException;

    IPagingResponseDTO getEmpExtraDataByWorkCentersListForSickVacPaging(List<IWorkCentersDTO> list, Long minCode,
                                                                        IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                             SharedApplicationException;

    /**
     * Update emp extra data VALUE ONLY used by VAC (sick vac)
     * @param employeeExtraDataDTO
     * @return Boolean
     */
    public Boolean updateExtraDataValue(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                               SharedApplicationException;

    /**
     * Search for EmployeeExtraDataEntity  used by VAC (sick vac)
     * <br>
     * @return List
     */
    public List<IBasicDTO> searchForSickVac(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;

    IPagingResponseDTO searchForSickVacInWorkCentersList(IBasicDTO basicDTO, List<IWorkCentersDTO> wcList,
                                                         Long minCode) throws DataBaseException,
                                                                              SharedApplicationException;


    /**
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataType(Long civilId, Long extraDataTypeCode) throws DataBaseException,
                                                                                      SharedApplicationException;

    public List<IEmployeeExtraDataDTO> getAllEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException;
    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException;
    public Boolean updateEmpExtraDataForMov(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException;

    public IBasicDTO getEmployeeExtraDataForMov(Long civilId) throws DataBaseException, SharedApplicationException;

    public int updateStatusByMovingDate(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                  SharedApplicationException;

    public Boolean isDateOverlaped(IBasicDTO _dto) throws DataBaseException, SharedApplicationException;

    public IEmployeeExtraDataDTO getByExtraDataTypeAndCivilId(Long civilId,
                                                              Long extraDataTypeCode) throws DataBaseException,
                                                                                             SharedApplicationException;

    public Boolean updateEmpExtraDataForMer(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException;

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByName(Long civilId,
                                                                  String typeName) throws DataBaseException,
                                                                                          SharedApplicationException;

    public IEmployeeExtraDataDTO getEmpExtraDataByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                                       SharedApplicationException;
    
    public List<IBasicDTO> getByCivilIdAndExtraDataTypeCodeLst(Long civilId, List<Long> extraDataTypeCodeLst, Long status) 
                                                                            throws DataBaseException, SharedApplicationException;
    public String getNationalityNumberByCivilId(Long civilId) throws DataBaseException, SharedApplicationException ;
    
    public Date getFirstHireDateFromSocialInsurance (Long civilId) throws DataBaseException, SharedApplicationException ; 
}
