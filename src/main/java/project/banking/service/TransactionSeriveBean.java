package project.banking.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.dao.TransactionDao;


@Component
public class TransactionSeriveBean implements TransactionService {
	
	@Autowired
    private TransactionDao transactionDao;


    public HashMap deposit(HashMap transactionDetails) {
    	HashMap details2=null;
    	details2=transactionDao.depositAmount(transactionDetails);
        return details2;
    }
    public HashMap transfer(HashMap transactionDetails) {
    	HashMap details2=null;
    	transactionDao.depositToAnother(transactionDetails);
    	details2=transactionDao.transferAmount(transactionDetails);
    	
        return details2;
    }
    public HashMap withdraw(HashMap transactionDetails) {
    	HashMap details2=null;
    	details2=transactionDao.withdrawAmount(transactionDetails);
        return details2;
    }
    public HashMap viewTransaction(String accountNo) {
    	HashMap details2=null;
    	details2=transactionDao.viewTransaction(accountNo);
        return details2;
    }
}
