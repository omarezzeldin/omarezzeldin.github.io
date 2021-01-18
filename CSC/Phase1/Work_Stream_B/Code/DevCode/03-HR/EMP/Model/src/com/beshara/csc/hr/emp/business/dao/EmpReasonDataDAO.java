package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpReasonDataDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpReasonDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpReasonDataEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmpReasonTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpReasonDataEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpReasonTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class EmpReasonDataDAO extends EmpBaseDAO {
    public EmpReasonDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpReasonDataDAO();
    }

    /**
     * @return List
     */
    @Override
    public List<IEmpReasonDataDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpReasonDataEntity> list = EM().createNamedQuery("EmpReasonDataEntity.findAll").getResultList();
            for (EmpReasonDataEntity empReasonData : list) {
                arrayList.add(EmpDTOFactory.createEmpReasonDataDTO(empReasonData));
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
     * Create a New EmpReasonDataEntity
     * @param empReasonDataDTO
     * @return EmpReasonDataDTO
     */

    public IEmpReasonDataDTO add(IEmpReasonDataDTO empReasonDataDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            EmpReasonDataEntity empReasonDataEntity = new EmpReasonDataEntity();

            IEmpReasonDataEntityKey empReasonKey = (IEmpReasonDataEntityKey)empReasonDataDTO.getCode();

            empReasonDataEntity.setRestypeCode(empReasonKey.getRestypeCode());
            empReasonDataEntity.setResdatSerial(empReasonKey.getResdatSerial());

            empReasonDataEntity.setResdatDesc(empReasonDataDTO.getName());
            empReasonDataEntity.setCreatedBy(empReasonDataDTO.getCreatedBy());
            empReasonDataEntity.setCreatedDate(empReasonDataDTO.getCreatedDate());
            empReasonDataEntity.setAuditStatus(empReasonDataDTO.getAuditStatus());
            empReasonDataEntity.setTabrecSerial(empReasonDataDTO.getTabrecSerial());

            if (empReasonDataDTO.getEmpReasonTypesCode() != null) {
                Long code = empReasonDataDTO.getEmpReasonTypesCode();
                IEmpReasonTypesEntityKey key = EmpEntityKeyFactory.createEmpReasonTypesEntityKey(code);
                EmpReasonTypesEntity empReasonTypesEntity = EM().find(EmpReasonTypesEntity.class, key);
                if (empReasonTypesEntity == null)
                    throw new ItemNotFoundException();
                empReasonDataEntity.setEmpReasonTypesEntity(empReasonTypesEntity);
            }
            doAdd(empReasonDataEntity);
            // Set PK after creation
            return empReasonDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmpReasonDataDTO addRejReasonCode(IEmpReasonDataDTO empReasonDataDTO1) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            EmpReasonDataEntity empReasonDataEntity = new EmpReasonDataEntity();

            IEmpReasonDataDTO empReasonDataDTO = (IEmpReasonDataDTO)empReasonDataDTO1;
            Long code = IEMPConstants.REJECTION_REASON_CODE;
            Long resdatSerial = queryEmpReasonDataEntityFindNewId(code);
            empReasonDataDTO.setCode(new EmpReasonDataEntityKey(Long.valueOf(empReasonDataDTO.getEmpReasonTypesDTO().getCode().getKey()),
                                                                resdatSerial));
            empReasonDataEntity.setRestypeCode(((IEmpReasonDataEntityKey)empReasonDataDTO.getCode()).getRestypeCode());
            empReasonDataEntity.setResdatSerial(resdatSerial);

            empReasonDataEntity.setResdatDesc(empReasonDataDTO.getName());
            empReasonDataEntity.setCreatedBy(empReasonDataDTO.getCreatedBy());
            empReasonDataEntity.setCreatedDate(empReasonDataDTO.getCreatedDate());
            empReasonDataEntity.setAuditStatus(empReasonDataDTO.getAuditStatus());
            empReasonDataEntity.setTabrecSerial(empReasonDataDTO.getTabrecSerial());

            if (empReasonDataDTO.getEmpReasonTypesDTO() != null) {
                EmpReasonTypesEntity empReasonTypesEntity =
                    EM().find(EmpReasonTypesEntity.class, empReasonDataDTO.getEmpReasonTypesDTO().getCode());
                if (empReasonTypesEntity == null)
                    throw new ItemNotFoundException();
                empReasonDataEntity.setEmpReasonTypesEntity(empReasonTypesEntity);
            }
            doAdd(empReasonDataEntity);
            // Set PK after creation
            return empReasonDataDTO;
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
     * Update an Existing EmpReasonDataEntity
     * @param empReasonDataDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO empReasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpReasonDataDTO empReasonDataDTO = (IEmpReasonDataDTO)empReasonDataDTO1;

            EmpReasonDataEntity empReasonDataEntity =
                EM().find(EmpReasonDataEntity.class, (IEmpReasonDataEntityKey)empReasonDataDTO.getCode());

            empReasonDataEntity.setRestypeCode(((IEmpReasonDataEntityKey)empReasonDataDTO.getCode()).getRestypeCode());
            empReasonDataEntity.setResdatSerial(((IEmpReasonDataEntityKey)empReasonDataDTO.getCode()).getResdatSerial());
            empReasonDataEntity.setResdatDesc(empReasonDataDTO.getName());
            empReasonDataEntity.setCreatedBy(empReasonDataDTO.getCreatedBy());
            empReasonDataEntity.setCreatedDate(empReasonDataDTO.getCreatedDate());
            empReasonDataEntity.setLastUpdatedBy(empReasonDataDTO.getLastUpdatedBy());
            empReasonDataEntity.setLastUpdatedDate(empReasonDataDTO.getLastUpdatedDate());
            empReasonDataEntity.setAuditStatus(empReasonDataDTO.getAuditStatus());
            empReasonDataEntity.setTabrecSerial(empReasonDataDTO.getTabrecSerial());
            if (empReasonDataDTO.getEmpReasonTypesDTO() != null) {
                EmpReasonTypesEntity empReasonTypesEntity =
                    EM().find(EmpReasonTypesEntity.class, (IEmpReasonTypesEntityKey)empReasonDataDTO.getEmpReasonTypesDTO().getCode());
                if (empReasonTypesEntity == null)
                    throw new ItemNotFoundException();
                empReasonDataEntity.setEmpReasonTypesEntity(empReasonTypesEntity);
            }
            doUpdate(empReasonDataEntity);
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
     * Remove an Existing EmpReasonDataEntity
     * @param empReasonDataDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO empReasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpReasonDataDTO empReasonDataDTO = (IEmpReasonDataDTO)empReasonDataDTO1;

            EmpReasonDataEntity empReasonDataEntity =
                EM().find(EmpReasonDataEntity.class, (IEmpReasonDataEntityKey)empReasonDataDTO.getCode());

            if (empReasonDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(empReasonDataEntity);
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
     * Get EmpReasonDataEntity By Primary Key
     * @param id
     * @return EmpReasonDataDTO
     */
    @Override
    public IEmpReasonDataDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpReasonDataEntityKey id = (IEmpReasonDataEntityKey)id1;

            EmpReasonDataEntity empReasonDataEntity = EM().find(EmpReasonDataEntity.class, id);
            if (empReasonDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpReasonDataDTO empReasonDataDTO = EmpDTOFactory.createEmpReasonDataDTO(empReasonDataEntity);
            return empReasonDataDTO;
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
     * Search for EmpReasonDataEntity
     * <br>
     * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return super.search(basicDTO);
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
     * @param restypeCode
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getByTypeCode(Long restypeCode) throws DataBaseException,
                                                                  SharedApplicationException { //sheka
        try {
            List list =
                EM().createNamedQuery("EmpReasonDataEntity.getByTypeCode").setParameter("restypeCode", restypeCode).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (Object entity : list) {
                listDTO.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)entity));
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


    /**
     * Get the MaxId of AbilitiesEntity
     * <br>
     * @return Object
     */
    public Long queryEmpReasonDataEntityFindNewId(Long restypeCode) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            Long id =
                (Long)EM().createNamedQuery("EmpReasonDataEntity.getMaxId").setParameter("restypeCode", restypeCode).getSingleResult();
            if (id != null) {
                return id + 1;
            } else {
                return new Long(0);
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


    public List<IBasicDTO> getSpecialReasons(Long restypeCode, Long vactypeCode) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            String queryStr =
                " select hr_emp_reason_data.* from hr_emp_reason_data, hr_vac_type_rej_reasons  where " + "hr_emp_reason_data.resdat_serial = hr_vac_type_rej_reasons.resdat_serial and " +
                "hr_emp_reason_data.restype_code = hr_vac_type_rej_reasons.restype_code " +
                "and hr_emp_reason_data.restype_code=?  and hr_vac_type_rej_reasons.vactype_code= ?";


            Query q = EM().createNativeQuery(queryStr, EmpReasonDataEntity.class);
            q.setParameter(1, restypeCode).setParameter(2, vactypeCode);

            List list = q.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (Object row : list) {
                arrayList.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)row));
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

    public List<IBasicDTO> getSpecialReasonsForOthersVac(Long restypeCode, Long vactypeCode) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            String queryStr =
                "select hr_emp_reason_data.* from hr_emp_reason_data " + "where hr_emp_reason_data.restype_code=?  and " +
                "resdat_serial  not in " +
                "(select resdat_serial  from hr_vac_type_rej_reasons where  vactype_code= ?) " +
                "and resdat_serial   in " +
                "(select resdat_serial  from hr_vac_type_rej_reasons where  vactype_code<> ?) ";


            Query q = EM().createNativeQuery(queryStr, EmpReasonDataEntity.class);
            q.setParameter(1, restypeCode).setParameter(2, vactypeCode).setParameter(3, vactypeCode);

            List list = q.getResultList();
            if (list == null || list.size() == 0) {
                return null;
            }
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (Object row : list) {
                arrayList.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)row));
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


    public List<IBasicDTO> getPublicReasons(Long restypeCode) throws DataBaseException, SharedApplicationException {
        try {
            String queryStr =
                "select hr_emp_reason_data.* from hr_emp_reason_data where hr_emp_reason_data.resdat_serial not in(" +
                " select hr_vac_type_rej_reasons.resdat_serial from hr_emp_reason_data, hr_vac_type_rej_reasons  where " +
                " hr_emp_reason_data.resdat_serial = hr_vac_type_rej_reasons.resdat_serial and " +
                "hr_emp_reason_data.restype_code = hr_vac_type_rej_reasons.restype_code" +
                ") and hr_emp_reason_data.restype_code=? ";


            Query q = EM().createNativeQuery(queryStr, EmpReasonDataEntity.class);
            q.setParameter(1, restypeCode);

            List list = q.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (Object row : list) {
                arrayList.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)row));
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
     *
     * @param restypeCode
     * @param vactypeCode
     * @return list of EmpReasonDataEntity representing rejection reason for certain vacation type
     * (all vacation rejection reasons not assigned to any vactypecode union vactypecode reasons)
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getEmpReasonsForVacType(Long restypeCode, Long vactypeCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            String queryStr =
                "select hr_emp_reason_data.* from hr_emp_reason_data where hr_emp_reason_data.resdat_serial not in(" +
                " select hr_vac_type_rej_reasons.resdat_serial from hr_emp_reason_data, hr_vac_type_rej_reasons  where " +
                " hr_emp_reason_data.resdat_serial = hr_vac_type_rej_reasons.resdat_serial and " +
                "hr_emp_reason_data.restype_code = hr_vac_type_rej_reasons.restype_code" +
                ") and hr_emp_reason_data.restype_code=? union " +
                " select hr_emp_reason_data.* from hr_emp_reason_data, hr_vac_type_rej_reasons  where " +
                "hr_emp_reason_data.resdat_serial = hr_vac_type_rej_reasons.resdat_serial and " +
                "hr_emp_reason_data.restype_code = hr_vac_type_rej_reasons.restype_code " +
                "and hr_emp_reason_data.restype_code=?  and hr_vac_type_rej_reasons.vactype_code= ?";


            Query q = EM().createNativeQuery(queryStr, EmpReasonDataEntity.class);
            q.setParameter(1, restypeCode).setParameter(2, restypeCode).setParameter(3, vactypeCode);

            List list = q.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (Object row : list) {
                arrayList.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)row));
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

    public List<IBasicDTO> checkDublicateName(String name) throws DataBaseException, SharedApplicationException {
        List<EmpReasonDataEntity> list =
            EM().createNamedQuery("EmpReasonDataEntity.checkDublicateName").setParameter("name", name).getResultList();
        ArrayList arrayList = new ArrayList();
        for (EmpReasonDataEntity entity : list) {
            arrayList.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)entity));
        }
        return arrayList;
    }

    public List<IBasicDTO> checkDublicateNameAndType(Long code, String name) throws DataBaseException,
                                                                                    SharedApplicationException {
        List<EmpReasonDataEntity> list =
            EM().createNamedQuery("EmpReasonDataEntity.checkDublicateNameAndType").setParameter("code",
                                                                                                code).setParameter("name",
                                                                                                                   name).getResultList();
        ArrayList arrayList = new ArrayList();
        for (EmpReasonDataEntity entity : list) {
            arrayList.add(EmpDTOFactory.createEmpReasonDataDTO((EmpReasonDataEntity)entity));
        }
        return arrayList;
    }

    ///Added by Mohamed sayed mohamed at 30-3-2015

    public int proceedGenReasonAdd(IBasicDTO empReasonDataDTO1) throws DataBaseException, SharedApplicationException {

        int res = checkDublicateGenReason(empReasonDataDTO1.getName());
        if (res > 0) {
            return -1;
        }
        res = checkDublicatePrivateReason(empReasonDataDTO1.getName());
        if (res > 0) {
            return res;
        }
        return 0;
    }

    private int checkDublicateGenReason(String name) {
        String strQuery =
            "SELECT count(*) " + "  FROM hr_emp_reason_data " + " WHERE  hr_emp_reason_data.restype_code = 4 and RESDAT_DESC ='" +
            name + "' and   hr_emp_reason_data.resdat_serial  Not IN (" +
            "          SELECT hr_vac_type_rej_reasons.resdat_serial " + "            FROM   hr_vac_type_rej_reasons " +
            "          ) ";


        Query query = EM().createNativeQuery(strQuery);
        //query.setParameter("name", name);
        Vector resultVector = (Vector)query.getSingleResult();
        return Integer.parseInt(resultVector.get(0).toString());

    }

    private int checkDublicatePrivateReason(String name) {
        String strQuery =
            "SELECT count(*) " + "  FROM hr_emp_reason_data " + " WHERE  hr_emp_reason_data.restype_code = 4 and RESDAT_DESC = '" +
            name + "'  and   hr_emp_reason_data.resdat_serial   IN (" +
            "          SELECT hr_vac_type_rej_reasons.resdat_serial " + "            FROM   hr_vac_type_rej_reasons " +
            "          ) ";


        Query query = EM().createNativeQuery(strQuery);

        Vector resultVector = (Vector)query.getSingleResult();
        return Integer.parseInt(resultVector.get(0).toString());

    }

    public int convertPrivateToGen(IBasicDTO empReasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            String strsql =
                "delete from hr_vac_type_rej_reasons where resdat_serial in (select  hr_emp_reason_data.resdat_serial from  hr_emp_reason_data where RESDAT_DESC = '" +
                empReasonDataDTO1.getName() + "'  )";

            //updated by Ismael for dataAudit requirements

            //Query query = EM().createNativeQuery(strsql);
            Connection conn = getConnectionForUpdate();
            PreparedStatement ps;

            ps = conn.prepareCall(strsql);
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

    /**
     *Hany Omar
     * @param resDatSerial
     * @return
     */
    public Boolean isRejReasonUsedBefore(Long resDatSerial) {
        String strQuery =
            "SELECT COUNT(1) FROM HR_VAC_EMPLOYEE_VACATIONS WHERE REFUSE_RESTYPE_CODE =" + IEMPConstants.REJECTION_REASON +
            "AND REFUSE_RESDAT_SERIAL = " + resDatSerial;

        Query query = EM().createNativeQuery(strQuery);
        Vector vector = (Vector)query.getSingleResult();
        String result = vector.get(0).toString();
        int returnValue = Integer.parseInt(result);

        return returnValue > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Hany Omar
     * check if general reason redundant or not
     * @param resDatDesc
     * @param resDatSerial
     * @return
     */
    public Boolean isGenRejReasonRedundant(String resDatDesc, Long resDatSerial) {

        String subQuery = "SELECT 1 FROM HR_VAC_TYPE_REJ_REASONS RR WHERE ER.RESDAT_SERIAL = RR.RESDAT_SERIAL";

        String strQuery =
            "SELECT count(1) FROM HR_EMP_REASON_DATA ER WHERE ER.RESTYPE_CODE = 4 AND ER.RESDAT_DESC = '" +
            resDatDesc + "' " + " AND ER.RESDAT_SERIAL <> " + resDatSerial + " AND NOT EXISTS (" + subQuery + " )";

        System.out.println(strQuery);
        Query query = EM().createNativeQuery(strQuery);
        Vector vector = (Vector)query.getSingleResult();
        String result = vector.get(0).toString();
        int returnValue = Integer.parseInt(result);

        return returnValue > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean isGenRejReasonRedundanInPrivateReasons(String resDatDesc) {
        String strQuery =
            "SELECT COUNT(*) FROM HR_EMP_REASON_DATA   ER WHERE ER.RESTYPE_CODE = 4 AND ER.RESDAT_DESC = '" +
            resDatDesc + "' " +
            "AND  EXISTS (SELECT 1 FROM HR_VAC_TYPE_REJ_REASONS RR WHERE ER.RESDAT_SERIAL = RR.RESDAT_SERIAL )";

        Query query = EM().createNativeQuery(strQuery);
        Vector vector = (Vector)query.getSingleResult();
        String result = vector.get(0).toString();
        int returnValue = Integer.parseInt(result);

        return returnValue > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean isPrivateRejReasonRedundan(String resDatDesc, Long resDatSerial) {
        String strQuery =
            "  SELECT COUNT(*) FROM HR_EMP_REASON_DATA   ER WHERE ER.RESTYPE_CODE = 4 AND ER.RESDAT_DESC ='" +
            resDatDesc + "'" + " AND ER.RESDAT_SERIAL <> " + resDatSerial;


        System.out.println(strQuery);
        Query query = EM().createNativeQuery(strQuery);
        Vector vector = (Vector)query.getSingleResult();
        String result = vector.get(0).toString();
        int returnValue = Integer.parseInt(result);

        return returnValue > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

}
