package gov.tn.taxecommune.web.controllers.viewControllers.agentmunicipalControllers;

import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.entity.ArticleTaxation;
import gov.tn.taxecommune.entity.Reclamation;
import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.service.ArticleService;
import gov.tn.taxecommune.service.ReclamationDtoService;
import gov.tn.taxecommune.service.ReclamationService;
import gov.tn.taxecommune.service.TaxationService;
import gov.tn.taxecommune.service.UserService;
import gov.tn.taxecommune.service.searching.ReclamationFinder;
import gov.tn.taxecommune.service.searching.ReclamationSearchErrorResponse;
import gov.tn.taxecommune.service.searching.ReclamationSearchParameters;
import gov.tn.taxecommune.service.searching.ReclamationSearchResult;
import gov.tn.taxecommune.web.dto.ReclamationDto;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

/**
 * Created by Keno&Kemo on 20.11.2017..
 */
@Controller
@RequestMapping("/agentPage")
public class AReclamationController {

	private ReclamationService reclamationService;
	private TaxationService taxationService;
	private ArticleService articleService;
	private UserService userService;
	private ReclamationFinder reclamationFinder;
	private ReclamationSearchErrorResponse reclamationSearchErrorResponse;
	private ReclamationDtoService reclamationDtoService;

	/*
	 * Get all Reclamations or search Reclamations if there are searching parameters
	 */

	public AReclamationController(ReclamationService reclamationService, ArticleService articleService,
			TaxationService taxationService, ReclamationFinder reclamationFinder,
			ReclamationSearchErrorResponse reclamationSearchErrorResponse,
			ReclamationDtoService reclamationDtoService) {
		super();
		this.taxationService = taxationService;
		this.reclamationService = reclamationService;
		this.articleService = articleService;
		this.reclamationFinder = reclamationFinder;
		this.reclamationSearchErrorResponse = reclamationSearchErrorResponse;
		this.reclamationDtoService = reclamationDtoService;
	}

	@GetMapping("/reclamations")
	public ModelAndView getReclamations(ModelAndView modelAndView,
			ReclamationSearchParameters reclamationSearchParameters) {
//			@PathVariable("contribuable") String contribuable) {
		int selectedPageSize = reclamationSearchParameters.getPageSize().orElse(InitialPagingSizes.INITIAL_PAGE_SIZE);
		int selectedPage = (reclamationSearchParameters.getPage().orElse(0) < 1) ? InitialPagingSizes.INITIAL_PAGE
				: (reclamationSearchParameters.getPage().get() - 1);

//		try {			
		PageRequest pageRequest = PageRequest.of(selectedPage, selectedPageSize, new Sort(Sort.Direction.ASC, "id"));
		ReclamationSearchResult reclamationSearchResult = new ReclamationSearchResult();

		// Empty search parameters
		if (!reclamationSearchParameters.getPropertyValue().isPresent()
				|| reclamationSearchParameters.getPropertyValue().get().isEmpty())
			reclamationSearchResult.setReclamationPage(reclamationDtoService.findAllPageable(pageRequest));

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
		modelAndView.setViewName("agentPage/reclamation/reclamations");
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			modelAndView.setViewName("contribuablePage/reclamation/reclamations");
//			e.printStackTrace();
//		}
		return modelAndView;
	}

//	@GetMapping("/Reclamations/{id}")
//	public String getEditReclamationForm(@PathVariable Long id, Model model) {
//		ReclamationUpdateDto ReclamationUpdateDto = ReclamationUpdateDtoService.findById(id);
//		List<Role> allRoles = roleService.findAll();
//
//		userUpdateDto.setRoles(userService.getAssignedRolesList(userUpdateDto));
//
//		model.addAttribute("userUpdateDto", userUpdateDto);
//		model.addAttribute("allRoles", allRoles);
//		return "adminPage/user/editUser";
//	}
//
//	@PostMapping("/users/{id}")
//	public String updateUser(Model model, @PathVariable Long id,
//			@ModelAttribute("oldUser") @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult,
//			RedirectAttributes redirectAttributes) {
//
//		String formWithErrors = "adminPage/user/editUser";
//		Optional<User> persistedUser = userService.findById(id);
//		List<Role> allRoles = roleService.findAll();
//
//		User emailAlreadyExists = userService.findByEmailAndIdNot(userUpdateDto.getEmail(), id);
//		User usernameAlreadyExists = userService.findByUsernameAndIdNot(userUpdateDto.getUsername(), id);
//		boolean hasErrors = false;
//
//		if (emailAlreadyExists != null) {
//			bindingResult.rejectValue("email", "emailAlreadyExists");
//			hasErrors = true;
//		}
//
//		if (usernameAlreadyExists != null) {
//			bindingResult.rejectValue("username", "usernameAlreadyExists");
//			hasErrors = true;
//		}
//
//		if (bindingResult.hasErrors())
//			hasErrors = true;
//
//		if (hasErrors) {
//			model.addAttribute("userUpdateDto", userUpdateDto);
//			model.addAttribute("rolesList", allRoles);
//			model.addAttribute("org.springframework.validation.BindingResult.userUpdateDto", bindingResult);
//			return formWithErrors;
//		} else {
//			userService.save(userService.getUpdatedUser(persistedUser.get(), userUpdateDto));
//			redirectAttributes.addFlashAttribute("userHasBeenUpdated", true);
//			return "redirect:/adminPage/users";
//		}
//	}

	@GetMapping("/reclamations/newReclamation")
	public String getAddNewReclamationForm(Model model) {
		model.addAttribute("newReclamation", new ReclamationDto());
		return "contribuablePage/reclamation/newReclamation";
	}

	@PostMapping("/reclamations/newReclamation")
	public String saveNewReclamation(@ModelAttribute("newReclamation") @Valid ReclamationDto newReclamation,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		boolean reclamationAlreadyExists = false;
		Taxation taxation = taxationService.findByCode(newReclamation.getTaxation());
		Article article = articleService.findByNumeroMunicipal(newReclamation.getArticle()).get();
		ArticleTaxation ar = new ArticleTaxation();
		ar.setTaxation(taxation);
		Reclamation t1 = new Reclamation();
		t1.setArticleTaxation(ar);
		t1.setCodeReclamation("rec" + article.getNumeroMunicipal());
		t1.setMotifReclamation(newReclamation.getMotifReclamation());
		boolean hasErrors = false;
		String formWithErrors = "agentPage/Reclamation/newReclamation";

		if (reclamationAlreadyExists) {
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
