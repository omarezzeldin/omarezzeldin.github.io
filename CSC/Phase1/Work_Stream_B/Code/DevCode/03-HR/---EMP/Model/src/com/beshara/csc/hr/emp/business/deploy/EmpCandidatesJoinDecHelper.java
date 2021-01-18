package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.csc.nl.reg.integration.business.joindec.IJoinDecTargetDTO;
import com.beshara.csc.nl.reg.integration.business.joindec.JoinDecHelper;

import java.util.List;


public class EmpCandidatesJoinDecHelper extends JoinDecHelper{
    
   
  
   
  
    public EmpCandidatesJoinDecHelper(List<IJoinDecTargetDTO> targetList) {
        
     
        
        // using multi selection
       
        super(targetList ,2,1);
        
         }
           
           
         // you can write your own business here  
 
    
    
    
    
    
    


    
    
    
}
