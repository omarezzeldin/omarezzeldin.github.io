package com.beshara.csc.hr.emp.business.dto;


import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IEmpMoiWsCriminalDTO extends IEmpDTO {

    /**
     * @return Long
     */
    Long getCrmWsSerial();

    /**
     * @return Long
     */
    Long getUserCode();

    /**
     * @return Long
     */
    Long getCivilId();

    /**
     * @return String
     */
    String getCrmstatusCode();

    /**
     * @return Timestamp
     */
    Timestamp getLastLoginDate();


    /**
     * @param crmWsSerial
     */
    public void setCrmWsSerial(Long crmWsSerial);

    /**
     * @param userCode
     */
    public void setUserCode(Long userCode);

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId);

    /**
     * @param crmstatusCode
     */
    public void setCrmstatusCode(String crmstatusCode);

    /**
     * @param lastLoginDate
     */
    public void setLastLoginDate(Timestamp lastLoginDate);


}
