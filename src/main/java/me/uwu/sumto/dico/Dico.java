package me.uwu.sumto.dico;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dico {
    public final List<String> words;

    public Dico(DicoLang dicoLang) throws IOException {
        words = Files.readAllLines(new File("dico/" + dicoLang.file + ".dico").toPath());
    }

    public void keepOnlyWordWithLength(int length) {
        // utile car on peut pas supprimer pendant un foreach
        List<String> toKeep = words.stream().filter(word -> word.length() == length).collect(Collectors.toList());
        if (toKeep.size() != 0) {  // si 0, on garde tout car c'es surement un erreur de frappe
            words.clear();
            words.addAll(toKeep);
        }
    }

    public void keepOnlyWordStartingWith(char letter) {
        String finalLetter = String.valueOf(letter).toUpperCase();

        // utile car on peut pas supprimer pendant un foreach
        List<String> toKeep = words.stream().filter(word -> word.startsWith(finalLetter)).collect(Collectors.toList());
        if (toKeep.size() != 0) {  // si 0, on garde tout car c'es surement un erreur de frappe
            words.clear();
            words.addAll(toKeep);
        }
    }

    public void keepOnlyWordContainingLetterAt(char letter, int index) {
        // utile car on peut pas supprimer pendant un foreach
        List<String> toKeep = words.stream().filter(word -> word.charAt(index) == letter).collect(Collectors.toList());
        if (toKeep.size() != 0) {  // si 0, on garde tout car c'es surement un erreur de frappe
            words.clear();
            words.addAll(toKeep);
        }
    }

    public void keepOnlyWordContaining(char[] letters) {
        List<String> toKeep = new ArrayList<>(); // utile car on peut pas supprimer pendant un foreach

        words.forEach(word -> {
            boolean pass = true;
            for (char letter : letters) {
                if (!word.contains(String.valueOf(letter).toUpperCase())) {
                    pass = false;
                    break;
                }
            }

            if (pass) toKeep.add(word);
        });

        if (toKeep.size() != 0) { // si 0, on garde tout car c'es surement un erreur de frappe
            words.clear();
            words.addAll(toKeep);
        }
    }

    public void keepOnlyWordNotContaining(char[] letters) {
        List<String> toKeep = new ArrayList<>(); // utile car on peut pas supprimer pendant un foreach

        words.forEach(word -> {
            boolean pass = true;
            for (char letter : letters) {
                if (word.contains(String.valueOf(letter).toUpperCase())) {
                    pass = false;
                    break;
                }
            }

            if (pass) toKeep.add(word);
        });


        if (toKeep.size() != 0) { // si 0, on garde tout car c'es surement un erreur de frappe
            words.clear();
            words.addAll(toKeep);
        }
    }
}
