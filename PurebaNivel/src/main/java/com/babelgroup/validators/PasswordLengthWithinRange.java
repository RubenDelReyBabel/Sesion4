package com.babelgroup.validators;

public class PasswordLengthWithinRange extends AbstractValidator{

    private final int upper;
    private final int lower;
    private final int score;

    public PasswordLengthWithinRange(Validator next, int lower, int upper, int score) {
        super(next);
        this.lower = lower;
        this.upper = upper;
        this.score = score;
    }

    @Override
    public void extra(Password password) {
        int length = password.getPassword().length();
        if(length >= lower && length <= upper){
            password.incrementScoreBy(score);
        }
    }
}
