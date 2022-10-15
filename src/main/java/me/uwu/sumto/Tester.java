package me.uwu.sumto;

import me.uwu.sumto.game.Tusmo;

public class Tester {
    public static void main(String[] args) {
        int numberOfTusmoToSkip = 5; // nombre de tableaux à faire


        for (int i = 0; i < numberOfTusmoToSkip; i++) {
            try {
                // retrouve la session
                Tusmo tusmo = new Tusmo("sd27b9be", "4a6b60c169906fe09ff27c81ca");

                // joue à notre place sur le site
                tusmo.play();
            } catch (Exception e) {
                // arrive quand la session est expirée/invalide
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
