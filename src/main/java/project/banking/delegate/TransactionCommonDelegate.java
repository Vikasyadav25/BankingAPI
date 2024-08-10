package project.banking.delegate;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import project.banking.forms.Transactionforms;

@Component
public class TransactionCommonDelegate {
	
	@Autowired
    private TransactionDepositDelegate deposit;
	
	@Autowired
    private TransactionWithdrawDelegate withdraw;
	
	@Autowired
    private TransactionTransferDelegate transfer;
	
	@Autowired
    private TransactionViewDelegate view;

    
	@Transactional
    public void deposit(@RequestBody Transactionforms transactionFrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=transactionFrom.totransactionDeatils();
    	infoTable2=deposit.Start(infoTable);
    	transactionFrom.setStatusre(infoTable2);
    }
	@Transactional
    public void withdraw(@RequestBody Transactionforms transactionFrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=transactionFrom.totransactionDeatils();
    	infoTable2=withdraw.Start(infoTable);
    	transactionFrom.setStatusre(infoTable2);
    }
	
	  @Transactional
    public void transfer(@RequestBody Transactionforms transactionFrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=transactionFrom.totransactionDeatils();
    	infoTable2=transfer.Start(infoTable);
    	transactionFrom.setStatusre(infoTable2);
    }
	  
	
    public void viewTransaction(@RequestBody Transactionforms transactionFrom) {
    	HashMap infoTable2=null;
    	HashMap infoTable=transactionFrom.totransactionDeatils();
    	infoTable2=view.Start(infoTable);
    	transactionFrom.setStatusre(infoTable2);
    }

}
