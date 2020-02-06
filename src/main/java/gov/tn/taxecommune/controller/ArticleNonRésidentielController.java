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
//import gov.tn.taxeCommune.entity.ArticleNonRésidentiel;
//import gov.tn.taxeCommune.repository.ArticleNonRésidentielRepository;
//
//@Controller
//@RequestMapping("articleNonRésidentiel")
//public class ArticleNonRésidentielController{
//	private final ArticleNonRésidentielRepository articleNonRésidentielRepository;
//
//	@Autowired
//	public ArticleNonRésidentielController(ArticleNonRésidentielRepository articleNonRésidentielRepository) {
//		super();
//		this.articleNonRésidentielRepository = articleNonRésidentielRepository;
//	}
//
//	@GetMapping("/signup")
//	public String showSignUpForm(ArticleNonRésidentiel articleNonRésidentiel) {
//		return "add-articleNonRésidentiel";
//	}
//
//	@GetMapping("/list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("articlesNonRésidentiel", articleNonRésidentielRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("/ajouter")
//	public String addArticleNonRésidentiel(@Valid ArticleNonRésidentiel articleNonRésidentiel, BindingResult result,
//			Model model) {
//		if (result.hasErrors()) {
//			return "add-articleNonRésidentiel";
//		}
//
//		articleNonRésidentielRepository.save(articleNonRésidentiel);
//		return "redirect:list";
//	}
//
//	@GetMapping("/editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		ArticleNonRésidentiel articleNonRésidentiel = articleNonRésidentielRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
//		model.addAttribute("articleNonRésidentiel", articleNonRésidentiel);
//		return "update-article";
//	}
//
//	@PostMapping("/modifier/{id}")
//	public String updateArticleNonRésidentiel(@PathVariable("id") long id, @Valid ArticleNonRésidentiel articleNonRésidentiel,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			articleNonRésidentiel.setId(id);
//			return "update-articleNonRésidentiel";
//		}
//
//		articleNonRésidentielRepository.save(articleNonRésidentiel);
//		model.addAttribute("articlesNonRésidentiel", articleNonRésidentielRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("/supprimer/{id}")
//	public String deleteArticleNonRésidentiel(@PathVariable("id") long id, Model model) {
//		ArticleNonRésidentiel articleNonRésidentiel = articleNonRésidentielRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid article non résidentiel  Id:" + id));
//		articleNonRésidentielRepository.delete(articleNonRésidentiel);
//		model.addAttribute("articlesnonrésidentiels", articleNonRésidentielRepository.findAll());
//		return "index";
//	}
//
//}
