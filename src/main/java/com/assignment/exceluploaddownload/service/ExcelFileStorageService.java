package com.assignment.exceluploaddownload.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.entity.ExcelFile;
import com.assignment.exceluploaddownload.payload.UploadResponse;
import com.assignment.exceluploaddownload.repository.ExcelFileRepository;
import com.assignment.exceluploaddownload.util.ExcelFileProcessor;

@Service
public class ExcelFileStorageService {

	/*
	 * @Autowired private ExcelDataRepository excelDataRepository;
	 */

	@Autowired
	private ExcelFileRepository excelFileRepository;

	public Object storeFiles(MultipartFile[] uploadedFiles) throws IOException {
		ArrayList<UploadResponse> response = new ArrayList<>();
		for (MultipartFile uploadedFile : uploadedFiles) {
			UploadResponse uploadResponse = new UploadResponse();
			ExcelFile excelFile = storeExcelFile(uploadedFile);
			uploadResponse.setFilename(excelFile.getName());
			ArrayList<String> columns = ExcelFileProcessor.retrieveHeadersFromExcelFile(excelFile.getContent());
			uploadResponse.setColoumnHeadings(columns);
			response.add(uploadResponse);
		}
		return response;
	}

	private ExcelFile storeExcelFile(MultipartFile uploadedFile) throws IOException {
		ExcelFile excelFile = new ExcelFile();
		excelFile.setContent(uploadedFile.getBytes());
		excelFile.setName(uploadedFile.getOriginalFilename());
		return excelFileRepository.saveAndFlush(excelFile);
	}

	/*
	 * private ExcelData storeExcelDataValues(ExcelDataDto excelDataDto, ExcelFile
	 * excelFile) { ExcelData excelData = new ExcelData(); String[] columnValues =
	 * new String[excelDataDto.getColumnValues().size()]; columnValues =
	 * excelDataDto.getColumnValues().toArray(columnValues);
	 * excelData.setColumnValues(columnValues);
	 * excelData.setColumnHeading(excelDataDto.getColumnHeading());
	 * excelData.setExcelFile(excelFile); excelFile.getData().add(excelData); return
	 * excelDataRepository.save(excelData); }
	 */
	public void downloadFile(String file1Id, String file2Id) {
		ExcelFile excelFile = excelFileRepository.getOne(Long.parseLong(file1Id));
		ExcelFile excelFile2 = excelFileRepository.getOne(Long.parseLong(file2Id));
		String columnOrder[] = { "ID", "NAME", "FAME", "GAME", "DI", "LASTNAME" };
		ExcelFileProcessor.concatenateTwoFilesWithOrder(columnOrder, excelFile.getContent(), excelFile2.getContent());
	}
}
