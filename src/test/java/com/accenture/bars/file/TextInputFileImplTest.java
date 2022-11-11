package com.accenture.bars.file;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

class TextInputFileImplTest {

	@Test
	void testTextValidReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/valid-txt.txt");
		TextInputFileImpl textFileReader = new TextInputFileImpl();
		textFileReader.setFile(file);
		System.out.println("testTextValidReadFile()");
		for (Request request :textFileReader.readFile()) {
			String startDate = request.getStartDate().now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String endDate = request.getEndDate().now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

			System.out.println(request.getBillingCycle() + " " + startDate + " " + endDate);
		}
		System.out.println("=======================");
	}
	
	@Test
	void testTextInvalidBillingCycleReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/billing-cycle-not-on-range-txt.txt");
		TextInputFileImpl textFileReader = new TextInputFileImpl();
		textFileReader.setFile(file);
		System.out.println("testTextInvalidBillingCycleReadFile()");
		try {
			textFileReader.readFile();
			} catch(BarsException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				throw new IOException();
			}
		
		System.out.println("=======================");
	}
	
	@Test
	void testTextInvalidStartDateFormatReadFile() throws IOException {
		File file = 
				new File("C:/BARS_TEST/invalid-start-date-txt.txt");
		TextInputFileImpl textFileReader = new TextInputFileImpl();
		textFileReader.setFile(file);
		System.out.println("testTextInvalidStartDateFormatReadFile()");
		try {
			textFileReader.readFile();
			} catch(BarsException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				throw new IOException();
			}
		
		System.out.println("=======================");
	}
	
	@Test
	void testTextInvalidEndDateFormatReadFile() throws IOException {
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
