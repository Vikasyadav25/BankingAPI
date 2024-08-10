package project.banking.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class AccountDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
//    To register Customer 
    public HashMap<String, Object> createAccount(HashMap accountDeatils) {

    	HashMap<String, Object> hmdetails = new HashMap<>();
        try {
        	String accountNumber = String.format("%010d", new Random().nextInt(1000000000));
        	String consumerNumber = String.format("%010d", new Random().nextInt(1000000000));
        	
            // Check if the record already exists
            String checkSql = "SELECT account_number FROM accounts WHERE email = ? OR phone_number = ?";
            Map<String, Object> existingCustomer = null;

            try {
                existingCustomer = jdbcTemplate.queryForMap(checkSql, accountDeatils.get("email"),accountDeatils.get("phone_number"));
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                // No existing customer found, proceed with insertion
            }
            if (existingCustomer != null && !existingCustomer.isEmpty()) {
                hmdetails.put("status", "error");
                hmdetails.put("message", "Account with this email,Adhar or phone number already exists.");
                hmdetails.put("account_number", existingCustomer.get("account_number"));
            } else {
            	
                StringBuffer sql = new StringBuffer();
                Map<String, Object> existingCustomer1 = null;
                
                sql.append("INSERT INTO accounts (first_name,last_name,account_number, account_type, balance,"
                		+ " consumer_number, adhar_card, email, phone_number, date_of_birth,"
                		+ "photo, branch, address, district, state,"
                		+ " pincode,delete_flag,status) "
                        + "VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                
                String checkSql1 = "SELECT account_number,consumer_number,status,created_at,updated_at FROM accounts WHERE email = ? OR phone_number = ?";
                
                
                LocalDate localDate = LocalDate.parse((String) accountDeatils.get("date_of_birth"));
                Date dateOfBirth = Date.valueOf(localDate);
                
                jdbcTemplate.update(sql.toString(), 
                		accountDeatils.get("first_name"), 
                		accountDeatils.get("last_name"), 
                		accountNumber, 
                		accountDeatils.get("account_type"), 
                		accountDeatils.get("balance")
                		,consumerNumber,
                		accountDeatils.get("adhar_card"),
                		accountDeatils.get("email"),
                		accountDeatils.get("phone_number"),
                		dateOfBirth,
                		accountDeatils.get("photo"),
                		accountDeatils.get("branch"),
                		accountDeatils.get("address"),
                		accountDeatils.get("district"),
                		accountDeatils.get("state")
                		,accountDeatils.get("pincode")
                		,'N','P');
                
                
                existingCustomer1 = jdbcTemplate.queryForMap(checkSql1, accountDeatils.get("email"), accountDeatils.get("phone_number"));
                
                hmdetails.put("status", "success");
                hmdetails.put("message", "Account created successfully Pending for Approval");
                hmdetails.put("account_number", existingCustomer1.get("account_number"));
                hmdetails.put("consumer_number", existingCustomer1.get("consumer_number"));
                hmdetails.put("status", existingCustomer1.get("status"));
                hmdetails.put("created_at", existingCustomer1.get("created_at"));
                hmdetails.put("updated_at", existingCustomer1.get("updated_at"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            hmdetails.put("status", "error");
            hmdetails.put("message", e.getMessage());
        }
        return hmdetails;
    }
    
    
    
    
    public HashMap<String, Object> updateAccount(HashMap accountDetails) {

    	HashMap<String, Object> hmdetails = new HashMap<>();
        try {
            
                StringBuffer sql = new StringBuffer();
                StringBuffer sql2 = new StringBuffer();
                sql2.append("update customers set first_name = ?,last_name = ?,phone_number =?,address=? where email=? ");
                
                sql.append("UPDATE accounts SET ")
                    .append("first_name = ?, ")
                    .append("last_name = ?, ")
                    .append("account_type = ?, ")
                    .append("balance = ?, ")
                    .append("adhar_card = ?, ")
                    .append("phone_number = ?, ")
                    .append("date_of_birth = ?, ")
                    .append("photo = ?, ")
                    .append("branch = ?, ")
                    .append("address = ?, ")
                    .append("district = ?, ")
                    .append("state = ?, ")
                    .append("pincode = ?, ")
                    .append("status = 'P', ")
                    .append("updated_at = CURRENT_TIMESTAMP ")
                    .append("WHERE account_number = ?");

                // Convert date string to java.sql.Date
                LocalDate localDate = LocalDate.parse((String) accountDetails.get("date_of_birth"));
                Date dateOfBirth = Date.valueOf(localDate);

                jdbcTemplate.update(sql.toString(),
                    accountDetails.get("first_name"),
                    accountDetails.get("last_name"),
                    accountDetails.get("account_type"),
                    accountDetails.get("balance"),
                    accountDetails.get("adhar_card"),
                    accountDetails.get("phone_number"),
                    dateOfBirth,
                    accountDetails.get("photo"),
                    accountDetails.get("branch"),
                    accountDetails.get("address"),
                    accountDetails.get("district"),
                    accountDetails.get("state"),
                    accountDetails.get("pincode"),
                    accountDetails.get("account_number") // For WHERE clause
                );
                jdbcTemplate.update(sql2.toString(),
                        accountDetails.get("first_name"),
                        accountDetails.get("last_name"),
                        accountDetails.get("phone_number"),
                        accountDetails.get("address"),
                        accountDetails.get("email") // For WHERE clause
                    );
                
                String fetchUpdatedSql = "SELECT account_number, consumer_number, status, created_at, updated_at FROM accounts WHERE account_number = ?";
                Map<String, Object> updatedAccount = jdbcTemplate.queryForMap(fetchUpdatedSql, accountDetails.get("account_number"));
                hmdetails.put("status", "success");
                hmdetails.put("message", "Account updated successfully pending for Approval");
                hmdetails.put("account_number", updatedAccount.get("account_number"));
                hmdetails.put("consumer_number", updatedAccount.get("consumer_number"));
                hmdetails.put("status", updatedAccount.get("status"));
                hmdetails.put("created_at", updatedAccount.get("created_at"));
                hmdetails.put("updated_at", updatedAccount.get("updated_at"));

        } catch (Exception e) {
            e.printStackTrace();
            hmdetails.put("status", "error");
            hmdetails.put("message", e.getMessage());
        }
        return hmdetails;
    }
    
    
    
    
    public HashMap<String, Object> deleteAccount(String accountNumber,String remarks) {

    	HashMap<String, Object> hmdetails = new HashMap<>();
        try {
                StringBuffer sql = new StringBuffer();
                sql.append("UPDATE accounts SET remarks=? ,")
                    .append("delete_flag = 'Y', ")
                    .append("updated_at = CURRENT_TIMESTAMP ")
                    .append("WHERE account_number = ?");
                jdbcTemplate.update(sql.toString(), remarks,accountNumber) ;
                
                String fetchUpdatedSql = "SELECT account_number, consumer_number, status, created_at, updated_at,remarks FROM accounts WHERE account_number = ?";
                Map<String, Object> updatedAccount = jdbcTemplate.queryForMap(fetchUpdatedSql, accountNumber);
                hmdetails.put("status", "success");
                hmdetails.put("message", "Account Deleted successfully pending for confirmation from Manager");
                hmdetails.put("account_number", updatedAccount.get("account_number"));
                hmdetails.put("consumer_number", updatedAccount.get("consumer_number"));
                hmdetails.put("status", updatedAccount.get("status"));
                hmdetails.put("created_at", updatedAccount.get("created_at"));
                hmdetails.put("updated_at", updatedAccount.get("updated_at"));
                hmdetails.put("remarks", updatedAccount.get("remarks"));
                System.out.println("Hello i am println in dao for test case"+hmdetails.toString());
        } catch (Exception e) {
            e.printStackTrace();
            hmdetails.put("status", "error");
            hmdetails.put("message", e.getMessage());
        }
        return hmdetails;
    }
    
    
    
    public HashMap<String, Object> appRejAccount(String accountNumber,char status,String remarks) {

    	HashMap<String, Object> hmdetails = new HashMap<>();
    	System.out.println(remarks);
        try {
        	StringBuffer sql = new StringBuffer();
            sql.append("UPDATE accounts SET manager_remarks=?, ")
                .append("status = ?, ")
                .append("updated_at = CURRENT_TIMESTAMP ")
                .append("WHERE account_number = ?");
            jdbcTemplate.update(sql.toString(),remarks,status,accountNumber) ;
            
            String fetchUpdatedSql = "SELECT account_number, consumer_number, status, created_at, updated_at,manager_remarks FROM accounts WHERE account_number = ?";
            Map<String, Object> updatedAccount = jdbcTemplate.queryForMap(fetchUpdatedSql, accountNumber);
            hmdetails.put("status", "success");
            hmdetails.put("message", "Account checked successfully");
            hmdetails.put("account_number", updatedAccount.get("account_number"));
            hmdetails.put("consumer_number", updatedAccount.get("consumer_number"));
            hmdetails.put("status", updatedAccount.get("status"));
            hmdetails.put("created_at", updatedAccount.get("created_at"));
            hmdetails.put("manager_remarks", updatedAccount.get("manager_remarks"));

        } catch (Exception e) {
            e.printStackTrace();
            hmdetails.put("status", "error");
            hmdetails.put("message", e.getMessage());
        }
        return hmdetails;
    }
    
    
    
    
}
