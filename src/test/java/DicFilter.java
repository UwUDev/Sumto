import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DicFilter {
    public static void main(String[] args) throws IOException {
        List<String> dico = Files.readAllLines(new File("test_room/a.dico").toPath());
        Set<String> set = new LinkedHashSet<>(dico);
        dico.clear();
        dico.addAll(set);
        StringBuilder dicoString = new StringBuilder();
        dico.forEach(line -> dicoString.append(
                        line.replace("à", "a")
                        .replace("â", "a")
                        .replace("ä", "a")
                        .replace("é", "e")
                        .replace("è", "e")
                        .replace("ê", "e")
                        .replace("ë", "e")
                        .replace("ï", "i")
                        .replace("î", "i")
                        .replace("ô", "o")
                        .replace("ö", "o")
                        .replace("ù", "u")
                        .replace("û", "u")
                        .replace("ü", "u")
                        .replace("ÿ", "y")
                        .replace("ç", "c")
                                .replace("œ", "oe")
                                .replace("æ", "ae")
                                .replace(" ", "")
                                .replace("-", "")
                                .replace("'", "")
                                .replace("’", "")
                                .replace("(", "")
                                .replace(")", "")
                                .replace("!", "")
                                .replace("?", "")
                                .replace(";", "")
                                .replace(":", "")
                                .replace(",", "")
                                .replace(".", "")
                        .toUpperCase()
        ).append("\n"));

        FileWriter myWriter = new FileWriter("test_room/b.dico");
        myWriter.write(dicoString.toString());
        myWriter.close();
    }
}
