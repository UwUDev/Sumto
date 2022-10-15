package me.uwu.sumto;

import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("\u001B[35mWord size: \u001B[0m");
        int len = sc.nextInt(); // taille du mot

        System.out.print("\u001B[35mForced first letter? (y/n): \u001B[0m");
        String choice = sc.next();

        Solver solver;

        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) { // si on veut forcer une lettre de début
            System.out.print("\u001B[35mFirst letter: \u001B[0m");
            solver = new Solver(DicoLang.FR, len, sc.next().charAt(0)); // constructor avec lettre de début
        } else solver = new Solver(DicoLang.FR, len); // constructor sans lettre de début

        DecimalFormat df = new DecimalFormat(); // pour formater le pourcentage
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        for (int i = 0; i < 8; i++) {
            int possibilities = solver.dico.words.size();

            float percent = 100f / possibilities;
            String move = solver.getRandomMove();
            if ( possibilities > 1) {

                // coloration deu percentage (plus on est pres de 100%, plus c'est vert)
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
                System.exit(0); // on a trouvé le mot du coup, on termine le programme
            }
        }

        // si on n'a pas trouvé le mot après 8 essais, on affiche les mots restants + le nombre de mots restants
        // 8, car je n'ai pas trouvé de jeux ou il y avait plus de 8 essais
        System.out.println("\u001B[31mNo more moves, exiting...");
        solver.dico.words.forEach(System.out::println);
        System.out.println(solver.dico.words.size());
    }
}
