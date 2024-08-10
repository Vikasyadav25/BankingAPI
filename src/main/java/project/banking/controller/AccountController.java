package project.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import project.banking.delegate.AccountCommonDelegate;
import project.banking.exception.ExitException;
import project.banking.exception.NoDataFoundException;
import project.banking.forms.Customerforms;

@RestController
@RequestMapping("/account")

public class AccountController {

    @Autowired
    private AccountCommonDelegate commonDelegate;

//  To Create Account 
    @PostMapping(value="/createAccount",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customerforms> createAccount(@Validated @RequestBody Customerforms in) {
    	try {
    		commonDelegate.createAccount(in);
    		return ResponseEntity.status(HttpStatus.CREATED).body(in);
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
    	
    }
//  To Update Account 
    @PostMapping(value="/updateAccount",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customerforms> updateAccount(@Validated @RequestBody Customerforms in) {
    	try {
    		commonDelegate.updateAccount(in);
    		return ResponseEntity.ok(in);
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
    	
    }
//  To Delete Account 
    @PostMapping(value="/deleteAccount",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customerforms> deleteAccount(@Validated @RequestBody Customerforms in) {
    	try {
    		commonDelegate.deleteAccount(in);
    		return ResponseEntity.ok(in);
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
    	
    }
//    Approve or Reject Account by Admin
    @PostMapping(value="/approve-reject",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customerforms> appRejAccount(@Validated @RequestBody Customerforms in) {
    	try {
    		commonDelegate.appRejAccount(in);
    		 return ResponseEntity.ok(in);
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
    	
    }
}
