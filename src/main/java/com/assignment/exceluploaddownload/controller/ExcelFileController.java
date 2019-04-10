package com.assignment.exceluploaddownload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.service.ExcelFileStorageService;

@RestController
@RequestMapping("api/v1/")
public class ExcelFileController {

	@Autowired
	private ExcelFileStorageService excelFileStorageService;
	
	@RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
	public Object uploadExcelFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
		
		
		return ResponseEntity.ok()
				.body(excelFileStorageService.storeFiles(files));
		}
	
	@RequestMapping(value = "downloadFile", method = RequestMethod.GET)
	public Object downloadExcelFile() {
		
		return null;
	}
}
