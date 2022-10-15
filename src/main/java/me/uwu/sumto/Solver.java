package me.uwu.sumto;

import me.uwu.sumto.dico.Dico;
import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Solver {
    public final Dico dico;
    private final int wordSize;

    // 2 constructeurs disponibles, car wordle n'impose pas de lettre de début
    public Solver(DicoLang lang, int wordSize, char firstLetter) throws IOException {
        this.dico = new Dico(lang);
        this.wordSize = wordSize;
        this.dico.keepOnlyWordWithLength(wordSize);
        this.dico.keepOnlyWordStartingWith(firstLetter);
    }

    public Solver(DicoLang lang, int wordSize) throws IOException {
        this.dico = new Dico(lang);
        this.wordSize = wordSize;
        this.dico.keepOnlyWordWithLength(wordSize);
    }


    /**
     * prends un mot aléatoire dans la liste des valides
     */
    public String getRandomMove() {
        // TODO: 15/10/2022 faire un truc plus efficace faudrait faire une fonction getBestMove qui prends un mot avec le plus de lettres différentes
        // TODO: 15/10/2022 regarder les stratégies pour trouver le mot le plus efficace a motus

        //System.out.println(dico.dico.size());
        String best = dico.words.get(new Random().nextInt(dico.words.size()));
        dico.words.remove(best);
        return best;
    }


    /**
     * filtre les mots valides en fonction du mot et de la réponse (c'est-à-dire cases jaunes, crises et rouges)
     *
     * @param word le mot donné
     * @param data la réponse donnée
     *             r = rouge
     *             y = jaune
     *             - = gris
     */

    public void filter(String word, String data) {
        word = word.toUpperCase();
        StringBuilder toWhiteList = new StringBuilder(); // les lettres qui sont dans le mot et qui sont jaunes
        StringBuilder toVerify = new StringBuilder(); // les lettres qui sont dans le mot et qui sont grises
        StringBuilder toKeep = new StringBuilder(); // les lettres qui sont dans le mot et qui sont rouges
        if (word.length() != wordSize || data.length() != wordSize) {
            System.err.println("word size mismatch");
            return;
        }

        for (int i = 1; i < wordSize; i++) {
            if (data.charAt(i) == '-')
                // on ajoute les lettres grises à la liste des lettres à vérifier (s'il y a les meme lettres dans les mots valides).
                toVerify.append(word.charAt(i));
            if (data.charAt(i) == 'y') {
                // signifie que la lettre existe dans le mot, mais pas à la bonne place (va aider pour filtrer les mots contenant la lettre)
                toWhiteList.append(word.charAt(i));

                // on ajoute les lettres jaunes à la liste des lettres à garder (s'il y a les meme lettres dans les mots valides).
                toKeep.append(word.charAt(i));
            }
            if (data.charAt(i) == 'r') {
                // signifie que la lettre existe dans le mot, et à la bonne place
                toKeep.append(word.charAt(i));

                // on filtre les mots contenant la lettre à tel emplacement
                dico.keepOnlyWordContainingLetterAt(word.charAt(i), i);
            }
            //toWhiteList.append(word.charAt(i));
        }

        // on garde seulement les mots qui contiennent les lettres jaunes
        dico.keepOnlyWordContaining(toWhiteList.toString().toCharArray());

        StringBuilder toBan = new StringBuilder();
        for (char character1 : toVerify.toString().toCharArray()) {
            int counter = 0;
            for (char character2 : toKeep.toString().toCharArray()) {
                // compte le nombre de fois où la lettre existe dans le mot et si elle est au moins une fois jaune ou rouge
                if (character1 == character2)
                    counter++;
            }

            // si la lettre est présente qu'une dans le mot, on la bannit
            // sinon la lettre est présente plus d'une fois dans le mot et il y en a une rouge ou jaune, on la garde
            if (counter == 0)
                toBan.append(character1);
        }
        //System.out.println(toBan);

        Set<Character> charSet = new LinkedHashSet<>(); // LinkedHashSet pour ne pas avoir de doublons
        for (char c : toBan.toString().toCharArray()) {
            charSet.add(c);
        }

        toBan = new StringBuilder();
        for (char character : charSet) {
            toBan.append(character);
        }

        //System.out.println(toBan);

        // on supprime tous les mots qui contiennent les lettres qui ne seront jamais utilisées
        dico.keepOnlyWordNotContaining(toBan.toString().toCharArray());
    }

}
