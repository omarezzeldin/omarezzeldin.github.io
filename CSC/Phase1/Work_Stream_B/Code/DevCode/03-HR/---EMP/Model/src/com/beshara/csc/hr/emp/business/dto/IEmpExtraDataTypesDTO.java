package com.beshara.csc.hr.emp.business.dto;


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
public interface IEmpExtraDataTypesDTO extends IEmpDTO {

    public String getExtdattypeName();

    public Long getStatus();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setExtdattypeName(String extdattypeName);

    public void setStatus(Long status);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);
    
    public void setParameterCode(Long parameterCode);

    public Long getParameterCode();
    
}

