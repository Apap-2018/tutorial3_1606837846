package com.apap.tutorial3.service;
import com.apap.tutorial3.model.PilotModel;

import java.util.*;

public interface PilotService {
	void addPilot(PilotModel pilot);
	List<PilotModel> getPilotList();
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel getPilotDetailById(String id);
}
