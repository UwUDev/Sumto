package me.uwu.sumto;

import me.uwu.sumto.dico.Dico;
import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*Dico dico = new Dico(DicoLang.FR);
        System.out.println(dico.dico.size());
        dico.keepOnlyWordWithLength(9);
        System.out.println(dico.dico.size());
        dico.keepOnlyWordStartingWith('V');
        System.out.println(dico.dico.size());
        System.out.println(dico.dico.get(500));
        dico.keepOnlyWordContaining(new char[]{'E', 'O'});
        System.out.println(dico.dico.size());
        char e = 'E';
        System.out.println("yfgh".contains(String.valueOf(e)));*/
        Scanner sc = new Scanner(System.in);
        System.out.print("Word size: ");
        int len = sc.nextInt();
        System.out.print("First letter: ");
        Solver solver = new Solver(DicoLang.FR, len, sc.next().charAt(0));
        for (int i = 0; i < 8; i++) {
            String move = solver.getRandomMove();
            System.out.println("Try: " + move);
            System.out.print("Result: ");
            solver.filter(move, sc.next());
        }
        //solver.filter("oursins", "ryy---r");
        //solver.dico.keepOnlyWordNotContaining("XFDNI".toCharArray());
        /*System.out.println(solver.getBestMove());
        solver.dico.keepOnlyWordContaining("ET".toCharArray());
        solver.dico.keepOnlyWordNotContaining("LPH".toCharArray());
        solver.dico.keepOnlyWordContainingLetterAt('E', 1);*/
        solver.dico.dico.forEach(System.out::println);
        System.out.println(solver.dico.dico.size());
    }
}
