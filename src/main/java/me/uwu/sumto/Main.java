package me.uwu.sumto;

import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[35mWord size: \u001B[0m");
        int len = sc.nextInt();
        System.out.print("\u001B[35mForced first letter? (y/n): \u001B[0m");
        String choise = sc.next();
        Solver solver;
        if (choise.equalsIgnoreCase("y") || choise.equalsIgnoreCase("yes")) {
            System.out.print("\u001B[35mFirst letter: \u001B[0m");
            solver = new Solver(DicoLang.FR, len, sc.next().charAt(0));
        } else solver = new Solver(DicoLang.FR, len);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        for (int i = 0; i < 80; i++) {
            int possibilities = solver.dico.dico.size();

            float percent = 100f / possibilities;
            String move = solver.getRandomMove();
            if ( possibilities > 1) {
                String color;

                if (possibilities > 50)
                    color = "\u001B[31m";
                else if (possibilities > 10)
                    color = "\u001B[33m";
                else color = "\u001B[32m";

                System.out.println(color + possibilities + "\u001B[36m possibilities");
                System.out.println("\u001B[32mTry: \u001B[33m" + move + color + "  " + df.format(percent) + "%\u001B[0m");
                System.out.println("\u001B[36mType result: \u001B[34m(r = good placment  |  y = valid letter  |  - = unused letter)   \u001B[33mtype \"skip\" to skip this word");
                System.out.print("\u001B[35m> \u001B[0m");
                String filter = sc.next();
                if (!filter.equalsIgnoreCase("skip"))
                    solver.filter(move, filter);
                else System.out.println("\u001B[31mSkipping word " + move + "...");
            } else {
                System.out.println("\u001B[32mWord found: \u001B[33m" + move + "\u001B[0m");
            }
        }
        solver.dico.dico.forEach(System.out::println);
        System.out.println(solver.dico.dico.size());
    }
}
