package gov.tn.taxecommune.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Keno&Kemo on 21.10.2017..
 */
public class CodePostalValidator implements ConstraintValidator<ValidCodePostal, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String CODEPOSTAL_PATTERN = "[0-9]{4}";
    @Override
    public void initialize(ValidCodePostal constraintAnnotation) {
    }
    @Override
    public boolean isValid(String codePostal, ConstraintValidatorContext context){
        return (validateCodePostal(codePostal));
    }

    private boolean validateCodePostal(String email) {
        pattern = Pattern.compile(CODEPOSTAL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
