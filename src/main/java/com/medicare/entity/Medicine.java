package com.medicare.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
	
	private int medicineId;
	private String medicineName;
	private String drugName;
	private String ServiceName = "Medicare";
	
	public Medicine(int medicineId, String medicineName, String drugName) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.drugName = drugName;
	}
	
	
	
	
	
}
