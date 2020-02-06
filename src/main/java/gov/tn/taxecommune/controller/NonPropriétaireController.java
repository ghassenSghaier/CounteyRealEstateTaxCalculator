 //package gov.tn.taxeCommune.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import gov.tn.taxeCommune.entity.NonPropriétaire;
//import gov.tn.taxeCommune.repository.ContribuableRepository;
//import gov.tn.taxeCommune.repository.NonPropriétaireRepository;
//import gov.tn.taxeCommune.repository.RoleRepository;
//import gov.tn.taxeCommune.repository.UserRepository;
//
//@Controller
//@RequestMapping("/nonPropriétaire/")
//public class NonPropriétaireController {
//
//	private final NonPropriétaireRepository nonPropriétaireRepository;
//
//	@Autowired
//	public NonPropriétaireController(ContribuableRepository contribuableRepository, UserRepository userRepository,
//			RoleRepository roleRepository, PasswordEncoder encoder,
//			NonPropriétaireRepository nonPropriétaireRepository) {
//		super();
//		this.nonPropriétaireRepository = nonPropriétaireRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(NonPropriétaireRepository nonPropriétaireRepository) {
//		return "add-nonPropriétaire";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("nonPropriétaires", nonPropriétaireRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addNonPropriétaire(@Valid NonPropriétaire nonPropriétaire, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-nonPropriétaire";
//		}
//
//		nonPropriétaireRepository.save(nonPropriétaire);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		NonPropriétaire nonPropriétaire = (NonPropriétaire) nonPropriétaireRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid non propriétaire Id:" + id));
//		model.addAttribute("nonPropriétaire", nonPropriétaire);
//		return "update-nonPropriétaire";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updateNonPropriétaire(@PathVariable("id") long id, @Valid NonPropriétaire nonPropriétaire,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			nonPropriétaire.setId(id);
//			return "update-nonPropriétaire";
//		}
//
//		nonPropriétaireRepository.save(nonPropriétaire);
//		model.addAttribute("nonPropriétaires", nonPropriétaireRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("supprimer/{id}")
//	public String deleteNonPropriétaire(@PathVariable("id") long id, Model model) {
//		NonPropriétaire nonPropriétaire = (NonPropriétaire) nonPropriétaireRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid personne Id:" + id));
//		nonPropriétaireRepository.delete(nonPropriétaire);
//		model.addAttribute("nonPropriétaires", nonPropriétaireRepository.findAll());
//		return "index";
//	}
//}