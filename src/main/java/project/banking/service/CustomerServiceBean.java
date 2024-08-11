
package project.banking.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.banking.dao.CustomerDao;

@Component
public class CustomerServiceBean implements CustomerService {

    @Autowired
    private CustomerDao customerRepository;


    public HashMap onloadCustomer(String email) {
    	System.out.println("Hello i am println in servicebean for test case");
    	HashMap details2=null;
    	details2=customerRepository.saveCustomer(email);
        return details2;
    }
    
    public HashMap updateCustomer(Long id,String fname,String lname,String address,String phno,String email) {
    	System.out.println("Hello i am println in servicebean for test case"+fname);
    	HashMap details2=null;
    	details2=customerRepository.updateCustomer(id,fname,lname,address,phno,email);
        return details2;
    }
    
}
