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
}
