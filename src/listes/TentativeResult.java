package listes;

import entities.Result;

public class TentativeResult extends Liste<Result>{
    public TentativeResult(int tailleMax) {
        super(tailleMax);
    }

    private String accordPionDescription(int cpt){
        if (cpt == 0){
            return("aucun pion n'est ");
        } else if (cpt == 1){
            return("un pion est ");
        } else{
            return(cpt + " pions sont ");
        }
    }
    public String description(){
        int valide = 0;
        int couleur = 0;
        int invalide = 0;

        for (int i = 0; i < getTaille(); i++){
            if (getElement(i) == Result.VALIDE){
                valide++;
            } else if (getElement(i) == Result.COULEUR) {
                couleur++;
            } else {
                invalide++;
            }
        }

        return "Dans cette tentative, " +
                accordPionDescription(valide) +
                "de la bonne couleur et au bon endroit, et " +
                accordPionDescription(couleur) +
                "de la bonne couleur mais au mauvais endroit.\n" +
                "Donc " +
                accordPionDescription(invalide) +
                "de la mauvaise couleur !";
    }
}
