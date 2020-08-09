package gov.tn.taxecommune.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.web.dto.ArticleUpdateDto;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */

@Service
public class ArticleUpdateDtoService {

	private ArticleService articleService;
	private ModelMapper modelMapper;

	public ArticleUpdateDtoService(ArticleService articleService, ModelMapper modelMapper) {
		this.articleService = articleService;
		this.modelMapper = modelMapper;
	}

	public List<ArticleUpdateDto> findAll() {
		List<Article> articleList = articleService.findAllEagerly();
		List<ArticleUpdateDto> articleUpdateDtosList = new ArrayList<>();

		for (Article article : articleList) {
			articleUpdateDtosList.add(modelMapper.map(article, ArticleUpdateDto.class));
		}
		return articleUpdateDtosList;
	}

	public ArticleUpdateDto findById(String id) {
		return modelMapper.map(articleService.findByNumeroMunicipalEagerly(id), ArticleUpdateDto.class);
	}

}
