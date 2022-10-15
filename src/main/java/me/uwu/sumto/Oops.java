package me.uwu.sumto;

import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;

public class Oops {

    /*NE PAS FAIRE ATTENTION A CETTE CLASSE, C4EST JUSTE QUAND JE ME FAIL*/
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver(DicoLang.FR, 9, 's');
        solver.filter("saloperie", "ry-y-y-y-");
        solver.filter("souvenirs", "ry--yyr-y");
        solver.filter("sceptique", "r-y-yy---");
        solver.dico.words.forEach(System.out::println);
        System.out.println(solver.dico.words.size());
    }
}
