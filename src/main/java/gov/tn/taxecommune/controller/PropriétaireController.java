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
//import gov.tn.taxeCommune.entity.Propriétaire;
//import gov.tn.taxeCommune.repository.PropriétaireRepository;
//import gov.tn.taxeCommune.repository.RoleRepository;
//import gov.tn.taxeCommune.repository.UserRepository;
//
//@Controller
//@RequestMapping("/propriétaire/")
//public class PropriétaireController{
//
//	private final PropriétaireRepository propriétaireRepository;
//
//	@Autowired
//	public PropriétaireController(UserRepository userRepository,RoleRepository roleRepository, PasswordEncoder encoder,PropriétaireRepository propriétaireRepository) {
//		super();
//		this.propriétaireRepository = propriétaireRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(Propriétaire propriétaire) {
//		return "add-Propriétaire";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("propriétaires", propriétaireRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addPropriétaire(@Valid Propriétaire propriétaire, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-propriétaire";
//		}
//
//		propriétaireRepository.save(propriétaire);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		Propriétaire propriétaire = (Propriétaire) propriétaireRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid propriétaire Id:" + id));
//		model.addAttribute("propriétaire", propriétaire);
//		return "update-propriétaire";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updatePropriétaire(@PathVariable("id") long id, @Valid Propriétaire propriétaire, BindingResult result,
//			Model model) {
//		if (result.hasErrors()) {
//			propriétaire.setId(id);
//			return "update-contribuable";
//		}
//
//		propriétaireRepository.save(propriétaire);
//		model.addAttribute("propriétaires", propriétaireRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("supprimer/{id}")
//	public String deletePropriétaire(@PathVariable("id") long id, Model model) {
//		Propriétaire propriétaire = (Propriétaire) propriétaireRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid propriétaire Id:" + id));
//		propriétaireRepository.delete(propriétaire);
//		model.addAttribute("propriétaires", propriétaireRepository.findAll());
//		return "index";
//	}
//}