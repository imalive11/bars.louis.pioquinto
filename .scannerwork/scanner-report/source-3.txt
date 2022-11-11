package com.accenture.bars.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.bars.domain.Record;
import com.accenture.bars.exception.BarsException;


@RestController
public class BarsController {
	
	private static final Logger log = 
			LoggerFactory.getLogger(BarsController.class);
	
	private FileProcessor fileProcessor;
	
	public BarsController(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}

	@GetMapping("/bars")
	public List<Record> requestBilling(@RequestParam("filePath") String fileName)
			throws BarsException, IOException {
		String filePath = "C:/BARS_TEST/" + fileName;
		File file = new File(filePath);
		
		log.info("BarsController - ===========t==> FileName = " + fileName);
		log.info("BarsController - =============> FilePath = " + filePath);
		
		return fileProcessor.retrieveRecordFromDB(fileProcessor.execute(file));
	}
	
}