package listes;

public interface Liste<T> {
    public int getTaille();
    public int getTailleMax();
    public T getElement(int index) throws IndexOutOfBoundsException;
    public void addElement(T element) throws IndexOutOfBoundsException;
}
