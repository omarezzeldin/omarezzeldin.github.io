package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;


public interface IEmployeesEntityKey extends IEntityKey {
    public int hashCode();

    public Long getCivilId();

}
