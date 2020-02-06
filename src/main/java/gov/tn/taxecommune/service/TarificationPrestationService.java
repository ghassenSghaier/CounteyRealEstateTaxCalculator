package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.tn.taxecommune.entity.Tarificationprestation;
import gov.tn.taxecommune.repository.TarificationPrestationRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class TarificationPrestationService {

	private TarificationPrestationRepository TarificationPrestationRepository;
	private CacheManager cacheManager;

	public TarificationPrestationService(TarificationPrestationRepository TarificationPrestationRepository,
			CacheManager cacheManager) {
		this.TarificationPrestationRepository = TarificationPrestationRepository;
		this.cacheManager = cacheManager;

	}

	// region find methods
	// ==============================================================================================

	@Cacheable(value = "cache.TarificationPrestationByNbMinPrestations", key = "#nbPrestations", unless = "#result == null")
	public Tarificationprestation findByNbMinPrestations(int nbPrestations) {
		return TarificationPrestationRepository.findBynbMinPrestation(nbPrestations);
	}

	@Cacheable(value = "cache.allTarificationPrestations")
	public List<Tarificationprestation> findAll() {
		return TarificationPrestationRepository.findAll();
	}

	@Cacheable(value = "cache.allTarificationPrestationsPageable")
	public Page<Tarificationprestation> findAllPageable(Pageable pageable) {
		return TarificationPrestationRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.TarificationPrestationByCodeTarification", key = "#codeTarificationPrestation", unless = "#result == null")
	public Optional<Tarificationprestation> findByCodeTarificationPrestation(String codeTarificationPrestation) {
		return TarificationPrestationRepository.findByCodeTarification(codeTarificationPrestation);
	}

	@Cacheable(value = "cache.TarificationPrestationByNbPrestations", key = "#nbPrestation", unless = "#result == null")
	public Tarificationprestation findByNbPrestations(int nbPrestation) {
		return TarificationPrestationRepository.findByNbPrestations(nbPrestation);
	}

//	@Cacheable(value = "cache.articleById", key = "#id", unless = "#result == null")
//	public Optional<Article> findById(Long numeroMunicipal) {
//		return articleRepository.findByNumeroMunicipal(numeroMunicipal);
//	}

	public Page<Tarificationprestation> findByCodeTarificationPageable(String codeTarification, Pageable pageRequest) {
		Optional<Tarificationprestation> TarificationPrestation = TarificationPrestationRepository
				.findByCodeTarification(codeTarification);
		List<Tarificationprestation> TarificationPrestations = TarificationPrestation.isPresent()
				? Collections.singletonList(TarificationPrestation.get())
				: Collections.emptyList();
		return new PageImpl<>(TarificationPrestations, pageRequest, TarificationPrestations.size());
	}

	// region Find eagerly
//	public Optional<TarificationPrestation> findByCodeTarificationPrestationEagerly(Long codeTarificationPrestation) {
//		return TarificationPrestationRepository.findByCodeTarificationEagerly(codeTarificationPrestation);
//	}
//
//	@Cacheable(value = "cache.allTarificationPrestationsEagerly")
//	public List<TarificationPrestation> findAllEagerly() {
//		return TarificationPrestationRepository.findAllEagerly();
//	}
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byTauxTarificationContaining")
//	public Page<Tarificationprestation> findByTauxTarificationcontaining(double tauxTarification, Pageable pageable) {
//		return TarificationPrestationRepository.findByTauxPrestationContainingOrderByIdAsc(tauxTarification, pageable);
//	}
//
//	@Cacheable(value = "cache.byCodeTarificationContaining")
//	public Page<Tarificationprestation> findByCodeTarificationContaining(String nomTarificationPrestation,
//			Pageable pageable) {
//		return TarificationPrestationRepository.findByCodeTarificationContainingOrderByIdAsc(nomTarificationPrestation,
//				pageable);
//	}
//
//	@Cacheable(value = "cache.byNbPrestationsMinContaining")
//	public Page<Tarificationprestation> findByNbPrestationsContaining(int nbPrestations, Pageable pageable) {
//		return TarificationPrestationRepository.findByNbMinPrestationContainingOrderByIdAsc(nbPrestations, pageable);
//	}

	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.TarificationPrestationByNbMinPrestations", "cache.allTarificationPrestations",
			"cache.allTarificationPrestationsPageable", "cache.TarificationPrestationByCodeTarification",
			"cache.byTauxTarificationContaining", "cache.byCodeTarificationContaining",
			"cache.byNbPrestationsMinContaining", "cache.TarificationPrestationByNbPrestations" }, allEntries = true)
	public void save(Tarificationprestation TarificationPrestation) {
		TarificationPrestationRepository.save(TarificationPrestation);
	}

	@CacheEvict(value = { "cache.TarificationPrestationByNbMinPrestations", "cache.allTarificationPrestations",
			"cache.allTarificationPrestationsPageable", "cache.TarificationPrestationByCodeTarification",
			"cache.byTauxTarificationContaining", "cache.byCodeTarificationContaining",
			"cache.byNbPrestationsMinContaining", "cache.TarificationPrestationByNbPrestations" }, allEntries = true)
	public void deleteById(Long id) {
		TarificationPrestationRepository.deleteById(id);
	}

	public boolean checkIfCodeTarificationIsTaken(List<Tarificationprestation> allTarificationPrestations,
			Tarificationprestation TarificationPrestation, Tarificationprestation persistedTarificationPrestation) {
		boolean NomTarificationPrestationAlreadyExists = false;
		for (Tarificationprestation TarificationPrestation1 : allTarificationPrestations) {
			// Check if the role name is edited and if it is taken
			if (!TarificationPrestation.getCodeTarification()
					.equals(persistedTarificationPrestation.getCodeTarification())
					&& TarificationPrestation.getNbMinPrestation() == TarificationPrestation1.getNbMinPrestation()) {
				NomTarificationPrestationAlreadyExists = true;
			}
		}
		return NomTarificationPrestationAlreadyExists;
	}

}
