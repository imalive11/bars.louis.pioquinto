package com.accenture.bars.factory;

import java.io.File;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;

public class InputFileFactory {
	
	private static InputFileFactory factory;
	
	private InputFileFactory() {
		
	}
	
	public static InputFileFactory getInstance() {
		if (factory == null) {
			factory = new InputFileFactory();
		}
		return factory;
	}
	
	public AbstractInputFile getInputFile(File file) throws BarsException{
		
		String fileName = file.getName();
		int i =  fileName.lastIndexOf('.');
		String fileType = fileName.substring(i+1);
	
		if(fileType.equals("csv")) {
			AbstractInputFile csvFileReader = new CSVInputFileImpl();
			csvFileReader.setFile(file);
			return csvFileReader;
		}else if(fileType.equals("txt")){
			AbstractInputFile textFileReader = new TextInputFileImpl();	
			textFileReader.setFile(file);
			return textFileReader;
		} else {
			throw new BarsException(BarsException.FILE_NOT_SUPPORTED);
		}	
	}

}
