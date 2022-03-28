package me.uwu.sumto;

import me.uwu.sumto.dico.Dico;
import me.uwu.sumto.dico.DicoLang;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Solver {
    private final DicoLang lang;
    public final Dico dico;
    private final int wordSize;
    private final char firstLetter;

    public Solver(DicoLang lang, int wordSize, char firstLetter) throws IOException {
        this.lang = lang;
        this.dico = new Dico(lang);
        this.wordSize = wordSize;
        this.dico.keepOnlyWordWithLength(wordSize);
        this.firstLetter = firstLetter;
        this.dico.keepOnlyWordStartingWith(firstLetter);
    }

    public String getRandomMove() {
        System.out.println(dico.dico.size());
        String best = dico.dico.get(new Random().nextInt(dico.dico.size()));
        dico.dico.remove(best);
        return best;
    }

    public void filter(String word, String data) {
        word = word.toUpperCase();
        StringBuilder toWhiteList = new StringBuilder();
        StringBuilder toVerify = new StringBuilder();
        StringBuilder toKeep = new StringBuilder();
        if (word.length() != wordSize || data.length() != wordSize) {
            System.err.println("word size mismatch");
            return;
        }

        for (int i = 1; i < wordSize; i++) {
            if (data.charAt(i) == '-')
                if (data.charAt(i) != firstLetter)
                    toVerify.append(word.charAt(i));
            if (data.charAt(i) == 'y') {
                toWhiteList.append(word.charAt(i));
                toKeep.append(word.charAt(i));
            }
            if (data.charAt(i) == 'r') {
                toKeep.append(word.charAt(i));
                dico.keepOnlyWordContainingLetterAt(word.charAt(i), i);
            }
                //toWhiteList.append(word.charAt(i));
        }
        dico.keepOnlyWordContaining(toWhiteList.toString().toCharArray());
        StringBuilder toBan = new StringBuilder();
        for (char character1 : toVerify.toString().toCharArray()) {
            int counter = 0;
            for (char character2 : toKeep.toString().toCharArray()) {
                if (character1 == character2)
                    counter++;
            }
            if (counter == 0)
                toBan.append(character1);
        }
        System.out.println(toBan);

        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : toBan.toString().toCharArray()) {
            charSet.add(c);
        }

        toBan = new StringBuilder();
        for (Character character : charSet) {
            toBan.append(character);
        }

        System.out.println(toBan);

        dico.keepOnlyWordNotContaining(toBan.toString().toCharArray());
    }

    public <T> List<T> getUndupedElements(List<T> values) {
        List<T> distinctValues = values.stream().distinct().collect(Collectors.toList());
        List<Object> toRemove = new ArrayList<>();

        for(Object object : distinctValues) {
            values.remove(object);
            if(values.indexOf(object) != -1) {
                toRemove.add(object);
            }
        }

        distinctValues.removeIf(toRemove::contains);

        return distinctValues;
    }
}
