package com.db.desafio.naruto01.annotations;

import com.db.desafio.naruto01.annotations.implementacoes.ValidarTipoDeNinjaImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidarTipoDeNinjaImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidarTipoDeNinja {
    String message() default "Tipo inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}