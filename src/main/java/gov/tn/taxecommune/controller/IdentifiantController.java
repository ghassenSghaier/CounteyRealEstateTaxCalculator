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
//import gov.tn.taxeCommune.entity.Identifiant;
//import gov.tn.taxeCommune.repository.IdentifiantRepository;
//
//@Controller
//@RequestMapping("/Identifiant/")
//public class IdentifiantController {
//	private final IdentifiantRepository identifiantRepository;
//
//	@Autowired
//	public IdentifiantController(IdentifiantRepository identifiantRepository) {
//
//		this.identifiantRepository = identifiantRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(IdentifiantRepository identifiantRepository) {
//		return "add-identifiant";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("identifiants", identifiantRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addIdentifiant(@Valid Identifiant identifiant, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-identifiant";
//		}
//
//		identifiantRepository.save(identifiant);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		Identifiant identifiant = identifiantRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
//		model.addAttribute("identifiant", identifiant);
//		return "update-identifiant";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updateIdentifiant(@PathVariable("id") long id, @Valid Identifiant identifiant,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			identifiant.setId(id);
//			return "update-identifiant";
//		}
//
//		identifiantRepository.save(identifiant);
//		model.addAttribute("identifiants", identifiantRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("supprimer/{id}")
//	public String deleteIdentifiant(@PathVariable("id") long id, Model model) {
//		Identifiant identifiant = identifiantRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid identifiant Id:" + id));
//		identifiantRepository.delete(identifiant);
//		model.addAttribute("identifiants", identifiantRepository.findAll());
//		return "index";
//	}
//
//}
