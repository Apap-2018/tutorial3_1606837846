package com.apap.tutorial3.controller;

import java.util.*;
import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	/*
	 * @RequestMapping(value= {"/challenge", "challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	 */
	@RequestMapping(value = {"/pilot/view/license-number/{licenseNumber}"})
	public String viewPath(@PathVariable String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		String res = "view-pilot";
		
		if (archive == null) {
			model.addAttribute("licenseNum", licenseNumber);
			res = "result-failed";
		}else {
			model.addAttribute("pilot", archive);
		}
		return res;
		
	}
	
	@RequestMapping(value = {"/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}"})
	public String changeHourPath(@PathVariable String licenseNumber, @PathVariable String flyHour, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		String res = "change-success";
		
		if (archive == null) {
			model.addAttribute("licenseNumber", licenseNumber);
			res = "change-failed";
		}else {
			archive.changeHour(flyHour);
			model.addAttribute("action", "diubah");
		}
		return res;	
	}
	
	@RequestMapping(value = {"/pilot/delete/id/{idNumber}"})
	public String deletePath(@PathVariable String idNumber, Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		PilotModel searchRes = pilotService.getPilotDetailById(idNumber);
		String res = "change-success";
		
		if (searchRes == null) {
			model.addAttribute("id", idNumber);
			res = "delete-failed";
		}else {
			//menghapus jika ditemukan pilot dengan id sesuai input
			Iterator<PilotModel> iter = archive.iterator();
			while(iter.hasNext()) {
				PilotModel pilot = iter.next();
				if(pilot.getId().equalsIgnoreCase(idNumber)) {
					iter.remove();
				}
			}
			model.addAttribute("action", "dihapus");
		}
		return res;	
		
		
	}
}
