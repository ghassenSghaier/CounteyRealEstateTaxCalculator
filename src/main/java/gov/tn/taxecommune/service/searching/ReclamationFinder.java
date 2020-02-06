package gov.tn.taxecommune.service.searching;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.service.ReclamationDtoService;
import gov.tn.taxecommune.web.dto.ReclamationDto;
import lombok.Data;

@Data
@Service
public class ReclamationFinder {
	private ReclamationDtoService reclamationDtoService;

	@Autowired
	public ReclamationFinder(ReclamationDtoService reclamationDtoService) {
		this.reclamationDtoService = reclamationDtoService;
	}

	public ReclamationSearchResult searchReclamationsByProperty(PageRequest pageRequest,
			ReclamationSearchParameters reclamationSearchParameters) {
		Page<ReclamationDto> reclamationDtoPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);
		String user = null;
		switch (reclamationSearchParameters.getReclamationsProperty().get()) {

		case "ID":
			try {
				long id = Long.parseLong(reclamationSearchParameters.getPropertyValue().get());
				reclamationDtoPage = reclamationDtoService.findByIdPageable(id, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ReclamationSearchResult(reclamationDtoService.findAllPageable(pageRequest), true);
			}
			break;
		case "user":
			try {
				user = reclamationSearchParameters.getPropertyValue().get();
				reclamationDtoPage = reclamationDtoService.findByUserPageable(user, pageRequest);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new ReclamationSearchResult(reclamationDtoService.findAllPageable(pageRequest), true);
			}
			break;
		case "Article":

			try {
				user = reclamationSearchParameters.getPropertyValue().get();
				String article = reclamationSearchParameters.getPropertyValue().get();
				reclamationDtoPage = reclamationDtoService.findByArticlePageable(article, pageRequest);

			} catch (NumberFormatException e) {

				e.printStackTrace();
				return new ReclamationSearchResult(reclamationDtoService.findAllPageable(pageRequest), true);
			}

			break;

		case "taxation":

			try {
				user = reclamationSearchParameters.getPropertyValue().get();
				String article = reclamationSearchParameters.getPropertyValue().get();
				reclamationDtoPage = reclamationDtoService.findByTaxationPageable(article, pageRequest);

			} catch (NumberFormatException e) {

				e.printStackTrace();
				return new ReclamationSearchResult(reclamationDtoService.findAllPageable(pageRequest), true);
			}

			break;

		case "motif":

			try {
				String article = reclamationSearchParameters.getPropertyValue().get();
				reclamationDtoPage = reclamationDtoService.findByMotifPageable(article, pageRequest);

			} catch (NumberFormatException e) {

				e.printStackTrace();
				return new ReclamationSearchResult(reclamationDtoService.findAllPageable(pageRequest), true);
			}

			break;

		}

		return new ReclamationSearchResult(reclamationDtoPage, false);
	}
}
