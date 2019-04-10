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

	private String columnName;

	private String[] columnData ;

	public void setColumnData(String[] columnData) {
		this.columnData = columnData;
	}

	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "EXCEL_FILES_ID")
	private ExcelFile excelFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public ExcelFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(ExcelFile excelFile) {
		this.excelFile = excelFile;
	}

}
