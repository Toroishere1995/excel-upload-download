package com.assignment.exceluploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.exceluploaddownload.entity.ExcelData;

public interface ExcelDataRepository extends JpaRepository<ExcelData, Long> {

}
