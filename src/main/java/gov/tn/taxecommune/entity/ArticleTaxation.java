package gov.tn.taxecommune.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="article_taxation")
public class ArticleTaxation implements Serializable {

    private static final long serialVersionUID = 1L;    

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private Taxation taxation;

	@OneToOne
	private Reclamation reclamation;

	@OneToOne
	private Degrevement degrevement;

	private Date AnnéeTaxation;

	@Column(name = "montant_payment")
	private double montantPayment;

	@Column(name = "montant_payment_fnah")
	private double montantPaymentFNAH;

	public ArticleTaxation(Long id, Taxation taxation, Date annéeTaxation, double montantPayment,
			double montantPaymentFNAH) {
		super();
		this.id = id;
		this.taxation = taxation;
		AnnéeTaxation = annéeTaxation;
		this.montantPayment = montantPayment;
		this.montantPaymentFNAH = montantPaymentFNAH;
	}

	public ArticleTaxation() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Taxation getTaxation() {
		return taxation;
	}

	public void setTaxation(Taxation taxation) {
		this.taxation = taxation;
	}

	public Date getAnnéeTaxation() {
		return AnnéeTaxation;
	}

	public void setAnnéeTaxation(Date annéeTaxation) {
		AnnéeTaxation = annéeTaxation;
	}

	public double getMontantPayment() {
		return montantPayment;
	}

	public void setMontantPayment(double montantPayment) {
		this.montantPayment = montantPayment;
	}

	public double getMontantPaymentFNAH() {
		return montantPaymentFNAH;
	}

	public void setMontantPaymentFNAH(double montantPaymentFNAH) {
		this.montantPaymentFNAH = montantPaymentFNAH;
	}

	public Reclamation getReclamation() {
		return reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}

	public Degrevement getDegrevement() {
		return degrevement;
	}

	public void setDegrevement(Degrevement degrevement) {
		this.degrevement = degrevement;
	}

}
