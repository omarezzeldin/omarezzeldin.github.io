package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmployeeHchildCatsEntity;

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
public class EmployeeHchildCatsDTO extends BasicDTO implements IEmployeeHchildCatsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long hchildsCatCode;
    private String hchildsCatName;
    private Timestamp hfromDate;
    private Long hrValue;
    /** 
     * EmployeeHchildCatsDTO Default Constructor */
    public EmployeeHchildCatsDTO() {        super();
    }    /** 
     * @param employeeHchildCatsEntity 
     */
    public EmployeeHchildCatsDTO(EmployeeHchildCatsEntity employeeHchildCatsEntity) {        this.civilId = employeeHchildCatsEntity.getCivilId();
        this.hchildsCatCode = employeeHchildCatsEntity.getHchildsCatCode();
        this.hchildsCatName = employeeHchildCatsEntity.getHchildsCatName();
        this.hfromDate = employeeHchildCatsEntity.getHfromDate();
        this.hrValue = employeeHchildCatsEntity.getHrValue();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getHchildsCatCode() {        return hchildsCatCode;
    }    /** 
     * @return String 
     */
    public String getHchildsCatName() {        return hchildsCatName;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getHfromDate() {        return hfromDate;
    }    /** 
     * @return Long 
     */
    public Long getHrValue() {        return hrValue;
    }    /** 
     * @param civilId 
     */
    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    /** 
     * @param hchildsCatCode 
     */
    public void setHchildsCatCode(Long hchildsCatCode) {        this.hchildsCatCode = hchildsCatCode;
    }    /** 
     * @param hchildsCatName 
     */
    public void setHchildsCatName(String hchildsCatName) {        this.hchildsCatName = hchildsCatName;
    }    /** 
     * @param hfromDate 
     */
    public void setHfromDate(Timestamp hfromDate) {        this.hfromDate = hfromDate;
    }    /** 
     * @param hrValue 
     */
    public void setHrValue(Long hrValue) {        this.hrValue = hrValue;
    }}
