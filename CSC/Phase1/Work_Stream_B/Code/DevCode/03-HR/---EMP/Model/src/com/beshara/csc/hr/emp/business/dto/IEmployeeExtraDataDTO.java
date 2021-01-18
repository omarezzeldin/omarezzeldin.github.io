package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Date;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Fitiany    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
public interface IEmployeeExtraDataDTO extends IEmpDTO {

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setValue(String value);

    public String getValue();

    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setEmployeesDTO(IBasicDTO employeesDTO);

    public IBasicDTO getEmployeesDTO();

    public void setEmpExtraDataTypesDTO(IBasicDTO empExtraDataTypesDTO);

    public IBasicDTO getEmpExtraDataTypesDTO();

    public void setValueDesc(String valueDesc);

    public String getValueDesc();

    public void setStatus(Long status);

    public Long getStatus();

    public void setMinCode(Long minCode);

    public Long getMinCode();

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();
    
    public void setCscBookNo(String cscBookNo);

    public String getCscBookNo();

    public void setCscBookDate(Date cscBookDate);

    public Date getCscBookDate();

    public void setComments(String comments);

    public String getComments();
}

