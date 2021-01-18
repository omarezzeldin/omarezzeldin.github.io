package com.beshara.csc.hr.emp.business.dao;

import java.math.BigDecimal;


public class BigDecimalMaxId {

    private BigDecimal maxId;

    public BigDecimalMaxId(BigDecimal maxId) {
        this.maxId = maxId;
    }

    public synchronized BigDecimal getMaxId() {
        maxId = (maxId.add(new BigDecimal(1)));
        System.out.println("from BigDecimalMaxId maxId>>>" + maxId);
        return maxId;
    }
}
