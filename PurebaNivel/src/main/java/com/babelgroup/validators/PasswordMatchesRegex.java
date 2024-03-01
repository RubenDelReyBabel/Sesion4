package com.babelgroup.validators;

public class PasswordMatchesRegex extends AbstractValidator{

    private final int score;
    private final String regex;
    private final boolean matches;

    public PasswordMatchesRegex(Validator next, int score, String regex, boolean matches) {
        super(next);
        this.score = score;
        this.regex = regex;
        this.matches = matches;
    }

    @Override
    public void extra(Password password) {
        for(Character c: password.getPassword().toCharArray()){
            if (String.valueOf(c).matches(regex) == matches){
                password.incrementScoreBy(score);
                break;
            }
        }

    }
}
