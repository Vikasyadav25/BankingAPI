package project.banking.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.dao.AccountDao;
import project.banking.dao.CustomerDao;

@Component
public class AccountSeviceBean implements AccountService {
	@Autowired
    private AccountDao accountRepository;


    public HashMap createAccount(HashMap accountDetails) {
    	System.out.println("Hello i am println in servicebean for test case");
    	HashMap details2=null;
    	details2=accountRepository.createAccount(accountDetails);
        return details2;
    }
    public HashMap updateAccount(HashMap accountDetails) {
    	System.out.println("Hello i am println in servicebean for test case");
    	HashMap details2=null;
    	details2=accountRepository.updateAccount(accountDetails);
        return details2;
    }
    public HashMap deleteAccount(String accountNumber,String remarks) {
    	System.out.println("Hello i am println in servicebean for test case");
    	HashMap details2=null;
    	details2=accountRepository.deleteAccount(accountNumber,remarks);
        return details2;
    }
    public HashMap appRejAccount(String accountNumber,char status,String remarks) {
    	System.out.println("Hello i am println in servicebean for test case");
    	HashMap details2=null;
    	details2=accountRepository.appRejAccount(accountNumber, status, remarks);
        return details2;
    }
}
