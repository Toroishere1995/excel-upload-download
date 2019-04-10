package com.assignment.exceluploaddownload.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXCEL_DATA")
public class ExcelData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String columnHeading;

	private String[] columnValues;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "EXCEL_FILES_ID")
	private ExcelFile excelFile;

	public String[] getColumnValues() {
		return columnValues;
	}

	public String getColumnHeading() {
		return columnHeading;
	}

	public ExcelFile getExcelFile() {
		return excelFile;
	}

	public Long getId() {
		return id;
	}

	public void setColumnValues(String[] columnValues) {
		this.columnValues = columnValues;
	}

	public void setColumnHeading(String columnHeading) {
		this.columnHeading = columnHeading;
	}

	public void setExcelFile(ExcelFile excelFile) {
		this.excelFile = excelFile;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
