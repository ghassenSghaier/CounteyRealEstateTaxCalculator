package gov.tn.taxecommune.service.searching;

import java.util.Collections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import gov.tn.taxecommune.service.ArticleDtoService;
import gov.tn.taxecommune.service.ArticleTclDtoService;
import gov.tn.taxecommune.web.dto.ArticleTclDto;

public class ArticleTclFinder {

	private ArticleTclDtoService articleDtoService;
	private ArticleTclDtoService articleTclDtoService;

	public ArticleTclFinder(ArticleDtoService articleDtoService, ArticleTclDtoService articleTclDtoService) {
//		super(articleDtoService);
		// TODO Auto-generated constructor stub
		this.articleTclDtoService = articleTclDtoService;
	}

	public ArticleTclSearchResult searchArticlesByProperty(PageRequest pageRequest,
			ArticleSearchParameters articleSearchParameters) {
		Page<ArticleTclDto> articleDtoPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
		switch (articleSearchParameters.getArticlesProperty().get()) {
		case "NumeroMunicipal":
			try {
				long id = Long.parseLong(articleSearchParameters.getPropertyValue().get());
				articleDtoPage = articleDtoService.findByIdPageable(id, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ArticleTclSearchResult(articleTclDtoService.findAllPageable(pageRequest), true);
			}
			break;
		case "RepArticle":
			try {
				int repArticle = Integer.parseInt(articleSearchParameters.getPropertyValue().get());
				articleDtoPage = articleDtoService.findByRepArticleContaining(repArticle, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ArticleTclSearchResult(articleTclDtoService.findAllPageable(pageRequest), true);
			}
			break;
		case "CodePostal":
			articleDtoPage = articleDtoService
					.findByCodePostalContaining(articleSearchParameters.getPropertyValue().get(), pageRequest);
			break;
		case "SurfaceTotal":
			try {
				double surfaceTotal = Double.parseDouble(articleSearchParameters.getPropertyValue().get());
				articleDtoPage = articleDtoService.findBySurfaceTotalContaining(surfaceTotal, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ArticleTclSearchResult(articleTclDtoService.findAllPageable(pageRequest), true);
			}
			break;
		}
		return new ArticleTclSearchResult(articleDtoPage, false);
	}

}
