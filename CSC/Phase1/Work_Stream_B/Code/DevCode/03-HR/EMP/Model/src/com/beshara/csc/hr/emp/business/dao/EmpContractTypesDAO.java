package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.persistence.Query;


public class EmpContractTypesDAO extends EmpBaseDAO {
    public EmpContractTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpContractTypesDAO();
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("EmpContractTypesEntity.findNewId");
            Long id = (Long)query.getSingleResult();
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


}
