package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.PilotModel;

import org.springframework.stereotype.Service;

/**
 * 
 * PilotInMemoryService
 *
 */


@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	

	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
				PilotModel temp = archivePilot.get(i);
				return temp;
			}
		}
		return null;
	}
	
	@Override
	public PilotModel updatePilot(String licenseNumber, Integer flyHour) {
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getLicenseNumber().equals(licenseNumber)){
				PilotModel temp = archivePilot.get(i);
				temp.setFlyHour(flyHour);
				return temp;	
			}
			
		}
		return null;
	}
	
	@Override
	public PilotModel deletePilot(String id) {
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getId().equals(id)) {
				PilotModel temp = archivePilot.get(i);
				archivePilot.remove(i);
				return temp;
			}
		}
		return null;
	}
	
		
}
