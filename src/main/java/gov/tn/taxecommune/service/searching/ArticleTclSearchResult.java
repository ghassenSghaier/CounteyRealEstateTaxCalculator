package gov.tn.taxecommune.service.searching;

import lombok.Data;
import org.springframework.data.domain.Page;
import gov.tn.taxecommune.web.dto.ArticleDto;
import gov.tn.taxecommune.web.dto.ArticleTclDto;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ArticleTclSearchResult{
    private Page<ArticleTclDto> articlePage;
    private boolean numberFormatException;
        
	public void setArticlePage(Page<ArticleTclDto> findAllPageable) {
		this.articlePage=findAllPageable;
		
	}    
	public Page<ArticleTclDto> getArticlePage() {
		return articlePage;
		
	}
	public boolean isNumberFormatException() {
		return numberFormatException;
		
	}
	public ArticleTclSearchResult(Page<ArticleTclDto> articlePage, boolean numberFormatException) {
		super();
		this.articlePage = articlePage;
		this.numberFormatException = numberFormatException;
	}
	public ArticleTclSearchResult() {
		super();
	}
	
	
}
