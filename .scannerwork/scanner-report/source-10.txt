package com.accenture.bars.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "billing")
public class Billing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "billing_id")
	private int billingId;
	
	@Column(name="billing_cycle")
	private int billingCycle;
	
	@Column(name="billing_month")
	private String billingMonth;

	@Column(name="amount")
	private Double amount;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="last_edited")
	private String lastEdited;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account accountId;
	
	public Billing() {
		//No-args Constructor is required calling class object without parameters
	}

	public Billing(int billingId, 
			int billingCycle, 
			String billingMonth, 
			Double amount, 
			LocalDate startDate,
			LocalDate endDate, 
			String lastEdited) {
		
		this.billingId = billingId;
		this.billingCycle = billingCycle;
		this.billingMonth = billingMonth;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastEdited = lastEdited;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "Billing [billingId=" + billingId + ", billingCycle=" + billingCycle +
				", billingMonth=" + billingMonth+ ", amount=" + amount +
				", startDate=" + startDate + ", endDate=" + endDate +
				", lastEdited=" + lastEdited + ", accountId=" + accountId + "]";
	}
	
	
	
	

}
