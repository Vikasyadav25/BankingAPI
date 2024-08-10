package project.banking.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public HashMap createAccount(HashMap accountDeatils);
    
    public HashMap updateAccount(HashMap accountDeatils);
    
    public HashMap deleteAccount(String accountNumber,String remarks);
    
    public HashMap appRejAccount(String accountNumber,char status,String remarks);
}
