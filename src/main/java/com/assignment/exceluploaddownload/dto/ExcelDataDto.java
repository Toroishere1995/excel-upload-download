package com.assignment.exceluploaddownload.dto;

import java.util.ArrayList;

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
