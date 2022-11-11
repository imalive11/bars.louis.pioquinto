package com.accenture.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;

@Service
public class TextInputFileImpl extends AbstractInputFile{
	
	public static final int BILLINGCYCLEENDRANGE = 2;
	public static final int STARTDATEINITIALRANGEYEAR = 6;
	public static final int STARTDATEENDRANGEYEAR = 10;
	public static final int STARTDATEINITIALRANGEMONTH = 2;
	public static final int STARTDATEENDRANGEMONTH = 4;
	public static final int STARTDATEINITIALRANGEDAY = 4;
	public static final int STARTDATEENDRANGEDAY = 6;
	
	public static final int ENDDATEINITIALRANGEYEAR = 14;
	public static final int ENDDATEENDRANGEYEAR = 18;
	public static final int ENDDATEINITIALRANGEMONTH = 10;
	public static final int ENDDATEENDRANGEMONTH = 12;
	public static final int ENDDATEINITIALRANGEDAY = 12;
	public static final int ENDDATEENDRANGEDAY = 14;

	private static final Logger log = 
			LoggerFactory.getLogger(TextInputFileImpl.class);

	public TextInputFileImpl() {
		//No-args Constructor is required to call the instance of this class
	}

	@Override
	public List<Request> readFile() throws IOException {

		List<Request> requests = new ArrayList<>();

		int billingCycle = 0;
		LocalDate startDate = null;
		LocalDate endDate = null;

		File filePath = getFile();
		String line ="";

		int row = 0;

		try (BufferedReader br =  new BufferedReader(new FileReader(filePath))){

			while ((line = br.readLine()) != null) {
				row++;
				String stringBilling = line.substring(0,BILLINGCYCLEENDRANGE);
				String stringStartDate = 
						line.substring(STARTDATEINITIALRANGEYEAR,STARTDATEENDRANGEYEAR) + "-" + 
						line.substring(STARTDATEINITIALRANGEMONTH,STARTDATEENDRANGEMONTH) + "-" + 
						line.substring(STARTDATEINITIALRANGEDAY,STARTDATEENDRANGEDAY);
				String stringEndDate = 
						line.substring(ENDDATEINITIALRANGEYEAR,ENDDATEENDRANGEYEAR) + "-" + 
						line.substring(ENDDATEINITIALRANGEMONTH,ENDDATEENDRANGEMONTH) + "-" + 
						line.substring(ENDDATEINITIALRANGEDAY,ENDDATEENDRANGEDAY);

				billingCycle = Integer.parseInt(stringBilling);
				if (billingCycle < MIN_BILLING_CYCLE || 
						billingCycle > MAX_BILLING_CYCLE) {
					throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE +
							" " + row);
				}
				
				startDate = LocalDate.parse(stringStartDate);
				endDate = LocalDate.parse(stringEndDate);

				requests.add(new Request(billingCycle,startDate, endDate));
				log.info("TextInputFileImpl - ==> Processing Request ROW: " + row + " <==");
			}
		}catch (DateTimeParseException e) {
			if(endDate == null) {
				throw new BarsException(BarsException.INVALID_END_DATE_FORMAT +
						" " + row);
			} else {
				throw new BarsException(BarsException.INVALID_START_DATE_FORMAT +
						" " + row);
			}
		} catch (IOException e) {
			throw e;
		}
		return requests;
	}

}
