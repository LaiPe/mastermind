package listes;

import entities.Pion;

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


    public void compare(Combinaison c) throws IndexOutOfBoundsException{
        if (c.getTaille() != super.getTaille()){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < super.getTaille(); i++){
            if (c.getElement(i).equals(super.getElement(i))){
                pionsDecouverts.set(i,Boolean.TRUE);
            }
        }
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
