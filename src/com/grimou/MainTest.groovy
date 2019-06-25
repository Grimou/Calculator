package com.grimou

class MainTest extends groovy.util.GroovyTestCase {

    public static final String[] testCases = ["1+1", "1 + 2", "1 + -1", "-1 - -1", "5-4", "5*2", "(2+5)*3", "10/2", "2+2*5+5", "2.8*3-1", "2^8", "2^8*5-1", "sqrt(4)", "1/0"]
    public static final String[] testCasesResults = ["2", "3", "0", "0", "1", "10", "21", "5", "17", "7.4", "256", "1279", "2", "erreur"]

    void testMain() {
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("test case : " + testCases[i]);
            System.out.print("    ");
            Main.main(testCases[i]);
            System.out.println("    " + testCasesResults[i])
        }
    }
}
