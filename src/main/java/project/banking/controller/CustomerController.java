package project.banking.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import project.banking.delegate.CustomerCommonDelegate;
import project.banking.exception.ExitException;
import project.banking.exception.NoDataFoundException;
import project.banking.forms.Customerforms;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    @Autowired
    private CustomerCommonDelegate commonDelegate;

//  To register Customer 
    @PostMapping(value="/CustomerRegister",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customerforms> registerCustomer(@RequestBody Customerforms in) {
    	try {
    		commonDelegate.registerCustomer(in);
        }
    	catch(NoDataFoundException noData) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found", noData);
    	}
    	catch(ExitException gExt) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, gExt.getMessage(), gExt);
    	}
    	catch(Exception ex) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
    	}
    	return ResponseEntity.ok(in);
    	
    }
//  To Update customer
    @PostMapping(value="/CustomerUpdate",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customerforms> updateCustomer(@RequestBody Customerforms in) {
    	try {
    		commonDelegate.updateCustomer(in);
        }
    	catch(NoDataFoundException noData) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found", noData);
    	}
    	catch(ExitException gExt) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, gExt.getMessage(), gExt);
    	}
    	catch(Exception ex) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
    	}
    	return ResponseEntity.ok(in);
    	
    }
    
}
