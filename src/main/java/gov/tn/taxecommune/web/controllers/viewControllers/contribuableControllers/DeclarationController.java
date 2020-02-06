package gov.tn.taxecommune.web.controllers.viewControllers.contribuableControllers;

import java.util.NoSuchElementException;

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

import gov.tn.taxecommune.entity.Declaration;
import gov.tn.taxecommune.service.ControleService;
import gov.tn.taxecommune.service.DeclarationDtoService;
import gov.tn.taxecommune.service.DeclarationService;
import gov.tn.taxecommune.service.searching.DeclarationFinder;
import gov.tn.taxecommune.service.searching.DeclarationSearchErrorResponse;
import gov.tn.taxecommune.service.searching.DeclarationSearchParameters;
import gov.tn.taxecommune.service.searching.DeclarationSearchResult;
import gov.tn.taxecommune.web.dto.DeclarationDto;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Controller
@RequestMapping("/contribuablePage")
public class DeclarationController {
	
	private ControleService controleService;
	private DeclarationService declarationService;
	private DeclarationSearchErrorResponse declarationSearchErrorResponse;
	private DeclarationDtoService declarationDtoService;
	private DeclarationFinder declarationFinder;
	

	/*
	 * Get all users or search users if there are searching parameters
	 */
	@GetMapping("/declarations")
	public ModelAndView getDeclarations(ModelAndView modelAndView,
			DeclarationSearchParameters declarationSearchParameters) {
		int selectedPageSize = declarationSearchParameters.getPageSize().orElse(InitialPagingSizes.INITIAL_PAGE_SIZE);
		int selectedPage = (declarationSearchParameters.getPage().orElse(0) < 1) ? InitialPagingSizes.INITIAL_PAGE
				: (declarationSearchParameters.getPage().get() - 1);

		PageRequest pageRequest = PageRequest.of(selectedPage, selectedPageSize, new Sort(Sort.Direction.ASC, "id"));
		DeclarationSearchResult declarationSearchResult = new DeclarationSearchResult();

		// Empty search parameters
		if (!declarationSearchParameters.getPropertyValue().isPresent()
				|| declarationSearchParameters.getPropertyValue().get().isEmpty())
			declarationSearchResult.setDeclarationPage(declarationDtoService.findAllPageable(pageRequest));

		// Search queries
		else {
			declarationSearchResult = declarationFinder.searchDeclarationsByProperty(pageRequest,
					declarationSearchParameters);

			if (declarationSearchResult.isNumberFormatException())
				return declarationSearchErrorResponse.respondToNumberFormatException(declarationSearchResult,
						modelAndView);

			if (declarationSearchResult.getDeclarationPage().getTotalElements() == 0) {
				modelAndView = declarationSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
				declarationSearchResult.setDeclarationPage(declarationDtoService.findAllPageable(pageRequest));
			}
			modelAndView.addObject("declarationsProperty", declarationSearchParameters.getDeclarationsProperty().get());
			modelAndView.addObject("propertyValue", declarationSearchParameters.getPropertyValue().get());
		}

		Pager pager = new Pager(declarationSearchResult.getDeclarationPage().getTotalPages(),
				declarationSearchResult.getDeclarationPage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
				declarationSearchResult.getDeclarationPage().getTotalElements());
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("declarations", declarationSearchResult.getDeclarationPage());
		modelAndView.addObject("selectedPageSize", selectedPageSize);
		modelAndView.addObject("pageSizes", InitialPagingSizes.PAGE_SIZES);
		modelAndView.setViewName("contribuablePage/declaration/declarations");
		return modelAndView;
	}

//	@GetMapping("/declarations/{id}")
//	public String getEditDeclarationForm(@PathVariable Long id, Model model) {
//		DeclarationUpdateDto declarationUpdateDto = declarationUpdateDtoService.findById(id);
//		List<Controle> allControles = controleService.findAll();
//		declarationUpdateDto.setControles(declarationService.getAssignedControlesList(declarationUpdateDto));
//		model.addAttribute("declarationUpdateDto", declarationUpdateDto);
//		model.addAttribute("allControles", allControles);
//		return "contribuablePage/declaration/editDeclaration";
//	}
//
//	@PostMapping("/declarations/{id}")
//	public String updateDeclaration(Model model, @PathVariable Long id,
//			@ModelAttribute("oldDeclaration") @Valid DeclarationUpdateDto declarationUpdateDto,
//			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//
//		String formWithErrors = "contribuablePage/declaration/editDeclaration";
//		Optional<Declaration> persistedDeclaration = declarationService.findById(id);
//		
//		List<Controle> allControles = controleService.findByDeclaration(declarationUpdateDto.getCodeDeclaration());
//		Declaration numeroMunicipalAlreadyExists = controleService.findByIdNot(id);
//		boolean hasErrors = false;
//
//		if (numeroMunicipalAlreadyExists != null) {
//			bindingResult.rejectValue("numeromunicipal", "numeroMunicipalAlreadyExists");
//			hasErrors = true;
//		}
//
////        if (usernameAlreadyExists != null) {
////            bindingResult.rejectValue("username", "usernameAlreadyExists");
////            hasErrors = true;
////        }
//
//		if (bindingResult.hasErrors())
//			hasErrors = true;
//
//		if (hasErrors) {
//			model.addAttribute("declarationUpdateDto", declarationUpdateDto);
//			model.addAttribute("controlesList", allControles);
//			model.addAttribute("org.springframework.validation.BindingResult.declarationUpdateDto", bindingResult);
//			return formWithErrors;
//		} else {
//			declarationService
//					.save(declarationService.getUpdatedDeclaration(persistedDeclaration.get(), declarationUpdateDto));
//			redirectAttributes.addFlashAttribute("declarationHasBeenUpdated", true);
//			return "redirect:/contribuablePage/declarations";
//		}
//	}

	@GetMapping("/declarations/newDeclaration")
	public String getAddNewDeclarationForm(Model model) {
//		model.addAttribute("newArticle", new ArticleDto());
		return "contribuablePage/declaration/newDeclaration";
	}

	@PostMapping("/declarations/newDeclaration")
	public String saveNewDeclaration(@ModelAttribute("newDeclaration") @Valid DeclarationDto newDeclaration,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		Declaration declarationAlreadyExists = null;
		boolean hasErrors = false;
		String formWithErrors = "contribuablePage/declaration/newDeclaration";
		String urlTclArticles = "redirect:/contribuablePage/declarations";
		String urlRedirect = null;
		try {
			if(newDeclaration.getNumDeclare() != 0)
//			declarationAlreadyExists = declarationService.findByDeclaration(newDeclaration.getDateDeclaration())
//					.get();

			if (declarationAlreadyExists != null) {
				bindingResult.rejectValue("codeDeclaration", "DeclarationAlreadyExists");
				hasErrors = true;
			}

			if (bindingResult.hasErrors())
				hasErrors = true;

			if (hasErrors) {
				urlRedirect = formWithErrors;
				return urlRedirect;
			}

		} catch (NoSuchElementException e) {
			if (bindingResult.hasErrors())
				hasErrors = true;

			if (hasErrors) {
				urlRedirect = formWithErrors;
				return urlRedirect;
			}

			else {
				Declaration declaration = declarationService.declareNewArticle(newDeclaration);
//          article.setEnabled(true);
				declarationService.save(declaration);
				redirectAttributes.addFlashAttribute("declarationHasBeenSaved", true);
				urlRedirect = urlTclArticles;
				return urlRedirect;
			}
		}
		return urlRedirect;
	}
}
