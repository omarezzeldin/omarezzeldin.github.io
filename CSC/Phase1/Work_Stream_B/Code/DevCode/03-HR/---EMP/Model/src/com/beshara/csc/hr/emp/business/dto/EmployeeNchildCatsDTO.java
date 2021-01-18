package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmployeeNchildCatsEntity;

import java.sql.Timestamp;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class EmployeeNchildCatsDTO extends BasicDTO implements IEmployeeNchildCatsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long nchildsCatCode;
    private String nchildsCatName;
    private Timestamp nfromDate;
    private Long nrValue;
    /** 
     * EmployeeNchildCatsDTO Default Constructor */
    public EmployeeNchildCatsDTO() {        super();
    }    /** 
     * @param employeeNchildCatsEntity 
     */
    public EmployeeNchildCatsDTO(EmployeeNchildCatsEntity employeeNchildCatsEntity) {        this.civilId = employeeNchildCatsEntity.getCivilId();
        this.nchildsCatCode = employeeNchildCatsEntity.getNchildsCatCode();
        this.nchildsCatName = employeeNchildCatsEntity.getNchildsCatName();
        this.nfromDate = employeeNchildCatsEntity.getNfromDate();
        this.nrValue = employeeNchildCatsEntity.getNrValue();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getNchildsCatCode() {        return nchildsCatCode;
    }    /** 
     * @return String 
     */
    public String getNchildsCatName() {        return nchildsCatName;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getNfromDate() {        return nfromDate;
    }    /** 
     * @return Long 
     */
    public Long getNrValue() {        return nrValue;
    }    /** 
     * @param civilId 
     */
    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    /** 
     * @param nchildsCatCode 
     */
    public void setNchildsCatCode(Long nchildsCatCode) {        this.nchildsCatCode = nchildsCatCode;
    }    /** 
     * @param nchildsCatName 
     */
    public void setNchildsCatName(String nchildsCatName) {        this.nchildsCatName = nchildsCatName;
    }    /** 
     * @param nfromDate 
     */
    public void setNfromDate(Timestamp nfromDate) {        this.nfromDate = nfromDate;
    }    /** 
     * @param nrValue 
     */
    public void setNrValue(Long nrValue) {        this.nrValue = nrValue;
    }}
