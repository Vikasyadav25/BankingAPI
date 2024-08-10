package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import project.banking.forms.Customerforms;

@Component
public class AccountCommonDelegate {
	
	@Autowired
    private AccountCreateDelegate createAccountDel;
	
	@Autowired
    private AccountUpdateDelegate updateAccountDel;
	
	@Autowired
    private AccountDeleteDelegate deleteAccountDel;
	
	@Autowired
    private AccountAppRejDelegate appRejAccountDel;

	@Transactional
    public void createAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapAccountdetails();
    	infoTable2=createAccountDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    @Transactional
    public void updateAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapAccountdetails();
    	infoTable2=updateAccountDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    @Transactional
    public void deleteAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	String accountNumber=customerfrom.getAccount_number();
    	String remarks=customerfrom.getRemarks();
    	infoTable2=deleteAccountDel.Start(accountNumber, remarks);
    	customerfrom.setStatus(infoTable2);
    }
    @Transactional
    public void appRejAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	String accountNumber=customerfrom.getAccount_number();
    	char status=customerfrom.getAccountStatus();
    	String remarks=customerfrom.getManager_remarks();
    	infoTable2=appRejAccountDel.Start(accountNumber, status, remarks);
    	customerfrom.setStatus(infoTable2);
    }
    
}
