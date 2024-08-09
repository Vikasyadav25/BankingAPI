package project.banking.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;


@Service
public interface CustomerService {
    public HashMap registerCustomer(String fname,String lname,String address,String phno,String email);
    // Other service methods if needed
    public HashMap updateCustomer(Long id,String fname,String lname,String address,String phno,String email);
}
