package com.accenture.bars.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.bars.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer>{
	
	public Billing findByBillingCycleAndStartDateAndEndDate
	(int billingCycle, LocalDate startDate, LocalDate endDate);
	
}
