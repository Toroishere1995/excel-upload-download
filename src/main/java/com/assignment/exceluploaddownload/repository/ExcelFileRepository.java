package com.assignment.exceluploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.exceluploaddownload.entity.ExcelFile;

/**
 * Repository for handling CRUD with DB.
 * 
 * @author ayushsaxena
 *
 */
public interface ExcelFileRepository extends JpaRepository<ExcelFile, Long> {

}
