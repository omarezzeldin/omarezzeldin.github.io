package com.beshara.csc.hr.emp.presentation.backingbean.documenttypes;

import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IDocumentTypesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.inf.business.client.IInfDocumentTypesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class DocumentTypesListBean extends LookUpBaseBean {


    public DocumentTypesListBean() {
        //        setPageDTO(EmpDTOFactory.createDocumentTypesDTO());
        //        super.setSelectedPageDTO(EmpDTOFactory.createDocumentTypesDTO());
        //        setClient((IDocumentTypesClient)EmpClientFactory.getDocumentTypesClient());

        //setDivSearch("divSearchCustomized");

        setPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
        super.setSelectedPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
        setClient((IInfDocumentTypesClient)InfClientFactory.getInfDocumentTypesClient());
        setSaveSortingState(true);
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
}

