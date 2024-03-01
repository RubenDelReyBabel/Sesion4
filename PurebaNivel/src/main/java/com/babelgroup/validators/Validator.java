package com.babelgroup.validators;

public interface Validator {

    void execute(Password password);
    void extra(Password password);
}
