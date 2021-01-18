package com.beshara.csc.hr.emp.business.dto;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */


public class EmpMinEdtypesDTO extends EmpDTO implements IEmpMinEdtypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long minCode;
    private Long extdattypeCode;
    private IEmpExtraDataTypesDTO empExtraDataTypesDTO ;
    private String  minName ;


    /**
     * EmpMinEdtypesDTO Default Constructor
     */
    public EmpMinEdtypesDTO() {
        super();
    }


    /**
     * @return Long
     */
    public Long getMinCode() {
        return minCode;
    }

    /**
     * @return Long
     */
    public Long getExtdattypeCode() {
        return extdattypeCode;
    }


    /**
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     * @param extdattypeCode
     */
    public void setExtdattypeCode(Long extdattypeCode) {
        this.extdattypeCode = extdattypeCode;
    }


    public void setEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO empExtraDataTypesDTO) {
        this.empExtraDataTypesDTO = empExtraDataTypesDTO;
    }

    public IEmpExtraDataTypesDTO getEmpExtraDataTypesDTO() {
        return empExtraDataTypesDTO;
    }

    public void setMinName(String minName) {
        this.minName = minName;
    }

    public String getMinName() {
        return minName;
    }
}

