package project.banking.dao;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public HashMap<String, Object> saveCustomer( String fname, String lname, String address, String phno, String email) {
    	HashMap<String, Object> hmdetails = new HashMap<>();

        try {
            // Check if the record already exists
            String checkSql = "SELECT id FROM customers WHERE email = ? OR phone_number = ?";
            Map<String, Object> existingCustomer = null;

            try {
                existingCustomer = jdbcTemplate.queryForMap(checkSql, email, phno);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                // No existing customer found, proceed with insertion
            }
            if (existingCustomer != null && !existingCustomer.isEmpty()) {
                hmdetails.put("status", "error");
                hmdetails.put("message", "Customer with this email or phone number already exists.");
                hmdetails.put("id", existingCustomer.get("id"));
            } else {
                StringBuffer sql = new StringBuffer();
                Map<String, Object> existingCustomer1 = null;
                sql.append("INSERT INTO customers ( first_name, last_name, address, phone_number, email) ");
                sql.append("VALUES ( ?, ?, ?, ?, ?)");
                String checkSql1 = "SELECT id,created_at,updated_at FROM customers WHERE email = ? OR phone_number = ?";
                jdbcTemplate.update(sql.toString(), fname, lname, address, phno, email);
                existingCustomer1 = jdbcTemplate.queryForMap(checkSql1, email, phno);
                
                hmdetails.put("status", "success");
                hmdetails.put("message", "Customer saved successfully");
                hmdetails.put("id", existingCustomer1.get("id"));
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
    
    
//    To Update customer
    public HashMap<String, Object> updateCustomer( Long id,String fname, String lname, String address, String phno, String email) {
    	HashMap<String, Object> hmdetails = new HashMap<>();
        System.out.println("I am calling in dao method---" + fname + " " + lname);
        try {
            // Check if the record exists
            String checkSql = "SELECT id FROM customers WHERE id = ?";
            Map<String, Object> existingCustomer = null;

            try {
                existingCustomer = jdbcTemplate.queryForMap(checkSql, id);
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                // No existing customer found
                hmdetails.put("status", "error");
                hmdetails.put("message", "Customer not found.");
                return hmdetails;
            }

            if (existingCustomer != null && !existingCustomer.isEmpty()) {
                // Update the existing record
            	Map<String, Object> existingCustomer1 = null;
                String updateSql = "UPDATE customers SET first_name = ?, last_name = ?, address = ?, phone_number = ?, email = ?, updated_at = ? WHERE id = ?";
                jdbcTemplate.update(updateSql, fname, lname, address, phno, email, Timestamp.valueOf(LocalDateTime.now()), id);
                String checkSql1 = "SELECT to_char(created_at, 'YYYY-MM-DD HH24:MI:SS.US') as created_at ,to_char(updated_at, 'YYYY-MM-DD HH24:MI:SS.US') as updated_at  FROM customers WHERE id = ? ";
                existingCustomer1 = jdbcTemplate.queryForMap(checkSql1, id);
                hmdetails.put("status", "success");
                hmdetails.put("message", "Customer updated successfully");
                hmdetails.put("id", id);
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
