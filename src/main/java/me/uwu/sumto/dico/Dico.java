package me.uwu.sumto.dico;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dico {
    public final List<String> dico;

    public Dico(DicoLang dicoLang) throws IOException {
        dico = Files.readAllLines(new File("dico/" + dicoLang.file + ".dico").toPath());
    }

    public void keepOnlyWordWithLength(int length) {
        List<String> toKeep = new ArrayList<>(dico.stream().filter(word -> word.length() == length).collect(Collectors.toList()));
        if (toKeep.size() != 0) {
            dico.clear();
            dico.addAll(toKeep);
        }
    }

    public void keepOnlyWordStartingWith(char letter) {
        String finalLetter = String.valueOf(letter).toUpperCase();
        List<String> toKeep = new ArrayList<>(dico.stream().filter(word -> word.startsWith(finalLetter)).collect(Collectors.toList()));
        if (toKeep.size() != 0) {
            dico.clear();
            dico.addAll(toKeep);
        }
    }

    public void keepOnlyWordContainingLetterAt(char letter, int index) {
        List<String> toKeep = new ArrayList<>(dico.stream().filter(word -> word.charAt(index) == letter).collect(Collectors.toList()));
        if (toKeep.size() != 0) {
            dico.clear();
            dico.addAll(toKeep);
        }
    }

    public void keepOnlyWordContaining(char[] letters) {
        List<String> toKeep = new ArrayList<>();

        dico.forEach(word -> {
            boolean pass = true;
            for (char letter : letters) {
                if (!word.contains(String.valueOf(letter).toUpperCase())) {
                    pass = false;
                    break;
                }
            }

            if (pass) toKeep.add(word);
        });

        if (toKeep.size() != 0) {
            dico.clear();
            dico.addAll(toKeep);
        }
    }

    public void keepOnlyWordNotContaining(char[] letters) {
        List<String> toKeep = new ArrayList<>();

        dico.forEach(word -> {
            boolean pass = true;
            for (char letter : letters) {
                if (word.contains(String.valueOf(letter).toUpperCase())) {
                    pass = false;
                    break;
                }
            }

            if (pass) toKeep.add(word);
        });

        if (toKeep.size() != 0) {
            dico.clear();
            dico.addAll(toKeep);
        }
    }
}
