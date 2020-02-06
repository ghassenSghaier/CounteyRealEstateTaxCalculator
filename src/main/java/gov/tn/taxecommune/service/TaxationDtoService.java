package gov.tn.taxecommune.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.web.dto.TaxationDto;

/**
 * Created by Keno&Kemo on 04.12.2017..
 */
@Service
public class TaxationDtoService {

	private TaxationService taxationService;
	private ModelMapper modelMapper;

	public TaxationDtoService(TaxationService taxationService, ModelMapper modelMapper) {
		this.taxationService = taxationService;
		this.modelMapper = modelMapper;
	}

	public List<TaxationDto> findAll() {
		List<Taxation> users = taxationService.findAll();
		return users.stream().map(user -> modelMapper.map(user, TaxationDto.class)).collect(Collectors.toList());
	}

	public Page<TaxationDto> findAllPageable(Pageable pageable) {
		Page<Taxation> users = taxationService.findAllPageable(pageable);
		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(userDtos, pageable, users.getTotalElements());
	}

	public Optional<TaxationDto> findById(Long id) {
		Optional<Taxation> retrievedUser = taxationService.findById(id);
		return retrievedUser.map(user -> modelMapper.map(user, TaxationDto.class));
	}

	public TaxationDto findByArticle(String email) {
		return modelMapper.map(taxationService.findByArticle(email), TaxationDto.class);
	}

	public Page<TaxationDto> findByIdPageable(Long id, PageRequest pageRequest) {
		Page<Taxation> users = taxationService.findByIdPageable(id, pageRequest);
		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
	}

//	public Page<TaxationDto> findByArticleContaining(String codeArticle, PageRequest pageRequest) {
//		Page<Taxation> users = taxationService.findByArticleContaining(codeArticle, pageRequest);
//		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
//				.collect(Collectors.toList());
//		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
//	}
//	
//	public Page<TaxationDto> findByMontantFNAHContaining(double fnah, PageRequest pageRequest) {
//		Page<Taxation> users = taxationService.findByFNAHContaining(Double.valueOf(fnah), pageRequest);
//		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
//				.collect(Collectors.toList());
//		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
//	}
//	
//	public Page<TaxationDto> findByDateTaxationContaining(Date date, PageRequest pageRequest) {
//		Page<Taxation> users = taxationService.findByDateContaining(date, pageRequest);
//		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
//				.collect(Collectors.toList());
//		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
//	}
//
//	public Page<TaxationDto> findByMontantTIBContaining(double tib, PageRequest pageRequest) {
//		Page<Taxation> users = taxationService.findByTIBContaining(tib, pageRequest);
//		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
//				.collect(Collectors.toList());
//		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
//	}
//
//	public Page<TaxationDto> findByMontantTCLContaining(double tcl, PageRequest pageRequest) {
//		Page<Taxation> users = taxationService.findByTCLContaining(tcl, pageRequest);
//		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
//				.collect(Collectors.toList());
//		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
//	}

//	public Page<TaxationDto> findByMontantTTNBContaining(double tnb, PageRequest pageRequest) {
//		Page<Taxation> users = taxationService.findByTTNBContaining(tnb, pageRequest);
//		List<TaxationDto> userDtos = users.stream().map(user -> modelMapper.map(user, TaxationDto.class))
//				.collect(Collectors.toList());
//		return new PageImpl<>(userDtos, pageRequest, users.getTotalElements());
//	}
}
