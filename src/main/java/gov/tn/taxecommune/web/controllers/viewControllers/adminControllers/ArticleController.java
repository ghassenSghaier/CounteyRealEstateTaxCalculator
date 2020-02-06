package gov.tn.taxecommune.web.controllers.viewControllers.adminControllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.validation.Valid;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
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
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.entity.Rue;
import gov.tn.taxecommune.service.ArticleDtoService;
import gov.tn.taxecommune.service.ArticleService;
import gov.tn.taxecommune.service.ArticleTclDtoService;
import gov.tn.taxecommune.service.ArticleUpdateDtoService;
import gov.tn.taxecommune.service.ContribuableService;
import gov.tn.taxecommune.service.PrestationService;
import gov.tn.taxecommune.service.RueService;
import gov.tn.taxecommune.service.searching.ArticleFinder;
import gov.tn.taxecommune.service.searching.ArticleSearchErrorResponse;
import gov.tn.taxecommune.service.searching.ArticleSearchParameters;
import gov.tn.taxecommune.service.searching.ArticleSearchResult;
import gov.tn.taxecommune.service.searching.ArticleTclFinder;
import gov.tn.taxecommune.service.searching.ArticleTclSearchErrorResponse;
import gov.tn.taxecommune.service.searching.ArticleTclSearchResult;
import gov.tn.taxecommune.web.dto.ArticleDto;
import gov.tn.taxecommune.web.dto.ArticleTclDto;
import gov.tn.taxecommune.web.dto.ArticleUpdateDto;
import gov.tn.taxecommune.web.paging.InitialPagingSizes;
import gov.tn.taxecommune.web.paging.Pager;

@Controller
@RequestMapping("/adminPage")
public class ArticleController {

    private ArticleService articleService;
    private RueService rueService;
    private ArticleUpdateDtoService articleUpdateDtoService;
    private ArticleDtoService articleDtoService;
    private ContribuableService contribuableService;
    private PrestationService pService;
    private ArticleTclDtoService articleTclDtoService;
    private ArticleFinder articleFinder;
    private ArticleTclFinder articleTclFinder;
    private ArticleSearchErrorResponse articleSearchErrorResponse;
    private ArticleTclSearchErrorResponse articleTclSearchErrorResponse;

    public ArticleController(ArticleService articleService, RueService rueService,
            ArticleUpdateDtoService articleUpdateDtoService, PrestationService pService,
            ArticleDtoService articleDtoService, ArticleFinder articleFinder,
            ArticleSearchErrorResponse articleSearchErrorResponse,
            ArticleTclSearchErrorResponse articleTclSearchErrorResponse, ArticleTclDtoService articleTclDtoService) {
        this.articleService = articleService;
        this.rueService = rueService;
        this.pService = pService;
        this.articleUpdateDtoService = articleUpdateDtoService;
        this.articleDtoService = articleDtoService;
        this.articleFinder = articleFinder;
        this.articleSearchErrorResponse = articleSearchErrorResponse;
        this.articleTclSearchErrorResponse = articleTclSearchErrorResponse;
        this.articleTclDtoService = articleTclDtoService;
    }

    /*
	 * Get all users or search users if there are searching parameters
     */
    @GetMapping("/articles")
    public ModelAndView getArticles(ModelAndView modelAndView, ArticleSearchParameters articleSearchParameters) {
        int selectedPageSize = articleSearchParameters.getPageSize().orElse(InitialPagingSizes.INITIAL_PAGE_SIZE);
        int selectedPage = (articleSearchParameters.getPage().orElse(0) < 1) ? InitialPagingSizes.INITIAL_PAGE
                : (articleSearchParameters.getPage().get() - 1);

        PageRequest pageRequest = PageRequest.of(selectedPage, selectedPageSize, new Sort(Sort.Direction.ASC, "id"));
        ArticleSearchResult articleSearchResult = new ArticleSearchResult();

        // Empty search parameters
        if (!articleSearchParameters.getPropertyValue().isPresent()
                || articleSearchParameters.getPropertyValue().get().isEmpty()) {
            articleSearchResult.setArticlePage(articleDtoService.findAllPageable(pageRequest));
        } // Search queries
        else {
            articleSearchResult = articleFinder.searchArticlesByProperty(pageRequest, articleSearchParameters);

            if (articleSearchResult.isNumberFormatException()) {
                return articleSearchErrorResponse.respondToNumberFormatException(articleSearchResult, modelAndView);
            }

            if (articleSearchResult.getArticlePage().getTotalElements() == 0) {
                modelAndView = articleSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
                articleSearchResult.setArticlePage(articleDtoService.findAllPageable(pageRequest));
            }
            modelAndView.addObject("articlesProperty", articleSearchParameters.getArticlesProperty().get());
            modelAndView.addObject("propertyValue", articleSearchParameters.getPropertyValue().get());
        }

        Pager pager = new Pager(articleSearchResult.getArticlePage().getTotalPages(),
                articleSearchResult.getArticlePage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
                articleSearchResult.getArticlePage().getTotalElements());
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("articles", articleSearchResult.getArticlePage());
        modelAndView.addObject("selectedPageSize", selectedPageSize);
        modelAndView.addObject("pageSizes", InitialPagingSizes.PAGE_SIZES);
        modelAndView.setViewName("adminPage/article/articles");
        return modelAndView;
    }

    @GetMapping("articles/tcl")
    public ModelAndView getArticlesTcl(ModelAndView modelAndView, ArticleSearchParameters articleSearchParameters) {
        int selectedPageSize = articleSearchParameters.getPageSize().orElse(InitialPagingSizes.INITIAL_PAGE_SIZE);
        int selectedPage = (articleSearchParameters.getPage().orElse(0) < 1) ? InitialPagingSizes.INITIAL_PAGE
                : (articleSearchParameters.getPage().get() - 1);

        PageRequest pageRequest = PageRequest.of(selectedPage, selectedPageSize, new Sort(Sort.Direction.ASC, "numeroMunicipal"));
        ArticleTclSearchResult articleTclSearchResult = new ArticleTclSearchResult();

        // Empty search parameters
        if (!articleSearchParameters.getPropertyValue().isPresent()
                || articleSearchParameters.getPropertyValue().get().isEmpty()) {
            articleTclSearchResult.setArticlePage(articleTclDtoService.findAllPageable(pageRequest));
        } // Search queries
        else {
            articleTclSearchResult = articleTclFinder.searchArticlesByProperty(pageRequest, articleSearchParameters);

            if (articleTclSearchResult.isNumberFormatException()) {
                return articleTclSearchErrorResponse.respondToNumberFormatException(articleTclSearchResult,
                        modelAndView);
            }

            if (articleTclSearchResult.getArticlePage().getTotalElements() == 0) {
                modelAndView = articleSearchErrorResponse.respondToEmptySearchResult(modelAndView, pageRequest);
                articleTclSearchResult.setArticlePage(articleTclDtoService.findAllPageable(pageRequest));
            }
            modelAndView.addObject("articlesProperty", articleSearchParameters.getArticlesProperty().get());
            modelAndView.addObject("propertyValue", articleSearchParameters.getPropertyValue().get());
        }

        Pager pager = new Pager(articleTclSearchResult.getArticlePage().getTotalPages(),
                articleTclSearchResult.getArticlePage().getNumber(), InitialPagingSizes.BUTTONS_TO_SHOW,
                articleTclSearchResult.getArticlePage().getTotalElements());
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("articles", articleTclSearchResult.getArticlePage());
        modelAndView.addObject("selectedPageSize", selectedPageSize);
        modelAndView.addObject("pageSizes", InitialPagingSizes.PAGE_SIZES);
        modelAndView.setViewName("adminPage/article/tcl/articles");
        return modelAndView;
    }

    @GetMapping("/articles/{id}")
    public String getEditArticleForm(@PathVariable Long id, Model model) {
        ArticleUpdateDto articleUpdateDto = articleUpdateDtoService.findById(id);
        List<Rue> allRues = rueService.findAll();
        articleUpdateDto.setRue(articleService.getAssignedRue(articleUpdateDto));
        model.addAttribute("articleUpdateDto", articleUpdateDto);
        model.addAttribute("allRues", allRues);
        return "adminPage/user/editArticle";
    }

    @PostMapping("/articles/{id}")
    public String updateArticle(Model model, @PathVariable Long id,
            @ModelAttribute("oldArticle") @Valid ArticleUpdateDto articleUpdateDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        String formWithErrors = "adminPage/article/editArticle";
        Optional<Article> persistedArticle = articleService.findById(id);
        List<Rue> allRues = rueService.findAll();
        Article numeroMunicipalAlreadyExists = articleService.findByNumeroMunicipal(id).get();
        boolean hasErrors = false;

        if (numeroMunicipalAlreadyExists != null) {
            bindingResult.rejectValue("numeromunicipal", "numeroMunicipalAlreadyExists");
            hasErrors = true;
        }

//        if (usernameAlreadyExists != null) {
//            bindingResult.rejectValue("username", "usernameAlreadyExists");
//            hasErrors = true;
//        }
        if (bindingResult.hasErrors()) {
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("articleUpdateDto", articleUpdateDto);
            model.addAttribute("ruesList", allRues);
            model.addAttribute("org.springframework.validation.BindingResult.articleUpdateDto", bindingResult);
            return formWithErrors;
        } else {
            articleService.save(articleService.getUpdatedArticle(persistedArticle.get(), articleUpdateDto));
            redirectAttributes.addFlashAttribute("articleHasBeenUpdated", true);
            return "redirect:/adminPage/articles";
        }
    }

    @GetMapping("/articles/newArticle")
    public String getAddNewArticleForm(Model model) {
//		model.addAttribute("newArticle", new ArticleDto());
        return "adminPage/article/newArticle";
    }

    @GetMapping("/articles/newArticle/tcl")
    public String getAddNewArticleTclForm(Model model) {
        List<Prestation> prestations = pService.findAll();
        model.addAttribute("newArticle", new ArticleTclDto());
//		model.addAttribute("services",prestations);
        return "adminPage/article/tcl/newArticle";
    }

    @PostMapping("/articles/newArticle")
    public String saveNewArticle(@ModelAttribute("newArticle") @Valid ArticleDto newArticle,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Article numeroMunicipalAlreadyExists = articleService.findByNumeroMuniciaplNot((Long.parseLong(newArticle.getNumeroMunicipal())));
        boolean hasErrors = false;
        String formWithErrors = "adminPage/article/newArticle";

        if (numeroMunicipalAlreadyExists != null) {
            bindingResult.rejectValue("numeroMunicipal", "NumeroMunicipalAlreadyExists");
            hasErrors = true;
        }

        if (bindingResult.hasErrors()) {
            hasErrors = true;
        }

        if (hasErrors) {
            return formWithErrors;
        } else {
            Article article = articleService.createNewArticle(newArticle);
//            article.setEnabled(true);
            articleService.save(article);
            redirectAttributes.addFlashAttribute("articleHasBeenSaved", true);
            return "redirect:/adminPage/articles";
        }
    }

    @PostMapping("/articles/newArticle/tcl")
    public String saveNewArticleTcl(@ModelAttribute("newArticle") @Valid ArticleTclDto newArticle,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        org.jboss.logging.Logger log = LoggerFactory.logger(this.getClass());
        Article numeroMunicipalAlreadyExists = null;
        boolean hasErrors = false;
        String formWithErrors = "adminPage/article/tcl/newArticle";
        String urlTclArticles = "redirect:/adminPage/articles/tcl";
        String urlRedirect = null;
        System.out.println(newArticle.toString());
        log.debug(newArticle.toString());
        try {
            numeroMunicipalAlreadyExists = articleService.findByNumeroMunicipal(Long.parseLong(newArticle.getNumeroMunicipal())).get();
//			identifiantContribuableAlreadyExists = contribuableService.findByIdentifiantContribuable(newArticle.getIdentifiant()).get();

            if (numeroMunicipalAlreadyExists != null) {
                bindingResult.rejectValue("numeroMunicipal", "NumeroMunicipalAlreadyExists");
                hasErrors = true;
            }

            if (bindingResult.hasErrors()) {
                hasErrors = true;
            }

            if (hasErrors) {
                urlRedirect = formWithErrors;
                return urlRedirect;
            }

        } catch (NoSuchElementException e) {
            if (bindingResult.hasErrors()) {
                hasErrors = true;
            }

            if (hasErrors) {
                urlRedirect = formWithErrors;
                return urlRedirect;
            } else {
                Article article = articleService.createNewArticleTcl(newArticle);
//          article.setEnabled(true);
                articleService.save(article);
                redirectAttributes.addFlashAttribute("articleHasBeenSaved", true);
                urlRedirect = urlTclArticles;
                return urlRedirect;
            }
        }
        return urlRedirect;
    }
}
