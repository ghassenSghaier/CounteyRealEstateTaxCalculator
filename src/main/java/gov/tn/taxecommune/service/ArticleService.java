package gov.tn.taxecommune.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.ArticlePrestationPK;
import gov.tn.taxecommune.entity.ArticleRue;
import gov.tn.taxecommune.entity.ArticleRuePK;
import gov.tn.taxecommune.entity.ArticleStatutRésidentiel;
import gov.tn.taxecommune.entity.ArticleTypeActivitePK;
import gov.tn.taxecommune.entity.ArticleTypeActivité;
import gov.tn.taxecommune.entity.Categorie;
import gov.tn.taxecommune.entity.CategorieArticle;
import gov.tn.taxecommune.entity.CategorieArticlePK;
import gov.tn.taxecommune.entity.CategorieTarificationprestation;
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.entity.Rue;
import gov.tn.taxecommune.entity.StatutRésidentiel;
import gov.tn.taxecommune.entity.Tarificationprestation;
import gov.tn.taxecommune.entity.Taxation;
import gov.tn.taxecommune.entity.TypeActivité;
import gov.tn.taxecommune.entity.TypeArticle;
import gov.tn.taxecommune.repository.ArticleRepository;
import gov.tn.taxecommune.web.dto.ArticleDto;
import gov.tn.taxecommune.web.dto.ArticleTclDto;
import gov.tn.taxecommune.web.dto.ArticleTclUpdateDto;
import gov.tn.taxecommune.web.dto.ArticleTibAppartementDto;
import gov.tn.taxecommune.web.dto.ArticleTibAppartementUpdateDto;
import gov.tn.taxecommune.web.dto.ArticleTibDto;
import gov.tn.taxecommune.web.dto.ArticleTibUpdateDto;
import gov.tn.taxecommune.web.dto.ArticleTnbDto;
import gov.tn.taxecommune.web.dto.ArticleUpdateDto;

@Service
public class ArticleService {

//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	private UserRepository userRepository;
    private ArticleRepository articleRepository;
    private CategorieArticleService cvsService;
//	private RoleService roleService;
    private RueService rueService;
    private ArticleRueService arueService;
    private TypeActivitéService typeActivitéService;
    private ArticleTypeActivitéService articleTypeActivitéService;
    private StatutRésidentielService statutRésidentielService;
    private ArticleStatutRésidentielService articleStatutRésidentielService;
    private TypeArticleService typeArticleService;
    private PrestationService prestationService;
    private ArticlePrestationService articlePrestationService;
    private TarificationPrestationService tarificationPrestationService;
    private CategorieVocationService categorieVocationService;
    private CategorieTarificationPrestationService ctpService;
    private CategorieVocationService categorieService;
    private CategorieArticleService caService;
    private VocationService vocationService;
    private ArticlePrestationService apService;
    private TaxationService taxService;
    private CacheManager cacheManager;

    public ArticleService(ArticleRepository articleRepository, RueService rueService, ArticleRueService arueService,
            CategorieArticleService cvsService, TypeActivitéService typeActivitéService, ArticleTypeActivitéService articleTypeActivitéService,
            StatutRésidentielService statutRésidentielService,
            ArticleStatutRésidentielService articleStatutRésidentielService, TypeArticleService typeArticleService,
            PrestationService prestationService, ArticlePrestationService articlePrestationService,
            TarificationPrestationService tarificationPrestationService,
            CategorieVocationService categorieVocationService, CategorieTarificationPrestationService ctpService,
            CategorieVocationService categorieService, VocationService vocationService,
            CategorieArticleService caService, ArticlePrestationService apService, TaxationService taxService,
            CacheManager cacheManager) {
        super();
        this.articleRepository = articleRepository;
        this.rueService = rueService;
        this.typeActivitéService = typeActivitéService;
        this.articleTypeActivitéService = articleTypeActivitéService;
        this.statutRésidentielService = statutRésidentielService;
        this.articleStatutRésidentielService = articleStatutRésidentielService;
        this.typeArticleService = typeArticleService;
        this.prestationService = prestationService;
        this.articlePrestationService = articlePrestationService;
        this.tarificationPrestationService = tarificationPrestationService;
        this.categorieVocationService = categorieVocationService;
        this.ctpService = ctpService;
        this.categorieService = categorieService;
        this.vocationService = vocationService;
        this.caService = caService;
        this.apService = apService;
        this.taxService = taxService;
        this.cacheManager = cacheManager;
        this.arueService = arueService;
        this.cvsService = cvsService;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public RueService getRueService() {
        return rueService;
    }

    public void setRueService(RueService rueService) {
        this.rueService = rueService;
    }

    public TypeActivitéService getTypeActivitéService() {
        return typeActivitéService;
    }

    public void setTypeActivitéService(TypeActivitéService typeActivitéService) {
        this.typeActivitéService = typeActivitéService;
    }

    public ArticleTypeActivitéService getArticleTypeActivitéService() {
        return articleTypeActivitéService;
    }

    public void setArticleTypeActivitéService(ArticleTypeActivitéService articleTypeActivitéService) {
        this.articleTypeActivitéService = articleTypeActivitéService;
    }

    public StatutRésidentielService getStatutRésidentielService() {
        return statutRésidentielService;
    }

    public void setStatutRésidentielService(StatutRésidentielService statutRésidentielService) {
        this.statutRésidentielService = statutRésidentielService;
    }

    public ArticleStatutRésidentielService getArticleStatutRésidentielService() {
        return articleStatutRésidentielService;
    }

    public void setArticleStatutRésidentielService(ArticleStatutRésidentielService articleStatutRésidentielService) {
        this.articleStatutRésidentielService = articleStatutRésidentielService;
    }

    public TypeArticleService getTypeArticleService() {
        return typeArticleService;
    }

    public void setTypeArticleService(TypeArticleService typeArticleService) {
        this.typeArticleService = typeArticleService;
    }

    public PrestationService getPrestationService() {
        return prestationService;
    }

    public void setPrestationService(PrestationService prestationService) {
        this.prestationService = prestationService;
    }

    public ArticlePrestationService getArticlePrestationService() {
        return articlePrestationService;
    }

    public void setArticlePrestationService(ArticlePrestationService articlePrestationService) {
        this.articlePrestationService = articlePrestationService;
    }

    public TarificationPrestationService getTarificationPrestationService() {
        return tarificationPrestationService;
    }

    public void setTarificationPrestationService(TarificationPrestationService tarificationPrestationService) {
        this.tarificationPrestationService = tarificationPrestationService;
    }

    public CategorieVocationService getCategorieVocationService() {
        return categorieVocationService;
    }

    public void setCategorieVocationService(CategorieVocationService categorieVocationService) {
        this.categorieVocationService = categorieVocationService;
    }

    public CategorieTarificationPrestationService getCtpService() {
        return ctpService;
    }

    public void setCtpService(CategorieTarificationPrestationService ctpService) {
        this.ctpService = ctpService;
    }

    public CategorieVocationService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(CategorieVocationService categorieService) {
        this.categorieService = categorieService;
    }

    public CategorieArticleService getCaService() {
        return caService;
    }

    public void setCaService(CategorieArticleService caService) {
        this.caService = caService;
    }

    public VocationService getVocationService() {
        return vocationService;
    }

    public void setVocationService(VocationService vocationService) {
        this.vocationService = vocationService;
    }

    public ArticlePrestationService getApService() {
        return apService;
    }

    public void setApService(ArticlePrestationService apService) {
        this.apService = apService;
    }

    public TaxationService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxationService taxService) {
        this.taxService = taxService;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    // region find methods
    // ==============================================================================================
    @Cacheable(value = "cache.allArticles")
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    /*@Cacheable(value = "cache.articlesNotById", key = "#id", unless = "#result == null")
	public Article findByIdNot(Long id) {
		return articleRepository.findByIdNot(id);
	}*/
    @Cacheable(value = "cache.articlesNotByNumeroMuniciapl", key = "#numeroMuniciapl", unless = "#result == null")
    public Article findByNumeroMuniciaplNot(long numeroMuniciapl) {
        return articleRepository.findByNumeroMunicipalNot(numeroMuniciapl);
    }

    @Cacheable(value = "cache.articlesByNumRep", key = "#numRep", unless = "#result == null")
    public List<Article> findByNumRep(String numRep) {
        return articleRepository.findByNumRep(numRep);
    }

    @Cacheable(value = "cache.allArticlesPageable")
    public Page<Article> findAllPageable(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Cacheable(value = "cache.articleByNumeroMunicipal", key = "#numeroMunicipal", unless = "#result == null")
    public Optional<Article> findByNumeroMunicipal(String numeroMunicipal) {
        return articleRepository.findByNumeroMunicipal(numeroMunicipal);
    }

//	@Cacheable(value = "cache.articleById", key = "#id", unless = "#result == null")
//	public Optional<Article> findById(Long id) {
//		return articleRepository.findById(id);
//	}
    public Page<Article> findByNumeroMunicipalPageable(String numeroMunicipal, Pageable pageRequest) {
        Optional<Article> article = articleRepository.findByNumeroMunicipal(numeroMunicipal);
        List<Article> articles = article.isPresent() ? Collections.singletonList(article.get())
                : Collections.emptyList();
        return new PageImpl<>(articles, pageRequest, articles.size());
    }

    // region Find eagerly
    public Article findByNumeroMunicipalEagerly(String numeroMunicipal) {
        return articleRepository.findByNumeroMunicipalEagerly(numeroMunicipal);
    }

    @Cacheable(value = "cache.allArticlesEagerly")
    public List<Article> findAllEagerly() {
        return articleRepository.findAllEagerly();
    }
    // endregion

    // region Find by containing
    @Cacheable(value = "cache.byCodePostalContaining")
    public Page<Article> findByCodePostalContaining(String codePostal, Pageable pageable) {
        return articleRepository.findByCodePostalContainingOrderByNumeroMunicipalAsc(codePostal, pageable);
    }

    @Cacheable(value = "cache.byRepArticleContaining")
    public Page<Article> findByRepArticleContaining(int repArticle, Pageable pageable) {
        return articleRepository.findByRepArticleContainingOrderByNumeroMunicipalAsc(repArticle, pageable);
    }

    @Cacheable(value = "cache.bySurfaceTotalContaining")
    public Page<Article> findBySurfaceTotalContaining(double surfaceTotal, Pageable pageable) {
        return articleRepository.findBySurfaceTotalContainingOrderByNumeroMunicipalAsc(surfaceTotal, pageable);
    }

    @Cacheable(value = "cache.bySurfaceCouvertContaining")
    public Page<Article> findBySurfaceCouvertContaining(double surfaceCouvert, Pageable pageable) {
        return articleRepository.findBySurfaceCouvertContainingOrderByNumeroMunicipalAsc(surfaceCouvert, pageable);
    }

    public Page<Article> findByIdPageable(Long id, Pageable pageRequest) {
        Optional<Article> article = articleRepository.findById(id);
        List<Article> articles = article.isPresent() ? Collections.singletonList(article.get())
                : Collections.emptyList();
        return new PageImpl<>(articles, pageRequest, articles.size());
    }

//	@Cacheable(value = "cache.by¨PrestationContaining")
//	public Page<Article> findByPrestationContaining(String prestation, Pageable pageable) {
//		return articleRepository.findByPrestationContainingOrderByNumeroMunicipalAsc(prestation, pageable);
//	}
    // endregion
    // ==============================================================================================
    // endregion
    @Transactional
    @CacheEvict(value = {"cache.allArticles", "cache.allArticlesPageable", "cache.articleByNumeroMunicipal",
        "cache.articlesByNumRep", "cache.allArticlesEagerly", "cache.byCodePostalContaining",
        "cache.byRepArticleContaining", "cache.bySurfaceTotalContaining", "cache.bySurfaceCouvertContaining",
        "cache.articlesNotByNumMunicip", "cache.byRepArticleContaining",
        "cache.articlesNotByNumeroMuniciapl"}, allEntries = true)
    public void save(Article article) {
        articleRepository.save(article);
    }

    @CacheEvict(value = {"cache.allArticles", "cache.allArticlesPageable", "cache.articleByNumeroMunicipal",
        "cache.articlesByNumRep", "cache.allArticlesEagerly", "cache.byCodePostalContaining",
        "cache.byRepArticleContaining", "cache.bySurfaceTotalContaining", "cache.bySurfaceCouvertContaining",
        "cache.articlesNotByNumMunicip", "cache.byRepArticleContaining",
        "cache.articlesNotByNumeroMuniciapl"}, allEntries = true)
    public void deleteByNumeroMunicipal(String id) {
        articleRepository.deleteByNumeroMunicipal(id);
    }

    // ========================= Creation Des Articles =====================
    public Article createNewArticle(ArticleDto articleTclDto) {

        Article article = new Article();
        String numMunicipal = articleTclDto.getNumeroMunicipal();
        article.setNumeroMunicipal(numMunicipal);
        article.setCodePostal(articleTclDto.getCodePostal());
        article.setSurfaceTotal(articleTclDto.getSurfaceTotal());
        List<String> nomPrestations = articleTclDto.getPres();
        List<Prestation> prestations = new ArrayList<>();
        List<ArticlePrestation> aps = null;
        Rue r = null;
        String codeRue = articleTclDto.getCodeRue();
        for (String nomP : nomPrestations) {
            Prestation p = null;
            try {
                p = prestationService.findByNom(nomP).get();
                r = rueService.findByCodeRue(codeRue).get();
            } catch (NoSuchElementException e) {
                if (p != null) {
                    prestations.add(p);
                    ArticlePrestation ap = new ArticlePrestation(article, p, true, new Date(), null);
                    ArticleRue ar = new ArticleRue(article, r, true, new Date(), null);
                    article.getPrestations().add(ap);
                    article.getArticlerues().add(ar);
                }
            }
        }
        return article;
    }

    public Article createNewArticleTcl(ArticleTclDto articleTclDto) {

        Article article = createNewArticle(articleTclDto);
        double surfaceCouvert = articleTclDto.getSurfaceCouvert();
        article.setActivitePrincipal(articleTclDto.getActivitePrincipal());
        article.setActiviteSecondaire(articleTclDto.getActiviteSecondaire());
        article.setNomCommercial(articleTclDto.getNomCommercial());
        System.out.println(surfaceCouvert);
        switch (articleTclDto.getMarquePublicitaire()) {
            case "oui":
                article.setMarquePublicitaire(true);
            case "non":
                article.setMarquePublicitaire(false);
        }
        switch (articleTclDto.getTravauxPublics()) {
            case "oui":
                article.setTravauxPublics(true);
            case "non":
                article.setTravauxPublics(false);
        }
        ArticleTypeActivité atEncours = null;
        TypeActivité typeActivité = null;
        Categorie[] catTcl = new Categorie[1];
        Tarificationprestation[] tp = new Tarificationprestation[1];
        List<CategorieTarificationprestation> ctps = null;
        Rue rue = null;
        ArticleRue ar = null;
        CategorieTarificationprestation result = null;
        List<String> nomPrestation = articleTclDto.getPres();
        int nbServices = articleTclDto.getPres().size();
        try {
            typeActivité = typeActivitéService.findByNom(articleTclDto.getTypeActivite()).get();
            ArticleTypeActivitePK ataPK = new ArticleTypeActivitePK(article.getNumeroMunicipal(), typeActivité.getCodeType());
            atEncours = new ArticleTypeActivité();
            atEncours.setArticleTypeActivitePK(ataPK);
            atEncours.setEncoursArticletypeactivite(true);
            atEncours.setOuvertureArticletypeactivite(new Date());
            rue = rueService.findByCodeRue(articleTclDto.getCodeRue()).get();
            ArticleRuePK arPk = new ArticleRuePK(rue.getCodeRue(), article.getNumeroMunicipal());
            ar = new ArticleRue();
            ar.setArticleruePK(arPk);
            //ar.setRue(rue);
            //ar.setArticle(article);
            ar.setDateAjout(new Date());
            ar.setEncoursArticle(true);
            ar.setArticle(article);
            ar.setRue(rue);
            arueService.save(ar);
            for (String nomP : nomPrestation) {
                Prestation p = prestationService.findByNom(nomP).get();
                ArticlePrestationPK apPk = new ArticlePrestationPK(article.getNumeroMunicipal(), p.getCodePrestation());
                ArticlePrestation ap = new ArticlePrestation();
                ap.setDateAjout(new Date());
                ap.setEncoursPrestation(true);
                ap.setArticlePrestationPK(apPk);
                ap.setPrestation(p);
                ap.setArticle(article);
                //article.getPrestations().add(ap);
                articlePrestationService.save(ap);
            }
            catTcl[0] = categorieService.findByNomVocationAndIntervalSizeCategorie("VOCATION_TCL", surfaceCouvert);
            tp[0] = tarificationPrestationService.findByNbPrestations(nbServices);
//			ctps = ctpService.findByCategorieTarificationPrestation(catTcl[0].getCodeCategorie(),tp[0].getCodeTarification());
            ctps = ctpService.findAll();
            result = ctps.stream()
                    .filter(x -> x.getaCategorie().getCodeCategorie().equals(catTcl[0].getCodeCategorie()))
                    .filter(x -> x.gettPrestation().getCodeTarification().equals(tp[0].getCodeTarification()))
                    .filter(x -> x.getEncoursctPrestation() == true).findFirst().get();
            //CategorieArticle va = new CategorieArticle(article, catTcl[0], new Date(), false, null);
            CategorieArticle va  = new CategorieArticle();
            CategorieArticlePK caPK = new CategorieArticlePK(article.getNumeroMunicipal(), catTcl[0].getCodeCategorie());
            va.setCategorieArticlePK(caPK);
            va.setOuvertureVocation(new Date());
            va.setArticle(article);
            va.setCategorie(catTcl[0]);
            double montantTCL = result.getTaxeReference() * articleTclDto.getSurfaceCouvert();
            Taxation t = new Taxation(article, new Date(), 0, 0, montantTCL, 0);
            //article.getTaxations().add(t);
            taxService.save(t);
            //article.getCvs().add(va);
            cvsService.save(va);
            //article.getArticleActivite().add(atEncours);
            articleTypeActivitéService.save(atEncours);
            article.setEstDeclare(false);
            article.setEstBloquee(true);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return article;
    }

    public Article createNewArticleTnb(ArticleTnbDto articleTclDto) {

        Article article = createNewArticle(articleTclDto);
        List<ArticleTypeActivité> at = null;
//		ArticleDensité atEncours = null;						
//		List<Prestation> prestations = new ArrayList<>();
//		List<String> nomPrestations = articleTclDto.getPrestations();
//		for (String nomP : nomPrestations) {
//			Prestation p = prestationService.findByNom(nomP);
//			prestations.add(prestationService.findByNom(nomP));
//			ArticlePrestation ap = new ArticlePrestation(article, p, true, new Date(), null);
//			article.setPrestations(ap);
//		}
//		article.setPrestations(articleTclDto.getPrestations());
//        article.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//        user.setRoles(Collections.singletonList(roleService.findByName("ROLE_USER")));

        return article;
    }

    public Article createNewArticleTib(ArticleTibDto articleTclDto) {

        Article article = new Article();
        Article article1 = null;
        List<ArticleStatutRésidentiel> as = null;
        ArticleStatutRésidentiel asEncours = null;
        StatutRésidentiel statutRésidentiel = null;
        try {
            findByNumeroMunicipal(articleTclDto.getNumeroMunicipal()).get();
            article.setNumeroMunicipal(articleTclDto.getNumeroMunicipal());
            article.setRepArticle(articleTclDto.getRepArticle());
            article.setCodePostal(articleTclDto.getCodePostal());
            article.setSurfaceTotal(articleTclDto.getSurfaceTotal());
            article.setSurfaceCouvert(articleTclDto.getSurfaceCouvert());
            try {
                StatutRésidentiel statut = statutRésidentielService.findByNom(articleTclDto.getStatutRésidence()).get();
                ArticleStatutRésidentiel asr = new ArticleStatutRésidentiel(article, statut, new Date(), true, null);
                article.getStatutRésidence().add(asr);
                TypeArticle typeArticle = typeArticleService.findByNom(articleTclDto.getTypeArticle()).get();
//				article.setTypeArticle(typeArticle);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();

        }
        return article;
    }

    public Article createNewArticleTibAppartement(ArticleTibAppartementDto articleTclDto) {

        Article article = new Article();
        try {
            findByNumeroMunicipal(articleTclDto.getNumeroMunicipal()).get();
            article.setNumeroMunicipal(articleTclDto.getNumeroMunicipal());
            article.setRepArticle(articleTclDto.getRepArticle());
            article.setCodePostal(articleTclDto.getCodePostal());
            article.setSurfaceTotal(articleTclDto.getSurfaceTotal());
            article.setSurfaceCouvert(articleTclDto.getSurfaceCouvert());
            List<String> nomPrestations = articleTclDto.getPres();
            List<Prestation> prestations = new ArrayList<>();
            for (String nomP : nomPrestations) {
                Prestation p = prestationService.findByNom(nomP).get();
                prestations.add(prestationService.findByNom(nomP).get());
                ArticlePrestation ap = new ArticlePrestation(article, p, true, new Date(), null);
                article.getPrestations().add(ap);
            }

            try {
                StatutRésidentiel statut = statutRésidentielService.findByNom(articleTclDto.getStatutRésidence()).get();
                ArticleStatutRésidentiel asr = new ArticleStatutRésidentiel(article, statut, new Date(), true, null);
                article.getStatutRésidence().add(asr);
                TypeArticle typeArticle = typeArticleService.findByNom(articleTclDto.getTypeArticle()).get();
//				article.setTypeArticle(typeArticle);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();

        }

        article.setRésidenceImmeuble(articleTclDto.getRésidenceImmeuble());
        article.setNomImmeuble(articleTclDto.getNomImmeuble());
        article.setNumeroEtage(articleTclDto.getNumeroEtage());
        article.setNumeroAppartement(articleTclDto.getNumeroAppartement());
        return article;
    }

    public Article getUpdatedArticleTcl(Article persistedArticle, ArticleTclUpdateDto articleTclUpdateDto) {

        Article article = new Article();
        List<ArticleTypeActivité> at = null;
        ArticleTypeActivité atEncours = null;
        TypeActivité typeActivité = null;
        try {
            persistedArticle.setNumeroMunicipal(articleTclUpdateDto.getNumeroMunicipal());
            persistedArticle.setNumeroMunicipal(articleTclUpdateDto.getNumeroMunicipal());
            persistedArticle.setCodePostal(articleTclUpdateDto.getCodePostal());
            persistedArticle.setSurfaceTotal(articleTclUpdateDto.getSurfaceTotal());
            persistedArticle.setSurfaceCouvert(articleTclUpdateDto.getSurfaceCouvert());
            persistedArticle.setActivitePrincipal(articleTclUpdateDto.getActivitéPrincipal());
            persistedArticle.setActiviteSecondaire(articleTclUpdateDto.getActivitéSecondaire());
            persistedArticle.setNomCommercial(articleTclUpdateDto.getNomCommercial());
            persistedArticle.setMarquePublicitaire(articleTclUpdateDto.isMarquePublicitaire());
            persistedArticle.setTravauxPublics(articleTclUpdateDto.isTravauxPublics());

        } catch (Exception e) {
        }
        try {
            typeActivité = typeActivitéService.findByNom(articleTclUpdateDto.getTypeActivité()).get();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            at = articleTypeActivitéService.findByArticle(articleTclUpdateDto.getNumeroMunicipal());
            for (ArticleTypeActivité a : at) {
                if (a.isEncoursArticletypeactivite() == true) {
                    break;
                }
            }

        } catch (Exception e2) {
            atEncours = new ArticleTypeActivité(article, typeActivité, new Date(), true, null);
            persistedArticle.getArticleActivite().add(atEncours);
        }
        return persistedArticle;
    }

    public Article getUpdatedArticleTib(Article persistedArticle, ArticleTibUpdateDto articleTclUpdateDto) {
        persistedArticle.setNumeroMunicipal(articleTclUpdateDto.getNumeroMunicipal());
        persistedArticle.setRepArticle(articleTclUpdateDto.getRepArticle());
        persistedArticle.setCodePostal(articleTclUpdateDto.getCodePostal());
        persistedArticle.setSurfaceTotal(articleTclUpdateDto.getSurfaceTotal());
        persistedArticle.setSurfaceCouvert(articleTclUpdateDto.getSurfaceCouvert());
        return persistedArticle;
    }

    public Article getUpdatedArticleTibAppartement(Article persistedArticle,
            ArticleTibAppartementUpdateDto articleTclUpdateDto) {
        persistedArticle.setNumeroMunicipal(articleTclUpdateDto.getNumeroMunicipal());
        persistedArticle.setRepArticle(articleTclUpdateDto.getRepArticle());
        persistedArticle.setCodePostal(articleTclUpdateDto.getCodePostal());
        persistedArticle.setSurfaceTotal(articleTclUpdateDto.getSurfaceTotal());
        persistedArticle.setSurfaceCouvert(articleTclUpdateDto.getSurfaceCouvert());
        persistedArticle.setRésidenceImmeuble(articleTclUpdateDto.getRésidenceImmeuble());
        persistedArticle.setNomImmeuble(articleTclUpdateDto.getNomImmeuble());
        persistedArticle.setNumeroEtage(articleTclUpdateDto.getNumeroEtage());
        persistedArticle.setNumeroAppartement(articleTclUpdateDto.getNumeroAppartement());
        return persistedArticle;
    }

    public Article getUpdatedArticle(Article persistedArticle, ArticleUpdateDto articleTclUpdateDto) {

        persistedArticle.setNumeroMunicipal(articleTclUpdateDto.getNumeroMunicipal());
        persistedArticle.setCodePostal(articleTclUpdateDto.getCodePostal());
        persistedArticle.setSurfaceTotal(articleTclUpdateDto.getSurfaceTotal());
        return persistedArticle;
    }
//	public Densité getAssignedDensité(ArticleTnbUpdateDto articleTnbUpdateDto) {
//
//		Map<Long, Densité> assignedDensitéMap = new HashMap<>();
//		Densité densité = articleTnbUpdateDto.getDensité();
//		assignedDensitéMap.put(densité.getCodeDensité(), densité);
//		Densité articleDensité = new Densité();
//		List<Densité> allDensités = densitéService.findAll();
//		for (Densité d : allDensités) {
//			if (assignedDensitéMap.containsKey(densité.getCodeDensité())) {
//				articleDensité = d;
//			}
//		}
//		return articleDensité;
//	}
//	public CategorieTIB getAssignedCategorieTib(ArticleTibUpdateDto articleTibUpdateDto) {
//		Map<Long, CategorieTIB> assignedCategorieTIBMap = new HashMap<>();
//		CategorieTIB categorieTib = articleTibUpdateDto.getCategorieTib();
//		assignedCategorieTIBMap.put(categorieTib.getCodeCategorie(), categorieTib);
//		CategorieTIB articleCategorie = new CategorieTIB();
//		List<CategorieTIB> allCategories = categorieTib.findAll();
//		for (CategorieTIB c : allCategories) {
//			if (assignedCategorieTIBMap.containsKey(categorieTib.getCodeCategorie())) {
//				articleCategorie = c;
//			}
//		}
//		return articleCategorie;
//	}
//
//	public CategorieTCL getAssignedCategorieTcl(ArticleTclUpdateDto articleTclUpdateDto) {
//
//		Map<Long, CategorieTCL> assignedCategorieTCLMap = new HashMap<>();
//		CategorieTCL categorieTcl = articleTclUpdateDto.getCategorieTcl();
//		assignedCategorieTCLMap.put(categorieTcl.getCodeCategorie(), categorieTcl);
//		CategorieTCL articleCategorie = new CategorieTCL();
//		List<CategorieTCL> allCategories = categorieTcl.findAll();
//		for (CategorieTCL c : allCategories) {
//			if (assignedCategorieTCLMap.containsKey(categorieTcl.getCodeCategorie())) {
//				articleCategorie = c;
//			}
//		}
//		return articleCategorie;
//	}	
//	public ContribuableArticle getAssignedContribuableArticleList(ArticleUpdateDto articleUpdateDto) {
//		Map<Long, ContribuableArticle> assignedContribuableArticleMap = new HashMap<>();
//		ContribuableArticle contribuableArticle = articleUpdateDto.getContribuableArticle();
//		assignedContribuableArticleMap.put(contribuableArticle.getId(), contribuableArticle);
//		ContribuableArticle articleContribuableArticle = new ContribuableArticle();
//		List<ContribuableArticle> allContribuableArticles = contribuableArticleService.findAll();
//		for (ContribuableArticle c : allContribuableArticles) {
//			if (assignedContribuableArticleMap.containsKey(contribuableArticle.getId())) {
//				articleContribuableArticle = c;
//			}
//		}
//		return articleContribuableArticle;
//	}
//	

    public Rue getAssignedRue(ArticleUpdateDto articleUpdateDto) {
        Map<String, Rue> assignedRueMap = new HashMap<>();
        Rue rue = rueService.findByCodeRue(articleUpdateDto.getRue().getCodeRue()).get();
        assignedRueMap.put(rue.getCodeRue(), rue);
        Rue articleRue = new Rue();
        List<Rue> allRues = rueService.findAll();
        for (Rue r : allRues) {
            if (assignedRueMap.containsKey(rue.getCodeRue())) {
                articleRue = r;
            }
        }
        return articleRue;
    }

}
