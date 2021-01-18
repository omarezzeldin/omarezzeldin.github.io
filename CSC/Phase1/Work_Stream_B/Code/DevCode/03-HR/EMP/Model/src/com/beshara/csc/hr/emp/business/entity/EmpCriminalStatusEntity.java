package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Entity
@NamedQueries( { @NamedQuery(name = "EmpCriminalStatusEntity.findAll",
                             query = "select o from EmpCriminalStatusEntity o"),
                 @NamedQuery(name = "EmpCriminalStatusEntity.findNewId",
                             query = "select MAX(o.crmstatusCode) from EmpCriminalStatusEntity o"),
                 @NamedQuery(name = "EmpCriminalStatusEntity.searchByCode",
                             query = "select o from EmpCriminalStatusEntity o where o.crmstatusCode=:code"),

        @NamedQuery(name = "EmpCriminalStatusEntity.searchByName",
                    query = "select o from EmpCriminalStatusEntity o where o.name like :name order by o.name") })
@Table(name = "HR_EMP_CRIMINAL_STATUS")
@IdClass(IEmpCriminalStatusEntityKey.class)

public class EmpCriminalStatusEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CRMSTATUS_CODE", nullable = false)
    private String crmstatusCode;
    @Column(name = "CRMSTATUS_NAME", nullable = false)
    private String name;


    /**
     * EmpCriminalStatusEntity Default Constructor
     */
    public EmpCriminalStatusEntity() {
    }


    /**
     * @return String
     */
    public String getCrmstatusCode() {
        return crmstatusCode;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * @param crmstatusCode
     */
    public void setCrmstatusCode(String crmstatusCode) {
        this.crmstatusCode = crmstatusCode;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


}
