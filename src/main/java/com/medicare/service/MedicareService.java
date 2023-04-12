package com.medicare.service;

import java.util.List;

import com.medicare.entity.Medicine;

public interface MedicareService {
	public List<Medicine> getMedicinesByDisease(String disease);

}
