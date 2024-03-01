package com.babelgroup.validators;

public class PasswordLengthMoreThan extends AbstractValidator{

    private final int lower;
    private final int score;

    public PasswordLengthMoreThan(Validator next, int lower, int score) {
        super(next);
        this.lower = lower;
        this.score = score;
    }

    @Override
    public void extra(Password password) {
        int length = password.getPassword().length();
        if(length > lower){
            password.incrementScoreBy(score);
        }
    }
}
