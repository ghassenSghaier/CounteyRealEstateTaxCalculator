package gov.tn.taxecommune.service.searching;

import lombok.Data;
import org.springframework.data.domain.Page;
import gov.tn.taxecommune.web.dto.ArticleDto;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ArticleSearchResult {
    private Page<ArticleDto> articlePage;
    private boolean numberFormatException;
	public void setArticlePage(Page<ArticleDto> findAllPageable) {
		this.articlePage=findAllPageable;
		
	}
	
	public Page<ArticleDto> getArticlePage() {
		return articlePage;
		
	}
	public boolean isNumberFormatException() {
		return numberFormatException;
		
	}
	public ArticleSearchResult(Page<ArticleDto> articlePage, boolean numberFormatException) {
		super();
		this.articlePage = articlePage;
		this.numberFormatException = numberFormatException;
	}
	public ArticleSearchResult() {
		super();
	}
	
	
}
