package listes;

import entities.Couleur;
import entities.Pion;
import entities.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CombinaisonSecrete extends EntityList<Pion> {

    private List<Boolean> pionsDecouverts;

    public CombinaisonSecrete(int tailleMax) {
        super(tailleMax);
        pionsDecouverts = new ArrayList<>(tailleMax);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < getTaille(); i++){
            Pion pion = getElement(i);
            if (pionsDecouverts.get(i)){
                result.append(pion.toString());
            } else {
                result.append('-');
            }
        }
        for (int i = getTaille() ; i < getTailleMax() ; i++){
            result.append('.');
        }

        return result.toString();
    }

    @Override
    public void addElement(Pion p) throws IndexOutOfBoundsException{
        try{
            super.addElement(p);
            pionsDecouverts.add(Boolean.FALSE);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }


    private HashMap<Couleur,Integer> initCptCouleurs(){
        /*
        cptCouleur est un regroupement de compteurs pour chaque couleur du jeu.
        Lorsqu'un pion est validé (bonne couleur bon endroit) ou de la bonne couleur mais au mauvais endroit,
        le compteur associé à sa couleur est incrémenté.
        */
        HashMap<Couleur, Integer> cptCouleurs = new HashMap<>();
        for (int i = 1; i <= 8;i++){
            cptCouleurs.put(Couleur.getByIndex(i),0);
        }
        return cptCouleurs;
    }

    //? J'ai eu besoin de la methode contains mais elle n'est plus accessible car Liste<> n'étends pas List<>.
    private Boolean contains(Pion pionCherche, HashMap<Couleur,Integer> c){
        /*
        Lorsque qu'on cherche à déterminer si un pion est de la bonne couleur mais au mauvais endroit,
        on crée une copie de cptCouleur (pour éviter les effets de bord) puis
        à chaque fois qu'un pion de la combinaison secrete est de la même couleur que le pion à déterminer,
        on décrémente la copie du compteur associé à la couleur en question.
        Si lors d'une de ces occurences de pions égaux en couleur, le compteur associé à la couleur est nul (0),
        alors cela signifie qu'un NOUVEAU pion de cette couleur est contenu dans la combinaison secrete.
        */
        HashMap<Couleur,Integer>cptCouleurs = new HashMap<>(c);

        for(int i = 0; i < getTaille(); i++){
            boolean pionsMemeCouleur = getElement(i).equals(pionCherche);
            boolean nouveauPionCouleur = cptCouleurs.get(getElement(i).getCouleurPion()) == 0;

            if(pionsMemeCouleur && nouveauPionCouleur){
                 return Boolean.TRUE;
            } else if (pionsMemeCouleur){
                addCptCouleur(c,pionCherche,-1);
            }
        }
        return Boolean.FALSE;
    }

    //? pouvoir substituer une couleur à un pion (ici que le type du deuxième arg soit couleur mais que dans l'appel je puisse metre un pion ==> allegement de la syntaxe)
    private HashMap<Couleur,Integer> addCptCouleur(HashMap<Couleur,Integer> c, Pion p, int add){
        c.put(p.getCouleurPion(), c.get(p.getCouleurPion()) + add);
        return c;
    }

    public TentativeResult compare(Combinaison c) throws IndexOutOfBoundsException{
        TentativeResult result = new TentativeResult(getTailleMax());
        HashMap<Couleur, Integer> cptCouleurs = initCptCouleurs();

        List<Boolean> pionsDecourvertsCompare = new ArrayList<>();

        if (c.getTaille() != getTaille()){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < c.getTaille(); i++){
            Pion pionTentative = c.getElement(i);
            Pion pionSecret = getElement(i);

            if (pionTentative.equals(pionSecret)){
                pionsDecourvertsCompare.add(Boolean.TRUE);
                addCptCouleur(cptCouleurs,pionTentative,1);
                result.addElement(Result.VALIDE);
            }
            else if (contains(pionTentative,cptCouleurs)){
                pionsDecourvertsCompare.add(Boolean.FALSE);
                addCptCouleur(cptCouleurs,pionTentative,1);
                result.addElement(Result.COULEUR);
            }
            else {
                pionsDecourvertsCompare.add(Boolean.FALSE);
                result.addElement(Result.INVALIDE);
            }
        }
        pionsDecouverts = pionsDecourvertsCompare;
        return result;
    }

    public Boolean decouverte(){
        for (Boolean b : pionsDecouverts){
            if (!b){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public String toIndexString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < getTaille() ; i++){
            result.append(getElement(i).getCouleurPion().getIndex());
        }

        for (int i = getTaille() ; i < getTailleMax() ; i++){
            result.append(".");
        }

        return result.toString();
    }

    //? SUPPRIMER LA METHODE GETELEMENT (dispensable mais est-ce possible avec un héritage ou alors passer par la délégation)
}
