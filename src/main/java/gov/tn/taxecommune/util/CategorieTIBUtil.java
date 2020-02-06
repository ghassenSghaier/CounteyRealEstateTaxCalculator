//package gov.tn.taxecommune.util;
//
//import gov.tn.taxecommune.entity.Article;
//import gov.tn.taxecommune.entity.CategorieArticle;
//import gov.tn.taxecommune.entity.CategorieTIB;
//
//public class CategorieTIBUtil {
//
//	private static CategorieTIB categorieTib;
//	private static Article article;
//	private static final double coefTIB = 0.02;
//	private static final double coefFNAH = 0.04;
//
//	public static void putCategorieTib() {
//		int x = (int) article.getSurfaceCouvert();
//
//		if (BasicUtil.isBetween(x, 0, 100)) {
//			categorieTib.setNomCategorie(CategorieArticle.Categorie1);
//			categorieTib.setPrixReference(100);
//		} else if (BasicUtil.isBetween(x, 100, 200)) {
//			categorieTib.setNomCategorie(CategorieArticle.Categorie2);
//			categorieTib.setPrixReference(151);
//		} else if (BasicUtil.isBetween(x, 200, 400)) {
//			categorieTib.setNomCategorie(CategorieArticle.Categorie3);
//			categorieTib.setPrixReference(201);
//		} else {
//			categorieTib.setNomCategorie(CategorieArticle.Categorie4);
//			categorieTib.setPrixReference(251);
//		}
//	}
//
////	public static void calculBaseTaxation() {
////		
////	}
//
//	public static void calculTaxationArticleTib() {
//		double baseTaxation = article.getSurfaceCouvert() * categorieTib.getPrixReference() * coefTIB;
//		article.setMontantTIB(baseTaxation * article.getTauxPrestation());
//		article.setMontantFNAH(baseTaxation * coefFNAH);
//	}
//
//	public static CategorieTIB getCategorieTib() {
//		return categorieTib;
//	}
//
//	public static void setCategorieTib(CategorieTIB categorieTib) {
//		CategorieTIBUtil.categorieTib = categorieTib;
//	}
//
//	public static Article getArticle() {
//		return article;
//	}
//
//	public static void setArticle(Article article) {
//		CategorieTIBUtil.article = article;
//	}
//}
