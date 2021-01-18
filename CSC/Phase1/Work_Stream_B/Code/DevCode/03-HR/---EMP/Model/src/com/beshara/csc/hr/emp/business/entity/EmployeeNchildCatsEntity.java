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
 * This Class Manipulate the Persistence Methods of EmployeeNchildCats Entity.
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
@NamedQuery(name = "EmployeeNchildCatsEntity.findAll", query = "select o from EmployeeNchildCatsEntity o"),
@NamedQuery(name = "EmployeeNchildCatsEntity.findNewId", query = "select MAX(o.civilId) from EmployeeNchildCatsEntity o")
})
@Table(name = "HR_EMP_EMPLOYEE_NCHILD_CATS")
public class EmployeeNchildCatsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
@Column(name="CIVIL_ID", nullable=false)
private Long civilId;
@Column(name="NCHILDS_CAT_CODE", nullable=false)
private Long nchildsCatCode;
@Column(name="NCHILDS_CAT_NAME", nullable=false)
private String nchildsCatName;
@Column(name="NFROM_DATE", nullable=false)
private Timestamp nfromDate;
@Column(name="NR_VALUE", nullable=true)
private Long nrValue;
 

    /**
     * EmployeeNchildCatsEntity Default Constructor
     */    
    public EmployeeNchildCatsEntity() {
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
public Long getNchildsCatCode() {
   return nchildsCatCode;
}
/**
* @return String
*/
public String getNchildsCatName() {
   return nchildsCatName;
}
/**
* @return Timestamp
*/
public Timestamp getNfromDate() {
   return nfromDate;
}
/**
* @return Long
*/
public Long getNrValue() {
   return nrValue;
}


/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param nchildsCatCode
*/
public void setNchildsCatCode(Long nchildsCatCode) {
   this.nchildsCatCode=nchildsCatCode;
}
/**
* @param nchildsCatName
*/
public void setNchildsCatName(String nchildsCatName) {
   this.nchildsCatName=nchildsCatName;
}
/**
* @param nfromDate
*/
public void setNfromDate(Timestamp nfromDate) {
   this.nfromDate=nfromDate;
}
/**
* @param nrValue
*/
public void setNrValue(Long nrValue) {
   this.nrValue=nrValue;
}


}
