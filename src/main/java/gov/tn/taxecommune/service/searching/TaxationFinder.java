package gov.tn.taxecommune.service.searching;

import java.util.Collections;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.service.TaxationDtoService;
import gov.tn.taxecommune.web.dto.TaxationDto;
import lombok.Data;

@Data
@Service
public class TaxationFinder {
	private TaxationDtoService taxationDtoService;

	@Autowired
	public TaxationFinder(TaxationDtoService taxationDtoService) {
		this.taxationDtoService = taxationDtoService;
	}

	public TaxationSearchResult searchTaxationsByProperty(PageRequest pageRequest,
			TaxationSearchParameters userSearchParameters) {
		Page<TaxationDto> userDtoPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
		switch (userSearchParameters.getTaxationsProperty().get()) {
		case "ID":
			try {
				long id = Long.parseLong(userSearchParameters.getPropertyValue().get());
				userDtoPage = taxationDtoService.findByIdPageable(id, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new TaxationSearchResult(taxationDtoService.findAllPageable(pageRequest), true);
			}
			break;
//		case "Article":
//			userDtoPage = taxationDtoService.findByArticleContaining(userSearchParameters.getPropertyValue().get(),
//					pageRequest);
//			break;
//		case "MontantTCL":
//			userDtoPage = taxationDtoService.findByMontantTCLContaining(
//					Double.valueOf(userSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
//		case "MontantTNB":
//			userDtoPage = taxationDtoService.findByMontantTTNBContaining(
//					Double.valueOf(userSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
//		case "MontantTIB":
//			userDtoPage = taxationDtoService.findByMontantTIBContaining(
//					Double.valueOf(userSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
//		case "MontantFNAH":
//			userDtoPage = taxationDtoService.findByMontantFNAHContaining(
//					Double.valueOf(userSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
//		case "AnneeTaxation":
//
//			userDtoPage = taxationDtoService
//					.findByDateTaxationContaining(new Date(userSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
		}
		return new TaxationSearchResult(userDtoPage, false);
	}
}
