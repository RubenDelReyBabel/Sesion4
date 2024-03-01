package com.babelgroup.validators;

public class Password {

    private String password;
    private int score;

    public Password(String password, int score) {
        this.password = password;
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScoreBy(int score){
        this.score += score;
    }
}
