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
 * This Class Manipulate the Persistence Methods of DegreeData Entity.
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
@NamedQuery(name = "DegreeDataEntity.findAll", query = "select o from DegreeDataEntity o"),
@NamedQuery(name = "DegreeDataEntity.findNewId", query = "select MAX(o.civilId) from DegreeDataEntity o")
})
@Table(name = "HR_EMP_DEGREE_DATA")

public class DegreeDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

@Id
@Column(name="CIVIL_ID", nullable=true)
private Long civilId;
@Column(name="FINCADRE_CODE", nullable=true)
private Long fincadreCode;
@Column(name="FINGROUP_CODE", nullable=true)
private Long fingroupCode;
@Column(name="FINDEGREE_CODE", nullable=true)
private Long findegreeCode;
@Column(name="DEGREE_DATE", nullable=true)
private Timestamp degreeDate;
@Column(name="STATUS", nullable=true)
private Long status;
 

    /**
     * DegreeDataEntity Default Constructor
     */    
    public DegreeDataEntity() {
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
public Long getFincadreCode() {
   return fincadreCode;
}
/**
* @return Long
*/
public Long getFingroupCode() {
   return fingroupCode;
}
/**
* @return Long
*/
public Long getFindegreeCode() {
   return findegreeCode;
}
/**
* @return Timestamp
*/
public Timestamp getDegreeDate() {
   return degreeDate;
}
/**
* @return Long
*/
public Long getStatus() {
   return status;
}


/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param fincadreCode
*/
public void setFincadreCode(Long fincadreCode) {
   this.fincadreCode=fincadreCode;
}
/**
* @param fingroupCode
*/
public void setFingroupCode(Long fingroupCode) {
   this.fingroupCode=fingroupCode;
}
/**
* @param findegreeCode
*/
public void setFindegreeCode(Long findegreeCode) {
   this.findegreeCode=findegreeCode;
}
/**
* @param degreeDate
*/
public void setDegreeDate(Timestamp degreeDate) {
   this.degreeDate=degreeDate;
}
/**
* @param status
*/
public void setStatus(Long status) {
   this.status=status;
}


}
