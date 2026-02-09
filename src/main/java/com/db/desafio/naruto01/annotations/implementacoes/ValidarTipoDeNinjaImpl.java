package com.db.desafio.naruto01.annotations.implementacoes;

import com.db.desafio.naruto01.annotations.ValidarTipoDeNinja;
import com.db.desafio.naruto01.enuns.TipoDeNinja;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidarTipoDeNinjaImpl implements ConstraintValidator<ValidarTipoDeNinja, String> {


    @Override
    public boolean isValid(String sTipo, ConstraintValidatorContext constraintValidatorContext) {
        return TipoDeNinja.fromString(sTipo) != null;
    }
}
