package com.assignment.exceluploaddownload.entity;

/*
@Entity
@Table(name = "EXCEL_DATA")
*/
public class ExcelData {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	private Long id;

	private String columnHeading;

	private String[] columnValues;

	/*
	 * @ManyToOne(optional = false, fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "EXCEL_FILES_ID") private ExcelFile excelFile; public
	 * ExcelFile getExcelFile() { return excelFile;
	 * 
	 * }
	 * 
	 * public void setExcelFile(ExcelFile excelFile) { this.excelFile = excelFile; }
	 */

	public String[] getColumnValues() {
		return columnValues;
	}

	public String getColumnHeading() {
		return columnHeading;
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

	public void setId(Long id) {
		this.id = id;
	}

}
