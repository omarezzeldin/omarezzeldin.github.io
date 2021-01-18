package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.datafiltration.IDfDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpExtraDataTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmployeeExtraDataEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.inf.EmpKwtCitizensResidentsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;


public class EmployeeExtraDataDAO extends EmpBaseDAO {
    public EmployeeExtraDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmployeeExtraDataDAO();
    }


    /**<code>select o from EmployeeExtraDataEntity o</code>.
     * @return List
     */
    @Override
    public List<IEmployeeExtraDataDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList<IEmployeeExtraDataDTO> arrayList = new ArrayList<IEmployeeExtraDataDTO>();
            List list = EM().createNamedQuery("EmployeeExtraDataEntity.findAll").getResultList();
            for (Object employeeExtraData : list) {
                arrayList.add(EmpDTOFactory.createEmployeeExtraDataDTO((EmployeeExtraDataEntity)employeeExtraData));
            }
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /**
     * Create a New EmployeeExtraDataEntity
     * @param employeeExtraDataDTO
     * @return EmployeeExtraDataDTO
     */
    @Override
    public IEmployeeExtraDataDTO add(IBasicDTO employeeExtraDataDTO1) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            EmployeeExtraDataEntity employeeExtraDataEntity = new EmployeeExtraDataEntity();

            IEmployeeExtraDataDTO employeeExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraDataDTO1;
//            if (employeeExtraDataDTO.getCode() != null) {
//                employeeExtraDataEntity.setSerial(((IEmployeeExtraDataEntityKey)employeeExtraDataDTO.getCode()).getSerial());
//            } else {
                employeeExtraDataEntity.setSerial(findMaxId());
//            }

            employeeExtraDataEntity.setFromDate(employeeExtraDataDTO.getFromDate());

            employeeExtraDataEntity.setUntilDate(employeeExtraDataDTO.getUntilDate());

            employeeExtraDataEntity.setValue(employeeExtraDataDTO.getValue());
            employeeExtraDataEntity.setCreatedBy(employeeExtraDataDTO.getCreatedBy());
            employeeExtraDataEntity.setCreatedDate(employeeExtraDataDTO.getCreatedDate());
            employeeExtraDataEntity.setAuditStatus(employeeExtraDataDTO.getAuditStatus());
            employeeExtraDataEntity.setTabrecSerial(employeeExtraDataDTO.getTabrecSerial());

            if (employeeExtraDataDTO.getEmployeesDTO() != null) {
                List list =
                    EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
                                                                                                                ((IEmployeesEntityKey)employeeExtraDataDTO.getEmployeesDTO().getCode()).getCivilId()).getResultList();
                if (list == null || list.size() == 0)
                    throw new NoResultException();
                employeeExtraDataEntity.setEmployeesEntity((EmployeesEntity)list.get(0));
            } else {
                throw new ItemNotFoundException();
            }

            if (employeeExtraDataDTO.getEmpExtraDataTypesDTO() != null) {
                List list =
                    EM().createQuery("SELECT o FROM EmpExtraDataTypesEntity o WHERE o.extdattypeCode = :extdattypeCode").setParameter("extdattypeCode",
                                                                                                                                      ((IEmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode()).getResultList();
                if (list == null || list.size() == 0)
                    throw new NoResultException();
                employeeExtraDataEntity.setEmpExtraDataTypesEntity((EmpExtraDataTypesEntity)list.get(0));
            } else {
                throw new ItemNotFoundException();
            }


            if (employeeExtraDataDTO.getValueDesc() != null)
                employeeExtraDataEntity.setValueDesc(employeeExtraDataDTO.getValueDesc());

            if (employeeExtraDataDTO.getStatus() != null)
                employeeExtraDataEntity.setStatus(employeeExtraDataDTO.getStatus());

            if (employeeExtraDataDTO.getMinCode() != null)
                employeeExtraDataEntity.setMinCode(employeeExtraDataDTO.getMinCode());

            if (employeeExtraDataDTO.getRealCivilId() != null)
                employeeExtraDataEntity.setRealCivilId(employeeExtraDataDTO.getRealCivilId());
               
                employeeExtraDataEntity.setCscBookNo(employeeExtraDataDTO.getCscBookNo());
                employeeExtraDataEntity.setCscBookDate(employeeExtraDataDTO.getCscBookDate());
                employeeExtraDataEntity.setComments(employeeExtraDataDTO.getComments());
            

            doAdd(employeeExtraDataEntity);
            employeeExtraDataDTO.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(employeeExtraDataEntity.getSerial()));
            // Set PK after creation
            return employeeExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Create a New EmployeeExtraDataEntity
     * @param employeeExtraDataDTO
     * @return EmployeeExtraDataDTO
     */
    public IEmployeeExtraDataDTO addForEmployementCycleCMT(IBasicDTO employeeExtraDataDTO1) throws DataBaseException,
                                                                                                   SharedApplicationException {
        IEmployeeExtraDataDTO employeeExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraDataDTO1;
        try {
            System.out.println("--------------------- IN addForEmployementCycleCMT -------------------------------------------\n");
            EmployeeExtraDataEntity employeeExtraDataEntity = new EmployeeExtraDataEntity();


            employeeExtraDataEntity.setSerial(findMaxId() );
//((IEmployeeExtraDataEntityKey)employeeExtraDataDTO.getCode()).getSerial());
            employeeExtraDataEntity.setFromDate(employeeExtraDataDTO.getFromDate());
            employeeExtraDataEntity.setUntilDate(employeeExtraDataDTO.getUntilDate());
            employeeExtraDataEntity.setValue(employeeExtraDataDTO.getValue());
            employeeExtraDataEntity.setCreatedBy(employeeExtraDataDTO.getCreatedBy());
            employeeExtraDataEntity.setCreatedDate(employeeExtraDataDTO.getCreatedDate());
            employeeExtraDataEntity.setAuditStatus(employeeExtraDataDTO.getAuditStatus());
            employeeExtraDataEntity.setTabrecSerial(employeeExtraDataDTO.getTabrecSerial());
            employeeExtraDataEntity.setCscBookNo(employeeExtraDataDTO.getCscBookNo());
            employeeExtraDataEntity.setCscBookDate(employeeExtraDataDTO.getCscBookDate());
            employeeExtraDataEntity.setComments(employeeExtraDataDTO.getComments());
            
            doAdd(employeeExtraDataEntity);
            // Set PK after creation
            System.out.println("---------------------- IN addForEmployementCycleCMT ------------------------------------------\n");


            return employeeExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Update an Existing EmployeeExtraDataEntity
     * @param employeeExtraDataDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO employeeExtraDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmployeeExtraDataDTO employeeExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraDataDTO1;

            EmployeeExtraDataEntity employeeExtraDataEntity =
                EM().find(EmployeeExtraDataEntity.class, (IEmployeeExtraDataEntityKey)employeeExtraDataDTO.getCode());

            employeeExtraDataEntity.setSerial(((IEmployeeExtraDataEntityKey)employeeExtraDataDTO.getCode()).getSerial());
            employeeExtraDataEntity.setFromDate(SharedUtils.getSqlDate());
            employeeExtraDataEntity.setUntilDate(SharedUtils.getSqlDate());
            employeeExtraDataEntity.setValue(employeeExtraDataDTO.getValue());
            employeeExtraDataEntity.setLastUpdatedBy(employeeExtraDataDTO.getLastUpdatedBy());
            employeeExtraDataEntity.setLastUpdatedDate(employeeExtraDataDTO.getLastUpdatedDate());
            employeeExtraDataEntity.setAuditStatus(employeeExtraDataDTO.getAuditStatus());
            employeeExtraDataEntity.setTabrecSerial(employeeExtraDataDTO.getTabrecSerial());
            
            if(employeeExtraDataDTO.getCscBookNo() != null && !"".equals(employeeExtraDataDTO.getCscBookNo()))
                employeeExtraDataEntity.setCscBookNo(employeeExtraDataDTO.getCscBookNo());
            
            if(employeeExtraDataDTO.getCscBookDate() != null && !"".equals(employeeExtraDataDTO.getCscBookDate()))
                employeeExtraDataEntity.setCscBookDate(employeeExtraDataDTO.getCscBookDate());
            
            if(employeeExtraDataDTO.getComments() != null && !"".equals(employeeExtraDataDTO.getComments()))
                employeeExtraDataEntity.setComments(employeeExtraDataDTO.getComments());

            if (employeeExtraDataDTO.getEmployeesDTO() != null) {
                List list =
                    EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
                                                                                                                ((IEmployeesEntityKey)employeeExtraDataDTO.getEmployeesDTO().getCode()).getCivilId()).getResultList();
                if (list == null || list.size() == 0)
                    throw new NoResultException();
                employeeExtraDataEntity.setEmployeesEntity((EmployeesEntity)list.get(0));
            } else {
                throw new ItemNotFoundException();
            }

            if (employeeExtraDataDTO.getEmpExtraDataTypesDTO() != null) {
                List list =
                    EM().createQuery("SELECT o FROM EmpExtraDataTypesEntity o WHERE o.extdattypeCode = :extdattypeCode").setParameter("extdattypeCode",
                                                                                                                                      ((IEmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode()).getResultList();
                if (list == null || list.size() == 0)
                    throw new NoResultException();
                employeeExtraDataEntity.setEmpExtraDataTypesEntity((EmpExtraDataTypesEntity)list.get(0));
            } else {
                throw new ItemNotFoundException();
            }

            doUpdate(employeeExtraDataEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Remove an Existing EmployeeExtraDataEntity
     * @param employeeExtraDataDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO employeeExtraDataDTO) throws DataBaseException, SharedApplicationException {
        try {
            EmployeeExtraDataEntity employeeExtraDataEntity =
                EM().find(EmployeeExtraDataEntity.class, (IEmployeeExtraDataEntityKey)employeeExtraDataDTO.getCode());

            if (employeeExtraDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(employeeExtraDataEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Get EmployeeExtraDataEntity By Primary Key
     * @param id
     * @return EmployeeExtraDataDTO
     */
    @Override
    public IEmployeeExtraDataDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {

        try {
            IEmployeeExtraDataEntityKey _id = (IEmployeeExtraDataEntityKey)id;

            EmployeeExtraDataEntity employeeExtraDataEntity = EM().find(EmployeeExtraDataEntity.class, _id);
            if (employeeExtraDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeeExtraDataDTO employeeExtraDataDTO =
                EmpDTOFactory.createEmployeeExtraDataDTO(employeeExtraDataEntity);
            return employeeExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Get all employees main data in a given work center for VAC (sick vac)
     * @param entityKey
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getByWorkCenterForSickVac(IEntityKey entityKey) throws DataBaseException,
                                                                                  SharedApplicationException { //sheka
        try {
            IWorkCentersEntityKey wrkCenterEntityKey = (IWorkCentersEntityKey)entityKey;
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();

            StringBuffer ejbql = new StringBuffer("select o from EmployeeExtraDataEntity o where o.serial = o.serial");

            ejbql.append(" AND o.employeesEntity.minCode=" + wrkCenterEntityKey.getMinCode() +
                         " AND o.employeesEntity.wrkCode='" + wrkCenterEntityKey.getWrkCode() + "'");

            ejbql.append(" AND o.employeesEntity.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                         IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                         IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
                         IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                         IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");

            List list = EM().createQuery(ejbql.toString()).getResultList();

            //        List<EmployeeExtraDataEntity> list =
            //            em.createNamedQuery("EmployeeExtraDataEntity.getByEmpWorkCenter")
            //            .setParameter("minCode", wrkCenterEntityKey.getMinCode())
            //            .setParameter("wrkCode", wrkCenterEntityKey.getWrkCode())
            //            .getResultList();
            for (Object employeeExtraDataEntity : list) {

                arrayList.add(getEmployeeExtraDataDTOForSickVac((EmployeeExtraDataEntity)employeeExtraDataEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
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
            IEmployeeExtraDataDTO _employeeExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraDataDTO;

            EmployeeExtraDataEntity employeeExtraDataEntity =
                EM().find(EmployeeExtraDataEntity.class, (IEmployeeExtraDataEntityKey)_employeeExtraDataDTO.getCode());

            employeeExtraDataEntity.setValue(_employeeExtraDataDTO.getValue());
            employeeExtraDataEntity.setLastUpdatedBy(_employeeExtraDataDTO.getLastUpdatedBy());
            employeeExtraDataEntity.setLastUpdatedDate(_employeeExtraDataDTO.getLastUpdatedDate());
            doUpdate(employeeExtraDataEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Search for EmployeeExtraDataEntity  used by VAC (sick vac)
     * <br>
     * @return List
     */
    public List<IBasicDTO> searchForSickVac(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeeExtraDataSearchDTO searchDTO = (IEmployeeExtraDataSearchDTO)basicDTO;
            StringBuffer ejbql = new StringBuffer("select o from EmployeeExtraDataEntity o WHERE o.serial=o.serial");
            if (searchDTO.getCivilId() != null)
                ejbql.append(" AND  o.employeesEntity.realCivilId = '" + searchDTO.getCivilId() + "'");
            if (searchDTO.getEmpName() != null && !searchDTO.getEmpName().equals("")) {
                //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
                //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
                //                         searchDTO.getEmpName() + "')");
                ejbql.append(" AND o.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 searchDTO.getEmpName()) + " ) ");

            }

            if (searchDTO.getExtraDataTypeCode() != null)
                ejbql.append(" AND o.extraDataTypeCode = " + searchDTO.getExtraDataTypeCode() + "");
            if (searchDTO.getValue() != null && !searchDTO.getValue().equals("") &&
                !searchDTO.getValue().equals(ISystemConstant.VIRTUAL_VALUE.toString()))
                ejbql.append(" AND o.value='" + searchDTO.getValue() + "'");

            if (searchDTO.getWorkCenterCode() != null && !searchDTO.getWorkCenterCode().equals("")) {
                String[] str = searchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.employeesEntity.minCode=" + Long.parseLong(str[0]) +
                             " AND o.employeesEntity.wrkCode='" + str[1] + "'");
            }
            ejbql.append(" AND o.employeesEntity.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                         IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                         IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
                         IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                         IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");

            List list = null;
            System.out.println(ejbql.toString());
            if (ejbql != null)
                list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (Object entity : list) {
                listDTO.add(getEmployeeExtraDataDTOForSickVac((EmployeeExtraDataEntity)entity));
            }
            return listDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public Long getCountSearchForSickVacInWorkCentersList(IBasicDTO basicDTO, List<IWorkCentersDTO> wcList,
                                                          Long minCode, String statusForHire) throws DataBaseException,
                                                                                                     SharedApplicationException {


        return buildSearchQueryCountWithPaging(minCode, wcList, basicDTO, statusForHire);

    }

    /**
     * @param basicDTO, wcList
     * Search for EmployeeExtraDataEntity  used by VAC (sick vac)
     * <br>
     * @return List
     */
    public List<IBasicDTO> searchForSickVacInWorkCentersList(IBasicDTO basicDTO, List<IWorkCentersDTO> wcList,
                                                             Long minCode,
                                                             String statusForHire) throws DataBaseException,
                                                                                          SharedApplicationException {

        IEmployeeExtraDataSearchDTO searchDTO = (IEmployeeExtraDataSearchDTO)basicDTO;
        IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        try {

            StringBuffer ejbql = new StringBuffer("select o from EmployeeExtraDataEntity o WHERE o.serial=o.serial");
            if (searchDTO.getCivilId() != null)
                ejbql.append(" AND  o.employeesEntity.realCivilId = '" + searchDTO.getCivilId() + "'");
            if (searchDTO.getEmpName() != null && !searchDTO.getEmpName().equals("")) {

                ejbql.append(" and o.employeesEntity.activeFlag= " + ISystemConstant.ACTIVE_FLAG +
                             " AND  o.employeesEntity.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 searchDTO.getEmpName()) + " ) ");

            }

            if (searchDTO.getExtraDataTypeCode() != null)
                ejbql.append(" AND o.extraDataTypeCode = " + searchDTO.getExtraDataTypeCode() + "");
            if (searchDTO.getValue() != null && !searchDTO.getValue().equals("") &&
                !searchDTO.getValue().equals(ISystemConstant.VIRTUAL_VALUE.toString()))
                ejbql.append(" AND o.value='" + searchDTO.getValue() + "'");

            if (searchDTO.getWorkCenterCode() != null && !searchDTO.getWorkCenterCode().equals("")) {
                String[] str = searchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.employeesEntity.minCode=" + Long.parseLong(str[0]) +
                             " AND o.employeesEntity.wrkCode='" + str[1] + "'");
            }
            ejbql.append(" AND o.employeesEntity.hirstatusCode IN (" + statusForHire + ") ");
            ejbql.append(" and  o.employeesEntity.minCode=" + minCode);

            if (wcList != null && wcList.size() > 0) {
                ejbql.append(" AND o.employeesEntity.wrkCode IN (");
                for (IWorkCentersDTO wcDTO : wcList) {
                    String wrkCode = ((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode();
                    ejbql.append("").append("'" + wrkCode + "'").append(",");
                }
                ejbql.deleteCharAt(ejbql.length() - 1);
                ejbql.append(")");
            }


            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);
                    ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }


            List list = null;
            System.out.println(ejbql.toString());


            Query query = null;
            if (ejbql != null)
                query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();

            for (Object entity : list) {
                listDTO.add(getEmployeeExtraDataDTOForSickVac((EmployeeExtraDataEntity)entity));
            }

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return listDTO;
    }
    //    select distinct ex.* from hr_emp_employees emp inner join hr_emp_employee_extra_data ex ON
    //    emp.civil_id = ex.civil_id
    //    where ex.extdattype_code=1
    //    AND ex.min_code=NVL(:P_MIN_CODE , ex.MIN_CODE)
    //    AND emp.WRK_CODE = NVL(:P_WRK_CODE , emp.WRK_CODE)
    //    AND hr_emp_pac.check_emp_hire(ex.R_CIVIL_ID) = 1;


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getEmpExtraDataByWorkCenterForSickVac(IEntityKey entityKey) throws DataBaseException,
                                                                                              SharedApplicationException {

        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List list = buildSearchQuery(entityKey);
            for (Object employeeExtraDataEntity : list) {

                arrayList.add(getEmployeeExtraDataDTOForSickVac((EmployeeExtraDataEntity)employeeExtraDataEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeeExtraDataEntity> buildSearchQuery(IEntityKey entityKey) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            IWorkCentersEntityKey wrkCenterEntityKey = (IWorkCentersEntityKey)entityKey;
            List<EmployeeExtraDataEntity> list = null;
            Query query = null;


            StringBuilder sql = new StringBuilder("SELECT ex.* ");
            sql.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp ");
            sql.append(" ON ex.CIVIL_ID = emp.CIVIL_ID ");
            sql.append(" AND ex.MIN_CODE = emp.MIN_CODE ");
            sql.append(" WHERE hr_emp_pac.check_emp_hire(ex.R_CIVIL_ID) = 1 ");
            sql.append(" AND ex.extdattype_code=");
            sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);
            sql.append(" AND emp.MIN_CODE = NVL(");
            sql.append(wrkCenterEntityKey.getMinCode());
            sql.append(", emp.MIN_CODE)");

            sql.append(" AND emp.WRK_CODE = NVL(");
            sql.append(wrkCenterEntityKey.getWrkCode());
            sql.append(", emp.WRK_CODE)");


            query = EM().createNativeQuery(sql.toString(), EmployeeExtraDataEntity.class);

            System.out.println(sql.toString());
            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    //    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    //    public List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVac(Long minCode, List<IWorkCentersDTO> list,
    //                                                                      String statusForHire) throws DataBaseException,
    //                                                                                                   SharedApplicationException {
    //        try {
    //            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
    //            //    List resultList = buildSearchQueryList(list);
    //            List resultList = buildSearchQueryList(minCode, list, statusForHire);
    //            for (Object employeeExtraDataEntity : resultList) {
    //                EmployeeExtraDataEntity targetEmployeeExtraDataEntity = null;
    //                try {
    //                    targetEmployeeExtraDataEntity = (EmployeeExtraDataEntity)employeeExtraDataEntity;
    //                } catch (ClassCastException ex) {
    //                    ex.printStackTrace();
    //                }
    //                arrayList.add(getEmployeeExtraDataDTOForSickVac(targetEmployeeExtraDataEntity));
    //                //                arrayList.add(getEmployeeExtraDataDTOForSickVac((EmployeeExtraDataEntity)employeeExtraDataEntity));
    //            }
    //            if (arrayList.size() == 0)
    //                throw new NoResultException();
    //            return arrayList;
    //        } catch (Exception e) {
    //            e = wrapIntoDataBaseException(e);
    //            if (e instanceof DataBaseException) {
    //                throw (DataBaseException)e;
    //            } else {
    //                throw (SharedApplicationException)e;
    //            }
    //        }
    //
    //    }

    public Long getEmpExtraDataByWorkCentersCountForSickVacPaging(Long minCode, List<IWorkCentersDTO> list,
                                                                  String statusForHire) throws DataBaseException,
                                                                                               SharedApplicationException {
        return buildSearchQueryCountWithPaging(minCode, list, statusForHire);
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVacPaging(Long minCode, List<IWorkCentersDTO> list,
                                                                            String statusForHire,
                                                                            IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                                 SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            //    List resultList = buildSearchQueryListwi(list);
            List resultList = buildSearchQueryListWithPaging(minCode, list, statusForHire, requestDTO);
            for (Object employeeExtraDataEntity : resultList) {
                EmployeeExtraDataEntity targetEmployeeExtraDataEntity = null;
                try {
                    targetEmployeeExtraDataEntity = (EmployeeExtraDataEntity)employeeExtraDataEntity;
                } catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
                arrayList.add(getEmployeeExtraDataDTOForSickVac(targetEmployeeExtraDataEntity));
                //                arrayList.add(getEmployeeExtraDataDTOForSickVac((EmployeeExtraDataEntity)employeeExtraDataEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeeExtraDataEntity> buildSearchQueryList(List<IWorkCentersDTO> list) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {
            List<EmployeeExtraDataEntity> list1 = null;
            Query query = null;


            StringBuilder sql = new StringBuilder("SELECT ex.* ");
            sql.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp ");
            sql.append(" ON ex.CIVIL_ID = emp.CIVIL_ID ");
            sql.append(" AND ex.MIN_CODE = emp.MIN_CODE ");
            sql.append(" WHERE hr_emp_pac.check_emp_hire(ex.R_CIVIL_ID) = 1 ");
            sql.append(" AND ex.extdattype_code=");
            sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);


            sql.append(" AND emp.MIN_CODE IN (");
            for (IWorkCentersDTO wcDTO : list) {
                Long minCode = ((IWorkCentersEntityKey)wcDTO.getCode()).getMinCode();
                sql.append("").append(minCode).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");


            sql.append(" AND emp.WRK_CODE IN (");
            for (IWorkCentersDTO wcDTO : list) {
                Long wrkCode = Long.parseLong(((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode());
                sql.append("").append(wrkCode).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");


            query = EM().createNativeQuery(sql.toString(), EmployeeExtraDataEntity.class);

            System.out.println(sql.toString());
            list1 = query.getResultList();


            if (list1 == null || list1.size() == 0)
                throw new NoResultException();

            return list1;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeeExtraDataEntity> buildSearchQueryList(Long minCode, List<IWorkCentersDTO> list,
                                                               String statusForHire) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            List<EmployeeExtraDataEntity> list1 = null;
            Query query = null;


            StringBuilder sql = new StringBuilder("SELECT ex.* ");
            sql.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp ");
            sql.append(" ON ex.CIVIL_ID = emp.CIVIL_ID ");
            sql.append(" and active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in (" + statusForHire +
                       " ) ");
            sql.append(" AND ex.extdattype_code=");
            sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);
            sql.append(" AND emp.MIN_CODE =" + minCode);

            if (list != null) {
                sql.append(" AND emp.WRK_CODE IN (");
                for (IWorkCentersDTO wcDTO : list) {
                    Long wrkCode = Long.parseLong(((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode());
                    sql.append("").append(wrkCode).append(",");
                }
                sql.deleteCharAt(sql.length() - 1);
                sql.append(")");
            }

            query = EM().createNativeQuery(sql.toString(), EmployeeExtraDataEntity.class);

            System.out.println(sql.toString());
            list1 = query.getResultList();


            if (list1 == null || list1.size() == 0)
                throw new NoResultException();

            return list1;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeeExtraDataEntity> buildSearchQueryListWithPaging(Long minCode, List<IWorkCentersDTO> list,
                                                                         String statusForHire,
                                                                         IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                              SharedApplicationException {
        try {
            List<EmployeeExtraDataEntity> list1 = null;
            Query query = null;


            //            StringBuilder sql = new StringBuilder("SELECT ex.* ");
            //            sql.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp ");
            //            sql.append(" ON ex.CIVIL_ID = emp.CIVIL_ID ");
            //            sql.append(" and active_flag= " + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in (" +
            //                       statusForHire + " ) ");
            //            sql.append(" AND ex.extdattype_code=");
            //            sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);
            //            sql.append(" AND emp.MIN_CODE =" + minCode);
            //
            //            if (list != null) {
            //                sql.append(" AND emp.WRK_CODE IN (");
            //                for (IWorkCentersDTO wcDTO : list) {
            //                    Long wrkCode = Long.parseLong(((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode());
            //                    sql.append("").append(wrkCode).append(",");
            //                }
            //                sql.deleteCharAt(sql.length() - 1);
            //                sql.append(")");
            //            }

            StringBuilder sql =
                new StringBuilder("select  SERIAL    ," + "            CIVIL_ID    ," + "            EXTDATTYPE_CODE    ," +
                                  "            FROM_DATE    ," + "            UNTIL_DATE    ," +
                                  "            VALUE    ," + "            CREATED_BY    ," +
                                  "            CREATED_DATE    ," + "            LAST_UPDATED_BY    ," +
                                  "            LAST_UPDATED_DATE    ," + "            AUDIT_STATUS    ," +
                                  "            TABREC_SERIAL    ," + "            VALUE_DESC    ," +
                                  "            STATUS    ," + "            MIN_CODE    ," +
                                  "            R_CIVIL_ID    " + " from " + "(");
            sql.append("SELECT ex.SERIAL, " + "ex.CIVIL_ID    ," + "ex.EXTDATTYPE_CODE    ," + "ex.FROM_DATE    ," +
                       "ex.UNTIL_DATE    ," + "ex.VALUE    ," + "ex.CREATED_BY    ," + "ex.CREATED_DATE    ," +
                       "ex.LAST_UPDATED_BY    ," + "ex.LAST_UPDATED_DATE    ," + "ex.AUDIT_STATUS    ," +
                       "ex.TABREC_SERIAL    ," + "ex.VALUE_DESC    ," + "ex.STATUS    ," + "ex.MIN_CODE    ," +
                       "ex.R_CIVIL_ID    ," +
                       " emp.REAL_CIVIL_ID,FIRST_NAME,SECOND_NAME,THIRD_NAME,LAST_NAME,FAMILY_NAME,wrk_name ");
            sql.append("FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp " +
                       "            join INF_KWT_CITIZENS_RESIDENTS kwt on kwt.civil_id=emp.real_civil_id " +
                       "            ON ex.CIVIL_ID = emp.CIVIL_ID " + "            join NL_ORG_WORK_CENTERS wrkcent " +
                       "            on wrkcent.MIN_CODE=emp.MIN_CODE and wrkcent.wrk_code=emp.wrk_code ");

            sql.append(" and emp.active_flag= " + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in (" +
                       statusForHire + " ) ");
            sql.append(" AND ex.extdattype_code=");
            sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);
            sql.append(" AND emp.MIN_CODE =" + minCode);

            if (list != null) {
                sql.append(" AND emp.WRK_CODE IN (");
                for (IWorkCentersDTO wcDTO : list) {
                    Long wrkCode = Long.parseLong(((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode());
                    sql.append("").append(wrkCode).append(",");
                }
                sql.deleteCharAt(sql.length() - 1);
                sql.append(")");
            }

            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and emp.WRK_CODE in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and emp.WRK_CODE Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                sql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    sql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        sql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        sql.append(" , ");
                    }
                }
            }
            sql.append(")");
            query = EM().createNativeQuery(sql.toString(), EmployeeExtraDataEntity.class);

            System.out.println(sql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list1 = query.getResultList();


            if (list1 == null || list1.size() == 0)
                throw new NoResultException();

            return list1;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }


    private Long buildSearchQueryCountWithPaging(Long minCode, List<IWorkCentersDTO> list, IBasicDTO basicDTO,
                                                 String statusForHire) throws DataBaseException,
                                                                              SharedApplicationException {


        IEmployeeExtraDataSearchDTO searchDTO = (IEmployeeExtraDataSearchDTO)basicDTO;
        try {


            Query query = null;
            StringBuilder sql = new StringBuilder("SELECT nvl(count(ex.serial),0) ");
            sql.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp ");


            sql.append(" ON ex.CIVIL_ID = emp.CIVIL_ID ");


            if (searchDTO.getCivilId() != null)
                sql.append(" AND  emp.REAL_CIVIL_ID = '" + searchDTO.getCivilId() + "'");

            if (searchDTO.getEmpName() != null && !searchDTO.getEmpName().equals("")) {

                sql.append(" AND  emp.REAL_CIVIL_ID IN ( Select kwt.civil_Id From INF_KWT_CITIZENS_RESIDENTS kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_Name , ' ' ) , CONCAT ( kwt.second_Name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_Name , ' ' ) , CONCAT ( kwt.last_Name , ' ' ) ) ) , kwt.family_Name )",
                                                                                   searchDTO.getEmpName()) + " ) ");

            }

            sql.append(" and active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in (" + statusForHire +
                       " ) ");

            sql.append(" AND emp.MIN_CODE =" + minCode);
            if (searchDTO.getValue() != null && !searchDTO.getValue().equals("") &&
                !searchDTO.getValue().equals(ISystemConstant.VIRTUAL_VALUE.toString())) {
                sql.append(" AND ex.value='" + searchDTO.getValue() + "'");
            }
            if (searchDTO.getExtraDataTypeCode() != null) {
                sql.append(" AND ex.EXTDATTYPE_CODE = " + searchDTO.getExtraDataTypeCode() + "");
            } else {
                sql.append(" AND ex.extdattype_code=");
                sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);
            }
            if (list != null) {
                sql.append(" AND emp.WRK_CODE IN (");
                for (IWorkCentersDTO wcDTO : list) {
                    Long wrkCode = Long.parseLong(((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode());
                    sql.append("").append(wrkCode).append(",");
                }
                sql.deleteCharAt(sql.length() - 1);
                sql.append(")");
            }

            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and emp.WRK_CODE in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and emp.WRK_CODE Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }


            query = EM().createNativeQuery(sql.toString());

            System.out.println(sql.toString());

            Vector<Object> count = (Vector<Object>)query.getSingleResult();
            Long counter = 0L;
            if (count != null && count.size() > 0) {
                try {
                    counter = Long.parseLong(String.valueOf(count.get(0)));
                } catch (NumberFormatException ex) {
                    System.out.println("Number Formate  Exception @ Line 839");
                    counter = 0L;
                }
            }
            return counter;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }


    private Long buildSearchQueryCountWithPaging(Long minCode, List<IWorkCentersDTO> list,
                                                 String statusForHire) throws DataBaseException,
                                                                              SharedApplicationException {
        try {

            Query query = null;


            StringBuilder sql = new StringBuilder("SELECT nvl(count(ex.serial),0) ");
            sql.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA ex INNER JOIN HR_EMP_EMPLOYEES emp ");
            sql.append(" ON ex.CIVIL_ID = emp.CIVIL_ID ");
            sql.append(" and active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in (" + statusForHire +
                       " ) ");
            sql.append(" AND ex.extdattype_code=");
            sql.append(IEMPConstant.EMP_EXTRA_DATA_TYPE_MOH_TRANSFERING);
            sql.append(" AND emp.MIN_CODE =" + minCode);

            if (list != null) {
                sql.append(" AND emp.WRK_CODE IN (");
                for (IWorkCentersDTO wcDTO : list) {
                    Long wrkCode = Long.parseLong(((IWorkCentersEntityKey)wcDTO.getCode()).getWrkCode());
                    sql.append("").append(wrkCode).append(",");
                }
                sql.deleteCharAt(sql.length() - 1);
                sql.append(")");
            }

            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and emp.WRK_CODE in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and emp.WRK_CODE Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }

            query = EM().createNativeQuery(sql.toString());

            System.out.println(sql.toString());

            Vector<Object> count = (Vector<Object>)query.getSingleResult();
            Long counter = 0L;
            if (count != null && count.size() > 0) {
                try {
                    counter = Long.parseLong(String.valueOf(count.get(0)));
                } catch (NumberFormatException ex) {
                    System.out.println("Number Formate  Exception @ Line 839");
                    counter = 0L;
                }
            }
            return counter;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }

    public IEmployeeExtraDataDTO getEmployeeExtraDataDTOForSickVac(EmployeeExtraDataEntity employeeExtraDataEntity) throws DataBaseException,
                                                                                                                           SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = employeeExtraDataEntity.getEmployeesEntity();
            EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
            IKwtCitizensResidentsDTO kwtCitizen = InfDTOFactory.createKwtCitizensResidentsDTO();
            kwtCitizen.setCode(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));
            kwtCitizen.setFirstName(kwtCitizensResidentsEntity.getFirstName());
            kwtCitizen.setSecondName(kwtCitizensResidentsEntity.getSecondName());
            kwtCitizen.setThirdName(kwtCitizensResidentsEntity.getThirdName());
            kwtCitizen.setLastName(kwtCitizensResidentsEntity.getLastName());
            kwtCitizen.setFamilyName(kwtCitizensResidentsEntity.getFamilyName());
            kwtCitizen.setMapCode(kwtCitizensResidentsEntity.getMapCode());

            //        if (kwtCitizensResidentsEntity.getMapCode() != null && kwtCitizensResidentsEntity.getMapCode().length() != 0 &&
            //            kwtCitizensResidentsEntity.getKwMapEntity() != null) {
            //            KwMapEntity kwMapEntity = kwtCitizensResidentsEntity.getKwMapEntity();
            //            IKwMapDTO kwMapDTO = InfDTOFactory.createKwMapDTO(kwMapEntity);
            //            kwtCitizen.setKwMapDTO(kwMapDTO);
            //        }

            kwtCitizen.setKwMapDTO(getKwtMap(kwtCitizensResidentsEntity.getMapCode()));

            IEmployeesDTO emp = EmpDTOFactory.createEmployeesDTO();
            emp.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(kwtCitizensResidentsEntity.getCivilId()));
            emp.setMinistryFileNo(employeesEntity.getMinistryFileNo());

            //emp.setWorkCenterDTO(OrgDTOFactory.createWorkCentersDTO(employeesEntity.getWorkCentersEntity()));
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                emp.setWorkCenterDTO(OrgClientFactory.getWorkCentersClient().getById(wEk));
            } catch (Exception e) {
                throw new RuntimeException();
            }

            //
            //        emp.setMinCode(wEk.getMinCode());
            //        emp.setWorkCenterKey(key);

            emp.setCitizensResidentsDTO(kwtCitizen);

            IEmployeeExtraDataDTO employeeExtraDataDTO = EmpDTOFactory.createEmployeeExtraDataDTO();
            employeeExtraDataDTO.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(employeeExtraDataEntity.getSerial()));
            employeeExtraDataDTO.setValue(employeeExtraDataEntity.getValue());
            employeeExtraDataDTO.setEmployeesDTO(emp);

            return employeeExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    /**
     * Get the MaxId of AbilitiesEntity
     * <br>
     * @return Object
     */

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmployeeExtraDataEntity.findNewId").getSingleResult();
            if (id != null) {
                return id + 1;
            } else {
                return new Long(1);
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
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
                                                                                      SharedApplicationException { //sheka
        try {
            List list =
                EM().createNamedQuery("EmployeeExtraDataEntity.getByCivilIdAndExtraDataTypeCode").setParameter("civilId",
                                                                                                               civilId).setParameter("extraDataTypeCode",
                                                                                                                                     extraDataTypeCode).getResultList();
            if (list != null && list.size() != 0) {
                EmployeeExtraDataEntity entity = null;
                entity = (EmployeeExtraDataEntity)list.get(list.size() - 1);
                return (entity.getValue() != null && entity.getValue().equals(ISystemConstant.ACTIVE_FLAG.toString()));
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    private IKwMapDTO getKwtMap(String mapCode) throws DataBaseException, SharedApplicationException {


        try {
            if (mapCode == null) {
                return null;
            }
            return (IKwMapDTO)InfClientFactory.getKwMapClient().getById(InfEntityKeyFactory.createKwMapEntityKey(mapCode));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            /*e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
            */
        }


    }


    /**
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataForCivil(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("EmployeeExtraDataEntity.getAllEmployeeExtraDataByCivilId").setParameter("civilId",
                                                                                                               civilId).getResultList();
            if (list != null && list.size() != 0) {
                EmployeeExtraDataEntity entity = null;
                entity = (EmployeeExtraDataEntity)list.get(list.size() - 1);
                return (entity.getValue() != null && entity.getValue().equals(ISystemConstant.ACTIVE_FLAG.toString()));
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmployeeExtraDataDTO> getAllEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            ArrayList<IEmployeeExtraDataDTO> arrayList = new ArrayList<IEmployeeExtraDataDTO>();
            List list =
                EM().createNamedQuery("EmployeeExtraDataEntity.getAllEmployeeExtraDataByCivilId").setParameter("civilId",
                                                                                                               civilId).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (Object employeeExtraData : list) {
                arrayList.add(EmpDTOFactory.createEmployeeExtraDataDTO((EmployeeExtraDataEntity)employeeExtraData));
            }
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            ArrayList<IEmployeeExtraDataDTO> arrayList = new ArrayList<IEmployeeExtraDataDTO>();
            List list =
                EM().createNamedQuery("EmployeeExtraDataEntity.getAllEmployeeExtraDataByCivilId").setParameter("civilId",
                                                                                                               civilId).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (Object employeeExtraData : list) {
                arrayList.add(EmpEntityConverter.getEmployeeExtraDataDTO2((EmployeeExtraDataEntity)employeeExtraData));
            }
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmployeeExtraDataDTO getEmpExtraDataByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            EmployeeExtraDataEntity entity =
                (EmployeeExtraDataEntity)EM().createNamedQuery("EmployeeExtraDataEntity.getEmpExtraDataByRealCivilId").setParameter("realCivilId",
                                                                                                                                    realCivilId).getSingleResult();
            if (entity == null)
                return null;

            IEmployeeExtraDataDTO employeeExtraDataDTO = EmpDTOFactory.createEmployeeExtraDataDTO(entity);

            return employeeExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /*
     * new method added by M.abdelsabour
     * to be used to get Employeee ExtraData for mov
     * */

    public IBasicDTO getEmployeeExtraDataForMov(Long civilId) throws DataBaseException, SharedApplicationException {
        try {

            Query query = EM().createNamedQuery("EmployeeExtraDataEntity.getEmployeeExtraDataForMov");
            query.setParameter("status", IEMPConstants.Active_Status_Extra_Data);
            query.setParameter("civilId", civilId);
            EmployeeExtraDataEntity extraDtaEntity = (EmployeeExtraDataEntity)query.getSingleResult();
            IEmployeeExtraDataDTO dto =
                EmpDTOFactory.createEmployeeExtraDataDTO((EmployeeExtraDataEntity)extraDtaEntity);
            return dto;


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /*
     * new method added by M.abdelsabour
     * to update EmpExtraData for moving employee
     *
     * */

    public Boolean updateEmpExtraDataForMov(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException {

        try {
            IEmployeeExtraDataDTO empExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraDataDTO;

            EmployeeExtraDataEntity employeeExtraDataEntity =
                EM().find(EmployeeExtraDataEntity.class, (IEmployeeExtraDataEntityKey)empExtraDataDTO.getCode());
            if (empExtraDataDTO.getUntilDate() != null)
                employeeExtraDataEntity.setUntilDate(empExtraDataDTO.getUntilDate());
            if (empExtraDataDTO.getStatus() != null)
                employeeExtraDataEntity.setStatus(empExtraDataDTO.getStatus());
            doUpdate(employeeExtraDataEntity);

            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * @author Black Horse [E.Saber]
     * @since 15/09/2015
     * @param realCivilId
     * @param movingDate
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public int updateStatusByMovingDate(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(movingDate);
            cal.add(Calendar.DATE, -1);
            movingDate = new Date(cal.getTime().getTime());

            StringBuilder st = new StringBuilder("UPDATE HR_EMP_EMPLOYEE_EXTRA_DATA");
            st.append(" SET STATUS = 0 , UNTIL_DATE =");
            st.append(" TO_DATE ( '" + movingDate + "' , 'yyyy/mm/dd' )");
            st.append(" WHERE CIVIL_ID IN (");
            st.append(" SELECT CIVIL_ID FROM HR_EMP_EMPLOYEES WHERE REAL_CIVIL_ID = ");
            st.append(realCivilId + ")");
            st.append(" and EXTDATTYPE_CODE = 1 ");
            System.out.println("updateStatusByMovingDate :: " + st);
            // updated by A.AGAMY for data audit
            //Query query = EM().createNativeQuery(st.toString());
            Connection con = getConnectionForUpdate();
            PreparedStatement ps = con.prepareCall(st.toString());
            return ps.executeUpdate();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public Boolean isDateOverlaped(IBasicDTO _dto) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeeExtraDataDTO dto = (IEmployeeExtraDataDTO)_dto;
            Long civilId = ((IEmployeesEntityKey)dto.getEmployeesDTO().getCode()).getCivilId();
            Long extraDataTypeCode =
                ((IEmpExtraDataTypesEntityKey)dto.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();

            Long count =
                (Long)EM().createNamedQuery("EmployeeExtraDataEntity.getOverlapedEmployeeExtraData").setParameter("civilId",
                                                                                                                  civilId).setParameter("extraDataTypeCode",
                                                                                                                                        extraDataTypeCode).setParameter("status",
                                                                                                                                                                        ISystemConstant.ACTIVE_FLAG).setParameter("fromDate",
                                                                                                                                                                                                                  dto.getFromDate()).setParameter("untilDate",
                                                                                                                                                                                                                                                  dto.getUntilDate()).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                              "true").getSingleResult();
            if (count.equals(0L))
                return Boolean.FALSE;

            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean isDateOverlapedForEdit(IBasicDTO _dto) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeeExtraDataDTO dto = (IEmployeeExtraDataDTO)_dto;
            Long civilId = ((IEmployeesEntityKey)dto.getEmployeesDTO().getCode()).getCivilId();
            Long extraDataTypeCode =
                ((IEmpExtraDataTypesEntityKey)dto.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
            Long serial = ((IEmployeeExtraDataEntityKey)dto.getCode()).getSerial();
            System.out.println("EmployeeExtraDataDAO isDateOverlapedForEdit civilId = "+civilId+" , extraDataTypeCode = "+extraDataTypeCode
                               +" , serial = "+serial+" , dto.getFromDate() = "+dto.getFromDate()+" , dto.getUntilDate() = "+(dto.getUntilDate() == null ? " NULL " : dto.getUntilDate()) );
            
            Query query = EM().createNamedQuery("EmployeeExtraDataEntity.getOverlapedEmployeeExtraDataForEdit");
            query.setParameter("civilId",civilId) ;
            query.setParameter("serial", serial);
            query.setParameter("extraDataTypeCode", extraDataTypeCode);
            query.setParameter("status", ISystemConstant.ACTIVE_FLAG);
            query.setParameter("fromDate", dto.getFromDate());
            query.setParameter("untilDate", dto.getUntilDate());
            query.setHint("toplink.refresh", "true");
            Long count = (Long)query.getSingleResult();
            System.out.println("EmployeeExtraDataDAO isDateOverlapedForEdit count = "+count+" , extraDataTypeCode = "+extraDataTypeCode+" , serial = "+serial);
            if (count.equals(0L))
                return Boolean.FALSE;

            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    //new added and updated

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVac(Long minCode, List<IWorkCentersDTO> list,
                                                                      String statusForHire) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();

            List resultList = buildSearchQueryList(minCode, list, statusForHire);
            for (Object employeeExtraDataEntity : resultList) {
                EmployeeExtraDataEntity targetEmployeeExtraDataEntity = null;
                try {
                    targetEmployeeExtraDataEntity = (EmployeeExtraDataEntity)employeeExtraDataEntity;
                } catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
                arrayList.add(getCustomEmployeeExtraDataDTOForSickVac(targetEmployeeExtraDataEntity));

            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    public IEmployeeExtraDataDTO getCustomEmployeeExtraDataDTOForSickVac(EmployeeExtraDataEntity employeeExtraDataEntity) throws DataBaseException,
                                                                                                                                 SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = employeeExtraDataEntity.getEmployeesEntity();
            EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
            IKwtCitizensResidentsDTO kwtCitizen = InfDTOFactory.createKwtCitizensResidentsDTO();
            kwtCitizen.setCode(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));
            kwtCitizen.setFirstName(kwtCitizensResidentsEntity.getFirstName());
            kwtCitizen.setSecondName(kwtCitizensResidentsEntity.getSecondName());
            kwtCitizen.setThirdName(kwtCitizensResidentsEntity.getThirdName());
            kwtCitizen.setLastName(kwtCitizensResidentsEntity.getLastName());
            kwtCitizen.setFamilyName(kwtCitizensResidentsEntity.getFamilyName());
            IEmployeesDTO emp = EmpDTOFactory.createEmployeesDTO();
            emp.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(kwtCitizensResidentsEntity.getCivilId()));
            emp.setMinistryFileNo(employeesEntity.getMinistryFileNo());
            if (employeesEntity.getWorkCentersEntity() != null)
                emp.getWorkCenterDTO().setName(employeesEntity.getWorkCentersEntity().getWrkName());

            emp.setCitizensResidentsDTO(kwtCitizen);

            IEmployeeExtraDataDTO employeeExtraDataDTO = EmpDTOFactory.createEmployeeExtraDataDTO();
            employeeExtraDataDTO.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(employeeExtraDataEntity.getSerial()));
            employeeExtraDataDTO.setValue(employeeExtraDataEntity.getValue());
            employeeExtraDataDTO.setEmployeesDTO(emp);

            return employeeExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    /**
     * get EmpExtraData By CivilId and extraDataType
     * @param civilId
     * @param extraDataTypeCode
     * @return IEmployeeExtraDataDTO
     */
    public IEmployeeExtraDataDTO getByExtraDataTypeAndCivilId(Long civilId,
                                                              Long extraDataTypeCode) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("EmployeeExtraDataEntity.getByCivilIdAndExtraDataTypeCode");
            query.setParameter("civilId", civilId);
            query.setParameter("extraDataTypeCode", extraDataTypeCode);
            List list = query.getResultList();
            if (list != null && list.size() != 0) {
                EmployeeExtraDataEntity entity = null;
                entity = (EmployeeExtraDataEntity)list.get(0);
                IEmployeeExtraDataDTO dto = EmpDTOFactory.createEmployeeExtraDataDTO(entity);
                return dto;
            }
            return null;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean updateEmpExtraDataForMer(IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException {

        try {
            IEmployeeExtraDataDTO empExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraDataDTO;

            EmployeeExtraDataEntity employeeExtraDataEntity =
                EM().find(EmployeeExtraDataEntity.class, (IEmployeeExtraDataEntityKey)empExtraDataDTO.getCode());

            if (employeeExtraDataEntity == null) {
                throw new ItemNotFoundException();
            }
            employeeExtraDataEntity.setFromDate(empExtraDataDTO.getFromDate());
            employeeExtraDataEntity.setUntilDate(empExtraDataDTO.getUntilDate());
            employeeExtraDataEntity.setValue(empExtraDataDTO.getValue());
            employeeExtraDataEntity.setValueDesc(empExtraDataDTO.getValueDesc() );
            employeeExtraDataEntity.setCscBookNo(empExtraDataDTO.getCscBookNo() );
            employeeExtraDataEntity.setCscBookDate(empExtraDataDTO.getCscBookDate() );
            employeeExtraDataEntity.setComments(empExtraDataDTO.getComments() );
            
            doUpdate(employeeExtraDataEntity);

            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByName(Long civilId,
                                                                  String typeName) throws DataBaseException,
                                                                                          SharedApplicationException {

        try {
            ArrayList arrayList = new ArrayList();

            StringBuffer query =
                new StringBuffer("select o from EmployeeExtraDataEntity o where o.realCivilId = :civilId and ");
            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.empExtraDataTypesEntity.extdattypeName",
                                                                             typeName));


            List<EmployeeExtraDataEntity> list =
                EM().createQuery(query.toString()).setParameter("civilId", civilId).getResultList();

            for (EmployeeExtraDataEntity dto : list) {
                arrayList.add(EmpDTOFactory.createEmployeeExtraDataDTO(dto));
            }

            if (arrayList.size() == 0) {
                throw new ItemNotFoundException();
            }

            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    public List<IBasicDTO> getByCivilIdAndExtraDataTypeCodeLst(Long civilId, String extraDataTypeCodeLst,
                                                               Long status) throws DataBaseException,
                                                                                   SharedApplicationException {
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        StringBuilder queryString = new StringBuilder("");
        queryString.append(" SELECT *");
        queryString.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA EXTRA");
        queryString.append(" WHERE EXTRA.CIVIL_ID = " + civilId);
        queryString.append(" AND EXTRA.EXTDATTYPE_CODE in (" + extraDataTypeCodeLst + ")");
        if (status != null) {
            queryString.append(" AND EXTRA.STATUS = " + status);
        }
        System.out.println("getByCivilIdAndExtraDataTypeCodeLst -> " + queryString.toString());
        List<EmployeeExtraDataEntity> list =
            EM().createNativeQuery(queryString.toString(), EmployeeExtraDataEntity.class).getResultList();
        for (EmployeeExtraDataEntity employeeExtraData : list) {
            arrayList.add(EmpDTOFactory.createEmployeeExtraDataDTO(employeeExtraData));
        }
        return arrayList;
    }


    public String getNationalityNumberByCivilId(Long civilId) throws DataBaseException, SharedApplicationException {
        String value = null;
        try {
           
            StringBuilder queryString = new StringBuilder("");
            queryString.append(" SELECT EXTRA.VALUE ");
            queryString.append(" FROM HR_EMP_EMPLOYEE_EXTRA_DATA EXTRA");
            queryString.append(" WHERE EXTRA.CIVIL_ID = " + civilId);
            queryString.append(" AND EXTRA.EXTDATTYPE_CODE = 306 ");
            queryString.append(" AND EXTRA.STATUS =  1 ");

            System.out.println("getNationalityNumberByCivilId -> " + queryString.toString());
            Vector vector = (Vector)EM().createNativeQuery(queryString.toString()).getSingleResult();
            if (vector != null && vector.size() > 0) {
                value = (String)vector.get(0);
            }

           
        } catch (Exception e) {
            System.out.println("@@@ EmployeeExtraDataDAO : getNationalityNumberByCivilId : not found");
//            e = wrapIntoDataBaseExceptionForNationality(e);
//            if (e instanceof DataBaseException) {
//                throw (DataBaseException)e;
//            } else {
//                throw (SharedApplicationException)e;
//            }           
        }
        return value;

    }
    
    
    protected Exception wrapIntoDataBaseExceptionForNationality(Exception e) {
       // e.printStackTrace();
        if (e instanceof DataBaseException || e instanceof SharedApplicationException) {
            return e;
        } else {
            return new DataBaseException(com.beshara.csc.sharedutils.business.util.SharedUtils.getExceptionMessage(e));
        }
    }
    
    public Date getFirstHireDateFromSocialInsurance (Long civilId)
    {
            
            StringBuilder queryString = new StringBuilder(""); 
            queryString.append("select FROM_DATE from HR_EMP_EMPLOYEE_EXTRA_DATA");
            queryString.append(" where CIVIL_ID = "+civilId) ;
            queryString.append(" and EXTDATTYPE_CODE =22 " );
            Query query = EM().createNativeQuery(queryString.toString());
           Vector vec = (Vector)query.getSingleResult();
            return new  java.sql.Date(((Timestamp)vec.get(0)).getTime());
}
    

}
