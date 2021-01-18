package com.beshara.csc.hr.emp.business.dto;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IEmpMinEdtypesDTO extends IEmpDTO {

    /**
     * @return Long
     */
    Long getMinCode();

    /**
     * @return Long
     */
    Long getExtdattypeCode();


    /**
     * @param minCode
     */
    public void setMinCode(Long minCode);

    /**
     * @param extdattypeCode
     */
    public void setExtdattypeCode(Long extdattypeCode);

    public void setEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO empExtraDataTypesDTO) ;
    
    public IEmpExtraDataTypesDTO getEmpExtraDataTypesDTO();

    public void setMinName(String minName) ;
    
    public String getMinName();

}
