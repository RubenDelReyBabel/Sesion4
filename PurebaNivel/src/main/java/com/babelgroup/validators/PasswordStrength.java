package com.babelgroup.validators;

import com.babelgroup.validators.strength.Range;

import java.util.List;
import java.util.Map;

public class PasswordStrength extends AbstractValidator{

    private final List<Range> rangeList;
    public PasswordStrength(Validator next, List<Range> rangeList) {
        super(next);
        this.rangeList = rangeList;
    }

    @Override
    public void extra(Password password) {
        for(Range range: rangeList){
            range.check(password);
        }
    }
}