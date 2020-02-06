package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import gov.tn.taxecommune.service.ArticleDtoService;
import gov.tn.taxecommune.service.ArticleTclDtoService;
import gov.tn.taxecommune.service.UserDtoService;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Service
public class ArticleTclSearchErrorResponse extends ArticleSearchErrorResponse {

    private ArticleTclDtoService articleTclDtoService;

    
    public ArticleTclSearchErrorResponse(ArticleDtoService articleDtoService,
			ArticleTclDtoService articleTclDtoService) {
		super(articleDtoService);
		this.articleTclDtoService = articleTclDtoService;
	}

	public ModelAndView respondToNumberFormatException(ArticleTclSearchResult articleTclSearchResult, ModelAndView modelAndView) {
        Pager pager = new Pager(articleTclSearchResult.getArticlePage().getTotalPages(), articleTclSearchResult.getArticlePage().getNumber(),
                                InitialPagingSizes.BUTTONS_TO_SHOW, articleTclSearchResult.getArticlePage().getTotalElements());

        modelAndView.addObject("numberFormatException", true);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("articles", articleTclSearchResult.getArticlePage());
        modelAndView.setViewName("adminPage/article/tcl/articles");
        return modelAndView;
    }

    public ModelAndView respondToEmptySearchResult(ModelAndView modelAndView, PageRequest pageRequest) {
        modelAndView.addObject("noMatches", true);
        modelAndView.addObject("articles", articleTclDtoService.findAllPageable(pageRequest));
        return modelAndView;
    }
}
