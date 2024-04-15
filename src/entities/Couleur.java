package entities;

public enum Couleur {
    NOIR(1,"\u001B[30m"),
    ROUGE(2,"\u001B[31m"),
    VERT(3,"\u001B[32m"),
    JAUNE(4,"\u001B[33m"),
    BLEU(5,"\u001B[34m"),
    MAGENTA(6,"\u001B[35m"),
    CYAN(7,"\u001B[36m"),
    BLANC(8,"\u001B[37m");

    private final int index;
    private final String codeANSI;

    Couleur(int index, String codeANSI){
        this.index = index;
        this.codeANSI = codeANSI;
    }

    public static Couleur getByIndex(int index) {
        for (Couleur couleur : Couleur.values()) {
            if (couleur.index == index) {
                return couleur;
            }
        }
        throw new IllegalArgumentException("Index invalide : " + index);
    }
    public String getCodeANSI() {
        return codeANSI;
    }
}
