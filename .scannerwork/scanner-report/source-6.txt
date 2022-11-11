package com.accenture.bars.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.entity.Billing;
import com.accenture.bars.exception.BarsException;
import com.accenture.bars.factory.InputFileFactory;
import com.accenture.bars.repository.BillingRepository;

@Component
public class FileProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);
	private BillingRepository billingRepository;
	
	@Autowired
	public FileProcessor(BillingRepository billingRepository) {
		this.billingRepository = billingRepository;
	}
	
	public FileProcessor() {
	}
	
	public List<Request> execute(File file) throws BarsException, IOException{
		
		InputFileFactory inputFile = InputFileFactory.getInstance();
		List<Request> requests = 
				inputFile.getInputFile(file).readFile();
		if (requests.isEmpty()) {
			throw new BarsException(BarsException.NO_REQUEST_TO_READ);
		}
		return requests;
	}
	
	public List<Record> retrieveRecordFromDB(List<Request> requests){
		List<Record> records = new ArrayList<>();
		for (Request request : requests) {
			Billing billing = 
					billingRepository.findByBillingCycleAndStartDateAndEndDate(
							request.getBillingCycle(),
							request.getStartDate(),
							request.getEndDate());
			if (billing != null) { 
				Record record = new Record();
				record.setAccountName(billing.getAccountId().
						getAccountName());
				record.setBillingCycle(billing.getBillingCycle());
				record.setStartDate(billing.getStartDate());
				record.setEndDate(billing.getEndDate());
				record.setAmount(billing.getAmount());
				record.setFirstName(billing.getAccountId().
						getCustomerId().
						getFirstName());
				record.setLastName(billing.getAccountId().
						getCustomerId().
						getLastName());
				records.add(record);
            }
		}
		if (records.isEmpty()) {
			throw new BarsException(BarsException.NO_REQUEST_TO_WRITE);
		} else {
			log.info("Successfully processed Request file");
			return records;
		}
	}	
}
