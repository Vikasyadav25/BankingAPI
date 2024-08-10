package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.service.TransactionService;

@Component
public class TransactionViewDelegate {
	@Autowired
    private TransactionService transactionService;

    
    public HashMap Start(HashMap customers) {
    	HashMap details=new HashMap();
    	String accountNo=(customers.get("account_number")).toString();
    	details=transactionService.viewTransaction(accountNo);
    	return details;
    }
}
