package me.uwu.sumto;

import me.uwu.sumto.game.Tusmo;

public class Tester {
    public static void main(String[] args) {
        int numberOfTusmoToSkip = 5;


        for (int i = 0; i < numberOfTusmoToSkip; i++) {
            try {
                Tusmo tusmo = new Tusmo("sd27b9be", "4a6b60c169906fe09ff27c81ca");
                tusmo.play();
            } catch (Exception exception) {
                exception.printStackTrace();
                System.exit(0);
            }
        }
    }
}
