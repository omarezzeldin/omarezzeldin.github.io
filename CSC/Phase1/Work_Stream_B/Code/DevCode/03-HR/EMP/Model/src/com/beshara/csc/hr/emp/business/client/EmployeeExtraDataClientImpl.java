package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.deploy.EmployeeExtraDataSession;
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

public class EmployeeExtraDataClientImpl extends BaseClientImpl3 implements IEmployeeExtraDataClient {

    /**
     * @param employeeExtraDataSession
     */
    public EmployeeExtraDataClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmployeeExtraDataSession.class;
    }

    @Override
    protected EmployeeExtraDataSession SESSION() {
        return (EmployeeExtraDataSession)super.SESSION();
    }

    /**
     *Get all employees main data in a given work center for VAC (sick vac)
     * @param entityKey
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getByWorkCenterForSickVac(IEntityKey entityKey) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().getByWorkCenterForSickVac(RI(), entityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getEmpExtraDataByWorkCenterForSickVac(IEntityKey entityKey) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return SESSION().getEmpExtraDataByWorkCenterForSickVac(RI(), entityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVac(List<IWorkCentersDTO> list,
                                                                      Long minCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return SESSION().getEmpExtraDataByWorkCentersListForSickVac(RI(), list, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IPagingResponseDTO getEmpExtraDataByWorkCentersListForSickVacPaging(List<IWorkCentersDTO> list,
                                                                               Long minCode,
                                                                               IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                                    SharedApplicationException {
        try {
            return SESSION().getEmpExtraDataByWorkCentersListForSickVacPaging(RI(), list, minCode, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Update emp extra data VALUE ONLY used by VAC (sick vac)
     * @param employeeExtraDataDTO
     * @return Boolean
     */
    public Boolean updateExtraDataValue(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().updateExtraDataValue(RI(), employeeExtraDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Search for EmployeeExtraDataEntity  used by VAC (sick vac)
     * <br>
     * @return List
     */
    public List<IBasicDTO> searchForSickVac(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().searchForSickVac(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO searchForSickVacInWorkCentersList(IBasicDTO basicDTO, List<IWorkCentersDTO> wcList,
                                                                Long minCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().searchForSickVacInWorkCentersList(RI(), basicDTO, wcList, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataType(Long civilId, Long extraDataTypeCode) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return SESSION().isActiveExtraDataType(RI(), civilId, extraDataTypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IEmployeeExtraDataDTO> getAllEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().getAllEmployeeExtraDataByCivilId(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().getEmployeeExtraDataByCivilId(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getEmployeeExtraDataForMov(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeeExtraDataForMov(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean updateEmpExtraDataForMov(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().updateEmpExtraDataForMov(RI(), employeeExtraDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public int updateStatusByMovingDate(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().updateStatusByMovingDate(RI(), realCivilId, movingDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean isDateOverlaped(IBasicDTO _dto) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().isDateOverlaped(RI(), _dto);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    @Override
    public IEmployeeExtraDataDTO getByExtraDataTypeAndCivilId(Long civilId,
                                                              Long extraDataTypeCode) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().getByExtraDataTypeAndCivilId(RI(), civilId, extraDataTypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean updateEmpExtraDataForMer(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().updateEmpExtraDataForMer(RI(), employeeExtraDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByName(Long civilId,
                                                                  String typeName) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().getEmployeeExtraDataByName(RI(), civilId, typeName);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeeExtraDataDTO getEmpExtraDataByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            return SESSION().getEmpExtraDataByRealCivilId(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getByCivilIdAndExtraDataTypeCodeLst(Long civilId, List<Long> extraDataTypeCodeLst,
                                                               Long status) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().getByCivilIdAndExtraDataTypeCodeLst(RI(), civilId, extraDataTypeCodeLst, status);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public String getNationalityNumberByCivilId(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getNationalityNumberByCivilId(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    public Date getFirstHireDateFromSocialInsurance (Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getFirstHireDateFromSocialInsurance(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
