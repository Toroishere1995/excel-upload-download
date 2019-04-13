package com.assignment.exceluploaddownload.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.assignment.exceluploaddownload.exception.ExcelFileNotFoundException;
import com.assignment.exceluploaddownload.exception.ExcelFileProcessingException;

public class ExcelFileProcessor {

	/*
	 * public static ArrayList<ExcelDataDto> convertExcelToEntity(MultipartFile
	 * excelFile) {
	 * 
	 * ArrayList<ExcelDataDto> columnNameWithValues = new ArrayList<ExcelDataDto>();
	 * try {
	 * 
	 * File convFile = new File(excelFile.getOriginalFilename());
	 * convFile.createNewFile(); FileOutputStream fos = new
	 * FileOutputStream(convFile); fos.write(excelFile.getBytes()); fos.close();
	 * FileInputStream file = new FileInputStream(convFile); XSSFWorkbook workbook =
	 * new XSSFWorkbook(file); XSSFSheet sheet = workbook.getSheetAt(0);
	 * 
	 * Iterator<Row> rowIterator = sheet.iterator(); while (rowIterator.hasNext()) {
	 * Row row = rowIterator.next(); Iterator<Cell> cellIterator =
	 * row.cellIterator();
	 * 
	 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); if
	 * (cell.getRowIndex() == 0) { ExcelDataDto excelDataDto = new ExcelDataDto();
	 * excelDataDto.setColumnHeading(cell.getStringCellValue()); ArrayList<String>
	 * values = new ArrayList<>(); excelDataDto.setColumnValues(values);
	 * columnNameWithValues.add(excelDataDto); } else { ExcelDataDto excelDataDto =
	 * columnNameWithValues.get(cell.getColumnIndex()); String cellValue = ""; if
	 * (cell.getCellType() == cell.CELL_TYPE_NUMERIC) { cellValue =
	 * String.valueOf(cell.getNumericCellValue()); } else if (cell.getCellType() ==
	 * cell.CELL_TYPE_BOOLEAN) { cellValue =
	 * String.valueOf(cell.getBooleanCellValue()); } else { cellValue =
	 * cell.getStringCellValue(); } excelDataDto.getColumnValues().add(cellValue);
	 * columnNameWithValues.set(cell.getColumnIndex(), excelDataDto); } }
	 * 
	 * } } catch (IOException e) { e.printStackTrace(); } return
	 * columnNameWithValues; }
	 */

	public static ArrayList<String> retrieveHeadersFromExcelFile(byte[] excelFile) {
		ArrayList<String> headers = new ArrayList<>();
		try {
			XSSFSheet sheet = getXSSFSheetForFile(excelFile);
			XSSFRow rowSheet1 = sheet.getRow(0);
			for (int i = rowSheet1.getFirstCellNum(); i < rowSheet1.getLastCellNum(); i++) {
				String headerValue = rowSheet1.getCell(i).getStringCellValue();
				headers.add(headerValue);
			}
		} catch (Exception e) {
			throw new ExcelFileProcessingException("Headers are not valid text.");
		}
		return headers;
	}

	public static ByteArrayInputStream concatenateTwoFilesWithOrder(String[] columnOrder, byte[] file1, byte[] file2) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			XSSFSheet firstFileSheet = getXSSFSheetForFile(file1);
			XSSFSheet secondFileSheet = getXSSFSheetForFile(file2);
			XSSFWorkbook outWorkBook = reArrange(firstFileSheet, secondFileSheet, columnOrder);
			outWorkBook.write(out);
			out.close();
			System.out.println("File Columns Were Re-Arranged Successfully");
		} catch (IOException e) {
			throw new ExcelFileNotFoundException("File not found or maybe it is corrupt");

		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	private static XSSFWorkbook reArrange(XSSFSheet firstFileSheet, XSSFSheet secondFileSheet, String[] columnOrder) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> headerMap = mapHeaders(columnOrder, firstFileSheet, secondFileSheet);
		XSSFWorkbook outWorkbook = new XSSFWorkbook();
		XSSFSheet outSheet = outWorkbook.createSheet();

		int columnNum = 0;
		XSSFRow headerRow = outSheet.createRow(0);
		for (String column : columnOrder) {
			XSSFCell cell = headerRow.createCell(columnNum);
			cell.setCellValue(column);
			columnNum++;
		}
		int lastRowNum = firstFileSheet.getLastRowNum() > secondFileSheet.getLastRowNum()
				? firstFileSheet.getLastRowNum()
				: secondFileSheet.getLastRowNum();
		int rowCount = 1;
		while (rowCount <= lastRowNum) {
			XSSFRow createdRow = outSheet.createRow(rowCount);
			int cellNum = -1;
			XSSFRow firstFileRow = firstFileSheet.getRow(rowCount);
			XSSFRow secondFileRow = secondFileSheet.getRow(rowCount);
			for (String column : columnOrder) {
				String value = headerMap.get(column);
				Integer index = Integer.parseInt(value.substring(2));
				cellNum++;
				if (value.startsWith("f_")) {
					fillCellsOfOutFile(outSheet, firstFileRow, cellNum, index, createdRow);
				} else if (value.startsWith("s_")) {
					fillCellsOfOutFile(outSheet, secondFileRow, cellNum, index, createdRow);
				}
			}
			rowCount++;
		}
		return outWorkbook;
	}

	private static void fillCellsOfOutFile(XSSFSheet outSheet, XSSFRow fileRow, int cellNum, Integer columnIndex,
			XSSFRow createdRow) {

		if (fileRow == null) {
			XSSFCell outCellDummy = createdRow.createCell(cellNum);
			outCellDummy.setCellType(HSSFCell.CELL_TYPE_BLANK);
			return;
		}
		XSSFCell cell = fileRow.getCell(columnIndex.intValue());
		if (cell == null) {
			return;
		}
		XSSFCell outCell = createdRow.createCell(cellNum);

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			outCell.setCellFormula(cell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			outCell.setCellValue(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING:
			outCell.setCellValue(cell.getStringCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			outCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			outCell.setCellValue(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			outCell.setCellErrorValue(cell.getErrorCellValue());
			break;
		default:
			outCell.setCellValue(cell.getStringCellValue());
			break;
		}

	}

	private static XSSFSheet getXSSFSheetForFile(byte[] file) {
		File convFile = new File("file");
		FileOutputStream fos;
		FileInputStream fileInput;
		XSSFWorkbook fileWorkbook;
		XSSFSheet mainSheet = null;
		try {
			convFile.createNewFile();
			fos = new FileOutputStream(convFile);
			fos.write(file);
			fos.close();
			fileInput = new FileInputStream(convFile);
			fileWorkbook = new XSSFWorkbook(fileInput);
			mainSheet = fileWorkbook.getSheetAt(0);

		} catch (IOException e) {

			throw new ExcelFileNotFoundException("File not found or maybe it is corrupt");
		}
		if (mainSheet == null) {
			// throw ;
		}
		return mainSheet;
	}

	public static LinkedHashMap<String, String> mapHeaders(String[] columnOrder, XSSFSheet firstFileSheet,
			XSSFSheet secondFileSheet) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		XSSFRow rowSheet1 = firstFileSheet.getRow(0);
		XSSFRow rowSheet2 = secondFileSheet.getRow(0);
		for (String column : columnOrder) {
			Integer colNum = new Integer(-1);
			StringBuilder value = new StringBuilder("");
			for (int i = rowSheet1.getFirstCellNum(); i < rowSheet1.getLastCellNum(); i++) {
				if (rowSheet1.getCell(i).getStringCellValue().equals(column)) {
					colNum = new Integer(i);
					break;
				}
			}
			if (colNum.intValue() == -1) {
				for (int j = rowSheet2.getFirstCellNum(); j < rowSheet2.getLastCellNum(); j++) {
					if (rowSheet2.getCell(j).getStringCellValue().equals(column)) {
						colNum = new Integer(j);
						break;
					}
				}
				if(colNum.intValue() == -1) {
					throw new ExcelFileProcessingException("Header specified does not exist");
				}
				value.append("s_");

			} else {
				value.append("f_");

			}
			value.append(colNum);
			map.put(column, value.toString());
		}
		return map;
	}
}
