package gov.tn.taxecommune.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Controle;
import gov.tn.taxecommune.entity.Declaration;
import gov.tn.taxecommune.repository.DeclarationRepository;
import gov.tn.taxecommune.web.dto.DeclarationDto;
import gov.tn.taxecommune.web.dto.DeclarationUpdateDto;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class DeclarationService {
	private DeclarationRepository declarationRepository;
	private ControleService controleService;
	private CacheManager cacheManager;

	public DeclarationService(DeclarationRepository declarationRepository, ControleService controleService) {
		super();
		this.declarationRepository = declarationRepository;
		this.controleService = controleService;
	}

	public DeclarationRepository getDeclarationRepository() {
		return declarationRepository;
	}

	public void setDeclarationRepository(DeclarationRepository declarationRepository) {
		this.declarationRepository = declarationRepository;
	}

	public ControleService getControleService() {
		return controleService;
	}

	public void setControleService(ControleService controleService) {
		this.controleService = controleService;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allDeclarations")
	public List<Declaration> findAll() {
		return declarationRepository.findAll();
	}

	@Cacheable(value = "cache.allDeclarationsPageable")
	public Page<Declaration> findAllPageable(Pageable pageable) {
		return declarationRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.DeclarationByEmail", key = "#email", unless = "#result == null")
	public List<Declaration> findByEmail(String email) {
		return declarationRepository.findByEmailEagerly(email).get();
	}

	@Cacheable(value = "cache.declarationById", key = "#id", unless = "#result == null")
	public Optional<Declaration> findById(Long id) {
		return declarationRepository.findById(id);
	}

	public Optional<Declaration> findByAdrAndNum(String adr, int num) {
		return declarationRepository.findByAdresseAndNumDeclare(adr, num);
	}

	@Cacheable(value = "cache.declarationByAdr", key = "#id", unless = "#result == null")
	public List<Declaration> findByAdresse(String adresse) {
		return declarationRepository.findByAdresse(adresse).get();
	}

	public Page<Declaration> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<Declaration> Declaration = declarationRepository.findById(id);
		List<Declaration> Declarations = Declaration.isPresent() ? Collections.singletonList(Declaration.get())
				: Collections.emptyList();
		return new PageImpl<>(Declarations, pageRequest, Declarations.size());
	}

//	public Declaration findByEmailAndIdNot(String email, Long id) {
//		return declarationRepository.findByEmailAndIdNot(email, id);
//	}
//
//	public Declaration findByDeclarationnameAndIdNot(String Declarationname, Long id) {
//		return DeclarationRepository.findByDeclarationnameAndIdNot(Declarationname, id);
//	}
//
//	public Declaration findByDeclarationname(String Declarationname) {
//		return DeclarationRepository.findByDeclarationname(Declarationname);
//	}

	// region Find eagerly
//	public Declaration findByIdEagerly(Long id) {
//		return declarationRepository.findByIdEagerly(id);
//	}

	@Cacheable(value = "cache.allDeclarationsEagerly")
	public List<Declaration> findAllEagerly() {
		return declarationRepository.findAllEagerly();
	}
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byStatutDeclarantContaining")
//	public Page<Declaration> findByStatutDeclarantContaining(String name, Pageable pageable) {
//		return declarationRepository.findByStatutDeclarantContainingOrderByIdAsc(name, pageable);
//	}
//
//	@Cacheable(value = "cache.byEtatDeclareContaining")
//	public Page<Declaration> findByEtatDeclareContaining(String surname, Pageable pageable) {
//		return declarationRepository.findByEtatDeclareContainingOrderByIdAsc(surname, pageable);
//	}
//
//	@Cacheable(value = "cache.bySurfaceTotalContaining")
//	public Page<Declaration> findBySurfaceTotalContaining(double surfaceTotal, Pageable pageable) {
//		return declarationRepository.findBySurfaceTotalContainingOrderByIdAsc(surfaceTotal, pageable);
//	}

//	@Cacheable(value = "cache.bySurfaceNBContaining")
//	public Page<Declaration> findBySurfaceNBContaining(double surfaceNB, Pageable pageable) {
//		return declarationRepository.findBySurfaceNBContainingOrderByIdAsc(surfaceNB, pageable);
//	}
	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allDeclarationsPageable", "cache.allDeclarations", "cache.DeclarationByEmail",
			"cache.DeclarationById", "cache.allDeclarationsEagerly", "cache.byStatutDeclarantContaining",
			"cache.byEtatDeclareContaining", "cache.bySurfaceTotalContaining", "cache.bySurfaceNBContaining",
			"cache.declarationByAdrAndNum", "cache.declarationByAdr" }, allEntries = true)
	public void save(Declaration Declaration) {
		declarationRepository.save(Declaration);
	}

	@CacheEvict(value = { "cache.allDeclarationsPageable", "cache.allDeclarations", "cache.DeclarationByEmail",
			"cache.DeclarationById", "cache.allDeclarationsEagerly", "cache.byStatutDeclarantContaining",
			"cache.byEtatDeclareContaining", "cache.bySurfaceTotalContaining", "cache.bySurfaceNBContaining",
			"cache.declarationByAdrAndNum", "cache.declarationByAdr" }, allEntries = true)
	public void deleteById(Long id) {
		declarationRepository.deleteById(id);
	}

	public Declaration declareNewArticle(DeclarationDto DeclarationDto) {
		Declaration Declaration = new Declaration();
		Declaration.setStatutDeclarant(DeclarationDto.getStatutDeclarant());
		Declaration.setEtatDeclare(DeclarationDto.getEtatDeclare());
		Declaration.setSurfaceTotal(DeclarationDto.getSurfaceTotal());
		Declaration.setSurfaceNB(DeclarationDto.getSurfaceNB());
		Declaration.setSurfaceB(DeclarationDto.getSurfaceB());
		return Declaration;
	}

	public Declaration getUpdatedDeclaration(Declaration persistedDeclaration,
			DeclarationUpdateDto DeclarationUpdateDto) {
		persistedDeclaration.setStatutDeclarant(DeclarationUpdateDto.getStatutDeclarant());
		persistedDeclaration.setEtatDeclare(DeclarationUpdateDto.getEtatDeclare());
		persistedDeclaration.setSurfaceTotal(DeclarationUpdateDto.getSurfaceTotal());
		persistedDeclaration.setSurfaceNB(DeclarationUpdateDto.getSurfaceNB());
		persistedDeclaration.setSurfaceB(DeclarationUpdateDto.getSurfaceB());
		persistedDeclaration.setControles(getAssignedControlesList(DeclarationUpdateDto));
		return persistedDeclaration;
	}

	public List<Controle> getAssignedControlesList(DeclarationUpdateDto DeclarationUpdateDto) {
		Map<Long, Controle> assignedControleMap = new HashMap<>();
		List<Controle> controles = DeclarationUpdateDto.getControles();
		for (Controle controle : controles) {
			assignedControleMap.put(controle.getId(), controle);
		}

		List<Controle> DeclarationControles = new ArrayList<Controle>();
		List<Controle> allControles = controleService.findByUsername(DeclarationUpdateDto.getUser().getUsername());
		for (Controle controle : allControles) {
			if (assignedControleMap.containsKey(controle.getId())) {
				DeclarationControles.add(controle);
			} else {
				DeclarationControles.add(null);
			}
		}
		return DeclarationControles;
	}
}
