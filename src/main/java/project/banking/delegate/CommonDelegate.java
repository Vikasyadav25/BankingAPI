package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import project.banking.forms.Customerforms;

@Component
public class CommonDelegate {
	
	@Autowired
    private CustomerRegisterDelegate customerRegisterDel;
	@Autowired
    private CustomerUpdateDelegate customerUpdateDel;

    
    public void registerCustomer(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapMain();
    	infoTable2=customerRegisterDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    
    public void updateCustomer(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapMain();
    	Long id=customerfrom.getId();
//    	String fname=customerfrom.getFirst_name();
//    	String lname=customerfrom.getLast_name();
//    	String email=customerfrom.getEmail();
//    	String phNo=customerfrom.getPhone_number();
//    	String address=customerfrom.getAddress();
    	infoTable2=customerUpdateDel.Start(id,infoTable);
    	customerfrom.setStatus(infoTable2);
    }
	
}
