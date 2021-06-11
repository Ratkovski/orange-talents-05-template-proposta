package br.com.zupacademy.ratkovski.proposta.config.validation;


import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsBase64Validator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsBase64 {

    String message() default "A digital precisa estar em base64";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
