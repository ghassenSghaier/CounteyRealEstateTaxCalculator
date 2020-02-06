package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import gov.tn.taxecommune.service.ReclamationDtoService;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Service
public class ReclamationSearchErrorResponse {

	private ReclamationDtoService reclamationDtoService;

	public ReclamationSearchErrorResponse(ReclamationDtoService userDtoService) {
		this.reclamationDtoService = userDtoService;
	}

	public ModelAndView respondToNumberFormatException(ReclamationSearchResult reclamationSearchResult,
			ModelAndView modelAndView) {
		Pager pager = new Pager(reclamationSearchResult.getReclamationPage().getTotalPages(),
				reclamationSearchResult.getReclamationPage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
				reclamationSearchResult.getReclamationPage().getTotalElements());

		modelAndView.addObject("numberFormatException", true);
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("reclamations", reclamationSearchResult.getReclamationPage());
		modelAndView.setViewName("agentPage/reclamation/reclamations");
		return modelAndView;
	}

	public ModelAndView respondToEmptySearchResult(ModelAndView modelAndView, PageRequest pageRequest) {
		modelAndView.addObject("noMatches", true);
		modelAndView.addObject("reclamations", reclamationDtoService.findAllPageable(pageRequest));
		return modelAndView;
	}
}
