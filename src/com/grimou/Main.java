package com.grimou;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Equation equation = new Equation(String.join("", args).replaceAll("\\s", ""));

        System.out.println(equation.solve());
    }
}
