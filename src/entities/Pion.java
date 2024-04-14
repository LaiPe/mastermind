package entities;

public class Pion {
    private Couleur couleurPion;

    public Pion (Couleur couleurPion){
        this.couleurPion = couleurPion;
    }

    public Couleur getCouleurPion() {
        return couleurPion;
    }

    @Override
    public String toString() {
        return couleurPion.getCodeANSI()+"O"+Couleur.BLANC.getCodeANSI();
    }

    @Override
    public boolean equals(Object obj) {
        // Vérification de la référence de l'objet
        if (this == obj) {
            return true;
        }

        // Vérification si l'objet passé est null ou n'est pas du même type
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Comparaison
        return getCouleurPion() == ((Pion) obj).getCouleurPion();
    }
}

