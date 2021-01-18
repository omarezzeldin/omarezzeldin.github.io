package com.beshara.csc.hr.emp.presentation.backingbean.hirestatus;

import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireStatusClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class HireStatusListBean extends LookUpBaseBean{
    
    public HireStatusListBean() {
        setPageDTO(EmpDTOFactory.createHireStatusDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createHireStatusDTO());
        setClient((IHireStatusClient)EmpClientFactory.getHireStatusClient());
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

