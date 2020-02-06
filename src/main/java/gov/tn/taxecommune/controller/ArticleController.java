//package gov.tn.taxecommune.controller;
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
//import org.springframework.web.bind.annotation.RestController;
//
//import gov.tn.taxecommune.entity.Article;
//import gov.tn.taxecommune.repository.ArticleRepository;
//
//@Controller
//@RequestMapping("/taxecommune/rest/v1/services/article")
//public class ArticleController {
//
//	@Autowired
//	private ArticleRepository articleRepository;
//
//	@Autowired
//	public ArticleController(ArticleRepository articleRepository) {
//		this.articleRepository = articleRepository;
//	}
//
//	@GetMapping("/signup")
//	public String showSignUpForm(Article article) {
//		return "add-article";
//	}
//
//	@GetMapping("/list")
//	public String showUpdateForm(Model model) {
//		model.addAttribute("articles", articleRepository.findAll());
//		return "index";
//	}
//
//	@PostMapping("/ajouter")
//	public String addArticle(@Valid Article article, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-article";
//		}
//
//		articleRepository.save(article);
//		return "redirect:list";
//	}
//
//	@GetMapping("/editer/{id}")
//	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//		Article article = articleRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
//		model.addAttribute("article", article);
//		return "update-article";
//	}
//
//	@PostMapping("/modifier/{id}")
//	public String updateArticle(@PathVariable("id") Long id, @Valid Article article, BindingResult result,
//			Model model) {
//		if (result.hasErrors()) {
//			article.setNumeroMunicipal(id);
//			return "update-article";
//		}
//
//		articleRepository.save(article);
//		model.addAttribute("articles", articleRepository.findAll());
//		return "index";
//	}
//
//	@GetMapping("/supprimer/{id}")
//	public String deleteArticle(@PathVariable("id") long id, Model model) {
//		Article article = articleRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid personne Id:" + id));
//		articleRepository.delete(article);
//		model.addAttribute("articles", articleRepository.findAll());
//		return "index";
//	}
//
//}
