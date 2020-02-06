package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.Page;
import gov.tn.taxecommune.web.dto.DeclarationDto;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class DeclarationSearchResult {
	private Page<DeclarationDto> declarationPage;
	private boolean numberFormatException;

	public void setDeclarationPage(Page<DeclarationDto> findAllPageable) {
		this.declarationPage = findAllPageable;

	}

	public Page<DeclarationDto> getDeclarationPage() {
		return declarationPage;

	}

	public boolean isNumberFormatException() {
		return numberFormatException;

	}

	public DeclarationSearchResult(Page<DeclarationDto> declarationPage, boolean numberFormatException) {
		super();
		this.declarationPage = declarationPage;
		this.numberFormatException = numberFormatException;
	}

	public DeclarationSearchResult() {
		super();
	}

}
