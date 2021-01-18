package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.dao.DAOFactoryUtil;
//import com.beshara.base.dataauditing.Auditable;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.JoinCondHelper;
import com.beshara.csc.hr.emp.business.dao.HireTypeConditionsDAO;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class HireTypeConditionsHelper extends JoinCondHelper {
    HireTypeConditionsDAO hireTypeConditionsDAO;

    private HireTypeConditionsHelper(IJoinCondTargetDTO target) {
        super(target);
        init();
    }

    private HireTypeConditionsHelper(IJoinCondTargetDTO target, String transactionName) {
        super(target, transactionName);
        init();
    }

    public static HireTypeConditionsHelper getInstanceForJoin(IJoinCondTargetDTO target, String transactionName) {
        return new HireTypeConditionsHelper(target, transactionName);
    }

    public static HireTypeConditionsHelper getInstanceForCancel(IJoinCondTargetDTO target) {
        return new HireTypeConditionsHelper(target);
    }

    private void init() {
        hireTypeConditionsDAO = (HireTypeConditionsDAO)DAOFactoryUtil.getInstance(HireTypeConditionsEntity.class);
    }

    @Override
    protected IJoinCondDTO getActiveCond() throws DataBaseException, SharedApplicationException {
        return (IJoinCondDTO)hireTypeConditionsDAO.getActiveConditionRelation(Long.parseLong(getTarget().getCode().getKey()));
    }

    @Override
    //@Auditable
    protected void updateCond(IJoinCondDTO cond) throws ItemNotFoundException {
        try {
            hireTypeConditionsDAO.update(cond);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //@Auditable
    protected IJoinCondDTO addCond(IJoinCondDTO cond) throws ItemNotFoundException {
        try {
            return (IJoinCondDTO)hireTypeConditionsDAO.add(cond);
        }  catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
