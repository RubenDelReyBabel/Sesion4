package com.babelgroup.validators;

public abstract class AbstractValidator implements Validator {

    private Validator next;

    public AbstractValidator(Validator next){
        this.next = next;
    }
    @Override
    public void execute(Password password){
        extra(password);
        if(next != null){
            next.execute(password);
        }
    }

    @Override
    public abstract void extra(Password password);
}
