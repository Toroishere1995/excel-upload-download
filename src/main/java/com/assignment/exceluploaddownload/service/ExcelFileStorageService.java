package com.assignment.exceluploaddownload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.constants.Constants;
import com.assignment.exceluploaddownload.entity.ExcelFile;
import com.assignment.exceluploaddownload.exception.ExcelFileNotFoundException;
import com.assignment.exceluploaddownload.payload.UploadResponse;
import com.assignment.exceluploaddownload.repository.ExcelFileRepository;
import com.assignment.exceluploaddownload.util.ExcelFileProcessor;
import com.assignment.exceluploaddownload.validation.MultipartFileValidator;

/**
 * Service class for handling business operations consisting of excel file
 * processing.
 * 
 * @author ayushsaxena
 *
 */
@Service
public class ExcelFileStorageService {

	/*
	 * @Autowired private ExcelDataRepository excelDataRepository;
	 */

	@Autowired
	private ExcelFileRepository excelFileRepository;

	@Autowired
	private MultipartFileValidator validator;

	/**
	 * Method to store excel files in db, and return the list of headers and
	 * filename.
	 * 
	 * @param uploadedFiles
	 * @return
	 */
	public List<UploadResponse> storeFiles(MultipartFile[] uploadedFiles) {
		validator.validate(uploadedFiles, null);
		List<UploadResponse> response = new ArrayList<>();
		for (MultipartFile uploadedFile : uploadedFiles) {
			UploadResponse uploadResponse = new UploadResponse();
			ArrayList<String> columns;
			ExcelFile excelFile;
			try {
				columns = ExcelFileProcessor.retrieveHeadersFromExcelFile(uploadedFile.getBytes());
				excelFile = storeExcelFile(uploadedFile);
			} catch (IOException e) {
				throw new ExcelFileNotFoundException(Constants.CURROPT_FILE);
			}
			uploadResponse.setFilename(excelFile.getName());
			uploadResponse.setId(excelFile.getId());
			uploadResponse.setColoumnHeadings(columns);
			response.add(uploadResponse);
		}
		return response;
	}

	/**
	 * Method to store Excel file in DB.
	 * 
	 * @param uploadedFile
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * Method for downloading excel file.
	 * 
	 * @param file1Id
	 * @param file2Id
	 * @param headings
	 * @return
	 */
	public ByteArrayInputStream downloadFile(String file1Id, String file2Id, ArrayList<String> headings) {
		ByteArrayInputStream stream = null;
		try {
			ExcelFile excelFile = excelFileRepository.getOne(Long.parseLong(file1Id));

			ExcelFile excelFile2 = excelFileRepository.getOne(Long.parseLong(file2Id));
			String columnOrder[] = new String[headings.size()];
			columnOrder = headings.toArray(columnOrder);
			stream = ExcelFileProcessor.concatenateTwoFilesWithOrder(columnOrder, excelFile.getContent(),
					excelFile2.getContent());

		} catch (EntityNotFoundException e) {
			throw new ExcelFileNotFoundException(Constants.INVALID_FILE_ID);
		}
		return stream;
	}
}
