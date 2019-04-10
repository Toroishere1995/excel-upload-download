package com.assignment.exceluploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.exceluploaddownload.entity.ExcelFile;

public interface ExcelFileRepository extends JpaRepository<ExcelFile, Long> {

}
