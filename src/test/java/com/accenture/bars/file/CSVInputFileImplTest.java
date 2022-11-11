package com.accenture.bars.file;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

class CSVInputFileImplTest {

	@Test
	void testCSVValidReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/valid-csv.csv");
		CSVInputFileImpl CSVFileReader = new CSVInputFileImpl();
		CSVFileReader.setFile(file);
		System.out.println("testCSVValidReadFile()");
		
		List<Request> actualRequests = new ArrayList<>();
		List<Request> expectedRequests = new ArrayList<>();
		
		for (Request request :CSVFileReader.readFile()) {
			Request actualRequest = new Request();
			
			String startDate = request.getStartDate().now()
					.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String endDate = request.getEndDate().now()
					.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			actualRequest.setStartDate(request.getStartDate());
			actualRequest.setEndDate(request.getEndDate());
			actualRequest.setBillingCycle(request.getBillingCycle());
			actualRequests.add(actualRequest);
			
			System.out.println(request.getBillingCycle() + " " + startDate + " " + endDate);
		}
		System.out.println("=======================");
	}
	
	@Test
	void testCSVInvalidBillingCycleReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/billing-cycle-not-on-range-csv.csv");
		CSVInputFileImpl CSVFileReader = new CSVInputFileImpl();
		CSVFileReader.setFile(file);
		System.out.println("testCSVInvalidBillingCycleReadFile()");
		try {
			CSVFileReader.readFile();
			} catch(BarsException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				throw new IOException();
			}
		System.out.println("=======================");
	}
	
	@Test
	void testCSVInvalidStartDateFormatReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/invalid-start-date-csv.csv");
		CSVInputFileImpl CSVFileReader = new CSVInputFileImpl();
		CSVFileReader.setFile(file);
		System.out.println("CSVTextInvalidStartDateFormatReadFile()");
		try {
			CSVFileReader.readFile();
			} catch(BarsException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				throw new IOException();
			}
		System.out.println("=======================");
	}
	
	@Test
	void testCSVInvalidEndDateFormatReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/invalid-end-date-txt.txt");
		TextInputFileImpl textFileReader = new TextInputFileImpl();
		textFileReader.setFile(file);
		System.out.println("testTextInvalidEndDateFormatReadFile()");
		try {
			textFileReader.readFile();
			} catch(BarsException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				throw new IOException();
			}
		System.out.println("=======================");
	}
}
