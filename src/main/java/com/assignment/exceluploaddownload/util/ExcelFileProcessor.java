package com.assignment.exceluploaddownload.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exceluploaddownload.dto.ExcelDataDto;

public class ExcelFileProcessor {
	
	public static ArrayList<ExcelDataDto> convertExcelToEntity(MultipartFile excelFile) {
		
		ArrayList<ExcelDataDto> columnNameWithValues=new ArrayList<ExcelDataDto>();
		try {
		
			File convFile = new File(excelFile.getOriginalFilename());
		    convFile.createNewFile(); 
		    FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(excelFile.getBytes());
		    fos.close();
			FileInputStream file = new FileInputStream(convFile);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(cell.getRowIndex()==0) {
						ExcelDataDto excelDataDto=new ExcelDataDto();
						excelDataDto.setColumnHeading(cell.getStringCellValue());
						ArrayList<String> values=new ArrayList<>();
						excelDataDto.setColumnValues(values);
						columnNameWithValues.add(excelDataDto);
					}else {
						ExcelDataDto excelDataDto=columnNameWithValues.get(cell.getColumnIndex());
						String cellValue="";
						if(cell.getCellType()==cell.CELL_TYPE_NUMERIC ) {
							cellValue=String.valueOf(cell.getNumericCellValue());
						}else if(cell.getCellType()==cell.CELL_TYPE_BOOLEAN){
							cellValue=String.valueOf(cell.getBooleanCellValue());
						}else {
							cellValue=cell.getStringCellValue();
						}
						excelDataDto.getColumnValues().add(cellValue);
						columnNameWithValues.set(cell.getColumnIndex(), excelDataDto);
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return columnNameWithValues;
	}
}
