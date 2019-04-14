package com.assignment.exceluploaddownload.dto;

import java.util.ArrayList;

/**
 * Class used as a DTO for sending required data as a response.
 * 
 * @author ayushsaxena
 *
 */
public class ExcelDataDto {

	private String columnHeading;

	private ArrayList<String> columnValues;

	public String getColumnHeading() {
		return columnHeading;
	}

	public void setColumnHeading(String columnHeading) {
		this.columnHeading = columnHeading;
	}

	public ArrayList<String> getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(ArrayList<String> columnValues) {
		this.columnValues = columnValues;
	}
}
