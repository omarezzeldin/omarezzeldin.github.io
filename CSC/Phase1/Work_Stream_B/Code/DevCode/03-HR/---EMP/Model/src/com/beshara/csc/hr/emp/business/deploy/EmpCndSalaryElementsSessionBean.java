package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.hr.emp.business.dao.EmpCndSalaryElementsDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "EmpCndSalaryElementsSession", mappedName = "Emp-Model-EmpCndSalaryElementsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpCndSalaryElementsSessionBean extends EmpBaseSessionBean implements EmpCndSalaryElementsSession {

    /**
     * JobsSession Default Constructor */
    public EmpCndSalaryElementsSessionBean() {
        super();
    }

    public IEmpCndSalaryElementsDTO getByCandCode(IRequestInfoDTO requestInfo,
                                                  IEntityKey candidateCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().getByCandCode(candidateCode);
    }
    //@Auditable
    public IEmpCndSalaryElementsDTO add(IRequestInfoDTO requestInfo,
                                        IBasicDTO empCndSalaryElementsDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        IEmpCndSalaryElementsDTO dto = (IEmpCndSalaryElementsDTO)empCndSalaryElementsDTO1;
        if (dto.getFromDate() == null) {
            dto.setFromDate(SharedUtils.getSqlDate());
        }
        return DAO().add(dto);
    }

    protected EmpCndSalaryElementsDAO DAO() {
        return (EmpCndSalaryElementsDAO)super.DAO();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCndSalaryElementsEntity.class;
    }

    public List<IEmpCandidateExtraDataDTO> getBounsForCandidae(IRequestInfoDTO requestInfo,
                                                               IEmpCandidatesEntityKey ek) throws DataBaseException,
                                                                                                  SharedApplicationException {
        return DAO().getBounsForCandidae(ek);

    }

}
