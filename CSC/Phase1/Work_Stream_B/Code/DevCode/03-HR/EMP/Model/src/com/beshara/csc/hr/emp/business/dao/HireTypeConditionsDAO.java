package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.util.List;

public class HireTypeConditionsDAO extends EmpBaseDAO {
    public HireTypeConditionsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HireTypeConditionsDAO();
    }
     
    
    public IBasicDTO getActiveConditionRelation(Long prmTypeCode) throws DataBaseException,SharedApplicationException {
        
        try{
            List<HireTypeConditionsEntity> objList =
                EM().createNamedQuery("HireTypeConditionsEntity.listRelatedHireCondition").setParameter("status",
                                                                                                      ISystemConstant.ACTIVE_FLAG).setParameter("hireTypeCode",
                                                                                                                                                prmTypeCode).getResultList();
            if (objList != null && objList.size() > 0) {
                return EmpEntityConverter.getHireTypeConditionsDTO(objList.get(0));
            }
            return null;   
        }
        catch(Exception e){
                        e = wrapIntoDataBaseException(e);
                        if(e instanceof DataBaseException ){
                            throw (DataBaseException)e;
                        }else {
                            throw (SharedApplicationException)e;
                        }
                    }
    }
}
