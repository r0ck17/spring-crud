package by.javaguru.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class LessThanValidator implements ConstraintValidator<IsAdult, LocalDate> {

    @Override
    public boolean isValid(LocalDate data, ConstraintValidatorContext context) {
        if (data == null) {
            return false;
        }
        return Period.between(data, LocalDate.now()).getYears() >= 18;
    }
}
