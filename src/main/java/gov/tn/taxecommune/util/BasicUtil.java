package gov.tn.taxecommune.util;

public class BasicUtil {
	
	public static boolean isBetween(int x, int lower, int upper) {
		return lower < x && x <= upper;
	}
}
