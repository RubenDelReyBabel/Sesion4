package com.babelgroup;

import com.babelgroup.validators.*;
import com.babelgroup.validators.strength.Range;
import com.babelgroup.validators.strength.RangeWithin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int threshold = 8;

        Validator v11 = new PasswordThreshold(null, threshold);

        List<Range> strenghtList = new ArrayList<>();
        strenghtList.add(new RangeWithin(1, 2, "Muy débil"));
        strenghtList.add(new RangeWithin(3, 5, "Débil"));
        strenghtList.add(new RangeWithin(6, 7, "Moderada"));
        strenghtList.add(new RangeWithin(8, 9, "Fuerte"));
        strenghtList.add(new RangeWithin(10, 10, "Muy fuerte"));
        Validator v10 = new PasswordStrength(v11, strenghtList);

        Validator v9 = new PasswordExtraPoint(v10, 1);

        Validator v8 = new PasswordMatchesRegex(v9, 2, "[A-Za-z0-9 ]", false);
        Validator v7 = new PasswordMatchesRegex(v8, 1, "[0-9]", true);
        Validator v6 = new PasswordMatchesRegex(v7, 1, "[a-z]", true);
        Validator v5 = new PasswordMatchesRegex(v6, 1, "[A-Z]", true);

        Validator v4 = new PasswordLengthMoreThan(v5, 12, 3);
        Validator v3 = new PasswordLengthWithinRange(v4, 9, 12, 2);
        Validator v2 = new PasswordLengthWithinRange(v3, 7, 8, 1);
        Validator v1 = new PasswordLengthWithinRange(v2, 0, 6, 0);

        //runTests(v1);
        runApp(v1);
    }

    public static void runApp(Validator validator){
        Password password = new Password(getPassword(), 0);
        validator.execute(password);
    }

    private static String getPassword(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String password = null;
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