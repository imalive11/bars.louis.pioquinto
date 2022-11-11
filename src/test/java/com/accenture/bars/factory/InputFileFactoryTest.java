package com.accenture.bars.factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;

class InputFileFactoryTest {

	@Test
	void testGetInstance() {
		InputFileFactory input = InputFileFactory.getInstance();
		
		assertTrue(input instanceof InputFileFactory);
	}

	@Test
	void testCSVGetInputFile() {
		InputFileFactory input = InputFileFactory.getInstance();
		File file = 
				new File("C:/BARS_TEST/valid-csv.csv");
		
		assertTrue(input.getInputFile(file) instanceof CSVInputFileImpl);
	}
	
	@Test
	void testTextGetInputFile() {
		InputFileFactory input = InputFileFactory.getInstance();
		File file = 
				new File("C:/BARS_TEST/valid-txt.txt");
		assertTrue(input.getInputFile(file) instanceof TextInputFileImpl);
	}
	
	@Test
	void testNotSupportedGetInputFile() {
		InputFileFactory input = InputFileFactory.getInstance();
		File file = 
				new File("C:/BARS_TEST/unsupported-file.png");
		try {
		input.getInputFile(file);
		} catch(BarsException e) {
			System.out.println(e.getMessage());
		}
	}
}
