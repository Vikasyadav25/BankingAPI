package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.service.TransactionService;


@Component
public class TransactionTransferDelegate {
	@Autowired
    private TransactionService transactionService;

    
    public HashMap Start(HashMap transactionDetails) {
    	HashMap details=new HashMap();
    	details=transactionService.transfer(transactionDetails);
    	return details;
    }
}
