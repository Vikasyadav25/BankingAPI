package project.banking.forms;

import java.util.HashMap;

public class Transactionforms {
	
	private String transaction_id;
	private String utr_number;
	private String account_number;
	private String transaction_type;
	private String amount;
	private String balance_after;
	private String transaction_date;
	private String from_account;
	private String to_account;
	private String remarks;
	private String status;
	 private HashMap statusre;
	
	
	
	public HashMap getStatusre() {
		return statusre;
	}
	public void setStatusre(HashMap statusre) {
		this.statusre = statusre;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getUtr_number() {
		return utr_number;
	}
	public void setUtr_number(String utr_number) {
		this.utr_number = utr_number;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalance_after() {
		return balance_after;
	}
	public void setBalance_after(String balance_after) {
		this.balance_after = balance_after;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getFrom_account() {
		return from_account;
	}
	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}
	public String getTo_account() {
		return to_account;
	}
	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public HashMap totransactionDeatils() {
		HashMap hmDet=new HashMap();
		hmDet.put("transaction_id",getTransaction_id());
		hmDet.put("utr_number",getUtr_number());
		hmDet.put("account_number",getAccount_number());
		hmDet.put("transaction_type",getTransaction_type());
		hmDet.put("amount",getAmount());
		hmDet.put("balance_after",getBalance_after());
		hmDet.put("transaction_date",getTransaction_date());
		hmDet.put("from_account",getFrom_account());
		hmDet.put("to_account",getTo_account());
		hmDet.put("remarks",getRemarks());
		hmDet.put("status",getStatus());
		hmDet.put("statusre",getStatusre());
		
		return hmDet;
	}
}
