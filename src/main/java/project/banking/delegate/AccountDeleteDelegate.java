package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.service.AccountService;

@Component
public class AccountDeleteDelegate {
	@Autowired
    private AccountService accountService;
	  public HashMap Start(HashMap customers) {
	    	
	    	HashMap details=new HashMap();
//	    	String fname=(customers.get("first_name")).toString();
//	    	String lname=(customers.get("last_name")).toString();
//	    	String address=(customers.get("address")).toString();
//	    	String phno=(customers.get("phone_number")).toString();
//	    	String email=(customers.get("email")).toString();
	    	
	    	details=accountService.createAccount(customers);
	    	return details;
	    }
}
