package com.assignment.exceluploaddownload.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dummy controller for application setup.
 * 
 * @author ayushsaxena
 *
 */
@RestController
public class HomeController {

	/**
	 * Dummy Method .
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String home() {
		return "Das Boot,reporting for duty";
	}
}
