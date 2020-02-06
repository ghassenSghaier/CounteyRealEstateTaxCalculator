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
import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.service.ArticleService;
import gov.tn.taxecommune.service.TaxationDtoService;
import gov.tn.taxecommune.service.TaxationService;
import gov.tn.taxecommune.service.searching.TaxationFinder;
import gov.tn.taxecommune.service.searching.TaxationSearchErrorResponse;
import gov.tn.taxecommune.service.searching.TaxationSearchParameters;
import gov.tn.taxecommune.service.searching.TaxationSearchResult;
import gov.tn.taxecommune.web.dto.TaxationDto;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

/**
 * Created by Keno&Kemo on 20.11.2017..
 */
@Controller
@RequestMapping("/agentPage")
public class TaxationController {
	
	private TaxationService taxationService;
	private ArticleService articleService;	
	private TaxationFinder taxationFinder;	
	private TaxationSearchErrorResponse taxationSearchErrorResponse;
	private TaxationDtoService taxationDtoService;
	
	/*
	 * Get all taxations or search taxations if there are searching parameters
	 */
	
	public TaxationController(TaxationService taxationService, ArticleService articleService,
			TaxationFinder taxationFinder, TaxationSearchErrorResponse taxationSearchErrorResponse,
			TaxationDtoService taxationDtoService) {
		super();
		this.taxationService = taxationService;
		this.articleService = articleService;
		this.taxationFinder = taxationFinder;
		this.taxationSearchErrorResponse = taxationSearchErrorResponse;
		this.taxationDtoService = taxationDtoService;
	}	
	
	@GetMapping("/taxations")
	public ModelAndView getTaxations(ModelAndView modelAndView, TaxationSearchParameters taxationSearchParameters) {
		int selectedPageSize = taxationSearchParameters.getPageSize().orElse(InitialPagingSizes.INITIAL_PAGE_SIZE);
		int selectedPage = (taxationSearchParameters.getPage().orElse(0) < 1) ? InitialPagingSizes.INITIAL_PAGE
				: (taxationSearchParameters.getPage().get() - 1);

		PageRequest pageRequest = PageRequest.of(selectedPage, selectedPageSize, new Sort(Sort.Direction.ASC, "id"));
		TaxationSearchResult taxationSearchResult = new TaxationSearchResult();

		// Empty search parameters
		if (!taxationSearchParameters.getPropertyValue().isPresent()
				|| taxationSearchParameters.getPropertyValue().get().isEmpty())
			taxationSearchResult.setTaxationPage(taxationDtoService.findAllPageable(pageRequest));

		// Search queries
		else {
			taxationSearchResult = taxationFinder.searchTaxationsByProperty(pageRequest, taxationSearchParameters);

			if (taxationSearchResult.isNumberFormatException())
				return taxationSearchErrorResponse.respondToNumberFormatException(taxationSearchResult, modelAndView);

			if (taxationSearchResult.getTaxationPage().getTotalElements() == 0) {
				modelAndView = taxationSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
				taxationSearchResult.setTaxationPage(taxationDtoService.findAllPageable(pageRequest));
			}
			modelAndView.addObject("taxationsProperty", taxationSearchParameters.getTaxationsProperty().get());
			modelAndView.addObject("propertyValue", taxationSearchParameters.getPropertyValue().get());
		}

		Pager pager = new Pager(taxationSearchResult.getTaxationPage().getTotalPages(),
				taxationSearchResult.getTaxationPage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
				taxationSearchResult.getTaxationPage().getTotalElements());
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("taxations", taxationSearchResult.getTaxationPage());
		modelAndView.addObject("selectedPageSize", selectedPageSize);
		modelAndView.addObject("pageSizes", InitialPagingSizes.PAGE_SIZES);
		modelAndView.setViewName("agentPage/taxation/taxations");
		return modelAndView;
	}

//	@GetMapping("/taxations/{id}")
//	public String getEditTaxationForm(@PathVariable Long id, Model model) {
//		TaxationUpdateDto taxationUpdateDto = taxationUpdateDtoService.findById(id);
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

	@GetMapping("/taxations/newTaxation")
	public String getAddNewTaxationForm(Model model) {
		model.addAttribute("newTaxation", new TaxationDto());
		return "agentPage/taxation/newTaxation";
	}

	@PostMapping("/taxations/newTaxation")
	public String saveNewTaxation(@ModelAttribute("newTaxation") @Valid TaxationDto newTaxation, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		boolean taxationAlreadyExists = false;
		Article article = articleService.findByNumeroMunicipal(newTaxation.getNumeroMunicipal()).get();
		Taxation t1 = new Taxation();
		t1.setMontantFNAH(newTaxation.getMontantFNAH());
		t1.setMontantTCL(newTaxation.getMontantTCL());
		t1.setMontantTIB(newTaxation.getMontantTIB());
		t1.setMontantTTNB(newTaxation.getMontantTTNB());
		t1.setArticle(article);
		for(Taxation t: taxationService.findAll())
		{
			taxationAlreadyExists = taxationService.checkIfTaxationAlreadyExists(taxationService.findAll(),t1,t);
		}		
		boolean hasErrors = false;
		String formWithErrors = "agentPage/taxation/newTaxation";

		if (taxationAlreadyExists) {
			bindingResult.rejectValue("taxation", "taxationAlreadyExists");
			hasErrors = true;
		}
		
		if (bindingResult.hasErrors())
			hasErrors = true;

		if (hasErrors)
			return formWithErrors;

		else {
//			Taxation taxation = taxationService.createNewTaxation(newTaxation);
//			taxation.setEnabled(true);

			taxationService.save(t1);
			redirectAttributes.addFlashAttribute("taxationHasBeenSaved", true);
			return "redirect:/agentPage/taxations";
		}
	}

}
