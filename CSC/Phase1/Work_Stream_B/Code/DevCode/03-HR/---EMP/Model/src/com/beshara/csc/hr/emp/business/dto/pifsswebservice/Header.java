package com.beshara.csc.hr.emp.business.dto.pifsswebservice;

import java.io.Serializable;

// Header.java


//  import com.fasterxml.jackson.annotation.*;

    public class Header implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

        private String providerCode;
        private String messageType;
        private String titleEN;
        private Object description;
        private String providerDescription;
        private String titleAR;
        private Object key;
        private Object errors;
        private String statusCode;

        @Override
          public String toString() {
              return "Header{\r\n\t" + "providerCode=" + providerCode + ", messageType=" + messageType + ", titleEN=" + titleEN + ", description=" + description + ", providerDescription=" + providerDescription + ", titleAR=" + titleAR + ", key=" + key + ", errors=" + errors + ", statusCode=" + statusCode + '}';
          }
        

    public String getProviderCode() { return providerCode; }
        
        public void setProviderCode(String value) { this.providerCode = value; }

        
        public String getMessageType() { return messageType; }
        
        public void setMessageType(String value) { this.messageType = value; }

         
        public String getTitleEN() { return titleEN; }
       
        public void setTitleEN(String value) { this.titleEN = value; }

         
        public Object getDescription() { return description; }
         
        public void setDescription(Object value) { this.description = value; }

         
        public String getProviderDescription() { return providerDescription; }
        
        public void setProviderDescription(String value) { this.providerDescription = value; }

        
        public String getTitleAR() { return titleAR; }
       
        public void setTitleAR(String value) { this.titleAR = value; }

       
        public Object getKey() { return key; }
        
        public void setKey(Object value) { this.key = value; }

         
        public Object getErrors() { return errors; }
         public void setErrors(Object value) { this.errors = value; }

         public String getStatusCode() { return statusCode; }
         public void setStatusCode(String value) { this.statusCode = value; }
    }