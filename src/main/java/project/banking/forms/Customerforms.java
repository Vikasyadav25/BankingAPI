package project.banking.forms;

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


    // Getters and Setters
}
