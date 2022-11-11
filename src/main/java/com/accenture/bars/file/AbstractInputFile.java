package com.accenture.bars.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.bars.domain.Request;

@Service
public abstract class AbstractInputFile {
	
	public static final int MIN_BILLING_CYCLE = 1;
	public static final int MAX_BILLING_CYCLE = 12;
	
	private File file;
	
	public AbstractInputFile() {
		
	}
	
	public abstract List<Request> readFile() throws IOException;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
