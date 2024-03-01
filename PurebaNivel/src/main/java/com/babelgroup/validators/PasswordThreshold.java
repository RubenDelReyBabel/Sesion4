package com.babelgroup.validators;

import com.babelgroup.validators.strength.Range;

import java.util.List;

public class PasswordThreshold extends AbstractValidator{

    private final int threshold;
    public PasswordThreshold(Validator next, int threshold) {
        super(next);
        this.threshold = threshold;
    }

    @Override
    public void extra(Password password) {
        if (password.getScore() >= threshold){
            System.out.println("La contraseña supera el umbral mínimo de fuerza");
        }
        else{
            System.out.println("La contraseña no supera el umbral mínimo de fuerza");
        }
    }
}