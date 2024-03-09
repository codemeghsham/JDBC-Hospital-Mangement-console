package org.hospital.service;

import java.util.List;

import org.hospital.model.DoctorModel;

import org.hospital.repository.DoctorRepository;

public class DoctorService
{
	DoctorRepository docRepo=new DoctorRepository();
	
	public int addDoctor(DoctorModel model)
	{
		return (docRepo.isDoctorPresent(model.getDocName()))?-1: docRepo.isAddDoctor(model)?1:0;
		
	}

	public List<DoctorModel> getAllDoctor()
	{
		return docRepo.getAllDoctor();

	}
	
	public DoctorModel searchDoctor(String doctorName) {
        return docRepo.searchDoctor(doctorName);
    }

	public boolean deleteDoctor(int doctorId) {
	    return docRepo.deleteDoctor(doctorId);
	}
}
