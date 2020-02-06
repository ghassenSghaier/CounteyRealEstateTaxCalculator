package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import gov.tn.taxecommune.service.ArticleDtoService;
import gov.tn.taxecommune.service.UserDtoService;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Service
public class ArticleSearchErrorResponse {

    private ArticleDtoService articleDtoService;

    public ArticleSearchErrorResponse(ArticleDtoService articleDtoService) {
        this.articleDtoService = articleDtoService;
    }

    public ModelAndView respondToNumberFormatException(ArticleSearchResult articleSearchResult, ModelAndView modelAndView) {
        Pager pager = new Pager(articleSearchResult.getArticlePage().getTotalPages(), articleSearchResult.getArticlePage().getNumber(),
                                InitialPagingSizes.BUTTONS_TO_SHOW, articleSearchResult.getArticlePage().getTotalElements());

        modelAndView.addObject("numberFormatException", true);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("articles", articleSearchResult.getArticlePage());
        modelAndView.setViewName("adminPage/article/articles");
        return modelAndView;
    }

    public ModelAndView respondToEmptySearchResult(ModelAndView modelAndView, PageRequest pageRequest) {
        modelAndView.addObject("noMatches", true);
        modelAndView.addObject("articles", articleDtoService.findAllPageable(pageRequest));
        return modelAndView;
    }
}
