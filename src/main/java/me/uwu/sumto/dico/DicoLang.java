package me.uwu.sumto.dico;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DicoLang {

    //on ajoutera les autres langues ici plus tard
    FR("Français", "baguette", "fr");

    public final String name, file, identifier;

    public static DicoLang getDicoLangByIdentifier(String identifier) {
        for (DicoLang dico : DicoLang.values()) {
            if (dico.identifier.equalsIgnoreCase(identifier))
                return dico;
        }
        return null;
    }
}
