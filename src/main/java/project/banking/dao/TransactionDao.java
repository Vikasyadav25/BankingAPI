package project.banking.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	
//  To deposit amount
  public HashMap<String, Object> depositAmount(HashMap transactionDetails) {

  	HashMap<String, Object> hmdetails = new HashMap<>();
      try {
      	String utr_numberNum = String.format("%07d", new Random().nextLong(1000000));
      	String utr_number="UTR2024"+utr_numberNum;
              
     // Check the current balance
        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        Map<String, Object> checkBalance = jdbcTemplate.queryForMap(checkSql, transactionDetails.get("account_number"));
        BigDecimal balance = (BigDecimal) checkBalance.get("balance");
        BigDecimal amount = new BigDecimal((String) transactionDetails.get("amount"));

        // Calculate the new balance after the transaction
        BigDecimal balanceAfter = balance.add(amount); // For deposit; use subtract for withdrawal

        // Update the account balance
        String updateAccount = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        jdbcTemplate.update(updateAccount, balanceAfter, transactionDetails.get("account_number"));

        // Insert the transaction record
        String insertTransactionSql = "INSERT INTO transactions (utr_number, account_number, transaction_type, amount, balance_after,"
        		+ " transaction_date, from_account, to_account, remarks, status) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
        jdbcTemplate.update(insertTransactionSql,
                utr_number,
                transactionDetails.get("account_number"),
                transactionDetails.get("transaction_type"),
                amount,
                balanceAfter,
                transactionDetails.get("from_account"),
                transactionDetails.get("to_account"),
                transactionDetails.get("remarks"),
                "SUCCESS"
        );

        // Fetch the transaction details
        String checkSql1 = "SELECT account_number, utr_number, status, transaction_date, amount, balance_after FROM transactions WHERE utr_number = ?";
        Map<String, Object> transactionRecord = jdbcTemplate.queryForMap(checkSql1, utr_number);

        // Prepare the response
        hmdetails.put("status", "success");
        hmdetails.put("message", "Transaction completed successfully");
        hmdetails.put("account_number", transactionRecord.get("account_number"));
        hmdetails.put("utr_number", transactionRecord.get("utr_number"));
        hmdetails.put("status", transactionRecord.get("status"));
        hmdetails.put("transaction_date", transactionRecord.get("transaction_date"));
        hmdetails.put("transactionAmount", transactionRecord.get("amount"));
        hmdetails.put("currentBalance", transactionRecord.get("balance_after"));


      } catch (Exception e) {
          e.printStackTrace();
          hmdetails.put("status", "error");
          hmdetails.put("message", e.getMessage());
      }
      return hmdetails;
  }
  
  
  
  
//   To withdrawAmount 
  public HashMap<String, Object> withdrawAmount(HashMap transactionDetails) {

  	HashMap<String, Object> hmdetails = new HashMap<>();
      try {
      	String utr_numberNum = String.format("%07d", new Random().nextLong(1000000));
      	String utr_number="UTR2024"+utr_numberNum;
              
     // Check the current balance
        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        Map<String, Object> checkBalance = jdbcTemplate.queryForMap(checkSql, transactionDetails.get("account_number"));
        BigDecimal balance = (BigDecimal) checkBalance.get("balance");
        BigDecimal amount = new BigDecimal((String) transactionDetails.get("amount"));

        // Check if the withdrawal amount is greater than the available balance
        if (amount.compareTo(balance) > 0) {
            hmdetails.put("status", "error");
            hmdetails.put("message", "Insufficient balance. Transaction cannot be completed.");
            return hmdetails;
        }

        // Calculate the new balance after the withdrawal
        BigDecimal balanceAfter = balance.subtract(amount);

        // Update the account balance
        String updateAccount = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        jdbcTemplate.update(updateAccount, balanceAfter, transactionDetails.get("account_number"));

        // Insert the transaction record
        String insertTransactionSql = "INSERT INTO transactions (utr_number, account_number, transaction_type, amount, balance_after,"
                + " transaction_date, from_account, to_account, remarks, status) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
        jdbcTemplate.update(insertTransactionSql,
                utr_number,
                transactionDetails.get("account_number"),
                transactionDetails.get("transaction_type"),
                amount,
                balanceAfter,
                transactionDetails.get("from_account"),
                transactionDetails.get("to_account"),
                transactionDetails.get("remarks"),
                "SUCCESS"
        );

        // Fetch the transaction details
        String checkSql1 = "SELECT account_number, utr_number, status, transaction_date, amount, balance_after FROM transactions WHERE utr_number = ?";
        Map<String, Object> transactionRecord = jdbcTemplate.queryForMap(checkSql1, utr_number);

        // Prepare the response
        hmdetails.put("status", "success");
        hmdetails.put("message", "Withdrawal completed successfully");
        hmdetails.put("account_number", transactionRecord.get("account_number"));
        hmdetails.put("utr_number", transactionRecord.get("utr_number"));
        hmdetails.put("status", transactionRecord.get("status"));
        hmdetails.put("transaction_date", transactionRecord.get("transaction_date"));
        hmdetails.put("transactionAmount", transactionRecord.get("amount"));
        hmdetails.put("currentBalance", transactionRecord.get("balance_after"));


      } catch (Exception e) {
          e.printStackTrace();
          hmdetails.put("status", "error");
          hmdetails.put("message", e.getMessage());
      }
      return hmdetails;
  }
  
  
  
///  To transfer 
  public HashMap<String, Object> transferAmount(HashMap transactionDetails) {

	  	HashMap<String, Object> hmdetails = new HashMap<>();
	      try {
	      	String utr_numberNum = String.format("%07d", new Random().nextLong(1000000));
	      	String utr_number="UTR2024"+utr_numberNum;
	              
	     // Check the current balance
	        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
	        Map<String, Object> checkBalance = jdbcTemplate.queryForMap(checkSql, transactionDetails.get("account_number"));
	        BigDecimal balance = (BigDecimal) checkBalance.get("balance");
	        BigDecimal amount = new BigDecimal((String) transactionDetails.get("amount"));

	        // Check if the withdrawal amount is greater than the available balance
	        if (amount.compareTo(balance) > 0) {
	            hmdetails.put("status", "error");
	            hmdetails.put("message", "Insufficient balance. Transaction cannot be completed.");
	            return hmdetails;
	        }

	        // Calculate the new balance after the withdrawal
	        BigDecimal balanceAfter = balance.subtract(amount);

	        // Update the account balance
	        String updateAccount = "UPDATE accounts SET balance = ? WHERE account_number = ?";
	        jdbcTemplate.update(updateAccount, balanceAfter, transactionDetails.get("account_number"));

	        // Insert the transaction record
	        String insertTransactionSql = "INSERT INTO transactions (utr_number, account_number, transaction_type, amount, balance_after,"
	                + " transaction_date, from_account, to_account, remarks, status) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
	        jdbcTemplate.update(insertTransactionSql,
	                utr_number,
	                transactionDetails.get("account_number"),
	                transactionDetails.get("transaction_type"),
	                amount,
	                balanceAfter,
	                transactionDetails.get("from_account"),
	                transactionDetails.get("to_account"),
	                transactionDetails.get("remarks"),
	                "SUCCESS"
	        );
	        
	        

	        // Fetch the transaction details
	        String checkSql1 = "SELECT account_number, utr_number, status, transaction_date, amount, balance_after FROM transactions WHERE utr_number = ?";
	        Map<String, Object> transactionRecord = jdbcTemplate.queryForMap(checkSql1, utr_number);

	        // Prepare the response
	        hmdetails.put("status", "success");
	        hmdetails.put("message", "Withdrawal completed successfully");
	        hmdetails.put("account_number", transactionRecord.get("account_number"));
	        hmdetails.put("utr_number", transactionRecord.get("utr_number"));
	        hmdetails.put("status", transactionRecord.get("status"));
	        hmdetails.put("transaction_date", transactionRecord.get("transaction_date"));
	        hmdetails.put("transactionAmount", transactionRecord.get("amount"));
	        hmdetails.put("currentBalance", transactionRecord.get("balance_after"));
//

	      } catch (Exception e) {
	          e.printStackTrace();
	          hmdetails.put("status", "error");
	          hmdetails.put("message", e.getMessage());
	      }
	      return hmdetails;
	  }
	  
	  
	  
  public HashMap<String, Object> depositToAnother(HashMap transactionDetails) {
	  HashMap<String, Object> hmdetails = new HashMap<>();
      try {
      	String utr_numberNum = String.format("%07d", new Random().nextLong(1000000));
      	String utr_number="UTR2024"+utr_numberNum;
              
     // Check the current balance
        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        Map<String, Object> checkBalance = jdbcTemplate.queryForMap(checkSql, transactionDetails.get("to_account"));
        BigDecimal balance = (BigDecimal) checkBalance.get("balance");
        BigDecimal amount = new BigDecimal((String) transactionDetails.get("amount"));

        // Calculate the new balance after the transaction
        BigDecimal balanceAfter = balance.add(amount); // For deposit; use subtract for withdrawal

        // Update the account balance
        String updateAccount = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        jdbcTemplate.update(updateAccount, balanceAfter, transactionDetails.get("to_account"));

        // Insert the transaction record
        String insertTransactionSql = "INSERT INTO transactions (utr_number, account_number, transaction_type, amount, balance_after,"
        		+ " transaction_date, from_account, to_account, remarks, status) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
        jdbcTemplate.update(insertTransactionSql,
                utr_number,
                transactionDetails.get("to_account"),
                transactionDetails.get("transaction_type"),
                amount,
                balanceAfter,
                transactionDetails.get("account_number"),
                transactionDetails.get("to_account"),
                transactionDetails.get("remarks"),
                "SUCCESS"
        );


      } catch (Exception e) {
          e.printStackTrace();
          hmdetails.put("status", "error");
          hmdetails.put("message", e.getMessage());
      }
      return hmdetails;
	 
	  }
	  
	  
//  viewTransaction
  public HashMap<String, Object> viewTransaction(String  accountNo) {
	  HashMap<String, Object> hmdetails = new HashMap<>();
      try {
          // SQL query to retrieve all transactions for the given account number
          String sql = "SELECT transaction_id, utr_number, transaction_type, amount, balance_after, transaction_date, from_account, to_account, remarks, status " +
                       "FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC";

          // Execute the query and get the results
          List<Map<String, Object>> transactions = jdbcTemplate.queryForList(sql, accountNo);

          if (transactions.isEmpty()) {
              hmdetails.put("status", "error");
              hmdetails.put("message", "No transactions found for the given account number.");
          } else {
              hmdetails.put("status", "success");
              hmdetails.put("transactions", transactions);
          }
      } catch (Exception e) {
          e.printStackTrace();
          hmdetails.put("status", "error");
          hmdetails.put("message", e.getMessage());
      }
      return hmdetails;
  }
}
