package gov.tn.taxecommune.web.controllers.viewControllers.contribuableControllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.entity.ArticleTaxation;
import gov.tn.taxecommune.entity.Reclamation;
import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.service.ArticleService;
import gov.tn.taxecommune.service.ArticleTaxationService;
import gov.tn.taxecommune.service.ReclamationDtoService;
import gov.tn.taxecommune.service.ReclamationService;
import gov.tn.taxecommune.service.ReclamationUpdateDtoService;
import gov.tn.taxecommune.service.TaxationService;
import gov.tn.taxecommune.service.UserService;
import gov.tn.taxecommune.service.searching.ReclamationFinder;
import gov.tn.taxecommune.service.searching.ReclamationSearchErrorResponse;
import gov.tn.taxecommune.service.searching.ReclamationSearchParameters;
import gov.tn.taxecommune.service.searching.ReclamationSearchResult;
import gov.tn.taxecommune.web.dto.ReclamationDto;
import gov.tn.taxecommune.web.dto.ReclamationUpdateDto;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

/**
 * Created by Keno&Kemo on 20.11.2017..
 */
@Controller
@RequestMapping("/contribuablePage")
public class CReclamationController {

	private ReclamationService reclamationService;
	private TaxationService taxationService;
	private ArticleService articleService;
	private ReclamationFinder reclamationFinder;
	private ReclamationSearchErrorResponse reclamationSearchErrorResponse;
	private ReclamationDtoService reclamationDtoService;
	private ReclamationUpdateDtoService reclamationUpdateDtoService;
	private ArticleTaxationService articleTaxationService;

	public CReclamationController(ReclamationService reclamationService, TaxationService taxationService,
			ArticleService articleService, ReclamationFinder reclamationFinder,
			ReclamationSearchErrorResponse reclamationSearchErrorResponse, ReclamationDtoService reclamationDtoService,
			ReclamationUpdateDtoService reclamationUpdateDtoService, ArticleTaxationService articleTaxationService) {
		super();
		this.reclamationService = reclamationService;
		this.taxationService = taxationService;
		this.articleService = articleService;
		this.reclamationFinder = reclamationFinder;
		this.reclamationSearchErrorResponse = reclamationSearchErrorResponse;
		this.reclamationDtoService = reclamationDtoService;
		this.reclamationUpdateDtoService = reclamationUpdateDtoService;
		this.articleTaxationService = articleTaxationService;
	}

	/*
	 * Get all Reclamations or search Reclamations if there are searching parameters
	 */

	@GetMapping("/reclamations/{username}")
	public ModelAndView getReclamations(ModelAndView modelAndView,
			ReclamationSearchParameters reclamationSearchParameters, @PathVariable("username") String username) {
		int selectedPageSize = reclamationSearchParameters.getPageSize().orElse(InitialPagingSizes.INITIAL_PAGE_SIZE);
		int selectedPage = (reclamationSearchParameters.getPage().orElse(0) < 1) ? InitialPagingSizes.INITIAL_PAGE
				: (reclamationSearchParameters.getPage().get() - 1);

//		try {					
		PageRequest pageRequest = PageRequest.of(selectedPage, selectedPageSize, new Sort(Sort.Direction.ASC, "id"));
		ReclamationSearchResult reclamationSearchResult = new ReclamationSearchResult();

		// Empty search parameters
		if (!reclamationSearchParameters.getPropertyValue().isPresent()
				|| reclamationSearchParameters.getPropertyValue().get().isEmpty())
			reclamationSearchResult
					.setReclamationPage(reclamationDtoService.findAllPageableForUser(username, pageRequest));

		// Search queries
		else {
			reclamationSearchResult = reclamationFinder.searchReclamationsByProperty(pageRequest,
					reclamationSearchParameters);

			if (reclamationSearchResult.isNumberFormatException())
				return reclamationSearchErrorResponse.respondToNumberFormatException(reclamationSearchResult,
						modelAndView);

			if (reclamationSearchResult.getReclamationPage().getTotalElements() == 0) {
				modelAndView = reclamationSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
				reclamationSearchResult.setReclamationPage(reclamationDtoService.findAllPageable(pageRequest));
			}
			modelAndView.addObject("reclamationsProperty", reclamationSearchParameters.getReclamationsProperty().get());
			modelAndView.addObject("propertyValue", reclamationSearchParameters.getPropertyValue().get());
		}

		Pager pager = new Pager(reclamationSearchResult.getReclamationPage().getTotalPages(),
				reclamationSearchResult.getReclamationPage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
				reclamationSearchResult.getReclamationPage().getTotalElements());
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("reclamations", reclamationSearchResult.getReclamationPage());
		modelAndView.addObject("selectedPageSize", selectedPageSize);
		modelAndView.addObject("pageSizes", InitialPagingSizes.PAGE_SIZES);
//			User user = userService.findByUsername(contribuable);
		modelAndView.setViewName("contribuablePage/reclamation/reclamations");
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			modelAndView.setViewName("contribuablePage/reclamation/reclamations");
//			e.printStackTrace();
//		}
		return modelAndView;
	}

	@GetMapping("/reclamations/{username}/{id}")
	public String getEditReclamationForm(@PathVariable Long id, Model model,
			@PathVariable("username") String username) {
		ReclamationUpdateDto reclamationUpdateDto = reclamationUpdateDtoService.findById(id);
		List<ArticleTaxation> allaTaxations = articleTaxationService.findAllAtReclamationForUser(username);

		reclamationUpdateDto.setArticleTaxation(reclamationService.getAssignedATList(reclamationUpdateDto).get(0));

		model.addAttribute("reclamationUpdateDto", reclamationUpdateDto);
		model.addAttribute("allReclamations", allaTaxations);
		return "contribuablePage/reclamation/editReclamation";
	}

//	@PostMapping("/reclamations/{username}/{id}")
//	public String updateReclamation(Model model, @PathVariable Long id, @PathVariable String username,
//			@ModelAttribute("oldReclamation") @Valid ReclamationUpdateDto userUpdateDto, BindingResult bindingResult,
//			RedirectAttributes redirectAttributes) {
//
//		String formWithErrors = "contribuablePage/reclamation/editReclamation";
//		Optional<Reclamation> persistedReclamation = reclamationService.findById(id);
//		List<ArticleTaxation> allaTaxations = articleTaxationService.findAllAtReclamationForUser(username);	
//		
//		Reclamation reclamationAlreadyExists = reclamationService.findByUserAndArticle(username);
//		boolean hasErrors = false;
//
//		if (reclamationAlreadyExists != null) {
//			bindingResult.rejectValue("email", "emailAlreadyExists");
//			hasErrors = true;
//		}
//
//		if (bindingResult.hasErrors())
//			hasErrors = true;
//
//		if (hasErrors) {
//			model.addAttribute("reclamationUpdateDto", userUpdateDto);
//			model.addAttribute("aTaxationsList", allaTaxations);
//			model.addAttribute("org.springframework.validation.BindingResult.userUpdateDto", bindingResult);
//			return formWithErrors;
//		} else {
//			userService.save(reclamationService.getUpdatedReclamation(persistedReclamation.get(), userUpdateDto));
//			redirectAttributes.addFlashAttribute("userHasBeenUpdated", true);
//			return "redirect:/adminPage/users";
//		}
//	}

	@GetMapping("/reclamations/newReclamation")
	public String getAddNewReclamationForm(Model model) {
		model.addAttribute("newReclamation", new ReclamationDto());
		return "contribuablePage/reclamation/newReclamation";
	}

	@PostMapping("/reclamations/newReclamation/{username}")
	public String saveNewReclamation(@ModelAttribute("newReclamation") @Valid ReclamationDto newReclamation,
			@PathVariable String username, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		Reclamation reclamationAlreadyExists = null;
		Taxation taxation = taxationService.findByCode(newReclamation.getTaxation());
		Article article = articleService.findByNumeroMunicipal(newReclamation.getArticle()).get();
		reclamationAlreadyExists = reclamationService.findByUserAndArticle(username, newReclamation.getArticle());
		Reclamation t1 = new Reclamation();
		t1.setCodeReclamation("rec" + article.getNumeroMunicipal());
		t1.setMotifReclamation(newReclamation.getMotifReclamation());
		boolean hasErrors = false;
		String formWithErrors = "agentPage/Reclamation/newReclamation";

		if (reclamationAlreadyExists != null) {
			bindingResult.rejectValue("Reclamation", "ReclamationAlreadyExists");
			hasErrors = true;
		}

		if (bindingResult.hasErrors())
			hasErrors = true;

		if (hasErrors)
			return formWithErrors;

		else {
//			Reclamation Reclamation = ReclamationService.createNewReclamation(newReclamation);
//			Reclamation.setEnabled(true);

			reclamationService.save(t1);
			redirectAttributes.addFlashAttribute("ReclamationHasBeenSaved", true);
			return "redirect:/contribuablePage/reclamtion/reclamations";
		}
	}

}
