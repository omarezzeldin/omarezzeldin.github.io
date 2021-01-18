package com.beshara.csc.esv.emp.dao;


import com.beshara.csc.esv.emp.entity.EmpRequestEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.persistence.EntityManager;

public class EmpRequestDAO {

    private EntityManager em;

    public EmpRequestDAO(EntityManager em) {
        super();
        this.em = em;
    }

    public EmpRequestEntity getEmpRequestById(Long requestNo) throws SharedApplicationException, DataBaseException {
        EmpRequestEntity empRequestEntity =
            (EmpRequestEntity)em.createNativeQuery("SELECT * FROM \"_empAllInfo\" WHERE \"_requestNo\" = " +
                                 String.valueOf(requestNo),EmpRequestEntity.class).setHint("eclipselink.refresh", "true").getSingleResult();

        if (empRequestEntity == null) {
            throw new ItemNotFoundException();
        }
        return empRequestEntity;
    }
}
