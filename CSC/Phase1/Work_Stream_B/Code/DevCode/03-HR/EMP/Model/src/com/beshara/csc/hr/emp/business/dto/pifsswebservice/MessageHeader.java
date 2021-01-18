package com.beshara.csc.hr.emp.business.dto.pifsswebservice;

public class MessageHeader {
    private String requestTime;
    private String requestID;
    
    public MessageHeader() {
        super();
    }
    
    @Override
      public String toString() {
          return "MessageHeader{\r\n\t" + "requestTime=" + requestTime + ", requestID=" + requestID +'}';
      }
    
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestID() {
        return requestID;
    }
}
