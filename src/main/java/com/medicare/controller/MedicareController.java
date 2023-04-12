package com.medicare.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.entity.GenericResponse;
import com.medicare.entity.Medicine;
import com.medicare.service.MedicareService;

@RestController
@RequestMapping("Medicare")
public class MedicareController {
	@Autowired
	MedicareService medicareService;
	GenericResponse genericResponse;

	@GetMapping("/getMedicinesByDisease/{disease}")
	public GenericResponse getMedicinesByDisease(@PathVariable("disease") String disease) {
		genericResponse = new GenericResponse();
		genericResponse.setResponseMessage("Medicine Details Retrieved Successfully");
		 genericResponse.setResultsList(medicareService.getMedicinesByDisease(disease));
		 return genericResponse;

	}
}
