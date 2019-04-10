package com.assignment.exceluploaddownload.payload;

import java.util.ArrayList;
import java.util.List;

public class UploadResponse {

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