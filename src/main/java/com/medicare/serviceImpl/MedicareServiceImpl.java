package com.medicare.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.medicare.entity.MedicareData;
import com.medicare.entity.Medicine;
import com.medicare.service.MedicareService;

@Service
public class MedicareServiceImpl implements MedicareService {

	@Override
	public List<Medicine> getMedicinesByDisease(String disease) {
		// TODO Auto-generated method stub
		Medicine medicineOne=new Medicine(1,"PARACETAMOL","PARACETAMOL");
		MedicareData medicareOne=new MedicareData(medicineOne,"fever");
		Medicine medicineTwo=new Medicine(2,"DOLO 650","PARACETAMOL");
		MedicareData medicareTwo=new MedicareData(medicineTwo,"fever");
		List<MedicareData> medicines =new ArrayList<>();
		List<Medicine> desiredMedicines =new ArrayList<>();
		medicines.add(medicareOne);
		medicines.add(medicareTwo);
		for(MedicareData medicine:medicines) {
			if(medicine.getDisease().equalsIgnoreCase(disease)){
				desiredMedicines.add(medicine.getMedicine());
			}
		}
		
	
		return desiredMedicines;
	}
}
