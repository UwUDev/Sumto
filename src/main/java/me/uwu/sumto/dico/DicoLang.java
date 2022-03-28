package me.uwu.sumto.dico;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public enum DicoLang {
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
