package project.banking.delegate;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.banking.service.CustomerService;

@Component
public class CustomerRegisterDelegate {

    @Autowired
    private CustomerService customerService;

    
    public HashMap Start(HashMap customers) {
    	HashMap details=new HashMap();
//    	String fname=(customers.get("first_name")).toString();
//    	String lname=(customers.get("last_name")).toString();
//    	String address=(customers.get("address")).toString();
//    	String phno=(customers.get("phone_number")).toString();
    	String email=(customers.get("email")).toString();
    	details=customerService.onloadCustomer(email);
    	return details;
    }
}
