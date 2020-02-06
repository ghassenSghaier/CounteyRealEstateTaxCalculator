package gov.tn.taxecommune.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Keno&Kemo on 21.10.2017..
 */
public class CodeRueValidator implements ConstraintValidator<ValidCodeRue, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String CODERUE_PATTERN = "[0-9]{4}";

	@Override
	public void initialize(ValidCodeRue constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return (validateCodeRue(email));
	}

	private boolean validateCodeRue(String email) {
		pattern = Pattern.compile(CODERUE_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
