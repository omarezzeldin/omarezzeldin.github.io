package com.beshara.csc.hr.emp.business.entity.job;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "NL_JOB_JOBS")
public class EmpJobsEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    @Id
    @Column(name = "JOB_CODE", nullable = false)
    private String jobCode;

    @Column(name = "JOB_NAME", nullable = false)
    private String jobName;

    @Column(name = "JOB_KEY", nullable = false)
    private String jobKey;
    
    @Column(name = "CAT_CODE", nullable = false)
    private Long catCode;

    public EmpJobsEntity() {
    }


    /**
     *
     * @return String
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     *
     * @param jobCode
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }


    /**
     *
     * @return String
     */
    public String getJobName() {
        return jobName;
    }

    /**
     *
     * @param jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public Long getCatCode() {
        return catCode;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobKey() {
        return jobKey;
    }
}
