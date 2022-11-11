package com.accenture.bars.domain;

import java.time.LocalDate;

public class Request {
	
	private int billingCycle;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Request() {
		
	}

	public Request(int billingCycle, LocalDate startDate, LocalDate endDate) {
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
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
	

}
