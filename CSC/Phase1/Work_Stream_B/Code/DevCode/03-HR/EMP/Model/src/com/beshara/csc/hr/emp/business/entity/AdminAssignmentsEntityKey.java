package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class AdminAssignmentsEntityKey extends EntityKey implements IAdminAssignmentsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long admassignmentSerial;

    public AdminAssignmentsEntityKey() {
        super();
    }

    public AdminAssignmentsEntityKey(Long admassignmentSerial) {
        super(new Object[] { admassignmentSerial });
        this.admassignmentSerial = admassignmentSerial;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getAdmassignmentSerial() {
        return admassignmentSerial;
    }
}
