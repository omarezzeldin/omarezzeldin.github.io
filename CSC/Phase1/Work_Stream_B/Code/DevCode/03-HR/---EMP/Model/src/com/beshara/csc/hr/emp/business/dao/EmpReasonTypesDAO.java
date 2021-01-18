package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpReasonTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpReasonTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpReasonTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class EmpReasonTypesDAO extends EmpBaseDAO {
    public EmpReasonTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpReasonTypesDAO();
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {

        try {
            Long id = (Long)EM().createNamedQuery("EmpReasonTypesEntity.findNewId").getSingleResult();
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

    public IEmpReasonTypesDTO add(IBasicDTO empReasonTypesDTO1) throws DataBaseException, SharedApplicationException {

        try {
            EmpReasonTypesEntity empReasonTypesEntity = new EmpReasonTypesEntity();
            IEmpReasonTypesDTO empReasonTypesDTO = (IEmpReasonTypesDTO)empReasonTypesDTO1;

            Long resTypeCode = findMaxId();
            empReasonTypesEntity.setRestypeCode(resTypeCode);
            empReasonTypesDTO.setCode(EmpEntityKeyFactory.createEmpReasonTypesEntityKey(resTypeCode));

            empReasonTypesEntity.setRestypeName(empReasonTypesDTO.getName());
            empReasonTypesEntity.setCreatedBy(empReasonTypesDTO.getCreatedBy());
            empReasonTypesEntity.setCreatedDate(empReasonTypesDTO.getCreatedDate());
            empReasonTypesEntity.setAuditStatus(empReasonTypesDTO.getAuditStatus());
            empReasonTypesEntity.setTabrecSerial(empReasonTypesDTO.getTabrecSerial());


            this.doAdd(empReasonTypesEntity);
            // Set PK after creation
            return empReasonTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    public List<IEmpReasonTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpReasonTypesEntity> list = EM().createNamedQuery("EmpReasonTypesEntity.findAll").getResultList();
            for (EmpReasonTypesEntity empReasonTypes : list) {
                arrayList.add(EmpDTOFactory.createEmpReasonTypesDTO(empReasonTypes));
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

    public Boolean update(IBasicDTO empReasonTypesDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpReasonTypesDTO empReasonTypesDTO = (IEmpReasonTypesDTO)empReasonTypesDTO1;

            EmpReasonTypesEntity empReasonTypesEntity =
                EM().find(EmpReasonTypesEntity.class, (IEmpReasonTypesEntityKey)empReasonTypesDTO.getCode());

            empReasonTypesEntity.setRestypeCode(((IEmpReasonTypesEntityKey)empReasonTypesDTO.getCode()).getRestypeCode());
            empReasonTypesEntity.setRestypeName(empReasonTypesDTO.getName());
            empReasonTypesEntity.setCreatedBy(empReasonTypesDTO.getCreatedBy());
            empReasonTypesEntity.setCreatedDate(empReasonTypesDTO.getCreatedDate());
            empReasonTypesEntity.setLastUpdatedBy(empReasonTypesDTO.getLastUpdatedBy());
            empReasonTypesEntity.setLastUpdatedDate(empReasonTypesDTO.getLastUpdatedDate());
            empReasonTypesEntity.setAuditStatus(empReasonTypesDTO.getAuditStatus());
            empReasonTypesEntity.setTabrecSerial(empReasonTypesDTO.getTabrecSerial());
            //updated by Ismael for dataAudit requirements
            doUpdate(empReasonTypesEntity);
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

    public Boolean remove(IBasicDTO empReasonTypesDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpReasonTypesDTO empReasonTypesDTO = (IEmpReasonTypesDTO)empReasonTypesDTO1;

            EmpReasonTypesEntity empReasonTypesEntity =
                EM().find(EmpReasonTypesEntity.class, (IEmpReasonTypesEntityKey)empReasonTypesDTO.getCode());

            if (empReasonTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(empReasonTypesEntity);
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

    public IEmpReasonTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpReasonTypesEntityKey id = (IEmpReasonTypesEntityKey)id1;

            EmpReasonTypesEntity empReasonTypesEntity = EM().find(EmpReasonTypesEntity.class, id);
            if (empReasonTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpReasonTypesDTO empReasonTypesDTO = EmpDTOFactory.createEmpReasonTypesDTO(empReasonTypesEntity);
            return empReasonTypesDTO;
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
        return super.search(basicDTO);
    }
}
