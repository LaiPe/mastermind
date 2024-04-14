package entities;

public enum Couleur {
    NOIR(1,"Noir","\u001B[30m"),
    ROUGE(2,"Rouge","\u001B[31m"),
    VERT(3,"Vert","\u001B[32m"),
    JAUNE(4,"Jaune","\u001B[33m"),
    BLEU(5,"Bleu","\u001B[34m"),
    MAGENTA(6,"Magenta","\u001B[35m"),
    CYAN(7,"Cyan","\u001B[36m"),
    BLANC(8,"Blanc","\u001B[37m");

    private final int index;
    private final String nom;
    private final String codeANSI;

    Couleur(int index, String nom, String codeANSI){
        this.index = index;
        this.nom = nom;
        this.codeANSI = codeANSI;
    }

    public int getIndex() {
        return index;
    }

    public String getNom() {
        return nom;
    }

    public String getCodeANSI() {
        return codeANSI;
    }
}
