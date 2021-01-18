package com.beshara.csc.hr.emp.business.dto.pifsswebservice;


import java.io.Serializable;

public class RegisterReturnType implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public RegisterReturnType() {

    }
    private boolean result;
    private String messageDescription;
    private Header header;
    private MessageHeader messageHeader;
    private Object body;

    @Override
    public String toString() {
        return "RegisterReturnType{" +
                "result=" + result +
                ", messageDescription='" + messageDescription + '\'' +
                ", header=" + header +
                ", messageHeader=" + messageHeader +
                ", body=" + body +
                '}';
    }

    public Header getHeader() { return header; }
     
    public void setHeader(Header value) { this.header = value; }

    
    public Object getBody() { return body; }
    
    public void setBody(Object value) { this.body = value; }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }
}
