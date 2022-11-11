package com.accenture.bars.exception;

public class BarsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String INVALID_START_DATE_FORMAT =
			"ERROR: Invalid Start Date Format at row";
	public static final String INVALID_END_DATE_FORMAT =
			"ERROR: Invalid End Date Format at row";
	public static final String INVALID_BILLING_CYCLE =
			"ERROR: Invalid Billing Cycle Value at row";
	public static final String BILLING_CYCLE_NOT_ON_RANGE =
			"ERROR: Billing Cycle not on range at row";
	public static final String PATH_DOES_NOT_EXIST =
			"Please use an existing request file path";
	public static final String NO_SUPPORTED_FILE =
			"No supported request file found in the file path";
	public static final String FILE_NOT_SUPPORTED =
			"File is not supported for processing";
	public static final String NO_REQUEST_TO_READ =
			"No record(s) to read to the ouput file";
	public static final String NO_REQUEST_TO_WRITE =
			"No record(s) to write to the ouput file";
	
	public BarsException(String message, Throwable cause) {
		super(message, cause);
	}
	public BarsException(String message) {
		super(message);	
	}
	

}

