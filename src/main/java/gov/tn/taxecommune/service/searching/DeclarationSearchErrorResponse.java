package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import gov.tn.taxecommune.service.DeclarationDtoService;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Service
public class DeclarationSearchErrorResponse {

	private DeclarationDtoService declarationDtoService;

	public DeclarationSearchErrorResponse(DeclarationDtoService declarationDtoService) {
		this.declarationDtoService = declarationDtoService;
	}

	public ModelAndView respondToNumberFormatException(DeclarationSearchResult declarationSearchResult,
			ModelAndView modelAndView) {
		Pager pager = new Pager(declarationSearchResult.getDeclarationPage().getTotalPages(),
				declarationSearchResult.getDeclarationPage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
				declarationSearchResult.getDeclarationPage().getTotalElements());

		modelAndView.addObject("numberFormatException", true);
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("declarations", declarationSearchResult.getDeclarationPage());
		modelAndView.setViewName("contribuablePage/declaration/declarations");
		return modelAndView;
	}

	public ModelAndView respondToEmptySearchResult(ModelAndView modelAndView, PageRequest pageRequest) {
		modelAndView.addObject("noMatches", true);
		modelAndView.addObject("declarations", declarationDtoService.findAllPageable(pageRequest));
		return modelAndView;
	}
}
