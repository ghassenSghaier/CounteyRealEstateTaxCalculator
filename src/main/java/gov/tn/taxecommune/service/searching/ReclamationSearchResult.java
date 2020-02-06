package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.Page;

import gov.tn.taxecommune.web.dto.ReclamationDto;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ReclamationSearchResult {
	private Page<ReclamationDto> reclamationPage;
	private boolean numberFormatException;

	public void setReclamationPage(Page<ReclamationDto> findAllPageable) {
		this.reclamationPage = findAllPageable;

	}

	public Page<ReclamationDto> getReclamationPage() {
		return reclamationPage;

	}

	public boolean isNumberFormatException() {
		return numberFormatException;

	}

	public ReclamationSearchResult(Page<ReclamationDto> reclamationPage, boolean numberFormatException) {
		super();
		this.reclamationPage = reclamationPage;
		this.numberFormatException = numberFormatException;
	}

	public ReclamationSearchResult() {
		super();
	}

}
