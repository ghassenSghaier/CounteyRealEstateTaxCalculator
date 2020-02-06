package gov.tn.taxecommune.customAnnotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Keno&Kemo on 21.10.2017..
 */
public class NumeroMunicipalValidator implements ConstraintValidator<ValidNumeroMunicipal, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String NUMEROMUNICIPAL_PATTERN = "[0-9]{12}";

	@Override
	public void initialize(ValidNumeroMunicipal constraintAnnotation) {
	}

	@Override
	public boolean isValid(String numeroMunicipal, ConstraintValidatorContext context) {
		return (validateNumeroMunicipal(numeroMunicipal));
	}

	private boolean validateNumeroMunicipal(String numeroMunicipal) {
		pattern = Pattern.compile(NUMEROMUNICIPAL_PATTERN);
		matcher = pattern.matcher(numeroMunicipal);
		return matcher.matches();
	}
}
