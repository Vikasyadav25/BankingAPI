package project.banking.dao;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao  {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
//    To register Customer 
    public HashMap<String, Object> saveCustomer( String email) {
    	HashMap<String, Object> hmdetails = new HashMap<>();

        try {
            // Check if the record already exists
            String checkSql = "SELECT id,first_name,email,phone_number,address,created_at,updated_at FROM customers WHERE email = ?";
            Map<String, Object> existingCustomer = null;

            try {
                existingCustomer = jdbcTemplate.queryForMap(checkSql, email);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                // No existing customer found
                existingCustomer = null;
            }

            if (existingCustomer != null && !existingCustomer.isEmpty()) {
                hmdetails.put("status", "exists");
                hmdetails.put("message", "Customer record found.");
                hmdetails.put("id", existingCustomer.get("id"));
                hmdetails.put("first_name", existingCustomer.get("first_name"));
                hmdetails.put("last_name", existingCustomer.get("last_name"));
                hmdetails.put("email", existingCustomer.get("email"));
                hmdetails.put("phone_number", existingCustomer.get("phone_number"));
                hmdetails.put("address", existingCustomer.get("address"));
                hmdetails.put("created_at", existingCustomer.get("created_at"));
                hmdetails.put("updated_at", existingCustomer.get("updated_at"));
                System.out.println(hmdetails.toString());
            } else {
                hmdetails.put("status", "not_found");
                hmdetails.put("message", "No customer found with the provided email or phone number.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            hmdetails.put("status", "error");
            hmdetails.put("message", e.getMessage());
        }

        return hmdetails;
    }
    
    
//    To Update customer
    public HashMap<String, Object> updateCustomer( Long id,String fname, String lname, String address, String phno, String email) {
    	HashMap<String, Object> hmdetails = new HashMap<>();
        System.out.println("I am calling in dao method---" + fname + " " + lname);
        try {
            // Check if the record exists
            String checkSql = "SELECT id FROM customers WHERE email = ?";
            Map<String, Object> existingCustomer = null;

            try {
                existingCustomer = jdbcTemplate.queryForMap(checkSql, email);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                // No existing customer found
                hmdetails.put("status", "error");
                hmdetails.put("message", "Customer not found.");
                return hmdetails;
            }

            if (existingCustomer != null && !existingCustomer.isEmpty()) {
                // Update the existing record
            	Date currentTime = new Date();
            	Map<String, Object> existingCustomer1 = null;
                String updateSql = "UPDATE customers SET first_name = ?, last_name = ?, address = ?, phone_number = ? ,updated_at = ? WHERE email = ?";
                jdbcTemplate.update(updateSql, fname, lname, address, phno,currentTime,email);
                String checkSql1 = "SELECT to_char(created_at, 'YYYY-MM-DD HH24:MI:SS.US') as created_at ,to_char(updated_at, 'YYYY-MM-DD HH24:MI:SS.US') as updated_at  FROM customers WHERE email = ? ";
                existingCustomer1 = jdbcTemplate.queryForMap(checkSql1, email);
                hmdetails.put("status", "success");
                hmdetails.put("message", "Customer updated successfully");
                hmdetails.put("email", email);
                hmdetails.put("created_at", existingCustomer1.get("created_at"));
                hmdetails.put("updated_at", existingCustomer1.get("updated_at"));
            } else {
                hmdetails.put("status", "error");
                hmdetails.put("message", "Customer not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            hmdetails.put("status", "error");
            hmdetails.put("message", e.getMessage());
        }

        return hmdetails;
    }
    

    
    
  
    
  
}
