//package gov.tn.taxeCommune.controller;
//
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import gov.tn.taxeCommune.entity.Location;
//import gov.tn.taxeCommune.repository.LocationRepository;
//
//
//@Controller
//@RequestMapping("/location/")
//public class LocationController {
//	private final LocationRepository locationRepository;
//
//	@Autowired
//	public LocationController(LocationRepository locationRepository) {
//		this.locationRepository = locationRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(Location location) {
//		return "add-location";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("locations", locationRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addLocation(@Valid Location location, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-location";
//		}
//
//		locationRepository.save(location);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		Location location = locationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid location Id:" + id));
//		model.addAttribute("location", location);
//		return "update-location";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updateLocation(@PathVariable("id") long id, @Valid Location location, BindingResult result,
//			Model model) {
//		if (result.hasErrors()) {
//			location.setId(id);
//			return "update-location";
//		}
//
//		locationRepository.save(location);
//		model.addAttribute("locations", locationRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("supprimer/{id}")
//	public String deleteLocation(@PathVariable("id") long id, Model model) {
//		Location location = locationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid location Id:" + id));
//		locationRepository.delete(location);
//		model.addAttribute("locations", locationRepository.findAll());
//		return "index";
//	}
//
//}
