package gov.tn.taxecommune.service.searching;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.service.DeclarationDtoService;
import gov.tn.taxecommune.web.dto.DeclarationDto;
import lombok.Data;

@Data
@Service
public class DeclarationFinder {
	private DeclarationDtoService declarationDtoService;

	@Autowired
	public DeclarationFinder(DeclarationDtoService declarationDtoService) {
		this.declarationDtoService = declarationDtoService;
	}

	public DeclarationSearchResult searchDeclarationsByProperty(PageRequest pageRequest,
			DeclarationSearchParameters declarationSearchParameters) {
		Page<DeclarationDto> declarationDtoPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
		switch (declarationSearchParameters.getDeclarationsProperty().get()) {

		case "ID":
			try {
				long id = Long.parseLong(declarationSearchParameters.getPropertyValue().get());
				declarationDtoPage = declarationDtoService.findByIdPageable(id, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new DeclarationSearchResult(declarationDtoService.findAllPageable(pageRequest), true);
			}
			break;
//		case "StatutDeclarant":
//			declarationDtoPage = declarationDtoService
//					.findByStatutDeclarantContaining(declarationSearchParameters.getPropertyValue().get(), pageRequest);
			//break;
//		case "SurfaceNB":
//			declarationDtoPage = declarationDtoService.findBySurfaceNBContaining(
//					Double.parseDouble(declarationSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
//		case "SurfaceTotal":
//			declarationDtoPage = declarationDtoService.findBySurfaceTotalContaining(
//					Double.parseDouble(declarationSearchParameters.getPropertyValue().get()), pageRequest);
//			break;
//		case "EtatDeclare":
//			declarationDtoPage = declarationDtoService
//					.findByEtatDeclareContaining(declarationSearchParameters.getPropertyValue().get(), pageRequest);
//			break;

		}

		return new DeclarationSearchResult(declarationDtoPage, false);
	}
}
