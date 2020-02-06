package gov.tn.taxecommune.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Keno&Kemo on 21.10.2017..
 */
public class TripleNumericFieldValidator implements ConstraintValidator<ValidTripleNumericField, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String NUMCHAMP_PATTERN = "^([0-9]{3})$";
    @Override
    public void initialize(ValidTripleNumericField constraintAnnotation) {
    }
    @Override
    public boolean isValid(String champNum, ConstraintValidatorContext context){
        return (validateChamp(champNum));
    }

    private boolean validateChamp(String champNum) {
        pattern = Pattern.compile(NUMCHAMP_PATTERN);
        matcher = pattern.matcher(champNum);
        return matcher.matches();
    }
}
