package com.babelgroup.validators.strength;

import com.babelgroup.validators.Password;

public class RangeWithin implements Range{

    public int lower;
    public int upper;
    public String message;

    public RangeWithin(int lower, int upper, String message) {
        this.lower = lower;
        this.upper = upper;
        this.message = message;
    }

    @Override
    public void check(Password password) {
        if (password.getScore() >= lower && password.getScore() <= upper){
            System.out.println(message);
        }
    }
}
