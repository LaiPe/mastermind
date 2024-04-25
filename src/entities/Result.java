package entities;

public enum Result {
    VALIDE(Couleur.VERT + "\u2713" + Couleur.RESET),
    COULEUR(Couleur.MAGENTA + "\u2248" + Couleur.RESET),
    INVALIDE(Couleur.ROUGE + "\u2717" + Couleur.RESET);

    private final String string;
    Result(String string){
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
