package entities;

public enum Couleur {
    NOIR(1, "\u001B[30m", "Noir"),
    ROUGE(2, "\u001B[31m", "Rouge"),
    VERT(3, "\u001B[32m", "Vert"),
    JAUNE(4, "\u001B[33m", "Jaune"),
    BLEU(5, "\u001B[34m", "Bleu"),
    MAGENTA(6, "\u001B[35m", "Magenta"),
    CYAN(7, "\u001B[36m", "Cyan"),
    BLANC(8, "\u001B[97m", "Blanc"),
    RESET(9, "\u001B[0m", "");

    private final int index;
    private final String codeANSI;
    private final String nom;

    Couleur(int index, String codeANSI, String nom) {
        this.index = index;
        this.codeANSI = codeANSI;
        this.nom = nom;
    }

    public static Couleur getByIndex(int index) {
        for (Couleur couleur : Couleur.values()) {
            if (couleur.index == index) {
                return couleur;
            }
        }
        throw new IllegalArgumentException("Index invalide : " + index);
    }

    @Override
    public String toString() {
        return codeANSI;
    }

    public String getNom() {
        return nom;
    }
}
