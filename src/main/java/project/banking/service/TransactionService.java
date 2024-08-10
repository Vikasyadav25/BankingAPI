package project.banking.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
	
	 public HashMap deposit(HashMap transactionDetails);
	 public HashMap transfer(HashMap transactionDetails);
	 public HashMap withdraw(HashMap transactionDetails);
	 public HashMap viewTransaction(String accountNo);

}
