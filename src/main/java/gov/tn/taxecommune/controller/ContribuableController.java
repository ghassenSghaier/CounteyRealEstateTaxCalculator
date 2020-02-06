package gov.tn.taxecommune.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.tn.taxecommune.entity.Contribuable;
import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.message.request.AddContribuableForm;
//import gov.tn.taxecommune.message.request.SignUpForm;
import gov.tn.taxecommune.message.response.ResponseMessage;
import gov.tn.taxecommune.repository.ContribuableRepository;
import gov.tn.taxecommune.repository.RoleRepository;
import gov.tn.taxecommune.repository.UserRepository;

@Controller
@RequestMapping("/taxecommune/rest/v1/services/contribuable")
public class ContribuableController {

	@Autowired
	private ContribuableRepository contribuableRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;

//	@Autowired
//	public ContribuableController(ContribuableRepository contribuableRepository, UserRepository userRepository,
//			RoleRepository roleRepository,PasswordEncoder encoder) {
//		super();
//		this.contribuableRepository = contribuableRepository;
//		this.userRepository = userRepository;
//		this.roleRepository = roleRepository;
//		this.encoder=encoder;
//	}

	@GetMapping("signup")
	public String showSignUpForm(Contribuable contribuable) {
		return "add-user";
	}

	@GetMapping("agent/home")
	public String agentHome(Model model) {
		return "agenthome";
	}

	@GetMapping("home")
	public String contribuableHome(Model model) {
		return "contribuablehome";
	}

	@GetMapping("all")
	public String showUpdateForm(Model model) {
		model.addAttribute("contribuables", contribuableRepository.findAll());
		return "agentmunicipal/listcontribuable";
	}

//	@PostMapping("ajouter")
//	public String addContribuable(@Valid Contribuable contribuable, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-contribuable";
//		}
//
//		contribuableRepository.save(contribuable);
//		return "redirect:list";
//	}

	@PostMapping("add")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	public ResponseEntity<?> registerUser(@Valid AddContribuableForm contribuable, BindingResult result, Model model) {
		if (userRepository.existsByUsername(contribuable.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
//					HttpStatus.BAD_REQUEST);
//		}
//		System.out.println(signUpRequest.toString());
		// Creating user's account
		// User userlocal = new User(contribuable.getUsername(),
		// encoder.encode(contribuable.getPassword()));
//				signUpRequest.getUserName(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		Contribuable newcontribuable = new Contribuable(contribuable.getUsername(),
				encoder.encode(contribuable.getPassword()), contribuable.getIdentifiant(), contribuable.getNom(),
				contribuable.getPrenom(), contribuable.getStatut(), contribuable.getEmail(), contribuable.getAdresse(),
				contribuable.getVille(), contribuable.getCodePostal(), contribuable.getNumeroTel(), null);
//		signUpRequest.getUserName(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = contribuable.getUserRoles();
		Set<Role> roles = new HashSet<>();
		System.out.println(roles.toString());
		strRoles.forEach(role -> {
			switch (role) {
			case "ROLE_AGENTMUNICIPAL":
				Role agentMunicipaleRole = roleRepository.findByNom("ROLE_AGENTMUNICIPAL")
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(agentMunicipaleRole);
				break;
			case "ROLE_CONTRIBUABLE":
				Role contribuableRole = roleRepository.findByNom("ROLE_AGENTMUNICIPAL")
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(contribuableRole);
				break;
//				default:
//					Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User ....Role not find."));
//				roles.add(userRole);
			}
		});
		newcontribuable.setRoles((List<Role>) roles);
		contribuableRepository.save(newcontribuable);
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	@GetMapping("editer/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Contribuable contribuable = contribuableRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid contribuable Id:" + id));
		model.addAttribute("contribuable", contribuable);
		return "update-contribuable";
	}

	@PostMapping("modifier/{id}")
	public String updateContribuable(@PathVariable("id") Long id, @Valid Contribuable contribuable,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			contribuable.setId(id);
			return "update-contribuable";
		}

		contribuableRepository.save(contribuable);
		model.addAttribute("contribuables", contribuableRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteContribuable(@PathVariable("id") long id, Model model) {
		Contribuable contribuable = contribuableRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid personne Id:" + id));
		contribuableRepository.delete(contribuable);
		model.addAttribute("contribuables", contribuableRepository.findAll());
		return "index";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

}