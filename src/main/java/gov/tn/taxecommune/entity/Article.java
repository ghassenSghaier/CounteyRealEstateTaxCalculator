package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "num_municipal", length = 12, unique = true)
    private long numeroMunicipal;
    /**
     * s'il s'agit de plusieurs maison dans le meme emplacement *
     */
    @Column(name = "rep_article", length = 1)
    private int repArticle;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ArticleRue> articlerues;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<CategorieArticle> cvs;

    @Column(name = "code_postal_article", length = 4)
    private String codePostal;

    @Column(name = "immeuble_appartement_article")
    private boolean appartementImmeuble;

    /**
     * nom du résidence *
     */
    @Column(name = "immeuble_résidence_article", length = 10)
    private String résidenceImmeuble;

    /**
     * nom de l'immeuble *
     */
    @Column(name = "nom_immeuble_article", length = 10)
    private String nomImmeuble;

    /**
     * numéro de l'étage *
     */
    @Column(name = "numero_etage_article", length = 2)
    private int numeroEtage;

    /**
     * numéro de l'appartement *
     */
    @Column(name = "numero_appartement_article", length = 2)
    private int numeroAppartement;

    /**
     * ***** </s'il s'agit d'un appartement dans un immeuble> ***********
     */
    /**
     * surface total de l'article *
     */
    @Column(name = "surface_total_article")
    private double surfaceTotal;

    /**
     * surface couvert de l'article *
     */
    @Column(name = "surface_couvert_article")
    private double surfaceCouvert;

    /**
     **
     * ******************
     * <s'il s'agit d'un article à vocation résidentiel> ******************
     */
    /**
     * statut résidentiel de l'article *
     */
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ArticleStatutRésidentiel> articlestatutRésidence;

    /**
     * type de l'article *
     */
    @JsonBackReference
    @JoinColumn(name = "code_type", referencedColumnName = "code_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeArticle typeArticle;

  
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ArticleTypeActivité> articleActivite;

    /**
     * activité principale de l'article *
     */
    @Column(name = "activité_principal_article", length = 20)
    private String activitePrincipal;

    /**
     * activité secondaire de l'article *
     */
    @Column(name = "activité_secondaire_article", length = 20)
    private String activiteSecondaire;

    /**
     * nom commercial de l'article *
     */
    @Column(name = "nom_commercial_article", length = 20)
    private String nomCommercial;

    /**
     * marque publicitaire de l'article (existe ou pas) *
     */
    @Column(name = "marque_publicitaire_article")
    private Boolean marquePublicitaire;

    /**
     * travaux publics de l'article (existe ou pas) *
     */
    @Column(name = "travaux_publics_article")
    private Boolean travauxPublics;

    /**
     **
     * ***************
     * </s'il s'agit d'un article à vocation industriel> ******************
     */
    /**
     * prestations de l'articles *
     */
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ArticlePrestation> prestations;

    /**
     * autre services possibles *
     */
    @Column(name = "autres_service_article", length = 20)
    private String autreService;

    /**
     * taux de prestation suivant le nombre de services *
     */
    @Column(name = "taux_prestation_article")
    protected double tauxPrestation;

    /**
     **
     * ***********************
     * <s'il s'agit d'un Terrain non bati> ******************
     */
    /**
     * si la base de taxation est ou bien valeur venale de l'article ou bien la
     * densité urbaine
     *
     */
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<DensiteArticle> densiteArticle;

    @JsonBackReference
    @JoinColumn(name = "code_base", referencedColumnName = "code_base")
    @ManyToOne(fetch = FetchType.LAZY)
    protected BaseTaxation baseTaxation;

    /**
     * la densité urbaine de la zone dans lequel se trouve l'article *
     */
    /**
     * la valeur vénale de l'article *
     */
    @Column(name = "valeur_venale_article")
    private double valeurVenale;

    /**
     **
     * ***********************
     * </s'il s'agit d'un Terrain non bati> ******************
     */
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ContribuableArticle> contribuableArticle;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER, orphanRemoval = true)
    private Collection<Taxation> taxations;

    private boolean estDeclare;

    private boolean estBloquee;

    public Article() {
        super();
        //cvs = new ArrayList<>();
        //articleActivite = new ArrayList<ArticleTypeActivité>();
        //taxations = new ArrayList<Taxation>();
        //articlerues = new ArrayList<ArticleRue>();
    }

    public long getNumeroMunicipal() {
        return numeroMunicipal;
    }

    public void setNumeroMunicipal(long numeroMunicipal) {
        this.numeroMunicipal = numeroMunicipal;
    }

    public int getRepArticle() {
        return repArticle;
    }

    public void setRepArticle(int repArticle) {
        this.repArticle = repArticle;
    }

    public Collection<ArticleRue> getArticlerues() {
        return articlerues;
    }

    public void setArticlerues(List<ArticleRue> articlerues) {
        this.articlerues = articlerues;
    }

    public Collection<ArticleStatutRésidentiel> getArticlestatutRésidence() {
        return articlestatutRésidence;
    }

    public void setArticlestatutRésidence(Collection<ArticleStatutRésidentiel> articlestatutRésidence) {
        this.articlestatutRésidence = articlestatutRésidence;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public boolean isAppartementImmeuble() {
        return appartementImmeuble;
    }

    public void setAppartementImmeuble(boolean appartementImmeuble) {
        this.appartementImmeuble = appartementImmeuble;
    }

    public String getRésidenceImmeuble() {
        return résidenceImmeuble;
    }

    public void setRésidenceImmeuble(String résidenceImmeuble) {
        this.résidenceImmeuble = résidenceImmeuble;
    }

    public String getNomImmeuble() {
        return nomImmeuble;
    }

    public void setNomImmeuble(String nomImmeuble) {
        this.nomImmeuble = nomImmeuble;
    }

    public int getNumeroEtage() {
        return numeroEtage;
    }

    public void setNumeroEtage(int numeroEtage) {
        this.numeroEtage = numeroEtage;
    }

    public int getNumeroAppartement() {
        return numeroAppartement;
    }

    public void setNumeroAppartement(int numeroAppartement) {
        this.numeroAppartement = numeroAppartement;
    }

    public double getSurfaceTotal() {
        return surfaceTotal;
    }

    public void setSurfaceTotal(double surfaceTotal) {
        this.surfaceTotal = surfaceTotal;
    }

    public double getSurfaceCouvert() {
        return surfaceCouvert;
    }

    public void setSurfaceCouvert(double surfaceCouvert) {
        this.surfaceCouvert = surfaceCouvert;
    }

    public Collection<DensiteArticle> getDensiteArticle() {
        return densiteArticle;
    }

    public void setDensiteArticle(Collection<DensiteArticle> densiteArticle) {
        this.densiteArticle = densiteArticle;
    }

    public Collection<ArticleStatutRésidentiel> getStatutRésidence() {
        return articlestatutRésidence;
    }

    public void setStatutRésidence(List<ArticleStatutRésidentiel> statutRésidence) {
        this.articlestatutRésidence = statutRésidence;
    }

    public TypeArticle getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(TypeArticle typeArticle) {
        this.typeArticle = typeArticle;
    }

    public Collection<ArticleTypeActivité> getArticleActivite() {
        return articleActivite;
    }

    public void setArticleActivite(List<ArticleTypeActivité> articleActivite) {
        this.articleActivite = articleActivite;
    }

    public String getActivitePrincipal() {
        return activitePrincipal;
    }

    public void setActivitePrincipal(String activitePrincipal) {
        this.activitePrincipal = activitePrincipal;
    }

    public String getActiviteSecondaire() {
        return activiteSecondaire;
    }

    public void setActiviteSecondaire(String activiteSecondaire) {
        this.activiteSecondaire = activiteSecondaire;
    }

    public String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public Boolean getMarquePublicitaire() {
        return marquePublicitaire;
    }

    public void setMarquePublicitaire(Boolean marquePublicitaire) {
        this.marquePublicitaire = marquePublicitaire;
    }

    public Boolean getTravauxPublics() {
        return travauxPublics;
    }

    public void setTravauxPublics(Boolean travauxPublics) {
        this.travauxPublics = travauxPublics;
    }

    public Collection<ArticlePrestation> getPrestations() {
        return prestations;
    }

    public void setPrestations(Collection<ArticlePrestation> prestations) {
        this.prestations = prestations;
    }

    public String getAutreService() {
        return autreService;
    }

    public void setAutreService(String autreService) {
        this.autreService = autreService;
    }

    public double getTauxPrestation() {
        return tauxPrestation;
    }

    public void setTauxPrestation(double tauxPrestation) {
        this.tauxPrestation = tauxPrestation;
    }

    public BaseTaxation getBaseTaxation() {
        return baseTaxation;
    }

    public void setBaseTaxation(BaseTaxation baseTaxation) {
        this.baseTaxation = baseTaxation;
    }

    public double getValeurVenale() {
        return valeurVenale;
    }

    public void setValeurVenale(double valeurVenale) {
        this.valeurVenale = valeurVenale;
    }

    public Collection<ContribuableArticle> getContribuableArticle() {
        return contribuableArticle;
    }

    public void setContribuableArticle(List<ContribuableArticle> contribuableArticle) {
        this.contribuableArticle = contribuableArticle;
    }

    public Collection<Taxation> getTaxations() {
        return taxations;
    }

    public void setTaxations(Collection<Taxation> taxations) {
        this.taxations = taxations;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Collection<CategorieArticle> getCvs() {
        return cvs;
    }

    public void setCvs(Collection<CategorieArticle> cvs) {
        this.cvs = cvs;
    }

    public boolean isEstDeclare() {
        return estDeclare;
    }

    public void setEstDeclare(boolean estDeclare) {
        this.estDeclare = estDeclare;
    }

    public boolean isEstBloquee() {
        return estBloquee;
    }

    public void setEstBloquee(boolean estBloquée) {
        this.estBloquee = estBloquée;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(numeroMunicipal, article.numeroMunicipal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroMunicipal);
    }
}
