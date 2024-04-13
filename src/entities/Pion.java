package entities;

public class Pion {
    private Couleur couleurPion;

    public Pion (Couleur couleurPion){
        this.couleurPion = couleurPion;
    }

    @Override
    public String toString() {
        return couleurPion.getCodeANSI()+"O"+Couleur.BLANC.getCodeANSI();
    }
}
