package gov.tn.taxecommune.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("cache.allUsersPageable", "cache.allUsers", "cache.allUsersPageable",
				"cache.allArticles", "cache.allArticlesPageable", "cache.articleByNumeroMunicipal",
				"cache.articlesByNumRep", "cache.articleById", "cache.allArticlesEagerly",
				"cache.bynumeroMunicipalContaining", "cache.byCodePostalContaining", "cache.allRues",
				"cache.allRuesPageable", "cache.rueByCodeRue", "cache.allRuesEagerly", "cache.byClasseRueContaining",
				"cache.byNomRueContaining", "cache.byAutreClasseContaining", "cache.allUsersEagerly",
				"cache.userByEmail", "cache.userById", "cache.bySurfaceTotalContaining",
				"cache.bySurfaceCouvertContaining", "cache.allRoles", "cache.roleByNom", "cache.roleById",
				"cache.byNameContaining", "cache.bySurnameContaining", "cache.byRepArticleContaining",
				"cache.articlesNotByNumeroMuniciapl", "cache.articlesNotByNumMunicip", "cache.byUsernameContaining",
				"cache.byEmailContaining", "cache.allTypeActivités", "cache.typeActivitéByNom",
				"cache.typeActivitéById", "cache.allMunicips", "cache.municipByNom", "cache.municipById",
				"cache.municipByCode", "cache.allarons", "cache.aronByNom", "cache.aronById", "cache.aronByCode",
				"cache.allCellules", "cache.CelluleByCode", "cache.CelluleById", "cache.CelluleByCodeRue",
				"cache.allCelluleRuesByCelluleANDRue", "cache.allSecteurs", "cache.SecteurByCode", "cache.SecteurById",
				"cache.allPrestations", "cache.prestationByNom", "cache.presentationById", "cache.allMunicipArons",
				"cache.municipAronByCodeMunicip", "cache.municipAronByCodeAron", "cache.municipAronById",
				"cache.allMunicipAronsByMunicipAndAron", "cache.allAronSecteurs",
				"cache.allAronSecteurByAronAndSecteur", "cache.aronSecteurByCodeSecteur", "cache.aronSecteurByCodeAron",
				"cache.aronSecteurById", "cache.allSecteurCellules", "cache.SecteurCelluleByCodeSecteur",
				"cache.SecteurCelluleByCodeCellule", "cache.SecteurCelluleById",
				"cache.allSecteurCellulesBySecteurAndCellule", "cache.allCelluleRues", "cache.CelluleRueByCodeRue",
				"cache.CelluleRueByCodeCellule", "cache.CelluleRueById", "allCelluleRuesByCelluleANDRue",
				"cache.allDensites", "cache.densitéByNom", "cache.densitéById",
				"cache.TarificationPrestationByNbMinPrestations", "cache.allTarificationPrestations",
				"cache.TarificationPrestationByNbPrestations", "cache.allTarificationPrestationsPageable",
				"cache.TarificationPrestationByCodeTarification", "cache.byTauxTarificationContaining",
				"cache.byCodeTarificationContaining", "cache.byNbPrestationsMinContaining", "cache.allCoefs",
				"cache.CoefByCode", "cache.CoefById", "cache.allCategoriesPageable", "cache.allCategories",
				"cache.CategorieByCodeCategorie", "cache.categoriesByNomVocation",
				"cache.categorieByNomVocationIntervalSizeCategorie", "cache.allTaxations", "cache.allTaxationsPageable",
				"cache.taxationByMonTCL", "cache.taxationById", "cache.taxationByCode", "cache.taxationByArticle",
				"cache.taxationByDateContaining", "cache.taxationByTTNBContaining", "cache.taxationByArticleContaining",
				"cache.taxationByTCLContaining", "cache.taxationbyTIBContaining", "cache.taxationByFNAHContaining",
				"cache.categorieByNomCatagorieIntervalSizeCategorie", "cache.categorieById",
				"cache.allCategoriesEagerly", "cache.byCodeCategorieContaining", "cache.byNomCategorieContaining",
				"cache.categorieByIntervalSizeCategorie", "cache.categorieByCodeCategorie",
				"cache.allArticleTypeActivités", "cache.articleTypeActivitéByCodeArticle",
				"cache.articleTypeActivitéByCodeType", "articleTypeActivitéById", "cache.allVocationsPageable",
				"cache.allVocations", "cache.VocationByCodeVocation", "cache.VocationById", "cache.allVocationsEagerly",
				"cache.byNomVocationContaining", "cache.byCodeVocationContaining", "cache.VocationByNomVocation",
				"cache.allCtps", "cache.allCtpsPageable", "cache.ctpByCode", "cache.ctpById", "cache.byNomContaining",
				"cache.byCodeContaining", "cache.ctpByCodeCategorie", "cache.ctpByCodeTarification",
				"cache.ctpByTprestations", "cache.ctpByCategorie", "cache.allTypeArticles", "cache.TypeArticleByNom",
				"cache.TypeArticleById", "cache.allStatus", "cache.statutByNom", "cache.statutById",
				"cache.allArticlesCategories", "cache.allArticlesCategoriesPageable", "cache.categorieArticleById",
				"cache.allArticlePrestations", "cache.articlePrestationByCodeArticle",
				"cache.articlePrestationByCodePrestation", "cache.articlePrestationById", "cache.allReclamations",
				"cache.allReclamationsPageable", "cache.allReclamations", "cache.reclamationByMotif",
				"cache.reclamationByCode", "cache.reclamationById", "cache.allReclamationsEagerly",
				"cache.byCodeReclamationContaining", "cache.byMotifReclamationContaining",
				"cache.allReclamationsUserPageable", "cache.allatReclamationsPageable");
	}
}
