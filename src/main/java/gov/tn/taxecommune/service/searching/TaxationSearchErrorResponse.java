package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import gov.tn.taxecommune.service.TaxationDtoService;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Service
public class TaxationSearchErrorResponse {

	private TaxationDtoService taxationDtoService;

	public TaxationSearchErrorResponse(TaxationDtoService taxationDtoService) {
		this.taxationDtoService = taxationDtoService;
	}

	public ModelAndView respondToNumberFormatException(TaxationSearchResult taxationSearchResult,
			ModelAndView modelAndView) {
		Pager pager = new Pager(taxationSearchResult.getTaxationPage().getTotalPages(),
				taxationSearchResult.getTaxationPage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
				taxationSearchResult.getTaxationPage().getTotalElements());

		modelAndView.addObject("numberFormatException", true);
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("taxations", taxationSearchResult.getTaxationPage());
		modelAndView.setViewName("agentPage/taxation/taxations");
		return modelAndView;
	}

	public ModelAndView respondToEmptySearchResult(ModelAndView modelAndView, PageRequest pageRequest) {
		modelAndView.addObject("noMatches", true);
		modelAndView.addObject("taxations", taxationDtoService.findAllPageable(pageRequest));
		return modelAndView;
	}
}
