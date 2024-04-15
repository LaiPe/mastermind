package entities;

public enum Result {
    VALIDE(Couleur.VERT + "\u2713" + Couleur.BLANC),
    COULEUR(Couleur.MAGENTA + "\u2248" + Couleur.BLANC),
    INVALIDE(Couleur.ROUGE + "\u2717" + Couleur.BLANC);

    private final String string;
    Result(String string){
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
