package gov.tn.taxecommune.web.controllers.restAPIControllers;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.entity.Cellule;
import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.entity.User;
import gov.tn.taxecommune.service.ArticleService;
import gov.tn.taxecommune.service.CelluleService;
import gov.tn.taxecommune.service.TaxationService;
import gov.tn.taxecommune.service.UserService;

@RestController
public class RestfulController {
	private UserService userService;
	private ArticleService articleService;
	private TaxationService taxationService;
	private CelluleService celluleService;

	public RestfulController(UserService userService, ArticleService articleService, TaxationService taxationService,
			CelluleService celluleService) {
		this.userService = userService;
		this.articleService = articleService;
		this.taxationService = taxationService;
		this.celluleService = celluleService;
	}

	@GetMapping("/adminPage/json-users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> allUsers = userService.findAll();

		if (allUsers == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		else if (allUsers.isEmpty())
			return new ResponseEntity<>(allUsers, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@DeleteMapping("/adminPage/json-users/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		Optional<User> userToDelete = userService.findById(id);

		if (!userToDelete.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		userService.deleteById(id);
		return new ResponseEntity<>(userToDelete.get(), HttpStatus.NO_CONTENT);
	}

	@GetMapping("/adminPage/json-articles")
	public ResponseEntity<List<Article>> getArticles() {
		List<Article> allArticles = articleService.findAll();

		if (allArticles == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		else if (allArticles.isEmpty())
			return new ResponseEntity<>(allArticles, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(allArticles, HttpStatus.OK);
	}

	@GetMapping("/adminPage/json-articles/get/{codeArticle}")
	public ResponseEntity<Article> getArticles(@PathVariable String codeArticle) {
		Article maxByNumMunicip = null;

		List<Article> allArticles = articleService.findByNumRep(codeArticle);
		if (allArticles == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			maxByNumMunicip = allArticles.stream().max(Comparator.comparing(Article::getNumeroMunicipal))
					.orElseThrow(NoSuchElementException::new);
		}

		return new ResponseEntity<>(maxByNumMunicip, HttpStatus.OK);
	}

	@DeleteMapping("/adminPage/json-articles/delete/{id}")
	public ResponseEntity<Article> deleteArticle(@PathVariable String numeroMunicipal) {
		Optional<Article> articleToDelete = articleService.findByNumeroMunicipal(numeroMunicipal);

		if (!articleToDelete.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		articleService.deleteByNumeroMunicipal(numeroMunicipal);
		return new ResponseEntity<>(articleToDelete.get(), HttpStatus.NO_CONTENT);
	}

	@GetMapping("/agentPage/json-taxations/{numeroMunicipal}")
	public ResponseEntity<List<Taxation>> getTaxations(@PathVariable String numeroMunicipal) {
		List<Taxation> allTaxations = taxationService.findByArticle(numeroMunicipal);

		if (allTaxations == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		else if (allTaxations.isEmpty())
			return new ResponseEntity<>(allTaxations, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(allTaxations, HttpStatus.OK);
	}

	@GetMapping("/adminPage/json-cellules/{codeRue}")
	public ResponseEntity<Cellule> getCellule(@PathVariable String codeRue) {

		Cellule cellule = null;
		try {
			cellule = celluleService.findByCodeRue(codeRue).get();
		} catch (NoSuchElementException e) {
			if (cellule == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(cellule, HttpStatus.OK);
	}

}
