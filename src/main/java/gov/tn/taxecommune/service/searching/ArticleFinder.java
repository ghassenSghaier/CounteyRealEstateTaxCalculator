package gov.tn.taxecommune.service.searching;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.service.ArticleDtoService;
import gov.tn.taxecommune.web.dto.ArticleDto;

import java.util.Collections;

@Data
@Service
public class ArticleFinder {
	private ArticleDtoService articleDtoService;

	@Autowired
	public ArticleFinder(ArticleDtoService articleDtoService) {
		this.articleDtoService = articleDtoService;
	}

	public ArticleSearchResult searchArticlesByProperty(PageRequest pageRequest,
			ArticleSearchParameters articleSearchParameters) {
		Page<ArticleDto> articleDtoPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
		switch (articleSearchParameters.getArticlesProperty().get()) {
		case "NumeroMunicipal":
			try {
				long id = Long.parseLong(articleSearchParameters.getPropertyValue().get());
				articleDtoPage = articleDtoService.findByIdPageable(id, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ArticleSearchResult(articleDtoService.findAllPageable(pageRequest), true);
			}
			break;
		case "RepArticle":
			try {
				int repArticle = Integer.parseInt(articleSearchParameters.getPropertyValue().get());
				articleDtoPage = articleDtoService.findByRepArticleContaining(repArticle, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ArticleSearchResult(articleDtoService.findAllPageable(pageRequest), true);
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
				return new ArticleSearchResult(articleDtoService.findAllPageable(pageRequest), true);
			}
			break;
		}
		return new ArticleSearchResult(articleDtoPage, false);
	}
}
