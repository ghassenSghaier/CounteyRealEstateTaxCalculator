//package gov.tn.taxecommune.util;
//
//import gov.tn.taxecommune.entity.Article;
//import gov.tn.taxecommune.entity.CategorieArticle;
//import gov.tn.taxecommune.entity.CategorieTCL;
//import gov.tn.taxecommune.entity.TypeActivité;
//
//public class CategorieTCLUtil {
//
//	private static CategorieTCL categorieTcl;
//	private static Article article;
//
////	public CategorieTCLUtil(CategorieTCL categorieTcl, Article article) {
////		super();
////		this.categorieTcl = categorieTcl;
////		this.article = article;
////	}
//
////	public CategorieTCL getCategorieTcl() {
////		return categorieTcl;
////	}
////
////	public void setCategorieTcl(CategorieTCL categorieTcl) {
////		this.categorieTcl = categorieTcl;
////	}
////
////	public Article getArticle() {
////		return article;
////	}
////
////	public void setArticle(Article article) {
////		this.article = article;
////	}
//
//	public static void putCategorieTCL() {
//
//		if (article.getTypeActivité().equals("industriel")) {
//			categorieTcl.setNomCategorie(CategorieArticle.Categorie1);
//		} else {
//			if (1000 < article.getSurfaceCouvert() && article.getSurfaceCouvert() <= 2000) {
//				categorieTcl.setNomCategorie(CategorieArticle.Categorie2);
//			} else if (2000 < article.getSurfaceCouvert() && article.getSurfaceCouvert() <= 5000) {
//				categorieTcl.setNomCategorie(CategorieArticle.Categorie3);
//			} else {
//				categorieTcl.setNomCategorie(CategorieArticle.Categorie4);
//			}
//		}
//	}
//
//	public static void putTauxTaxationTcl() {
//		int x = article.getPrestations().size();
//		if (BasicUtil.isBetween(x, 0, 2)) {
//			switch (categorieTcl.getNomCategorie()) {
//			case Categorie1:
//				categorieTcl.setPrixReference(0.760);
//			case Categorie2:
//				categorieTcl.setPrixReference(0.520);
//			case Categorie3:
//				categorieTcl.setPrixReference(0.640);
//			case Categorie4:
//				categorieTcl.setPrixReference(0.840);
//			}
//		} else if (BasicUtil.isBetween(x, 2, 4)) {
//			switch (categorieTcl.getNomCategorie()) {
//			case Categorie1:
//				categorieTcl.setPrixReference(0.950);
//			case Categorie2:
//				categorieTcl.setPrixReference(0.650);
//			case Categorie3:
//				categorieTcl.setPrixReference(0.800);
//			case Categorie4:
//				categorieTcl.setPrixReference(1.050);
//			}
//		} else if (BasicUtil.isBetween(x, 2, 4)) {
//			switch (categorieTcl.getNomCategorie()) {
//			case Categorie1:
//				categorieTcl.setPrixReference(1.140);
//			case Categorie2:
//				categorieTcl.setPrixReference(0.780);
//			case Categorie3:
//				categorieTcl.setPrixReference(0.960);
//			case Categorie4:
//				categorieTcl.setPrixReference(1.260);
//			}
//		} else {
//			switch (categorieTcl.getNomCategorie()) {
//			case Categorie1:
//				categorieTcl.setPrixReference(1.330);
//			case Categorie2:
//				categorieTcl.setPrixReference(0.910);
//			case Categorie3:
//				categorieTcl.setPrixReference(1.120);
//			case Categorie4:
//				categorieTcl.setPrixReference(1.470);
//			}
//		}
//	}
//
//	public static double calculTaxationTcl() {
//		return article.getSurfaceCouvert() * categorieTcl.getPrixReference();
//	}
//
//	public static CategorieTCL getCategorieTcl() {
//		return categorieTcl;
//	}
//
//	public static void setCategorieTcl(CategorieTCL categorieTcl) {
//		CategorieTCLUtil.categorieTcl = categorieTcl;
//	}
//
//	public static Article getArticle() {
//		return article;
//	}
//
//	public static void setArticle(Article article) {
//		CategorieTCLUtil.article = article;
//	}
//
//}
