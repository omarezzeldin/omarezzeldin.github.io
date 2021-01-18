package com.beshara.csc.hr.emp.presentation.backingbean.hirestages;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireStagesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.Calendar;


public class HireStagesListBean extends LookUpBaseBean {

    private boolean viewStatus;

    public HireStagesListBean() {
        setPageDTO(EmpDTOFactory.createHireStagesDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createHireStagesDTO());
        setClient((IHireStagesClient)EmpClientFactory.getHireStagesClient());
        //        setDivSearch("divSearchCustomized");
        setContentDiv("hirestagecontent.jsp");
        setSaveSortingState(true);
        setSingleSelection(true);
    }


    public void search() throws DataBaseException, NoResultException {
        try {
            if (this.getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
    }

    @Override
    public void showEditDiv() {
        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            viewStatus = ((IHireStagesDTO)getSelectedDTOS().get(0)).getStatus() == 1 ? true : false;
            super.showEditDiv();
        }
    }

    public void editHireStage() {
        try {
            IHireStagesDTO hireStageDTO = (IHireStagesDTO)getSelectedPageDTO();
            if (hireStageDTO.getStatus() != null) {
                Calendar cal = Calendar.getInstance();
                if (hireStageDTO.getStatus().equals(1L)) {
                    if (viewStatus == false) {
                        hireStageDTO.setStatus(0L);
                        hireStageDTO.setUntilDate(new java.sql.Date(cal.getTime().getTime()));
                    }
                } else if (hireStageDTO.getStatus().equals(0L)) {
                    if (viewStatus == true) {
                        hireStageDTO.setStatus(1L);
                        hireStageDTO.setFromDate(new java.sql.Date(cal.getTime().getTime()));
                        hireStageDTO.setUntilDate(null);
                    }
                }
            }
            getClient().update(getSelectedPageDTO());
            if (isSearchMode()) {
                search();
            } else {
                getAll();
            }
            if (getSelectedPageDTO() != null && getSelectedPageDTO().getCode() != null) {
                getHighlightedDTOS().add(getSelectedPageDTO());
                getPageIndex((String)getSelectedPageDTO().getCode().getKey());
                setSelectedRadio("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewStatus(boolean viewStatus) {
        this.viewStatus = viewStatus;
    }

    public boolean isViewStatus() {
        return viewStatus;
    }
}

