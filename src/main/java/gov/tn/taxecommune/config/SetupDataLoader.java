package gov.tn.taxecommune.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.AronSecteur;
import gov.tn.taxecommune.entity.AronSecteurPK;
import gov.tn.taxecommune.entity.Arrondissement;
import gov.tn.taxecommune.entity.Categorie;
import gov.tn.taxecommune.entity.CategorieTarificationprestation;
import gov.tn.taxecommune.entity.CategorieTarificationprestationPK;
import gov.tn.taxecommune.entity.Cellule;
import gov.tn.taxecommune.entity.CelluleRue;
import gov.tn.taxecommune.entity.CelluleRuePK;
import gov.tn.taxecommune.entity.ClasseRue;
import gov.tn.taxecommune.entity.Densite;
import gov.tn.taxecommune.entity.MunicipAron;
import gov.tn.taxecommune.entity.MunicipAronPK;
import gov.tn.taxecommune.entity.Municipalité;
import gov.tn.taxecommune.entity.Parametre;
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.entity.Rue;
import gov.tn.taxecommune.entity.Secteur;
import gov.tn.taxecommune.entity.SecteurCellule;
import gov.tn.taxecommune.entity.StatutRésidentiel;
import gov.tn.taxecommune.entity.Tarificationprestation;
import gov.tn.taxecommune.entity.TypeActivité;
import gov.tn.taxecommune.entity.TypeArticle;
import gov.tn.taxecommune.entity.User;
import gov.tn.taxecommune.entity.Vocation;
import gov.tn.taxecommune.service.AronSecteurService;
import gov.tn.taxecommune.service.ArrondissementService;
import gov.tn.taxecommune.service.CategorieTarificationPrestationService;
import gov.tn.taxecommune.service.CategorieVocationService;
import gov.tn.taxecommune.service.CelluleRueService;
import gov.tn.taxecommune.service.CelluleService;
import gov.tn.taxecommune.service.CoefficientService;
import gov.tn.taxecommune.service.DensitéService;
import gov.tn.taxecommune.service.MunicipAronService;
import gov.tn.taxecommune.service.MunicipService;
import gov.tn.taxecommune.service.PrestationService;
import gov.tn.taxecommune.service.RoleService;
import gov.tn.taxecommune.service.RueService;
import gov.tn.taxecommune.service.SecteurCelluleService;
import gov.tn.taxecommune.service.SecteurService;
import gov.tn.taxecommune.service.StatutRésidentielService;
import gov.tn.taxecommune.service.TarificationPrestationService;
import gov.tn.taxecommune.service.TypeActivitéService;
import gov.tn.taxecommune.service.TypeArticleService;
import gov.tn.taxecommune.service.UserService;
import gov.tn.taxecommune.service.VocationService;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;
	private UserService userService;
	private RoleService roleService;
	private MunicipService municipService;
	private ArrondissementService aronService;
	private SecteurService secteurService;
	private CelluleService celluleService;
	private RueService rueService;
	private CelluleRueService celluleRueService;
	private MunicipAronService municipAronService;
	private AronSecteurService aronSecteurService;
	private SecteurCelluleService secteurCelluleService;
	private CoefficientService coefService;
	private DensitéService densitéService;
	private CategorieVocationService cvService;
	private VocationService vService;
	private TarificationPrestationService tpService;
	private CategorieTarificationPrestationService ctpService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private PrestationService pService;
	private TypeActivitéService tService;
	private TypeArticleService taService;
	private StatutRésidentielService srService;

	public SetupDataLoader(@Lazy UserService userService, RoleService roleService, MunicipService municipService,
			ArrondissementService aronService, SecteurService secteurService, CelluleService celluleService,
			RueService rueService, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
			CelluleRueService celluleRueService, MunicipAronService municipAronService,
			AronSecteurService aronSecteurService, SecteurCelluleService secteurCelluleService,
			CoefficientService coefService, DensitéService densitéService, CategorieVocationService cvService,
			VocationService vService, TarificationPrestationService tpService,
			CategorieTarificationPrestationService ctpService, PrestationService pService, TypeActivitéService tService,
			TypeArticleService taService, StatutRésidentielService srService) {
		this.userService = userService;
		this.roleService = roleService;
		this.municipService = municipService;
		this.aronService = aronService;
		this.secteurService = secteurService;
		this.celluleService = celluleService;
		this.rueService = rueService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.celluleRueService = celluleRueService;
		this.municipAronService = municipAronService;
		this.aronSecteurService = aronSecteurService;
		this.secteurCelluleService = secteurCelluleService;
		this.coefService = coefService;
		this.densitéService = densitéService;
		this.cvService = cvService;
		this.vService = vService;
		this.tpService = tpService;
		this.ctpService = ctpService;
		this.pService = pService;
		this.tService = tService;
		this.taService = taService;
		this.srService = srService;
	}

	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}
	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	// API
	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		// region Creating roles
		Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
		Role roleUser = createRoleIfNotFound("ROLE_USER");
		Role roleAgentMunicipal = createRoleIfNotFound("ROLE_AGENTMUNICIPAL");
		Role roleContribuable = createRoleIfNotFound("ROLE_CONTRIBUABLE");
		List<Role> adminRoles = Collections.singletonList(roleAdmin);
		List<Role> userRoles = Collections.singletonList(roleUser);
		List<Role> agentMunicipalRoles = Collections.singletonList(roleAgentMunicipal);
		List<Role> contribuableRoles = Collections.singletonList(roleContribuable);                
		// end region
                
		// region Creating users
		createUserIfNotFound("admin@gmail.com", "Admin", "Admin", "admin", "admin", adminRoles);

		for (int i = 1; i < 50; i++) {
			createUserIfNotFound("user" + i + "@gmail.com", "User" + i, "User" + i, "user" + i, "user" + i, userRoles);
		}
		for (int i = 1; i < 3; i++) {
			createUserIfNotFound("agentmunicipal" + i + "@gmail.com", "AgentMunicipal" + i, "AgentMunicipal" + i,
					"agentMunicipal" + i, "user" + i, agentMunicipalRoles);
		}
		for (int i = 1; i < 5; i++) {
			createUserIfNotFound("contribuable" + i + "@gmail.com", "Contribuable" + i, "Contribuable" + i,
					"contribuable" + i, "contribuable" + i, contribuableRoles);
		}

		// create arrondissements et municips ..........
		Municipalité municip = createMuniciaplIfNotFound("01", "bablaasal");
		Arrondissement aron = createArrondissementIfNotFound("01", "aron01");
		Secteur secteur = createSecteurIfNotFound("01");
		Cellule cellule = createCelluleIfNotFound("01");
		Rue rue = createRueIfNotFound("0001", "rue de la liberté");
		createAronMunicipIfNotFound(municip, aron, true, new Date(), null);
		createAronSecteurIfNotFound(aron, secteur, true, new Date(), null);
//		createSecteurCelluleIfNotFound(cellule, secteur, true, new Date(), null);	
		createCelluleRueIfNotFound(cellule, rue, true, new Date(), null);
		// endregion

		// creating type article
		TypeArticle ta1 = createTypeArticleIfNotFound("ta01", "maison_arabe");
		TypeArticle ta2 = createTypeArticleIfNotFound("ta02", "maison_adjacant");
		TypeArticle ta3 = createTypeArticleIfNotFound("ta03", "etage_adjacant");
		TypeArticle ta4 = createTypeArticleIfNotFound("ta04", "villa");
		TypeArticle ta5 = createTypeArticleIfNotFound("ta05", "villa_rdc");
		TypeArticle ta6 = createTypeArticleIfNotFound("ta06", "villa_r+");
		TypeArticle ta7 = createTypeArticleIfNotFound("ta07", "Appartement");
		TypeArticle ta8 = createTypeArticleIfNotFound("ta08", "Studio");
		TypeArticle ta9 = createTypeArticleIfNotFound("ta09", "maison_v0");
		TypeArticle ta10 = createTypeArticleIfNotFound("ta10", "houch");
		TypeArticle ta11 = createTypeArticleIfNotFound("ta11", "Fort");
		TypeArticle ta12 = createTypeArticleIfNotFound("ta12", "Autre");
		// end region
		// creating statut résidentiel
		StatutRésidentiel sr1 = createStatutRésidentielIfNotFound("sr01", "Principal");
		StatutRésidentiel sr2 = createStatutRésidentielIfNotFound("sr02", "Secondaire");
		StatutRésidentiel sr3 = createStatutRésidentielIfNotFound("sr03", "Vacant");
		StatutRésidentiel sr4 = createStatutRésidentielIfNotFound("sr04", "Occupé");
		// end region

		// creating type Activité tcl
		TypeActivité type1 = createTypeActivitéIfNotFound("t01", "Industriel");
		TypeActivité type2 = createTypeActivitéIfNotFound("t02", "Commercial");
		TypeActivité type3 = createTypeActivitéIfNotFound("t03", "Hotel");
		TypeActivité type4 = createTypeActivitéIfNotFound("t04", "Professionnel");
		TypeActivité type5 = createTypeActivitéIfNotFound("t05", "Services");
		TypeActivité type6 = createTypeActivitéIfNotFound("t06", "Administratif");
		TypeActivité type7 = createTypeActivitéIfNotFound("t07", "Agriculturel");
		// end region

		// region creating prestations
		Prestation p1 = createPrestationIfNotFound("p01", "Nettoyage");
		Prestation p2 = createPrestationIfNotFound("p02", "Eclairage Public");
		Prestation p3 = createPrestationIfNotFound("p03", "Rues Goudronnés");
		Prestation p4 = createPrestationIfNotFound("p04", "Dégagement Eaux Usés");
		Prestation p5 = createPrestationIfNotFound("p05", "Dégagement Eaux Pluvial");
		Prestation p6 = createPrestationIfNotFound("p06", "Trottoirs Pavetés");
		// end region

		// create coefficients region
		Parametre tib = createCoeffIfNotFound("tib", "TIB", 0.02);
		Parametre ttnb = createCoeffIfNotFound("ttnb", "TTNB", 0.003);
		Parametre fnah = createCoeffIfNotFound("fnah", "FNAH", 0.04);
		List<Parametre> tibs = new ArrayList<>();
		tibs.add(tib);
		tibs.add(fnah);
		List<Parametre> ttnbs = new ArrayList<>();
		tibs.add(ttnb);
		// end region
		// create Densité region
		Densite faible = createDensitéIfNotFound("d1", "faible", 0.300);
		Densite moyen = createDensitéIfNotFound("d2", "moyen", 0.090);
		Densite haute = createDensitéIfNotFound("d3", "haute", 0.030);
		// end region
		// region create categorie vocation

		// create vocation
		Vocation vocation1 = createVocationIfNotFound("v01", "VOCATION_TIB");
		Vocation vocation2 = createVocationIfNotFound("v02", "VOCATION_TNB");
		Vocation vocation3 = createVocationIfNotFound("v03", "VOCATION_TCL");

		// end region

		List<Categorie> categorieTIB = new ArrayList<Categorie>();
		List<Categorie> categorieTNB = new ArrayList<Categorie>();
		List<Categorie> categorieTCL = new ArrayList<Categorie>();

		Categorie categorie1 = createCategorieVocationIfNotFound("cv1", "Categorie1_TIB", null, vocation1, 0, 100);
		Categorie categorie2 = createCategorieVocationIfNotFound("cv2", "Categorie2_TIB", null, vocation1, 101, 200);
		Categorie categorie3 = createCategorieVocationIfNotFound("cv3", "Categorie3_TIB", null, vocation1, 201, 400);
		Categorie categorie4 = createCategorieVocationIfNotFound("cv4", "Categorie4_TIB", null, vocation1, 401, 500000);
		Categorie categorie5 = createCategorieVocationIfNotFound("cv5", "Categorie1_TNB", faible, vocation2, 0, 0);
		Categorie categorie6 = createCategorieVocationIfNotFound("cv6", "Categorie2_TNB", moyen, vocation2, 0, 0);
		Categorie categorie7 = createCategorieVocationIfNotFound("cv7", "Categorie3_TNB", haute, vocation2, 0, 0);
		Categorie categorie8 = createCategorieVocationIfNotFound("cv8", "Categorie1_TCL", null, vocation3, 0, 3000);
		Categorie categorie9 = createCategorieVocationIfNotFound("cv9", "Categorie2_TCL", null, vocation3, 3000, 4000);
		Categorie categorie10 = createCategorieVocationIfNotFound("cv10", "Categorie3_TCL", null, vocation3, 4000,
				5000);
		Categorie categorie11 = createCategorieVocationIfNotFound("cv11", "Categorie4_TCL", null, vocation3, 5000,
				150000);
		// end region

		categorieTIB.add(categorie1);
		categorieTIB.add(categorie2);
		categorieTIB.add(categorie3);
		categorieTIB.add(categorie4);
		categorieTNB.add(categorie5);
		categorieTNB.add(categorie6);
		categorieTNB.add(categorie7);
		categorieTCL.add(categorie8);
		categorieTCL.add(categorie9);
		categorieTCL.add(categorie10);
		categorieTCL.add(categorie11);

		// end region

		// create prestation repository
		Tarificationprestation tp1 = createTarificationPrestationIfNotFound("t01", 0.08, 1, 2);
		Tarificationprestation tp2 = createTarificationPrestationIfNotFound("t02", 0.10, 3, 4);
		Tarificationprestation tp3 = createTarificationPrestationIfNotFound("t03", 0.12, 5, 6);
		Tarificationprestation tp4 = createTarificationPrestationIfNotFound("t04", 0.14, 7, 12);

		// end region

		// create CategorieTarificationPrestation pour TCL

		// categorie1
		CategorieTarificationprestation ctp1 = createCategorieTarificationPrestationIfNotFound("Categorie1_TCL_P1",
				"tcl1p1", categorie8, tp1, true, 0.780, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp2 = createCategorieTarificationPrestationIfNotFound("Categorie1_TCL_p2",
				"tcl1p2", categorie8, tp2, true, 0.950, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp3 = createCategorieTarificationPrestationIfNotFound("Categorie1_TCL_p3",
				"tcl1p3", categorie8, tp3, true, 1.140, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp4 = createCategorieTarificationPrestationIfNotFound("Categorie1_TCL_p4",
				"tcl1p4", categorie8, tp4, true, 1.330, 0, 0, new Date(), null);
		// end categorie1
		// categorie2
		CategorieTarificationprestation ctp5 = createCategorieTarificationPrestationIfNotFound("Categorie2_TCL_P1",
				"tcl2p1", categorie9, tp1, true, 0.520, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp6 = createCategorieTarificationPrestationIfNotFound("Categorie2_TCL_p2",
				"tcl2p2", categorie9, tp2, true, 0.650, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp7 = createCategorieTarificationPrestationIfNotFound("Categorie2_TCL_p3",
				"tcl2p3", categorie9, tp3, true, 0.780, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp8 = createCategorieTarificationPrestationIfNotFound("Categorie2_TCL_p4",
				"tcl2p4", categorie9, tp4, true, 0.910, 0, 0, new Date(), null);
		// end categorie 2
		// categorie3
		CategorieTarificationprestation ctp9 = createCategorieTarificationPrestationIfNotFound("Categorie3_TCL_P1",
				"tcl3p1", categorie10, tp1, true, 0.640, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp10 = createCategorieTarificationPrestationIfNotFound("Categorie3_TCL_p2",
				"tcl3p2", categorie10, tp2, true, 0.80, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp11 = createCategorieTarificationPrestationIfNotFound("Categorie3_TCL_p3",
				"tcl3p3", categorie10, tp3, true, 0.960, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp12 = createCategorieTarificationPrestationIfNotFound("Categorie3_TCL_p4",
				"tcl3p4", categorie10, tp4, true, 1.120, 0, 0, new Date(), null);
		// end categorie3

		// categorie4
		CategorieTarificationprestation ctp13 = createCategorieTarificationPrestationIfNotFound("Categorie4_TCL_P1",
				"tcl4p1", categorie11, tp1, true, 0.840, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp14 = createCategorieTarificationPrestationIfNotFound("Categorie4_TCL_p2",
				"tcl4p2", categorie11, tp2, true, 1.050, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp15 = createCategorieTarificationPrestationIfNotFound("Categorie4_TCL_p3",
				"tcl4p3", categorie11, tp3, true, 1.260, 0, 0, new Date(), null);
		CategorieTarificationprestation ctp16 = createCategorieTarificationPrestationIfNotFound("Categorie4_TCL_p4",
				"tcl4p4", categorie11, tp4, true, 1.470, 0, 0, new Date(), null);
		// end categorie4

		// end region

		// create CategorieTarificationPrestation pour TIB

		// categorie1
		CategorieTarificationprestation ctp17 = createCategorieTarificationPrestationIfNotFound("Categorie1_TIB_P1",
				"tib11p1", categorie1, tp1, true, 0, 100, 150, new Date(), null);
		CategorieTarificationprestation ctp18 = createCategorieTarificationPrestationIfNotFound("Categorie1_TIB_p2",
				"tib1p2", categorie1, tp2, true, 0, 151, 200, new Date(), null);
		CategorieTarificationprestation ctp19 = createCategorieTarificationPrestationIfNotFound("Categorie1_TIB_p3",
				"tib1p3", categorie1, tp3, true, 0, 201, 250, new Date(), null);
		CategorieTarificationprestation ctp20 = createCategorieTarificationPrestationIfNotFound("Categorie1_TIB_p4",
				"tib1p4", categorie1, tp4, true, 0, 201, 250, new Date(), null);
		// end categorie1
		// categorie2
		CategorieTarificationprestation ctp21 = createCategorieTarificationPrestationIfNotFound("Categorie2_TIB_P1",
				"tib2p1", categorie2, tp1, true, 0, 100, 150, new Date(), null);
		CategorieTarificationprestation ctp22 = createCategorieTarificationPrestationIfNotFound("Categorie2_TIB_p2",
				"tib2p2", categorie2, tp2, true, 0, 151, 200, new Date(), null);
		CategorieTarificationprestation ctp23 = createCategorieTarificationPrestationIfNotFound("Categorie2_TIB_p3",
				"tib2p3", categorie2, tp3, true, 0, 201, 250, new Date(), null);
		CategorieTarificationprestation ctp24 = createCategorieTarificationPrestationIfNotFound("Categorie2_TIB_p4",
				"tib2p4", categorie2, tp4, true, 0, 251, 300, new Date(), null);
		// end categorie 2
		// categorie3
		CategorieTarificationprestation ctp25 = createCategorieTarificationPrestationIfNotFound("Categorie3_TIB_P1",
				"tib3p1", categorie3, tp1, true, 0, 100, 150, new Date(), null);
		CategorieTarificationprestation ctp26 = createCategorieTarificationPrestationIfNotFound("Categorie3_TIB_p2",
				"tib3p2", categorie3, tp2, true, 0, 151, 200, new Date(), null);
		CategorieTarificationprestation ctp27 = createCategorieTarificationPrestationIfNotFound("Categorie3_TIB_p3",
				"tib3p3", categorie3, tp3, true, 0, 201, 250, new Date(), null);
		CategorieTarificationprestation ctp28 = createCategorieTarificationPrestationIfNotFound("Categorie3_TIB_p4",
				"tib3p4", categorie3, tp4, true, 0, 251, 300, new Date(), null);
		// end categorie3

		// categorie4
		CategorieTarificationprestation ctp29 = createCategorieTarificationPrestationIfNotFound("Categorie4_TIB_P1",
				"tib4p1", categorie4, tp1, true, 0, 100, 150, new Date(), null);
		CategorieTarificationprestation ctp30 = createCategorieTarificationPrestationIfNotFound("Categorie4_TIB_p2",
				"tib4p2", categorie4, tp2, true, 0, 151, 200, new Date(), null);
		CategorieTarificationprestation ctp31 = createCategorieTarificationPrestationIfNotFound("Categorie4_TIB_p3",
				"tib4p3", categorie4, tp3, true, 0, 201, 250, new Date(), null);
		CategorieTarificationprestation ctp32 = createCategorieTarificationPrestationIfNotFound("Categorie4_TIB_p4",
				"tib4p4", categorie4, tp4, true, 0, 251, 300, new Date(), null);
		// end categorie4

		// end region

		alreadySetup = true;
	}

	@Transactional
	StatutRésidentiel createStatutRésidentielIfNotFound(final String codeStatut, final String nomStatut) {
		StatutRésidentiel statutRésidentiel = null;
		try {
			statutRésidentiel = srService.findByNom(nomStatut).get();
		} catch (NoSuchElementException e) {
			if (statutRésidentiel == null) {
				statutRésidentiel = new StatutRésidentiel(codeStatut, nomStatut);
				srService.save(statutRésidentiel);
			}
		}
		return statutRésidentiel;
	}

	@Transactional
	TypeArticle createTypeArticleIfNotFound(final String codeType, final String nomType) {
		TypeArticle typeArticle = null;
		try {
			typeArticle = taService.findByNom(nomType).get();
		} catch (NoSuchElementException e) {
			if (typeArticle == null) {
				typeArticle = new TypeArticle(codeType, nomType);
				taService.save(typeArticle);
			}
		}
		return typeArticle;
	}

	@Transactional
	TypeActivité createTypeActivitéIfNotFound(final String codeType, final String nomType) {
		TypeActivité typeActivité = null;
		try {
			typeActivité = tService.findByNom(nomType).get();
		} catch (NoSuchElementException e) {
			if (typeActivité == null) {
				typeActivité = new TypeActivité(codeType, nomType);
				tService.save(typeActivité);
			}
		}
		return typeActivité;
	}

	@Transactional
	Prestation createPrestationIfNotFound(final String codePrestation, final String nomPrestation) {
		Prestation prestation = null;
		try {
			prestation = pService.findByNom(nomPrestation).get();
		} catch (NoSuchElementException e) {
			if (prestation == null) {
				prestation = new Prestation(codePrestation, nomPrestation);
				pService.save(prestation);
			}
		}
		return prestation;
	}

	@Transactional
	Role createRoleIfNotFound(final String name) {
		Role role = null;
		try {
			role = roleService.findByNom(name);
		} catch (NoSuchElementException e) {
			if (role == null) {
				role = new Role(name);
				roleService.save(role);
			}
		}
		return role;
	}

	@Transactional
	void createUserIfNotFound(final String email, String name, String surname, String username, String password,
			List<Role> userRoles) {
		User user = null;
//		try {
		user = userService.findByEmail(email);
//		} catch (NoSuchElementException e) {			
		if (user == null) {
			user = new User();
			user.setNom(name);
			user.setPrenom(surname);
			user.setUsername(username);
			user.setPassword(bCryptPasswordEncoder.encode(password));
			user.setEmail(email);
			user.setRoles(userRoles);
			user.setEnabled(true);
			userService.save(user);
		}
//		}
	}

	@Transactional
	void createCelluleRueIfNotFound(final Cellule aron, final Rue rue, boolean enCours, Date dateAjout,
			Date dateCloture) {
                CelluleRuePK crpk = new CelluleRuePK(aron.getCode(),rue.getCodeRue());
		CelluleRue aronSecteur = new CelluleRue(crpk,aron, rue, true, new Date(), null);                
		List<CelluleRue> allAronSecteurs = null;
		try {
			allAronSecteurs = celluleRueService.findByCelluleANDRue(aron, rue).get();
			for (CelluleRue ars : allAronSecteurs) {
//				trouve = true;
				if (aronSecteur.equals(ars)) {
					if (ars.getDateCloture() != null && ars.getEncoursRue() == false) {
						throw new NoSuchElementException();
					}
				}
			}
		} catch (NoSuchElementException e) {
//			allAronSecteurs.add(aronSecteur);
//			aron.setCelluleRues(allAronSecteurs);
//			rue.setCelluleRues(allAronSecteurs);
//			rueService.save(rue);
//			celluleService.save(aron);
			celluleRueService.save(aronSecteur);
		}
	}

	@Transactional
	void createSecteurCelluleIfNotFound(final Cellule cellule, final Secteur secteur, boolean enCours, Date dateAjout,
			Date dateCloture) {
		SecteurCellule celluleSecteur = new SecteurCellule(secteur, cellule, true, new Date(), null);
		try {
			List<SecteurCellule> allCelluleSecteurs = secteurCelluleService.findBySecteurAndCellule(cellule, secteur)
					.get();
//			boolean trouve = false;
			for (SecteurCellule ars : allCelluleSecteurs) {
				if (celluleSecteur.equals(ars)) {
//					trouve = true;
					if (ars.getDateCloture() != null && ars.getEncoursCellule() == false) {
						throw new NoSuchElementException();
					}
				}
			}
//			if (trouve = false) {
//				throw new NoSuchElementException();
//			}
		} catch (NoSuchElementException e) {

			secteurCelluleService.save(celluleSecteur);
		}
	}

	@Transactional
	void createAronSecteurIfNotFound(final Arrondissement aron, final Secteur secteur, boolean enCours, Date dateAjout,
			Date dateCloture) {
            AronSecteurPK aspk= new AronSecteurPK(aron.getCode(),secteur.getCode());
		AronSecteur aronSecteur = new AronSecteur(aspk,aron,secteur, true, new Date(), null);
		try {
			List<AronSecteur> allCelluleSecteurs = aronSecteurService.findByAronAndSecteur(aron, secteur).get();
//			boolean trouve = false;
			for (AronSecteur ars : allCelluleSecteurs) {
				if (aronSecteur.equals(ars)) {
//					trouve = true;
					if (ars.getDateCloture() != null && ars.getEncoursSecteur() == false) {
						throw new NoSuchElementException();
					}
				}
			}
//			if (trouve = false) {
//				throw new NoSuchElementException();
//			}
		} catch (NoSuchElementException e) {

			aronSecteurService.save(aronSecteur);
		}
	}

	@Transactional
	void createAronMunicipIfNotFound(final Municipalité municip, final Arrondissement aron, boolean enCours,
			Date dateAjout, Date dateCloture) {
                MunicipAronPK mapk= new MunicipAronPK(municip.getCodeMunicip(),aron.getCode());
		MunicipAron aronSecteur = new MunicipAron(mapk,municip, aron, true, new Date(), null);
		try {
			List<MunicipAron> allCelluleSecteurs = municipAronService.findByMunicipAndAron(aron, municip).get();
//			boolean trouve = false;
			for (MunicipAron ars : allCelluleSecteurs) {

				if (aronSecteur.equals(ars)) {
//					trouve = true;
					if (ars.getDateCloture() != null && ars.getEncoursAron() == false) {
						throw new NoSuchElementException();
					}
				}
			}
//			if (trouve = false) {
//				throw new NoSuchElementException();
//			}
		} catch (NoSuchElementException e) {

			municipAronService.save(aronSecteur);
		}
	}

	@Transactional
	Rue createRueIfNotFound(final String code, final String name) {
		Rue rue = null;
		try {
			rue = rueService.findByCodeRue(code).get();
		} catch (NoSuchElementException e) {
			if (rue == null) {
				rue = new Rue(code, name, ClasseRue.Passage, null);
				rueService.save(rue);
			}
		}
		return rue;
	}

	@Transactional
	Cellule createCelluleIfNotFound(final String code) {
		Cellule cellule = null;
		try {
			cellule = celluleService.findByCode(code).get();
		} catch (NoSuchElementException e) {
			if (cellule == null) {
				cellule = new Cellule(code);
				celluleService.save(cellule);
			}
		}
		return cellule;
	}

	@Transactional
	Secteur createSecteurIfNotFound(final String code) {
		Secteur secteur = null;
		try {
			secteur = secteurService.findByCode(code).get();
		} catch (NoSuchElementException e) {
			if (secteur == null) {
				secteur = new Secteur(code);
				secteurService.save(secteur);
			}
		}
		return secteur;
	}

	@Transactional
	Arrondissement createArrondissementIfNotFound(final String code, final String name) {
		Arrondissement aron = null;
		try {
			aron = aronService.findByCode(code).get();
		} catch (NoSuchElementException e) {
			if (aron == null) {
				aron = new Arrondissement(code, name);
				aronService.save(aron);
			}
		}
		return aron;
	}

	@Transactional
	Municipalité createMuniciaplIfNotFound(final String code, String nom) {
		Municipalité municip = null;
		try {
			municip = municipService.findByCode(code).get();
		} catch (NoSuchElementException e) {
			if (municip == null) {
				municip = new Municipalité();
				municip.setNom(nom);
				municip.setCodeMunicip(code);
				municipService.save(municip);
			}
		}
		return municip;

	}

	@Transactional
	Parametre createCoeffIfNotFound(String codeCoef, String nomCoef, double d) {
		Parametre coef = null;
		try {
			coef = coefService.findByCode(codeCoef).get();
		} catch (NoSuchElementException e) {
			if (coef == null) {
				coef = new Parametre();
				coef.setNomParam(nomCoef);
				coef.setCodeParam(codeCoef);
				coef.setValeurParam(d);
				coefService.save(coef);
			}
		}
		return coef;

	}

	@Transactional
	Densite createDensitéIfNotFound(String codeDensité, String nomDensitéUrbaine, double prixDensité) {
		Densite densité = null;
		try {
			densité = densitéService.findByNom(nomDensitéUrbaine).get();
		} catch (NoSuchElementException e) {
			if (densité == null) {
				densité = new Densite();
				densité.setNomDensité(nomDensitéUrbaine);
				densité.setCodeDensité(codeDensité);
				densité.setPrixDensité(prixDensité);
				densitéService.save(densité);
			}
		}
		return densité;

	}

	@Transactional
	Categorie createCategorieVocationIfNotFound(String codeCategorie, String nomcategorie, Densite densité,
			Vocation vocation, double maxCouvert, double minCouvert) {
		Categorie cv = null;
		try {
			cv = cvService.findByCodeCategorie(codeCategorie).get();
		} catch (NoSuchElementException e) {
			if (cv == null) {
				cv = new Categorie();
				cv.setCodeCategorie(codeCategorie);
				cv.setNomCategorie(nomcategorie);
				cv.setVocation(vocation);
				vocation.getCategories().add(cv);
				cv.setMaxCouvert(maxCouvert);
				cv.setMinCouvert(minCouvert);
				cvService.save(cv);
				vService.save(vocation);
			}
		}
		return cv;

	}

	@Transactional
	Vocation createVocationIfNotFound(String codeVocation, String nomVocation) {
		Vocation v = null;
		try {
			v = vService.findByCodeVocation(codeVocation).get();
		} catch (NoSuchElementException e) {
			if (v == null) {
				v = new Vocation();
				v.setCodeVocation(codeVocation);
				v.setNomVocation(nomVocation);
				vService.save(v);
			}
		}
		return v;

	}

	@Transactional
	Tarificationprestation createTarificationPrestationIfNotFound(String codeTarification, double d,
			int nbMinPrestation, int nbMaxPrestation) {
		Tarificationprestation t = null;
		try {
			t = tpService.findByCodeTarificationPrestation(codeTarification).get();
		} catch (NoSuchElementException e) {
			if (t == null) {
				t = new Tarificationprestation();
				t.setCodeTarification(codeTarification);
				t.setTauxPrestation(d);
				t.setNbMinPrestation(nbMinPrestation);
				t.setNbMaxPrestation(nbMaxPrestation);
				tpService.save(t);
			}
		}
		return t;

	}

	@Transactional
	CategorieTarificationprestation createCategorieTarificationPrestationIfNotFound(String nomctPrestation,
			String codectPrestation, Categorie aCategorie, Tarificationprestation tPrestation,
			Boolean encoursctPrestation, double taxeReference, double prixMinReference, double prixMaxReference,
			Date dateAjout, Date dateCloture) {
		CategorieTarificationprestation t = null;
		List<CategorieTarificationprestation> t1 = null;
		List<CategorieTarificationprestation> Joint = new ArrayList<>();
		CategorieTarificationprestation ctp = null;
		try {

			t = ctpService.findByCode(codectPrestation).get();
			if ((t.getEncoursctPrestation() == false) && (t.getDateCloture() != null)) {
				throw new NoSuchElementException();
			}
		} catch (NoSuchElementException e) {
			if (t == null) {
				ctp = new CategorieTarificationprestation();
                                ctp.setCategorieTarificationprestationPK(new CategorieTarificationprestationPK(aCategorie.getCodeCategorie(),tPrestation.getCodeTarification()));
				ctp.setNomCTPrestation(nomctPrestation);
				ctp.setCodeCTPrestation(codectPrestation);
				ctp.setaCategorie(aCategorie);
				ctp.settPrestation(tPrestation);
				ctp.setEncoursctPrestation(encoursctPrestation);
				ctp.setTaxeReference(taxeReference);
				ctp.setPrixMinReference(prixMinReference);
				ctp.setPrixMaxReference(prixMaxReference);
				ctp.setDateAjout(dateAjout);
				ctp.setDateCloture(dateCloture);
				ctpService.save(ctp);
			}
		}
		return ctp;

	}
}