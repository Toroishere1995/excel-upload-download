package com.assignment.exceluploaddownload.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.exception.ExcelFileNotFoundException;
import com.assignment.exceluploaddownload.exception.ExcelFileProcessingException;

@Component
public class MultipartFileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

		MultipartFile[] multipartFiles = (MultipartFile[]) target;
		if(multipartFiles.length==0) {
			throw new ExcelFileNotFoundException("No files added yet");
		}
		for (MultipartFile file : multipartFiles) {

			String filename = file.getOriginalFilename();
			String[] comp = filename.split("\\.");

			if (comp.length > 2) {
				throw new ExcelFileProcessingException("Not a valid file");
			}
			if (!(comp[1].equalsIgnoreCase("xls") || comp[1].equalsIgnoreCase("xlsx"))) {
				throw new ExcelFileProcessingException("Not an Excel File");
			}
			if (file.isEmpty()) {
				throw new ExcelFileProcessingException("Empty file");
			}

		}
	}

}
