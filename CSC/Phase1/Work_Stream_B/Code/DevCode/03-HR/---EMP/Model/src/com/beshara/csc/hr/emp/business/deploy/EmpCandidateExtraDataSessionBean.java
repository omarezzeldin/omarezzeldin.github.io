package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateExtraDataDAO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless(name = "EmpCandidateExtraDataSession", mappedName = "Emp-Model-EmpCandidateExtraDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpCandidateExtraDataSessionBean extends EmpBaseSessionBean implements EmpCandidateExtraDataSession {
    public EmpCandidateExtraDataSessionBean() {
        super();
    }
    protected EmpCandidateExtraDataDAO DAO() {
        return (EmpCandidateExtraDataDAO)super.DAO();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCandidateExtraDataEntity.class;
    }
    
    public String extraDataValueForType(IRequestInfoDTO ri,Long candCode, Long candSeq, Long extraDataType) throws DataBaseException,
    SharedApplicationException{
        return DAO().extraDataValueForType(candCode, candSeq, extraDataType);
        }
}
