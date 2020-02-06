//package gov.tn.taxeCommune.controller;
//
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import gov.tn.taxeCommune.entity.ArticleTerreNue;
//import gov.tn.taxeCommune.repository.ArticleTerreNueRepository;
//
//@RestController
//@RequestMapping("/articleTerreNue/")
//public class ArticleTerreNueController {
//	private final ArticleTerreNueRepository articleTerreNueRepository;
//
//	@Autowired
//	public ArticleTerreNueController(ArticleTerreNueRepository articleTerreNueRepository) {
//		super();
//		this.articleTerreNueRepository = articleTerreNueRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(ArticleTerreNue articleTerreNue) {
//		return "add-articleTerreNue";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("articlesTerreNue", articleTerreNueRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addArticleTerreNue(@Valid ArticleTerreNue articleTerreNue, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-articleTerreNue";
//		}
//
//		articleTerreNueRepository.save(articleTerreNue);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		ArticleTerreNue articleTerreNue = articleTerreNueRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
//		model.addAttribute("articleTerreNue", articleTerreNue);
//		return "update-articleTerreNue";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updateArticleTerreNue(@PathVariable("id") long id, @Valid ArticleTerreNue articleTerreNue,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			articleTerreNue.setId(id);
//			return "update-articleTerreNue";
//		}
//
//		articleTerreNueRepository.save(articleTerreNue);
//		model.addAttribute("articlesTerreNue", articleTerreNueRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("supprimer/{id}")
//	public String deleteArticleTerreNue(@PathVariable("id") long id, Model model) {
//		ArticleTerreNue articleTerreNue = articleTerreNueRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid personne Id:" + id));
//		articleTerreNueRepository.delete(articleTerreNue);
//		model.addAttribute("articlesTerreNue", articleTerreNueRepository.findAll());
//		return "index";
//	}
//
//}
