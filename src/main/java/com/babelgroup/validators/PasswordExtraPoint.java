package com.babelgroup.validators;

public class PasswordExtraPoint extends AbstractValidator{

    private final int score;

    public PasswordExtraPoint(Validator next, int score) {
        super(next);
        this.score = score;
    }

    @Override
    public void extra(Password password) {
        if (password.getScore() == 9){
            password.incrementScoreBy(score);
        }
    }
}
