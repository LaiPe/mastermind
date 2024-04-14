package listes;

import entities.Pion;
import entities.Result;

import java.util.ArrayList;
import java.util.List;

public class CombinaisonSecrete extends Liste<Pion> {

    private final List<Boolean> pionsDecouverts;

    public CombinaisonSecrete(int tailleMax) {
        super(tailleMax);
        pionsDecouverts = new ArrayList<>(tailleMax);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < super.getTaille(); i++){
            Pion pion = super.getElement(i);
            if (pionsDecouverts.get(i)){
                result.append(pion.toString());
            } else {
                result.append('-');
            }
        }
        for (int i = super.getTaille() ; i < super.getTailleMax() ; i++){
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

    //J'ai eu besoin de la methode contains mais elle n'est plus accessible car Liste<> n'étends pas List<>.
    private Boolean contains(Pion pionCherche){
        for(int i = 0; i < getTaille(); i++){
            if(getElement(i).getCouleurPion() == pionCherche.getCouleurPion()){
                 return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    public TentativeResult compare(Combinaison c) throws IndexOutOfBoundsException{
        TentativeResult result = new TentativeResult(getTailleMax());

        if (c.getTaille() != getTaille()){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < c.getTaille(); i++){
            if (c.getElement(i).equals(getElement(i))){
                pionsDecouverts.set(i,Boolean.TRUE);
                result.addElement(Result.VALIDE);
            } else if (contains(c.getElement(i))){
                result.addElement(Result.COULEUR);
            }
            else {
                result.addElement(Result.INVALIDE);
            }
        }
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

    //SUPPRIMER LA METHODE GETELEMENT (dispensable mais est-ce possible avec un héritage ou alors passer par la délégation)
}
