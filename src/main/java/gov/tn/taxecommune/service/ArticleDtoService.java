package gov.tn.taxecommune.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.web.dto.ArticleDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Keno&Kemo on 04.12.2017..
 */
@Service
public class ArticleDtoService<T> {

//    private UserService userService;
	private ArticleService articleService;
	private ModelMapper modelMapper;

	public ArticleDtoService(ArticleService articleService, ModelMapper modelMapper) {
		this.articleService = articleService;
		this.modelMapper = modelMapper;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public List<ArticleDto> findAll() {
		List<Article> articles = articleService.findAll();
		return articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
	}

	public Page<ArticleDto> findAllPageable(Pageable pageable) {
		Page<Article> articles = articleService.findAllPageable(pageable);
		List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageable, articles.getTotalElements());
	}

	public Optional<ArticleDto> findById(Long id) {
		Optional<Article> retrievedArticle = articleService.findById(id);
		return retrievedArticle.map(article -> modelMapper.map(article, ArticleDto.class));
	}

	public Page<ArticleDto> findByIdPageable(Long id, PageRequest pageRequest) {
		Page<Article> articles = articleService.findByIdPageable(id, pageRequest);
		List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageRequest, articles.getTotalElements());
	}

	public Page<ArticleDto> findByCodePostalContaining(String codePostal, PageRequest pageRequest) {
		Page<Article> articles = articleService.findByCodePostalContaining(codePostal, pageRequest);
		List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageRequest, articles.getTotalElements());
	}

	public Page<ArticleDto> findByRepArticleContaining(int repArticle, PageRequest pageRequest) {
		Page<Article> articles = articleService.findByRepArticleContaining(repArticle, pageRequest);
		List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageRequest, articles.getTotalElements());
	}

	public Page<ArticleDto> findBySurfaceTotalContaining(double surfaceTotal, PageRequest pageRequest) {
		Page<Article> articles = articleService.findBySurfaceTotalContaining(surfaceTotal, pageRequest);
		List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageRequest, articles.getTotalElements());
	}

	public Page<ArticleDto> findBySurfaceCouvertContaining(double surfaceCouvert, PageRequest pageRequest) {
		Page<Article> articles = articleService.findBySurfaceCouvertContaining(surfaceCouvert, pageRequest);
		List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(articleDtos, pageRequest, articles.getTotalElements());
	}

//    public Page<ArticleDto> findByPrestationContaining(String prestation, PageRequest pageRequest) {
//        Page<Article> articles = articleService.findByPrestationContaining(prestation, pageRequest);
//        List<ArticleDto> articleDtos = articles.stream().map(article -> modelMapper.map(article, ArticleDto.class)).collect(Collectors.toList());
//        return new PageImpl<>(articleDtos, pageRequest, articles.getTotalElements());
//    }

}
