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
//import gov.tn.taxeCommune.entity.Approbation;
//import gov.tn.taxeCommune.repository.ApprobationRepository;
//
//
//@Controller
//@RequestMapping("approbation")
//public class ApprobationController {
//	private final ApprobationRepository approbationRepository;
//
//	@Autowired
//	public ApprobationController(ApprobationRepository approbationRepository) {
//		this.approbationRepository = approbationRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(Approbation approbation) {
//		return "add-approbation";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("approbations", approbationRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addApprobation(@Valid Approbation approbation, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-approbation";
//		}
//
//		approbationRepository.save(approbation);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		Approbation approbation = approbationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid approbation Id:" + id));
//		model.addAttribute("approbation", approbation);
//		return "update-approbation";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updateApprobation(@PathVariable("id") long id, @Valid Approbation approbation, BindingResult result,
//			Model model) {
//		if (result.hasErrors()) {
//			approbation.setId(id);
//			return "update-approbation";
//		}
//
//		approbationRepository.save(approbation);
//		model.addAttribute("approbations", approbationRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("suuprimer/{id}")
//	public String deleteApprobation(@PathVariable("id") long id, Model model) {
//		Approbation approbation = approbationRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid approbation Id:" + id));
//		approbationRepository.delete(approbation);
//		model.addAttribute("approbations", approbationRepository.findAll());
//		return "index";
//	}
//
//}
