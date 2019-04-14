package com.assignment.exceluploaddownload.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.payload.ResponsePayload;
import com.assignment.exceluploaddownload.service.ExcelFileStorageService;

/**
 * Controller for handling api hits for upload and download.
 * 
 * @author ayushsaxena
 *
 */
@RestController
@RequestMapping("api/v1/")
public class ExcelFileController {

	@Autowired
	private ExcelFileStorageService excelFileStorageService;

	/**
	 * Method to upload files.
	 * 
	 * @param files
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
	public ResponseEntity<ResponsePayload> uploadExcelFiles(@RequestParam("files") MultipartFile[] files)
			throws IOException {

		ResponsePayload payload = new ResponsePayload("201", "Excel Files uploaded successfully",
				excelFileStorageService.storeFiles(files), "POST SUCCESSFUL");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

	/**
	 * Method for downloadingExcelFile.
	 * 
	 * @param file1Id
	 * @param file2Id
	 * @param headings
	 * @return
	 */
	@RequestMapping(value = "downloadFile", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> downloadExcelFile(@RequestParam("file1Id") String file1Id,
			@RequestParam("file2Id") String file2Id, @RequestBody ArrayList<String> headings) {

		ByteArrayInputStream stream = excelFileStorageService.downloadFile(file1Id, file2Id, headings);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "merged.xlsx" + "\"")
				.body(new InputStreamResource(stream));

	}
}
