package br.ufscar.dc.dsw.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRentalValidator.class)
public @interface UniqueRental {
	
    String message() default "Já existe uma locação para esse cliente ou locadora neste dia/horário";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}