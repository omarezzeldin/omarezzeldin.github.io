package com.beshara.csc.hr.emp.business.dto.pifsswebservice;


public class TerminationReturnType extends RegisterReturnType {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    public TerminationReturnType() {
        super();
    }

    @Override
    public String toString() {
        return "TerminationReturnType{" +
                "result=" +  isResult() +
                ", messageDescription='" + getMessageDescription() + '\'' +
                ", header=" + getHeader() +
                ", messageHeader=" + getMessageHeader() +
                ", body=" + getBody() +
                '}';
    }
}
