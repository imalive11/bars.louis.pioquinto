package com.accenture.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

@Service
public class CSVInputFileImpl extends AbstractInputFile{

	public static final int BILLINGCYCLEINDEX = 0;
	public static final int STARTDATEINDEX = 1;
	public static final int ENDDATEINDEX = 2;
	private static final Logger log = 
			LoggerFactory.getLogger(CSVInputFileImpl.class);

	public CSVInputFileImpl() {
		//No-args Constructor is required to call the instance of this class
	}

	@Override
	public List<Request> readFile() throws IOException{
		List<Request> requests = new ArrayList<>();

		DateTimeFormatter startDateFormatter = 
				DateTimeFormatter.ofPattern("MM/d/yyyy");
		DateTimeFormatter endDateFormatter = 
				DateTimeFormatter.ofPattern("MM/d/yyyy");

		int billingCycle = 0;
		LocalDate startDate=null;
		LocalDate endDate=null;

		File filePath = getFile();
		String line ="";

		int row = 0;
		try (BufferedReader br =  
				new BufferedReader(new FileReader(filePath));){

			while ((line = br.readLine()) != null) {
				row++;
				String[] values = line.split(",");

				String stringBilling = values[BILLINGCYCLEINDEX];
				String stringStartDate = values[STARTDATEINDEX];
				String stringEndDate = values[ENDDATEINDEX];

				billingCycle = Integer.parseInt(stringBilling);	
				if (billingCycle < MIN_BILLING_CYCLE || 
						billingCycle > MAX_BILLING_CYCLE) {
					throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + 
							" " + row); 
				}

				startDate = LocalDate.parse(stringStartDate, startDateFormatter);
				endDate = LocalDate.parse(stringEndDate, endDateFormatter);

				requests.add(new Request(billingCycle,startDate, endDate));
				log.info("CSVInputFileImpl - ==> Processing Request ROW: " + row + " <==");
			}
		} catch (IOException e) {
			throw e;
		} catch (DateTimeParseException e) {
			if(endDate == null) {
				throw new BarsException(BarsException.INVALID_START_DATE_FORMAT
						+ " " + row);
			} else {
				throw new BarsException(BarsException.INVALID_END_DATE_FORMAT
						+ " " + row);
			}
		}
		return requests;
	}

}
