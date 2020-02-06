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
//import gov.tn.taxeCommune.entity.Article;
//import gov.tn.taxeCommune.entity.ArticleNonRésidentiel;
//import gov.tn.taxeCommune.entity.ArticleRésidentiel;
//import gov.tn.taxeCommune.repository.ArticleRésidentielRepository;
//
//@Controller
//@RequestMapping("/articleRésidentiel/")
//public class ArticleRésidentielController {
//	private final ArticleRésidentielRepository articleRésidentielRepository;
//
//	@Autowired
//	public ArticleRésidentielController(ArticleRésidentielRepository articleRésidentielRepository) {
//		super();
//		this.articleRésidentielRepository = articleRésidentielRepository;
//	}
//
//	@GetMapping("signup")
//	public String showSignUpForm(ArticleNonRésidentiel articleNonRésidentiel) {
//		return "add-articleRésidentiel";
//	}
//
//	@GetMapping("list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("articlesRésidentiel", articleRésidentielRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("ajouter")
//	public String addArticleRésidentiel(@Valid ArticleRésidentiel articleRésidentiel, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-articleRésidentiel";
//		}
//
//		articleRésidentielRepository.save(articleRésidentiel);
//		return "redirect:list";
//	}
//
//	@GetMapping("editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		ArticleRésidentiel articleRésidentiel = articleRésidentielRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
//		model.addAttribute("articleRésidentiel", articleRésidentiel);
//		return "update-articleRésidentiel";
//	}
//
//	@PostMapping("modifier/{id}")
//	public String updateArticleRésidentiel(@PathVariable("id") long id, @Valid ArticleRésidentiel articleRésidentiel,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			articleRésidentiel.setId(id);
//			return "update-articleRésidentiel";
//		}
//
//		articleRésidentielRepository.save(articleRésidentiel);
//		model.addAttribute("articlesRésidentiel", articleRésidentielRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("supprimer/{id}")
//	public String deleteArticleRésidentiel(@PathVariable("id") long id, Model model) {
//		Article article = articleRésidentielRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid personne Id:" + id));
//		articleRésidentielRepository.delete(article);
//		model.addAttribute("articlesRésidentiel", articleRésidentielRepository.findAll());
//		return "index";
//	}
//
//}
