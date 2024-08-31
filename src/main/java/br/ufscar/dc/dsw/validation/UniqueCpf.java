package br.ufscar.dc.dsw.validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueCpfValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {
 String message() default "CPF is already registered";
 Class<?>[] groups() default { };
 Class<? extends Payload>[] payload() default { };
}