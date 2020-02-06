package gov.tn.taxecommune.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.repository.TaxationRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class TaxationService {
	private TaxationRepository taxationRepository;

	public TaxationService(TaxationRepository taxationRepository) {
		this.taxationRepository = taxationRepository;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allTaxations")
	public List<Taxation> findAll() {
		return taxationRepository.findAll();
	}

	@Cacheable(value = "cache.allTaxationsPageable")
	public Page<Taxation> findAllPageable(Pageable pageable) {
		return taxationRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.taxationByMonTCl", key = "#monTCL", unless = "#result == null")
	public List<Taxation> findByMonTCL(double monTCL) {
		return taxationRepository.findByMontantTCL(monTCL);
	}

	@Cacheable(value = "cache.taxationByArticle", key = "#codeArticle", unless = "#result == null")
	public List<Taxation> findByArticle(String codeArticle) {
		return taxationRepository.findByArticle(codeArticle);
	}

	@Cacheable(value = "cache.taxationById", key = "#id", unless = "#result == null")
	public Optional<Taxation> findById(Long id) {
		return taxationRepository.findById(id);
	}
	
	@Cacheable(value = "cache.taxationByCode", key = "#code", unless = "#result == null")
	public Taxation findByCode(String code) {
		return taxationRepository.findByCode(code).get();
	}

	public Page<Taxation> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<Taxation> taxation = taxationRepository.findById(id);
		List<Taxation> users = taxation.isPresent() ? Collections.singletonList(taxation.get())
				: Collections.emptyList();
		return new PageImpl<>(users, pageRequest, users.size());
	}

	// region Find by containing
//	@Cacheable(value = "cache.taxationByFNAHContaining")
//	public Page<Taxation> findByFNAHContaining(double name, Pageable pageable) {
//		return taxationRepository.findByMontantFNAHContainingOrderByIdAsc(name, pageable);
//	}
//
//	@Cacheable(value = "cache.taxationByTIBContaining")
//	public Page<Taxation> findByTIBContaining(double surname, Pageable pageable) {
//		return taxationRepository.findByMontantTIBContainingOrderByIdAsc(surname, pageable);
//	}

//	@Cacheable(value = "cache.taxationByDateContaining")
//	public Page<Taxation> findByDateContaining(Date date, Pageable pageable) {
//		return taxationRepository.findByAnneeTaxationContainingOrderByIdAsc(date, pageable);
//	}
//
//	@Cacheable(value = "cache.taxationByTCLContaining")
//	public Page<Taxation> findByTCLContaining(double username, Pageable pageable) {
//		return taxationRepository.findByMontantTCLContainingOrderByIdAsc(username, pageable);
//	}
//
//	@Cacheable(value = "cache.taxationByArticleContaining")
//	public Page<Taxation> findByArticleContaining(String codeArticle, Pageable pageable) {
//		return taxationRepository.findByCodeArticleContainingOrderByIdAsc(codeArticle, pageable);
//	}
//
//	@Cacheable(value = "cache.taxationByTTNBContaining")
//	public Page<Taxation> findByTTNBContaining(double email, Pageable pageable) {
//		return taxationRepository.findByMontantTTNBContainingOrderByIdAsc(email, pageable);
//	}
	// end region

	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allTaxations", "cache.allTaxationsPageable", "cache.taxationByMonTCL",
			"cache.taxationById", "cache.taxationByArticle", "cache.taxationByDateContaining",
			"cache.taxationByTTNBContaining", "cache.taxationByArticleContaining", "cache.taxationByTCLContaining",
			"cache.taxationbyTIBContaining", "cache.taxationByFNAHContaining" }, allEntries = true)
	public void save(Taxation taxation) {
		taxationRepository.save(taxation);
	}

	@CacheEvict(value = { "cache.allTaxations", "cache.allTaxationsPageable", "cache.taxationByMonTCL",
			"cache.taxationById", "cache.taxationByArticle", "cache.taxationByDateContaining",
			"cache.taxationByTTNBContaining", "cache.taxationByArticleContaining", "cache.taxationByTCLContaining",
			"cache.taxationbyTIBContaining", "cache.taxationByFNAHContaining" }, allEntries = true)
	public void delete(Taxation taxation) {
		taxationRepository.delete(taxation);
	}

//    public boolean checkIfRoleNameIsTaken(List<Role> allRoles, Role role, Role persistedRole) {
//        boolean roleNameAlreadyExists = false;
//        for (Role role1 : allRoles) {
//            //Check if the role name is edited and if it is taken
//            if (!role.getNom().equals(persistedRole.getNom())
//                    && role.getNom().equals(role1.getNom())) {
//                roleNameAlreadyExists = true;
//            }
//        }
//        return roleNameAlreadyExists;
//    }

	public boolean checkIfTaxationAlreadyExists(List<Taxation> allTaxations, Taxation taxation,
			Taxation persistedTaxation) {
		boolean taxationAlreadyExists = false;
		for (Taxation t1 : allTaxations) {
			// Check if the role name is edited and if it is taken
			if (!taxation.getAnneeTaxation().equals(persistedTaxation.getAnneeTaxation())
					&& taxation.getAnneeTaxation().equals(t1.getAnneeTaxation())) {
				if (!taxation.getArticle().equals(persistedTaxation.getArticle())
						&& taxation.getArticle().equals(t1.getArticle())) {
					if (!((taxation.getMontantFNAH() == persistedTaxation.getMontantFNAH())
							&& taxation.getMontantFNAH() == t1.getMontantFNAH())
							|| (!(taxation.getMontantTIB() == persistedTaxation.getMontantTIB())
									&& taxation.getMontantTIB() == t1.getMontantTIB())
							|| (!(taxation.getMontantTCL() == persistedTaxation.getMontantTCL())
									&& taxation.getMontantTCL() == t1.getMontantTCL())
							|| (!(taxation.getMontantTTNB() == persistedTaxation.getMontantTTNB())
									&& taxation.getMontantTTNB() == t1.getMontantTTNB())) {

						taxationAlreadyExists = true;
					}
				}
			}

		}
		return taxationAlreadyExists;
	}

}
