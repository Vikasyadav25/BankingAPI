package project.banking.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import project.banking.delegate.TransactionCommonDelegate;
import project.banking.exception.ExitException;
import project.banking.exception.NoDataFoundException;
import project.banking.forms.Transactionforms;

@RestController
@RequestMapping("/transactions")

public class TransactionsController {

    @Autowired
    private TransactionCommonDelegate commonDelegate;

//  To deposit Amount 
    @PostMapping(value="/deposit",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transactionforms> deposit(@RequestBody Transactionforms in) {
    	try {
    		commonDelegate.deposit(in);
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
    @PostMapping(value="/withdraw",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transactionforms> withdraw(@RequestBody Transactionforms in) {
    	try {
    		commonDelegate.withdraw(in);
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
    @PostMapping(value="/transfer",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transactionforms> transfer(@RequestBody Transactionforms in) {
    	try {
    		commonDelegate.transfer(in);
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
    @PostMapping(value="/viewTransaction",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transactionforms> viewTransaction(@RequestBody Transactionforms in) {
    	try {
    		commonDelegate.viewTransaction(in);
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
