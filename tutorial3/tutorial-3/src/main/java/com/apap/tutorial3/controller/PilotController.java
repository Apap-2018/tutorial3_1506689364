package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

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
	
//	@RequestMapping("/pilot/view")
//	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
//		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		
//		model.addAttribute("pilot", archive);
//		return "view-pilot";
//	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping(value = {"/pilot/view", "pilot/view/license-number/{licenseNumber}"})
	public String viewPath(@PathVariable Optional<String> licenseNumber, Model model) {
		if(licenseNumber.isPresent()) {
			PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			if(pilot != null) {
				model.addAttribute("pilot", pilot);
				return "view";
			} else {
				model.addAttribute("licenseNumber", licenseNumber.get());
				return "viewTidakKetemu";
			}
		} else {
			return "viewKosong";
		}
	
	}
	
	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}")
	public String update(@PathVariable Optional<String> licenseNumber,
			@PathVariable Optional<Integer> flyHour,
			Model model) {
		
		PilotModel pilot = pilotService.updatePilot(licenseNumber.get(), flyHour.get());
		
		model.addAttribute("pilot", pilot);
		
		if(pilot != null) {
			model.addAttribute("pilot", pilot);
			return "update";	
		} else {
			return "idKosong";
		}
	}
	
	@RequestMapping(value= {"/pilot/delete/id/{id}"})
	public String deletePath(@PathVariable Optional<String> id, Model model) {
		
		if(id.isPresent()) {
			PilotModel pilot = pilotService.deletePilot(id.get());
			if(pilot != null) {
				model.addAttribute("pilot", pilot);
				return "delete";
			} else {
				model.addAttribute("id", id.get());
				return "idTidakKetemu";
			}
		} else {
			return "idKosong";
		}
		
	}
	
	
}
