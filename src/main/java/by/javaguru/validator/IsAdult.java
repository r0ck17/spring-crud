package by.javaguru.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = LessThanValidator.class)
public @interface IsAdult {

    String message() default "Возраст должен быть от 18 лет";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
