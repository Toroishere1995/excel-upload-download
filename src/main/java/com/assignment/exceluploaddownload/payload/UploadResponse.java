package com.assignment.exceluploaddownload.payload;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for result attribute of ResponsePayload.
 * 
 * @author ayushsaxena
 *
 */
public class UploadResponse {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private List<String> coloumnHeadings = new ArrayList<>();

	private String filename;

	public List<String> getColoumnHeadings() {
		return coloumnHeadings;
	}

	public String getFilename() {
		return filename;
	}

	public void setColoumnHeadings(List<String> coloumnHeadings) {
		this.coloumnHeadings = coloumnHeadings;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
