package project.banking.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public HashMap createAccount(HashMap accountDeatils);
}
