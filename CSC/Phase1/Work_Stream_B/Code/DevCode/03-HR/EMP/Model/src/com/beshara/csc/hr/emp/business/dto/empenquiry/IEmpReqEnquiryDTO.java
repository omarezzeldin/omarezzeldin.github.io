package com.beshara.csc.hr.emp.business.dto.empenquiry;


import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpDTO;

import java.sql.Timestamp;

public interface IEmpReqEnquiryDTO extends IEmpDTO {

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();

    public void setFullName(String fullName);

    public String getFullName();

    public void setHireTypeName(String hireType);

    public String getHireTypeName();

    public void setCandMinName(String candMinName);

    public String getCandMinName();

    public void setConcernedMinCode(Long concernedMinCode);

    public Long getConcernedMinCode();

    public void setConcernedMinName(String concernedMinName);

    public String getConcernedMinName();

    public void setRequestArrivalDate(Timestamp requestArrivalDate);

    public Timestamp getRequestArrivalDate();

    public void setHireStageName(String status);

    public String getHireStageName() ;

    public void setMinCode(Long minCode);

    public Long getMinCode();
    
    public void setPagingRequestDTO(IPagingRequestDTO pagingRequestDTO);

    public IPagingRequestDTO getPagingRequestDTO();
    
}
