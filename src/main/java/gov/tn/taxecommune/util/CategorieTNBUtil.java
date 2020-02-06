//package gov.tn.taxecommune.util;
//
//import gov.tn.taxecommune.entity.Article;
//import gov.tn.taxecommune.entity.BaseTaxation;
////import gov.tn.taxecommune.entity.CategorieTNB;
//import gov.tn.taxecommune.entity.DensitéUrbaine;
//
//public class CategorieTNBUtil {
//
////	private static Densité categorieTnb;
//	private static Article article;
//	private final static double coefTTNB = 0.003;
//
//	public static void calculTaxationTNB() {
//		if (article.getBaseTaxation() == BaseTaxation.ValeurVenale) {
//			article.setMontantTaxe(article.getValeurVenale() * coefTTNB);
//		} else {
//			article.setMontantTaxe(article.getDensitéUrbaine().getPrixDensité() * article.getSurfaceTotal());
//		}
//	}
//
//	public static void calculPrixRefDensité() {
//		if (article.getDensitéUrbaine().getNomDensitéUrbaine() == DensitéUrbaine.haute) {
//			article.getDensitéUrbaine().setPrixDensité(0.3);
//		} else if (article.getDensitéUrbaine().getNomDensitéUrbaine() == DensitéUrbaine.moyenne) {
//			article.getDensitéUrbaine().setPrixDensité(0.09);
//		} else {
//			article.getDensitéUrbaine().setPrixDensité(0.03);
//		}
//	}
//
////	public static Densité getDensité() {
////		return categorieTnb;
////	}
//
////	public static void setCategorieTnb(CategorieTNB categorieTnb) {
////		CategorieTNBUtil.categorieTnb = categorieTnb;
////	}
//
//	public static Article getArticle() {
//		return article;
//	}
//
//	public static void setArticle(Article article) {
//		CategorieTNBUtil.article = article;
//	}
//
//}
