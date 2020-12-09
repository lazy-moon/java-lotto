package com.nextstep.lotto.calculator;

import java.util.Scanner;

public class CalculatorManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new CalculatorManager().run();
    }

    public void run() {
        String source = input();
        int result = StringAddCalculator.splitAndSum(source);
        output(result);
    }

    private String input() {
        System.out.print("Input : ");
        String source = scanner.nextLine();
        return source.replace("\\n", "\n");
    }

    private void output(int result) {
        System.out.println("Output : " + result);
    }
}
