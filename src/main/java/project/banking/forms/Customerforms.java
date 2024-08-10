package project.banking.forms;

import java.math.BigDecimal;
import java.util.HashMap;

public class Customerforms {
	private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String phone_number;
    private String created_at;
    private String updated_at;
    private HashMap status;
    
    
    private String account_number;
    private String account_type;
    private BigDecimal balance;
    private String consumer_number;
    private String adhar_card;
    private String date_of_birth;
    private String photo;;
    private String branch;
    private String district;
    private String state;
    private String pincode;
    private char delete_flag;
    private char accountStatus;
    private String accountCreated_at;
    private String accountUpdated_at;
    private String remarks;
    private String manager_remarks;
    
    
	public String getManager_remarks() {
		return manager_remarks;
	}

	public void setManager_remarks(String manager_remarks) {
		this.manager_remarks = manager_remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public HashMap getStatus() {
		return status;
	}

	public void setStatus(HashMap status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
	

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getConsumer_number() {
		return consumer_number;
	}

	public void setConsumer_number(String consumer_number) {
		this.consumer_number = consumer_number;
	}

	public String getAdhar_card() {
		return adhar_card;
	}

	public void setAdhar_card(String adhar_card) {
		this.adhar_card = adhar_card;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public char getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(char delete_flag) {
		this.delete_flag = delete_flag;
	}

	public char getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(char accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountCreated_at() {
		return accountCreated_at;
	}

	public void setAccountCreated_at(String accountCreated_at) {
		this.accountCreated_at = accountCreated_at;
	}

	public String getAccountUpdated_at() {
		return accountUpdated_at;
	}

	public void setAccountUpdated_at(String accountUpdated_at) {
		this.accountUpdated_at = accountUpdated_at;
	}

	public HashMap toHashMapMain() {
		HashMap hmDet=new HashMap();
		hmDet.put("first_name",getFirst_name());
		hmDet.put("last_name",getLast_name());
		hmDet.put("email",getEmail());
		hmDet.put("address",getAddress());
		hmDet.put("phone_number",getPhone_number());
		hmDet.put("created_at",getCreated_at());
		hmDet.put("updated_at",getUpdated_at());
		hmDet.put("status",getStatus());
		return hmDet;
	}
	
	
	public HashMap toHashMapAccountdetails() { 
		HashMap hmDet=new HashMap();
		hmDet.put("first_name",getFirst_name());
		hmDet.put("last_name",getLast_name());
		hmDet.put("email",getEmail());
		hmDet.put("address",getAddress());
		hmDet.put("phone_number",getPhone_number());
		hmDet.put("account_number",getAccount_number());
		hmDet.put("account_type",getAccount_type());
		hmDet.put("balance",getBalance());
		hmDet.put("consumer_number",getConsumer_number());
		hmDet.put("adhar_card",getAdhar_card());
		hmDet.put("date_of_birth",getDate_of_birth());
		hmDet.put("photo",getPhoto());
		hmDet.put("branch",getBranch());
		hmDet.put("district",getDistrict());
		hmDet.put("state",getState());
		hmDet.put("pincode",getPincode());
		hmDet.put("delete_flag",getDelete_flag());
		hmDet.put("accountStatus",getAccountStatus());
		hmDet.put("accountCreated_at",getAccountCreated_at());
		hmDet.put("accountUpdated_at",getAccountUpdated_at());
		hmDet.put("remarks",getRemarks());
		hmDet.put("manager_remarks",getManager_remarks());
		return hmDet;
	}
    // Getters and Setters for Account
	
	
	
	
	
}
