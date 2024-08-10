package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.service.AccountService;

@Component
public class AccountDeleteDelegate {
	@Autowired
    private AccountService accountService;
	
	public HashMap Start(String accountNumber,String remarks) {
	    	
	    	HashMap details=new HashMap();
	    	details=accountService.deleteAccount(accountNumber,remarks);
	    	return details;
	}
}
