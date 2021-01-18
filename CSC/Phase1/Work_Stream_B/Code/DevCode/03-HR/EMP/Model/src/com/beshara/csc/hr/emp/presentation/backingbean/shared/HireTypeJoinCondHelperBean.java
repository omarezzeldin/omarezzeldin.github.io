package com.beshara.csc.hr.emp.presentation.backingbean.shared;

import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.joincond.JoinCondHelperBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.presentation.backingbean.hiretypes.HireTypesMainData;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class HireTypeJoinCondHelperBean extends JoinCondHelperBean{
    public HireTypeJoinCondHelperBean() {
        super();
    }
    
    public static HireTypeJoinCondHelperBean getInstance() {
        return new HireTypeJoinCondHelperBean();
    }

    @Override
    public String getContainerBeanName() {
        return HireTypesMainData.BEAN_NAME;
    }
    
    @Override
    public IJoinCondTargetDTO getTarget() {
        return (IJoinCondTargetDTO)JSFHelper.getValue(HireTypesMainData.CONDITION_TARGET_BINDING);
    }

    @Override
    public void applyJoin() throws SharedApplicationException, DataBaseException {
        EmpClientFactory.getHireTypesClient().joinCondition(getTarget());
    }
    
    @Override
    protected void applyCancel() throws SharedApplicationException, DataBaseException {
        EmpClientFactory.getHireTypesClient().cancelCondition(getTarget());
    }

    @Override
    protected IJoinCondDTO createCondDTO() {
        return EmpDTOFactory.createHireTypeConditionsDTO();
    }
}
