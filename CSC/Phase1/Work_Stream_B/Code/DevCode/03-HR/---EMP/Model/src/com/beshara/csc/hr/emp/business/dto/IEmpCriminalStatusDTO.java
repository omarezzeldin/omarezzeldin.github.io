package com.beshara.csc.hr.emp.business.dto;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IEmpCriminalStatusDTO extends IEmpDTO {

    /**
     * @return String
     */
    String getCrmstatusCode();

    /**
     * @return String
     */
    String getName();


    /**
     * @param crmstatusCode
     */
    public void setCrmstatusCode(String crmstatusCode);

    /**
     * @param name
     */
    public void setName(String name);


}
