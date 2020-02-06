package gov.tn.taxecommune.service.searching;

import org.springframework.data.domain.Page;

import gov.tn.taxecommune.web.dto.TaxationDto;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TaxationSearchResult {
	private Page<TaxationDto> taxationPage;
	private boolean numberFormatException;

	public void setTaxationPage(Page<TaxationDto> findAllPageable) {
		this.taxationPage = findAllPageable;

	}

	public Page<TaxationDto> getTaxationPage() {
		return taxationPage;

	}

	public boolean isNumberFormatException() {
		return numberFormatException;

	}

	public TaxationSearchResult(Page<TaxationDto> taxationPage, boolean numberFormatException) {
		super();
		this.taxationPage = taxationPage;
		this.numberFormatException = numberFormatException;
	}

	public TaxationSearchResult() {
		super();
	}

}
