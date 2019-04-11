package com.assignment.exceluploaddownload.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EXCEL_FILES")
public class ExcelFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Lob
	private byte[] content;

	/*
	 * @OneToMany(cascade = CascadeType.ALL ,mappedBy = "excelFile" ) private
	 * Set<ExcelData> data=new HashSet<ExcelData>(); public Set<ExcelData> getData()
	 * { return data; }
	 * 
	 * public void setData(Set<ExcelData> data) { this.data = data; }
	 */
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
