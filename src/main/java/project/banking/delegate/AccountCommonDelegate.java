package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

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

    
    public void createAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapAccountdetails();
    	infoTable2=createAccountDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    
    public void updateAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapAccountdetails();
    	Long id=customerfrom.getId();
    	infoTable2=updateAccountDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    public void deleteAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapAccountdetails();
    	Long id=customerfrom.getId();
    	infoTable2=deleteAccountDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    public void appRejAccount(@RequestBody Customerforms customerfrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=customerfrom.toHashMapAccountdetails();
    	Long id=customerfrom.getId();
    	infoTable2=appRejAccountDel.Start(infoTable);
    	customerfrom.setStatus(infoTable2);
    }
    
}
