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
    	System.out.println("Calling Dao Suess-----------------");
    	System.out.println(accountDeatils.toString());
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
}
