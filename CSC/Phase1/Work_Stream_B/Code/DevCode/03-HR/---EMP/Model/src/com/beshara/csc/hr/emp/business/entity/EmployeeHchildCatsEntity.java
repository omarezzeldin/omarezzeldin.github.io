package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of EmployeeHchildCats Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
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

@Entity
@NamedQueries({
@NamedQuery(name = "EmployeeHchildCatsEntity.findAll", query = "select o from EmployeeHchildCatsEntity o"),
@NamedQuery(name = "EmployeeHchildCatsEntity.findNewId", query = "select MAX(o.civilId) from EmployeeHchildCatsEntity o")
})
@Table(name = "HR_EMP_EMPLOYEE_HCHILD_CATS")
public class EmployeeHchildCatsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
@Column(name="CIVIL_ID", nullable=false)
private Long civilId;
@Column(name="HCHILDS_CAT_CODE", nullable=false)
private Long hchildsCatCode;
@Column(name="HCHILDS_CAT_NAME", nullable=false)
private String hchildsCatName;
@Column(name="HFROM_DATE", nullable=false)
private Timestamp hfromDate;
@Column(name="HR_VALUE", nullable=true)
private Long hrValue;
 

    /**
     * EmployeeHchildCatsEntity Default Constructor
     */    
    public EmployeeHchildCatsEntity() {
    }


/**
* @return Long
*/
public Long getCivilId() {
   return civilId;
}
/**
* @return Long
*/
public Long getHchildsCatCode() {
   return hchildsCatCode;
}
/**
* @return String
*/
public String getHchildsCatName() {
   return hchildsCatName;
}
/**
* @return Timestamp
*/
public Timestamp getHfromDate() {
   return hfromDate;
}
/**
* @return Long
*/
public Long getHrValue() {
   return hrValue;
}


/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param hchildsCatCode
*/
public void setHchildsCatCode(Long hchildsCatCode) {
   this.hchildsCatCode=hchildsCatCode;
}
/**
* @param hchildsCatName
*/
public void setHchildsCatName(String hchildsCatName) {
   this.hchildsCatName=hchildsCatName;
}
/**
* @param hfromDate
*/
public void setHfromDate(Timestamp hfromDate) {
   this.hfromDate=hfromDate;
}
/**
* @param hrValue
*/
public void setHrValue(Long hrValue) {
   this.hrValue=hrValue;
}


}
