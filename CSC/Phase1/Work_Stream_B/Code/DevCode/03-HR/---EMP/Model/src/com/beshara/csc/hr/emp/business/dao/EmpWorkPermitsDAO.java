package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpWorkPermitsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpWorkPermitsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpWorkPermitsEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpWorkPermitsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class EmpWorkPermitsDAO extends EmpBaseDAO {
    public EmpWorkPermitsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpWorkPermitsDAO();
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpWorkPermitsEntity> list = EM().createNamedQuery("EmpWorkPermitsEntity.findAll").getResultList();

            //        for (EmpWorkPermitsEntity empWorkPermitsEntity : list) {
            //            arrayList.add((IBasicDTO)EmpDTOFactory.createEmpWorkPermitsDTO(empWorkPermitsEntity));
            //        }
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
    
    
    public Long findNewId() throws DataBaseException, SharedApplicationException {

        try {
            Long id = (Long)EM().createNamedQuery("EmpWorkPermitsEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
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


    public EmpWorkPermitsDTO add(IBasicDTO empWorkPermitsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            EmpWorkPermitsEntity empWorkPermitsEntity = new EmpWorkPermitsEntity();
            EmpWorkPermitsDTO empWorkPermitsDTO = (EmpWorkPermitsDTO)empWorkPermitsDTO1;
            empWorkPermitsEntity.setSerial(findMaxId());
            empWorkPermitsEntity.setMinCode(empWorkPermitsDTO.getMinCode());
            empWorkPermitsEntity.setCivilId(empWorkPermitsDTO.getCivilId());
            empWorkPermitsEntity.setRealCivilId(empWorkPermitsDTO.getRealCivilId());
            empWorkPermitsEntity.setFromDate(empWorkPermitsDTO.getFromDate());
            empWorkPermitsEntity.setUntilDate(empWorkPermitsDTO.getUntilDate());
            empWorkPermitsEntity.setRealCivilId(empWorkPermitsDTO.getRealCivilId());
            empWorkPermitsEntity.setCreatedBy(empWorkPermitsDTO.getCreatedBy());
            empWorkPermitsEntity.setCreatedDate(empWorkPermitsDTO.getCreatedDate());
            empWorkPermitsEntity.setAuditStatus(empWorkPermitsDTO.getAuditStatus());
            empWorkPermitsEntity.setTabrecSerial(empWorkPermitsDTO.getTabrecSerial());
            doAdd(empWorkPermitsEntity);
            empWorkPermitsDTO.setCode(EmpEntityKeyFactory.createEmpWorkPermitsEntityKey(empWorkPermitsEntity.getSerial()));
            return empWorkPermitsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public Boolean update(IBasicDTO empWorkPermitsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            EmpWorkPermitsDTO empWorkPermitsDTO = (EmpWorkPermitsDTO)empWorkPermitsDTO1;

            EmpWorkPermitsEntity empWorkPermitsEntity =
                EM().find(EmpWorkPermitsEntity.class, (IEmpWorkPermitsEntityKey)empWorkPermitsDTO.getCode());
            empWorkPermitsEntity.setCivilId(empWorkPermitsDTO.getCivilId());
            empWorkPermitsEntity.setRealCivilId(empWorkPermitsDTO.getRealCivilId());
            empWorkPermitsEntity.setFromDate(empWorkPermitsDTO.getFromDate());
            empWorkPermitsEntity.setUntilDate(empWorkPermitsDTO.getUntilDate());
            empWorkPermitsEntity.setMinCode(empWorkPermitsDTO.getMinCode());
            empWorkPermitsEntity.setRealCivilId(empWorkPermitsDTO.getRealCivilId());
            empWorkPermitsEntity.setCreatedBy(empWorkPermitsDTO.getCreatedBy());
            empWorkPermitsEntity.setCreatedDate(empWorkPermitsDTO.getCreatedDate());
            empWorkPermitsEntity.setAuditStatus(empWorkPermitsDTO.getAuditStatus());
           // empWorkPermitsEntity.setTabrecSerial(empWorkPermitsDTO.getTabrecSerial());

            doUpdate(empWorkPermitsEntity);
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

    public Boolean remove(IBasicDTO empWorkPermitsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            EmpWorkPermitsDTO empWorkPermitsDTO = (EmpWorkPermitsDTO)empWorkPermitsDTO1;

            EmpWorkPermitsEntity empWorkPermitsEntity =
                EM().find(EmpWorkPermitsEntity.class, (IEmpWorkPermitsEntityKey)empWorkPermitsDTO.getCode());

            if (empWorkPermitsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(empWorkPermitsEntity);
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


    public List<IEmpWorkPermitsDTO> getEmpWorkPermitsByCivilId(Long civilId) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpWorkPermitsEntity> list =
                EM().createNamedQuery("EmpWorkPermitsEntity.getEmpWorkPermitsByCivilId").setParameter("civilId",
                                                                                                      civilId).getResultList();
            for (EmpWorkPermitsEntity ent : list)
                arrayList.add(EmpDTOFactory.createEmpWorkPermitsDTO(ent));

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


    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByMinCode(Long minCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpWorkPermitsEntity> list =
                EM().createNamedQuery("EmpWorkPermitsEntity.getAllEmpPermitsByMinCode").setParameter("minCode",
                                                                                                     minCode).getResultList();
            for (EmpWorkPermitsEntity ent : list)
                arrayList.add(EmpDTOFactory.createEmpWorkPermitsDTO(ent));

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

    public List<IEmpWorkPermitsDTO> getEmpWorkPermitsByCivilIdAndMinCode(Long civilId,
                                                                         Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpWorkPermitsEntity> list =
                EM().createNamedQuery("EmpWorkPermitsEntity.getEmpWorkPermitsByCivilIdAndMinCode").setParameter("civilId",
                                                                                                                civilId).setParameter("minCode",
                                                                                                                                      minCode).getResultList();
            for (EmpWorkPermitsEntity ent : list)
                arrayList.add(EmpDTOFactory.createEmpWorkPermitsDTO(ent));

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


    public Boolean checkConflictForworkPermits(Long civilId, Date fromDate, Date untilDate,Long serial) throws DataBaseException,
                                                                                                   SharedApplicationException {

        try {
            StringBuilder qryStr = new StringBuilder();

            qryStr.append(" select count (1) ");

            qryStr.append(" from HR_EMP_WORK_PERMITS ");
            qryStr.append("  where  ");
            qryStr.append("  civil_id = " + civilId);

            qryStr.append("  and  ( to_date('" + fromDate + "','yyyy-mm-dd') <= until_date )   ");

            qryStr.append("  and  ( to_date('" + untilDate + "','yyyy-mm-dd') >= from_date  ) ");
            if(serial !=null){
                qryStr.append("  and serial <> " + serial);
            }
            
            System.out.println(" checkConflictForworkPermits " + qryStr.toString());
            Query q = EM().createNativeQuery(qryStr.toString());
            q.setHint("toplink.refresh", "true");
            Vector row = (Vector)q.getSingleResult();
            Long result = Long.valueOf(row.get(0).toString());
            if (result > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }


}

