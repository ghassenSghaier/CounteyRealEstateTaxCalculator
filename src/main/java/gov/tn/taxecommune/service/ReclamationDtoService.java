package gov.tn.taxecommune.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.Reclamation;
import gov.tn.taxecommune.web.dto.ReclamationDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Keno&Kemo on 04.12.2017..
 */
@Service
public class ReclamationDtoService {

	private ReclamationService reclamationService;
	private ModelMapper modelMapper;

	public ReclamationDtoService(ReclamationService reclamationService, ModelMapper modelMapper) {
		this.reclamationService = reclamationService;
		this.modelMapper = modelMapper;
	}

	public List<ReclamationDto> findAll() {
		List<Reclamation> reclamations = reclamationService.findAll();
		return reclamations.stream().map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class))
				.collect(Collectors.toList());
	}

	public Page<ReclamationDto> findAllPageable(Pageable pageable) {
		Page<Reclamation> reclamations = reclamationService.findAllPageable(pageable);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageable, reclamations.getTotalElements());
	}

	public Page<ReclamationDto> findAllPageableForUser(String username, Pageable pageable) {
		Page<Reclamation> reclamations = reclamationService.findAllPageableForUser(username, pageable);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageable, reclamations.getTotalElements());
	}

	public Optional<ReclamationDto> findById(Long id) {
		Optional<Reclamation> retrievedreclamation = reclamationService.findById(id);
		return retrievedreclamation.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class));
	}

	public ReclamationDto findByUser(String username) {
		return modelMapper.map(reclamationService.findByUser(username), ReclamationDto.class);
	}

	public Page<ReclamationDto> findByUserPageable(String codeUser, PageRequest pageRequest) {
		Page<Reclamation> reclamations = reclamationService.findByUserPageable(codeUser, pageRequest);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
	}

	public Page<ReclamationDto> findByArticlePageable(String codeArticle, PageRequest pageRequest) {
		Page<Reclamation> reclamations = reclamationService.findByArticlePageable(codeArticle, pageRequest);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
	}

	public Page<ReclamationDto> findByTaxationPageable(String codeTaxation, PageRequest pageRequest) {
		Page<Reclamation> reclamations = reclamationService.findByTaxationPageable(codeTaxation, pageRequest);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
	}

	public Page<ReclamationDto> findByMotifPageable(String motif, PageRequest pageRequest) {
		Page<Reclamation> reclamations = reclamationService.findByMotifPageable(motif, pageRequest);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
	}

	public Page<ReclamationDto> findByIdPageable(Long id, PageRequest pageRequest) {
		Page<Reclamation> reclamations = reclamationService.findByIdPageable(id, pageRequest);
		List<ReclamationDto> reclamationDtos = reclamations.stream()
				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
	}

	public ReclamationDto findByCodeReclamation(String reclamationname) {
		return modelMapper.map(reclamationService.findByCodeReclamation(reclamationname), ReclamationDto.class);
	}

//	public Page<ReclamationDto> findByArticleContaining(String name, PageRequest pageRequest) {
//		Page<Reclamation> reclamations = reclamationService.findByArticleContaining(name, pageRequest);
//		List<ReclamationDto> reclamationDtos = reclamations.stream()
//				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
//	}
//
//	public Page<ReclamationDto> findByTaxationContaining(String surname, PageRequest pageRequest) {
//		Page<Reclamation> reclamations = reclamationService.findByTaxationContaining(surname, pageRequest);
//		List<ReclamationDto> reclamationDtos = reclamations.stream()
//				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
//	}
//
//	public Page<ReclamationDto> findByMotifContaining(String reclamationname, PageRequest pageRequest) {
//		Page<Reclamation> reclamations = reclamationService.findByMotifContaining(reclamationname, pageRequest);
//		List<ReclamationDto> reclamationDtos = reclamations.stream()
//				.map(reclamation -> modelMapper.map(reclamation, ReclamationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(reclamationDtos, pageRequest, reclamations.getTotalElements());
//	}

}
