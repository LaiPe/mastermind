package listes;

import entities.Pion;

import java.util.ArrayList;
import java.util.List;

public class CombinaisonSecrete extends Combinaison {

    private final List<Boolean> pionsDecouverts;
    private final int tailleMax;
    public CombinaisonSecrete(int tailleMax) {
        super(tailleMax);
        pionsDecouverts = new ArrayList<>(tailleMax);
        this.tailleMax = tailleMax;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i =0;i < super.getTaille();i++){
            Pion p = super.getPion(i);
            if (pionsDecouverts.get(i)){
                result.append(p.toString());
            } else{
                result.append('-');
            }
        }
        for (int i = super.getTaille() ; i < tailleMax ; i++){
            result.append('.');
        }

        return result.toString();
    }

    @Override
    public void addPion(Pion p) throws IndexOutOfBoundsException{
        try{
            super.addPion(p);
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
            if (c.getPion(i).equals(super.getPion(i))){
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

    //SUPPRIMER LA METHODE GETPION (dispensable mais est-ce possible avec un héritage ou alors passer par la délégation)
}
