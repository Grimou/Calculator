package com.grimou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {

    public String equation;
    private static final String number = "-?[0-9.]+";


    public Equation(String equation) {
        this.equation = equation;
    }

    public String solve() {
        return String.valueOf(this.solve(this.equation));
    }

    private String solve(String equation) {
        String finalResult = equation;
        Pattern regex;
        Matcher matcher;
        //l'ordre des boucles représente la priorité des opérations


        //on gère toutes les parenthèses
        String parentesisRegex = "\\(.*\\)";
        regex = Pattern.compile(parentesisRegex);
        matcher = regex.matcher(finalResult);
        while (matcher.find()) {
            finalResult = finalResult.substring(0, matcher.start()) + this.solve(finalResult.substring(matcher.start()+1, matcher.end()-1)) + finalResult.substring(matcher.end());
        }

        //on gère les opérateur unaire

        String sqrtRegex = "sqrt("+ number + ")";
        regex = Pattern.compile(sqrtRegex);
        matcher = regex.matcher(finalResult);
        while (matcher.find()) {
            double result = Math.sqrt(Float.parseFloat(matcher.group(1)));
            finalResult = finalResult.substring(0, matcher.start()) + result + finalResult.substring(matcher.end());
            matcher = regex.matcher(finalResult);
        }

        String firstPriorityRegex = "(" + number + ")([\\^])(" + number + ")";
        regex = Pattern.compile(firstPriorityRegex);
        matcher = regex.matcher(finalResult);
        while (matcher.find()) {
            double result = Math.pow(Float.parseFloat(matcher.group(1)), Float.parseFloat(matcher.group(3)));
            finalResult = finalResult.substring(0, matcher.start()) + result + finalResult.substring(matcher.end());
            matcher = regex.matcher(finalResult);
        }

        String secondPriorityRegex = "(" + number + ")([*/])(" + number + ")";
        regex = Pattern.compile(secondPriorityRegex);
        matcher = regex.matcher(finalResult);
        while (matcher.find()) {
            double result;
            if (matcher.group(2).equals("*")) {
                result = Float.parseFloat(matcher.group(1)) * Float.parseFloat(matcher.group(3));
            } else {
                result = Float.parseFloat(matcher.group(1)) / Float.parseFloat(matcher.group(3));
            }
            finalResult = finalResult.substring(0, matcher.start()) + result + finalResult.substring(matcher.end());
            matcher = regex.matcher(finalResult);
        }

        //addition et soustraction
        String thirdPriorityRegex = "(" + number + ")([+\\-])(" + number + ")";
        regex = Pattern.compile(thirdPriorityRegex);
        matcher = regex.matcher(finalResult);
        while (matcher.find()) {
            double result;
            if (matcher.group(2).equals("+")) {
                result = Float.parseFloat(matcher.group(1)) + Float.parseFloat(matcher.group(3));
            } else {
                result = Float.parseFloat(matcher.group(1)) - Float.parseFloat(matcher.group(3));
            }
            finalResult = finalResult.substring(0, matcher.start()) + result + finalResult.substring(matcher.end());
            matcher = regex.matcher(finalResult);
        }

        return finalResult;
    }
}
