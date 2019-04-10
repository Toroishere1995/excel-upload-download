package com.assignment.exceluploaddownload.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.dto.ExcelDataDto;
import com.assignment.exceluploaddownload.entity.ExcelData;
import com.assignment.exceluploaddownload.entity.ExcelFile;
import com.assignment.exceluploaddownload.payload.UploadResponse;
import com.assignment.exceluploaddownload.repository.ExcelDataRepository;
import com.assignment.exceluploaddownload.repository.ExcelFileRepository;
import com.assignment.exceluploaddownload.util.ExcelFileProcessor;

@Service
public class ExcelFileStorageService {

	@Autowired
	private ExcelDataRepository excelDataRepository;

	@Autowired
	private ExcelFileRepository excelFileRepository;

	public Object storeFiles(MultipartFile[] uploadedFiles) throws IOException {
		ArrayList<UploadResponse> response = new ArrayList<>();
		for (MultipartFile uploadedFile : uploadedFiles) {
			UploadResponse uploadResponse = new UploadResponse();
			ExcelFile excelFile = storeExcelFile(uploadedFile);
			uploadResponse.setFilename(excelFile.getName());
			ArrayList<ExcelDataDto> columns = ExcelFileProcessor.convertExcelToEntity(uploadedFile);
			for (ExcelDataDto column : columns) {
				storeExcelDataValues(column, excelFile);
				uploadResponse.getColoumnHeadings().add(column.getColumnHeading());
			}

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

	private ExcelData storeExcelDataValues(ExcelDataDto excelDataDto, ExcelFile excelFile) {
		ExcelData excelData = new ExcelData();
		String[] columnValues = new String[excelDataDto.getColumnValues().size()];
		columnValues = excelDataDto.getColumnValues().toArray(columnValues);
		excelData.setColumnValues(columnValues);
		excelData.setColumnHeading(excelDataDto.getColumnHeading());
		excelData.setExcelFile(excelFile);
		excelFile.getData().add(excelData);
		return excelDataRepository.save(excelData);
	}
}
