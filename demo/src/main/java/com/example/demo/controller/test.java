package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FaceDemo;

@RestController
@Validated
public class test {
	
	@Autowired
	private FaceDemo fDemo;
	
	@RequestMapping(value = "/helloWord",method = RequestMethod.GET)
	public String getFace() {
		
		return "hello Word!";
	}

}
