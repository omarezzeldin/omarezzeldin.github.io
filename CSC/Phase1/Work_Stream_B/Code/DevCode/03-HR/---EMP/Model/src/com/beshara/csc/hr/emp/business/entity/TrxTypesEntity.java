package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of TrxTypes Entity.
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
@NamedQuery(name = "TrxTypesEntity.findAll", query = "select o from TrxTypesEntity o"),
@NamedQuery(name = "TrxTypesEntity.findNewId", query = "select MAX(o.trxtypeCode) from TrxTypesEntity o")
})
@Table(name = "HR_EMP_TRX_TYPES")
@IdClass(ITrxTypesEntityKey.class)

public class TrxTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


@Id
@Column(name="TRXTYPE_CODE", nullable=false)
private Long trxtypeCode;
@Column(name="TRXTYPE_NAME", nullable=false)
private String trxtypeName;
@Column(name="CREATED_BY", nullable=true)
private Long createdBy;
@Column(name="CREATED_DATE", nullable=true)
private Timestamp createdDate;
@Column(name="LAST_UPDATED_BY", nullable=true)
private Long lastUpdatedBy;
@Column(name="LAST_UPDATED_DATE", nullable=true)
private Timestamp lastUpdatedDate;
@Column(name="AUDIT_STATUS", nullable=true)
private Long auditStatus;
@Column(name="TABREC_SERIAL", nullable=true)
private Long tabrecSerial;
 

    /**
     * TrxTypesEntity Default Constructor
     */    
    public TrxTypesEntity() {
    }


/**
* @return Long
*/
public Long getTrxtypeCode() {
   return trxtypeCode;
}
/**
* @return String
*/
public String getTrxtypeName() {
   return trxtypeName;
}
/**
* @return Long
*/
public Long getCreatedBy() {
   return createdBy;
}
/**
* @return Timestamp
*/
public Timestamp getCreatedDate() {
   return createdDate;
}
/**
* @return Long
*/
public Long getLastUpdatedBy() {
   return lastUpdatedBy;
}
/**
* @return Timestamp
*/
public Timestamp getLastUpdatedDate() {
   return lastUpdatedDate;
}
/**
* @return Long
*/
public Long getAuditStatus() {
   return auditStatus;
}
/**
* @return Long
*/
public Long getTabrecSerial() {
   return tabrecSerial;
}


/**
* @param trxtypeCode
*/
public void setTrxtypeCode(Long trxtypeCode) {
   this.trxtypeCode=trxtypeCode;
}
/**
* @param trxtypeName
*/
public void setTrxtypeName(String trxtypeName) {
   this.trxtypeName=trxtypeName;
}
/**
* @param createdBy
*/
public void setCreatedBy(Long createdBy) {
   this.createdBy=createdBy;
}
/**
* @param createdDate
*/
public void setCreatedDate(Timestamp createdDate) {
   this.createdDate=createdDate;
}
/**
* @param lastUpdatedBy
*/
public void setLastUpdatedBy(Long lastUpdatedBy) {
   this.lastUpdatedBy=lastUpdatedBy;
}
/**
* @param lastUpdatedDate
*/
public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
   this.lastUpdatedDate=lastUpdatedDate;
}
/**
* @param auditStatus
*/
public void setAuditStatus(Long auditStatus) {
   this.auditStatus=auditStatus;
}
/**
* @param tabrecSerial
*/
public void setTabrecSerial(Long tabrecSerial) {
   this.tabrecSerial=tabrecSerial;
}


}
