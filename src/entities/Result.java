package entities;

public enum Result {
    VALIDE(Couleur.VERT.getCodeANSI() + '\u2713' + Couleur.BLANC.getCodeANSI()),
    COULEUR(Couleur.MAGENTA.getCodeANSI() + '\u2248' + Couleur.BLANC.getCodeANSI()),
    INVALIDE(Couleur.ROUGE.getCodeANSI() + '\u2717' + Couleur.BLANC.getCodeANSI());

    String string;
    Result(String string){
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
