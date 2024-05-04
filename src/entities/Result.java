package entities;

public enum Result {
    VALIDE(Couleur.VERT + "\u2713" + Couleur.RESET, 2),
    COULEUR(Couleur.MAGENTA + "\u2248" + Couleur.RESET,1),
    INVALIDE(Couleur.ROUGE + "\u2717" + Couleur.RESET,0);

    private final String string;
    private final int index;
    Result(String string, int index){
        this.string = string;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    
    @Override
    public String toString() {
        return string;
    }
}
