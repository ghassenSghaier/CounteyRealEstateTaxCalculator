package gov.tn.taxecommune.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.Reclamation;
import gov.tn.taxecommune.web.dto.ReclamationUpdateDto;
import gov.tn.taxecommune.web.dto.UserUpdateDto;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */

@Service
public class ReclamationUpdateDtoService {

	private ReclamationService reclamationService;
	private ModelMapper modelMapper;

	public ReclamationUpdateDtoService(DeclarationService declarationService, ModelMapper modelMapper) {
		this.reclamationService = reclamationService;
		this.modelMapper = modelMapper;
	}

	public List<ReclamationUpdateDto> findAll() {
		List<Reclamation> reclList = reclamationService.findAllEagerly();
		List<ReclamationUpdateDto> reclamationUpdateDtosList = new ArrayList<>();

		for (Reclamation rec : reclList) {
			reclamationUpdateDtosList.add(modelMapper.map(rec, ReclamationUpdateDto.class));
		}
		return reclamationUpdateDtosList;
	}

	public ReclamationUpdateDto findById(Long id) {
		return modelMapper.map(reclamationService.findByIdEagerly(id), ReclamationUpdateDto.class);
	}

	public ReclamationService getReclamationService() {
		return reclamationService;
	}

}
