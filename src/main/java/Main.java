package com.babelgroup;

import com.babelgroup.validators.*;
import com.babelgroup.validators.strength.Range;
import com.babelgroup.validators.strength.RangeWithin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int threshold = 8;

        Validator passwordAboveThresholdValidator = new PasswordThreshold(null, threshold);

        List<Range> strenghtList = new ArrayList<>();
        strenghtList.add(new RangeWithin(1, 2, "Muy débil"));
        strenghtList.add(new RangeWithin(3, 5, "Débil"));
        strenghtList.add(new RangeWithin(6, 7, "Moderada"));
        strenghtList.add(new RangeWithin(8, 9, "Fuerte"));
        strenghtList.add(new RangeWithin(10, 10, "Muy fuerte"));
        Validator printStrenghtValidator = new PasswordStrength(passwordAboveThresholdValidator, strenghtList);

        Validator extraPointValidator = new PasswordExtraPoint(printStrenghtValidator, 1);

        Validator passwordSymbolValidator = new PasswordMatchesRegex(extraPointValidator, 2, "[A-Za-z0-9 ]", false);
        Validator passwordNumberValidator = new PasswordMatchesRegex(passwordSymbolValidator, 1, "[0-9]", true);
        Validator passwordLowercaseValidator = new PasswordMatchesRegex(passwordNumberValidator, 1, "[a-z]", true);
        Validator passwordUppercaseValidator = new PasswordMatchesRegex(passwordLowercaseValidator, 1, "[A-Z]", true);

        Validator passwordLengthValidator4 = new PasswordLengthMoreThan(passwordUppercaseValidator, 12, 3);
        Validator passwordLengthValidator3 = new PasswordLengthWithinRange(passwordLengthValidator4, 9, 12, 2);
        Validator passwordLengthValidator2 = new PasswordLengthWithinRange(passwordLengthValidator3, 7, 8, 1);
        Validator passwordLengthValidator1 = new PasswordLengthWithinRange(passwordLengthValidator2, 0, 6, 0);

        //runTests(v1);
        runApp(passwordLengthValidator1);
    }

    public static void runApp(Validator validator){
        Password password = new Password(getPassword(), 0);
        validator.execute(password);
    }

    private static String getPassword(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runTests(Validator validator){
        test(validator, "", 0);
        test(validator, "aaaaaa", 1);

        test(validator, "aaaaaaa", 2);
        test(validator, "aaaaaaaa", 2);

        test(validator, "aaaaaaaaa", 3);
        test(validator, "aaaaaaaaaaaa", 3);

        test(validator, "aaaaaaaaaaaaa", 4);

        test(validator, "a", 1);
        test(validator, "A", 1);
        test(validator, "aA", 2);

        test(validator, "1", 1);
        test(validator, "€", 2);
    }

    private static void test(Validator validator, String password, int expectedScore){
        Password p = new Password(password, 0);
        validator.execute(p);
        if(p.getScore() != expectedScore){
            throw new RuntimeException(" esperado: " + expectedScore + " - real: " + p.getScore() + password);
        }
    }
}