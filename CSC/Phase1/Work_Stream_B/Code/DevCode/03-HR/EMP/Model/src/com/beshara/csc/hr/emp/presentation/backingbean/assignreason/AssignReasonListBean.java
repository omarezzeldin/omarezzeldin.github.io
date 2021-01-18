package com.beshara.csc.hr.emp.presentation.backingbean.assignreason;

import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IAssignReasonsClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class AssignReasonListBean  extends LookUpBaseBean{
    
    public AssignReasonListBean() {
        setPageDTO(EmpDTOFactory.createAssignReasonsDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createAssignReasonsDTO());
        setClient((IAssignReasonsClient)EmpClientFactory.getAssignReasonsClient());
//        setDivSearch("divSearchCustomized");
        setSaveSortingState(true);
    }
    
    public void search() throws DataBaseException, NoResultException {
        try{
            if (this.getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        }
        catch (Exception e) {
          e.printStackTrace();
          setSearchMode(true);
          setMyTableData(new ArrayList());
        }        
    }
}
