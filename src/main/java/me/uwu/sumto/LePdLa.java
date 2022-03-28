package me.uwu.sumto;

import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;

public class LePdLa {
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver(DicoLang.FR, 9, 's');
        solver.filter("saloperie", "ry-y-y-y-");
        solver.filter("souvenirs", "ry--yyr-y");
        solver.filter("sceptique", "r-y-yy---");
        solver.dico.dico.forEach(System.out::println);
        System.out.println(solver.dico.dico.size());
    }
}
