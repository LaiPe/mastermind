package listes;


public abstract class EntityList<T> extends CapedList<T>{

    public EntityList(int tailleMax){
        super(tailleMax);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < getTaille() ; i++){
            result.append(getElement(i).toString());
        }

        for (int i = getTaille() ; i < getTailleMax() ; i++){
            result.append(".");
        }

        return result.toString();
    }
}
