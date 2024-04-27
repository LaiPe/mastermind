package listes;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityListe<T> implements Liste<T>{

    private final List<T> liste;
    private final int tailleMax;

    public EntityListe(int tailleMax){
        this.liste = new ArrayList<>(tailleMax);
        this.tailleMax = tailleMax;
    }

    @Override
    public int getTaille(){
        return liste.size();
    }
    @Override
    public int getTailleMax() {
        return tailleMax;
    }
    @Override
    public T getElement(int index) throws IndexOutOfBoundsException{
        if (index < liste.size()) {
            return liste.get(index);
        }
        else {
            System.err.println("Index invalide !");
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public void addElement(T element) throws IndexOutOfBoundsException{
        if (liste.size() < tailleMax) {
            liste.add(element);
        } else {
            System.err.println("Liste pleine !");
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < liste.size() ; i++){
            result.append(liste.get(i).toString());
        }

        for (int i = liste.size() ; i < tailleMax ; i++){
            result.append(".");
        }

        return result.toString();
    }
}
