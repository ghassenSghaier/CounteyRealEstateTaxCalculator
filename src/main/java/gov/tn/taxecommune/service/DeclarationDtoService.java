package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.Declaration;
import gov.tn.taxecommune.web.dto.DeclarationDto;

/**
 * Created by Keno&Kemo on 04.12.2017..
 */
@Service
public class DeclarationDtoService<T> {

	private DeclarationService declarationService;
	private ModelMapper modelMapper;

	public DeclarationDtoService(DeclarationService declarationService, ModelMapper modelMapper) {
		this.declarationService = declarationService;
		this.modelMapper = modelMapper;
	}

	public List<DeclarationDto> findAll() {
		List<Declaration> declarations = declarationService.findAll();
		return declarations.stream().map(declaration -> modelMapper.map(declaration, DeclarationDto.class))
				.collect(Collectors.toList());
	}

	public Page<DeclarationDto> findAllPageable(Pageable pageable) {
		Page<Declaration> declarations = declarationService.findAllPageable(pageable);
		List<DeclarationDto> declarationDtos = declarations.stream()
				.map(declaration -> modelMapper.map(declaration, DeclarationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(declarationDtos, pageable, declarations.getTotalElements());
	}

	public Optional<DeclarationDto> findById(Long id) {
		Optional<Declaration> retrieveddeclaration = declarationService.findById(id);
		return retrieveddeclaration.map(declaration -> modelMapper.map(declaration, DeclarationDto.class));
	}

	public DeclarationDto findByEmail(String email) {
		return modelMapper.map(declarationService.findByEmail(email), DeclarationDto.class);
	}

	public Page<DeclarationDto> findByIdPageable(Long id, PageRequest pageRequest) {
		Page<Declaration> declarations = declarationService.findByIdPageable(id, pageRequest);
		List<DeclarationDto> declarationDtos = declarations.stream()
				.map(declaration -> modelMapper.map(declaration, DeclarationDto.class)).collect(Collectors.toList());
		return new PageImpl<>(declarationDtos, pageRequest, declarations.getTotalElements());
	}

//	public Page<DeclarationDto> findByStatutDeclarantContaining(String name, PageRequest pageRequest) {
//		Page<Declaration> declarations = declarationService.findByStatutDeclarantContaining(name, pageRequest);
//		List<DeclarationDto> declarationDtos = declarations.stream()
//				.map(declaration -> modelMapper.map(declaration, DeclarationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(declarationDtos, pageRequest, declarations.getTotalElements());
//	}
//
//	public Page<DeclarationDto> findByEtatDeclareContaining(String surname, PageRequest pageRequest) {
//		Page<Declaration> declarations = declarationService.findByEtatDeclareContaining(surname, pageRequest);
//		List<DeclarationDto> declarationDtos = declarations.stream()
//				.map(declaration -> modelMapper.map(declaration, DeclarationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(declarationDtos, pageRequest, declarations.getTotalElements());
//	}

//	public Page<DeclarationDto> findBySurfaceTotalContaining(double string, PageRequest pageRequest) {
//		Page<Declaration> declarations = declarationService.findBySurfaceTotalContaining(string, pageRequest);
//		List<DeclarationDto> declarationDtos = declarations.stream()
//				.map(declaration -> modelMapper.map(declaration, DeclarationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(declarationDtos, pageRequest, declarations.getTotalElements());
//	}
//
//	public Page<DeclarationDto> findBySurfaceNBContaining(double surfaceNB, PageRequest pageRequest) {
//		Page<Declaration> declarations = declarationService.findBySurfaceNBContaining(surfaceNB, pageRequest);
//		List<DeclarationDto> declarationDtos = declarations.stream()
//				.map(declaration -> modelMapper.map(declaration, DeclarationDto.class)).collect(Collectors.toList());
//		return new PageImpl<>(declarationDtos, pageRequest, declarations.getTotalElements());
//	}
}
