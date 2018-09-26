package com.apap.tutorial3.service;
import java.util.*;

import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.*;
/*
 * PilotInMemoryService
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
	public List<PilotModel> getPilotList(){
		return archivePilot;
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		//ini menerima licenseNumber pilot dan mengembalikan object Pilot dengan licenseNumber tersebut. Returnnull jika tidak ditemukan.
		PilotModel pilotSementara= null;
		for(PilotModel temp: archivePilot) {
			if(temp.getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
				pilotSementara = temp;
				break;
			}
		}
		return pilotSementara;
	}
	
	@Override
	public PilotModel getPilotDetailById(String id) {
		//ini menerima id pilot dan mengembalikan object Pilot dengan licenseNumber tersebut. Returnnull jika tidak ditemukan.
		PilotModel pilotSementara= null;
		for(PilotModel temp: archivePilot) {
			if(temp.getId().equalsIgnoreCase(id)) {
				pilotSementara = temp;
				break;
			}
		}
		return pilotSementara;
	}
	
	
}
